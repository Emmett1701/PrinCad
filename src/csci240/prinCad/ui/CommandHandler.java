package csci240.prinCad.ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import csci240.prinCad.command.CanvasCommandInterface;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public abstract class CommandHandler{
	//Owning canvas
	private CanvasCommandInterface _canvas;
	
	//Current File
	protected static File _file = null;
	
	public CommandHandler (CanvasCommandInterface canvas) {
		_canvas = canvas;
	}
	
	public final CanvasCommandInterface getCanvas() {return _canvas; }
	
	protected Boolean changeFileToBackup(File originalFile) throws IOException{
		String originalExtension = originalFile.toString(); //Extension to original File
		
		StringBuilder builder = new StringBuilder(originalExtension); //Create String Builder to try to replace .pcd to .bak
		try {
			builder.replace(originalExtension.lastIndexOf(".pcd"), originalExtension.length(), ".bak");
		}
		catch(StringIndexOutOfBoundsException ex)
		{
			Log.error("Unable to rename .pcd file to .bak file", ex);
			throw ex;
		}
		
		String bakExtension = builder.toString(); //Get the .bak extension created by String Builder
		File bakFile = new File(bakExtension); //Create a File class using the bakExtension
		Files.deleteIfExists(bakFile.toPath()); //Delete the old .bak file that corresponds to the name of the .pcd file if it exists
		Boolean backupCreated = originalFile.renameTo(bakFile); //Rename current .pcd file to a .bak file and check for success
		
		return backupCreated; //return success condition
	}
	
	//Handle action event
	public abstract void action(ActionEvent e);
	
}
