/***
 * Hotel Reservation Project
 * UTSA CS 3773-002 - Fall 2021
 * Team 4: Andres De La Rosa, Jackson Raymond, Jalyn Merritt, Aden Rojas, Christa Baca 
 */

package controller;

import application.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
	private TextField textFieldFirstName; // first name
	
	@FXML
	private TextField textFieldLastName; // first name
	
	@FXML
	private TextField textFieldNumber; // phone number
	
	@FXML
	private TextField textfield3; 
	
	// HyperLinks
	@FXML
	private Hyperlink hyperlink; // Login here.
	
	@FXML
	private Hyperlink homehandler;
	
	// ChoiceBoxes
	@FXML
	private ComboBox comboBoxAccount; // Option for customer/administrator account type
	
	// Static variables to set style for button when mouse is away/hovering
	private static String normal_button_style = "-fx-background-color: white; -fx-background-radius: 20;";
	private static String hovered_button_style = "-fx-background-color: #d3d3d3; -fx-background-radius: 20;";
	
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
		
		// Normal button style set to white
	    button.setStyle(normal_button_style);
	    
	    // Changes to hovered button style, set to a light grey
	    button.setOnMouseEntered(e -> button.setStyle(hovered_button_style));
	    
	    // Changes back to normal button style when mouse stops hovering
	    button.setOnMouseExited(e -> button.setStyle(normal_button_style));
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
		window.setMaximized(true);
	}
	
	/**
	 * Changes view to the HOME PAGE after button is clicked
	 * 
	 * @param event	 event in which user clicks on the LOGIN button
	 * @throws IOException	if a file is unable to be read
	 */
	@FXML
	public void handleSignIn( ActionEvent event ) throws IOException {
		// Loads the FXML document for home_page and displays it
		Parent root = FXMLLoader.load(getClass().getResource("/application/home_page.fxml"));
		Stage window = (Stage)button.getScene().getWindow();
		window.setScene(new Scene (root));
		window.setMaximized(true);
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
		Parent root = FXMLLoader.load(getClass().getResource("/application/login_screen.fxml"));
		Stage window = (Stage)button.getScene().getWindow();
		window.setScene(new Scene (root));
		window.setMaximized(true);
	}
}
