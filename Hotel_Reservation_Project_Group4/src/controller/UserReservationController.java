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
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;

/**
 * UserReservationController is a class that handles events that occur when the user
 * interacts with user_reservation.fxml
 * 
 * @author Christa
 */
public class UserReservationController implements Initializable {
	// Buttons
	@FXML
	private Button button; // CANCEL RESERVATION button
	
	// Labels
	@FXML
	private Label label1; // Here is your reservation
	
	@FXML
	private Label label2; // Placeholder for reservation info
	
	// HyperLinks
	@FXML
	private Hyperlink hyperlink1; // PROJECT NAME
	
	@FXML
	private Hyperlink hyperlink2; // My Reservation
	
	@FXML
	private Hyperlink hyperlink3; // Account Settings
	
	@FXML
	private Hyperlink hyperlink4; // Logout
	
	// Static variables to set style for button when mouse is away/hovering
	private static String normal_button_style = "-fx-background-color: white; -fx-background-radius: 20;";
	private static String hovered_button_style = "-fx-background-color: #d3d3d3; -fx-background-radius: 20;";
	
	private static String normal_nomadplus_link_style = "-fx-text-fill: white; -fx-font-size: 48;";
	private static String hovered_nomadplus_link_style = "-fx-text-fill: deepskyblue; -fx-font-weight: bold";
	
	private static String normal_my_reservation_link_style = "-fx-text-fill: #91cd75; -fx-font-size: 22;";
	private static String hovered_my_reservation_link_style = "-fx-text-fill: white; -fx-font-weight: bold;";
	
	private static String normal_account_settings_link_style = "-fx-text-fill: #91cd75; -fx-font-size: 22;";
	private static String hovered_account_settings_link_style = "-fx-text-fill: white; -fx-font-weight: bold;";
	
	private static String normal_logout_link_style = "-fx-text-fill: #91cd75; -fx-font-size: 22;";
	private static String hovered_logout_link_style = "-fx-text-fill: white; -fx-font-weight: bold;";

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
	    hyperlink1.setStyle(normal_nomadplus_link_style);
	    hyperlink2.setStyle(normal_my_reservation_link_style);
	    hyperlink3.setStyle(normal_account_settings_link_style);
	    hyperlink4.setStyle(normal_logout_link_style);
	    
	    // Changes to hovered button style, set to a light grey
	    button.setOnMouseEntered(e -> button.setStyle(hovered_button_style));
	    hyperlink1.setOnMouseEntered(e -> hyperlink1.setStyle(hovered_nomadplus_link_style));
	    hyperlink2.setOnMouseEntered(e -> hyperlink2.setStyle(hovered_my_reservation_link_style));
	    hyperlink3.setOnMouseEntered(e -> hyperlink3.setStyle(hovered_account_settings_link_style));
	    hyperlink4.setOnMouseEntered(e -> hyperlink4.setStyle(hovered_logout_link_style));
	    
	    // Changes back to normal button style when mouse stops hovering
	    button.setOnMouseExited(e -> button.setStyle(normal_button_style));
	    hyperlink1.setOnMouseExited(e -> hyperlink1.setStyle(normal_nomadplus_link_style));
	    hyperlink2.setOnMouseExited(e -> hyperlink2.setStyle(normal_my_reservation_link_style));
	    hyperlink3.setOnMouseExited(e -> hyperlink3.setStyle(normal_account_settings_link_style));
	    hyperlink4.setOnMouseExited(e -> hyperlink4.setStyle(normal_logout_link_style));
	    
	    // Sets to normal style to fix resizing
	    hyperlink1.setOnMouseClicked(e -> hyperlink1.setStyle(normal_nomadplus_link_style));
	    hyperlink2.setOnMouseClicked(e -> hyperlink2.setStyle(normal_my_reservation_link_style));
	    hyperlink3.setOnMouseClicked(e -> hyperlink3.setStyle(normal_account_settings_link_style));
	    hyperlink4.setOnMouseClicked(e -> hyperlink4.setStyle(normal_logout_link_style));
	    
	    hyperlink1.setOnMousePressed(e -> hyperlink1.setStyle(normal_nomadplus_link_style));
	    hyperlink2.setOnMousePressed(e -> hyperlink2.setStyle(normal_my_reservation_link_style));
	    hyperlink3.setOnMousePressed(e -> hyperlink3.setStyle(normal_account_settings_link_style));
	    hyperlink4.setOnMousePressed(e -> hyperlink4.setStyle(normal_logout_link_style));
	    
	    hyperlink1.setOnMouseReleased(e -> hyperlink1.setStyle(normal_nomadplus_link_style));
	    hyperlink2.setOnMouseReleased(e -> hyperlink2.setStyle(normal_my_reservation_link_style));
	    hyperlink3.setOnMouseReleased(e -> hyperlink3.setStyle(normal_account_settings_link_style));
	    hyperlink4.setOnMouseReleased(e -> hyperlink4.setStyle(normal_logout_link_style));
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
		Stage window = (Stage)button.getScene().getWindow();
		window.setScene(new Scene (root));
		window.setMaximized(true);
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
		Parent root = FXMLLoader.load(getClass().getResource("/application/home_page_customer_loggedin.fxml"));
		Stage window = (Stage)button.getScene().getWindow();
		window.setMaximized(true);
		window.setScene(new Scene (root, 1920, 1220));
	}
	
	/**
	 * Handles event in which user wants to logout from account
	 * 
	 * @param event  event in which user clicks Logout HyperLink
	 * @throws IOException  if a file is unable to be read
	 */
	@FXML
	public void handleLogout(ActionEvent event) throws IOException {
		LoginController.curUser = null; //Cancels out the user session
		// Loads the FXML document for login_screen and displays it
		Parent root = FXMLLoader.load(getClass().getResource("/application/login_screen.fxml"));
		Stage window = (Stage)button.getScene().getWindow();
		window.setScene(new Scene (root));
		window.setMaximized(true);
	}
}
