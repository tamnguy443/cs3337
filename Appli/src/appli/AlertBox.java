package appli;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AlertBox extends Application {
	Stage window;
	private boolean firstTime;
	private TrayIcon trayIcon;

	public static void display(String title, String message) {
		Stage window = new Stage();

		window.setTitle(title);
		window.setMinWidth(250);
		window.initModality(Modality.APPLICATION_MODAL);

		Label label1 = new Label();
		label1.setText(message);
		Button button = new Button("Ok");
		button.setOnAction(e -> window.close());

		VBox layout = new VBox();
		layout.getChildren().addAll(label1, button);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout, 300, 300);

		window.setScene(scene);
		window.showAndWait();

	}
	
	public AlertBox(Stage window, TrayIcon ti) {
		this.window = window;
//		createTrayIcon(this.window);
		firstTime = true;		
		Platform.setImplicitExit(false);
		this.trayIcon = ti;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

	}
	
	public void createTrayIcon(final Stage stage) {
		if (SystemTray.isSupported()) {
			// get the SystemTray instance
			SystemTray tray = SystemTray.getSystemTray();

			// load an image
			java.awt.Image image = null;
			try {
				File pathToFile = new File("trayPic.PNG");
				image = ImageIO.read(pathToFile);
			} catch (IOException ex) {
				ex.printStackTrace();
			}

			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent t) {
					hide(stage);
				}
			});
			// create a action listener to listen for default action executed on the tray
			// icon
			final ActionListener closeListener = new ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.exit(0);
				}
			};

			ActionListener showListener = new ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							stage.show();
						}
					});
				}
			};
			// create a popup menu
			PopupMenu popup = new PopupMenu();

			MenuItem showItem = new MenuItem("Show");
			showItem.addActionListener(showListener);
			popup.add(showItem);

			MenuItem closeItem = new MenuItem("Close");
			closeItem.addActionListener(closeListener);
			popup.add(closeItem);
			/// ... add other items
			// construct a TrayIcon
			trayIcon = new TrayIcon(image, "Healthy Helper", popup);
			// set the TrayIcon properties
			trayIcon.addActionListener(showListener);
			
			// add the tray image
			try {
				tray.add(trayIcon);
				
			} catch (AWTException e) {
				System.err.println(e);
			}
		}
	}

	public void notifyUser(String title, String food ) {
		if (firstTime) {
			trayIcon.displayMessage(title, food, TrayIcon.MessageType.NONE);
		} // title message type
	}
	
	private void hide(final Stage stage) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (SystemTray.isSupported()) {
					stage.hide();
//					notifyUser();
				} else {
					System.exit(0);
				}
			}
		});
	}
}
