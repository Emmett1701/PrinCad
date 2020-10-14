//Emmett Wainwright
//PrinCAD Project
//February, 2020
//SettingsInterface

package csci240.prinCad.ui;

import javafx.scene.paint.Color;

public interface SettingsInterface {
	//Logging Level Enum
	public enum LoggingLevel {
		None,
		Error,
		Information
	}
	
	/** DEFAULT SETTINGS **/
	//Scene Defaults
	final static String defaultSceneWidth = "1400";
	final static String defaultSceneHeight = "700";
	final static Color defaultSceneBackgroundColor = Color.DARKGOLDENROD;
	//Canvas Defaults
	final static String defaultCanvasWidth = "1000";
	final static String defaultCanvasHeight = "600";
	final static Color defaultCanvasBackgroundColor = Color.BLACK;
	final static Color defaultLineColor = Color.ORANGERED;
	final static Color defaultSelectionColor = Color.AQUAMARINE;
	//Logging Defaults
	final static LoggingLevel defaultLoggingLevel = LoggingLevel.Information; //Default to Error or higher to log issues with loading properties file
	//Other Defaults
	final static int defaultUndoBufferSize = 40;
	
	//Getters
	int getSceneWidth();
	int getSceneHeight();
	Color getSceneColor();
	int getCanvasWidth();
	int getCanvasHeight();
	Color getCanvasColor();
	LoggingLevel getLoggingLevel();
	int getUndoBufferSize();
	
	
	//Setters
	void setSceneWidth(int width);
	void setSceneHeight(int height);
	void setSceneColor(Color color);
	void setCanvasWidth(int width);
	void setCanvasHeight(int height);
	void setCanvasColor(Color color);
	void setLoggingLevel(LoggingLevel l);
	void setUndoBufferSize(int size);
	
	void saveSettings();
	
}
