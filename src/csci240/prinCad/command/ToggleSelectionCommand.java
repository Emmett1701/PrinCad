package csci240.prinCad.command;

import csci240.prinCad.ui.CommandHandler;
import csci240.prinCad.ui.PrinCanvas;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class ToggleSelectionCommand extends CommandHandler {
	
	private PrinCanvas ownedCanvas;
	
	public ToggleSelectionCommand(PrinCanvas canvas) {
		super(canvas);
		ownedCanvas = canvas;
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("ToggleSelection Pressed");
		ownedCanvas.toggleSelection();
	}

}
