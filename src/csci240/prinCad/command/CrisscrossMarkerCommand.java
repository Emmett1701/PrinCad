package csci240.prinCad.command;

import csci240.prinCad.control.CrisscrossMarkerTool;
import csci240.prinCad.ui.CommandHandler;
import csci240.prinCad.ui.PrinCanvas;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class CrisscrossMarkerCommand extends CommandHandler{

	public CrisscrossMarkerCommand(PrinCanvas canvas) {
		super(canvas);
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Handle CrossCross Marker Event");
		getCanvas().setActiveTool(new CrisscrossMarkerTool());
	}

}
