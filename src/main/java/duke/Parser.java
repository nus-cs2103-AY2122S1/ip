package duke;

public class Parser {

    public static Command parseUserInput(String input) throws DukeException {
        String[] strings = input.split(" ");
        String operation = strings[0];
        switch (operation) {
        case "bye":
        case "list":
            return new Command(operation);
        case "done":
            if (strings.length == 1) {
                throw new DukeException(Ui.EXPECTED_DONE_INDEX_GOT_NONE);
            }
            try {
                int doneTaskNum = Integer.parseInt(strings[1]);
                return new Command(strings[0], doneTaskNum);
            } catch (NumberFormatException e) {
                throw new DukeException(Ui.EXPECTED_DONE_INDEX_GOT_OTHER);
            }
        case "todo":
            if (strings.length == 1) {
                throw new DukeException(Ui.EXPECTED_TO_DO_DESCRIPTION);
            }
            return new Command(strings[0], input.substring(5));
        case "deadline":
            if (strings.length == 1) {
                throw new DukeException(Ui.EXPECTED_DEADLINE_DESCRIPTION);
            }
            String[] descriptionAndBy = input.substring(9).split("/by ");
            if (descriptionAndBy.length == 1) {
                throw new DukeException(Ui.EXPECTED_DEADLINE_BY);
            }
            return new Command(strings[0], descriptionAndBy[0], descriptionAndBy[1]);
        case "event":
            if (strings.length == 1) {
                throw new DukeException(Ui.EXPECTED_EVENT_DESCRIPTION);
            }
            String[] descriptionAndAt = input.substring(6).split("/at ");
            if (descriptionAndAt.length == 1) {
                throw new DukeException(Ui.EXPECTED_EVENT_AT);
            }
            return new Command(strings[0], descriptionAndAt[0], descriptionAndAt[1]);
        case "delete":
            if (strings.length == 1) {
                throw new DukeException(Ui.EXPECTED_DELETED_INDEX_GOT_NONE);
            }
            try {
                int delTaskNum = Integer.parseInt(strings[1]);
                return new Command(strings[0], delTaskNum);
            } catch (NumberFormatException e) {
                throw new DukeException(Ui.EXPECTED_DELETED_INDEX_GOT_OTHER);
            }
        default:
            throw new DukeException(Ui.UNRECOGNISED_OPERATION);
        }
    }
}