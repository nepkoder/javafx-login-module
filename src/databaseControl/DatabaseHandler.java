package databaseControl;

import java.sql.*;

public class DatabaseHandler {


    public static Connection GetDatabaseConnection() {
        Connection connection = null;
//    Connection connection;

     String dbUrl = "jdbc:mysql://localhost:3306/testing";
        String user = "root";
       String pass = "sujan";
        try {
//            driver setup for database
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, user, pass);

//            System.out.println("connection successful");

        } catch (ClassNotFoundException e) {
            e.getLocalizedMessage();
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            e.getLocalizedMessage();
        }

        return connection;
    }

    public static boolean CheckLoginUser(String uname, String pass) { //get input from login system module
        Connection connection = GetDatabaseConnection();
//        String checkQuery = "select * from registeredUser where user = ' "+uname+" ' and pass = ' "+pass+" ' ";
        String checkQuery = "select *from registeredUser where user = ? and pass = ? "; // i don't use id from database table.
        
        PreparedStatement preparedStatement = null;
        boolean status = false; //initially false

        try {
            preparedStatement = connection.prepareStatement(checkQuery);
            preparedStatement.setString(1, uname); // dynamically sending username
            preparedStatement.setString(2, pass); // sending password to checkquery statement
            ResultSet resultSet = preparedStatement.executeQuery();

            /* while (resultSet.next()) {
                return status;
            } */

            status = resultSet.next();
            preparedStatement.close();
            return status;

        } catch (SQLException e) {
//            e.getLocalizedMessage();
            e.printStackTrace();
        }
        return status;
    }

}
