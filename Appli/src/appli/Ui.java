package appli;

import java.io.BufferedWriter;
import javafx.animation.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Ui extends Application {
	Stage window;
	Scene scene1,scene2,scene3,scene4,scene5,scene6,scene7,scene8,scene9,scene10,scene11,scene12;
	Scene intro;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		window = primaryStage;

		// Scene 1: Introduction, asks user for name
		VBox introLayout = new VBox(20);
		Label introLabel = new Label("	Healthy Helper!\n" + "Please enter your name");
		introLabel.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 20));
		introLabel.setTextFill(Color.WHITE);
		TextField nameInput = new TextField();
		Button i = new Button("Continue!");
		i.setOnAction(e -> {
			if (nameInput.getText().trim().isEmpty()) {
				AlertBox.display("Missing input", "Please enter name");
			} else {
				recordInfo(nameInput.getText());
				window.setScene(scene9);
			}
		});
		introLayout.setPadding(new Insets(10, 10, 10, 10));
		introLayout.setStyle("-fx-background-color: #32cd32;");
		introLayout.getChildren().addAll(introLabel, nameInput, i);
		introLayout.setAlignment(Pos.CENTER);
		
//*********************************************************************************************************
		//Body type quiz is started
		//Had to update the quiz to include age and gender, so started with that first
		
		HBox ageGenderQuestion = new HBox(20);
		Label agLabel = new Label("	Body type question 1:\n  Please enter your age and gender.");
		agLabel.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 18));
		agLabel.setTextFill(Color.WHITE);
		agLabel.setAlignment(Pos.CENTER);
		ageGenderQuestion.getChildren().addAll(agLabel);
		
		//Making the input fields for age and Gender 
		GridPane ageGenderPane = new GridPane();
		ageGenderPane.setAlignment(Pos.CENTER);
		ageGenderPane.setHgap(10);
		ageGenderPane.setVgap(10);
		ageGenderPane.setPadding(new Insets(25,25,25,25));
		Label ageLabel = new Label("Age: ");
		ageLabel.setAlignment(Pos.CENTER_LEFT);
		TextField ageInput = new TextField();
		ageInput.setPrefWidth(50);
		ageInput.setMaxWidth(50);
		ageInput.setAlignment(Pos.TOP_CENTER);
		Label genderLabel = new Label("Gender: ");
		genderLabel.setAlignment(Pos.CENTER_LEFT);
		CheckBox genderCh1 = new CheckBox("Male");
		CheckBox genderCh2 = new CheckBox("Female");
		genderCh1.setFont(Font.font("Times New Roman", 15));
		genderCh2.setFont(Font.font("Times New Roman", 15));
		
		ageGenderPane.add(ageLabel,0,1);
		ageGenderPane.add(ageInput,1,1);
		ageGenderPane.add(genderLabel,0,2);
		ageGenderPane.add(genderCh1,1,2);
		ageGenderPane.add(genderCh2,1,3);
		
		
		Button nxtBtn = new Button("-->");
		nxtBtn.setAlignment(Pos.BASELINE_RIGHT);
		nxtBtn.setOnAction(e -> {
			if (ageInput.getText().isEmpty() && !(genderCh1.isSelected()) && !(genderCh2.isSelected())) {
				AlertBox.display("Missing input", "Please answer all questions");
			} else if ((ageInput.getText().isEmpty() && !(genderCh1.isSelected()) && genderCh2.isSelected())) {
				AlertBox.display("Missing input", "Please answer all questions");
			}else if ((ageInput.getText().isEmpty() && genderCh1.isSelected() && !(genderCh2.isSelected()))) {
				AlertBox.display("Missing input", "Please answer all questions");
			}else if ((!(ageInput.getText().isEmpty()) && genderCh1.isSelected() && genderCh2.isSelected())) {
				AlertBox.display("User Error", "Please only check one box");
			}else if ((!(ageInput.getText().isEmpty()) && !(genderCh1.isSelected()) && !(genderCh2.isSelected()))) {
				AlertBox.display("Missing input", "Please answer all questions");
			} else {
				recordInfo(ageInput.getText());
				recordInfo(genderCh1.getText());
				recordInfo(genderCh2.getText());
				window.setScene(scene2);
			}
		});
		Button bckBtn = new Button("<--");
		bckBtn.setAlignment(Pos.BASELINE_LEFT);
		bckBtn.setOnAction(e -> window.setScene(scene1));
		;
		
		
		AnchorPane agBtn = new AnchorPane();
		HBox agBox = new HBox(5, bckBtn, nxtBtn);
		agBtn.getChildren().addAll(agBox);
		AnchorPane.setRightAnchor(agBox, 10d);
		AnchorPane.setBottomAnchor(agBox, 10d);

		// Create the pane for first question
		BorderPane ageGenderLayout = new BorderPane();
		ageGenderLayout.setStyle("-fx-background-color: #32cd32;");
		ageGenderLayout.setTop(ageGenderQuestion);
		ageGenderLayout.setCenter(ageGenderPane);
		ageGenderLayout.setBottom(agBtn);
		ageGenderLayout.setPadding(new Insets(10, 10, 10, 10));
		
		
		
		
		// Scene 2: Used a label to displays first question
		HBox q1Display = new HBox(20);
		Label q1Label = new Label("	Body type question 2:\n 	Please Enter height and weight(lbs).");
		q1Label.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 18));
		q1Label.setTextFill(Color.WHITE);
		q1Label.setAlignment(Pos.CENTER);
		q1Display.getChildren().addAll(q1Label);

		// used labels to display height and weight
		GridPane displayHW = new GridPane();
		displayHW.setAlignment(Pos.CENTER);
		displayHW.setHgap(10);
		displayHW.setVgap(10);
		displayHW.setPadding(new Insets(25, 25, 25, 25));
		Label heightLabel = new Label("Height: ");
		heightLabel.setAlignment(Pos.CENTER_LEFT);
		Label footLabel = new Label(" Ft");
		footLabel.setAlignment(Pos.CENTER_LEFT);
		Label inchesLabel = new Label(" In");
		inchesLabel.setAlignment(Pos.CENTER_LEFT);
		Label weightLabel = new Label("Weight: ");
		weightLabel.setAlignment(Pos.CENTER_LEFT);
		Label poundLabel = new Label(" Lbs");
		poundLabel.setAlignment(Pos.CENTER_LEFT);

		// Creates texteFields for inputs of H and W
		TextField weightInput = new TextField();
		weightInput.setPrefWidth(50);
		weightInput.setMaxWidth(50);
		weightInput.setAlignment(Pos.TOP_CENTER);
		TextField heightInput = new TextField();
		heightInput.setPrefWidth(50);
		heightInput.setMaxWidth(50);
		heightInput.setAlignment(Pos.TOP_CENTER);
		TextField h2Input = new TextField();
		h2Input.setPrefWidth(50);
		h2Input.setMaxWidth(50);
		h2Input.setAlignment(Pos.TOP_CENTER);

		displayHW.add(heightLabel, 0, 1);
		displayHW.add(heightInput, 1, 1);
		displayHW.add(footLabel, 2, 1);
		displayHW.add(inchesLabel, 4, 1);
		displayHW.add(h2Input, 3, 1);
		displayHW.add(weightLabel, 0, 3);
		displayHW.add(weightInput, 1, 3);
		displayHW.add(poundLabel, 2, 3);

		// Back and next buttons
		Button nextButton = new Button("-->");
		nextButton.setAlignment(Pos.BASELINE_RIGHT);
		nextButton.setOnAction(e -> {
			if (weightInput.getText().isEmpty() && heightInput.getText().isEmpty() && h2Input.getText().isEmpty()) {
				AlertBox.display("Missing input", "Please fill in all fields");
			} else if ((weightInput.getText().isEmpty() || heightInput.getText().isEmpty()
					|| h2Input.getText().isEmpty())) {
				AlertBox.display("Missing input", "Please fill in all fields");
			} else {
				recordInfo(weightInput.getText());
				recordInfo(heightInput.getText());
				recordInfo(h2Input.getText());
				window.setScene(scene3);
			}
		});
		Button backButton = new Button("<--");
		backButton.setAlignment(Pos.BASELINE_LEFT);
		backButton.setOnAction(e -> window.setScene(scene1));
		;

		// Setting the next and back buttons on the bottom
		AnchorPane q1Btn = new AnchorPane();
		HBox hbox = new HBox(5, backButton, nextButton);
		q1Btn.getChildren().addAll(hbox);
		AnchorPane.setRightAnchor(hbox, 10d);
		AnchorPane.setBottomAnchor(hbox, 10d);

		// Create the pane for first question
		BorderPane q1Layout = new BorderPane();
		q1Layout.setStyle("-fx-background-color: #32cd32;");
		q1Layout.setTop(q1Display);
		q1Layout.setCenter(displayHW);
		q1Layout.setBottom(q1Btn);
		q1Layout.setPadding(new Insets(10, 10, 10, 10));

		// Scene 3: Second question of BType quiz
		HBox q2Display = new HBox(20);
		Label q2Label = new Label("	Body type question 3:\n How many times per week do you exercise? ");
		q2Label.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 18));
		q2Label.setTextFill(Color.WHITE);
		q2Display.setAlignment(Pos.TOP_CENTER);
		q2Display.getChildren().addAll(q2Label);

		// Used gridPane to try and create 4 options to choose from as buttons
		// When user clicks on the option (s)he will be directed to the next scene
		// This scene will only have the back button at the bottom

		GridPane q2Choices = new GridPane();
		q2Choices.setAlignment(Pos.CENTER);
		q2Choices.setHgap(10);
		q2Choices.setVgap(10);
		q2Choices.setPadding(new Insets(25, 25, 25, 25));
		q2Choices.setStyle("-fx-background-color: #32cd32;");
		Button q2ch1 = new Button("1-2");
		q2ch1.setAlignment(Pos.CENTER);
		q2ch1.setOnAction(e -> {
			window.setScene(scene4);
			recordInfo("1-2");
		});

		Button q2ch2 = new Button("3-4");
		q2ch2.setAlignment(Pos.CENTER);

		q2ch2.setOnAction(e -> {
			window.setScene(scene4);
			recordInfo("3-4");
		});
		Button q2ch3 = new Button("5-6");
		q2ch3.setAlignment(Pos.CENTER);
		q2ch3.setOnAction(e -> {
			window.setScene(scene4);
			recordInfo("5-6");
		});

		Button q2ch4 = new Button("Everyday");
		q2ch4.setAlignment(Pos.CENTER);
		q2ch4.setOnAction(e -> {
			window.setScene(scene4);
			recordInfo("e");
		});

		q2Choices.add(q2ch1, 0, 1);
		q2Choices.add(q2ch2, 1, 1);
		q2Choices.add(q2ch3, 0, 2);
		q2Choices.add(q2ch4, 1, 2);

		// Setting the back button position to bottom of the scene
		AnchorPane q2Btn = new AnchorPane();
		Button backBtn = new Button("<--");
		backBtn.setAlignment(Pos.BASELINE_LEFT);
		backBtn.setOnAction(e -> window.setScene(scene2));
		HBox q2Box = new HBox(10, backBtn);
		q2Btn.getChildren().addAll(q2Box);
		AnchorPane.setRightAnchor(q2Box, 10d);
		AnchorPane.setBottomAnchor(q2Box, 10d);

		// Creates the pane for scene 3
		BorderPane q2Layout = new BorderPane();
		q2Layout.setStyle("-fx-background-color:#32cd32;");
		q2Layout.setTop(q2Display);
		q2Layout.setCenter(q2Choices);
		q2Layout.setBottom(q2Btn);
		q2Layout.setPadding(new Insets(10, 10, 10, 10));

		// Scene 4: Displaying Question 3 of BType quiz
		HBox q3Display = new HBox(20);
		Label q3Label = new Label("	Body type question 4:\n What is your body type goal? ");
		q3Label.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 18));
		q3Label.setTextFill(Color.WHITE);
		q3Display.setAlignment(Pos.TOP_CENTER);
		/*
		 * Button q3NX = new Button("-->"); q3NX.setOnAction(e ->
		 * window.setScene(scene5));
		 */
		Button q3BK = new Button("<--");
		q3BK.setOnAction(e -> window.setScene(scene3));
		q3Display.getChildren().addAll(q3Label);

		// Setting the next and back buttons to the bottom of the scene
		AnchorPane q3Btn = new AnchorPane();
		HBox q3Box = new HBox(5, q3BK);
		q3Btn.getChildren().addAll(q3Box);
		AnchorPane.setRightAnchor(q3Box, 10d);
		AnchorPane.setBottomAnchor(q3Box, 10d);

		// 3 buttons to be used as the choices for the user on question 3
		GridPane q3Choices = new GridPane();
		q3Choices.setAlignment(Pos.CENTER);
		q3Choices.setHgap(10);
		q3Choices.setVgap(10);
		q3Choices.setPadding(new Insets(25, 25, 25, 25));
		Button q3ch1 = new Button(" Shred ");
		q3ch1.setAlignment(Pos.BASELINE_CENTER);
		q3ch1.setOnAction(e -> {
			window.setScene(scene6);
			recordInfo("s");
		});

		Button q3ch2 = new Button(" Bulk ");
		q3ch2.setAlignment(Pos.BASELINE_CENTER);
		q3ch2.setOnAction(e -> {
			window.setScene(scene7);
			recordInfo("b");
		});
		Button q3ch3 = new Button(" Maintain ");
		q3ch3.setAlignment(Pos.BASELINE_CENTER);
		q3ch3.setOnAction(e -> {
			window.setScene(scene6);
			recordInfo("m");
		});
		q3Choices.add(q3ch1, 0, 1);
		q3Choices.add(q3ch2, 1, 1);
		q3Choices.add(q3ch3, 2, 1);

		/*
		 * quizz is finished.
		 */
		// initialize foodMenu menuArr
		FoodMenu shredFoodMenu = new FoodMenu();
		FoodMenu bulkFoodMenu = new FoodMenu();
		FoodMenu mtFoodMenu = new FoodMenu();

		// putting everything on Scene 4
		BorderPane q3Layout = new BorderPane();
		q3Layout.setStyle("-fx-background-color: #32cd32;");
		q3Layout.setTop(q3Display);
		q3Layout.setCenter(q3Choices);
		q3Layout.setBottom(q3Btn);
		q3Layout.setPadding(new Insets(10, 10, 10, 10));

		// Making the actual startup screen for the app
		VBox rgStartLayout = new VBox(20);
		Label rgStartLabel = new Label("	Welcome to your profile!");
		rgStartLabel.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 20));
		rgStartLabel.setTextFill(Color.WHITE);
		rgStartLayout.getChildren().addAll(rgStartLabel);
		rgStartLayout.setAlignment(Pos.CENTER);

		// making options for the user to choose from in the actual app start
		GridPane startChoices = new GridPane();
		startChoices.setAlignment(Pos.CENTER);
		startChoices.setHgap(10);
		startChoices.setVgap(10);
		startChoices.setPadding(new Insets(25, 25, 25, 25));
		Button startCh1 = new Button(" Menu ");
		startCh1.setAlignment(Pos.BASELINE_CENTER);
		startCh1.setOnAction(e -> window.setScene(scene6));
		Button startCh2 = new Button(" Workouts ");
		startCh2.setAlignment(Pos.BASELINE_CENTER);
		startCh2.setOnAction(e -> window.setScene(scene6));
		Button startCh3 = new Button(" Schedule ");
		startCh3.setAlignment(Pos.BASELINE_CENTER);
		startCh3.setOnAction(e -> window.setScene(scene6));
		Button startCh4 = new Button(" Update info ");
		startCh4.setAlignment(Pos.BASELINE_CENTER);
		startCh4.setOnAction(e -> window.setScene(scene2));
		startChoices.add(startCh1, 0, 1);
		startChoices.add(startCh2, 0, 2);
		startChoices.add(startCh3, 0, 3);
		startChoices.add(startCh4, 0, 4);

		// Used borderpane to place all choices user can use
		BorderPane appLayout = new BorderPane();
		appLayout.setStyle("-fx-background-color: #32cd32;");
		appLayout.setTop(rgStartLayout);
		appLayout.setLeft(startChoices);
		appLayout.setPadding(new Insets(10, 10, 10, 10));

		// New scene for when menu is clicked on
		HBox shredMenuHeadings = new HBox(40);
		Label foodNameLabel = new Label("Food List: ");
		foodNameLabel.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 40));
		foodNameLabel.setAlignment(Pos.TOP_LEFT);
		shredMenuHeadings.getChildren().addAll(foodNameLabel);
		shredMenuHeadings.setAlignment(Pos.CENTER);

		// pane to setup the choices for meals
		GridPane shredMenuChoices = new GridPane();
		for (int k = 0; k < shredFoodMenu.getMenuArr().size(); k++) {
			this.makeFoodPane(shredFoodMenu.getMenuArr().get(k), shredMenuChoices, k);
		}
		Button addShredBtn = new Button("Add Selected Meals");
		addShredBtn.setOnAction(e -> window.setScene(scene10));

		
		AnchorPane shredBtn = new AnchorPane();
		HBox sdAddBox = new HBox(5, addShredBtn);
		shredBtn.getChildren().addAll(sdAddBox);
		AnchorPane.setRightAnchor(sdAddBox, 10d);
		AnchorPane.setBottomAnchor(sdAddBox, 10d);
		
		
		
		// Layout Pane for when Menu is clicked
		BorderPane shredMenuLayout = new BorderPane();
		shredMenuLayout.setStyle("-fx-background-color: #32cd32;");
		shredMenuLayout.setTop(shredMenuHeadings);
		shredMenuLayout.setCenter(shredMenuChoices);
		shredMenuLayout.setBottom(shredBtn);
		shredMenuLayout.setPadding(new Insets(10, 10, 10, 10));
		
		
		ScrollPane scrollPaneShred = new ScrollPane();
		scrollPaneShred.setContent(shredMenuLayout);
		scrollPaneShred.setFitToHeight(true);
	    scrollPaneShred.setFitToWidth(true);


		HBox bulkMenuHeadings = new HBox(40);
		Label blkLabel = new Label("Food List: ");
		blkLabel.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 40));
		blkLabel.setAlignment(Pos.TOP_LEFT);
		bulkMenuHeadings.getChildren().addAll(blkLabel);
		bulkMenuHeadings.setAlignment(Pos.CENTER);


		GridPane bulkMenuChoices = new GridPane();
		for (int k = 0; k < bulkFoodMenu.getMenuArr().size(); k++) {
			this.makeFoodPane(bulkFoodMenu.getMenuArr().get(k), bulkMenuChoices, k);
		}
		Button addBulkBtn = new Button("Add Selected Meals");
		addBulkBtn.setOnAction(e -> window.setScene(scene11));

		
		AnchorPane bulkBtn = new AnchorPane();
		HBox bkAddBox = new HBox(5, addBulkBtn);
		bulkBtn.getChildren().addAll(bkAddBox);
		AnchorPane.setRightAnchor(bkAddBox, 10d);
		AnchorPane.setBottomAnchor(bkAddBox, 10d);
		
		BorderPane bulkMenuLayout = new BorderPane();
		bulkMenuLayout.setStyle("-fx-background-color: #32cd32;");
		bulkMenuLayout.setTop(bulkMenuHeadings);
		bulkMenuLayout.setCenter(bulkMenuChoices);
		bulkMenuLayout.setBottom(bulkBtn);
		bulkMenuLayout.setPadding(new Insets(10, 10, 10, 10));
		
		ScrollPane scrollPaneBulk = new ScrollPane();
		scrollPaneBulk.setContent(bulkMenuLayout);
		scrollPaneBulk.setFitToHeight(true);
	    scrollPaneBulk.setFitToWidth(true);

		HBox mtMenuHeadings = new HBox(40); Label mtLabel = new Label("Food List: ");
		mtLabel.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 40));
		mtLabel.setAlignment(Pos.TOP_LEFT);
		mtMenuHeadings.getChildren().addAll(mtLabel);
		mtMenuHeadings.setAlignment(Pos.CENTER);
		
		GridPane mtMenuChoices = new GridPane();
		for (int k = 0; k < mtFoodMenu.getMenuArr().size(); k++) {
			this.makeFoodPane(mtFoodMenu.getMenuArr().get(k), mtMenuChoices, k);
		}
		Button addMtBtn = new Button("Add Selected Meals");
		addMtBtn.setOnAction(e -> window.setScene(scene12));
		
		AnchorPane mtBtn = new AnchorPane();
		HBox mtAddBox = new HBox(5, addMtBtn);
		mtBtn.getChildren().addAll(mtAddBox);
		AnchorPane.setRightAnchor(mtAddBox, 10d);
		AnchorPane.setBottomAnchor(mtAddBox, 10d);
		
		BorderPane mtMenuLayout = new BorderPane();
		mtMenuLayout.setStyle("-fx-background-color: #32cd32;");
		mtMenuLayout.setTop(mtMenuHeadings);
		mtMenuLayout.setCenter(mtMenuChoices);
		mtMenuLayout.setBottom(mtBtn);
		mtMenuLayout.setPadding(new Insets(10, 10, 10, 10));
		
		ScrollPane scrollPaneMt = new ScrollPane();
		scrollPaneMt.setContent(mtMenuLayout);
		scrollPaneMt.setFitToHeight(true);
	    scrollPaneMt.setFitToWidth(true);
	    
	    
	    
		   // Making the home screen for each category starting with shred
				VBox sdStartLayout = new VBox(20);
				Label sdStartLabel = new Label("	Welcome to your profile!");
				sdStartLabel.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 20));
				sdStartLabel.setTextFill(Color.WHITE);
				sdStartLayout.getChildren().addAll(sdStartLabel);
				sdStartLayout.setAlignment(Pos.CENTER);

				// making options for the user to choose from in the actual app start
				GridPane sdStartChoices = new GridPane();
				sdStartChoices.setAlignment(Pos.CENTER);
				sdStartChoices.setHgap(10);
				sdStartChoices.setVgap(10);
				sdStartChoices.setPadding(new Insets(25, 25, 25, 25));
				Button sdStartCh1 = new Button(" My Meals ");
				sdStartCh1.setAlignment(Pos.BASELINE_CENTER);
				sdStartCh1.setOnAction(e -> window.setScene(scene6));
				Button sdStartCh2 = new Button(" Workouts ");
				sdStartCh2.setAlignment(Pos.BASELINE_CENTER);
				sdStartCh2.setOnAction(e -> window.setScene(scene6));
				Button sdStartCh3 = new Button(" Schedule ");
				sdStartCh3.setAlignment(Pos.BASELINE_CENTER);
				sdStartCh3.setOnAction(e -> window.setScene(scene6));
				Button sdStartCh4 = new Button(" Update info ");
				sdStartCh4.setAlignment(Pos.BASELINE_CENTER);
				sdStartCh4.setOnAction(e -> window.setScene(scene9));
				sdStartChoices.add(sdStartCh1, 0, 1);
				sdStartChoices.add(sdStartCh2, 0, 2);
				sdStartChoices.add(sdStartCh3, 0, 3);
				sdStartChoices.add(sdStartCh4, 0, 4);

				// Used borderpane to place all choices user can use
				BorderPane sdHomeLayout = new BorderPane();
				sdHomeLayout.setStyle("-fx-background-color: #32cd32;");
				sdHomeLayout.setTop(sdStartLayout);
				sdHomeLayout.setCenter(sdStartChoices);
				sdHomeLayout.setPadding(new Insets(10, 10, 10, 10));
		      
			// Making the actual startup screen for the app
				VBox blkStartLayout = new VBox(20);
				Label blkStartLabel = new Label("	Welcome to your profile!");
					blkStartLabel.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 20));
					blkStartLabel.setTextFill(Color.WHITE);
					blkStartLayout.getChildren().addAll(blkStartLabel);
		    		blkStartLayout.setAlignment(Pos.CENTER);

			// making options for the user to choose from in the actual app start
					GridPane blkStartChoices = new GridPane();
					blkStartChoices.setAlignment(Pos.CENTER);
					blkStartChoices.setHgap(10);
					blkStartChoices.setVgap(10);
					blkStartChoices.setPadding(new Insets(25, 25, 25, 25));
					Button blkStartCh1 = new Button(" My Meals ");
					blkStartCh1.setAlignment(Pos.BASELINE_CENTER);
					blkStartCh1.setOnAction(e -> window.setScene(scene7));
					Button blkStartCh2 = new Button(" Workouts ");
					blkStartCh2.setAlignment(Pos.BASELINE_CENTER);
					blkStartCh2.setOnAction(e -> window.setScene(scene7));
					Button blkStartCh3 = new Button(" Schedule ");
					blkStartCh3.setAlignment(Pos.BASELINE_CENTER);
					blkStartCh3.setOnAction(e -> window.setScene(scene7));
					Button blkStartCh4 = new Button(" Update info ");
					blkStartCh4.setAlignment(Pos.BASELINE_CENTER);
					blkStartCh4.setOnAction(e -> window.setScene(scene9));
					blkStartChoices.add(blkStartCh1, 0, 1);
					blkStartChoices.add(blkStartCh2, 0, 2);
					blkStartChoices.add(blkStartCh3, 0, 3);
					blkStartChoices.add(blkStartCh4, 0, 4);

					// Used borderpane to place all choices user can use
					BorderPane blkHomeLayout = new BorderPane();
					blkHomeLayout.setStyle("-fx-background-color: #32cd32;");
				    blkHomeLayout.setTop(blkStartLayout);
					blkHomeLayout.setCenter(blkStartChoices);
					blkHomeLayout.setPadding(new Insets(10, 10, 10, 10));		
		      
			
			
			// Making the actual startup screen for the app
			   VBox mtStartLayout = new VBox(20);
			   Label mtStartLabel = new Label("	Welcome to your profile!");
					mtStartLabel.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 20));
					mtStartLabel.setTextFill(Color.WHITE);
					mtStartLayout.getChildren().addAll(mtStartLabel);
			   		mtStartLayout.setAlignment(Pos.CENTER);

			// making options for the user to choose from in the actual app start
				GridPane mtStartChoices = new GridPane();
					mtStartChoices.setAlignment(Pos.CENTER);
					mtStartChoices.setHgap(10);
					mtStartChoices.setVgap(10);
					mtStartChoices.setPadding(new Insets(25, 25, 25, 25));
					Button mtStartCh1 = new Button(" My Meals ");
					mtStartCh1.setAlignment(Pos.BASELINE_CENTER);
					mtStartCh1.setOnAction(e -> window.setScene(scene8));
					Button mtStartCh2 = new Button(" Workouts ");
					mtStartCh2.setAlignment(Pos.BASELINE_CENTER);
					mtStartCh2.setOnAction(e -> window.setScene(scene8));
					Button mtStartCh3 = new Button(" Schedule ");
					mtStartCh3.setAlignment(Pos.BASELINE_CENTER);
					mtStartCh3.setOnAction(e -> window.setScene(scene8));
					Button mtStartCh4 = new Button(" Update info ");
					mtStartCh4.setAlignment(Pos.BASELINE_CENTER);
					mtStartCh4.setOnAction(e -> window.setScene(scene9));
					mtStartChoices.add(mtStartCh1, 0, 1);
					mtStartChoices.add(mtStartCh2, 0, 2);
					mtStartChoices.add(mtStartCh3, 0, 3);
				    mtStartChoices.add(mtStartCh4, 0, 4);

		// Used borderpane to place all choices user can use
		BorderPane mtHomeLayout = new BorderPane();
		 mtHomeLayout.setStyle("-fx-background-color: #32cd32;");
		 mtHomeLayout.setTop(mtStartLayout);
		 mtHomeLayout.setCenter(mtStartChoices);
		 mtHomeLayout.setPadding(new Insets(10, 10, 10, 10));
	    

		// All scenes used
		scene1 = new Scene(introLayout, 400, 350);
		scene2 = new Scene(q1Layout, 400, 350);
		scene3 = new Scene(q2Layout, 400, 350);
		scene4 = new Scene(q3Layout, 400, 350);
		scene5 = new Scene(appLayout, 350, 350);
		scene6 = new Scene(scrollPaneShred, 700, 600);
		scene7 = new Scene(scrollPaneBulk, 700, 600);
		scene8 = new Scene(scrollPaneMt, 700, 600); 
		scene9 = new Scene(ageGenderLayout, 400, 350);
		scene10 = new Scene(sdHomeLayout, 400, 400);
		scene11 = new Scene(blkHomeLayout, 400, 400);
		scene12 = new Scene(mtHomeLayout, 400, 350);
		 
		window.setTitle("Healthy Helper");
		window.show();
		if (canRead()) {
			window.setScene(scene3);
//			regStart();
		} else {
			HBox introBox = new HBox();
			Image foodImage = new Image(new FileInputStream("ddddd.PNG"));
			ImageView foodImageView = new ImageView(foodImage);
			introBox.getChildren().add(foodImageView);
			intro = new Scene(introBox, 700, 800);
			window.setScene(intro);
			Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), (ActionEvent event) -> {
				window.setScene(scene1);
			}));
			timeline.setCycleCount(1);
			timeline.play();
			window.show();
		}
	}

	private void handleShredOptions(CheckBox fdCh1, CheckBox fdCh2, CheckBox fdCh3, CheckBox fdCh4, CheckBox fdCh5,
			CheckBox fdCh6) {
		String message = "Users meals chosen:\n";

		if (fdCh1.isSelected())
			message += "BBQ Chicken w/Quinoa Salad\n";

		if (fdCh2.isSelected())
			message += "Curry chicken w/Vegtables\n";

		if (fdCh3.isSelected())
			message += "Chicken fajitas\n";

		if (fdCh4.isSelected())
			message += "Teriyaki Chicken\n";

		if (fdCh5.isSelected())
			message += "Chicken Bruschetta Casserole\n";

		if (fdCh6.isSelected())
			message += "Chicken Tikka Masala";

		System.out.println(message);
	}

	private void makeFoodPane(FoodMenu food, GridPane foodPane, int order)
			throws FileNotFoundException {
		
		 foodPane.setAlignment(Pos.CENTER);
		 foodPane.setHgap(15);
		 foodPane.setVgap(15);
	     foodPane.setPadding(new Insets(25, 25, 25, 25));
		
		Image foodImage = new Image(new FileInputStream(food.getFoodPic()));
		ImageView foodImageView = new ImageView(foodImage);
		foodImageView.setX(50);
		foodImageView.setY(25);
		foodImageView.setFitHeight(80);
		foodImageView.setFitWidth(80);
		foodImageView.setPreserveRatio(true);

		CheckBox foodCheckBox = new CheckBox(food.getNameOfFood());
		foodCheckBox.setFont(Font.font("Times New Roman", 20));
		Button foodInfo = new Button(" View Information ");
		foodInfo.setAlignment(Pos.BASELINE_CENTER);
		foodInfo.setOnAction(e -> Ui.display());
		
		foodPane.getChildren().add(foodImageView);
		foodPane.setConstraints(foodImageView, 0, order);
		foodPane.getChildren().add(foodCheckBox);
		foodPane.setConstraints(foodCheckBox, 1, order);
		foodPane.getChildren().add(foodInfo);
		foodPane.setConstraints(foodInfo, 2, order);
	}
	
	public static void display() {
		
	Stage macroWindow = new Stage();
	macroWindow.initModality(Modality.APPLICATION_MODAL);
	macroWindow.setTitle("Macronutrients");
	Label infoLabel = new Label(" Protein: \n  Carbs: \n  Fats: \n  Total Calories: ");
	Button button1= new Button("Close window");
	button1.setOnAction(e -> macroWindow.close());
	VBox layout= new VBox(10);
	layout.getChildren().addAll(infoLabel, button1);
	layout.setAlignment(Pos.CENTER);
	Scene scene1= new Scene(layout, 300, 250);
	macroWindow.setScene(scene1);
	macroWindow.showAndWait();       
	}

	private void handleBulkOptions(CheckBox blkCh1, CheckBox blkCh2, CheckBox blkCh3, CheckBox blkCh4, CheckBox blkCh5,
			CheckBox blkCh6) {
		String message = "Users meals chosen:\n";

		if (blkCh1.isSelected())
			message += "Protein Pancakes\n";

		if (blkCh2.isSelected())
			message += "Spanish Paella\n";

		if (blkCh3.isSelected())
			message += "Steak and Potatoes\n";

		if (blkCh4.isSelected())
			message += "Chicken and Potatoes\n";

		if (blkCh5.isSelected())
			message += "Shepherd's Pie\n";

		if (blkCh6.isSelected())
			message += "Spaghetti Bolognese";

		System.out.println(message);
	}

	protected void regStart() {
		// this method will be called after canRead returns true, will start the APP on
		// regular menu
		System.out.println("regStarted");
		Profile pro = new Profile();

		BorderPane disInfo = new BorderPane();
		Label info = new Label(pro.getName() + "\n" + pro.getWeightPounds() + "\n" + pro.getHeightFeet() + "\n"
				+ pro.getHeightInches() + "\n" + pro.getSched() + "\n" + pro.getBodyType() + "\n");
		disInfo.setCenter(info);
		Scene s1 = new Scene(disInfo);
		window.setScene(s1);
		window.show();
	}

	protected static boolean canRead() {
		// this method checks if there is already INFO RECORDED in info.txt, so the APP
		// will skip the quiz
		File fil = new File("info.txt");
		Scanner in = null;

		try {
			in = new Scanner(fil);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if (in.hasNextLine()) {
			return true;
		} else {
			return false;
		}

	}

	protected void recordInfo(String a) {
		// this method takes a string and RECORDS the user info to info.txt, method is
		// called on "-->" during the quiz
		File fil = new File("info.txt");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fil, true));
			writer.append(a + "\n");
			writer.close();
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}
}
