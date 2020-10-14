package csci240.prinCad.command;

import java.io.BufferedReader;
import java.io.PrintWriter;

import csci240.prinCad.control.CadTool;
import javafx.scene.Scene;

public interface CanvasCommandInterface {

	void setActiveTool(CadTool activeTool);
	void clearModel();
	public void deleteSelected();
	boolean isBlank();
	Scene getScene();
	void openFromFile(BufferedReader br);
	void draw();
	void saveToFile(PrintWriter out);
	void undo();
	void redo();

}
