package application.application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelDataAccessor {
private Connection connection;
    
    public HotelDataAccessor(String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
        connection = DriverManager.getConnection(dbURL, user, password);
    }
    
    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
    
    public List<Hotel> getHotels(String location) throws SQLException {
    	String query;
    	if (!location.equals("")) {
    	query = "SELECT * FROM hotelData WHERE location = ?";
    	} else {query = "SELECT * FROM hotelData";}
    	try (
    		PreparedStatement preparedStatement = connection.prepareStatement(query);	
    	){
    		if (!location.equals("")) {
    		preparedStatement.setString(1, location);
    		}
    		ResultSet rs = preparedStatement.executeQuery();
    		List<Hotel> hotelList = new ArrayList<Hotel>();
    		while (rs.next()) {
    			String hotel_name = rs.getString("hotelName");
    			String room_type = rs.getString("roomtype");
    			String amenity_pool = rs.getString("pool");
    			String amenity_gym = rs.getString("gym");
    			String amenity_spa = rs.getString("spa");
    			String amenity_business_office = rs.getString("buisnessOffice");
    			String amenity_wifi = rs.getString("wifi");
    			int available_rooms = rs.getObject("totalRooms", Integer.class);
    			String hotel_location = rs.getString("location");
    			String price_per_day = rs.getString("price");
    			int weekend_differential = rs.getObject("weekendDiff", Integer.class);
    			String total_price = "0";
    			Hotel currentHotel = new Hotel(hotel_name, room_type, amenity_pool, amenity_gym, amenity_spa, amenity_business_office, amenity_wifi, available_rooms, hotel_location, price_per_day, weekend_differential, total_price);
    			hotelList.add(currentHotel);
    		}
    		return hotelList;
    	}
    		
    }
}
