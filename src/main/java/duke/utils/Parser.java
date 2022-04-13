package duke.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.task.TASKTYPE;

/**
 * Class that contains methods to make sense of the user command
 */
public class Parser {

    /**
     * Returns respective integer selector based on user command
     *
     * If "bye" is the user command, 0 is returned
     * If "list" is the user command, 1 is returned
     * If "done" is the user command, 2 is returned
     * If "todo" is the user command, 3 is returned
     * If "deadline" is the user command, 4 is returned
     * If "event" is the user command, 5 is returned
     * If "delete" is the user command, 6 is returned
     * If "find" is the user command, 7 is returned
     * If "sort" is the user command, 8 is returned
     *
     * Default integer returned is 9.
     *
     * @param userInput provided user command string
     * @return integer representation of user command
     */
    public static int decoder(String userInput) {
        int res;
        switch (userInput) {
        case "bye":
            res = 0;
            break;
        case "list":
            res = 1;
            break;
        case "done":
            res = 2;
            break;
        case "todo":
            res = 3;
            break;
        case "deadline":
            res = 4;
            break;
        case "event":
            res = 5;
            break;
        case "delete":
            res = 6;
            break;
        case "find":
            res = 7;
            break;
        case "sort":
            res = 8;
            break;
        default:
            res = 9;
            break;
        }
        return res;
    }

    /**
     * Returns corrected input string by removing unnecessary blank string tokens
     *
     * @param input input string to be corrected
     * @return corrected string
     */
    public static String removeSpace(String input) {
        String[] arr = input.split(" ");
        String out = "";
        for (int i = 0; i < arr.length; i++) {
            out = out.concat(arr[i]);
            if (i == arr.length - 1) {
                continue;
            }
            if (arr[i].equals("")) {
                continue;
            }
            out += " ";
        }
        return out;
    }

    /**
     * Returns Validity of the command input
     *
     * if valid return true
     * Else return false
     * @param input input string to be corrected
     * @param t Type of task to check validity for
     * @return true or false depending on validity
     */
    public static boolean isNotValid(String input, TASKTYPE t) {
        String[] arr = input.split(" ");
        if (arr.length == 1) {
            return true;
        }
        if (t.equals(TASKTYPE.T)) {
            return false;
        }
        int c = 0;
        for (String s : arr) {
            if (s.equals("/by") && t.equals(TASKTYPE.D)) {
                return !dFormatCheck(arr, c += 1);
            } else if (s.equals("/at") && t.equals(TASKTYPE.E)) {
                return !eFormatCheck(arr, c += 1);
            } else if (s.equals("/at") && t.equals(TASKTYPE.D)) {
                return true;
            } else if (s.equals("/by") && t.equals(TASKTYPE.E)) {
                return true;
            } else {
                c++;
            }
        }
        return false;
    }

    private static boolean dFormatCheck(String[] arr, int index) {
        if (index != arr.length - 1) {
            return false;
        }
        try {
            LocalDate.parse(arr[index]);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static boolean eFormatCheck(String[] arr, int index) {
        if (index != arr.length - 2) {
            return false;
        }
        try {
            LocalDate.parse(arr[index]);
            LocalDate.parse(arr[index + 1]);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns array of strings that represent a deadline input
     *
     * @param input input string to be parsed
     * @return array of string tokens for the deadline object constructor
     */
    public static String[] dSplitter(String input) {
        String[] arr = input.split(" ");
        String[] res = new String[2];
        int index = 0;
        for (String s : arr) {
            if (s.equals("/by") || s.equals("/at")) {
                index = 1;
                continue;
            }
            if (s.equals("")) {
                continue;
            }
            if (res[index] == null) {
                res[index] = s;
                continue;
            }
            res[index] = res[index].concat(" ").concat(s);
        }
        return res;
    }

    /**
     * Returns array of strings that represent an event input
     *
     * @param input input string to be parsed
     * @return array of string tokens for the Event object constructor
     */
    public static String[] eSplitter(String input) {
        String[] arr = input.split(" ");
        String[] res = new String[3];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            String s = arr[i];
            if (s.equals("/by") || s.equals("/at")) {
                index = 1;
                continue;
            }
            if (s.equals("")) {
                continue;
            }
            if (i == arr.length - 1) {
                index++;
            }
            if (res[index] == null) {
                res[index] = s;
                continue;
            }
            res[index] = res[index].concat(" ").concat(s);
        }
        return res;
    }

}
