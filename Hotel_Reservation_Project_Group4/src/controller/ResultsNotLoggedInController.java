/**
 * Hotel Reservation Project
 * UTSA CS 3773-002 - Fall 2021
 * Team 4: Andres De La Rosa, Jackson Raymond, Jalyn Merritt, Aden Rojas, Christa Baca 
 */

package controller;

import application.Main;
import application.application.Hotel;
import application.application.HotelDataAccessor;
import application.application.Reservation;
import application.application.UserDataAccessor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DateCell;
import javafx.scene.input.*;

/**
 * ResultsNotLoggedInController is a class that handles events that occur when the user
 * interacts with results_not_loggedin.fxml
 * 
 * @author Christa Baca
 */
public class ResultsNotLoggedInController implements Initializable{
	// TableView
	@FXML
	private TableView<Hotel> results_table;
	
	// TableColumn
	@FXML
	private TableColumn<Hotel, String> hotel_name;
	
	@FXML
	private TableColumn<Hotel, String> room_type;
	
	@FXML
	private TableColumn<Hotel, String> amenity_pool;
	
	@FXML
	private TableColumn<Hotel, String> amenity_gym;
	
	@FXML
	private TableColumn<Hotel, String> amenity_spa;
	
	@FXML
	private TableColumn<Hotel, String> amenity_business_office;
	
	@FXML
	private TableColumn<Hotel, String> amenity_wifi;
	
	@FXML
	private TableColumn<Hotel, Integer> available_rooms;
	
	@FXML
	private TableColumn<Hotel, String> hotel_location;
	
	@FXML 
	private TableColumn<Hotel, String> price_per_day;
	
	@FXML
	private TableColumn<Hotel, Integer> weekend_differential;
	
	@FXML
	private TableColumn<Hotel, String> total_price;
	
	// Buttons
	@FXML
	private Button search_button; // Search Button
	
	// Labels
	@FXML
	private Label label1; 
	
	// TextFields
	@FXML
	private TextField location_input;
	
	// HyperLinks
	@FXML
	private Hyperlink nomadplus_link; // Nomad+, links to home_page
	
	@FXML
	private Hyperlink login_link; // login
	
	@FXML
	private Hyperlink signup_link; // signup
	
	@FXML
	private Hyperlink go_back_link;
	
	// MenuButtons
	@FXML
	private MenuButton amenities_menu; // Amenities
	
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
	
	//@FXML
	//private ScrollPane scrollpane;
	
	// List of items for ComboBoxes (prices and rooms)
	ObservableList<String> list1 = FXCollections.observableArrayList("1 - 2 Guests", "3 Guests", "4 Guests", "5 Guests", "6 Guests", "7 Guests", "8 Guests", "9 Guests", "10 Guests");
	ObservableList<String> list2 = FXCollections.observableArrayList("1 Room", "2 Rooms", "3 Rooms", "4 Rooms", "5 Rooms", "6 Rooms", "7 Rooms", "8 Rooms", "9 Rooms", "10 Rooms");
	ObservableList<String> list3 = FXCollections.observableArrayList("Standard", "Queen", "King");
	ObservableList<String> list4 = FXCollections.observableArrayList("Less than $75", "$75 - $150", "$150+");

	// Static variables to set style for search_button when mouse is away/hovering
	private static String normal_button_style = "-fx-background-color: white; -fx-background-radius: 20";
	private static String hovered_button_style = "-fx-background-color: #d3d3d3; -fx-background-radius: 20;";
	private static String normal_login_button_style = "-fx-text-fill: white; -fx-font-size: 20;";
	private static String hovered_login_button_style = "-fx-text-fill: deepskyblue; -fx-font-weight: bold";
	private static String normal_signup_button_style = "-fx-text-fill: white; -fx-font-size: 20";
	private static String hovered_signup_button_style = "-fx-text-fill: deepskyblue; -fx-font-weight: bold;";
	private static String normal_nomadplus_link_style = "-fx-text-fill: white; -fx-text-size: 48;";
	private static String hovered_nomadplus_link_style = "-fx-text-fill: deepskyblue; -fx-font-weight: bold;";
	private static String normal_go_back_link_style = "-fx-text-fill: #91cd75; -fx-text-size: 16;";
	private static String hovered_go_back_link_style = "-fx-text-fill: white; -fx-font-weight: bold;";
	
	/**
	 * Initializes items for Number of Rooms and Price Range ComboBoxes
	 * 
	 * @param location  location properties for controller and FXMLLoader
	 * @param resources  resources for controller and FXMLLoader
	 */
	@Override
	public void initialize( URL location, ResourceBundle resources ) {	
		// Populate resultsTable
		populateTableView();
		
		// Sets list items for ComboBoxes
		num_guests_combobox.setItems(list1);
		num_rooms_combobox.setItems(list2);
		room_type_combobox.setItems(list3);
		price_range_combobox.setItems(list4);
		
		// Set combobox items to values
		num_guests_combobox.getSelectionModel().selectFirst();
		switch (HomePageController.currentReservation.getPartySize()) {
		case 0: System.out.println("ERROR\n");
				break;
		case 1: num_guests_combobox.getSelectionModel().selectFirst();
				break;
		case 2: num_guests_combobox.getSelectionModel().selectFirst();
				break;
		case 3: num_guests_combobox.getSelectionModel().select(1);
				break;
		case 4: num_guests_combobox.getSelectionModel().select(2);
				break;
		case 5: num_guests_combobox.getSelectionModel().select(3);
				break;
		case 6: num_guests_combobox.getSelectionModel().select(4);
				break;
		case 7: num_guests_combobox.getSelectionModel().select(5);
				break;
		case 8: num_guests_combobox.getSelectionModel().select(6);
				break;
		case 9: num_guests_combobox.getSelectionModel().select(7);
				break;
		case 10: num_guests_combobox.getSelectionModel().select(8);
				break;
		}
		num_rooms_combobox.getSelectionModel().selectFirst();
		switch (HomePageController.currentReservation.getNumRoomSel()) {
		case 0: System.out.println("ERROR\n");
				break;
		case 1: num_rooms_combobox.getSelectionModel().selectFirst();
				break;
		case 2: num_rooms_combobox.getSelectionModel().select(1);
				break;
		case 3: num_rooms_combobox.getSelectionModel().select(2);
				break;
		case 4: num_rooms_combobox.getSelectionModel().select(3);
				break;
		case 5: num_rooms_combobox.getSelectionModel().select(4);
				break;
		case 6: num_rooms_combobox.getSelectionModel().select(5);
				break;
		case 7: num_rooms_combobox.getSelectionModel().select(6);
				break;
		case 8: num_rooms_combobox.getSelectionModel().select(7);
				break;
		case 9: num_rooms_combobox.getSelectionModel().select(8);
				break;
		case 10: num_rooms_combobox.getSelectionModel().select(9);
		break;
		}
		
		room_type_combobox.getSelectionModel().selectFirst();
		switch (HomePageController.currentReservation.getTypeRoom()) {
		case "Standard": room_type_combobox.getSelectionModel().selectFirst();
			break;
		case "Queen": room_type_combobox.getSelectionModel().select(1);
			break;
		case "King": room_type_combobox.getSelectionModel().select(2);
			break;
		}
		price_range_combobox.getSelectionModel().selectFirst();
		switch (HomePageController.pricingChoice) {
		case "Less than $75": price_range_combobox.getSelectionModel().selectFirst();
			break;
		case "$75 - $150": price_range_combobox.getSelectionModel().select(1);
			break;
		case "$150+": price_range_combobox.getSelectionModel().select(2);
			break;
		}
		
		if (HomePageController.hotelLocation != "") {
			location_input.setText(HomePageController.hotelLocation);
		}
		
		check_in_datepicker.setValue(convertToLocalDateViaInstant(HomePageController.currentReservation.getCheckInDate()));
		check_out_datepicker.setValue(convertToLocalDateViaInstant(HomePageController.currentReservation.getCheckOutDate()));
		
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
	    search_button.setStyle(normal_button_style);
	    login_link.setStyle(normal_login_button_style);
	    signup_link.setStyle(normal_signup_button_style);
	    nomadplus_link.setStyle(normal_nomadplus_link_style);
	    go_back_link.setStyle(normal_go_back_link_style);
	    
	    
	    // Changes to hovered search_button style, set to a light grey
	    search_button.setOnMouseEntered(e -> search_button.setStyle(hovered_button_style));
	    login_link.setOnMouseEntered(e -> login_link.setStyle(hovered_login_button_style));
	    signup_link.setOnMouseEntered(e -> signup_link.setStyle(hovered_signup_button_style));
	    nomadplus_link.setOnMouseEntered(e -> nomadplus_link.setStyle(hovered_nomadplus_link_style));
	    go_back_link.setOnMouseEntered(e -> go_back_link.setStyle(hovered_go_back_link_style));
	    
	    // Changes back to normal search_button style when mouse stops hovering
	    search_button.setOnMouseExited(e -> search_button.setStyle(normal_button_style));
	    login_link.setOnMouseExited(e -> login_link.setStyle(normal_login_button_style));
	    signup_link.setOnMouseExited(e -> signup_link.setStyle(normal_signup_button_style));
	    nomadplus_link.setOnMouseExited(e -> nomadplus_link.setStyle(normal_nomadplus_link_style));
	    go_back_link.setOnMouseExited(e -> go_back_link.setStyle(normal_go_back_link_style));
	    
	    // Sets to normal style to fix resizing
	    login_link.setOnMouseClicked(e -> login_link.setStyle(normal_login_button_style));
	    signup_link.setOnMouseClicked(e -> signup_link.setStyle(normal_signup_button_style));
	    nomadplus_link.setOnMouseClicked(e -> nomadplus_link.setStyle(normal_nomadplus_link_style));
	    go_back_link.setOnMouseClicked(e -> go_back_link.setStyle(normal_go_back_link_style));
	    
	    login_link.setOnMousePressed(e -> login_link.setStyle(normal_login_button_style));
	    signup_link.setOnMousePressed(e -> signup_link.setStyle(normal_signup_button_style));
	    nomadplus_link.setOnMousePressed(e -> nomadplus_link.setStyle(normal_nomadplus_link_style));
	    go_back_link.setOnMousePressed(e -> go_back_link.setStyle(normal_go_back_link_style));
	    
	    login_link.setOnMouseReleased(e -> login_link.setStyle(normal_login_button_style));
	    signup_link.setOnMouseReleased(e -> signup_link.setStyle(normal_signup_button_style));
	    nomadplus_link.setOnMouseReleased(e -> nomadplus_link.setStyle(normal_nomadplus_link_style));
	    go_back_link.setOnMouseReleased(e -> go_back_link.setStyle(normal_go_back_link_style));
	}
	
		private ObservableList<Hotel> hotel = FXCollections.observableArrayList(
            	HomePageController.hotelList
    	);

	/**
	 * Handles event in which user wants to login
	 * 
	 * @param event  event in which user clicks Login HyperLink
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
	 * Handles event in which user wants to sign in
	 * 
	 * @param event  event in which user clicks signup HyperLink
	 * @throws IOException  if a file is unable to be read
	 */
	@FXML
	public void handleSignUp(ActionEvent event) throws IOException {
		// Loads the FXML document for signup_screen and displays it
		Parent root = FXMLLoader.load(getClass().getResource("/application/signup_screen.fxml"));
		Stage window = (Stage)signup_link.getScene().getWindow();
		window.setScene(new Scene (root));
		window.setMaximized(true);
		
	}
	
	/**
	 * Handles event in which user clicks to search hotels
	 * 
	 * @param event  event in which user clicks SEARCH search_button
	 * @throws IOException  if a file is unable to be read
	 */
	@FXML
	public void handleSearch(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
		int validFlag1 = 0, validFlag2 = 0, validFlag3 = 0;
		// Begin form validation, check if location_input is not blank
		if (location_input.getText() != null) {
			validFlag1 = 1;
		} else {
			// TODO: location_input error label "Please enter a Destination"
		}
		if (check_in_datepicker.getValue() != null) {
			validFlag2 = 1;
		} else {
		// TODO: check_in_date_picker error label "Please enter a check in date"
		}
		if (check_out_datepicker.getValue() != null) {
			validFlag3 = 1;
		} else {
		// TODO: check_out_date_picker error label "Please enter a check out date"
		}
		if (validFlag1 == 1 && validFlag2 == 1 && validFlag3 == 1) {
			java.util.Date check_in_date = java.util.Date.from(check_in_datepicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			java.util.Date check_out_date = java.util.Date.from(check_out_datepicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			int numRooms = Character.getNumericValue(num_rooms_combobox.getValue().charAt(0));
			if (Character.getNumericValue(num_rooms_combobox.getValue().charAt(1)) == 0) { numRooms = 10; }
			int partySize = Character.getNumericValue(num_guests_combobox.getValue().charAt(0));
			if (Character.getNumericValue(num_guests_combobox.getValue().charAt(1)) == 0) { partySize = 10; }
			HomePageController.pricingChoice = price_range_combobox.getValue();
			HomePageController.hotelLocation = location_input.getText();
			HomePageController.currentReservation = new Reservation(0, 0, location_input.getText(), check_in_date, check_out_date, numRooms, room_type_combobox.getValue(), 0, partySize, 0);
			if (HomePageController.currentReservation != null) {
				HotelDataAccessor hotelDataAccessor = new HotelDataAccessor( 
						"jdbc:mysql://awsmysql-nomadplus.c8lezqhu83hc.us-east-2.rds.amazonaws.com:3306"
								+ "/hotelData?autoReconnect=true&useSSL=false", "admin", "adminthisisjustaproject92521");
				HomePageController.hotelList = hotelDataAccessor.getHotels(location_input.getText());
			}
			
			populateTableView();
		}
	}
	
	/**
	 * Changes view to the first HOME PAGE after link is clicked
	 * 
	 * @param event	 event in which user clicks on the Nomad+ hyperlink
	 * @throws IOException	if a file is unable to be read
	 */
	
	@FXML
	public void handleBackToHomePage( ActionEvent event ) throws IOException {
		// Loads the FXML document for home_page and displays it
		Parent root = FXMLLoader.load(getClass().getResource("/application/home_page.fxml"));
		Stage window = (Stage)nomadplus_link.getScene().getWindow();
		window.setMaximized(true);
		window.setScene(new Scene (root, 1920, 1050));
	}
	
	public void populateTableView() {
		int i = 0;
		//ListIterator<Hotel> iter = HomePageController.hotelList.listIterator();
		boolean continueFlag = false;
		for (ListIterator<Hotel> iter = HomePageController.hotelList.listIterator(); continueFlag = iter.hasNext(); ) {
			if (HomePageController.hotelList.size() == i || continueFlag == false) { break; }
			if (continueFlag == true) {
			long noOfDaysBetween = ChronoUnit.DAYS.between(convertToLocalDateViaInstant(HomePageController.currentReservation.getCheckInDate()), convertToLocalDateViaInstant(HomePageController.currentReservation.getCheckOutDate()));
			List<String> hotelPrices = Arrays.asList(HomePageController.hotelList.get(i).getPrice_per_day().split(","));
			switch (HomePageController.currentReservation.getTypeRoom()) {
			case "Standard" : HomePageController.hotelList.get(i).setPrice_per_day(hotelPrices.get(0));
							  HomePageController.hotelList.get(i).setRoom_type("Standard");
				break;
			case "Queen" : HomePageController.hotelList.get(i).setPrice_per_day(hotelPrices.get(1));
			  			   HomePageController.hotelList.get(i).setRoom_type("Queen");
				break;
			case "King" : HomePageController.hotelList.get(i).setPrice_per_day(hotelPrices.get(2));
			  			  HomePageController.hotelList.get(i).setRoom_type("King");
				break;
			}
			int totalPrice = (int)noOfDaysBetween * Integer.valueOf(HomePageController.hotelList.get(i).getPrice_per_day().trim().substring(1));
			HomePageController.hotelList.get(i).setTotal_price(Integer.toString(totalPrice));
			//System.out.println(price_range_combobox.getSelectionModel().getSelectedIndex());
			//System.out.println(Integer.valueOf(HomePageController.hotelList.get(i).getPrice_per_day().trim().substring(1)));
			
			switch (HomePageController.pricingChoice) {
			case "Less than $75": if (Integer.valueOf(HomePageController.hotelList.get(i).getPrice_per_day().trim().substring(1)) > 75) {
								  	if(Integer.valueOf(iter.next().getPrice_per_day().trim().substring(1)) > 75){
								  		iter.remove();
								  	}
								  i--;
								  }
								  break;
			case "$75 - $150": if (Integer.valueOf(HomePageController.hotelList.get(i).getPrice_per_day().trim().substring(1)) < 75 || Integer.valueOf(HomePageController.hotelList.get(i).getPrice_per_day().trim().substring(1)) > 150) {
			  	if(Integer.valueOf(iter.next().getPrice_per_day().trim().substring(1)) < 75 || Integer.valueOf(iter.next().getPrice_per_day().trim().substring(1)) > 150){
			  		iter.remove();
			  	}
			  i--;
			  }
			  break;
			case "$150+": if (Integer.valueOf(HomePageController.hotelList.get(i).getPrice_per_day().trim().substring(1)) < 150) {
			  	if(Integer.valueOf(iter.next().getPrice_per_day().trim().substring(1)) > 75){
			  		iter.remove();
			  	}
			  i--;
			  }
			  break;
			}}
			if (continueFlag == true) {
			i++;
			}
		}
		hotel_name.setCellValueFactory(new PropertyValueFactory<>("Hotel_name"));
		room_type.setCellValueFactory(new PropertyValueFactory<>("Room_type"));
		amenity_pool.setCellValueFactory(new PropertyValueFactory<>("Amenity_pool"));
		amenity_gym.setCellValueFactory(new PropertyValueFactory<>("Amenity_gym"));
		amenity_spa.setCellValueFactory(new PropertyValueFactory<>("Amenity_spa"));
		amenity_business_office.setCellValueFactory(new PropertyValueFactory<>("Amenity_business_office"));
		amenity_wifi.setCellValueFactory(new PropertyValueFactory<>("Amenity_wifi"));
		available_rooms.setCellValueFactory(new PropertyValueFactory<>("Available_rooms"));
		hotel_location.setCellValueFactory(new PropertyValueFactory<>("Hotel_location"));
		price_per_day.setCellValueFactory(new PropertyValueFactory<>("Price_per_day"));
		weekend_differential.setCellValueFactory(new PropertyValueFactory<>("Weekend_differential"));
		total_price.setCellValueFactory(new PropertyValueFactory<>("Total_price"));
		results_table.setItems(hotel);
	    hotel = FXCollections.observableArrayList(
            	HomePageController.hotelList);
	}
	
	public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
}
