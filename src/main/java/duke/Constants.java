package duke;

public class Constants {
    /**
     * Available commands
     */
    public enum Commands {
        list        ("", "Lists all the tasks."),
        todo        ("[description]", "Adds a todo task."),
        deadline    ("[description] /by [dd-MM-yyyy] [*optional hh:mm]", "Adds a task with a deadline"),
        event       ("[description] /at [dd-MM-yyyy] [*optional hh:mm]", "Adds an event to the task"),
        delete      ("[index]", "Removes a task from the task list"),
        done        ("[index]", "Marks a task as done"),
        find        ("[keyword]", "Find a task by searching for a keyword"),
        help        ("", "Shows all the commands available"),
        dates       ("", "Shows all the available date and time type"),
        bye         ("", "Quit the app");


        private final String arguments;
        private final String description;

        Commands(String arguments, String description) {
            this.arguments = arguments;
            this.description = description;
        }

        @Override
        public String toString() {
            return this.name() + " " + arguments + "   -->   " + description;
        }
    }

    /**
     * Accepted Dates
     */
    public enum Dates {

        today       ("today", "today"),
        tomorrow    ("tomorrow", "tomorrow"),
        ydash       ("yyyy-MM-dd", "2021-12-25"),
        yslash      ("yyyy/MM/dd", "2021/12/25"),
        ddash       ("dd-MM-yyyy", "25-12-2021"),
        dslash      ("dd/MM/yyyy", "25/12/2021"),
        dtimedot    ("[date] hh:mm", "[date] 18:00"),
        dtimeblank    ("[date] hhmm", "[date] 1800");

        private final String accepted;
        private final String example;

        Dates(String accepted, String example) {
            this.accepted = accepted;
            this.example = example;
        }

        @Override
        public String toString() {
            return accepted + "   -->   example: " + example;
        }
    }
}
