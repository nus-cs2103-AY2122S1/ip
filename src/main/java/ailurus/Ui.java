package ailurus;

import java.util.ArrayList;

import ailurus.task.Task;
import ailurus.task.TaskList;

public class Ui {
    private final String chatbot = "Ailurus";

    /**
     * Welcomes user to use the chatbot
     *
     * @return welcome message string
     */
    public String showWelcome() {
        return this.say(String.format("Hello! I'm %s. These are your available commands:\n"
                + "todo <description>\n\t- add todo task\n"
                + "deadline <description> /by <yyyy-mm-dd>\n\t- add deadline task with date\n"
                + "event <description> /at <yyyy-mm-dd>\n\t- add event task with date\n"
                + "list\n\t- list all tasks and their task number\n"
                + "done <task number(s)>\n\t- mark task(s) as done (e.g. done 1 2 3)\n"
                + "delete <task number(s)>\n\t- delete task(s) (e.g. delete 1 2 3)\n"
                + "find <matching word>\n\t- find all tasks with matching word", this.chatbot));
    }

    /**
     * Show user the error
     *
     * @param errorMessage Error message to be shown to user
     * @return error message to be said
     */
    public String showError(String errorMessage) {
        return this.say(errorMessage);
    }

    /**
     * Customized display for chatbot messages
     *
     * @param message display message to be printed
     * @return message to be said
     */
    public String say(String message) {
        System.out.println(String.format("%s: %s", this.chatbot, message));
        return message;
    }


    /**
     * Printing out of the list of tasks
     *
     * @param list TaskList to be said by chatbot
     * @return list of tasks as string
     */
    public String sayList(TaskList list) {
        if (list.length() == 0) {
            throw new AilurusException(AilurusException.Error.EMPTYLIST);
        }
        this.say("");
        String message = "";
        for (int i = 0; i < list.length(); i++) {
            message += String.format("%d.%s\n", i + 1, list.getIndexString(i));

        }
        System.out.println(message);
        return message;
    }

    /**
     * Tell the user that the tasks are marked as done
     *
     * @param tasks tasks that are marked as done
     * @return message that tasks are done
     */
    public String sayDone(ArrayList<Task> tasks) {
        String tasksString = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksString += tasks.get(i) + "\n\t";
        }
        return this.say(String.format("Nice! I've marked this task as done:\n\t%s", tasksString));
    }

    /**
     * Tell the user that the task is added
     *
     * @param task task that is added
     * @param size size of task list
     * @return message for adding task
     */
    public String sayAdd(Task task, int size) {
        return this.say(String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                task.toString(), size));
    }

    /**
     * Tell the user that the tasks are deleted
     *
     * @param tasks tasks that are deleted
     * @param size size of task list
     * @return message for deleting tasks
     */
    public String sayDelete(ArrayList<Task> tasks, int size) {
        String tasksString = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksString += tasks.get(i) + "\n\t";
        }
        return this.say(String.format("Noted. I've removed this task:\n\t%s\nNow you have %d tasks in the list.",
                tasksString, size));
    }

    /**
     * Tell the user that there are matching tasks, and the list of them
     *
     * @param tasks list of tasks that are matching
     * @return message for finding task
     */
    public String sayFind(TaskList tasks) {
        String header = this.say("Here are the matching tasks in your list:");
        String list = this.sayList(tasks);
        return header + "\n" + list;
    }

    /**
     * Tell the user that the command given was invalid
     *
     * @return message for invalid command
     */
    public String sayInvalidCommand() {
        return this.say("(!) OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Tell the user that it is the end of chatting
     *
     * @return message for exiting
     */
    public String sayBye() {
        return this.say("Bye. Hope to see you again soon!");
    }

}
