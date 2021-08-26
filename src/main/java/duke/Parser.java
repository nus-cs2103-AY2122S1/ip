package duke;

/**
 * Encapsulates the methods needed to parse the input given to Duke and convert
 * it into a Command that Duke understands.
 */
public class Parser {

    /**
     * Returns a Command that Duke understands from a string passed to Duke by
     * the user.
     *
     * @param input String that user passed to Duke
     * @return Command that Duke is able to execute
     * @throws DukeException If invalid or insufficient arguments are provided
     */
    public static Command parseUserInput(String input) throws DukeException {
        String[] strings = input.split(" ");
        String operation = strings[0];
        switch (operation) {
        case "bye":
        case "list":
            return new Command(operation);
        case "done":
            if (strings.length == 1) {
                throw new DukeException("You need to indicate which task number should be marked as done.");
            }
            try {
                int doneTaskNum = Integer.parseInt(strings[1]);
                return new Command(strings[0], doneTaskNum);
            } catch (NumberFormatException e) {
                throw new DukeException("The task to be marked as done should be indicated its list index.");
            }
        case "todo":
            if (strings.length == 1) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            return new Command(strings[0], input.substring(5));
        case "deadline":
            if (strings.length == 1) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String[] descriptionAndBy = input.substring(9).split("/by ");
            if (descriptionAndBy.length == 1) {
                throw new DukeException("The done-by date of a deadline cannot be empty.");
            }
            return new Command(strings[0], descriptionAndBy[0], descriptionAndBy[1]);
        case "event":
            if (strings.length == 1) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            String[] descriptionAndAt = input.substring(6).split("/at ");
            if (descriptionAndAt.length == 1) {
                throw new DukeException("The timing of an event cannot be empty.");
            }
            return new Command(strings[0], descriptionAndAt[0], descriptionAndAt[1]);
        case "delete":
            if (strings.length == 1) {
                throw new DukeException("You need to indicate which task number should be deleted.");
            }
            try {
                int delTaskNum = Integer.parseInt(strings[1]);
                return new Command(strings[0], delTaskNum);
            } catch (NumberFormatException e) {
                throw new DukeException("The task to be deleted should be indicated its list index.");
            }
        default:
            throw new DukeException("Sorry, I do not understand this command.");
        }
    }
}
