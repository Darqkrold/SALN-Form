package application;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class FileSALNController {
	
	@FXML private Button NextButton;
	@FXML private ChoiceBox<String> FilingTypeChoiceBox;
		
	public void initialize() {
        setChoiceBoxChoices();
    }

    private void setChoiceBoxChoices() {
        List<String> choices = Arrays.asList("Joint Filing", "Separate Filing", "Not Applicable");
        FilingTypeChoiceBox.getItems().addAll(choices);
    }
	
	@FXML
	private void NextButtonClicked(){
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SpouseScene.fxml"));
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
