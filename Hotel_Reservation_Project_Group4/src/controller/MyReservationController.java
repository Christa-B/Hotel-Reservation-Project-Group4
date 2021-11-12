/**
 * Hotel Reservation Project
 * UTSA CS 3773-002 - Fall 2021
 * Team 4: Andres De La Rosa, Jackson Raymond, Jalyn Merritt, Aden Rojas, Christa Baca 
 */

package controller;

import application.Main;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DateCell;
import javafx.scene.input.*;

/**
 * MyReservationController is a class that handles events that occur when the user
 * interacts with my_reservation.fxml
 * 
 * @author Christa Baca
 */
public class MyReservationController implements Initializable{
	// Buttons
	@FXML
	private Button button; // Search Button
	
	@FXML
	private Button button1; // Submit changes
	
	@FXML
	private Button button2; // Cancel Reservation
	
	// Labels
	@FXML
	private Label label1; // Find the best hotels at the best price, here
	
	// HyperLinks
	@FXML
	private Hyperlink hyperlink1; // Nomad+, links to home_page_admin_loggedin **may need to copy for customer ver.
	
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
	private Hyperlink hyperlink7; // Go back

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
	    button1.setStyle(normal_button_style);
	    button2.setStyle(normal_button_style);
	    
	    // Changes to hovered button style, set to a light grey
	    button1.setOnMouseEntered(e -> button1.setStyle(hovered_button_style));
	    button2.setOnMouseEntered(e -> button2.setStyle(hovered_button_style));
	    
	    // Changes back to normal button style when mouse stops hovering
	    button1.setOnMouseExited(e -> button1.setStyle(normal_button_style));
	    button2.setOnMouseExited(e -> button2.setStyle(normal_button_style));
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
		Stage window = (Stage)button1.getScene().getWindow();
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
		Stage window = (Stage)button1.getScene().getWindow();
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
		Stage window = (Stage)button1.getScene().getWindow();
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
		Stage window = (Stage)button1.getScene().getWindow();
		window.setScene(new Scene (root));
		window.setMaximized(true);
	}
	
	
	/**
	 * Changes view to the LOGGED IN (admin) HOME PAGE after button is clicked
	 * 
	 * @param event	 event in which user clicks on the Project Name button
	 * @throws IOException	if a file is unable to be read
	 */
	
	@FXML
	public void handleBackToHomePage( ActionEvent event ) throws IOException {
		// Loads the FXML document for home_page_loggedinand displays it
		Parent root = FXMLLoader.load(getClass().getResource("/application/home_page_admin_loggedin.fxml"));
		Stage window = (Stage)button1.getScene().getWindow();
		window.setScene(new Scene (root));
		window.setMaximized(true);
	}
	
	/**
	 * Changes view to the first HOME PAGE after button is clicked
	 * 
	 * @param event	 event in which user clicks on the Project Name button
	 * @throws IOException	if a file is unable to be read
	 */
	
	@FXML
	public void handleLogout( ActionEvent event ) throws IOException {
		// Loads the FXML document for home_page and displays it
		Parent root = FXMLLoader.load(getClass().getResource("/application/home_page.fxml"));
		Stage window = (Stage)button1.getScene().getWindow();
		window.setScene(new Scene (root));
		window.setMaximized(true);
	}
}
