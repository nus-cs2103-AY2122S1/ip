package janet;

/**
 * Encapsulates the methods needed to parse the input given to Janet and convert
 * it into a Command that Janet understands.
 */
public class Parser {

    /**
     * Returns a Command that Janet understands from a string passed to Janet by
     * the user.
     *
     * @param input String that user passed to Janet
     * @return Command that Janet is able to execute
     * @throws JanetException If invalid or insufficient arguments are provided
     */
    public static Command parseUserInput(String input) throws JanetException {
        String[] strings = input.split(" ");
        String operation = strings[0];
        switch (operation) {
        case "bye":
            // Fallthrough
        case "list":
            return new Command(operation);
        case "done":
            if (strings.length == 1) {
                throw new JanetException(Ui.EXPECTED_DONE_INDEX_GOT_NONE);
            }
            try {
                int doneTaskNum = Integer.parseInt(strings[1]);
                return new Command(strings[0], doneTaskNum);
            } catch (NumberFormatException e) {
                throw new JanetException(Ui.EXPECTED_DONE_INDEX_GOT_OTHER);
            }
        case "todo":
            if (strings.length == 1) {
                throw new JanetException(Ui.EXPECTED_TO_DO_DESCRIPTION);
            }
            return new Command(strings[0], input.substring(5));
        case "deadline":
            if (strings.length == 1) {
                throw new JanetException(Ui.EXPECTED_DEADLINE_DESCRIPTION);
            }
            String[] descriptionAndBy = input.substring(9).split("/by ");
            if (descriptionAndBy.length == 1) {
                throw new JanetException(Ui.EXPECTED_DEADLINE_BY);
            }
            return new Command(strings[0], descriptionAndBy[0], descriptionAndBy[1]);
        case "event":
            if (strings.length == 1) {
                throw new JanetException(Ui.EXPECTED_EVENT_DESCRIPTION);
            }
            String[] descriptionAndAt = input.substring(6).split("/at ");
            if (descriptionAndAt.length == 1) {
                throw new JanetException(Ui.EXPECTED_EVENT_AT);
            }
            return new Command(strings[0], descriptionAndAt[0], descriptionAndAt[1]);
        case "delete":
            if (strings.length == 1) {
                throw new JanetException(Ui.EXPECTED_DELETED_INDEX_GOT_NONE);
            }
            try {
                int delTaskNum = Integer.parseInt(strings[1]);
                return new Command(strings[0], delTaskNum);
            } catch (NumberFormatException e) {
                throw new JanetException(Ui.EXPECTED_DELETED_INDEX_GOT_OTHER);
            }
        case "find":
            if (strings.length == 1) {
                throw new JanetException(Ui.EXPECTED_SEARCH_QUERY);
            }
            return new Command(strings[0], input.substring(5));
        default:
            throw new JanetException(Ui.UNRECOGNISED_OPERATION);
        }
    }
}
