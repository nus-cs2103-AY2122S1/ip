package duke;

import duke.task.Task;

/**
 * Stores commands, descriptors, responses and error messages used by duke.Duke chatbot.
 */
public class Ui {
    /** Enums for Duke chatbot descriptors */
    public enum Descriptors {
        AT("at"),
        BY("by");

        private final String descriptor;

        Descriptors(String descriptor) {
            this.descriptor = descriptor;
        }

        public String getDescriptor() {
            return this.descriptor;
        }

        public int getLength() {
            return this.descriptor.length();
        }

        @Override
        public String toString() {
            return this.descriptor;
        }
    }

    /** Enums for Duke chatbot commands */
    public enum Commands {
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DONE("done"),
        DELETE("delete"),
        LIST("list"),
        DATE("date"),
        FIND("find"),
        BYE("bye");

        private final String command;

        Commands(String command) {
            this.command = command;
        }

        public String getCommand() {
            return this.command;
        }

        public int getLength() {
            return this.command.length();
        }

        @Override
        public String toString() {
            return this.command;
        }
    }

    /**
     * Returns the standard welcome response.
     *
     * @return Standard welcome response.
     */
    public String getWelcomeMessage() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Returns the standard goodbye response.
     *
     * @return Standard goodbye response.
     */
    public String getGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns the standard response for mark success and the marked task.
     *
     * @param task Task marked as done.
     * @return Standard response for mark success and the marked task.
     */
    public String getMarkSuccessMessage(Task task) {
        return "Nice! I've marked this task as done:" + "\n" + task.toString();
    }

    /**
     * Returns the standard response for delete success, followed by the
     * deleted task and the new total count of tasks.
     *
     * @param task The deleted task.
     * @param size The new total count of tasks.
     * @return Standard response for delete success, followed by the deleted task and the new total count of tasks.
     */
    public String getDeleteSuccessMessage(Task task, int size) {
        return "Noted. I've removed this task:" + "\n" + task.toString() + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Returns the standard response for add success, followed by the
     * task added and the new total count of tasks.
     *
     * @param task The added task.
     * @param size The new total count of tasks.
     * @return standard response for add success, followed by the task added and the new total count of tasks.
     */
    public String getAddSuccessMessage(Task task, int size) {
        return "Got it. I have added this task:" + "\n" + task.toString() + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Returns the standard response for listing tasks.
     *
     * @return Standard response for listing tasks.
     */
    public String getListSuccessMessage() {
        return "Here are the tasks in your list:";
    }

    /**
     * Returns the standard response for success in finding tasks by date.
     *
     * @param formattedDateString Date being searched for.
     * @param counter Total count of tasks found to fall on date being searched for.
     * @param deadlines Total count of Deadlines found to fall on date being searched for.
     * @param events Total count of Events found to fall on date being searched for.
     * @param foundTasks String accumulation of matching tasks.
     * @return Standard response for success in finding tasks by date.
     */
    public String getDateListSuccessMessage(String formattedDateString, int counter, int deadlines, int events,
                                            String foundTasks) {
        String start = "Here are the Deadlines or Events that fall on " + formattedDateString + ":\n";
        String end = "A total of " + counter + " events (" + deadlines + " deadlines and "
                + events + " events) fall on " + formattedDateString;
        return start + foundTasks + end;
    }

    /**
     * Returns the standard response for success in finding tasks that match a search keyword.
     *
     * @param counter Total count of tasks found to match the search keyword.
     * @param keyword Search keyword being searched for.
     * @param foundTasks String accumulation of matching tasks.
     * @return Standard response for success in finding tasks that match a search keyword.
     */
    public String getFindSuccessMessage(int counter, String keyword, String foundTasks) {
        String start = "Here are the matching tasks in your list:\n";
        String end = "A total of " + counter
                + " tasks in your list match your search keyword, " + keyword + ".";

        return start + foundTasks + end;
    }

    /**
     * Returns the standard error message for invalid user command.
     *
     * @return Standard error message for invalid user command.
     */
    public static String exceptionInvalidUserCommand() {
        return "I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Returns the standard error message for missing task description.
     *
     * @param userCommand The initial command of the user input which has the missing task description.
     * @return Standard error message for missing task description.
     */
    public static String exceptionMissingTaskDescription(String userCommand) {
        return "The description of " + userCommand + " cannot be empty.";
    }

    /**
     * Returns the standard error message for missing date.
     *
     * @return Standard error message for missing date.
     */
    public static String exceptionMissingDate() {
        return "No date is provided to search for.";
    }

    /**
     * Returns the standard error message for missing index for deletion.
     *
     * @return Standard error message for missing index for deletion.
     */
    public static String exceptionMissingIndexForDelete() {
        return "An index must be provided to delete task at index.";
    }

    /**
     * Returns the standard error message for invalid index for deletion.
     * An index for deletion is invalid if it exceeds (size of TaskList - 1) or is less than 0.
     *
     * @return Standard error message for invalid index for deletion.
     */
    public static String exceptionInvalidIndexForDelete() {
        return "Index provided for delete is either less than 1 or exceeds the length of the list, hence invalid.";
    }

    /**
     * Returns the standard error message for missing index for marking a task as done.
     *
     * @return Standard error message for missing index for marking.
     */
    public static String exceptionMissingIndexForMarking() {
        return "An index must be provided to mark task at the index as done.";
    }

    /**
     * Returns the standard error message for invalid index for marking a task as done.
     * An index for marking is invalid if it exceeds (size of TaskList - 1) or is less than 0.
     *
     * @return Standard error message for invalid index for marking.
     */
    public static String exceptionInvalidIndexForMarking() {
        return "Index provided for done is either less than 1 or exceeds the length of the list, hence invalid.";
    }

    /**
     * Returns the standard error message for invalid date format.
     * This error message is meant for all errors related to parsing user input to LocalDate.
     *
     * @return Standard error message for invalid date format.
     */
    public static String exceptionInvalidDateTimeFormat() {
        return "Cannot read invalid datetime format.";
    }

    /**
     * Returns the standard error message for invalid LocalDate.
     * This error message is meant for all errors related to converting LocalDate into string formats.
     *
     * @return Standard error message for invalid LocalDate.
     */
    public static String exceptionInvalidLocalDate() {
        return "Stored date is corrupt and cannot be read.";
    }

    /**
     * Returns the standard error message for invalid number input.
     * This error message is meant for all errors related to failure to parse a string to int.
     *
     * @param command The initial command of user input that contains the invalid number input.
     * @return Standard error message for invalid number input.
     */
    public static String exceptionInvalidNumberInput(Commands command) {
        return "Index for " + command.getCommand() + " must be an integer.";
    }

    /**
     * Returns the standard error message for missing descriptor in user input.
     *
     * @param descriptor The descriptor that should be used.
     * @param command The command that is used in tandem with descriptor.
     * @return Standard error message for missing descriptor.
     */
    public static String exceptionMissingDescriptor(Descriptors descriptor, Commands command) {
        return "/" + descriptor.getDescriptor()
                + " must be provided and not empty for " + command.getCommand() + ".";
    }

    /**
     * Returns the standard error message for missing space after command in user input.
     * This error message is to warn users that they may have just missed a space in their input,
     * which allows for quick fixes.
     *
     * @param userCommand The command with missing space after it.
     * @return Standard error message for missing space after command.
     */
    public static String exceptionMissingSpaceAfterCommand(String userCommand) {
        return "There is a missing space after " + userCommand + ".";
    }

    /**
     * Returns the standard error message for wrong descriptor used for a command in user input.
     * This error message is meant to warn users that they have used the wrong descriptor in tandem with
     * a certain command.
     *
     * @param command The command for which the wrong descriptor was used.
     * @param descriptors The correct descriptor.
     * @return Standard error message for wrong descriptor.
     */
    public static String exceptionWrongDescriptor(Commands command, Descriptors descriptors) {
        return "Wrong descriptor used. Descriptor for " + command.getCommand()
                + " should be " + descriptors.getDescriptor() + ".";
    }

    /**
     * Returns the standard error message for missing space before descriptor.
     * This error message is meant to warn users of specifically a missing space before a descriptor
     * to allow for quick fixes.
     *
     * @param descriptor descriptor for which there is a missing space before it.
     * @return Standard error message for missing space before descriptor.
     */
    public static String exceptionMissingSpaceBeforeDescriptor(Descriptors descriptor) {
        return "There is a missing space before the descriptor " + descriptor.getDescriptor() + ".";
    }

    /**
     * Returns the standard error message for missing space after descriptor.
     * This error message is meant to warn users of specifically a missing space after a descriptor
     * to allow for quick fixes.
     *
     * @param descriptor descriptor for which there is a missing space after it.
     * @return Standard error message for missing space after descriptor.
     */
    public static String exceptionMissingSpaceAfterDescriptor(Descriptors descriptor) {
        return "There is a missing space after the descriptor " + descriptor.getDescriptor() + ".";
    }

    /**
     * Returns the standard error message for failure to convert save formats to corresponding tasks.
     * This error message is meant for failure to parse any read strings after reading the save file.
     *
     * @return Standard error message for failure to convert save formats to corresponding tasks.
     */
    public static String exceptionCorruptSaveFile() {
        return "Save files corrupted. Failed to read tasks from save file.";
    }

    /**
     * Returns the standard error message for failure to read save file.
     *
     * @return Standard error message for failure to read save file.
     */
    public static String exceptionCannotReadFile() {
        return "Failed to read tasks from save file.";
    }

    /**
     * Returns the standard error message for failure to write to save file.
     *
     * @return Standard error message for failure to write to save file.
     */
    public static String exceptionCannotSaveFile() {
        return "Failed to save tasks.";
    }

    public static String exceptionMissingSearchInput() {
        return "Cannot perform search. Search keyword must be provided";
    }
}
