package MySQL;
import java.sql.*;

public class JDBC_Implementation_in_Java    {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/mysql";/*jdbc:mysql://hostname:port/database_name*/
        String username = "root";
        String password = "MM";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            // Example: Execute SQL statement
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM User");

/*            while (resultSet.next()) {
                int uid = resultSet.getInt("uid");
                String phone_number = resultSet.getString("phone_number");
                // ... process results
            }*/

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
