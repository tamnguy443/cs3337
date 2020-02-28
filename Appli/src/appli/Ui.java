import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
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
		

		HBox topMenu = new HBox(20);
		Label questionLabel = new Label("	Body type question 1:\n Enter height(inches) and weight(lbs).");
		Button A = new Button("next");
		topMenu.setAlignment(Pos.TOP_CENTER);
		A.setAlignment(Pos.BOTTOM_RIGHT);
		A.setOnAction(e -> window.setScene(scene3));
		topMenu.getChildren().addAll(questionLabel);
		
		VBox leftMenu = new VBox(20);
		Label heightLabel = new Label("Height: ");
		heightLabel.setAlignment(Pos.CENTER_LEFT);
		Label weightLabel = new Label("Weight: ");
		weightLabel.setAlignment(Pos.CENTER_LEFT);
		TextField weightInput = new TextField();
		weightInput.setAlignment(Pos.TOP_CENTER);
		TextField heightInput = new TextField();
		heightInput.setAlignment(Pos.TOP_CENTER);
		Button nextButton = new Button("-->");
		nextButton.setAlignment(Pos.BOTTOM_RIGHT);
		nextButton.setOnAction(e -> window.setScene(scene2));
		Button backButton = new Button("<--");
		backButton.setOnAction(e -> window.setScene(scene1));
		leftMenu.getChildren().addAll(heightLabel, heightInput, weightLabel,weightInput, nextButton, backButton);
		leftMenu.setAlignment(Pos.BASELINE_LEFT);

		BorderPane layout = new BorderPane();
		layout.setTop(topMenu);
		layout.setLeft(leftMenu);
		
		VBox layout0 = new VBox(20);
		Label introLabel = new Label("	Healthy Helper!\n" + "Please enter your name");
		TextField nameInput = new TextField();
		Button i = new Button("Continue!");
		i.setOnAction(e->{
			if(nameInput.getText().trim().isEmpty()) {
				AlertBox.display("Missing input", "please enter your name");
		}else {
			window.setScene(scene2);
			}
		}
			);
		layout.setPadding(new Insets(10,10,10,10));
		layout0.getChildren().addAll(introLabel,nameInput,i);
		layout0.setAlignment(Pos.CENTER);
		
		VBox layout2 = new VBox(20);
		Label label2 = new Label(" ");
		Button p = new Button(" ");
		p.setOnAction(e-> window.setScene(scene2));
		layout2.getChildren().addAll(label2, p);
		layout2.setAlignment(Pos.CENTER);
		

		VBox layout5 = new VBox(20);
		Label label5 = new Label("N/A");
		Button t = new Button("N/A");
		t.setOnAction(e-> window.setScene(scene6));
		layout5.getChildren().addAll(label5, t);
		layout5.setAlignment(Pos.CENTER);
		
		VBox layout3 = new VBox(20);
		Label label3 = new Label("Question 1");
		Button r = new Button(" ");
		r.setOnAction(e-> window.setScene(scene2));
		layout3.getChildren().addAll(label3, r);
		layout3.setPadding(new Insets(20,20,20,20));
		layout3.setAlignment(Pos.CENTER);
		
		VBox layout4 = new VBox(20);
		Label label4 = new Label("Question 2:");
		Button u = new Button(" ");
		t.setOnAction(e-> window.setScene(scene4));
		Button q = new Button(" ");
		q.setOnAction(e -> AlertBox.display("lol!!", "go back!"));
		layout4.getChildren().addAll(label4, u, q);
		layout4.setAlignment(Pos.CENTER);

		scene1 = new Scene(layout0,350,350);
		scene2 = new Scene(layout, 350, 350);
		scene3 = new Scene(layout2, 350, 350);
		scene4 = new Scene(layout3, 350, 350);
		scene5 = new Scene(layout4, 350, 350);
		scene6 = new Scene(layout5, 350, 350);

		window.setTitle("Demo Board");
		window.setScene(scene1);
		window.show();
	}
}
