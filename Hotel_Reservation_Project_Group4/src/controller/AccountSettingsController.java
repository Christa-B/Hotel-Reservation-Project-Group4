/**
 * Hotel Reservation Project
 * UTSA CS 3773-002 - Fall 2021
 * Team 4: Andres De La Rosa, Jackson Raymond, Jalyn Merritt, Aden Rojas, Christa Baca 
 */

package controller;

import application.Main;
import application.application.User;
import application.application.UserDataAccessor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.util.Callback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.*;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DateCell;
import javafx.scene.input.*;

/**
 * AccountSettingsController is a class that handles events that occur when the user
 * interacts with account_settings.fxml
 * 
 * @author Christa Baca
 */
public class AccountSettingsController implements Initializable {
	// Buttons
	@FXML
	private Button button; // Submit changes button
	
	// Labels
	@FXML
	private Label label1; // Edit your account
	
	// HyperLinks
	@FXML
	private Hyperlink hyperlink1; // Nomad+, link to home page
	
	@FXML
	private Hyperlink hyperlink2; // manage hotels
	
	@FXML
	private Hyperlink hyperlink3; // manage reservations
	
	@FXML
	private Hyperlink hyperlink4; // my reservation
	
	@FXML
	private Hyperlink hyperlink5; // account settings
	
	@FXML
	private Hyperlink hyperlink6; // Logout 
	
	@FXML
	private Hyperlink hyperlink7; // Go back (to home page)
	
	//Textfields
	@FXML
	private TextField textfield1; // first name
	
	@FXML
	private TextField textfield2; // last name
	
	@FXML
	private TextField textfield3; // email
	
	@FXML
	private TextField textfield4; // phone number
	
	@FXML
	private Label overallNameErrorText; // If first & last both have errors
	
	@FXML
	private Label emailErrorText; // email errors

	@FXML
	private Label numberErrorText; // phone number errors
	
	@FXML
	private Label passwordErrorText; // password errors
	
	@FXML
	private Label accountTypeErrorText; // password errors
	
	@FXML
	private PasswordField passwordfield1; // old password
	
	@FXML
	private PasswordField passwordfield2; // new password
	
	@FXML
	private PasswordField passwordfield3; // confirm new password
	
	// Static variables to set style for button when mouse is away/hovering
	private static String normal_button_style = "-fx-background-color: white; -fx-background-radius: 20";
	private static String hovered_button_style = "-fx-background-color: #d3d3d3; -fx-background-radius: 20;";
	
	/**
	 * Initializes items for Number of Rooms and Price Range ComboBoxes
	 * 
	 * @param location  location properties for controller and FXMLLoader
	 * @param resources  resources for controller and FXMLLoader
	 */
	@Override
	public void initialize( URL location, ResourceBundle resources ) {
		// Normal button style set to white
	    button.setStyle(normal_button_style);
	    
	    // Changes to hovered button style, set to a light grey
	    button.setOnMouseEntered(e -> button.setStyle(hovered_button_style));
	    
	    // Changes back to normal button style when mouse stops hovering
	    button.setOnMouseExited(e -> button.setStyle(normal_button_style));
	    
	    if(LoginController.curUser.getAcctType().equals("Customer")) {
	    	hyperlink2.setVisible(false);
	    	hyperlink3.setVisible(false);
	    }
	}
	
	/**
	 * Changes view to the HOME PAGE after button is clicked and credentials are verified
	 * 
	 * @param event	 event in which user clicks on the LOGIN button
	 * @throws IOException	if a file is unable to be read
	 * @throws ClassNotFoundException	if a class cannot be found
	 * @throws SQLException	if SQL Database cannot be reached
	 */
	@FXML
	public void handleSubmitChanges( ActionEvent event ) throws IOException {
		try {
			// Initialize data accessor via link to DB
			UserDataAccessor userDataAccessor = new UserDataAccessor( 
					"jdbc:mysql://awsmysql-nomadplus.c8lezqhu83hc.us-east-2.rds.amazonaws.com:3306"
					+ "/userData?autoReconnect=true&useSSL=false", "admin", "adminthisisjustaproject92521");
		
			// Make regex for ensuring secure user input
			String nameRegexPattern = "(?i)(^[A-Za-zÀ-ÖØ-öø-ÿ])((?![ .,'-]$)[A-Za-zÀ-ÖØ-öø-ÿ .,'-]){0,254}[\\.]{0,1}$"; //Can take most special names, even accented ones
			String emailRegexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"; // Even reads cases like MyNavyFederal@email.nfcu.org
			String phoneNumRegexPattern = "^\\D?(\\d{3})\\D?\\D?(\\d{3})\\D?(\\d{4})$"; // takes the variations 9999999999, 999-999-9999, and (999) 999-9999
			String passwordRegexPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_=+])(?=\\S+$).{8,254}$"; //requirements listed in error message
			
			int thisUsrId = LoginController.curUser.getUserId();
			int nameFlag = 0;
			int ValidationFlag = 0;
			String fstNm = "";
			String lstNm = "";
			String email = "";
			String phnNum = "";
			String pWrd = "";
			String acctType = LoginController.curUser.getAcctType();
		
			//check for correct naming format
			if(textfield1.getText() == null || textfield1.getText().isEmpty()) {
				fstNm = LoginController.curUser.getFirstName();
				ValidationFlag++;
			}
			else {
				nameFlag++;
			}
			
			if(textfield2.getText() == null || textfield2.getText().isEmpty()) {
				lstNm = LoginController.curUser.getLastName();
				ValidationFlag++;
			}
			else {
				nameFlag++;
			}
			
			
			if((nameFlag >= 1) && (!patternMatches(textfield1.getText(), nameRegexPattern) || !patternMatches(textfield2.getText(), nameRegexPattern))){ 
				overallNameErrorText.setText("Please only enter letters and apostrophes. Titles like 'the third' should be entered as 'III'.");
				overallNameErrorText.setStyle("-fx-font-weight: bold");
				overallNameErrorText.setVisible(true);
			}
			else {
				overallNameErrorText.setVisible(false);
				
				if(textfield1.getText() == null || textfield1.getText().isEmpty()) {
					fstNm = LoginController.curUser.getFirstName();
					ValidationFlag++;
				}
				else {
					fstNm = textfield1.getText();
					ValidationFlag++;
				}
				
				if(textfield2.getText() == null || textfield2.getText().isEmpty()) {
					lstNm = LoginController.curUser.getLastName();
					ValidationFlag++;
				}
				else {
					lstNm = textfield2.getText();
					ValidationFlag++;
				}
			}
		
			//check for correct email format
			if(textfield3.getText() == null || textfield3.getText().isEmpty()) {
				email = LoginController.curUser.getEmailAd();
				ValidationFlag++;
			}
			else if(!patternMatches(textfield3.getText(), emailRegexPattern)){ 
				emailErrorText.setText("Please enter a valid email.");
				emailErrorText.setStyle("-fx-font-weight: bold");
				emailErrorText.setVisible(true);
			}
			else {
				emailErrorText.setVisible(false);
				
				if(textfield3.getText() == null || textfield3.getText().isEmpty()) {
					email = LoginController.curUser.getEmailAd();
				}
				else {
					email = textfield3.getText();
				}
				
				ValidationFlag++;
			}
		
			//check for correct phone number format
			if(textfield4.getText() == null || textfield4.getText().isEmpty()) {
				phnNum = LoginController.curUser.getPhoneNum();
				ValidationFlag++;
			}
			else if(!patternMatches(textfield4.getText(), phoneNumRegexPattern)){ 
				numberErrorText.setText("Please enter a valid phone number.");
				numberErrorText.setStyle("-fx-font-weight: bold");
				numberErrorText.setVisible(true);
			}
			else {
				numberErrorText.setVisible(false);
				
				if(textfield4.getText() == null || textfield4.getText().isEmpty()) {
					phnNum = LoginController.curUser.getPhoneNum();
				}
				else {
					phnNum = textfield4.getText();
				}
				
				ValidationFlag++;
			}
			
			if(passwordfield1.getText() == null || passwordfield1.getText().isEmpty()) {
				if(passwordfield2.getText() != null || !passwordfield2.getText().isEmpty()) {
					passwordErrorText.setText("Enter old password first.");
					passwordErrorText.setStyle("-fx-font-weight: bold");
					passwordErrorText.setVisible(true);
				}
				else {
					pWrd = LoginController.curUser.getPassW();
					ValidationFlag++;
				}
			}
			else if(!passwordfield1.getText().equals(LoginController.curUser.getPassW())){
				passwordErrorText.setText("Incorrect old password.");
				passwordErrorText.setStyle("-fx-font-weight: bold");
				passwordErrorText.setVisible(true);
			}
			else {
				//check for correct password format
				if(!patternMatches(passwordfield2.getText(), passwordRegexPattern)){ 
					passwordErrorText.setText("Passwords must be 8 characters or longer. Must have one digit, one lowercase letter, one uppercase letter, one special character"
							+ " and no whitespaces.");
					passwordErrorText.setStyle("-fx-font-weight: bold");
					passwordErrorText.setVisible(true);
				}
				else if(!passwordfield2.getText().equals(passwordfield3.getText())){
					passwordErrorText.setText("Please confirm your new password.");
					passwordErrorText.setStyle("-fx-font-weight: bold");
					passwordErrorText.setVisible(true);
				}
				else {
					passwordErrorText.setVisible(false);
					pWrd = passwordfield2.getText();
					ValidationFlag++;
				}
			}
			
			if(ValidationFlag == 5) {
				if(LoginController.curUser.getAcctType().equals("Admin")) { //admin case
					userDataAccessor.updateUser(thisUsrId, fstNm, lstNm, phnNum, email, pWrd, acctType);
					LoginController.curUser = userDataAccessor.getUser(textfield3.getText(), passwordfield2.getText());
					// Loads the FXML document for home_page and displays it
					Parent root = FXMLLoader.load(getClass().getResource("/application/home_page_admin_loggedin.fxml"));
					Stage window = (Stage)button.getScene().getWindow();
					window.setMaximized(true);
					window.setScene(new Scene (root, 1920, 1220));
				}
				else { //customer case
					userDataAccessor.updateUser(thisUsrId, fstNm, lstNm, phnNum, email, pWrd, acctType);
					LoginController.curUser = userDataAccessor.getUser(textfield3.getText(), passwordfield2.getText());
					Parent root = FXMLLoader.load(getClass().getResource("/application/home_page_customer_loggedin.fxml"));
					Stage window = (Stage)button.getScene().getWindow();
					window.setScene(new Scene (root, 1920, 1260));
					window.setMaximized(true);	
				}	
			}
			
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	
	/**
	 * Handles event in which user wants to manage hotel reservations
	 * 
	 * @param event  event in which user clicks Manage Reservations HyperLink
	 * @throws IOException  if a file is unable to be read
	 */
	@FXML
	public void handleManageReservations(ActionEvent event) throws IOException {
		// Loads the FXML document for manage_reservations and displays it
		Parent root = FXMLLoader.load(getClass().getResource("/application/manage_reservations.fxml"));
		Stage window = (Stage)hyperlink3.getScene().getWindow();
		window.setScene(new Scene (root));
		window.setMaximized(true);
	}
	
	
	/**
	 * Handles event in which user wants to manage hotel properties
	 * 
	 * @param event  event in which user clicks Manage Hotels HyperLink
	 * @throws IOException  if a file is unable to be read
	 */
	@FXML
	public void handleManageHotels(ActionEvent event) throws IOException {
		// Loads the FXML document for manage_hotels and displays it
		Parent root = FXMLLoader.load(getClass().getResource("/application/manage_hotels.fxml"));
		Stage window = (Stage)hyperlink2.getScene().getWindow();
		window.setScene(new Scene (root));
		window.setMaximized(true);
	}
	
	
	/**
	 * Handles event in which user wants to check their reservation
	 * 
	 * @param event  event in which user clicks My Reservation HyperLink
	 * @throws IOException  if a file is unable to be read
	 */
	@FXML
	public void handleMyReservation(ActionEvent event) throws IOException {
		// Loads the FXML document for my_reservation and displays it
		Parent root = FXMLLoader.load(getClass().getResource("/application/my_reservation.fxml"));
		Stage window = (Stage)hyperlink4.getScene().getWindow();
		window.setScene(new Scene (root));
		window.setMaximized(true);
	}
	
	
	/**
	 * Handles event in which user wants to change account settings
	 * 
	 * @param event  event in which user clicks Account Settings HyperLink
	 * @throws IOException  if a file is unable to be read
	 */
	@FXML
	public void handleAccountSettings(ActionEvent event) throws IOException {
		// Loads the FXML document for account_settings and displays it
		Parent root = FXMLLoader.load(getClass().getResource("/application/account_settings.fxml"));
		Stage window = (Stage)hyperlink5.getScene().getWindow();
		window.setScene(new Scene (root));
		window.setMaximized(true);
	}

	
	/**
	 * Changes view to the LOGGED IN (admin) HOME PAGE after link is clicked
	 * 
	 * @param event	 event in which user clicks on the Nomad+ hyperlink
	 * @throws IOException	if a file is unable to be read
	 */
	@FXML
	public void handleBackToHomePage( ActionEvent event ) throws IOException {
		if(LoginController.curUser.getAcctType().equals("Customer")) {
			Parent root = FXMLLoader.load(getClass().getResource("/application/home_page_customer_loggedin.fxml"));
			Stage window = (Stage)hyperlink1.getScene().getWindow();
			window.setMaximized(true);
			window.setScene(new Scene (root, 1920, 1260));
		}
		
		if(LoginController.curUser.getAcctType().equals("Admin")) {
			Parent root = FXMLLoader.load(getClass().getResource("/application/home_page_admin_loggedin.fxml"));
			Stage window = (Stage)hyperlink1.getScene().getWindow();
			window.setMaximized(true);
			window.setScene(new Scene (root, 1920, 1220));
		}
	}
	
	
	/**
	 * Changes view to the first HOME PAGE after link is clicked
	 * 
	 * @param event	 event in which user clicks on the Logout hyperlink
	 * @throws IOException	if a file is unable to be read
	 */
	@FXML
	public void handleLogout( ActionEvent event ) throws IOException {
		LoginController.curUser = null; //Cancels out the user session
		// Loads the FXML document for home_page and displays it
		Parent root = FXMLLoader.load(getClass().getResource("/application/home_page.fxml"));
		Stage window = (Stage)hyperlink6.getScene().getWindow();
		window.setMaximized(true);
		window.setScene(new Scene (root, 1920, 1050));
	}
	
	public static boolean patternMatches(String emailAddress, String regexPattern) {
	    return Pattern.compile(regexPattern)
	      .matcher(emailAddress)
	      .matches();
	}
	
}
