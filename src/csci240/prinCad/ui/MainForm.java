//Emmett Wainwright
//PrinCAD Project
//February, 2020
//MainForm

package csci240.prinCad.ui;

import java.io.File;
import java.net.URL;

import csci240.prinCad.util.Log;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class MainForm extends Application {
	
	private static int sceneWidth, sceneHeight, canvasWidth, canvasHeight, undoBufferSize;
	private static Color sceneBackgroundColor, canvasBackgroundColor;
	
	private static Stage stage;
	
	// Manager of file commands
	private FileManager _fileManager;
	
	//create static variable of instance of ApplicationSettings
	public static ApplicationSettings settings = new ApplicationSettings();
	
	
	public static void main(String[] args) {
		Log.info("PrinCad begin execution");
		
		try {
			// Launch the javaFX application
			launch(args);
			Log.info("PrinCad end execution");
		}
		catch (Exception ex) {
			Log.error("PrinCad crash with exception ", ex);
		}
		
	}
	
	// Format color for use with setStyle
	private String formatStyleColor(Color color) {
		String rx = String.format("%02X", Math.round(color.getRed() * 255.0));
		String gx = String.format("%02X", Math.round(color.getGreen() * 255.0));
		String bx = String.format("%02X", Math.round(color.getBlue() * 255.0));
		String fx = "-fx-background-color: #" + rx + gx + bx + ";";
		return fx;
	}

	
	// Override the start
	@Override public void start(Stage primaryStage) throws Exception{
		
		stage = primaryStage;
		
		//Get all settings necessary to create window
		sceneWidth = settings.getSceneWidth();
		sceneHeight = settings.getSceneHeight();
		sceneBackgroundColor = settings.getSceneColor();
		canvasWidth = settings.getCanvasWidth();
		canvasHeight = settings.getCanvasHeight();
		canvasBackgroundColor = settings.getCanvasColor();
		undoBufferSize = settings.getUndoBufferSize();
		
		
		// Create drawing canvas 
		PrinCanvas canvas = new PrinCanvas(canvasWidth, canvasHeight, canvasBackgroundColor, undoBufferSize);
		
		//Create border pane and attach canvas to center of layout
		BorderPane pane = new BorderPane(canvas);
		pane.setStyle(formatStyleColor(sceneBackgroundColor));
		
		//Create file manager
		_fileManager = new FileManager(canvas);
		
		//Create menu bar
		MenuBar mb = new MenuBar();
		pane.setTop(mb);
		ObservableList<Menu> menus = mb.getMenus();
		
		//add menus to menu bar
		Menu fileMenu = _fileManager.buildFileMenu();
		Menu editMenu = _fileManager.buildEditMenu();
		Menu toolsMenu = _fileManager.buildCADToolsMenu();
		menus.add(fileMenu);
		menus.add(editMenu);
		menus.add(toolsMenu);
		
		//Create VBox to hold right side button menu
		VBox vboxRight = new VBox(5);
		vboxRight.setPadding(new Insets(10));
		vboxRight.setAlignment(Pos.TOP_CENTER);
		pane.setRight(vboxRight);
		
		//Add buttons to right side bar
		ObservableList<Node> nodesRight = vboxRight.getChildren();
		_fileManager.addButtonsToRightBar(nodesRight); 
		
		//Create VBox to hold left side button menu
		VBox vboxLeft = new VBox(5);
		vboxLeft.setPadding(new Insets(10));
		vboxLeft.setAlignment(Pos.TOP_CENTER);
		pane.setLeft(vboxLeft);
		
		//Add buttons to left side bar
		ObservableList<Node> nodesLeft = vboxLeft.getChildren();
		_fileManager.addButtonsToLeftBar(nodesLeft);
		
		// Create a scene, attach layout pane to scene, set the size and background color
		Scene scene = new Scene(pane, sceneWidth, sceneHeight, sceneBackgroundColor);
		
		// Apply application styles
		File file = new File("AppStyles.css");
		if (!file.exists()) {
			Log.error(file.toString() + " does not exist.");
		}
		else {
			URL url = file.toURI().toURL();
			scene.getStylesheets().add(url.toExternalForm());
		}

		
		// Attach scene to stage
		primaryStage.setScene(scene);
		primaryStage.setTitle("CSCI 240 PrinCad Project");
        primaryStage.show();
	}
	
	
	
	//Override the stop
	@Override public void stop(){
		//Save Settings
		//settings.setCanvasWidth((int) stage.getWidth());
		//settings.setCanvasHeight((int) stage.getHeight());
	    settings.saveSettings();
	}
}
