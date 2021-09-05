package duke;

import duke.utils.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class Ui {
    private static File HELP;
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
                String filename = Main.class.getResource("bin/help.txt").getPath();
                HELP = new File(filename);
            }
            StringBuilder sb = new StringBuilder();
            Scanner sc = new Scanner(HELP);
            while (sc.hasNext()) {
                sb.append(sc.nextLine());
                sb.append("\n\t ");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            throw new DukeException("help.txt not found @ " + HELP);
        }
    }
    
    /**
     * Returns the Duke help manual as a formatted string.
     *
     * @return Duke help manual.
     * @throws DukeException
     */
    public static String help(String cmd) throws DukeException {
        try {
            if (HELP == null) {
                String filename = Main.class.getResource("bin/help.txt").getPath();
                HELP = new File(filename);
            }
            StringBuilder sb = new StringBuilder();
            Scanner sc = new Scanner(HELP);
            boolean found = false;
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.contains("> " + cmd)) {
                    found = true;
                    sb.append(line);
                    break;
                }
            }
            String sep = BORDERS.substring(1);
            while (found && sc.hasNext()) {
                String line = sc.nextLine();
                if (line.contains(sep)) {
                    break;
                }
                sb.append("\n\t ");
                sb.append(line);
            }
            if (!found) {
                return String.format("I don't have a manual entry for ' %s '!", cmd);
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            throw new DukeException("help.txt not found @ " + HELP);
        }
    }
}
