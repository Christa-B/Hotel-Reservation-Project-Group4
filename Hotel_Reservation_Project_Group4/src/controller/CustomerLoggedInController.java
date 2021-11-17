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
import javafx.scene.control.TextField;
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
public class CustomerLoggedInController implements Initializable{
	// Buttons
	@FXML
	private Button search_button; // Search Button
	
	// Labels
	@FXML
	private Label welcome_label; // Welcome, (user first name)
	
	// Textfields
	private TextField location_input;
	
	// HyperLinks
	@FXML
	private Hyperlink nomadplus_link; // Nomad+, links to home_page_customer_loggedin
	
	@FXML
	private Hyperlink my_reservation_link; // My Reservation
	
	@FXML
	private Hyperlink account_settings_link; // Account Settings
	
	@FXML
	private Hyperlink logout_link; // Logout 
	
	// MenuButtons
	@FXML
	private MenuButton amenities_menu; // Amenities
	
	// Images
	@FXML
	private ImageView image; // Background Image
	
	// ComboBoxes
	@FXML
	private ComboBox<String> num_guests_combobox; // # of Guests (adults)
	
	@FXML
	private ComboBox<String> num_rooms_combobox; // # of rooms
	
	@FXML
	private ComboBox<String> room_type_combobox; // Room types
	
	@FXML
	private ComboBox<String> price_range_combobox; // Price ranges
	
	// DatePickers 
	@FXML
	private DatePicker check_in_datepicker; // Check in date
	
	@FXML
	private DatePicker check_out_datepicker; // Check out dates
	
	// List of items for ComboBoxes (prices and rooms)
	ObservableList<String> list1 = FXCollections.observableArrayList("1 - 2 Guests", "3 Guests", "4 Guests", "5 Guests", "6 Guests", "7 Guests", "8 Guests", "9 Guests", "10 Guests");
	ObservableList<String> list2 = FXCollections.observableArrayList("1 Room", "2 Rooms", "3 Rooms", "4 Rooms", "5 Rooms", "6 Rooms", "7 Rooms", "8 Rooms", "9 Rooms", "10 Rooms");
	ObservableList<String> list3 = FXCollections.observableArrayList("Standard", "Queen", "King");
	ObservableList<String> list4 = FXCollections.observableArrayList("Less than $75", "$75 - $150", "$150+");

	// Static variables to set style for search_button when mouse is away/hovering
	private static String normal_search_button_style = "-fx-background-color: white; -fx-background-radius: 20";
	private static String hovered_search_button_style = "-fx-background-color: #d3d3d3; -fx-background-radius: 20;";
	
	private static String normal_nomadplus_link_style = "-fx-text-fill: white; -fx-text-size: 48;";
	private static String hovered_nomadplus_link_style = "-fx-text-fill: deepskyblue; -fx-font-weight: bold;";
	
	private static String normal_my_reservation_link_style = "-fx-text-fill: white; -fx-font-size: 20;";
	private static String hovered_my_reservation_link_style = "-fx-text-fill: deepskyblue; -fx-font-weight: bold;";
	
	private static String normal_account_settings_link_style = "-fx-text-fill: white; -fx-font-size: 20;";
	private static String hovered_account_settings_link_style = "-fx-text-fill: deepskyblue; -fx-font-weight: bold;";
	
	private static String normal_logout_link_style = "-fx-text-fill: white; -fx-font-size: 20;";
	private static String hovered_logout_link_style = "-fx-text-fill: deepskyblue; -fx-font-weight: bold;";
	
	/**
	 * Initializes items for Number of Rooms and Price Range ComboBoxes
	 * 
	 * @param location  location properties for controller and FXMLLoader
	 * @param resources  resources for controller and FXMLLoader
	 */
	@Override
	public void initialize( URL location, ResourceBundle resources ) {
		// Sets list items for ComboBoxes
		num_guests_combobox.setItems(list1);
		num_rooms_combobox.setItems(list2);
		room_type_combobox.setItems(list3);
		price_range_combobox.setItems(list4);
		
		// Set combobox items to default value
		num_guests_combobox.getSelectionModel().selectFirst();
		num_rooms_combobox.getSelectionModel().selectFirst();
		room_type_combobox.getSelectionModel().selectFirst();
		price_range_combobox.getSelectionModel().selectFirst();
		
		// Prevents text input but allows user to copy text in comboboxes
		num_guests_combobox.setEditable(true);
		num_guests_combobox.getEditor().setEditable(false);
		num_rooms_combobox.setEditable(true);
		num_rooms_combobox.getEditor().setEditable(false);
		room_type_combobox.setEditable(true);
		room_type_combobox.getEditor().setEditable(false);
		price_range_combobox.setEditable(true);
		price_range_combobox.getEditor().setEditable(false);
		
		// Normal search_button style set to white
	    search_button.setStyle(normal_search_button_style);
	    nomadplus_link.setStyle(normal_nomadplus_link_style);
	    my_reservation_link.setStyle(normal_my_reservation_link_style);
	    account_settings_link.setStyle(normal_account_settings_link_style);
	    logout_link.setStyle(normal_logout_link_style);
	    
	    // Changes to hovered search_button style, set to a light grey
	    search_button.setOnMouseEntered(e -> search_button.setStyle(hovered_search_button_style));
	    nomadplus_link.setOnMouseEntered(e -> nomadplus_link.setStyle(hovered_nomadplus_link_style));
	    my_reservation_link.setOnMouseEntered(e -> my_reservation_link.setStyle(hovered_my_reservation_link_style));
	    account_settings_link.setOnMouseEntered(e -> account_settings_link.setStyle(hovered_account_settings_link_style));
	    logout_link.setOnMouseEntered(e -> logout_link.setStyle(hovered_logout_link_style));
	    
	    // Changes back to normal search_button style when mouse stops hovering
	    search_button.setOnMouseExited(e -> search_button.setStyle(normal_search_button_style));
	    nomadplus_link.setOnMouseExited(e -> nomadplus_link.setStyle(normal_nomadplus_link_style));
	    my_reservation_link.setOnMouseExited(e -> my_reservation_link.setStyle(normal_my_reservation_link_style));
	    account_settings_link.setOnMouseExited(e -> account_settings_link.setStyle(normal_account_settings_link_style));
	    logout_link.setOnMouseExited(e -> logout_link.setStyle(normal_logout_link_style));
	    
	    // Sets to normal style when clicked, released, or pressed
	    
	    nomadplus_link.setOnMouseClicked(e -> nomadplus_link.setStyle(normal_nomadplus_link_style));
	    my_reservation_link.setOnMouseClicked(e -> my_reservation_link.setStyle(normal_my_reservation_link_style));
	    account_settings_link.setOnMouseClicked(e -> account_settings_link.setStyle(normal_account_settings_link_style));
	    logout_link.setOnMouseClicked(e -> logout_link.setStyle(normal_logout_link_style));
	    
	    nomadplus_link.setOnMousePressed(e -> nomadplus_link.setStyle(normal_nomadplus_link_style));
	    my_reservation_link.setOnMousePressed(e -> my_reservation_link.setStyle(normal_my_reservation_link_style));
	    account_settings_link.setOnMousePressed(e -> account_settings_link.setStyle(normal_account_settings_link_style));
	    logout_link.setOnMousePressed(e -> logout_link.setStyle(normal_logout_link_style));
	    
	    nomadplus_link.setOnMouseReleased(e -> nomadplus_link.setStyle(normal_nomadplus_link_style));
	    my_reservation_link.setOnMouseReleased(e -> my_reservation_link.setStyle(normal_my_reservation_link_style));
	    account_settings_link.setOnMouseReleased(e -> account_settings_link.setStyle(normal_account_settings_link_style));
	    logout_link.setOnMouseReleased(e -> logout_link.setStyle(normal_logout_link_style));
	    
	    //Indicates user logged in
	    String firstname = LoginController.curUser.getFirstName();
	    welcome_label.setText("Welcome, " + firstname + ". Would you like to make a reservation?");
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
		Parent root = FXMLLoader.load(Main.class.getResource("/application/my_reservation.fxml"));
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
		Parent root = FXMLLoader.load(Main.class.getResource("/application/account_settings.fxml"));
		Stage window = (Stage)account_settings_link.getScene().getWindow();
		window.setScene(new Scene (root));
	}
	
	
	/**
	 * Handles event in which user clicks to search hotels
	 * 
	 * @param event  event in which user clicks SEARCH search_button
	 * @throws IOException  if a file is unable to be read
	 */
	@FXML
	public void handleSearch(ActionEvent event) throws IOException {
		// Loads the FXML document for search results and displays it
		Parent root = FXMLLoader.load(Main.class.getResource("/application/results.fxml"));
		Stage window = (Stage)search_button.getScene().getWindow();
		window.setScene(new Scene (root));
		window.setMaximized(true);
		
	}
	
	/**
	 * Changes view to the LOGGED IN (customer) HOME PAGE after search_button is clicked
	 * 
	 * @param event	 event in which user clicks on the Project Name search_button
	 * @throws IOException	if a file is unable to be read
	 */
	
	@FXML
	public void handleBackToHomePage( ActionEvent event ) throws IOException {
		// Loads the FXML document for home_page_customer_loggedin and displays it
		Parent root = FXMLLoader.load(Main.class.getResource("/application/home_page_customer_loggedin.fxml"));
		Stage window = (Stage)nomadplus_link.getScene().getWindow();
		window.setMaximized(true);
		window.setScene(new Scene (root, 1920, 1260));
	}
	
	
	/**
	 * Changes view to the first HOME PAGE after search_button is clicked
	 * 
	 * @param event	 event in which user clicks on the Project Name search_button
	 * @throws IOException	if a file is unable to be read
	 */
	
	@FXML
	public void handleLogout( ActionEvent event ) throws IOException {
		LoginController.curUser = null; //Cancels out the user session
		// Loads the FXML document for home_page and displays it
		Parent root = FXMLLoader.load(Main.class.getResource("/application/home_page.fxml"));
		Stage window = (Stage)logout_link.getScene().getWindow();
		window.setMaximized(true);
		window.setScene(new Scene (root, 1920, 1050));
	}
}
