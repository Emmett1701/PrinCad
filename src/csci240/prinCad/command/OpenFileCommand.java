package csci240.prinCad.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import csci240.prinCad.ui.CommandHandler;
import csci240.prinCad.ui.PrinCanvas;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

public class OpenFileCommand extends CommandHandler {

	public OpenFileCommand(PrinCanvas canvas) {
		super(canvas);
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Handle open file event");
		
		try {
			Window stage = getCanvas().getScene().getWindow(); //Get stage
			
			FileChooser fileChooser = new FileChooser(); //Create file chooser
			fileChooser.setTitle("Open File");
			fileChooser.getExtensionFilters().add(new ExtensionFilter("PrinCad Files", "*.pcd"));
			File selectedFile = fileChooser.showOpenDialog(stage);
			
			if(selectedFile != null) {
				_file = selectedFile;
				BufferedReader br = new BufferedReader(new FileReader(_file));
				getCanvas().openFromFile(br);
			}
			
		}
		catch (Exception ex) {
			Log.error("Error in reading file with exception ", ex);
		}
		
		getCanvas().draw();
		
	}
}
