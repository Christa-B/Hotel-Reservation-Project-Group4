/***
 * Hotel Reservation Project
 * UTSA CS 3773-002 - Fall 2021
 * Team 4: Andres De La Rosa, Jackson Raymond, Jalyn Merritt, Aden Rojas, Christa Baca 
 */

package controller;

import application.Main;
import application.application.User;
import application.application.UserDataAccessor;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;

/**
 * SignUpController is a class that handles events that occur when the user
 * interacts with signup_screen.fxml
 * 
 * @author Christa
 */
public class SignUpController implements Initializable {
	// Buttons
	@FXML
	private Button button; // JOIN button
	
	// Labels
	@FXML
	private Label label1; // Project Name
	
	@FXML
	private Label label2; // Create Account
	
	@FXML
	private Label label3; // first name
	
	@FXML
	private Label label4; // last name
	
	@FXML
	private Label label5; // email
	
	@FXML
	private Label label6; // phone number
	
	@FXML
	private Label label7; // password
	
	@FXML
	private Label label8; // account type
	
	@FXML
	private Label label9; // Already have an account?
	
	@FXML
	private Label overallErrorText; // No info entered and sign in button clicked
	
	@FXML
	private Label overallNameErrorText; // If first & last both have errors
	
	@FXML
	private Label firstNameErrorText; // first name error only
	
	@FXML
	private Label lastNameErrorText; // last name error only
	
	@FXML
	private Label emailErrorText; // email errors

	@FXML
	private Label numberErrorText; // phone number errors
	
	@FXML
	private Label passwordErrorText; // password errors
	
	@FXML
	private Label accountTypeErrorText; // password errors
	
	@FXML
	private TextField textFieldFirstName; // first name
	
	@FXML
	private TextField textFieldLastName; // last name
	
	@FXML
	private TextField textFieldEmail; // email
	
	@FXML
	private TextField textFieldNumber; // phone number
	
	@FXML
	private PasswordField passwordFieldOne; // User password
	
	@FXML
	private PasswordField passwordFieldTwo; // To confirm password
	
	@FXML
	private PasswordField adminpasswordField; // To confirm admin passcode
	
	// HyperLinks
	@FXML
	private Hyperlink hyperlink; // Login here.
	
	@FXML
	private Hyperlink hyperlink2; // Go back
	
	@FXML
	private Hyperlink homehandler;
	
	// ChoiceBoxes
	@FXML
	private ComboBox<String> comboBoxAccount; // Option for customer/administrator account type
	
	// Static variables to set style for button/hyperlinks when mouse is away/hovering
	private static String normal_button_style = "-fx-background-color: white; -fx-background-radius: 20;";
	private static String hovered_button_style = "-fx-background-color: #d3d3d3; -fx-background-radius: 20;";
	private static String normal_login_button_style = "-fx-text-fill: #91cd75;";
	private static String hovered_login_button_style = "-fx-text-fill: white; -fx-font-weight: bold;";
	private static String normal_goback_button_style = "-fx-text-fill: #91cd75;";
	private static String hovered_goback_button_style = "-fx-text-fill: white; -fx-font-weight: bold;";
	private static String normal_homehandler_button_style = "-fx-text-fill: white;";
	private static String hovered_homehandler_button_style = "-fx-text-fill: deepskyblue; -fx-font-weight: bold;";
	
	// List of items for ComboBoxes (prices and rooms)
	ObservableList<String> list1 = FXCollections.observableArrayList("Customer", "Admin");

	/**
	 * This method will set a different style for button depending on whether or not mouse hovers it
	 * 
	 * @param location  location properties for controller and FXMLLoader
	 * @param resources  resources for controller and FXMLLoader
	 */
	@Override
	public void initialize( URL location, ResourceBundle resources ) {
		// Sets choicebox items
		comboBoxAccount.setItems(list1);
		
		// Set button/hyperlink styles to normal
	    button.setStyle(normal_button_style);
	    hyperlink.setStyle(normal_login_button_style);
	    hyperlink2.setStyle(normal_goback_button_style);
	    homehandler.setStyle(normal_homehandler_button_style);
	    
	    // Changes to hovered button style, set to a light grey
	    button.setOnMouseEntered(e -> button.setStyle(hovered_button_style));
	    hyperlink.setOnMouseEntered(e -> hyperlink.setStyle(hovered_login_button_style));
	    hyperlink2.setOnMouseEntered(e -> hyperlink2.setStyle(hovered_goback_button_style));
	    homehandler.setOnMouseEntered(e -> homehandler.setStyle(hovered_homehandler_button_style));
	    
	    // Changes back to normal button style when mouse stops hovering
	    button.setOnMouseExited(e -> button.setStyle(normal_button_style));
	    hyperlink.setOnMouseExited(e -> hyperlink.setStyle(normal_login_button_style));
	    hyperlink2.setOnMouseExited(e -> hyperlink2.setStyle(normal_goback_button_style));
	    homehandler.setOnMouseExited(e -> homehandler.setStyle(normal_homehandler_button_style));
	    
	    // Sets style to normal after clicked, pressed or released to fix resizing
	    hyperlink.setOnMouseClicked(e -> hyperlink.setStyle(normal_login_button_style));
	    hyperlink2.setOnMouseClicked(e -> hyperlink2.setStyle(normal_goback_button_style));
	    homehandler.setOnMouseClicked(e -> homehandler.setStyle(normal_homehandler_button_style));
	    
	    hyperlink.setOnMousePressed(e -> hyperlink.setStyle(normal_login_button_style));
	    hyperlink2.setOnMousePressed(e -> hyperlink2.setStyle(normal_goback_button_style));
	    homehandler.setOnMousePressed(e -> homehandler.setStyle(normal_homehandler_button_style));
	    
	    hyperlink.setOnMouseReleased(e -> hyperlink.setStyle(normal_login_button_style));
	    hyperlink2.setOnMouseReleased(e -> hyperlink2.setStyle(normal_goback_button_style));
	    homehandler.setOnMouseReleased(e -> homehandler.setStyle(normal_homehandler_button_style));
	}
	
	
	/**
	 * Changes view to the first HOME PAGE after button is clicked
	 * 
	 * @param event	 event in which user clicks on the Project Name button
	 * @throws IOException	if a file is unable to be read
	 */
	@FXML
	public void handleBackToHomePage( ActionEvent event ) throws IOException {
		// Loads the FXML document for home_page and displays it
		Parent root = FXMLLoader.load(Main.class.getResource("/application/home_page.fxml"));
		Stage window = (Stage)homehandler.getScene().getWindow();
		window.setMaximized(true);
		window.setScene(new Scene (root, 1920, 1050));	//Weird solution to fix scrollpane issue.
	}
	
	/**
	 * Changes view to the HOME PAGE after button is clicked
	 * 
	 * @param event	 event in which user clicks on the LOGIN button
	 * @throws IOException	if a file is unable to be read
	 */
	@FXML
	public void handleSignIn( ActionEvent event ) throws IOException {
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
		
		
		//setting up variables for addUser function
		int thisUsrId = userDataAccessor.makeNewId();
		int nameFlag = 0;
		int ValidationFlag = 0;
		String fstNm = null;
		String lstNm = null;
		String email = null;
		String phnNum = null;
		String pWrd = null;
		String buttonRes = null;
		
			//check for correct first name format
			if(textFieldFirstName.getText() == null || textFieldFirstName.getText().isEmpty()) {
				firstNameErrorText.setText("Please enter your first name.");
				firstNameErrorText.setStyle("-fx-font-weight: bold");
				firstNameErrorText.setVisible(true);
			}
			else {
				firstNameErrorText.setVisible(false);
				nameFlag++;
			}
			
			//check for correct last name format
			if(textFieldLastName.getText() == null || textFieldLastName.getText().isEmpty()) {
				lastNameErrorText.setText("Please enter your last name.");
				lastNameErrorText.setStyle("-fx-font-weight: bold");
				lastNameErrorText.setVisible(true);
			}
			else {
				lastNameErrorText.setVisible(false);
				nameFlag++;
			}
		
			//check for correct overall format
			if(textFieldFirstName.getText().length() > 255 || textFieldLastName.getText().length() > 255) {
				overallNameErrorText.setText("Invalid name length(s).");
				overallNameErrorText.setStyle("-fx-font-weight: bold");
				overallNameErrorText.setVisible(true);
			}
			else {
				if ((nameFlag == 2) && (!patternMatches(textFieldFirstName.getText(), nameRegexPattern) || !patternMatches(textFieldLastName.getText(), nameRegexPattern))){ 
					overallNameErrorText.setText("Please only enter letters and apostrophes. Titles like 'the third' should be entered as 'III'.");
					overallNameErrorText.setStyle("-fx-font-weight: bold");
					overallNameErrorText.setVisible(true);
				}
				else {
					overallNameErrorText.setVisible(false);
					fstNm = textFieldFirstName.getText();
					lstNm = textFieldLastName.getText();
					ValidationFlag += 2;
				}
			}
			
			//check for correct email format
			if(textFieldEmail.getText() == null || textFieldEmail.getText().isEmpty()) {
				emailErrorText.setText("Please enter your email.");
				emailErrorText.setStyle("-fx-font-weight: bold");
				emailErrorText.setVisible(true);
			}
			else if(!patternMatches(textFieldEmail.getText(), emailRegexPattern)){ 
				emailErrorText.setText("Please enter a valid email.");
				emailErrorText.setStyle("-fx-font-weight: bold");
				emailErrorText.setVisible(true);
			}
			else {
				if(textFieldEmail.getText().length() > 255) {
					emailErrorText.setText("Invalid email length.");
					emailErrorText.setStyle("-fx-font-weight: bold");
					emailErrorText.setVisible(true);
				}
				else {
					emailErrorText.setVisible(false);
					email = textFieldEmail.getText();
					ValidationFlag++;
				}
			}
			
			//check for correct phone number format
			if(textFieldNumber.getText() == null || textFieldNumber.getText().isEmpty()) {
				numberErrorText.setText("Please enter your phone number.");
				numberErrorText.setStyle("-fx-font-weight: bold");
				numberErrorText.setVisible(true);
			}
			else if(!patternMatches(textFieldNumber.getText(), phoneNumRegexPattern)){ 
				numberErrorText.setText("Valid format is 999-999-9999.");
				numberErrorText.setStyle("-fx-font-weight: bold");
				numberErrorText.setVisible(true);
			}
			else {
				numberErrorText.setVisible(false);
				phnNum = textFieldNumber.getText();
				ValidationFlag++;
			}
			
			//check for correct password format & confirming password
			if(passwordFieldOne.getText() == null || passwordFieldOne.getText().isEmpty()) {
				passwordErrorText.setText("Please enter a password.");
				passwordErrorText.setStyle("-fx-font-weight: bold");
				passwordErrorText.setVisible(true);
			}
			else if(!patternMatches(passwordFieldOne.getText(), passwordRegexPattern)){ 
				passwordErrorText.setText("Passwords must be 8 characters or longer. Must have one digit, one lowercase letter, one uppercase letter, one special character"
						+ " and no whitespaces.");
				passwordErrorText.setStyle("-fx-font-weight: bold");
				passwordErrorText.setVisible(true);
			}
			else if(!passwordFieldOne.getText().equals(passwordFieldTwo.getText())){
				passwordErrorText.setText("Please confirm your password.");
				passwordErrorText.setStyle("-fx-font-weight: bold");
				passwordErrorText.setVisible(true);
			}
			else {
				if(passwordFieldOne.getText().length() > 255) {
					passwordErrorText.setText("Invalid password length.");
					passwordErrorText.setStyle("-fx-font-weight: bold");
					passwordErrorText.setVisible(true);
				}
				else {
					passwordErrorText.setVisible(false);
					pWrd = passwordFieldOne.getText();
					ValidationFlag++;
				}
			}
			
			//check for account selection
			if(comboBoxAccount.getValue() == null || comboBoxAccount.getValue().isEmpty()) {
				accountTypeErrorText.setText("Please choose an account type.");
				accountTypeErrorText.setStyle("-fx-font-weight: bold");
				accountTypeErrorText.setVisible(true);
			}
			else {
				accountTypeErrorText.setVisible(false);
				buttonRes = comboBoxAccount.getValue();
				ValidationFlag++;
			}
		
		if(ValidationFlag == 6 && buttonRes.equals("Admin")) {
			adminpasswordField.setVisible(true);
			
			//We can set this to a different passcode later
			if(adminpasswordField.getText().equals("Arbitrary")) {
				userDataAccessor.addUser(thisUsrId, fstNm, lstNm, phnNum, email, pWrd, buttonRes);
				LoginController.curUser = new User();
				LoginController.curUser = userDataAccessor.getUser(textFieldEmail.getText(), passwordFieldOne.getText());
				// Loads the FXML document for home_page and displays it
				Parent root = FXMLLoader.load(Main.class.getResource("/application/home_page_admin_loggedin.fxml"));
				Stage window = (Stage)button.getScene().getWindow();
				window.setMaximized(true);
				window.setScene(new Scene (root, 1920, 1220));
			}
		}
		else if(ValidationFlag == 6) { //Regular customer case
			userDataAccessor.addUser(thisUsrId, fstNm, lstNm, phnNum, email, pWrd, buttonRes);
			LoginController.curUser = new User();
			LoginController.curUser = userDataAccessor.getUser(textFieldEmail.getText(), passwordFieldOne.getText());
			// Loads the FXML document for home_page and displays it
			Parent root = FXMLLoader.load(Main.class.getResource("/application/home_page_customer_loggedin.fxml"));
			Stage window = (Stage)button.getScene().getWindow();
			window.setMaximized(true);
			window.setScene(new Scene (root, 1920, 1220));
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
	 * Changes view to the LOGIN PAGE after HyperLink is clicked
	 * 
	 * @param event	 event in which user clicks on the Login here HyperLink
	 * @throws IOException	if a file is unable to be read
	 */
	@FXML
	public void handleLoginPage( ActionEvent event ) throws IOException {
		// Loads the FXML document for login_screen and displays it
		Parent root = FXMLLoader.load(Main.class.getResource("/application/login_screen.fxml"));
		Stage window = (Stage)hyperlink.getScene().getWindow();
		window.setScene(new Scene (root));
	}
	
	public static boolean patternMatches(String emailAddress, String regexPattern) {
	    return Pattern.compile(regexPattern)
	      .matcher(emailAddress)
	      .matches();
	}
}
