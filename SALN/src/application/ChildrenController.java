package application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ChildrenController {
	
	private int paneCount = 0;
	private GridPane gridPane;
	
	@FXML private ScrollPane scrollPane;
	@FXML private AnchorPane scrollPaneAnchor;
	@FXML private ImageView addiconimg;
	@FXML private Button NextButton;
	
	@FXML
    public void initialize() {		
		Group contentGroup = new Group(scrollPaneAnchor);

		scrollPaneAnchor.setPrefWidth(680);
        scrollPaneAnchor.setPrefHeight(500);
        scrollPane.setVmax(1200); 
        scrollPane.setPrefViewportHeight(100); 
        scrollPane.setVvalue(0);  
        scrollPane.setContent(contentGroup);
		
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
    }
	
	@FXML
	private void addNewPane() {
		gridPane = new GridPane();
	    gridPane.setHgap(10);
	       
	    TextField UCCounter = new TextField();
	    TextField UCfullnametxtField = new TextField();
	    TextField UCAgetxtField = new TextField();
	    DatePicker UCBirthdateDatePicker = new DatePicker();
	    
	    UCCounter.setPrefSize(55, 25);
	    
	    ImageView deleteButton = createDeleteButton(gridPane);
	    gridPane.add(deleteButton, 0, 0);
	    
	    gridPane.addRow(0, UCCounter ,UCfullnametxtField, UCBirthdateDatePicker, UCAgetxtField );
	    paneCount++;
  		UCCounter.setText("" + paneCount);

	  	UCCounter.textProperty().addListener((observable, oldValue, newValue) -> {	
	    });
	    
	    UCAgetxtField.textProperty().bindBidirectional(UCBirthdateDatePicker.valueProperty(), new StringConverter<LocalDate>() {
	        @Override
	        public String toString(LocalDate date) {
	            if (date != null) {
	                int age = calculateAge(date);
	                return String.valueOf(age);
	            }
	            return "";
	        }

	        @Override
	        public LocalDate fromString(String string) {
	            return null;
	        }
	    });
	    
	    double paneY = getLastPaneYPosition() + 20.0;
	    gridPane.setLayoutX(37.0);
	    gridPane.setLayoutY(paneY);	    
	  
	    scrollPaneAnchor.getChildren().add(gridPane);
	    
	    double scrollThreshold = scrollPane.getViewportBounds().getHeight() - 100;
	    if (paneY >= scrollThreshold) {
	        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
	        scrollPane.layout();
	        scrollPane.setVvalue(1.0);
	    } else {
	        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	    }
	}
	
	private ImageView createDeleteButton(GridPane gridPane) {
	    Image deleteImage = new Image(getClass().getResourceAsStream("/images/trashicon.png"));

	    ImageView deleteButton = new ImageView(deleteImage);
	    deleteButton.setFitWidth(35);
	    deleteButton.setFitHeight(35);

	    deleteButton.setOnMouseClicked(event -> deleteGridPane(gridPane));

	    return deleteButton;
	}
	
	private void deleteGridPane(GridPane gridPane) {
		scrollPaneAnchor.getChildren().remove(gridPane);
	    paneCount--;

	    int index = 1;
	    for (Node node : scrollPaneAnchor.getChildren()) {
	        if (node instanceof GridPane) {
	            GridPane remainingGridPane = (GridPane) node;
	            
	            TextField UCCounter = (TextField) remainingGridPane.getChildren().get(1);
	            UCCounter.setText("" + index);

	            double paneY = (index - 1) * 50.0;
	            remainingGridPane.setLayoutY(paneY);

	            index++;
	        }
	    }
	    
	    scrollPane.setVvalue(0.0);

	    
	}
	
	private double getLastPaneYPosition() {
	    double lastPaneY = 0.0;
	    for (Node node : scrollPaneAnchor.getChildren()) {
	        if (node instanceof GridPane) {
	            double nodeY = node.getLayoutY() + node.getBoundsInParent().getHeight();
	            lastPaneY = Math.max(lastPaneY, nodeY);
	        }
	    }
	    return lastPaneY;
	}
	
	private int calculateAge(LocalDate birthDate) {
	    LocalDate currentDate = LocalDate.now();
	    return Period.between(birthDate, currentDate).getYears();
	}
	
	@FXML
	private void NextButtonClicked() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RealPropertyScene.fxml"));
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
