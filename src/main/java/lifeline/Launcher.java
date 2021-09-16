package lifeline;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;


/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Starts the GUI or the console version of the program depending on the mode specified by the user.
     * If the user does not specify a mode, GUI version is run by default.
     *
     * @param args Arguments specified by the user.
     */
    public static void main(String[] args) {
        try {
            String mode = getMode(args);
            if (mode.equals("console")) {
                startConsole();
            } else {
                Application.launch(Main.class, args);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You did not specify a mode. Please specify 'gui' or 'console'");
        }
    }

    private static String getMode(String[] args) {
        List<String> arguments = Arrays.asList(args);
        int modeIndex = arguments.indexOf("--mode");
        String mode = modeIndex == -1 ? "gui" : arguments.get(modeIndex + 1);
        return mode.toLowerCase();
    }

    /**
     * Starts the console program.
     */
    public static void startConsole() {
        String pathToSaveAndLoadTasks = "save" + File.separator + "tasks.json";
        Lifeline lifeline = new Lifeline(pathToSaveAndLoadTasks);
        lifeline.start();
    }
}
