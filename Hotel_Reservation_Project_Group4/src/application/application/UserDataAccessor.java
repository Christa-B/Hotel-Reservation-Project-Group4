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
    				//connection.close();
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
    		//connection.close();
    	}
    }
    
    public int makeNewId() throws SQLException {
    	int errNum = -1; //Error case
    	String giveRow = "SELECT COUNT(*) FROM usrData"; //Gets the row amount
    	String doSort = "select userID from usrData order by userID"; //sorts the userId numbers
    	try(PreparedStatement doSortStatement = connection.prepareStatement(doSort);
    		PreparedStatement giveRowStatement = connection.prepareStatement(giveRow);){
    		int usrCnt = 0;
    		int curNum = 0;
    		int lstNum = 0;
    		int numRes = 0;
    		ResultSet rows = giveRowStatement.executeQuery();
    		ResultSet rs = doSortStatement.executeQuery();
    		
    		rows.next();
    		
    		if(rows.getInt(1) == 0) //For empty usrData set case
    			return usrCnt + 1;
    		
    		if(rows.getInt(1) == 1) //For singular entry usrData set case
    			return usrCnt + 2;
    		
    		rs.next(); //Starts the row probing
    		for(int i = 0; i < rows.getInt(1); i++) {
    			lstNum = rs.getObject("userID", Integer.class);
    			
    			//To ensure that the sql cursor doesn't go beyond the scope of the row count
    			if(i == rows.getInt(1) - 1)
    				return lstNum + 1;
    			
    			rs.next();
    			curNum = rs.getObject("userID", Integer.class);
    			
    			//This is for gap situations, i.e. userId 5 |gap| userId 9
    			if(curNum - lstNum > 1) {
    				numRes = lstNum + 1;
    				return numRes; //resulting userId
    			}
    		}
    		return errNum; //Base error result, should never arrive here
    	} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	return errNum; //Base error result, should never arrive here
    }
    
    //depreciated function
//    public int makeNewId() throws SQLException {
//    	//Looks at the row count in usrData
//    	String query = "SELECT COUNT(*) FROM usrData";
//    	try(PreparedStatement preparedStatement = connection.prepareStatement(query);){
//    		int usrCnt = 0;
//    		ResultSet rs = preparedStatement.executeQuery();
//    		while (rs.next()) {
//    			//Just get the Count and add 1
//    			usrCnt = rs.getInt(1) + 1;
//    		}
//    		return usrCnt;
//    	}
//    }
    
    public void updateUser(int userId, String firstName, String lastName, String phoneNum, String emailAd, String passW, String acctType) throws SQLException {
    	String query = "UPDATE usrData SET userId = ?, firstName = ?, lastName = ?, phoneNum = ?, emailAd = ?, passW = ?, acctType = ? WHERE userId = ?;";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(query);){
    		preparedStatement.setObject(1, userId);
    		preparedStatement.setString(2, firstName);
    		preparedStatement.setString(3, lastName);
    		preparedStatement.setString(4, phoneNum);
    		preparedStatement.setString(5, emailAd);
    		preparedStatement.setString(6, passW);
    		preparedStatement.setString(7, acctType);
    		preparedStatement.setObject(8, userId);
    		preparedStatement.executeUpdate();
    		//connection.close();
    	} catch (SQLException e) {
            e.printStackTrace();
        } 
    }
    
    public void deleteUser(int userId, String emailAd, String passW) {
    	String query = "DELETE FROM usrData WHERE userId = ? AND emailAd = ? AND passW = ?;";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(query);){
    		preparedStatement.setObject(1, userId);
    		preparedStatement.setString(2, emailAd);
    		preparedStatement.setString(3, passW);
    		preparedStatement.executeUpdate();
    	} catch (SQLException e) {
            e.printStackTrace();
        } 
    }
        

    // other methods, eg. addUser(...) etc
}
