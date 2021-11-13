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
    
    public void addUser(int userId, String firstName, String lastName, String phoneNum, String emailAd, String passW, String acctType) throws SQLException {
    	String query = "INSERT INTO usrData (userID, firstName, lastName, phoneNum, emailAd, passW, acctType) values (?, ?, ?, ?, ?, ?, ?)";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(query);){
    		preparedStatement.setObject(1, userId);
    		preparedStatement.setString(2, firstName);
    		preparedStatement.setString(3, lastName);
    		preparedStatement.setString(4, phoneNum);
    		preparedStatement.setString(5, emailAd);
    		preparedStatement.setString(6, passW);
    		preparedStatement.setString(7, acctType);
    		preparedStatement.executeUpdate();
    		connection.close();
    	}
    }
    
    public int makeNewId() throws SQLException {
    	//Looks at the row count in usrData
    	String query = "SELECT COUNT(*) FROM usrData";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(query);){
    		int usrCnt = 0;
    		ResultSet rs = preparedStatement.executeQuery();
    		while (rs.next()) {
    			//Just get the Count and add 1
    			usrCnt = rs.getInt(1) + 1;
    		}
    		return usrCnt;
    	}
    }
        

    // other methods, eg. addUser(...) etc
}
