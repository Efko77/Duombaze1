package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) { //Suveikia pries uploadinant
        Connection connection = MyDbUtils.createConnection();
        if (connection != null) {

            List<Student> students = getStudents(connection);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Prisijungeme" + students.size());
            alert.show();
        }
    }

    private List<Student> getStudents(Connection connection) {

        List<Student> students = new ArrayList<>();
        try {
            Statement statement = connection.createStatement(); // susikuriame steitmenta pasiruosiam ikelimui duomenu
            ResultSet resultSet = statement.executeQuery("SELECT * FROM student"); //Query nes nezinome kiek duomenu bus
            while (resultSet.next()) {
                Student student = new Student(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("surname"), resultSet.getString("phone"), resultSet.getString("email"));
                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;


    }
}
