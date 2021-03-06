package sample;

import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TableView<Student>studentsTableView;

    @FXML
    private TableColumn<Student, Integer>idTableColum;

    @FXML
    private TableColumn<Student, String> nameTableColum;
    @FXML
    private TableColumn<Student, String> surnameTableColum;
    @FXML
    private TableColumn<Student, String> phoneTableColum;
    @FXML
    private TableColumn<Student, String> emailTableColum;



    @Override
    public void initialize(URL location, ResourceBundle resources) { //Suveikia pries uploadinant
        Connection connection = MyDbUtils.createConnection();
        if (connection != null) {

            List<Student> students = getStudents(connection);

            idTableColum.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
            nameTableColum.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
            surnameTableColum.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
            phoneTableColum.setCellValueFactory(new PropertyValueFactory<Student, String>("phone"));
            emailTableColum.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));

            studentsTableView.setItems(FXCollections.observableList(students));

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
