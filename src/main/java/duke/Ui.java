package duke;

import duke.utils.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ui {
    private final static File help = new File("help.txt");
    private final static String BORDERS = "\t____________________________________________________________";
    public static void reply(String input) {
        System.out.print(BORDERS);
        System.out.print("\n\t ");
        System.out.println(input);
        System.out.println(BORDERS);
    }
    
    public static String help() throws DukeException {
        try {
            StringBuilder sb = new StringBuilder();
            Scanner sc = new Scanner(help);
            while (sc.hasNext()) {
                sb.append(sc.nextLine());
                sb.append("\n\t ");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            throw new DukeException("help.txt not found");
        }
    }
}
