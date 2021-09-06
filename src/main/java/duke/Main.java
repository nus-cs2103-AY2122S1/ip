package duke;

import java.io.IOException;
import java.util.List;

import duke.exceptions.DukeException;
import duke.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Bridge class between UI and main chat bot logic.
 *
 * @author kevin9foong
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            String mainWindowFxmlFilePath = "/view/MainWindow.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(mainWindowFxmlFilePath));
            AnchorPane mainLayoutPane = fxmlLoader.load();
            Scene scene = new Scene(mainLayoutPane);
            primaryStage.setScene(scene);

            MainWindow controller = fxmlLoader.getController();
            loadCustomAvatarImagesFromArgs(controller);
            linkGuiToChatBotAgent(controller);

            primaryStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void loadCustomAvatarImagesFromArgs(MainWindow mainWindow) {
        List<String> args = getParameters().getRaw();
        Image[] images = loadImagesFromFiles(args);
        if (images.length >= 3) {
            mainWindow.setBackgroundImage(images[0]);
            mainWindow.setUserImage(images[1]);
            mainWindow.setDukeImage(images[2]);
        } else if (images.length == 2) {
            mainWindow.setBackgroundImage(images[0]);
            mainWindow.setUserImage(images[1]);
        } else if (images.length == 1) {
            mainWindow.setBackgroundImage(images[0]);
        }
    }

    private void linkGuiToChatBotAgent(MainWindow mainWindow) {
        try {
            Duke duke = new Duke();
            mainWindow.linkDuke(duke);
        } catch (DukeException de) {
            mainWindow.displayAgentMessage(de.getMessage());
            mainWindow.disableUserInput();
        }
    }

    /**
     * Loads the image with given image file name placed in the relative /resources/images directory.
     *
     * @param imageFileName name of image file placed in relative /resources/images directory.
     * @return loaded <code>Image</code>.
     */
    private Image loadImageFromFile(String imageFileName) {
        return new Image(this.getClass().getResourceAsStream("/images/" + imageFileName));
    }

    /**
     * Loads custom images from provided file names placed in relative /resources/images directory.
     *
     * @param imageFileNames names of image files placed in relative /resources/images directory.
     * @return loaded images.
     */
    private Image[] loadImagesFromFiles(List<String> imageFileNames) {
        return imageFileNames.stream().map(this::loadImageFromFile).toArray(Image[]::new);
    }
}
