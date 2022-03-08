package duke.util;

public class Ui {
    /**
     * Introduction message when bot is first launched.
     *
     * @return String representation of the introduction message
     **/
    public static String getIntroMessage() {
        return "Hello! I'm Duke, your personal CLI bot!\n"
                + "I function as a simple TodoList.\n"
                + "I can keep track of 3 types of tasks:\n\n"
                + "1. Todo:"
                + "\n\tTo add a new todo task, enter\n\t'todo <task>'.\n\n"
                + "2. Deadline:"
                + "\n\tTo add a new deadline, enter\n\t'deadline <task> /by \n\t<date-time>'.\n\n"
                + "3. Event:"
                + "\n\tTo add a new event, enter\n\t 'event <event-name> /at \n\t<date-time>'.\n\n"
                + "* For deadline and event tasks, you can specify the time "
                + "by using the format:\n'YYYY-MM-DD' or 'YYYY-MM-DD hhmm',"
                + " where 'hhmm' represents the 24 hour clock.\n"
                + "e.g. event dinner /at 2021-12-26 1830\n\n"
                + "- To see all your tasks, enter 'list'.\n\n"
                + "- To sort task list in chronological order, enter 'sort'\n"
                + "  To reverse order of list, enter 'sort reverse'.\n\n"
                + "- To delete a task, enter\n 'delete <task-id>' e.g. 'delete 2'.\n\n"
                + "- To mark task as done, enter\n 'done <task-id>' e.g. 'done 2'.\n\n"
                + "- Once you are done, just enter 'bye' to quit the program.\n\n"
                + "Have fun! ^_^";
    }

    /**
     * Introduction message when bot is first launched.
     *
     * @return String representation of the introduction message.
     **/
    public static String getExitMessage() {
        return "Goodbyeeee! Hope to see you again soon! :>";
    }
}