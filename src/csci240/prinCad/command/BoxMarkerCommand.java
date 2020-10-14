package csci240.prinCad.command;

import csci240.prinCad.control.BoxMarkerTool;
import csci240.prinCad.ui.CommandHandler;
import csci240.prinCad.ui.PrinCanvas;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class BoxMarkerCommand extends CommandHandler{

	public BoxMarkerCommand(PrinCanvas canvas) {
		super(canvas);
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Box Marker Event");
		getCanvas().setActiveTool(new BoxMarkerTool());
	}

}
