package duke;

import duke.utils.DukeException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Scanner;

public class Ui {
    private static File HELP;
    private static final String DEFAULT_HELP_PATH = "help.txt";
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
            InputStream in = Ui.class.getResourceAsStream(DEFAULT_HELP_PATH);
            BufferedReader sc = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            while (sc.ready()) {
                sb.append(sc.readLine());
                sb.append("\n\t ");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            throw new DukeException("help.txt not found @ " + HELP);
        } catch (IOException e) {
            throw new DukeException(e.toString());
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
            InputStream in = Ui.class.getResourceAsStream(DEFAULT_HELP_PATH);
            BufferedReader sc = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            boolean isFound = false;
            while (sc.ready()) {
                String line = sc.readLine();
                if (line.contains("> " + cmd)) {
                    isFound = true;
                    sb.append(line);
                    break;
                }
            }
            String sep = BORDERS.substring(1);
            while (isFound && sc.ready()) {
                String line = sc.readLine();
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
        } catch (IOException e) {
            throw new DukeException(e.toString());
        }
    }
}

