/**
 * Stores commands, descriptors and responses used by Duke chatbot.
 */
public class Ui {
    // Enums for Duke chatbot descriptors
    protected enum Descriptors {
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

    // Enums for Duke chatbot commands
    protected enum Commands {
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
}
