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
import javafx.stage.Stage;
import javafx.util.Duration;

public class Ui extends Application {
	Stage window;
	Scene scene1, scene2, scene3, scene4, scene5, scene6, scene7;
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
				window.setScene(scene2);
			}
		});
		introLayout.setPadding(new Insets(10, 10, 10, 10));
		introLayout.setStyle("-fx-background-color: #32cd32;");
		introLayout.getChildren().addAll(introLabel, nameInput, i);
		introLayout.setAlignment(Pos.CENTER);

		// Scene 2: Used a label to displays first question
		HBox q1Display = new HBox(20);
		Label q1Label = new Label("	Body type question 1:\n 	Please Enter height and weight(lbs).");
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
		Label q2Label = new Label("	Body type question 2:\n How many times per week do you exercise? ");
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
		Label q3Label = new Label("	Body type question 3:\n What is your body type goal? ");
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
		FoodMenu foodMenu = new FoodMenu();

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
		// appLayout.setBottom(q3Btn);
		appLayout.setPadding(new Insets(10, 10, 10, 10));

		// New scene for when menu is clicked on
		HBox shredMenuHeadings = new HBox(40);
		Label foodNameLabel = new Label("Food List: ");
		foodNameLabel.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 40));
		foodNameLabel.setAlignment(Pos.TOP_LEFT);
		Label macroLabel = new Label("Macronutrients: ");
		macroLabel.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 20));
		macroLabel.setAlignment(Pos.TOP_RIGHT);
		shredMenuHeadings.getChildren().addAll(foodNameLabel);
		shredMenuHeadings.setAlignment(Pos.CENTER);

		// pane to setup the choices for meals
		GridPane shredMenuChoices = new GridPane();

		// layout for Shred Food Choices
		GridPane shredFoodChoices = new GridPane();
		shredFoodChoices.setAlignment(Pos.CENTER);
		shredFoodChoices.setHgap(15);
		shredFoodChoices.setVgap(15);
		
		shredFoodChoices.setPadding(new Insets(20, 20, 20, 20));
		ScrollPane scrollPaneShred = new ScrollPane();
		for (int k = 0; k < foodMenu.getMenuArr().size(); k++) {
			this.makeFoodPane(foodMenu.getMenuArr().get(k), shredMenuChoices, k);
		}
		scrollPaneShred.setContent(shredMenuChoices);

		// Layout Pane for when Menu is clicked
		BorderPane shredMenuLayout = new BorderPane();
		shredMenuLayout.setStyle("-fx-background-color: #32cd32;");
		shredMenuLayout.setTop(shredMenuHeadings);
		shredMenuLayout.setLeft(scrollPaneShred);
		shredMenuLayout.setCenter(shredFoodChoices);
		// menuLayout.setBottom(addMealBtn);
		shredMenuLayout.setPadding(new Insets(10, 10, 10, 10));

		HBox bulkMenuHeadings = new HBox(40);
		Label blkLabel = new Label("Food List: ");
		blkLabel.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 40));
		blkLabel.setAlignment(Pos.TOP_LEFT);
		bulkMenuHeadings.getChildren().addAll(blkLabel);
		bulkMenuHeadings.setAlignment(Pos.CENTER);

		// FIXME
		HBox blkScroll = new HBox(20);
		ScrollBar t = new ScrollBar();
		t.setMin(0);
		t.setMax(20);
		t.setValue(0);
		t.setOrientation(Orientation.VERTICAL);
		t.setUnitIncrement(5);
		t.setBlockIncrement(10);
		t.setPrefSize(20, 20);
		blkScroll.getChildren().add(t);

		GridPane bulkMenuChoices = new GridPane();
		bulkMenuChoices.setAlignment(Pos.CENTER);
		bulkMenuChoices.setHgap(15);
		bulkMenuChoices.setVgap(15);
		bulkMenuChoices.setPadding(new Insets(25, 25, 25, 25));
		Image ptPancakes = new Image(new FileInputStream("foodPics\\proteinPancakes.jpg"));
		ImageView ptPancakesView = new ImageView(ptPancakes);
		ptPancakesView.setX(50);
		ptPancakesView.setY(25);
		ptPancakesView.setFitHeight(80);
		ptPancakesView.setFitWidth(80);
		ptPancakesView.setPreserveRatio(true);
		Image spPaella = new Image(new FileInputStream("foodPics\\spanishPaella.jpg"));
		ImageView spPaellaView = new ImageView(spPaella);
		spPaellaView.setX(50);
		spPaellaView.setY(25);
		spPaellaView.setFitHeight(80);
		spPaellaView.setFitWidth(80);
		spPaellaView.setPreserveRatio(true);
		Image stkPotatoes = new Image(new FileInputStream("foodPics\\steakPotatoes.jpg"));
		ImageView stkPotatoesView = new ImageView(stkPotatoes);
		stkPotatoesView.setX(50);
		stkPotatoesView.setY(25);
		stkPotatoesView.setFitHeight(80);
		stkPotatoesView.setFitWidth(80);
		stkPotatoesView.setPreserveRatio(true);
		Image chkPotatoes = new Image(new FileInputStream("foodPics\\chknPotatoes.jpg"));
		ImageView chkPotatoesView = new ImageView(chkPotatoes);
		chkPotatoesView.setX(50);
		chkPotatoesView.setY(25);
		chkPotatoesView.setFitHeight(80);
		chkPotatoesView.setFitWidth(80);
		chkPotatoesView.setPreserveRatio(true);
		Image shpPie = new Image(new FileInputStream("foodPics\\shpPie.jpg"));
		ImageView shpPieView = new ImageView(shpPie);
		shpPieView.setX(50);
		shpPieView.setY(25);
		shpPieView.setFitHeight(80);
		shpPieView.setFitWidth(80);
		shpPieView.setPreserveRatio(true);
		Image spagBol = new Image(new FileInputStream("foodPics\\spaghettiBol.jpg"));
		ImageView spagBolView = new ImageView(spagBol);
		spagBolView.setX(50);
		spagBolView.setY(25);
		spagBolView.setFitHeight(80);
		spagBolView.setFitWidth(80);
		spagBolView.setPreserveRatio(true);
		Image calSurplus = new Image(new FileInputStream("foodPics\\caloric-surplus.jpg"));
		ImageView calSurplusView = new ImageView(calSurplus);
		calSurplusView.setX(50);
		calSurplusView.setY(25);
		calSurplusView.setFitHeight(300);
		calSurplusView.setFitWidth(300);
		calSurplusView.setPreserveRatio(true);

		bulkMenuChoices.getChildren().addAll(ptPancakesView, spPaellaView, stkPotatoesView, chkPotatoesView, shpPieView,
				spagBolView);
		bulkMenuChoices.setConstraints(ptPancakesView, 0, 1);
		bulkMenuChoices.setConstraints(spPaellaView, 0, 2);
		bulkMenuChoices.setConstraints(stkPotatoesView, 0, 3);
		bulkMenuChoices.setConstraints(chkPotatoesView, 0, 4);
		bulkMenuChoices.setConstraints(shpPieView, 0, 5);
		bulkMenuChoices.setConstraints(spagBolView, 0, 6);

		GridPane bulkFoodChoices = new GridPane();
		bulkFoodChoices.setAlignment(Pos.CENTER);
		bulkFoodChoices.setHgap(15);
		bulkFoodChoices.setVgap(15);
		CheckBox blkCh1 = new CheckBox("Protein Pancakes");
		CheckBox blkCh2 = new CheckBox("Spanish Paella");
		CheckBox blkCh3 = new CheckBox("Steak and Potatoes ");
		CheckBox blkCh4 = new CheckBox("Chicken and Potatoes ");
		CheckBox blkCh5 = new CheckBox("Shepherd's Pie ");
		CheckBox blkCh6 = new CheckBox("Spaghetti Bolognese ");
		Button addMealBtn = new Button("Add Meals");
		blkCh1.setFont(Font.font("Times New Roman", 15));
		blkCh2.setFont(Font.font("Times New Roman", 15));
		blkCh3.setFont(Font.font("Times New Roman", 15));
		blkCh4.setFont(Font.font("Times New Roman", 15));
		blkCh5.setFont(Font.font("Times New Roman", 15));
		blkCh6.setFont(Font.font("Times New Roman", 15));
		addMealBtn.setOnAction(e -> handleBulkOptions(blkCh1, blkCh2, blkCh3, blkCh4, blkCh5, blkCh6));
		addMealBtn.setOnAction(e -> window.setScene(scene5));
		bulkFoodChoices.setPadding(new Insets(20, 20, 20, 20));
		bulkFoodChoices.add(blkCh1, 0, 1);
		bulkFoodChoices.add(blkCh2, 0, 2);
		bulkFoodChoices.add(blkCh3, 0, 3);
		bulkFoodChoices.add(blkCh4, 0, 4);
		bulkFoodChoices.add(blkCh5, 0, 5);
		bulkFoodChoices.add(blkCh6, 0, 6);
		bulkFoodChoices.add(calSurplusView, 0, 8);
		bulkFoodChoices.add(addMealBtn, 0, 12);

		BorderPane bulkMenuLayout = new BorderPane();
		bulkMenuLayout.setStyle("-fx-background-color: #32cd32;");
		bulkMenuLayout.setTop(bulkMenuHeadings);
//		bulkMenuLayout.setLeft(bulkMenuChoices);
		bulkMenuLayout.setCenter(bulkMenuChoices); // was foodChoices
		bulkMenuLayout.setRight(blkScroll);
		bulkMenuLayout.setPadding(new Insets(10, 10, 10, 10));

		/*
		 * HBox mtMenuHeadings = new HBox(40); Label mtLabel = new Label("Food List: ");
		 * mtLabel.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 40));
		 * mtLabel.setAlignment(Pos.TOP_LEFT);
		 * mtMenuHeadings.getChildren().addAll(mtLabel);
		 * mtMenuHeadings.setAlignment(Pos.CENTER);
		 * 
		 * //FIXME HBox mtScroll = new HBox(20); ScrollBar r = new ScrollBar();
		 * r.setMin(0); r.setMax(20); r.setValue(0);
		 * r.setOrientation(Orientation.VERTICAL); r.setUnitIncrement(5);
		 * r.setBlockIncrement(10); r.setPrefSize(20, 20);
		 * mtScroll.getChildren().add(r);
		 * 
		 */

		// All scenes used
		scene1 = new Scene(introLayout, 400, 350);
		scene2 = new Scene(q1Layout, 400, 350);
		scene3 = new Scene(q2Layout, 400, 350);
		scene4 = new Scene(q3Layout, 400, 350);
		scene5 = new Scene(appLayout, 350, 350);
		scene6 = new Scene(shredMenuLayout, 700, 600);
		scene7 = new Scene(bulkMenuLayout, 700, 600);
		/*
		 * scene8 = new Scene(mtMenuLayout, 700, 600); scene9 = new Scene(, 400, 350);
		 * scene10 = new Scene(, 400, 350);
		 */
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
		Image foodImage = new Image(new FileInputStream(food.getFoodPic()));
		ImageView foodImageView = new ImageView(foodImage);
		foodImageView.setX(50);
		foodImageView.setY(25);
		foodImageView.setFitHeight(80);
		foodImageView.setFitWidth(80);
		foodImageView.setPreserveRatio(true);

		CheckBox foodCheckBox = new CheckBox(food.getNameOfFood());
		foodCheckBox.setFont(Font.font("Times New Roman", 30));
		Button foodInfo = new Button(" View Information ");
		foodInfo.setAlignment(Pos.BASELINE_CENTER);
		foodInfo.setOnAction(e -> System.out.println("dab"));

		foodPane.getChildren().add(foodImageView);
		foodPane.setConstraints(foodImageView, 0, order);
		foodPane.getChildren().add(foodCheckBox);
		foodPane.setConstraints(foodCheckBox, 1, order);
		foodPane.getChildren().add(foodInfo);
		foodPane.setConstraints(foodInfo, 2, order);

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
