import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

public class Ui extends Application {
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
		introLabel.setFont(Font.font ("Verdana", FontPosture.ITALIC, 20));
		introLabel.setTextFill(Color.WHITE);
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
		introLayout.setStyle("-fx-background-color: #32cd32;");
		introLayout.getChildren().addAll(introLabel,nameInput,i);
		introLayout.setAlignment(Pos.CENTER);
		
		//Scene 2: Used a label to displays first question
		HBox q1Display = new HBox(20);
		Label q1Label = new Label("	Body type question 1:\n 	Please Enter height and weight(lbs).");
		q1Label.setFont(Font.font ("Verdana", FontPosture.ITALIC, 14));
		q1Label.setTextFill(Color.WHITE);
		q1Label.setAlignment(Pos.CENTER);
		q1Display.getChildren().addAll(q1Label);
		
		//used labels to display height and weight
		GridPane displayHW = new GridPane();
		 displayHW.setAlignment(Pos.CENTER);
		 displayHW.setHgap(10);
		 displayHW.setVgap(10);
		 displayHW.setPadding(new Insets(25,25,25,25));
		Label footLabel = new Label("Feet: ");
		footLabel.setAlignment(Pos.CENTER_LEFT);
		Label inchesLabel = new Label("Inches: ");
		inchesLabel.setAlignment(Pos.CENTER_LEFT);
		Label weightLabel = new Label("Weight: ");
		weightLabel.setAlignment(Pos.CENTER_LEFT);
		
		//Creates texteFields for inputs of H and W
		TextField weightInput = new TextField();
		 weightInput.setPrefWidth(80);
		 weightInput.setMaxWidth(80);
		weightInput.setAlignment(Pos.TOP_CENTER);
		TextField heightInput = new TextField();
		 heightInput.setPrefWidth(80);
		 heightInput.setMaxWidth(80);
		heightInput.setAlignment(Pos.TOP_CENTER);
		TextField h2Input = new TextField();
		 h2Input.setPrefWidth(80);
		 h2Input.setMaxWidth(80);
		 h2Input.setAlignment(Pos.TOP_CENTER);
		 
		 displayHW.add(footLabel,0,1);
		 displayHW.add(heightInput,1,1);
		 displayHW.add(inchesLabel,2,1);
		 displayHW.add(h2Input,3,1);
		 displayHW.add(weightLabel,0,3);
		 displayHW.add(weightInput,1,3);

		
		//Back and next buttons
		Button nextButton = new Button("-->");
		nextButton.setAlignment(Pos.BASELINE_RIGHT);
		nextButton.setOnAction(e ->{
		if(weightInput.getText().isEmpty() && heightInput.getText().isEmpty() && h2Input.getText().isEmpty()) {
				AlertBox.display("Missing input", "Please fill in all fields");
			}else if ((weightInput.getText().isEmpty() || heightInput.getText().isEmpty() || h2Input.getText().isEmpty())){
			AlertBox.display("Missing input", "Please fill in all fields");
			}else {
			window.setScene(scene3);
			}
		});
		Button backButton = new Button("<--");
		backButton.setAlignment(Pos.BASELINE_LEFT);
		backButton.setOnAction(e -> window.setScene(scene1));;
		
		
		//Setting the next and back buttons on the bottom
		AnchorPane q1Btn = new AnchorPane();
        HBox hbox = new HBox(5, backButton, nextButton);
        q1Btn.getChildren().addAll(hbox);
        AnchorPane.setRightAnchor(hbox, 10d);
        AnchorPane.setBottomAnchor(hbox, 10d);
		
		//Create the pane for first question
        BorderPane q1Layout = new BorderPane();
        q1Layout.setStyle("-fx-background-color: #32cd32;");
		q1Layout.setTop(q1Display);
		q1Layout.setCenter(displayHW);
		q1Layout.setBottom(q1Btn);
		q1Layout.setPadding(new Insets(10,10,10,10));
		
		
		//Scene 3: Second question of BType quiz
		HBox q2Display = new HBox(20);
		Label q2Label = new Label("	Body type question 2:\n How many times per week do you exercise? ");
		q2Label.setFont(Font.font ("Verdana", FontPosture.ITALIC, 14));
		q2Label.setTextFill(Color.WHITE);
		q2Display.setAlignment(Pos.TOP_CENTER);
		q2Display.getChildren().addAll(q2Label);
		
		//Used gridPane to try and create 4 options to choose from as buttons
		//When user clicks on the option (s)he will be directed to the next scene
		//This scene will only have the back button at the bottom
		
		GridPane q2Choices = new GridPane();
		 q2Choices.setAlignment(Pos.CENTER);
		 q2Choices.setHgap(10);
		 q2Choices.setVgap(10);
		 q2Choices.setPadding(new Insets(25,25,25,25));
	    q2Choices.setStyle("-fx-background-color: #32cd32;");
	    Button q2ch1 = new Button("1-2");
	    q2ch1.setAlignment(Pos.CENTER);
	    q2ch1.setOnAction(e -> window.setScene(scene4));;
	    Button q2ch2 = new Button("3-4");
	    q2ch2.setAlignment(Pos.CENTER);
	    q2ch2.setOnAction(e -> window.setScene(scene4));;
	    Button q2ch3 = new Button("5-6");
	    q2ch3.setAlignment(Pos.CENTER);
	    q2ch3.setOnAction(e -> window.setScene(scene4));;
	    Button q2ch4 = new Button("Everyday");
	    q2ch4.setAlignment(Pos.CENTER);
	    q2ch4.setOnAction(e -> window.setScene(scene4));;
	    q2Choices.add(q2ch1, 0, 1);
	    q2Choices.add(q2ch2, 1, 1);
	    q2Choices.add(q2ch3, 0, 2);
	    q2Choices.add(q2ch4, 1, 2);
	    
	    //Setting the back button position to bottom of the scene
	    AnchorPane q2Btn = new AnchorPane();
	    Button backBtn = new Button("<--");
		backBtn.setAlignment(Pos.BASELINE_LEFT);
		backBtn.setOnAction(e -> window.setScene(scene2));
        HBox q2Box = new HBox(10, backBtn);
        q2Btn.getChildren().addAll(q2Box);
        AnchorPane.setRightAnchor(q2Box, 10d);
        AnchorPane.setBottomAnchor(q2Box, 10d);
		
		//Creates the pane for scene 3
		BorderPane q2Layout = new BorderPane();
		q2Layout.setStyle("-fx-background-color:#32cd32;");
		q2Layout.setTop(q2Display);
		q2Layout.setCenter(q2Choices);
		q2Layout.setBottom(q2Btn);
		q2Layout.setPadding(new Insets(10,10,10,10));
		
		//Scene 4: Displaying Question 3 of BType quiz
		HBox q3Display = new HBox(20);
		Label q3Label = new Label("	Body type question 3:\n What is your body type goal? ");
		q3Label.setFont(Font.font ("Verdana", FontPosture.ITALIC, 14));
		q3Label.setTextFill(Color.WHITE);
		q3Display.setAlignment(Pos.TOP_CENTER);
		/*Button q3NX = new Button("-->");
		q3NX.setOnAction(e -> window.setScene(scene5));
		*/
		Button q3BK = new Button("<--");
		q3BK.setOnAction(e -> window.setScene(scene3));
		q3Display.getChildren().addAll(q3Label);
		
		//Setting the next and back buttons to the bottom of the scene
		AnchorPane q3Btn = new AnchorPane();
        HBox q3Box = new HBox(5, q3BK);
        q3Btn.getChildren().addAll(q3Box);
        AnchorPane.setRightAnchor(q3Box, 10d);
        AnchorPane.setBottomAnchor(q3Box, 10d);
        
        //3 buttons to be used as the choices for the user on question 3
        GridPane q3Choices = new GridPane();
        q3Choices.setAlignment(Pos.CENTER);
        q3Choices.setHgap(10);
        q3Choices.setVgap(10);
        q3Choices.setPadding(new Insets(25,25,25,25));
	    Button q3ch1 = new Button(" Shred ");
	    q3ch1.setAlignment(Pos.BASELINE_CENTER);
	    Button q3ch2 = new Button(" Bulk ");
	    q3ch2.setAlignment(Pos.BASELINE_CENTER);
	    Button q3ch3 = new Button(" Maintain ");
	    q3ch3.setAlignment(Pos.BASELINE_CENTER);
	    q3Choices.add(q3ch1, 0, 1);
	    q3Choices.add(q3ch2, 1, 1);
	    q3Choices.add(q3ch3, 2, 1);
	    

	    //putting everything on Scene 4
		BorderPane q3Layout = new BorderPane();
		q3Layout.setStyle("-fx-background-color: #32cd32;");
		q3Layout.setTop(q3Display);
		q3Layout.setCenter(q3Choices);
		q3Layout.setBottom(q3Btn);
		q3Layout.setPadding(new Insets(10,10,10,10));
		
		

		scene1 = new Scene(introLayout,350,350);
		scene2 = new Scene(q1Layout, 350, 350);
		scene3 = new Scene(q2Layout, 350, 350);
		scene4 = new Scene(q3Layout, 350, 350);
		//scene5 = new Scene(q4Layout, 350, 350);

		window.setTitle("Healthy Helper");
		window.setScene(scene1);
		window.show();
	}
}
