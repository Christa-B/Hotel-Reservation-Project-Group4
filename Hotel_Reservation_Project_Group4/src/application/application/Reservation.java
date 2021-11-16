package application.application;

import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// Reservation details: resID, userID, hotelName, checkInDate, checkOutDate, numRoomSel, typeRoom, finalPayment, partySize, hotelID
public class Reservation {
	// int resId
    private final IntegerProperty resId = new SimpleIntegerProperty(this, "resId");
    public IntegerProperty resIdProperty() {
        return resId;
    }
    public final int getResId() {
        return resIdProperty().get();
    }
    public final void setResId(int resId) {
        resIdProperty().set(resId);
    }
	// int userId
    private final IntegerProperty userId = new SimpleIntegerProperty(this, "userId");
    public IntegerProperty userIdProperty() {
        return userId;
    }
    public final int getUserId() {
        return userIdProperty().get();
    }
    public final void setUserId(int userId) {
        userIdProperty().set(userId);
    }
	// String hotelName
    private final StringProperty hotelName = new SimpleStringProperty(this, "hotelName");
    public StringProperty hotelNameProperty() {
        return hotelName;
    }
    public final String getHotelName() {
        return hotelNameProperty().get();
    }
    public final void setHotelName(String firstName) {
    	hotelNameProperty().set(firstName);
    }
    // Date checkInDate
    private final ObjectProperty<Date> checkInDate = new SimpleObjectProperty<Date>(this, "checkInDate");
    public ObjectProperty<Date> checkInDateProperty() {
        return checkInDate;
    }
    public final Date getCheckInDate() {
        return checkInDateProperty().get();
    }
    public final void setCheckInDate(Date checkInDate) {
    	checkInDateProperty().set(checkInDate);
    }
    // Date checkOutDate
    private final ObjectProperty<Date> checkOutDate = new SimpleObjectProperty<Date>(this, "checkOutDate");
    public ObjectProperty<Date> checkOutDateProperty() {
        return checkOutDate;
    }
    public final Date getCheckOutDate() {
        return checkOutDateProperty().get();
    }
    public final void setCheckOutDate(Date checkOutDate) {
    	checkOutDateProperty().set(checkOutDate);
    }
    // int numRoomSel
    private final IntegerProperty numRoomSel = new SimpleIntegerProperty(this, "numRoomSel");
    public IntegerProperty numRoomSelProperty() {
        return numRoomSel;
    }
    public final int getNumRoomSel() {
        return numRoomSelProperty().get();
    }
    public final void setNumRoomSel(int numRoomSel) {
    	numRoomSelProperty().set(numRoomSel);
    }
    // String typeRoom
    private final StringProperty typeRoom = new SimpleStringProperty(this, "typeRoom");
    public StringProperty typeRoomProperty() {
        return typeRoom;
    }
    public final String getTypeRoom() {
        return typeRoomProperty().get();
    }
    public final void setTypeRoom(String typeRoom) {
    	typeRoomProperty().set(typeRoom);
    }
    // int finalPayment
    private final IntegerProperty finalPayment = new SimpleIntegerProperty(this, "finalPayment");
    public IntegerProperty finalPaymentProperty() {
        return finalPayment;
    }
    public final int getFinalPayment() {
        return finalPaymentProperty().get();
    }
    public final void setFinalPayment(int finalPayment) {
    	finalPaymentProperty().set(finalPayment);
    }
    // int partySize
    private final IntegerProperty partySize = new SimpleIntegerProperty(this, "partySize");
    public IntegerProperty partySizeProperty() {
        return partySize;
    }
    public final int getPartySize() {
        return partySizeProperty().get();
    }
    public final void setPartySize(int partySize) {
    	partySizeProperty().set(partySize);
    }
    // int hotelId
    private final IntegerProperty hotelId = new SimpleIntegerProperty(this, "hotelId");
    public IntegerProperty hotelIdProperty() {
        return hotelId;
    }
    public final int getHotelId() {
        return hotelIdProperty().get();
    }
    public final void setHotelId(int hotelId) {
    	hotelIdProperty().set(hotelId);
    }
    
    public Reservation() {}
    
    public Reservation(int resId, int userId, String hotelName, Date checkInDate, Date checkOutDate, int numRoomSel, String typeRoom, int finalPayment, int partySize, int hotelId) {
    	setResId(resId);
    	setUserId(userId);
    	setHotelName(hotelName);
    	setCheckInDate(checkInDate);
    	setCheckOutDate(checkOutDate);
    	setNumRoomSel(numRoomSel);
    	setTypeRoom(typeRoom);
    	setFinalPayment(finalPayment);
    	setPartySize(partySize);
    	setHotelId(hotelId);
    }
}
