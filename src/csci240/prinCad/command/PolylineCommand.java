package csci240.prinCad.command;

import csci240.prinCad.control.PolylineTool;
import csci240.prinCad.ui.CommandHandler;
import csci240.prinCad.ui.PrinCanvas;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class PolylineCommand extends CommandHandler {

	public PolylineCommand(PrinCanvas canvas) {
		super(canvas);
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Polyline Event");
		getCanvas().setActiveTool(new PolylineTool());
	}

}
