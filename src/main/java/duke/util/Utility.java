package duke.util;

public class Utility {
    public static int getIdFromString(String input, String prefix) throws DukeException {
        String idString = "";
        try {
            if (!input.startsWith(prefix) || input.length() <= prefix.length())
                throw new DukeException("you did not specify a duke.task id");

            idString = input.substring(prefix.length());
            return Integer.parseInt(idString);

        } catch (NumberFormatException e) {
            String msg = String.format("\"%s\" is not a valid integer", idString);
            throw new DukeException(msg);
        }
    }
}
