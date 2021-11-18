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
	private Button submit_button; // Submit changes submit_button
	
	@FXML
	private Button delete_account_button; // Delete account delete_account_button
	
	@FXML
	private Button confirm_button; // confirmation of deletion confirm_button
	
	// Labels
	@FXML
	private Label user_details_label; // User details
	
	// HyperLinks
	@FXML
	private Hyperlink nomadplus_link; // Nomad+, link to home page
	
	@FXML
	private Hyperlink manage_hotels_link; // manage hotels
	
	@FXML
	private Hyperlink manage_reservations_link; // manage reservations
	
	@FXML
	private Hyperlink my_reservation_link; // my reservation
	
	@FXML
	private Hyperlink account_settings_link; // account settings
	
	@FXML
	private Hyperlink logout_link; // Logout 
	
	@FXML
	private Hyperlink go_back_link; // Go back (to home page)
	
	//Textfields
	@FXML
	private TextField first_name_textfield; // first name
	
	@FXML
	private TextField last_name_textfield; // last name
	
	@FXML
	private TextField email_textfield; // email
	
	@FXML
	private TextField phone_num_textfield; // phone number
	
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
	private PasswordField old_passwordfield; // old password
	
	@FXML
	private PasswordField new_passwordfield; // new password
	
	@FXML
	private PasswordField confirm_passwordfield; // confirm new password
	
	// Static variables to set style for submit_button when mouse is away/hovering
	private static String normal_submit_button_style = "-fx-background-color: white; -fx-background-radius: 20";
	private static String hovered_submit_button_style = "-fx-background-color: #d3d3d3; -fx-background-radius: 20;";
	
	private static String normal_delete_account_button_style = "-fx-background-color: #dc143c; -fx-background-radius:20;";
	private static String hovered_delete_account_button_style = "-fx-background-color: #ad102f; -fx-background-radius:20;";
	
	private static String normal_nomadplus_link_style = "-fx-text-fill: white; -fx-font-size: 48; -fx-underline: false;";
	private static String hovered_nomadplus_link_style = "-fx-text-fill: deepskyblue; -fx-font-weight: bold; -fx-underline: false;";
	
	private static String normal_manage_hotels_link_style = "-fx-text-fill: white; -fx-font-size: 20; -fx-underline: false;";
	private static String hovered_manage_hotels_link_style = "-fx-text-fill: deepskyblue; -fx-font-weight: bold; -fx-underline: false;";
	
	private static String normal_manage_reservations_link_style = "-fx-text-fill: white; -fx-font-size: 20; -fx-underline: false;";
	private static String hovered_manage_reservations_link_style = "-fx-text-fill: deepskyblue; -fx-font-weight: bold; -fx-underline: false;";
	
	private static String normal_my_reservation_link_style = "-fx-text-fill: white; -fx-font-size: 20; -fx-underline: false;";
	private static String hovered_my_reservation_link_style = "-fx-text-fill: deepskyblue; -fx-font-weight: bold; -fx-underline: false;";
	
	private static String normal_account_settings_link_style = "-fx-text-fill: white; -fx-font-size: 20; -fx-underline: false;";
	private static String hovered_account_settings_link_style = "-fx-text-fill: deepskyblue; -fx-font-weight: bold; -fx-underline: false;";
	
	private static String normal_logout_link_style = "-fx-text-fill: white; -fx-font-size: 20; -fx-underline: false;";
	private static String hovered_logout_link_style = "-fx-text-fill: deepskyblue; -fx-font-weight: bold; -fx-underline: false;";
	
	private static String normal_go_back_link_style = "-fx-text-fill: #91cd75; -fx-font-size: 20; -fx-underline: false;";
	private static String hovered_go_back_link_style = "-fx-text-fill: white; -fx-font-size: 20; -fx-font-weight: bold; -fx-underline: false;";
	
	/**
	 * Initializes items for Number of Rooms and Price Range ComboBoxes
	 * 
	 * @param location  location properties for controller and FXMLLoader
	 * @param resources  resources for controller and FXMLLoader
	 */
	@Override
	public void initialize( URL location, ResourceBundle resources ) {
		// Normal submit_button style set to white
	    submit_button.setStyle(normal_submit_button_style);
	    delete_account_button.setStyle(normal_delete_account_button_style);
	    nomadplus_link.setStyle(normal_nomadplus_link_style);
	    manage_hotels_link.setStyle(normal_manage_hotels_link_style);
	    manage_reservations_link.setStyle(normal_manage_reservations_link_style);
	    my_reservation_link.setStyle(normal_my_reservation_link_style);
	    account_settings_link.setStyle(normal_account_settings_link_style);
	    logout_link.setStyle(normal_logout_link_style);
	    go_back_link.setStyle(normal_go_back_link_style);
	    
	    // Changes to hovered submit_button style, set to a light grey
	    submit_button.setOnMouseEntered(e -> submit_button.setStyle(hovered_submit_button_style));
	    delete_account_button.setOnMouseEntered(e -> delete_account_button.setStyle(hovered_delete_account_button_style));
	    nomadplus_link.setOnMouseEntered(e -> nomadplus_link.setStyle(hovered_nomadplus_link_style));
	    manage_hotels_link.setOnMouseEntered(e -> manage_hotels_link.setStyle(hovered_manage_hotels_link_style));
	    manage_reservations_link.setOnMouseEntered(e -> manage_reservations_link.setStyle(hovered_manage_reservations_link_style));
	    my_reservation_link.setOnMouseEntered(e -> my_reservation_link.setStyle(hovered_my_reservation_link_style));
	    account_settings_link.setOnMouseEntered(e -> account_settings_link.setStyle(hovered_account_settings_link_style));
	    logout_link.setOnMouseEntered(e -> logout_link.setStyle(hovered_logout_link_style));
	    go_back_link.setOnMouseEntered(e -> go_back_link.setStyle(hovered_go_back_link_style));
	    
	    // Changes back to normal submit_button style when mouse stops hovering
	    submit_button.setOnMouseExited(e -> submit_button.setStyle(normal_submit_button_style));
	    delete_account_button.setOnMouseExited(e -> delete_account_button.setStyle(normal_delete_account_button_style));
	    nomadplus_link.setOnMouseExited(e -> nomadplus_link.setStyle(normal_nomadplus_link_style));
	    manage_hotels_link.setOnMouseExited(e -> manage_hotels_link.setStyle(normal_manage_hotels_link_style));
	    manage_reservations_link.setOnMouseExited(e -> manage_reservations_link.setStyle(normal_manage_reservations_link_style));
	    my_reservation_link.setOnMouseExited(e -> my_reservation_link.setStyle(normal_my_reservation_link_style));
	    account_settings_link.setOnMouseExited(e -> account_settings_link.setStyle(normal_account_settings_link_style));
	    logout_link.setOnMouseExited(e -> logout_link.setStyle(normal_logout_link_style));
	    go_back_link.setOnMouseExited(e -> go_back_link.setStyle(normal_go_back_link_style));
	    
	    // Sets to normal style to fix resizing when clicking on a link
	    nomadplus_link.setOnMouseClicked(e -> nomadplus_link.setStyle(normal_nomadplus_link_style));
	    manage_hotels_link.setOnMouseClicked(e -> manage_hotels_link.setStyle(normal_manage_hotels_link_style));
	    manage_reservations_link.setOnMouseClicked(e -> manage_reservations_link.setStyle(normal_manage_reservations_link_style));
	    my_reservation_link.setOnMouseClicked(e -> my_reservation_link.setStyle(normal_my_reservation_link_style));
	    account_settings_link.setOnMouseClicked(e -> account_settings_link.setStyle(normal_account_settings_link_style));
	    logout_link.setOnMouseClicked(e -> logout_link.setStyle(normal_logout_link_style));
	    go_back_link.setOnMouseClicked(e -> go_back_link.setStyle(normal_go_back_link_style));
	    
	    nomadplus_link.setOnMousePressed(e -> nomadplus_link.setStyle(normal_nomadplus_link_style));
	    manage_hotels_link.setOnMousePressed(e -> manage_hotels_link.setStyle(normal_manage_hotels_link_style));
	    manage_reservations_link.setOnMousePressed(e -> manage_reservations_link.setStyle(normal_manage_reservations_link_style));
	    my_reservation_link.setOnMousePressed(e -> my_reservation_link.setStyle(normal_my_reservation_link_style));
	    account_settings_link.setOnMousePressed(e -> account_settings_link.setStyle(normal_account_settings_link_style));
	    logout_link.setOnMousePressed(e -> logout_link.setStyle(normal_logout_link_style));
	    go_back_link.setOnMousePressed(e -> go_back_link.setStyle(normal_go_back_link_style));
	    
	    nomadplus_link.setOnMouseReleased(e -> nomadplus_link.setStyle(normal_nomadplus_link_style));
	    manage_hotels_link.setOnMouseReleased(e -> manage_hotels_link.setStyle(normal_manage_hotels_link_style));
	    manage_reservations_link.setOnMouseReleased(e -> manage_reservations_link.setStyle(normal_manage_reservations_link_style));
	    my_reservation_link.setOnMouseReleased(e -> my_reservation_link.setStyle(normal_my_reservation_link_style));
	    account_settings_link.setOnMouseReleased(e -> account_settings_link.setStyle(normal_account_settings_link_style));
	    logout_link.setOnMouseReleased(e -> logout_link.setStyle(normal_logout_link_style));
	    go_back_link.setOnMouseReleased(e -> go_back_link.setStyle(normal_go_back_link_style));
	    
	    
	    if(LoginController.curUser.getAcctType().equals("Customer")) {
	    	manage_hotels_link.setVisible(false);
	    	manage_reservations_link.setVisible(false);
	    }
	 
	    
	    /*** POPULATE FIELDS WITH LOGGED IN USER INFO ***/
	    String firstname = LoginController.curUser.getFirstName();
	    first_name_textfield.setText(firstname);
	   
	    String lastname = LoginController.curUser.getLastName();
	    last_name_textfield.setText(lastname);
	    
	    String email = LoginController.curUser.getEmailAd();
	    email_textfield.setText(email);
	    
	    String phone_num = LoginController.curUser.getPhoneNum();
	    phone_num_textfield.setText(phone_num);
	    
	}
	
	/**
	 * Changes view to the HOME PAGE after submit_button is clicked and credentials are verified
	 * 
	 * @param event	 event in which user clicks on the LOGIN submit_button
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
			String phoneNumRegexPattern = "^(1-)?\\d{3}-\\d{3}-\\d{4}$"; // takes only 999-999-9999 format
			//^\\D?(\\d{3})\\D?\\D?(\\d{3})\\D?(\\d{4})$ Old phone number regex
			String passwordRegexPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_=+])(?=\\S+$).{8,254}$"; //requirements listed in error message
			
			int thisUsrId = LoginController.curUser.getUserId();
			int nameFlag = 0;
			int errorFlag = 0;
			String fstNm = "";
			String lstNm = "";
			String email = "";
			String phnNum = "";
			String pWrd = "";
			String acctType = LoginController.curUser.getAcctType();
		
			//check for correct naming format
			if(first_name_textfield.getText() == null || first_name_textfield.getText().isEmpty()) {
				fstNm = LoginController.curUser.getFirstName();
			}
			else {
				nameFlag++;
			}
			
			if(last_name_textfield.getText() == null || last_name_textfield.getText().isEmpty()) {
				lstNm = LoginController.curUser.getLastName();
			}
			else {
				nameFlag++;
			}
			
			
			if((nameFlag >= 1) && (!patternMatches(first_name_textfield.getText(), nameRegexPattern) || !patternMatches(last_name_textfield.getText(), nameRegexPattern))){ 
				overallNameErrorText.setText("Please only enter letters, apostrophes, dashes, or periods for special cases/suffixes.");
				overallNameErrorText.setStyle("-fx-font-weight: bold");
				overallNameErrorText.setVisible(true);
				errorFlag++;
			}
			else {
				overallNameErrorText.setVisible(false);
				
				if(first_name_textfield.getText() == null || first_name_textfield.getText().isEmpty()) {
					fstNm = LoginController.curUser.getFirstName();
				}
				else {
					fstNm = first_name_textfield.getText();
				}
				
				if(last_name_textfield.getText() == null || last_name_textfield.getText().isEmpty()) {
					lstNm = LoginController.curUser.getLastName();
				}
				else {
					lstNm = last_name_textfield.getText();
				}
			}
		
			//check for correct email format
			if(email_textfield.getText() == null || email_textfield.getText().isEmpty()) {
				email = LoginController.curUser.getEmailAd();
			}
			else if(!patternMatches(email_textfield.getText(), emailRegexPattern)){ 
				emailErrorText.setText("Please enter a valid email.");
				emailErrorText.setStyle("-fx-font-weight: bold");
				emailErrorText.setVisible(true);
				errorFlag++;
			}
			else {
				emailErrorText.setVisible(false);
				
				if(email_textfield.getText() == null || email_textfield.getText().isEmpty()) {
					email = LoginController.curUser.getEmailAd();
				}
				else {
					email = email_textfield.getText();
				}
			}
		
			//check for correct phone number format
			if(phone_num_textfield.getText() == null || phone_num_textfield.getText().isEmpty()) {
				phnNum = LoginController.curUser.getPhoneNum();
			}
			else if(!patternMatches(phone_num_textfield.getText(), phoneNumRegexPattern)){ 
				numberErrorText.setText("Valid format is 999-999-9999.");
				numberErrorText.setStyle("-fx-font-weight: bold");
				numberErrorText.setVisible(true);
				errorFlag++;
			}
			else {
				numberErrorText.setVisible(false);
				
				if(phone_num_textfield.getText() == null || phone_num_textfield.getText().isEmpty()) {
					phnNum = LoginController.curUser.getPhoneNum();
				}
				else {
					phnNum = phone_num_textfield.getText();
				}
			}
			
			//check for correct password format
			if(old_passwordfield.getText() == null || old_passwordfield.getText().isEmpty()) {
					pWrd = LoginController.curUser.getPassW();
			}
			else if(!old_passwordfield.getText().equals(LoginController.curUser.getPassW())){
				passwordErrorText.setText("Incorrect old password.");
				passwordErrorText.setStyle("-fx-font-weight: bold");
				passwordErrorText.setVisible(true);
				errorFlag++;
			}
			else {
				if(!patternMatches(new_passwordfield.getText(), passwordRegexPattern)){
					passwordErrorText.setText("Passwords must be 8 characters or longer. Must have one digit, one lowercase letter, one uppercase letter, one special character"
							+ " and no whitespaces.");
					passwordErrorText.setStyle("-fx-font-weight: bold");
					passwordErrorText.setVisible(true);
					errorFlag++;
				}
				else if(!new_passwordfield.getText().equals(confirm_passwordfield.getText())){
					passwordErrorText.setText("Please confirm your new password.");
					passwordErrorText.setStyle("-fx-font-weight: bold");
					passwordErrorText.setVisible(true);
					errorFlag++;
				}
				else {
					passwordErrorText.setVisible(false);
					pWrd = new_passwordfield.getText();
				}
			}
			
			if(errorFlag == 0) {
				userDataAccessor.updateUser(thisUsrId, fstNm, lstNm, phnNum, email, pWrd, acctType);
				LoginController.curUser = userDataAccessor.getUser(email, pWrd);
				
				if(LoginController.curUser.getAcctType().equals("Admin")) {
					// Loads the FXML document for home_page and displays it
					Parent root = FXMLLoader.load(getClass().getResource("/application/home_page_admin_loggedin.fxml"));
					Stage window = (Stage)submit_button.getScene().getWindow();
					window.setMaximized(true);
					window.setScene(new Scene (root, 1920, 1220));
				}
				else { //customer case
					Parent root = FXMLLoader.load(getClass().getResource("/application/home_page_customer_loggedin.fxml"));
					Stage window = (Stage)submit_button.getScene().getWindow();
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
	 * Changes view to the HOME PAGE after submit_button is clicked and credentials are verified
	 * 
	 * @param event	 event in which user clicks on the LOGIN submit_button
	 * @throws IOException	if a file is unable to be read
	 * @throws ClassNotFoundException	if a class cannot be found
	 * @throws SQLException	if SQL Database cannot be reached
	 */
	@FXML
	public void handleDeleteAccount( ActionEvent event ) throws IOException {
		confirm_button.setVisible(true);
	}
	
	/**
	 * Changes view to the HOME PAGE after submit_button is clicked and credentials are verified
	 * 
	 * @param event	 event in which user clicks on the LOGIN submit_button
	 * @throws IOException	if a file is unable to be read
	 * @throws ClassNotFoundException	if a class cannot be found
	 * @throws SQLException	if SQL Database cannot be reached
	 */
	@FXML
	public void handleDeleteConfirmation( ActionEvent event ) throws IOException {
		try {
			// Initialize data accessor via link to DB
			UserDataAccessor userDataAccessor = new UserDataAccessor( 
					"jdbc:mysql://awsmysql-nomadplus.c8lezqhu83hc.us-east-2.rds.amazonaws.com:3306"
					+ "/userData?autoReconnect=true&useSSL=false", "admin", "adminthisisjustaproject92521");
			
			int thisUsrId = LoginController.curUser.getUserId();
			String email = LoginController.curUser.getEmailAd();
			String pWrd = LoginController.curUser.getPassW();
			
			userDataAccessor.deleteUser(thisUsrId, email, pWrd);
			
			// Loads the FXML document for home_page and displays it
			Parent root = FXMLLoader.load(getClass().getResource("/application/home_page.fxml"));
			Stage window = (Stage)submit_button.getScene().getWindow();
			window.setMaximized(true);
			window.setScene(new Scene (root, 1920, 1220));
			
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
		Stage window = (Stage)manage_reservations_link.getScene().getWindow();
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
		Stage window = (Stage)manage_hotels_link.getScene().getWindow();
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
		Stage window = (Stage)my_reservation_link.getScene().getWindow();
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
		Stage window = (Stage)account_settings_link.getScene().getWindow();
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
			Stage window = (Stage)nomadplus_link.getScene().getWindow();
			window.setMaximized(true);
			window.setScene(new Scene (root, 1920, 1260));
		}
		
		if(LoginController.curUser.getAcctType().equals("Admin")) {
			Parent root = FXMLLoader.load(getClass().getResource("/application/home_page_admin_loggedin.fxml"));
			Stage window = (Stage)nomadplus_link.getScene().getWindow();
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
		Stage window = (Stage)logout_link.getScene().getWindow();
		window.setMaximized(true);
		window.setScene(new Scene (root, 1920, 1050));
	}
	
	public static boolean patternMatches(String emailAddress, String regexPattern) {
	    return Pattern.compile(regexPattern)
	      .matcher(emailAddress)
	      .matches();
	}
	
}
