package csci240.prinCad.command;

import csci240.prinCad.control.PlusMarkerTool;
import csci240.prinCad.ui.CommandHandler;
import csci240.prinCad.ui.PrinCanvas;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class PlusMarkerCommand extends CommandHandler {

	public PlusMarkerCommand(PrinCanvas canvas) {
		super(canvas);
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Plus Marker Event");
		getCanvas().setActiveTool(new PlusMarkerTool());
	}

}
