/**
 * Hotel Reservation Project
 * UTSA CS 3773-002 - Fall 2021
 * Team 4: Andres De La Rosa, Jackson Raymond, Jalyn Merritt, Aden Rojas, Christa Baca 
 */

package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * Main is a class containing the main method to run Hotel Reservation Project
 * 
 * @author Christa Baca 
 */
public class Main extends Application {
	
	/**
	 * Overrides the start method to show the login screen
	 * 
	 * @param pStage primary stage for login screen
	 */
	@Override
	public void start( Stage pStage ) {
		// Try and catch block for exception handling
		try {	
			// Load the FXML document
			Parent root = FXMLLoader.load( getClass().getResource( "/application/home_page.fxml"));
			
			// Set the scene to stage and show the stage to the user
			Scene scene = new Scene( root );
			pStage.setTitle( "Nomad+" );
			pStage.setScene( scene );
			pStage.show();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Main function to run program
	 * 
	 * @param args String[] argument to launch program 
	 */
	public static void main( String[] args ) {
		launch(args);
	}
}
