package application.application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Hotel {
	// String hotel_name
    private final StringProperty hotel_name = new SimpleStringProperty(this, "hotel_name");
    public StringProperty hotel_nameProperty() {
        return hotel_name;
    }
    public final String getHotel_name() {
        return hotel_nameProperty().get();
    }
    public final void setHotel_name(String hotel_name) {
    	hotel_nameProperty().set(hotel_name);
    }
    // String room_type
    private final StringProperty room_type = new SimpleStringProperty(this, "room_type");
    public StringProperty room_typeProperty() {
        return room_type;
    }
    public final String getRoom_type() {
        return room_typeProperty().get();
    }
    public final void setRoom_type(String room_type) {
    	room_typeProperty().set(room_type);
    }
    // String amenity_pool
    private final StringProperty amenity_pool = new SimpleStringProperty(this, "amenity_pool");
    public StringProperty amenity_poolProperty() {
        return amenity_pool;
    }
    public final String getAmenity_pool() {
        return amenity_poolProperty().get();
    }
    public final void setAmenity_pool(String amenity_pool) {
    	amenity_poolProperty().set(amenity_pool);
    }
    // String amenity_gym
    private final StringProperty amenity_gym = new SimpleStringProperty(this, "amenity_gym");
    public StringProperty amenity_gymProperty() {
        return amenity_gym;
    }
    public final String getAmenity_gym() {
        return amenity_gymProperty().get();
    }
    public final void setAmenity_gym(String amenity_gym) {
    	amenity_gymProperty().set(amenity_gym);
    }
    // String amenity_spa
    private final StringProperty amenity_spa = new SimpleStringProperty(this, "amenity_spa");
    public StringProperty amenity_spaProperty() {
        return amenity_spa;
    }
    public final String getAmenity_spa() {
        return amenity_spaProperty().get();
    }
    public final void setAmenity_spa(String amenity_spa) {
    	amenity_spaProperty().set(amenity_spa);
    }
    // String amenity_business_office
    private final StringProperty amenity_business_office = new SimpleStringProperty(this, "amenity_business_office");
    public StringProperty amenity_business_officeProperty() {
        return amenity_business_office;
    }
    public final String getAmenity_business_office() {
        return amenity_business_officeProperty().get();
    }
    public final void setAmenity_business_office(String amenity_business_office) {
    	amenity_business_officeProperty().set(amenity_business_office);
    }
    // String amenity_wifi
    private final StringProperty amenity_wifi = new SimpleStringProperty(this, "amenity_wifi");
    public StringProperty amenity_wifiProperty() {
        return amenity_wifi;
    }
    public final String getAmenity_wifi() {
        return amenity_wifiProperty().get();
    }
    public final void setAmenity_wifi(String amenity_wifi) {
    	amenity_wifiProperty().set(amenity_wifi);
    }
    // int available_rooms
    private final IntegerProperty available_rooms = new SimpleIntegerProperty(this, "available_rooms");
    public IntegerProperty available_roomsProperty() {
        return available_rooms;
    }
    public final Integer getAvailable_rooms() {
        return available_roomsProperty().get();
    }
    public final void setAvailable_rooms(Integer available_rooms) {
    	available_roomsProperty().set(available_rooms);
    }
    // String hotelLocation
    private final StringProperty hotel_location = new SimpleStringProperty(this, "hotel_location");
    public StringProperty hotel_locationProperty() {
        return hotel_location;
    }
    public final String getHotel_location() {
        return hotel_locationProperty().get();
    }
    public final void setHotel_location(String hotel_location) {
    	hotel_locationProperty().set(hotel_location);
    }
    // String price_per_day
    private final StringProperty price_per_day = new SimpleStringProperty(this, "price_per_day");
    public StringProperty price_per_dayProperty() {
        return price_per_day;
    }
    public final String getPrice_per_day() {
        return price_per_dayProperty().get();
    }
    public final void setPrice_per_day(String price_per_day) {
    	price_per_dayProperty().set(price_per_day);
    }
    // int weekend_differential
    private final IntegerProperty weekend_differential = new SimpleIntegerProperty(this, "weekend_differential");
    public IntegerProperty weekend_differentialProperty() {
        return weekend_differential;
    }
    public final Integer getWeekend_differential() {
        return weekend_differentialProperty().get();
    }
    public final void setWeekend_differential(Integer weekend_differential) {
    	weekend_differentialProperty().set(weekend_differential);
    }
    // String total_price
    private final StringProperty total_price = new SimpleStringProperty(this, "total_price");
    public StringProperty total_priceProperty() {
        return total_price;
    }
    public final String getTotal_price() {
        return total_priceProperty().get();
    }
    public final void setTotal_price(String total_price) {
    	total_priceProperty().set(total_price);
    }
    
    public Hotel() {}
    
    public Hotel(String hotel_name, String room_type, String amenity_pool, String amenity_gym, String amenity_spa, String amenity_business_office, String amenity_wifi, int available_rooms, String hotel_location, String price_per_day, int weekend_differential, String total_price) {
    	setHotel_name(hotel_name);
    	setRoom_type(room_type);
    	setAmenity_pool(amenity_pool);
    	setAmenity_gym(amenity_gym);
    	setAmenity_spa(amenity_spa);
    	setAmenity_business_office(amenity_business_office);
    	setAmenity_wifi(amenity_wifi);
    	setAvailable_rooms(available_rooms);
    	setHotel_location(hotel_location);
    	setPrice_per_day(price_per_day);
    	setWeekend_differential(weekend_differential);
    	setTotal_price(total_price);
    }
}
