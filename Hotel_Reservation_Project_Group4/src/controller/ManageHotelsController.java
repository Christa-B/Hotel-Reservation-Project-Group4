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
 * ManageHotels is a class that handles events that occur when the user
 * interacts with manage_hotels.fxml
 * 
 * @author Christa Baca
 */
public class ManageHotelsController implements Initializable{
	// Buttons
	@FXML
	private Button button1; // add hotel
	
	@FXML
	private Button button2; // submit changes
	
	// Labels
	@FXML
	private Label label1; // Find the best hotels at the best price, here
	
	// HyperLinks
	@FXML
	private Hyperlink hyperlink1; // Nomad+, links to home_page_admin_loggedin
	
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
	
	private static String normal_nomadplus_link_style = "-fx-text-fill: white; -fx-font-size: 48; -fx-underline: false;";
	private static String hovered_nomadplus_link_style = "-fx-text-fill: deepskyblue; -fx-font-weight: bold; -fx-underline: false;";
	
	private static String normal_manage_hotels_link_style = "-fx-text-fill: white; -fx-font-size: 20; -fx-underline: false;";
	private static String hovered_manage_hotels_link_style = "-fx-text-fill: deepskyblue; -fx-font-weight: bold; -fx-underline: false;";
	
	private static String normal_manage_reservations_link_style = "-fx-text-fill: white; -fx-font-size: 20; -fx-underline: false;";
	private static String hovered_manage_reservations_link_style = "-fx-text-fill: deepskyblue; -fx-font-weight: bold; -fx-underline: false;";
	
	private static String normal_my_reservation_link_style = "-fx-text-fill: white; -fx-font-size: 20; -fx-underline: false;";
	private static String hovered_my_reservation_link_style = "-fx-text-fill: deepskyblue; -fx-font-weight: bold; -fx-underline: false;";
	
	private static String normal_account_settings_link_style = "-fx-text-fill: white; -fx-font-size: 20; -fx-underline: false;";
	private static String hovered_account_settings_link_style = "-fx-text-fill: deepskyblue; -fx-font-weight: bold; -fx-underline: false;";
	
	private static String normal_logout_link_style = "-fx-text-fill: white; -fx-font-size: 20; -fx-underline: false;";
	private static String hovered_logout_link_style = "-fx-text-fill: deepskyblue; -fx-font-weight: bold; -fx-underline: false;";
	
	private static String normal_go_back_link_style = "-fx-text-fill: #91cd75; -fx-font-size: 20; -fx-underline: false;";
	private static String hovered_go_back_link_style = "-fx-text-fill: white; -fx-font-weight: bold; -fx-underline: false;";
	
	
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
	    hyperlink1.setStyle(normal_nomadplus_link_style);
	    hyperlink2.setStyle(normal_manage_hotels_link_style);
	    hyperlink3.setStyle(normal_manage_reservations_link_style);
	    hyperlink4.setStyle(normal_my_reservation_link_style);
	    hyperlink5.setStyle(normal_account_settings_link_style);
	    hyperlink6.setStyle(normal_logout_link_style);
	    hyperlink7.setStyle(normal_go_back_link_style);
	    
	    // Changes to hovered button style, set to a light grey
	    button1.setOnMouseEntered(e -> button1.setStyle(hovered_button_style));
	    button2.setOnMouseEntered(e -> button2.setStyle(hovered_button_style));
	    
	    hyperlink1.setOnMouseEntered(e -> hyperlink1.setStyle(hovered_nomadplus_link_style));
	    hyperlink2.setOnMouseEntered(e -> hyperlink2.setStyle(hovered_manage_hotels_link_style));
	    hyperlink3.setOnMouseEntered(e -> hyperlink3.setStyle(hovered_manage_reservations_link_style));
	    hyperlink4.setOnMouseEntered(e -> hyperlink4.setStyle(hovered_my_reservation_link_style));
	    hyperlink5.setOnMouseEntered(e -> hyperlink5.setStyle(hovered_account_settings_link_style));
	    hyperlink6.setOnMouseEntered(e -> hyperlink6.setStyle(hovered_logout_link_style));
	    hyperlink7.setOnMouseEntered(e -> hyperlink7.setStyle(hovered_go_back_link_style));
	    
	    // Changes back to normal button style when mouse stops hovering
	    button1.setOnMouseExited(e -> button1.setStyle(normal_button_style));  
	    button2.setOnMouseExited(e -> button2.setStyle(normal_button_style));
	    
	    hyperlink1.setOnMouseExited(e -> hyperlink1.setStyle(normal_nomadplus_link_style));
	    hyperlink2.setOnMouseExited(e -> hyperlink2.setStyle(normal_manage_hotels_link_style));
	    hyperlink3.setOnMouseExited(e -> hyperlink3.setStyle(normal_manage_reservations_link_style));
	    hyperlink4.setOnMouseExited(e -> hyperlink4.setStyle(normal_my_reservation_link_style));
	    hyperlink5.setOnMouseExited(e -> hyperlink5.setStyle(normal_account_settings_link_style));
	    hyperlink6.setOnMouseExited(e -> hyperlink6.setStyle(normal_logout_link_style));
	    hyperlink7.setOnMouseExited(e -> hyperlink7.setStyle(normal_go_back_link_style));
	    
	    // Sets to normal style for resizing
	    
	    hyperlink1.setOnMouseClicked(e -> hyperlink1.setStyle(normal_nomadplus_link_style));
	    hyperlink2.setOnMouseClicked(e -> hyperlink2.setStyle(normal_manage_hotels_link_style));
	    hyperlink3.setOnMouseClicked(e -> hyperlink3.setStyle(normal_manage_reservations_link_style));
	    hyperlink4.setOnMouseClicked(e -> hyperlink4.setStyle(normal_my_reservation_link_style));
	    hyperlink5.setOnMouseClicked(e -> hyperlink5.setStyle(normal_account_settings_link_style));
	    hyperlink6.setOnMouseClicked(e -> hyperlink6.setStyle(normal_logout_link_style));
	    hyperlink7.setOnMouseClicked(e -> hyperlink7.setStyle(normal_go_back_link_style));
	    
	    hyperlink1.setOnMousePressed(e -> hyperlink1.setStyle(normal_nomadplus_link_style));
	    hyperlink2.setOnMousePressed(e -> hyperlink2.setStyle(normal_manage_hotels_link_style));
	    hyperlink3.setOnMousePressed(e -> hyperlink3.setStyle(normal_manage_reservations_link_style));
	    hyperlink4.setOnMousePressed(e -> hyperlink4.setStyle(normal_my_reservation_link_style));
	    hyperlink5.setOnMousePressed(e -> hyperlink5.setStyle(normal_account_settings_link_style));
	    hyperlink6.setOnMousePressed(e -> hyperlink6.setStyle(normal_logout_link_style));
	    hyperlink7.setOnMousePressed(e -> hyperlink7.setStyle(normal_go_back_link_style));
	    
	    hyperlink1.setOnMouseReleased(e -> hyperlink1.setStyle(normal_nomadplus_link_style));
	    hyperlink2.setOnMouseReleased(e -> hyperlink2.setStyle(normal_manage_hotels_link_style));
	    hyperlink3.setOnMouseReleased(e -> hyperlink3.setStyle(normal_manage_reservations_link_style));
	    hyperlink4.setOnMouseReleased(e -> hyperlink4.setStyle(normal_my_reservation_link_style));
	    hyperlink5.setOnMouseReleased(e -> hyperlink5.setStyle(normal_account_settings_link_style));
	    hyperlink6.setOnMouseReleased(e -> hyperlink6.setStyle(normal_logout_link_style));
	    hyperlink7.setOnMouseReleased(e -> hyperlink7.setStyle(normal_go_back_link_style));
	}
	
	
	/**
	 * Handles event in which user wants to manage reservations
	 * 
	 * @param event  event in which user clicks My Reservation HyperLink
	 * @throws IOException  if a file is unable to be read
	 */
	@FXML
	public void handleManageReservations(ActionEvent event) throws IOException {
		// Loads the FXML document for manage_reservations and displays it
		Parent root = FXMLLoader.load(getClass().getResource("/application/manage_reservations.fxml"));
		Stage window = (Stage)hyperlink3.getScene().getWindow();
		window.setScene(new Scene (root));
		window.setMaximized(true);
	}
	
	
	/**
	 * Handles event in which user wants to manage hotel properties
	 * 
	 * @param event  event in which user clicks My Reservation HyperLink
	 * @throws IOException  if a file is unable to be read
	 */
	@FXML
	public void handleManageHotels(ActionEvent event) throws IOException {
		// Loads the FXML document for manage_hotels and displays it
		Parent root = FXMLLoader.load(getClass().getResource("/application/manage_hotels.fxml"));
		Stage window = (Stage)hyperlink2.getScene().getWindow();
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
		// Loads the FXML document for my_account and displays it
		Parent root = FXMLLoader.load(getClass().getResource("/application/my_reservation.fxml"));
		Stage window = (Stage)hyperlink4.getScene().getWindow();
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
		Stage window = (Stage)hyperlink5.getScene().getWindow();
		window.setScene(new Scene (root));
		window.setMaximized(true);
	}
	

	/**
	 * Changes view to the LOGGED IN (admin) HOME PAGE after link is clicked
	 * 
	 * @param event	 event in which user clicks on the Nomad+ hyperlink
	 * @throws IOException	if a file is unable to be read
	 */
	@FXML
	public void handleBackToHomePage( ActionEvent event ) throws IOException {
		// Loads the FXML document for home_page_admin_loggedin and displays it
		Parent root = FXMLLoader.load(getClass().getResource("/application/home_page_admin_loggedin.fxml"));
		Stage window = (Stage)hyperlink1.getScene().getWindow();
		window.setMaximized(true);
		window.setScene(new Scene (root, 1920, 1220));
	}
	
	
	/**
	 * Changes view to the first HOME PAGE after link is clicked
	 * 
	 * @param event	 event in which user clicks on the Logout hyperlink
	 * @throws IOException	if a file is unable to be read
	 */
	@FXML
	public void handleLogout( ActionEvent event ) throws IOException {
		LoginController.curUser = null; //Cancels out the user session
		// Loads the FXML document for home_page and displays it
		Parent root = FXMLLoader.load(getClass().getResource("/application/home_page.fxml"));
		Stage window = (Stage)hyperlink6.getScene().getWindow();
		window.setMaximized(true);
		window.setScene(new Scene (root, 1920, 1050));
	}
}
