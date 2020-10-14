package csci240.prinCad.command;

import csci240.prinCad.ui.CommandHandler;
import csci240.prinCad.ui.PrinCanvas;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class UndoCommand extends CommandHandler {

	public UndoCommand(PrinCanvas canvas) {
		super(canvas);
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Undo Pressed");
		getCanvas().undo();
	}
	
}
