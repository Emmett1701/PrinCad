//Emmett Wainwright
//PrinCAD Project
//February, 2020
//ApplicationSettings

package csci240.prinCad.ui;

import java.io.*;
import java.util.Properties;

import csci240.prinCad.util.Log;
import javafx.scene.paint.Color;

class ApplicationSettings implements SettingsInterface{
	
	private String settingsFilePath = "AppSettings.properties";
	private Properties appProperties = new Properties();
	
	//Sets default properties and attempts to load in properties from properties file
	ApplicationSettings()
	{
		
		//Set Scene Defaults
		appProperties.setProperty("sceneWidth",defaultSceneWidth);
		appProperties.setProperty("sceneHeight",defaultSceneHeight);
		appProperties.setProperty("sceneColor",defaultSceneBackgroundColor.toString());
		//Set Canvas Defaults
		appProperties.setProperty("canvasWidth",defaultCanvasWidth);
		appProperties.setProperty("canvasHeight",defaultCanvasHeight);
		appProperties.setProperty("canvasColor",defaultCanvasBackgroundColor.toString());
		//Set Logging Defaults
		appProperties.setProperty("loggingLevel", defaultLoggingLevel.toString());
		//Other Settings
		appProperties.setProperty("undoBufferSize", String.valueOf(defaultUndoBufferSize));
		
		//Try to read settings .properties file
		try {
			FileInputStream in = new FileInputStream(settingsFilePath); //open input stream
			appProperties.load(in);
			in.close(); //close input stream
			
			
			//set properties for all settings declared in .properties file
			for(String key : appProperties.stringPropertyNames())
			{
				String value = appProperties.getProperty(key);
				appProperties.setProperty(key, value);
			}
		}
		catch (IOException e) {
			Log.error("Error reading " +  settingsFilePath + " file with exception ", e);
		}
	}
	
	
	//Getters for all individual properties
	public int getSceneWidth(){
		return Integer.parseInt(appProperties.getProperty("sceneWidth"));
	}
	public int getSceneHeight(){
		return Integer.parseInt(appProperties.getProperty("sceneHeight"));
	}
	public Color getSceneColor(){
		return Color.web(appProperties.getProperty("sceneColor"));
	}
	public int getCanvasWidth(){
		return Integer.parseInt(appProperties.getProperty("canvasWidth"));
	}
	public int getCanvasHeight(){
		return Integer.parseInt(appProperties.getProperty("canvasHeight"));
	}
	public Color getCanvasColor(){
		return Color.web(appProperties.getProperty("canvasColor"));
	}
	public LoggingLevel getLoggingLevel() {
		return LoggingLevel.valueOf(appProperties.getProperty("loggingLevel"));
	}
	public int getUndoBufferSize() {
		return Integer.parseInt(appProperties.getProperty("undoBufferSize"));
	}
	
	//Setters for all individual properties
	public void setSceneWidth(int width){
		appProperties.setProperty("sceneWidth", Integer.toString(width));
	}
	public void setSceneHeight(int height){
		appProperties.setProperty("sceneHeight", Integer.toString(height));
	}
	public void setSceneColor(Color color){
		appProperties.setProperty("sceneColor", color.toString());
	}
	public void setCanvasWidth(int width){
		appProperties.setProperty("canvasWidth", Integer.toString(width));
	}
	public void setCanvasHeight(int height){
		appProperties.setProperty("canvasHeight", Integer.toString(height));
	}
	public void setCanvasColor(Color color){
		appProperties.setProperty("canvasColor", color.toString());
	}
	public void setLoggingLevel(LoggingLevel l) {
		appProperties.setProperty("loggingLevel", l.toString());
	}
	public void setUndoBufferSize(int size) {
		appProperties.setProperty("undoBufferSize", String.valueOf(size));
	}
	
	//Method to save settings to AppSettings.properties file
	public void saveSettings()
	{
		try {
			FileOutputStream out = new FileOutputStream(settingsFilePath); //open file output stream
			
			appProperties.store(out, null);
			out.close(); //close file output stream
			Log.info("Application Settings saved to " + settingsFilePath);
		}
		catch (IOException e) {
			Log.error("Error saving Application Settings to " + settingsFilePath + " with exception " , e);
		}
	}

}
