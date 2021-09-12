import javafx.application.Application;

public class Launcher {
    public static void main(String[] args) {
        try {
            Application.launch(Main.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
