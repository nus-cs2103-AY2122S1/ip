package jarvis;

import java.io.File;

import javafx.application.Application;

public class Launcher {
    /**
     * Starts the application in CLI mode or GUI mode depending on the command line arguments supplied by the user.
     * If the user does not supply any command line arguments, the application starts in GUI by default.
     *
     * @param args Command line arguments.
     */
    public static void main(String... args) {
        if (args.length == 0) {
            Launcher.launchGui();
            return;
        }

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

    private static void launchCli() {
        Jarvis jarvis = new Jarvis("data" + File.separator + "jarvis.txt");
        jarvis.launchInCliMode();
    }

    private static void launchGui() {
        Application.launch(Main.class);
    }
}
