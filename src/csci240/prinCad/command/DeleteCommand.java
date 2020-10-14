package csci240.prinCad.command;

import csci240.prinCad.ui.CommandHandler;
import csci240.prinCad.ui.PrinCanvas;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class DeleteCommand extends CommandHandler {

	public DeleteCommand(PrinCanvas canvas) {
		super(canvas);
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Delete Pressed");
		this.getCanvas().deleteSelected();
	}

}
