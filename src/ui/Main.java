package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Angelaccesorios;

public class Main extends Application{
	private Angelaccesorios angelaccesorios;
	private AngelaccesoriosGUI angelaccesoriosgui;
	
	public Main() {
		
		Angelaccesorios ang=new Angelaccesorios(Angelaccesorios.PROGRAM);
		angelaccesorios=new Angelaccesorios(Angelaccesorios.PROGRAM);
		
		boolean loadData;
		try {
			angelaccesorios = angelaccesorios.loadDataAngelaccesorios(ang);
			if(angelaccesorios.getFirstUser()!=null) {
				loadData=true;
			}else {
				loadData=false;
			}
		
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			loadData = false;
		} 
		
		if(!loadData) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Angelaccesorios");
			alert.setHeaderText(null);
			alert.setContentText("Error al cargar datos de archivo");
			alert.showAndWait();
		}
		
		angelaccesoriosgui= new AngelaccesoriosGUI(angelaccesorios);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		angelaccesoriosgui.setStage(primaryStage);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-pane.fxml"));

		fxmlLoader.setController(angelaccesoriosgui);
		Parent root= fxmlLoader.load();

		Scene scene= new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Angelaccesorios");
		primaryStage.show();
		
		if(angelaccesorios.getFirstUser()==null) {
			angelaccesoriosgui.loadRegisterAdmin();
		}else {
			angelaccesoriosgui.loadLogIn(null);
		}
		
	}

}
