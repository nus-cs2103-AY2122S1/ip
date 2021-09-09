package duke;

/**
 * Contains exceptions specialised to Duke.
 */
public abstract class DukeException extends Exception{
    abstract void exceptionMessage();

    /**
     * Exception thrown when the user tries to create a new ToDo object without a description.
     */
    public static class emptyToDoDescriptionException extends DukeException {
        @Override
        void exceptionMessage() {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        String stringExceptionMessage() {
            return "☹ OOPS!!! The description of a todo cannot be empty.";
        }

    }

    /**
     * Exception thrown when the user tries to create a new Event object without a description.
     */
    public static class emptyEventDescriptionException extends DukeException {
        @Override
        void exceptionMessage() {
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
        }

        String stringExceptionMessage() {
            return "☹ OOPS!!! The description of an event cannot be empty.";
        }
    }

    /**
     * Exception thrown when the user tries to create a new Deadline object without a description.
     */
    public static class emptyDeadlineDescriptionException extends DukeException {
        @Override
        void exceptionMessage() {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        String stringExceptionMessage() {
            return"☹ OOPS!!! The description of a deadline cannot be empty.";
        }
    }

    /**
     * Exception thrown when a user types in an invalid input.
     */
    public static class invalidInputException extends DukeException {
        @Override
        void exceptionMessage() {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        String stringExceptionMessage() {
            return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }

    public static class cannotUndoException extends DukeException {
        @Override
        void exceptionMessage() {
            System.out.println("☹ OOPS!!! I'm sorry, but there is no command to undo :-(");
        }

        String stringExceptionMessage() {
            return "☹ OOPS!!! I'm sorry, but there is no command to undo :-(";
        }
    }
}
