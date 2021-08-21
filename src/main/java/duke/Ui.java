package duke;

import duke.task.Task;

/**
 * Stores commands, descriptors and responses used by duke.Duke chatbot.
 */
public class Ui {
    // Enums for duke.Duke chatbot descriptors
    public enum Descriptors {
        AT("at"),
        BY("by");

        private final String DESCRIPTOR;

        public String getDescriptor() {
            return this.DESCRIPTOR;
        }

        public int getLength() {
            return this.DESCRIPTOR.length();
        }

        Descriptors(String descriptor) {
            this.DESCRIPTOR = descriptor;
        }

        @Override
        public String toString() {
            return this.DESCRIPTOR;
        }
    }

    // Enums for duke.Duke chatbot commands
    public enum Commands {
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DONE("done"),
        DELETE("delete"),
        LIST("list"),
        DATE("date"),
        BYE("bye");

        private final String COMMAND;

        public String getCommand() {
            return this.COMMAND;
        }

        public int getLength() {
            return this.COMMAND.length();
        }

        Commands(String command) {
            this.COMMAND = command;
        }

        @Override
        public String toString() {
            return this.COMMAND;
        }
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showMarkSuccess(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    public void showDeleteSuccess(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showAddSuccess(Task task, int size) {
        System.out.println("Got it. I have added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showListSuccess() {
        System.out.println("Here are the tasks in your list:");
    }

    public void showDateListSuccess(String formattedDateString) {
        System.out.println("Here are the Deadlines or Events that fall on " + formattedDateString + ":");
    }

    public void showDateListSummary(String formattedDateString, int counter, int deadlines, int events) {
        System.out.println("A total of " + counter + " events (" + deadlines + " deadlines and " +
                events + " events) fall on " + formattedDateString);
    }

    public static String exceptionInvalidUserCommand() {
        return "I'm sorry, but I don't know what that means :-(";
    }

    public static String exceptionMissingTaskDescription(String userCommand) {
        return "The description of " + userCommand + " cannot be empty.";
    }

    public static String exceptionMissingDate() {
        return "No date is provided to search for.";
    }

    public static String exceptionMissingIndexForDelete() {
        return "An index must be provided to delete task at index.";
    }

    public static String exceptionInvalidIndexForDelete() {
        return "Index provided for delete is either less than 1 or exceeds the length of the list, hence invalid.";
    }

    public static String exceptionMissingIndexForMarking() {
        return "An index must be provided to mark task at the index as done.";
    }

    public static String exceptionInvalidIndexForMarking() {
        return "Index provided for done is either less than 1 or exceeds the length of the list, hence invalid.";
    }

    public static String exceptionInvalidDateTimeFormat() {
        return "Cannot read invalid datetime format.";
    }

    public static String exceptionInvalidLocalDate() {
        return "Stored date is corrupt and cannot be read.";
    }

    public static String exceptionInvalidNumberInput(Commands command) {
        return "Index for " + command.getCommand() + " must be an integer.";
    }

    public static String exceptionMissingDescriptor(Descriptors descriptor, Commands command) {
        return "/" + descriptor.getDescriptor() +
                " must be provided and not empty for " + command.getCommand() + ".";
    }

    public static String exceptionMissingSpaceAfterCommand(String userCommand) {
        return "There is a missing space after " + userCommand + ".";
    }

    public static String exceptionMissingSpaceBeforeDescriptor(Descriptors descriptor) {
        return "There is a missing space before the descriptor " + descriptor.getDescriptor() + ".";
    }

    public static String exceptionMissingSpaceAfterDescriptor(Descriptors descriptor) {
        return "There is a missing space after the descriptor " + descriptor.getDescriptor() + ".";
    }

    public static String exceptionCorruptSaveFile() {
        return "Save files corrupted. Failed to read tasks from save file.";
    }

    public static String exceptionCannotReadFile() {
        return "Failed to read tasks from save file.";
    }

    public static String exceptionCannotSaveFile() {
        return "Failed to save tasks.";
    }

}
