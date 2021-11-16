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
import java.sql.SQLException;
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
 * interacts with home_page.fxml
 * 
 * @author Christa Baca
 */
public class HomePageController implements Initializable{
	// Buttons
	@FXML
	private Button button; // Search Button
	
	// Labels
	@FXML
	private Label label1; // Find the best hotels at the best price, here
	
	// Textfields
	private TextField location_input;
	
	// HyperLinks
	@FXML
	private Hyperlink nomadplus_link; // Nomad+/link to homepage
	
	@FXML
	private Hyperlink login_link; // Login
	
	@FXML
	private Hyperlink signup_link; // Sign in
	
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
	private DatePicker check_in_datepicker; // Check-in
	
	@FXML
	private DatePicker check_out_datepicker;	//check-out
	
	// List of items for ComboBoxes (prices and rooms)
	ObservableList<String> list1 = FXCollections.observableArrayList("1 - 2 Guests", "3 Guests", "4 Guests", "5 Guests", "6 Guests", "7 Guests", "8 Guests", "9 Guests", "10 Guests");
	ObservableList<String> list2 = FXCollections.observableArrayList("1 Room", "2 Rooms", "3 Rooms", "4 Rooms", "5 Rooms", "6 Rooms", "7 Rooms", "8 Rooms", "9 Rooms", "10 Rooms");
	ObservableList<String> list3 = FXCollections.observableArrayList("Standard", "Queen", "King");
	ObservableList<String> list4 = FXCollections.observableArrayList("Less than $75", "$75 - $150", "$150+");
	// Room & guest limitations based off of Booking.com, which maxes at 30 guests and 30 rooms.

	
	// Static variables to set style for button/labels when mouse is away/hovering
	private static String normal_button_style = "-fx-background-color: white; -fx-background-radius: 20";
	private static String hovered_button_style = "-fx-background-color: #d3d3d3; -fx-background-radius: 20;";
	private static String normal_login_button_style = "-fx-text-fill: white; -fx-font-size: 20;";
	private static String hovered_login_button_style = "-fx-text-fill: deepskyblue; -fx-font-weight: bold";
	private static String normal_signup_button_style = "-fx-text-fill: white; -fx-font-size: 20";
	private static String hovered_signup_button_style = "-fx-text-fill: deepskyblue; -fx-font-weight: bold;";
	private static String normal_nomadplus_link_style = "-fx-text-fill: white; -fx-text-size: 48;";
	private static String hovered_nomadplus_link_style = "-fx-text-fill: deepskyblue; -fx-font-weight: bold;";
	
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
		
		// Normal button/hyperlink style set to white
	    button.setStyle(normal_button_style);
	    login_link.setStyle(normal_login_button_style);
	    signup_link.setStyle(normal_signup_button_style);
	    nomadplus_link.setStyle(normal_nomadplus_link_style);
	    
	    // Changes to hovered button/hyperlink style, set to a light grey and deep sky blue respectively
	    button.setOnMouseEntered(e -> button.setStyle(hovered_button_style));
	    login_link.setOnMouseEntered(e -> login_link.setStyle(hovered_login_button_style));
	    signup_link.setOnMouseEntered(e -> signup_link.setStyle(hovered_signup_button_style));
	    nomadplus_link.setOnMouseEntered(e -> nomadplus_link.setStyle(hovered_nomadplus_link_style));
	    
	    // Changes back to normal button style when mouse stops hovering
	    button.setOnMouseExited(e -> button.setStyle(normal_button_style));
	    login_link.setOnMouseExited(e -> login_link.setStyle(normal_login_button_style));
	    signup_link.setOnMouseExited(e -> signup_link.setStyle(normal_signup_button_style));
	    nomadplus_link.setOnMouseExited(e -> nomadplus_link.setStyle(normal_nomadplus_link_style));
	    
	    // Changes to normal button style when mouse is clicked to fix it getting small for no reason
	    login_link.setOnMouseClicked(e -> login_link.setStyle(normal_login_button_style));
	    signup_link.setOnMouseClicked(e -> signup_link.setStyle(normal_signup_button_style));
	    nomadplus_link.setOnMouseClicked(e -> nomadplus_link.setStyle(normal_nomadplus_link_style));
	    
	    login_link.setOnMousePressed(e -> login_link.setStyle(normal_login_button_style));
	    signup_link.setOnMousePressed(e -> signup_link.setStyle(normal_signup_button_style));
	    nomadplus_link.setOnMousePressed(e -> nomadplus_link.setStyle(normal_nomadplus_link_style));
	    
	    login_link.setOnMouseReleased(e -> login_link.setStyle(normal_login_button_style));
	    signup_link.setOnMouseReleased(e -> signup_link.setStyle(normal_signup_button_style));
	    nomadplus_link.setOnMouseReleased(e -> nomadplus_link.setStyle(normal_nomadplus_link_style));
	}
	
	/**
	 * EDIT: Handles event in which user wants to login or sign in
	 * 
	 * @param event  event in which user clicks Logout HyperLink
	 * @throws IOException  if a file is unable to be read
	 */
	@FXML
	public void handleLogin(ActionEvent event) throws IOException {
		// Loads the FXML document for login_screen and displays it
		Parent root = FXMLLoader.load(getClass().getResource("/application/login_screen.fxml"));
		Stage window = (Stage)login_link.getScene().getWindow();
		window.setScene(new Scene (root));
		window.setMaximized(true);
		
	}
	
	/**
	 * EDIT: Handles event in which user wants to login or sign in
	 * 
	 * @param event  event in which user clicks Logout HyperLink
	 * @throws IOException  if a file is unable to be read
	 */
	@FXML
	public void handleSignUp(ActionEvent event) throws IOException {
		// Loads the FXML document for login_screen and displays it
		Parent root = FXMLLoader.load(getClass().getResource("/application/signup_screen.fxml"));
		Stage window = (Stage)signup_link.getScene().getWindow();
		window.setScene(new Scene (root));
		window.setMaximized(true);
		
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
		Parent root = FXMLLoader.load(getClass().getResource("/application/results_not_loggedin.fxml"));
		Stage window = (Stage)button.getScene().getWindow();
		window.setScene(new Scene (root));
		window.setMaximized(true);
	}
}
