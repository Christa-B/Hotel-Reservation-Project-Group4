/**
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
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;

/**
 * LoginController is a class that handles events that occur when the user
 * interacts with login_screen.fxml
 * 
 * 
 */
public class LoginController implements Initializable {
	
	public static User curUser; // Should ensure user data is passed around
	
	// Buttons
	@FXML
	private Button button; // LOGIN button
	
	// Labels 
	@FXML
	private Label label1; // Project Name
	
	@FXML
	private Label label2; // Welcome
	
	@FXML
	private Label label3; // email
	
	@FXML
	private Label label4; // password
	
	@FXML
	private Label label5; // Don't have an account?
	
	@FXML
	private Label errorText; // To display errors to User
	
	@FXML
	private TextField textField;
	
	@FXML
	private PasswordField passwordField;
	
	// Images
	@FXML
	private ImageView image; // Background Image
	
	// HyperLinks
	@FXML
	private Hyperlink hyperlink; // Sign up here
	
	@FXML
	private Hyperlink hyperlink2; // Go Back button
	
	@FXML
	private Hyperlink homehandler;
	
	// Static variables to set style for button when mouse is away/hovering
	private static String normal_button_style = "-fx-background-color: white; -fx-background-radius: 20;";
	private static String hovered_button_style = "-fx-background-color: #d3d3d3; -fx-background-radius: 20;";
	private static String normal_signup_button_style = "-fx-text-fill: #91cd75; -fx-underline: false;";
	private static String hovered_signup_button_style = "-fx-text-fill: white; -fx-font-weight: bold; -fx-underline: false;";
	private static String normal_homehandler_button_style = "-fx-text-fill: white; -fx-underline: false;";
	private static String hovered_homehandler_button_style = "-fx-text-fill: deepskyblue; -fx-font-weight: bold; -fx-underline: false;";
	private static String normal_goback_button_style = "-fx-text-fill: #91cd75; -fx-underline: false;";
	private static String hovered_goback_button_style = "-fx-text-fill: white; -fx-font-weight: bold; -fx-underline: false;";
	
	/**
	 * This method will set a different style for button depending on whether or not mouse hovers it
	 * 
	 * @param location  location properties for controller and FXMLLoader
	 * @param resources  resources for controller and FXMLLoader
	 */
	@Override
	public void initialize( URL location, ResourceBundle resources ) {
		// Normal button/hyperlink style set to normal
	    button.setStyle(normal_button_style);
	    hyperlink.setStyle(normal_signup_button_style);
	    homehandler.setStyle(normal_homehandler_button_style);
	    hyperlink2.setStyle(normal_goback_button_style);
	    
	    // Changes to hovered button style, set to a light grey
	    button.setOnMouseEntered(e -> button.setStyle(hovered_button_style));
	    hyperlink.setOnMouseEntered(e -> hyperlink.setStyle(hovered_signup_button_style));
	    homehandler.setOnMouseEntered(e -> homehandler.setStyle(hovered_homehandler_button_style));
	    hyperlink2.setOnMouseEntered(e -> hyperlink2.setStyle(hovered_goback_button_style));
	    
	    // Changes back to normal button style when mouse stops hovering
	    button.setOnMouseExited(e -> button.setStyle(normal_button_style));
	    hyperlink.setOnMouseExited(e -> hyperlink.setStyle(normal_signup_button_style));
	    homehandler.setOnMouseExited(e -> homehandler.setStyle(normal_homehandler_button_style));
	    hyperlink2.setOnMouseExited(e -> hyperlink2.setStyle(normal_goback_button_style));
	    
	    // Sets to normal to fix the resizing when selecting a hyperlink
	    hyperlink.setOnMouseClicked(e -> hyperlink.setStyle(normal_signup_button_style));
	    hyperlink2.setOnMouseClicked(e -> hyperlink2.setStyle(normal_goback_button_style));
	    homehandler.setOnMouseClicked(e -> homehandler.setStyle(normal_homehandler_button_style));
	    
	    hyperlink.setOnMousePressed(e -> hyperlink.setStyle(normal_signup_button_style));
	    hyperlink2.setOnMousePressed(e -> hyperlink2.setStyle(normal_goback_button_style));
	    homehandler.setOnMousePressed(e -> homehandler.setStyle(normal_homehandler_button_style));
	    
	    hyperlink.setOnMouseReleased(e -> hyperlink.setStyle(normal_signup_button_style));
	    hyperlink2.setOnMouseReleased(e -> hyperlink2.setStyle(normal_goback_button_style));
	    homehandler.setOnMouseReleased(e -> homehandler.setStyle(normal_homehandler_button_style));
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
	public void handleLogin( ActionEvent event ) throws IOException {
		try {
			// Initialize data accessor via link to DB
			UserDataAccessor userDataAccessor = new UserDataAccessor( 
				"jdbc:mysql://awsmysql-nomadplus.c8lezqhu83hc.us-east-2.rds.amazonaws.com:3306"
				+ "/userData?autoReconnect=true&useSSL=false", "admin", "adminthisisjustaproject92521");			
			String emailRegexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
			boolean stopFlag = false;
			// Check if textField/passwordField is empty
			if (textField.getText() != null && !textField.getText().isEmpty() 
				&& passwordField.getText() != null && !passwordField.getText().isEmpty()) { 
				// Validate email format was entered
				if (!patternMatches(textField.getText(), emailRegexPattern)){ 
					errorText.setText("Please enter a valid email.");
					errorText.setStyle("-fx-font-weight: bold");
					errorText.setVisible(true);
					stopFlag = true;
				}
				// If textField is not empty and input is correct, check if credentials are valid
				if (stopFlag == false) {
					// Initialize User, retrieve data from DB
					curUser = new User();
					curUser = userDataAccessor.getUser(textField.getText(), passwordField.getText());
					// Check if user record exists
					if (curUser == null) {
						errorText.setText("User does not exist.");
						errorText.setStyle("-fx-font-weight: bold");
						errorText.setVisible(true);
					} // Check if password is correct
					else if(curUser.getFirstName() == "exists-but-passW-is-wrong") {
						errorText.setText("Your email and/or password is incorrect.");
						errorText.setStyle("-fx-font-weight: bold");
						errorText.setVisible(true);
					} // If credentials are valid, loads the FXML document for home_page and display it
					else if (textField.getText().equals(curUser.getEmailAd()) &&
					passwordField.getText().equals(curUser.getPassW())) {
						if(curUser.getAcctType().equals("Customer")) {
							Parent root = FXMLLoader.load(getClass().getResource("/application/home_page_customer_loggedin.fxml"));
							Stage window = (Stage)button.getScene().getWindow();
							window.setScene(new Scene (root, 1920, 1260));
							window.setMaximized(true);	
						}
						
						if(curUser.getAcctType().equals("Admin")) {
							Parent root = FXMLLoader.load(getClass().getResource("/application/home_page_admin_loggedin.fxml"));
							Stage window = (Stage)button.getScene().getWindow();
							window.setScene(new Scene (root));
							window.setMaximized(true);	
						}	
					}
				}
			} else { // Run if one or more fields are empty
				errorText.setText("Please enter information into both fields.");
				errorText.setStyle("-fx-font-weight: bold");
				errorText.setVisible(true);
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
	 * Changes view to the HOME PAGE after button is clicked
	 * 
	 * @param event	 event in which user clicks on the Project Name button
	 * @throws IOException	if a file is unable to be read
	 */
	@FXML
	public void handleBackToHomePage( ActionEvent event ) throws IOException {
		// Loads the FXML document for home_page and displays it
		Parent root = FXMLLoader.load(getClass().getResource("/application/home_page.fxml"));
		Stage window = (Stage)homehandler.getScene().getWindow();
		window.setMaximized(true);
		window.setScene(new Scene (root, 1920, 1050));	//Weird solution to fix scrollpane issue.
	}
	
	
	/**
	 * Changes view to the SIGN-UP PAGE after HyperLink is clicked
	 * 
	 * @param event	 event in which user clicks on the Sign here HyperLink
	 * @throws IOException  if a file is unable to be read
	 */
	public void handleSignUp( ActionEvent event ) throws IOException {
		// Loads the FXML document for the signup_screen and displays it
		Parent root = FXMLLoader.load(getClass().getResource("/application/signup_screen.fxml"));
		Stage window = (Stage)hyperlink.getScene().getWindow();
		window.setScene(new Scene (root));
	}
	
	public static boolean patternMatches(String emailAddress, String regexPattern) {
	    return Pattern.compile(regexPattern)
	      .matcher(emailAddress)
	      .matches();
	}
	
	// Performs login functionality if user hits enter key instead of button (must be focused on either textfield)
	@FXML
	public void handleLoginEnter(KeyEvent event) throws IOException {
	    if (event.getCode() == KeyCode.ENTER) {
	    	try {
				// Initialize data accessor via link to DB
				UserDataAccessor userDataAccessor = new UserDataAccessor( 
					"jdbc:mysql://awsmysql-nomadplus.c8lezqhu83hc.us-east-2.rds.amazonaws.com:3306"
					+ "/userData?autoReconnect=true&useSSL=false", "admin", "adminthisisjustaproject92521");			
				String emailRegexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
				boolean stopFlag = false;
				// Check if textField/passwordField is empty
				if (textField.getText() != null && !textField.getText().isEmpty() 
					&& passwordField.getText() != null && !passwordField.getText().isEmpty()) { 
					// Validate email format was entered
					if (!patternMatches(textField.getText(), emailRegexPattern)){ 
						errorText.setText("Please enter a valid email.");
						errorText.setStyle("-fx-font-weight: bold");
						errorText.setVisible(true);
						stopFlag = true;
					}
					// If textField is not empty and input is correct, check if credentials are valid
					if (stopFlag == false) {
						// Initialize User, retrieve data from DB
						curUser = new User();
						curUser = userDataAccessor.getUser(textField.getText(), passwordField.getText());
						// Check if user record exists
						if (curUser == null) {
							errorText.setText("User does not exist.");
							errorText.setStyle("-fx-font-weight: bold");
							errorText.setVisible(true);
						} // Check if password is correct
						else if(curUser.getFirstName() == "exists-but-passW-is-wrong") {
							errorText.setText("Your email and/or password is incorrect.");
							errorText.setStyle("-fx-font-weight: bold");
							errorText.setVisible(true);
						} // If credentials are valid, loads the FXML document for home_page and display it
						else if (textField.getText().equals(curUser.getEmailAd()) &&
						passwordField.getText().equals(curUser.getPassW())) {
							if(curUser.getAcctType().equals("Customer")) {
								Parent root = FXMLLoader.load(getClass().getResource("/application/home_page_customer_loggedin.fxml"));
								Stage window = (Stage)button.getScene().getWindow();
								window.setScene(new Scene (root, 1920, 1260));
								window.setMaximized(true);	
							}
							
							if(curUser.getAcctType().equals("Admin")) {
								Parent root = FXMLLoader.load(getClass().getResource("/application/home_page_admin_loggedin.fxml"));
								Stage window = (Stage)button.getScene().getWindow();
								window.setScene(new Scene (root));
								window.setMaximized(true);	
							}	
						}
					}
				} else { // Run if one or more fields are empty
					errorText.setText("Please enter information into both fields.");
					errorText.setStyle("-fx-font-weight: bold");
					errorText.setVisible(true);
				}
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }
	}
	
}
