import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Demo extends Application {
	Stage window;
	Scene scene1, scene2, scene3, scene4, scene5, scene6;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		
		//Scene 1: Introduction, asks user for name 
		VBox introLayout = new VBox(20);
		Label introLabel = new Label("	Healthy Helper!\n" + "Please enter your name");
		TextField nameInput = new TextField();
		Button i = new Button("Continue!");
		i.setOnAction(e->{
			if(nameInput.getText().trim().isEmpty()) {
				AlertBox.display("Missing input", "please go back");
		}else {
			window.setScene(scene2);
			}
		}
			);
		introLayout.setPadding(new Insets(10,10,10,10));
		introLayout.getChildren().addAll(introLabel,nameInput,i);
		introLayout.setAlignment(Pos.CENTER);
		
		//Scene 2: Used a label to displays first question
		HBox q1Display = new HBox(20);
		Label q1Label = new Label("				Body type question 1:\n 		Enter height(inches) and weight(lbs).");
		q1Label.setAlignment(Pos.CENTER);
		q1Display.getChildren().addAll(q1Label);
		
		//used labels to display height and weight
		VBox displayHW = new VBox(20);
		Label heightLabel = new Label("Height: ");
		heightLabel.setAlignment(Pos.CENTER_LEFT);
		Label weightLabel = new Label("Weight: ");
		weightLabel.setAlignment(Pos.CENTER_LEFT);
		
		//Creates texteFields for inputs of H and W
		TextField weightInput = new TextField();
		weightInput.setAlignment(Pos.TOP_CENTER);
		TextField heightInput = new TextField();
		heightInput.setAlignment(Pos.TOP_CENTER);
		
		//Back and next buttons
		Button nextButton = new Button("-->");
		nextButton.setAlignment(Pos.BASELINE_RIGHT);
		nextButton.setOnAction(e ->{
		if(weightInput.getText().isEmpty() && heightInput.getText().isEmpty()) {
				AlertBox.display("Missing input", "Please fill in all fields");
			}else if ((weightInput.getText().isEmpty() || heightInput.getText().isEmpty())){
			AlertBox.display("Missing input", "Please fill in all fields");
			}else {
			window.setScene(scene3);
			}
		});
		Button backButton = new Button("<--");
		backButton.setAlignment(Pos.BASELINE_LEFT);
		backButton.setOnAction(e -> window.setScene(scene1));;
		
		//put it all together in leftMenu
		displayHW.getChildren().addAll(heightLabel, heightInput, weightLabel,weightInput);
		displayHW.setAlignment(Pos.BASELINE_LEFT);
		
		//Setting the next and back buttons on the bottom
		AnchorPane q1Btn = new AnchorPane();
        HBox hbox = new HBox(10, backButton, nextButton);
        q1Btn.getChildren().addAll(hbox);
        AnchorPane.setRightAnchor(hbox, 10d);
        AnchorPane.setBottomAnchor(hbox, 10d);
		
		//Create the pane for first question
		BorderPane q1Layout = new BorderPane();
		q1Layout.setTop(q1Display);
		q1Layout.setCenter(displayHW);
		q1Layout.setBottom(q1Btn);
		q1Layout.setPadding(new Insets(10,10,10,10));
		
		
		//Scene 3: Second question of Btype quiz
		HBox q2Display = new HBox(20);
		Label q2Label = new Label("	Body type question 2:\n How often do you exercise? ");
		q2Display.setAlignment(Pos.TOP_CENTER);
		q2Display.getChildren().addAll(q2Label);
		
		//Used flowPane to try and create 4 options to choose from as buttons
		//When user clicks on the option (s)he will be directed to the next scene
		//This scene will only have the back button at the bottom
		FlowPane q2Choices = new FlowPane();
	    q2Choices.setPadding(new Insets(6, 0, 6, 0));
	    q2Choices.setVgap(4);
	    q2Choices.setHgap(4);
	    q2Choices.setPrefWrapLength(170); // preferred width allows for two columns
	    q2Choices.setStyle("-fx-background-color: DAE6F3;");
	    
	    
	    AnchorPane q2Btn = new AnchorPane();
	    Button backBtn = new Button("<--");
		backBtn.setAlignment(Pos.BASELINE_LEFT);
		backBtn.setOnAction(e -> window.setScene(scene1));;
        HBox q2Box = new HBox(10, backBtn);
        q1Btn.getChildren().addAll(q2Box);
        AnchorPane.setRightAnchor(q2Box, 10d);
        AnchorPane.setBottomAnchor(q2Box, 10d);
		
		//Creates the pane for scene 3
		BorderPane q2Layout = new BorderPane();
		q2Layout.setTop(q2Display);
		q2Layout.setCenter(q2Choices);
		q2Layout.setBottom(q2Btn);
		q2Layout.setPadding(new Insets(10,10,10,10));
		
		
	/*	HBox q3Display = new HBox(20);
		Label q3Label = new Label("	Body type question 2:\n What is your body type goal? ");
		q2Display.setAlignment(Pos.TOP_CENTER);
		Button c = new Button("<--");
		c.setOnAction(e -> window.setScene(scene3));
		q2Display.getChildren().addAll(question2Label);
		*/
		

		scene1 = new Scene(introLayout,350,350);
		scene2 = new Scene(q1Layout, 350, 350);
		scene3 = new Scene(q2Layout, 350, 350);

		window.setTitle("Healthy Helper");
		window.setScene(scene1);
		window.show();
	}
}
