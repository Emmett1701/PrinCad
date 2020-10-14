package csci240.prinCad.command;

import csci240.prinCad.control.RectangleTool;
import csci240.prinCad.ui.CommandHandler;
import csci240.prinCad.ui.PrinCanvas;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class RectangleCommand extends CommandHandler {

	public RectangleCommand(PrinCanvas canvas) {
		super(canvas);
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Rectangle Event");
		getCanvas().setActiveTool(new RectangleTool());
	}

}
