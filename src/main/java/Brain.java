import java.util.Arrays;
import java.util.Scanner;

public class Brain {

    public static Storage storage = new Storage();

    /**
     * Takes the first word and decides the next course of action.
     * @param input takes in a string based on the scanner
     * @return boolean value to indicate whether to continue running the program
     */
    public static boolean decide(String input) {
        String[] parsedInput = input.split(" ", 2);
        switch (parsedInput[0]) {
            case "bye":
                Speech.goodbye();
                return false;
            case "list":
                storage.getStorage();
                return true;
            case "done":
                storage.done(parsedInput[1]);
                return true;
            case "deadline":
                storage.deadline(parsedInput[1]);
                return true;
            case "todo":
                storage.todo(parsedInput[1]);
                return true;
            case "event":
                storage.event(parsedInput[1]);
                return true;
            default:
                storage.basicAdd(input);
                return true;
        }

    }

}
