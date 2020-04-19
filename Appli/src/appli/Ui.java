package appli;

import java.io.BufferedWriter;
import javafx.animation.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.time.*;

public class Ui extends Application {
	Stage window;
	Scene scene1, scene2, scene3, scene4, scene5, scene6, scene7, scene8, scene9, scene10, scene11, scene12, scene13;
	Scene intro;

	public static void main(String[] args) {
		launch(args);
	}

	public static final String MainStyle = "Styler.css";

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
		// Body type quiz is started
		// Had to update the quiz to include age and gender, so started with that first

		HBox ageGenderQuestion = new HBox(20);
		Label agLabel = new Label("	Body type question 1:\n  Please enter your age and gender.");
		agLabel.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 18));
		agLabel.setTextFill(Color.WHITE);
		agLabel.setAlignment(Pos.CENTER);
		ageGenderQuestion.getChildren().addAll(agLabel);

		// Making the input fields for age and Gender
		GridPane ageGenderPane = new GridPane();
		ageGenderPane.setAlignment(Pos.CENTER);
		ageGenderPane.setHgap(10);
		ageGenderPane.setVgap(10);
		ageGenderPane.setPadding(new Insets(25, 25, 25, 25));
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

		ageGenderPane.add(ageLabel, 0, 1);
		ageGenderPane.add(ageInput, 1, 1);
		ageGenderPane.add(genderLabel, 0, 2);
		ageGenderPane.add(genderCh1, 1, 2);
		ageGenderPane.add(genderCh2, 1, 3);

		Button nxtBtn = new Button("-->");
		nxtBtn.setAlignment(Pos.BASELINE_RIGHT);
		nxtBtn.setOnAction(e -> {
			if (ageInput.getText().isEmpty() && !(genderCh1.isSelected()) && !(genderCh2.isSelected())) {
				AlertBox.display("Missing input", "Please answer all questions");
			} else if ((ageInput.getText().isEmpty() && !(genderCh1.isSelected()) && genderCh2.isSelected())) {
				AlertBox.display("Missing input", "Please answer all questions");
			} else if ((ageInput.getText().isEmpty() && genderCh1.isSelected() && !(genderCh2.isSelected()))) {
				AlertBox.display("Missing input", "Please answer all questions");
			} else if ((!(ageInput.getText().isEmpty()) && genderCh1.isSelected() && genderCh2.isSelected())) {
				AlertBox.display("User Error", "Please only check one box");
			} else if ((!(ageInput.getText().isEmpty()) && !(genderCh1.isSelected()) && !(genderCh2.isSelected()))) {
				AlertBox.display("Missing input", "Please answer all questions");
			} else {
				recordInfo(ageInput.getText());
				if(genderCh1.isSelected()) {
					recordInfo("m");
				} else {
					recordInfo("f");
				}
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
		Button q2ch1 = new Button("1-3");
		q2ch1.setAlignment(Pos.CENTER);
		q2ch1.setOnAction(e -> {
			window.setScene(scene4);
			recordInfo("1-3");
		});

		Button q2ch2 = new Button("4-5");
		q2ch2.setAlignment(Pos.CENTER);

		q2ch2.setOnAction(e -> {
			window.setScene(scene4);
			recordInfo("4-5");
		});
		Button q2ch3 = new Button("6-Everyday");
		q2ch3.setAlignment(Pos.CENTER);
		q2ch3.setOnAction(e -> {
			window.setScene(scene4);
			recordInfo("6-e");
		});

		q2Choices.add(q2ch1, 0, 1);
		q2Choices.add(q2ch2, 1, 1);
		q2Choices.add(q2ch3, 0, 2);

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
		Profile userProfile = new Profile();

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
		
		// Layout for Schedule
		// plan is to make the layout one big grid that displays the days from top to
		// bottom
		// below each day times will be shown along with all the food in order they are
		// meant to be eaten
		// grid will be placed on a scrollpane in order to see all days and meals
		// displayed
		
		VBox scheduleHeading = new VBox(20);
		Label nutritionHeading = new Label("	Nutrition Guide");
		nutritionHeading.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 40));
		nutritionHeading.setTextFill(Color.WHITE);
		scheduleHeading.getChildren().addAll(nutritionHeading);
		scheduleHeading.setAlignment(Pos.CENTER);

		// align left of border pane
		GridPane timeLayout = new GridPane();
		timeLayout.setAlignment(Pos.CENTER);
		timeLayout.setHgap(10);
		timeLayout.setVgap(10);
		timeLayout.setPadding(new Insets(25, 25, 25, 25));
		Label monBreakfastTimeLbl = new Label("8:00am");
		Label monLunchTimeLbl = new Label("12:30pm");
		Label monDinnerTimeLbl = new Label("8:30pm");
		Label monSnack1TimeLbl = new Label("3:00pm");
		Label monSnack2TimeLbl = new Label("5:30pm");
		timeLayout.add(monBreakfastTimeLbl, 6, 4);
		timeLayout.add(monLunchTimeLbl, 6, 5);
		timeLayout.add(monSnack1TimeLbl, 6, 6);
		timeLayout.add(monSnack2TimeLbl, 6, 7);
		timeLayout.add(monDinnerTimeLbl, 6, 8);

		GridPane schedule = new GridPane();
		schedule.setAlignment(Pos.CENTER);
		schedule.setHgap(10);
		schedule.setVgap(10);
		schedule.setPadding(new Insets(25, 25, 25, 25));
		Label monBreakfastLbl = new Label("Breakfast(8:00am): ");
		Label monLunchLbl = new Label("Lunch(12:30pm): ");
		Label monDinnerLbl = new Label("Dinner(8:30pm): ");
		Label monSnack1Lbl = new Label("Snack 1(3:00pm): ");
		Label monSnack2Lbl = new Label("Snack 2(5:30pm): ");
		Label monLbl = new Label("Monday: ");
		monLbl.getStyleClass().add("label-dayStyle");
		Label tuesLbl = new Label("Tuesday: ");
		tuesLbl.getStyleClass().add("label-dayStyle");
		Label wedLbl = new Label("Wednesday: ");
		wedLbl.getStyleClass().add("label-dayStyle");
		Label thursLbl = new Label("Thursday: ");
		thursLbl.getStyleClass().add("label-dayStyle");
		Label friLbl = new Label("Friday: ");
		friLbl.getStyleClass().add("label-dayStyle");
		Label satLbl = new Label("Saturday: ");
		satLbl.getStyleClass().add("label-dayStyle");
		schedule.add(monBreakfastLbl, 1, 2);
		schedule.add(monLunchLbl, 1, 6);
		schedule.add(monSnack1Lbl, 1, 10);
		schedule.add(monSnack2Lbl, 1, 14);
		schedule.add(monDinnerLbl, 1, 18);
		schedule.add(monLbl, 2, 0);
		schedule.add(tuesLbl, 6, 0);
		schedule.add(wedLbl, 10, 0);
		schedule.add(thursLbl, 14, 0);
		schedule.add(friLbl, 18, 0);
		schedule.add(satLbl, 22, 0);
		

		// takes you back to the main menu of app
		Button back = new Button("<--");
		back.getStyleClass().add("button-blue");
		back.setOnAction(e -> window.setScene(scene6));

		BorderPane scheduleLayout = new BorderPane();
		scheduleLayout.setStyle("-fx-background-color: #32cd32;");
		scheduleLayout.setTop(scheduleHeading);
		scheduleLayout.setCenter(schedule);
		scheduleLayout.setBottom(back);
		scheduleLayout.setPadding(new Insets(10, 10, 10, 10));

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
		CheckBox[] ShredCheckBoxes = new CheckBox[shredFoodMenu.getMenuArr().size()];
		for (int k = 0; k < shredFoodMenu.getMenuArr().size(); k++) {
			ShredCheckBoxes[k] = this.makeFoodPane(shredFoodMenu.getMenuArr().get(k), shredMenuChoices, k);
		}
		Button addShredBtn = new Button("Add Selected Meals");
		addShredBtn.setOnAction(e -> {
			for (int k = 0;k < ShredCheckBoxes.length; k++) {
				if (ShredCheckBoxes[k].isSelected()) {
					userProfile.selectedFood.add(shredFoodMenu.getMenuArr().get(k));
					recordInfo(shredFoodMenu.getMenuArr().get(k).getNameOfFood());
				}
			}
			window.setScene(scene10);
		});

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
		addBulkBtn.setOnAction(e -> {
			for (int k = 0;k < ShredCheckBoxes.length; k++) {
				if (ShredCheckBoxes[k].isSelected()) {
					userProfile.selectedFood.add(shredFoodMenu.getMenuArr().get(k));
					recordInfo(shredFoodMenu.getMenuArr().get(k).getNameOfFood());
				}
			}
			window.setScene(scene10);
		});

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

		HBox mtMenuHeadings = new HBox(40);
		Label mtLabel = new Label("Food List: ");
		mtLabel.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 40));
		mtLabel.setAlignment(Pos.TOP_LEFT);
		mtMenuHeadings.getChildren().addAll(mtLabel);
		mtMenuHeadings.setAlignment(Pos.CENTER);

		GridPane mtMenuChoices = new GridPane();
		for (int k = 0; k < mtFoodMenu.getMenuArr().size(); k++) {
			this.makeFoodPane(mtFoodMenu.getMenuArr().get(k), mtMenuChoices, k);
		}
		Button addMtBtn = new Button("Add Selected Meals");
		addMtBtn.setOnAction(e -> {
			for (int k = 0;k < ShredCheckBoxes.length; k++) {
				if (ShredCheckBoxes[k].isSelected()) {
					userProfile.selectedFood.add(shredFoodMenu.getMenuArr().get(k));
					recordInfo(shredFoodMenu.getMenuArr().get(k).getNameOfFood());
				}
			}
			window.setScene(scene10);
		});

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
		sdStartCh3.setOnAction(e -> {
			try {
				fillSched(schedule, userProfile);
			} catch (FileNotFoundException e1) {

			}
			window.setScene(scene13);
		});
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
		blkStartCh3.setOnAction(e -> window.setScene(scene13));
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
		mtStartCh3.setOnAction(e -> window.setScene(scene13));
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
//		scene11 = new Scene(blkHomeLayout, 400, 400);
//  	scene12 = new Scene(mtHomeLayout, 400, 350);
		scene13 = new Scene(scheduleLayout, 1000, 600);
		scene13.getStylesheets().add(MainStyle);

		window.setTitle("Healthy Helper");
		window.show();
					
		if (canRead()) {
			window.setScene(scene6);
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

	private void fillSched(GridPane pane, Profile userPro) throws FileNotFoundException {
		int day = 2; //day
		int row = 2; //time
		int foodRotation = 0;
		Schedule sched = new Schedule(userPro.selectedFood);
		for (int i = 0; i < 18; i++) {
			if (foodRotation >= sched.userSelectedFood.size() && sched.userSelectedFood.size() % 3 == 0) {
				foodRotation = 0;
				sched.rotateMeal();
				sched.rotateMeal();
			} else if(foodRotation >= sched.userSelectedFood.size()){
				foodRotation = 0;
			}
			Image foodImage = new Image(new FileInputStream(sched.userSelectedFood.get(foodRotation).getFoodPic()));
			ImageView foodImageView = new ImageView(foodImage);
			Button foodButton = new Button("",foodImageView);
			foodImageView.setX(80);
			foodImageView.setY(80);
			foodImageView.setFitHeight(100);
			foodImageView.setFitWidth(100);
			foodImageView.setPreserveRatio(true);
			int a = foodRotation;
			foodButton.setOnAction(e -> {
				try {
					this.showRecipe(sched.userSelectedFood.get(a));
				} catch (FileNotFoundException e1) {
				}
			});
			
			if (row == 2) { //breakfast
				pane.getChildren().add(foodButton);
				pane.setConstraints(foodButton, day, row);
				foodRotation++;
				row = row + 4;
				
			} else if(row == 6) { //lunch
				pane.getChildren().add(foodButton);
				pane.setConstraints(foodButton, day, row);
				foodRotation++;
				row = row + 12;
				
			} else if(row == 18) { //dinner
				pane.getChildren().add(foodButton);
				pane.setConstraints(foodButton, day, row);
				foodRotation++;
				day = day + 4;
				row = 2;
			}
		}
	}
	
	private void showRecipe(FoodMenu foodp2) throws FileNotFoundException {
		Stage macroWindow = new Stage();
		macroWindow.initModality(Modality.APPLICATION_MODAL);
		macroWindow.setTitle("Recipe");

		Image foodImage = new Image(new FileInputStream(foodp2.getFoodRecipe()));
		ImageView foodImageView = new ImageView(foodImage);
		foodImageView.setX(400);
		foodImageView.setY(400);
		foodImageView.setFitHeight(1000);
		foodImageView.setFitWidth(1000);
		foodImageView.setPreserveRatio(true);
		
		ScrollPane scrollPaneBulk = new ScrollPane();
		scrollPaneBulk.setContent(foodImageView);
		scrollPaneBulk.setFitToHeight(true);
		scrollPaneBulk.setFitToWidth(true);

		Scene scene1 = new Scene(scrollPaneBulk, 800, 800);
		macroWindow.setScene(scene1);
		macroWindow.setResizable(false);
		macroWindow.showAndWait();
		
	}

	private CheckBox makeFoodPane(FoodMenu food, GridPane foodPane, int order) throws FileNotFoundException {

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
		foodInfo.setOnAction(e -> {
			try {
				Ui.display(food);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});

		foodPane.getChildren().add(foodImageView);
		foodPane.setConstraints(foodImageView, 0, order);
		foodPane.getChildren().add(foodCheckBox);
		foodPane.setConstraints(foodCheckBox, 1, order);
		foodPane.getChildren().add(foodInfo);
		foodPane.setConstraints(foodInfo, 2, order);
		
		return foodCheckBox;
	}

	public static void display(FoodMenu foodp2) throws FileNotFoundException {

		Stage macroWindow = new Stage();
		macroWindow.initModality(Modality.APPLICATION_MODAL);
		macroWindow.setTitle("Macronutrients");

		Label proteinLabel = new Label(" Protein: " + foodp2.getProtein() + " grams");
		proteinLabel.setFont(Font.font("Times New Roman", 16));
		Label foodNameLabel = new Label(foodp2.getNameOfFood());
		foodNameLabel.setFont(Font.font("Times New Roman", 16));
		Label carbsLabel = new Label(" Carbs: " + foodp2.getCarbs() + " grams");
		carbsLabel.setFont(Font.font("Times New Roman", 16));
		Label fatsLabel = new Label(" Fats: " + foodp2.getFat() + " grams");
		fatsLabel.setFont(Font.font("Times New Roman", 16));
		Label pSizeLabel = new Label(" Portion Size: " + foodp2.getPortionSize() + " cups");
		pSizeLabel.setFont(Font.font("Times New Roman", 16));
		Label foodName = new Label("Name: ");
		foodName.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 20));
		foodName.setTextFill(Color.WHITE);
		Label macroLbl = new Label("Macros: ");
		macroLbl.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 20));
		macroLbl.setTextFill(Color.WHITE);

		// creating area for description
		VBox vbox = new VBox(200);
		Label description = new Label(foodp2.getDescription());
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.setStyle("-fx-background-color: #32cd32;");
		vbox.getChildren().add(description);
		vbox.setAlignment(Pos.CENTER);

		Image foodImage = new Image(new FileInputStream(foodp2.getFoodPic()));
		ImageView foodImageView = new ImageView(foodImage);
		foodImageView.setX(50);
		foodImageView.setY(25);
		foodImageView.setFitHeight(140);
		foodImageView.setFitWidth(200);
		foodImageView.setPreserveRatio(true);

		GridPane picLayout = new GridPane();
		picLayout.setAlignment(Pos.CENTER);
		picLayout.setHgap(15);
		picLayout.setVgap(15);
		picLayout.setPadding(new Insets(25, 25, 25, 25));
		picLayout.add(foodImageView, 0, 1);

		GridPane macroLayout = new GridPane();
		macroLayout.setAlignment(Pos.CENTER);
		macroLayout.setHgap(15);
		macroLayout.setVgap(15);
		macroLayout.setPadding(new Insets(25, 25, 25, 25));
		macroLayout.add(foodName, 1, 1);
		macroLayout.add(foodNameLabel, 2, 1);
		macroLayout.add(macroLbl, 1, 2);
		macroLayout.add(proteinLabel, 2, 2);
		macroLayout.add(carbsLabel, 2, 3);
		macroLayout.add(fatsLabel, 2, 4);
		macroLayout.add(pSizeLabel, 2, 5);

		BorderPane macroPane = new BorderPane();
		macroPane.setStyle("-fx-background-color: #32cd32;");
		macroPane.setLeft(picLayout);
		macroPane.setCenter(macroLayout);
		macroPane.setBottom(vbox);
		macroPane.setPadding(new Insets(10, 10, 10, 10));

		Scene scene1 = new Scene(macroPane, 800, 800);
		// scene1.getStylesheets().add(MainStyle);
		// scene1.setFill();
		macroWindow.setScene(scene1);
		macroWindow.setResizable(false);
		macroWindow.showAndWait();
	}

	protected void regStart() {
		// this method will be called after canRead returns true, will start the APP on
		// regular menu
//		System.out.println("regStarted");
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
