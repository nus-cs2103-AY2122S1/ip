package jarvis;

import java.io.File;

import javafx.application.Application;

public class Launcher {
    /**
     * Main method for the Jarvis application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
            case "-c":
            case "--console":
                Launcher.launchCli();
                break;
            case "-g":
            case "--gui":
                Launcher.launchGui();
                break;
            default:
                System.out.println("Invalid CLI argument!");
            }
        }
        if (args.length == 0) {
            Launcher.launchGui();
        }
    }

    private static void launchCli() {
        Jarvis jarvis = new Jarvis("data" + File.separator + "jarvis.txt");
        jarvis.launchInCliMode();
    }

    private static void launchGui() {
        Application.launch(Main.class);
    }
}
