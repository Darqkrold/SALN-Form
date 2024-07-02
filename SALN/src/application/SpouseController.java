package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SpouseController {
	
	@FXML private Button NextButton;
	
	@FXML
	private void NextButtonClicked() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChildrenScene.fxml"));
            Parent FileSALNRoot = fxmlLoader.load();	
            
            Scene FileSALNScene = new Scene(FileSALNRoot,700,400);
            Stage primaryStage = (Stage) NextButton.getScene().getWindow();
            primaryStage.setScene(FileSALNScene);
            primaryStage.show();            
		} catch (IOException e) {
            e.printStackTrace();
        }
	}

}
