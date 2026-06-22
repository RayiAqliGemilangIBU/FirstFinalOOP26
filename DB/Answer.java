import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    private static final String URL = "jdbc:mysql://oop.ibu.edu.ba:3306/oopgroup1?allowPublicKeyRetrieval=true";
    private static final String USER = "oopuser";
    private static final String PASSWORD = "ooppassWD";

    public static void getUsers(String name, int id) {
        String query = "SELECT id, name FROM users WHERE name != ? AND id <= ?";


        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Isi parameter secara manual terlebih dahulu
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String userName = resultSet.getString("name");
                System.out.println(userId + "-" + userName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        getUsers("Becir", 8);
    }
}