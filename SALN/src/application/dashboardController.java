package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class dashboardController {
	
	@FXML private Label EmployeeIDLabel;
	@FXML private Button SignOutButton;
	@FXML private ImageView fileiconimg;

	public void setUserData(String EmployeeID) {
		EmployeeIDLabel.setText(EmployeeID);
	}
	
	@FXML
	private void FileSALNClicked() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FileSALNScene.fxml"));
            Parent FileSALNRoot = fxmlLoader.load();	
            
            Scene FileSALNScene = new Scene(FileSALNRoot,700,400);
            Stage primaryStage = (Stage) fileiconimg.getScene().getWindow();
            primaryStage.setScene(FileSALNScene);
            primaryStage.show();            
		} catch (IOException e) {
            e.printStackTrace();
        }
	}

	@FXML
	private void SignOutButtonClicked() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sign Out Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to sign out?");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");

        Image logoIcon = new Image(getClass().getResourceAsStream("/images/SALNLogo.png"));
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(logoIcon);
        
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        alert.showAndWait().ifPresent(response -> {
            if (response == buttonTypeYes) {
                System.exit(0);
            }
        });
	}
}


