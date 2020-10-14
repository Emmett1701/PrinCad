package csci240.prinCad.command;

import csci240.prinCad.ui.CommandHandler;
import csci240.prinCad.ui.PrinCanvas;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class NewFileCommand extends CommandHandler{

	public NewFileCommand(PrinCanvas canvas) {
		super(canvas);
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Handle New File Command");
		
		//if Canvas isn't blank
		
		//TODO: Ask user if they want to save before Erasing the screen
		
		this.getCanvas().clearModel();
		_file = null;
	}
	
	
}
