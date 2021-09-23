package duke.utils;

import duke.task.TASK_TYPE;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
     *
     * Default integer returned is 8.
     *
     * @param userInput X coordinate of position.
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
            default:
                res = 8;
                break;
        }
        return res;
    }

    public static String removeSpace(String input) {
        String[] arr = input.split(" ");
        String out = "";
        for (int i = 0; i < arr.length; i++) {
            out = out.concat(arr[i]);
            if (i == arr.length-1) {
                continue;
            }
            if (arr[i].equals("")) {
                continue;
            }
            out += " ";
        }
        return out;
    }

    public static boolean isNotValid(String input, TASK_TYPE t) {
        String[] arr = input.split(" ");
        if (arr.length == 1) {
            return true;
        }
        if (t.equals(TASK_TYPE.T)) {
            return false;
        }
        int c = 0;
        for (String s : arr) {
            if (s.equals("/by") && t.equals(TASK_TYPE.D)) {
                return !DformatCheck(arr, c += 1);
            } else if (s.equals("/at") && t.equals(TASK_TYPE.E)) {
                return !EformatCheck(arr, c += 1);
            } else if (s.equals("/at") && t.equals(TASK_TYPE.D)){
                return true;
            } else if (s.equals("/by") && t.equals(TASK_TYPE.E)){
                return true;
            } else {
                c++;
            }
        }
        return false;
    }

    private static boolean DformatCheck(String[] arr, int index) {
        if (index != arr.length-1) {
            return false;
        }
        try {
            LocalDate.parse(arr[index]);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static boolean EformatCheck(String[] arr, int index) {
        if (index != arr.length-2) {
            return false;
        }
        try {
            LocalDate.parse(arr[index]);
            LocalDate.parse(arr[index+1]);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

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
            if (i == arr.length-1) {
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
