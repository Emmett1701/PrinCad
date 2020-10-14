package csci240.prinCad.command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import csci240.prinCad.ui.CommandHandler;
import csci240.prinCad.ui.PrinCanvas;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class SaveAsFileCommand extends CommandHandler{

	public SaveAsFileCommand(PrinCanvas canvas) {
		super(canvas);
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Save As File Event");
		
		try {
			Log.info("Prompt to save as");
			
			Window stage = getCanvas().getScene().getWindow(); //Get stage
			
			FileChooser fileChooser = new FileChooser(); //Create file chooser
			fileChooser.setTitle("Save Model As");
			String tempExtension = fileChooser.showSaveDialog(stage).toString(); //Save extension returned from filechooser as a string
			if(!tempExtension.substring(tempExtension.length()-4).equals(".pcd"))  //Make sure user didn't enter in the .pcd extension
				_file = new File(tempExtension + ".pcd"); //Add .pcd extension when setting current file
			else
				_file = new File(tempExtension);
			
			if(_file.exists()) {
				changeFileToBackup(_file);
			}
			
			FileWriter fw = new FileWriter(_file);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw, true);
			
			getCanvas().saveToFile(out); //Call saveToFile method in prinCanvas which has the ModelManager save the current state
			
			out.flush();
			out.close();
			
			Log.info("Drawing saved to file " + _file);
		}
		catch (Exception ex) {
			Log.error("Error in choosing file to save to with exception ", ex);
		}
	}
	
}
