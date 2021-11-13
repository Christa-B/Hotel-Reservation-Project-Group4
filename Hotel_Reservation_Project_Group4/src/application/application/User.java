package application.application;
import javafx.beans.property.StringProperty ;
import javafx.beans.property.SimpleStringProperty ;
import javafx.beans.property.IntegerProperty ;
import javafx.beans.property.SimpleIntegerProperty ;

// User details: userId, firstName, lastName, phoneNum, emailAd, passW, acctType
public class User {
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
    
    private final StringProperty firstName = new SimpleStringProperty(this, "firstName");
    public StringProperty firstNameProperty() {
        return firstName;
    }
    public final String getFirstName() {
        return firstNameProperty().get();
    }
    public final void setFirstName(String firstName) {
        firstNameProperty().set(firstName);
    }

    private final StringProperty lastName = new SimpleStringProperty(this, "lastName");
    public StringProperty lastNameProperty() {
        return lastName;
    }
    public final String getLastName() {
        return lastNameProperty().get();
    }
    public final void setLastName(String lastName) {
        lastNameProperty().set(lastName);
    }
    
    private final StringProperty phoneNum = new SimpleStringProperty(this, "phoneNum");
    public StringProperty phoneNumProperty() {
        return phoneNum;
    }
    public final String getPhoneNum() {
        return phoneNumProperty().get();
    }
    public final void setPhoneNum(String phoneNum) {
    	phoneNumProperty().set(phoneNum);
    }
    
    private final StringProperty emailAd = new SimpleStringProperty(this, "emailAd");
    public StringProperty emailAdProperty() {
        return emailAd;
    }
    public final String getEmailAd() {
        return emailAdProperty().get();
    }
    public final void setEmailAd(String emailAd) {
    	emailAdProperty().set(emailAd);
    }
    
    private final StringProperty passW = new SimpleStringProperty(this, "passW");
    public StringProperty passWProperty() {
        return passW;
    }
    public final String getPassW() {
        return passWProperty().get();
    }
    public final void setPassW(String passW) {
    	passWProperty().set(passW);
    }
    
    private final StringProperty acctType = new SimpleStringProperty(this, "acctType");
    public StringProperty acctTypeProperty() {
        return acctType;
    }
    public final String getAcctType() {
        return acctTypeProperty().get();
    }
    public final void setAcctType(String acctType) {
    	acctTypeProperty().set(acctType);
    }
    
    public User() {}

    public User(int userId, String firstName, String lastName, String phoneNum, String emailAd, String passW, String acctType) {
    	setUserId(userId);
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNum(phoneNum);
        setEmailAd(emailAd);
        setPassW(passW);
        setAcctType(acctType);
    }
}
