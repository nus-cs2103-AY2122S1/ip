package duke;

import duke.utils.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ui {
    private static File HELP;
    private static String DEFAULT_HELP_PATH = Ui.class.getResource("help/help.txt").getPath();
    private final static String BORDERS = "\t____________________________________________________________";

    /**
     * Prints reply to the user, enclosed above and below by borders.
     *
     * @param reply Reply to user.
     */
    public static void reply(String reply) {
        System.out.print(BORDERS);
        System.out.print("\n\t ");
        System.out.println(reply);
        System.out.println(BORDERS);
    }

    /**
     * Returns the Duke help manual as a formatted string.
     *
     * @return Duke help manual.
     * @throws DukeException
     */
    public static String help() throws DukeException {
        try {
            if (HELP == null) {
                HELP = new File(DEFAULT_HELP_PATH);
            }
            StringBuilder sb = new StringBuilder();
            Scanner sc = new Scanner(HELP);
            while (sc.hasNext()) {
                sb.append(sc.nextLine());
                sb.append("\n\t ");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            throw new DukeException("help.txt not found @ " + HELP + e.toString());
        }
    }
    
    /**
     * Returns manual segment containing the information for a certain command.
     *
     * @param cmd A command in Duke.
     * @return Duke help manual.
     * @throws DukeException
     */
    public static String help(String cmd) throws DukeException {
        try {
            if (HELP == null) {
                HELP = new File(DEFAULT_HELP_PATH);
            }
            StringBuilder sb = new StringBuilder();
            Scanner sc = new Scanner(HELP);
            boolean isFound = false;
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.contains("> " + cmd)) {
                    isFound = true;
                    sb.append(line);
                    break;
                }
            }
            String sep = BORDERS.substring(1);
            while (isFound && sc.hasNext()) {
                String line = sc.nextLine();
                if (line.contains(sep)) {
                    break;
                }
                sb.append("\n   ");
                sb.append(line);
            }
            if (!isFound) {
                return String.format("I don't have a manual entry for ` %s `!", cmd);
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            throw new DukeException("help.txt not found at file location: " + HELP);
        }
    }
}

