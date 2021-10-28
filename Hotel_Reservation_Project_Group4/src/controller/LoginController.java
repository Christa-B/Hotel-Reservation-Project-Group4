/**
 * Hotel Reservation Project
 * UTSA CS 3773-002 - Fall 2021
 * Team 4: Andres De La Rosa, Jackson Raymond, Jalyn Merritt, Aden Rojas, Christa Baca 
 */

package controller;

import application.Main;
import application.application.UserDataAccessor;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;

/**
 * LoginController is a class that handles events that occur when the user
 * interacts with login_screen.fxml
 * 
 * @author Christa
 */
public class LoginController implements Initializable {
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
	private Hyperlink homehandler;
	
	// Static variables to set style for button when mouse is away/hovering
	private static String normal_button_style = "-fx-background-color: white; -fx-background-radius: 20;";
	private static String hovered_button_style = "-fx-background-color: #d3d3d3; -fx-background-radius: 20;";

	/**
	 * This method will set a different style for button depending on whether or not mouse hovers it
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
	    
		try {
			UserDataAccessor userDataAccessor = new UserDataAccessor(
					"jdbc:mysql://awsmysql-nomadplus.c8lezqhu83hc.us-east-2.rds.amazonaws.com:3306"
					+ "/userData?autoReconnect=true&useSSL=false", "admin", "adminthisisjustaproject92521");
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
	 * @param event	 event in which user clicks on the LOGIN button
	 * @throws IOException	if a file is unable to be read
	 */
	@FXML
	public void handleLogin( ActionEvent event ) throws IOException {
		try {
			UserDataAccessor userDataAccessor = new UserDataAccessor( // Initialize data accessor via link to DB
					"jdbc:mysql://awsmysql-nomadplus.c8lezqhu83hc.us-east-2.rds.amazonaws.com:3306"
					+ "/userData?autoReconnect=true&useSSL=false", "admin", "adminthisisjustaproject92521");
			if (textField.getText() != null && !textField.getText().isEmpty() 
				&& passwordField.getText() != null && !passwordField.getText().isEmpty()) { // Check if textField/passwordField is empty
				for (int i = 0; i < userDataAccessor.getUserList().size(); i++) { // If textField is not empty, check if credentials are valid
					
					if(textField.getText().equals(userDataAccessor.getUserList().get(i).getEmailAd()) && 
							!passwordField.getText().equals(userDataAccessor.getUserList().get(i).getPassW())) {
						errorText.setText("Your password is incorrect.");
						errorText.setStyle("-fx-font-weight: bold");
						errorText.setVisible(true);
					}
					else if (!textField.getText().equals(userDataAccessor.getUserList().get(i).getEmailAd()) &&
							!passwordField.getText().equals(userDataAccessor.getUserList().get(i).getPassW())) {
						
						errorText.setText("Your username and/or password is incorrect.");
						errorText.setStyle("-fx-font-weight: bold");
						errorText.setVisible(true);
					}
					else if (textField.getText().equals(userDataAccessor.getUserList().get(i).getEmailAd()) &&
							passwordField.getText().equals(userDataAccessor.getUserList().get(i).getPassW())) {
						// If credentials are valid, loads the FXML document for home_page and display it
						Parent root = FXMLLoader.load(getClass().getResource("/application/home_page.fxml"));
						Stage window = (Stage)button.getScene().getWindow();
						window.setScene(new Scene (root));
					}
				}
			} else {
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
		Stage window = (Stage)button.getScene().getWindow();
		window.setScene(new Scene (root));
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
		Stage window = (Stage)button.getScene().getWindow();
		window.setScene(new Scene (root));
	}
}
