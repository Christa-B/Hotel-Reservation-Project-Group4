package application.application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReservationDataAccessor {
    private Connection connection;
    
    public ReservationDataAccessor(String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
        connection = DriverManager.getConnection(dbURL, user, password);
    }
    
    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
    
    public List<Reservation> getReservations(User currentUser) throws SQLException {
    	String query = "SELECT * FROM resData WHERE userId = ?";
    	try (
    		PreparedStatement preparedStatement = connection.prepareStatement(query);	
    	){
    		preparedStatement.setInt(1, currentUser.getUserId());
    		ResultSet rs = preparedStatement.executeQuery();
    		List<Reservation> reservationList = new ArrayList<Reservation>();
    		while (rs.next()) {
    			int resId = rs.getObject("resID", Integer.class);
    			int userId = rs.getObject("userID", Integer.class);
    			String hotelName = rs.getString("hotelName");
    			Date checkInDate = Date.valueOf(rs.getString("checkInDate"));
    			Date checkOutDate = Date.valueOf(rs.getString("checkOutDate"));
    			int numRoomSel = rs.getInt("numRoomSel");
    			String typeRoom = rs.getString("typeRoom");
    			int finalPayment = rs.getInt("finalPayment");
    			int partySize = rs.getInt("partySize");
    			int hotelId = rs.getInt("hotelID");
    			Reservation currentReservation = new Reservation(resId, userId, hotelName, checkInDate, checkOutDate, numRoomSel, typeRoom, finalPayment, partySize, hotelId);
    			reservationList.add(currentReservation);
    		}
    		return reservationList;
    	}
    		
    }
    
    public void addReservation(int resId, int userId, String hotelName, Date checkInDate, Date checkOutDate, int numRoomSel, String typeRoom, int finalPayment, int partySize, int hotelId) {
    	String query = "INSERT INTO resData (resID, userID, hotelName, checkInDate, checkOutDate, numRoomSel, typeRoom, finalPayment, partySize, hotelID) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(query);){
    		preparedStatement.setObject(1, resId);
    		preparedStatement.setObject(2, userId);
    		preparedStatement.setString(3, hotelName);
    		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
    		String checkInString = dateFormat.format(checkInDate);
    		preparedStatement.setString(4, checkInString);
    		String checkOutString = dateFormat.format(checkOutDate);
    		preparedStatement.setString(5, checkOutString);
    		preparedStatement.setInt(6, numRoomSel);
    		preparedStatement.setString(7, typeRoom);
    		preparedStatement.setInt(8, finalPayment);
    		preparedStatement.setInt(9, partySize);
    		preparedStatement.setInt(10, hotelId);
    		preparedStatement.executeUpdate();
    		//connection.close();
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public int getAvailableRooms(int hotelId, String roomType) throws SQLException {
    	String query = "SELECT COUNT(*) FROM resData WHERE hotelId = ? AND typeRoom = ?";
    	String roomQuery = "SELECT * FROM hotelData WHERE hotelId = ?";
    	try (
    		PreparedStatement preparedStatementCount = connection.prepareStatement(query);
    		PreparedStatement preparedStatementRooms = connection.prepareStatement(roomQuery);
    		ResultSet rsCount = preparedStatementCount.executeQuery();
    		ResultSet rsRooms = preparedStatementRooms.executeQuery();
    		
    	){
    		List<String> roomsArray = new ArrayList<>();
    		int totalRooms = 0;
    		if(rsRooms.next()) {
    			roomsArray = Arrays.asList(rsRooms.getString("numRoomType").split(","));
    		}
    		int count = 0;
    		while(rsCount.next()) {
    			count++;
    		}
    		if (roomType.equals("Standard")) {
    			totalRooms = Integer.parseInt(roomsArray.get(0));
    			return totalRooms - count;
    		} else if (roomType.equals("Queen")) {
    			totalRooms = Integer.parseInt(roomsArray.get(1));
    			return totalRooms - count;
    		} else if (roomType.equals("King")) {
    			totalRooms = Integer.parseInt(roomsArray.get(2));
    			return totalRooms - count;
    		} else { return 0; }
    		
    	}
    		
    }
    
    public int makeNewId() throws SQLException {
    	int errNum = -1; //Error case
    	String giveRow = "SELECT COUNT(*) FROM resData"; //Gets the row amount
    	String doSort = "select resID from resData order by resID"; //sorts the resId numbers
    	try(PreparedStatement doSortStatement = connection.prepareStatement(doSort);
    		PreparedStatement giveRowStatement = connection.prepareStatement(giveRow);){
    		int resCnt = 0;
    		int curNum = 0;
    		int lstNum = 0;
    		int numRes = 0;
    		ResultSet rows = giveRowStatement.executeQuery();
    		ResultSet rs = doSortStatement.executeQuery();
    		
    		rows.next();
    		
    		if(rows.getInt(1) == 0) //For empty resData set case
    			return resCnt + 1;
    		
    		if(rows.getInt(1) == 1) //For singular entry resData set case
    			return resCnt + 2;
    		
    		rs.next(); //Starts the row probing
    		for(int i = 0; i < rows.getInt(1); i++) {
    			lstNum = rs.getObject("resID", Integer.class);
    			
    			//To ensure that the sql cursor doesn't go beyond the scope of the row count
    			if(i == rows.getInt(1) - 1)
    				return lstNum + 1;
    			
    			rs.next();
    			curNum = rs.getObject("resID", Integer.class);
    			
    			//This is for gap situations, i.e. resId 5 |gap| resId 9
    			if(curNum - lstNum > 1) {
    				numRes = lstNum + 1;
    				return numRes; //resulting resId
    			}
    		}
    		return errNum; //Base error result, should never arrive here
    	} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	return errNum; //Base error result, should never arrive here
    }
}
