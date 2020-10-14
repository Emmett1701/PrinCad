package csci240.prinCad.command;

import csci240.prinCad.control.CircleTool;
import csci240.prinCad.ui.CommandHandler;
import csci240.prinCad.ui.PrinCanvas;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class CircleCommand extends CommandHandler {

	public CircleCommand(PrinCanvas canvas) {
		super(canvas);
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Circle Event");
		getCanvas().setActiveTool(new CircleTool());
	}

}
