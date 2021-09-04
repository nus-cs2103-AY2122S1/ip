package virushade;

import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Virushade virushade = new Virushade("./data/Virushade.txt");
        Platform.startup(() -> {
            Stage stage = new Stage();
            virushade.start(stage);
        });
    }
}
