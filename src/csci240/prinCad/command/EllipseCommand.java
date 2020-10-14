package csci240.prinCad.command;

import csci240.prinCad.control.EllipseTool;
import csci240.prinCad.ui.CommandHandler;
import csci240.prinCad.ui.PrinCanvas;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class EllipseCommand extends CommandHandler {

	public EllipseCommand(PrinCanvas canvas) {
		super(canvas);
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Ellipse Event");
		getCanvas().setActiveTool(new EllipseTool());
	}

}
