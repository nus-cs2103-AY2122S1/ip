package duke;

public class Validators {
    public static String validateTodoDescription(String input) {
        if (input.split("todo").length == 0) {
            try {
                throw new DukeException.emptyToDoDescriptionException();
            } catch (DukeException.emptyToDoDescriptionException e) {
                e.exceptionMessage();
                return e.stringExceptionMessage();
            }
        } else {
            return null;
        }
    }

    public static String validateEventDescription(String input) {
        if (input.split("event").length == 0) {
            try {
                throw new DukeException.emptyEventDescriptionException();
            } catch (DukeException.emptyEventDescriptionException e) {
                e.exceptionMessage();
                return e.stringExceptionMessage();
            }
        } else {
            return null;
        }
    }

    public static String validateDeadlineDescription(String input) {
        if (input.split("deadline").length == 0) {
            try {
                throw new DukeException.emptyDeadlineDescriptionException();
            } catch (DukeException.emptyDeadlineDescriptionException e) {
                e.exceptionMessage();
                return e.stringExceptionMessage();
            }
        } else {
            return null;
        }
    }
}
