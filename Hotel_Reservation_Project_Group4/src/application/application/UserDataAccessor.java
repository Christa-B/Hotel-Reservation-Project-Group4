package application.application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

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

    public User getUser(String inputEmailAd, String inputPassW) throws SQLException {
    	// Initialize two queries, one to check if email exists, two to retrieve all the data
    	String emailQuery = "SELECT * FROM usrData WHERE emailAd = ?";
        String query = "SELECT * FROM usrData WHERE emailAd = ? AND passW = ?";
    	try (
    		PreparedStatement emailStatement = connection.prepareStatement(emailQuery);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
    		emailStatement.setString(1, inputEmailAd);
    		ResultSet emailRs = emailStatement.executeQuery();
    		if ((emailRs.next() == false)) {
    			connection.close();
    			return null;
    		} else {
    			preparedStatement.setString(1, inputEmailAd);
    			preparedStatement.setString(2, inputPassW);
    			ResultSet rs = preparedStatement.executeQuery();
    			if (rs.next()) {
    				int userId = rs.getObject("userID", Integer.class);
    				String firstName = rs.getString("firstName");
    				String lastName = rs.getString("lastName");
    				String phoneNum = rs.getString("phoneNum");
    				String emailAd = rs.getString("emailAd");
    				String passW = rs.getString("passW");
    				String acctType = rs.getString("acctType");
    				User currentUser = new User(userId, firstName, lastName, phoneNum, emailAd, passW, acctType);
    				connection.close();
    				return currentUser;
    			} else { connection.close(); return new User(0, "exists-but-passW-is-wrong", "", "", "", "", "");}
    		}
    	}
    }

    // other methods, eg. addUser(...) etc
}
