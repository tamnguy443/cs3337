
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox extends Application {

	public static void display(String title, String message) {
		Stage window = new Stage();

		window.setTitle(title);
		window.setMinWidth(250);
		window.initModality(Modality.APPLICATION_MODAL);

		Label label1 = new Label();
		label1.setText(message);
		Button button = new Button("<--");
		button.setOnAction(e -> window.close());

		VBox layout = new VBox();
		layout.getChildren().addAll(label1, button);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout, 300, 300);

		window.setScene(scene);
		window.showAndWait();

	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub

	}
}
