package csci240.prinCad.command;

import csci240.prinCad.control.LineTool;
import csci240.prinCad.ui.CommandHandler;
import csci240.prinCad.ui.PrinCanvas;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class LineCommand extends CommandHandler {

	public LineCommand(PrinCanvas canvas) {
		super(canvas);
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Line Event");
		getCanvas().setActiveTool(new LineTool());
	}

}
