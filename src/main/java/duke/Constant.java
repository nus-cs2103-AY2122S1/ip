package duke;

/**
 * The class for Global Duke Constants.
 */
public class Constant {
    /**
     * Available commands.
     */
    public enum Command {

        LIST ("", "Lists all the tasks."),
        TODO ("[description]", "Adds a todo task."),
        DEADLINE ("[description] /by [dd-MM-yyyy] [*optional hh:mm]", "Adds a task with a deadline"),
        EVENT ("[description] /at [dd-MM-yyyy] [*optional hh:mm]", "Adds an event to the task"),
        DELETE ("[index]", "Removes a task from the task list"),
        DONE ("[index]", "Marks a task as done"),
        FIND ("[keyword]", "Find a task by searching for a keyword"),
        HELP ("", "Shows all the commands available"),
        DATES ("", "Shows all the available date and time type"),
        BYE ("", "Quit the app");

        private final String arguments;
        private final String description;

        Command(String arguments, String description) {
            this.arguments = arguments;
            this.description = description;
        }

        /**
         * Returns a string representation of the command.
         * @return A string representation of the command.
         */
        @Override
        public String toString() {
            return this.name() + " " + arguments + "   -->   " + description;
        }
    }

    /**
     * Accepted Dates.
     */
    public enum Date {

        TODAY ("today", "today"),
        TOMORROW ("tomorrow", "tomorrow"),
        YEAR_DASH ("yyyy-MM-dd", "2021-12-25"),
        YEAR_SLASH ("yyyy/MM/dd", "2021/12/25"),
        DAY_DASH ("dd-MM-yyyy", "25-12-2021"),
        DAY_SLASH ("dd/MM/yyyy", "25/12/2021"),
        DATETIME_DOT ("[date] hh:mm", "[date] 18:00"),
        DATETIME_BLANK ("[date] hhmm", "[date] 1800");

        private final String accepted;
        private final String example;

        Date(String accepted, String example) {
            this.accepted = accepted;
            this.example = example;
        }

        /**
         * Returns a string representation of the date.
         * @return A string representation of the date.
         */
        @Override
        public String toString() {
            return accepted + "   -->   example: " + example;
        }
    }
}
