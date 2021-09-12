import javafx.application.Application;

public class Launcher {
    /**
     * The main method of the app.
     * @param args
     */
    public static void main(String[] args) {
        try {
            Application.launch(Main.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
