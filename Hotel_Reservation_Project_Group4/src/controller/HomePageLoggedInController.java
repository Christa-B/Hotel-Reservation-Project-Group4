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
 * HomePageController is a class that handles events that occur when the user
 * interacts with home_page_customer_loggedin.fxml
 * 
 * @author Christa Baca
 */
public class HomePageLoggedInController implements Initializable{
	// Buttons
	@FXML
	private Button button; // Search Button
	
	// Labels
	@FXML
	private Label label1; // Find the best hotels at the best price, here
	
	// HyperLinks
	@FXML
	private Hyperlink hyperlink1; // Nomad+, links to home_page_customer_loggedin
	
	@FXML
	private Hyperlink hyperlink2; // My Reservation
	
	@FXML
	private Hyperlink hyperlink3; // Account Settings
	
	@FXML
	private Hyperlink hyperlink4; // Logout 
	
	// MenuButtons
	@FXML
	private MenuButton menubutton; // Amenities
	
	// Images
	@FXML
	private ImageView image; // Background Image
	
	// ComboBoxes
	@FXML
	private ComboBox<String> combobox1; // # of Guests (adults)
	
	@FXML
	private ComboBox<String> combobox2; // # of rooms
	
	@FXML
	private ComboBox<String> combobox3; // Room types
	
	@FXML
	private ComboBox<String> combobox4; // Price ranges
	
	// DatePickers 
	@FXML
	private DatePicker datepicker; // Check-in/Check-out dates
	
	// List of items for ComboBoxes (prices and rooms)
	ObservableList<String> list1 = FXCollections.observableArrayList("1 - 2 Guests", "3 Guests", "4 Guests", "5 Guests", "6 Guests", "7 Guests", "8 Guests", "9 Guests", "10 Guests");
	ObservableList<String> list2 = FXCollections.observableArrayList("1 Room", "2 Rooms", "3 Rooms", "4 Rooms", "5 Rooms", "6 Rooms", "7 Rooms", "8 Rooms", "9 Rooms", "10 Rooms");
	ObservableList<String> list3 = FXCollections.observableArrayList("Standard", "Queen", "King");
	ObservableList<String> list4 = FXCollections.observableArrayList("Less than $75", "$75 - $150", "$150+");

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
		// Sets list items for ComboBoxes
		combobox1.setItems(list1);
		combobox2.setItems(list2);
		combobox3.setItems(list3);
		combobox4.setItems(list4);
		
		// Set combobox items to default value
		combobox1.getSelectionModel().selectFirst();
		combobox2.getSelectionModel().selectFirst();
		combobox3.getSelectionModel().selectFirst();
		combobox4.getSelectionModel().selectFirst();
		
		// Prevents text input but allows user to copy text in comboboxes
		combobox1.setEditable(true);
		combobox1.getEditor().setEditable(false);
		combobox2.setEditable(true);
		combobox2.getEditor().setEditable(false);
		combobox3.setEditable(true);
		combobox3.getEditor().setEditable(false);
		combobox4.setEditable(true);
		combobox4.getEditor().setEditable(false);
		
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
		Parent root = FXMLLoader.load(getClass().getResource("/application/my_reservation.fxml"));
		Stage window = (Stage)button.getScene().getWindow();
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
		// Loads the FXML document for search results and displays it
		Parent root = FXMLLoader.load(getClass().getResource("/application/results.fxml"));
		Stage window = (Stage)button.getScene().getWindow();
		window.setScene(new Scene (root));
		window.setMaximized(true);
		
	}
	
	/**
	 * Changes view to the LOGGED IN (customer) HOME PAGE after button is clicked
	 * 
	 * @param event	 event in which user clicks on the Project Name button
	 * @throws IOException	if a file is unable to be read
	 */
	
	@FXML
	public void handleBackToHomePage( ActionEvent event ) throws IOException {
		// Loads the FXML document for home_page_customer_loggedin and displays it
		Parent root = FXMLLoader.load(getClass().getResource("/application/home_page_customer_loggedin.fxml"));
		Stage window = (Stage)button.getScene().getWindow();
		window.setMaximized(true);
		window.setScene(new Scene (root, 1920, 1050));
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
		Stage window = (Stage)button.getScene().getWindow();
		window.setMaximized(true);
		window.setScene(new Scene (root, 1920, 1050));
	}
}
