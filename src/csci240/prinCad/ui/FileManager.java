package csci240.prinCad.ui;

import csci240.prinCad.command.BoxMarkerCommand;
import csci240.prinCad.command.CircleCommand;
import csci240.prinCad.command.CrisscrossMarkerCommand;
import csci240.prinCad.command.DeleteCommand;
import csci240.prinCad.command.EllipseCommand;
import csci240.prinCad.command.LineCommand;
import csci240.prinCad.command.NewFileCommand;
import csci240.prinCad.command.OpenFileCommand;
import csci240.prinCad.command.PlusMarkerCommand;
import csci240.prinCad.command.PolylineCommand;
import csci240.prinCad.command.PropertiesCommand;
import csci240.prinCad.command.RectangleCommand;
import csci240.prinCad.command.RedoCommand;
import csci240.prinCad.command.SaveAsFileCommand;
import csci240.prinCad.command.SaveFileCommand;
import csci240.prinCad.command.ToggleSelectionCommand;
import csci240.prinCad.command.UndoCommand;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

class FileManager {
	
	private NewFileCommand _newFileCommand;
	private OpenFileCommand _openFileCommand;
	private SaveFileCommand _saveFileCommand;
	private SaveAsFileCommand _saveAsFileCommand;
	private UndoCommand _undoCommand;
	private RedoCommand _redoCommand;
	private DeleteCommand _deleteCommand;
	private ToggleSelectionCommand _toggleSelectionCommand;
	private PropertiesCommand _propertiesCommand;
	private PlusMarkerCommand _plusMarkerCommand;
	private BoxMarkerCommand _boxMarkerCommand;
	private CrisscrossMarkerCommand _crisscrossMarkerCommand;
	private LineCommand _lineCommand;
	private RectangleCommand _rectangleCommand;
	private CircleCommand _circleCommand;
	private EllipseCommand _ellipseCommand;
	private PolylineCommand _polylineCommand;

	
	FileManager(PrinCanvas canvas) {
		_newFileCommand = new NewFileCommand(canvas);
		_openFileCommand = new OpenFileCommand(canvas);
		_saveFileCommand = new SaveFileCommand(canvas);
		_saveAsFileCommand = new SaveAsFileCommand(canvas);
		_undoCommand = new UndoCommand(canvas);
		_redoCommand = new RedoCommand(canvas);
		_deleteCommand = new DeleteCommand(canvas);
		_toggleSelectionCommand = new ToggleSelectionCommand(canvas);
		_propertiesCommand = new PropertiesCommand(canvas);
		_lineCommand = new LineCommand(canvas);
		_rectangleCommand = new RectangleCommand(canvas);
		_circleCommand = new CircleCommand(canvas);
		_ellipseCommand = new EllipseCommand(canvas);
		_polylineCommand = new PolylineCommand(canvas);
		_plusMarkerCommand = new PlusMarkerCommand(canvas);
		_boxMarkerCommand = new BoxMarkerCommand(canvas);
		_crisscrossMarkerCommand = new CrisscrossMarkerCommand(canvas);
	}

	
	Menu buildFileMenu() {
		//Create File menu items
		MenuItem miNew = new MenuItem("New");
		miNew.setOnAction(e -> _newFileCommand.action(e));
		MenuItem miOpen = new MenuItem("Open");
		miOpen.setOnAction(e -> _openFileCommand.action(e));
		MenuItem miSave = new MenuItem("Save");
		miSave.setOnAction(e -> _saveFileCommand.action(e));
		MenuItem miSaveAs = new MenuItem("Save As");
		miSaveAs.setOnAction(e -> _saveAsFileCommand.action(e));
		
		//create File Menu
		Menu fileMenu = new Menu("File");
		ObservableList<MenuItem> fileMenuItems = fileMenu.getItems();

		//add menu items to file menu
		fileMenuItems.add(miNew);
		fileMenuItems.add(miOpen);
		fileMenuItems.add(miSave);
		fileMenuItems.add(miSaveAs);
		
		return fileMenu;
	}
	
	Menu buildEditMenu() {
		//Create Edit menu items
		MenuItem miUndo = new MenuItem("Undo");
		miUndo.setOnAction(e -> _undoCommand.action(e));
		MenuItem miRedo = new MenuItem("Redo");
		miRedo.setOnAction(e -> _redoCommand.action(e));
		MenuItem miDelete = new MenuItem("Delete");
		miDelete.setOnAction(e -> _deleteCommand.action(e));
		MenuItem miToggleSelection = new MenuItem("Toggle Selection");
		miToggleSelection.setOnAction(e -> _toggleSelectionCommand.action(e));
		MenuItem miProperties = new MenuItem("Properties");
		miProperties.setOnAction(e -> _propertiesCommand.action(e));
		
		//create Edit Menu
		Menu editMenu = new Menu("Edit");
		ObservableList<MenuItem> editMenuItems = editMenu.getItems();
		
		//add menu items to edit menu
		editMenuItems.add(miUndo);
		editMenuItems.add(miRedo);
		editMenuItems.add(miDelete);
		editMenuItems.add(miToggleSelection);
		editMenuItems.add(miProperties);
		
		return editMenu;
	}
	
	Menu buildCADToolsMenu() {
		//create menu items 
		MenuItem miLine = new MenuItem("Line");
        miLine.setOnAction(e -> _lineCommand.action(e));
        MenuItem miRectangle = new MenuItem("Rectangle");
        miRectangle.setOnAction(e -> _rectangleCommand.action(e));
        MenuItem miCircle = new MenuItem("Circle");
        miCircle.setOnAction(e -> _circleCommand.action(e));
        MenuItem miEllipse = new MenuItem("Ellipse");
        miEllipse.setOnAction(e -> _ellipseCommand.action(e));
        MenuItem miPolyline = new MenuItem("Polyline");
        miPolyline.setOnAction(e -> _polylineCommand.action(e));
        MenuItem miPlus = new MenuItem("Plus");
        miPlus.setOnAction(e -> _plusMarkerCommand.action(e));
        MenuItem miBox = new MenuItem("Box");
        miBox.setOnAction(e -> _boxMarkerCommand.action(e));
        MenuItem miCross = new MenuItem("CrissCross");
        miCross.setOnAction(e -> _crisscrossMarkerCommand.action(e));
        
        //create marker menu
        Menu markerMenu = new Menu("Markers");
        ObservableList<MenuItem> markerMenuItems = markerMenu.getItems();
        
        // add individual marker menu items to marker items menu
        markerMenuItems.add(miPlus);
        markerMenuItems.add(miBox);
        markerMenuItems.add(miCross);
		
        // create a menu 
        Menu cadToolMenu = new Menu("CAD Tools"); 
        ObservableList<MenuItem> cadToolMenuItems = cadToolMenu.getItems();
  
        // add menu items to menu
        cadToolMenuItems.add(markerMenu); 
        cadToolMenuItems.add(miLine);
        cadToolMenuItems.add(miRectangle);
        cadToolMenuItems.add(miCircle);
        cadToolMenuItems.add(miEllipse);
        cadToolMenuItems.add(miPolyline);
	
        return cadToolMenu;

	}
	
	// Add buttons to right bar
	public void addButtonsToRightBar(ObservableList<Node> nodes) {
		
		// Create File buttons
		Button nfb = new Button();
		nfb.setMinWidth(80);
		nfb.setText("New File");
		nfb.setOnAction(e -> _newFileCommand.action(e));
		
		Button ofb = new Button();
		ofb.setMinWidth(80);
		ofb.setText("Open File");
		ofb.setOnAction(e -> _openFileCommand.action(e));
	
		Button sfb = new Button();
		sfb.setMinWidth(80);
		sfb.setText("Save File");
		sfb.setOnAction(e -> _saveFileCommand.action(e));
		
		Button safb = new Button();
		safb.setMinWidth(80);
		safb.setText("Save As File");
		safb.setOnAction(e -> _saveAsFileCommand.action(e));
		
		
		//Create Edit Buttons
		Button ueb = new Button();
		ueb.setMinWidth(80);
		ueb.setText("Undo");
		ueb.setOnAction(e -> _undoCommand.action(e));
		
		Button reb = new Button();
		reb.setMinWidth(80);
		reb.setText("Redo");
		reb.setOnAction(e -> _redoCommand.action(e));
		
		Button deb = new Button();
		deb.setMinWidth(80);
		deb.setText("Delete");
		deb.setOnAction(e -> _deleteCommand.action(e));
		
		Button tseb = new Button();
		tseb.setMinWidth(80);
		tseb.setText("^Selection");
		tseb.setOnAction(e -> _toggleSelectionCommand.action(e));
		
		Button peb = new Button();
		peb.setMinWidth(80);
		peb.setText("Properties");
		peb.setOnAction(e -> _propertiesCommand.action(e));
		
		
		//Add Buttons
		nodes.add(nfb);
		nodes.add(ofb);
		nodes.add(sfb);
		nodes.add(safb);
		nodes.add(ueb);
		nodes.add(reb);
		nodes.add(deb);
		nodes.add(tseb);
		nodes.add(peb);
	}
	
	//Add buttons to left bar
	public void addButtonsToLeftBar(ObservableList<Node> nodes) {
		
		//Create CAD Tools Buttons
		Button pmb = new Button();
		pmb.setMinWidth(80);
		pmb.setText("Plus Marker");
		pmb.setOnAction(e -> _plusMarkerCommand.action(e));
		
		Button bmb = new Button();
		bmb.setMinWidth(80);
		bmb.setText("Box Marker");
		bmb.setOnAction(e -> _boxMarkerCommand.action(e));
		
		Button cmb = new Button();
		cmb.setMinWidth(80);
		cmb.setText("Crisscross Marker");
		cmb.setOnAction(e -> _crisscrossMarkerCommand.action(e));
		
		Button ltb = new Button();
		ltb.setMinWidth(80);
		ltb.setText("Line Tool");
		ltb.setOnAction(e -> _lineCommand.action(e));
		
		Button rtb = new Button();
		rtb.setMinWidth(80);
		rtb.setText("Rectangle Tool");
		rtb.setOnAction(e -> _rectangleCommand.action(e));
		
		Button ctb = new Button();
		ctb.setMinWidth(80);
		ctb.setText("Circle Tool");
		ctb.setOnAction(e -> _circleCommand.action(e));
		
		Button etb = new Button();
		etb.setMinWidth(80);
		etb.setText("Ellipse Tool");
		etb.setOnAction(e -> _ellipseCommand.action(e));
		
		Button ptb = new Button();
		ptb.setMinWidth(80);
		ptb.setText("Polyline Tool");
		ptb.setOnAction(e -> _polylineCommand.action(e));
		
		
		//Add Buttons
		nodes.add(pmb);
		nodes.add(bmb);
		nodes.add(cmb);
		nodes.add(ltb);
		nodes.add(rtb);
		nodes.add(ctb);
		nodes.add(etb);
		nodes.add(ptb);
		
	}

}
