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
 * AdminHomePageController is a class that handles events that occur when the user
 * interacts with home_page.fxml
 * 
 * @author Christa Baca
 */
public class AdminHomePageController implements Initializable{
	// Buttons
	@FXML
	private Button button; // Search Button
	
	// Labels
	@FXML
	private Label label1; // Placeholder for Admin Label
	
	// HyperLinks
	@FXML
	private Hyperlink hyperlink1; // PROJECT NAME
	
	@FXML
	private Hyperlink hyperlink2; // My Reservation; **Add link to see user reservations?
	
	@FXML
	private Hyperlink hyperlink3; // Add/Modify Hotel
	
	@FXML
	private Hyperlink hyperlink4; // Account Settings
	
	@FXML
	private Hyperlink hyperlink5; // Logout
	
	// MenuButtons
	@FXML
	private MenuButton menubutton; // Amenities
	
	// Images
	@FXML
	private ImageView image; // Background Image
	
	// ComboBoxes
	@FXML
	private ComboBox<String> combobox1; // Number of Rooms
	
	@FXML
	private ComboBox<String> combobox2; // Price Ranges
	
	// DatePickers 
	@FXML
	private DatePicker datepicker; // Check-in/Check-out dates
	
	// List of items for ComboBoxes (prices and rooms)
	ObservableList<String> list1 = FXCollections.observableArrayList("2 Rooms", "3 Rooms", "4 Rooms", "5 Rooms", "6 Rooms", "7 Rooms");
	ObservableList<String> list2 = FXCollections.observableArrayList("Low", "Mid", "High");

	// Static variables to set style for button when mouse is away/hovering
	private static String normal_button_style = "-fx-background-color: white; -fx-background-radius: 20;";
	private static String hovered_button_style = "-fx-background-color: #d3d3d3; -fx-background-radius: 20;";
	
	/**
	 * Initializes items for Number of Rooms and Price Range ComboBoxes
	 * 
	 * @param location  location properties for controller and FXMLLoader
	 * @param resources  resources for controller and FXMLLoader
	 */
	@Override
	public void initialize( URL location, ResourceBundle resources ) {
		// Sets list items for ComboBoxes
		combobox1.setItems(list1);
		combobox2.setItems(list2);
		
		// Normal button style set to white
	    button.setStyle(normal_button_style);
	    
	    // Changes to hovered button style, set to a light grey
	    button.setOnMouseEntered(e -> button.setStyle(hovered_button_style));
	    
	    // Changes back to normal button style when mouse stops hovering
	    button.setOnMouseExited(e -> button.setStyle(normal_button_style));
	}
	
	/**
	 * Handles event in which user wants to check their reservation
	 * 
	 * @param event  event in which user clicks My Reservation HyperLink
	 * @throws IOException  if a file is unable to be read
	 */
	@FXML
	public void handleMyReservation(ActionEvent event) throws IOException {
		// Loads the FXML document for account_settings and displays it
		Parent root = FXMLLoader.load(getClass().getResource("/application/user_reservation.fxml"));
		Stage window = (Stage)button.getScene().getWindow();
		window.setScene(new Scene (root));
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
	}
	
	/**
	 * Handles event in which user wants to logout from account
	 * 
	 * @param event  event in which user clicks Logout HyperLink
	 * @throws IOException  if a file is unable to be read
	 */
	@FXML
	public void handleLogout(ActionEvent event) throws IOException {
		// Loads the FXML document for login_screen and displays it
		Parent root = FXMLLoader.load(getClass().getResource("/application/login_screen.fxml"));
		Stage window = (Stage)button.getScene().getWindow();
		window.setScene(new Scene (root));
	}
	
	/**
	 * Handles event in which user clicks to search hotels
	 * 
	 * @param event  event in which user clicks SEARCH button
	 * @throws IOException  if a file is unable to be read
	 */
	@FXML
	public void handleSearch(ActionEvent event) throws IOException {
		// Loads the FXML document for account_settings and displays it
		Parent root = FXMLLoader.load(getClass().getResource("/application/results_page.fxml"));
		Stage window = (Stage)button.getScene().getWindow();
		window.setScene(new Scene (root));
	}
}