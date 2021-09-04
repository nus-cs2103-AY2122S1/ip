package duke;

import duke.task.Task;

/**
 * Stores commands, descriptors, responses and error messages used by duke.Duke chatbot.
 */
public class Ui {
    /** Enums for Duke chatbot descriptors */
    public enum Descriptors {
        AT("at"),
        BY("by"),
        WITHIN("within");

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
    public enum UserCommands {
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DONE("done"),
        DELETE("delete"),
        LIST("list"),
        DATE("date"),
        FIND("find"),
        BYE("bye"),
        PERIOD("period");

        private final String command;

        UserCommands(String command) {
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
     * @param periods Total count of Periods with time periods within which the date being searched for falls within.
     * @param foundTasks String accumulation of matching tasks.
     * @return Standard response for success in finding tasks by date.
     */
    public String getDateListSuccessMessage(String formattedDateString,
            int counter, int deadlines, int events, int periods,
            String foundTasks) {
        String start = "Here are the Deadlines, Events or Periods that fall on " + formattedDateString + ":\n";
        String end = "A total of "
                + counter + " tasks ("
                + deadlines + " deadlines, "
                + events + " events and "
                + periods + " periods) fall on "
                + formattedDateString;
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
}
