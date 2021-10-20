package application.application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

public class UserDataAccessor {
    // in real life, use a connection pool....
    private Connection connection ;
    /*
    public UserDataAccessor(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(dbURL, user, password);
    }
    */
    public UserDataAccessor(String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
        connection = DriverManager.getConnection(dbURL, user, password);
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public List<User> getUserList() throws SQLException {
        try (
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("select * from usrData");
        ){
            List<User> userList = new ArrayList<>();
            while (rs.next()) {
            	int userId = rs.getObject("userID", Integer.class);
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phoneNum = rs.getString("phoneNum");
                String emailAd = rs.getString("emailAd");
                String passW = rs.getString("passW");
                String acctType = rs.getString("acctType");
                User user = new User(userId, firstName, lastName, phoneNum, emailAd, passW, acctType);
                userList.add(user);
            }
            return userList ;
        } 
    }

    // other methods, eg. addUser(...) etc
}
