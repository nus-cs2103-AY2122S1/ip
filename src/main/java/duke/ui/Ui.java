package duke.ui;

import duke.taskList.TaskList;

/**
 * Represents an Ui class that is responsible for the interaction with user.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class Ui {

    /**
     * Shows a welcome message from chat bot.
     */
    public static void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Duke: Hello from\n" + logo);
        System.out.println("Duke: Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Shows a goodbye message when user enter exit command.
     *
     * @return String of the goodbye message.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    public String showAddTodo(String addedTask, String size) {
        return "Got it. I've added this task: \n" + addedTask + "\nNow you have "
                + size + " tasks in the list.";
    }

    public String showAddDeadline(String addedTask, String size) {
        return "Got it. I've added this task: \n" + addedTask + "\nNow you have "
                + size + " tasks in the list.";
    }

    public String showAddEvent(String addedTask, String size) {
        return "Got it. I've added this task: \n" + addedTask + "\nNow you have "
                + size + " tasks in the list.";
    }

    public String showDelete(String deletedTask, String size) {
        return "Noted. I've removed this task: \n" + deletedTask
                + "\nNow you have " + size + " tasks in the list.";
    }

    public String showMatchList(String keyword, TaskList tasks) {
        String result = "Here are the matching tasks in your list:";
        int count = 1;
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i).contains(keyword)) {
                result += "\n" + count + "." + tasks.getTask(i);
                count += 1;
            }
        }
        return result;
    }

    public String showList(TaskList tasks) {
        int count = 1;
        String str = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.getSize(); i++) {
            str += "\n" + count + "." + tasks.getTask(i);
            count += 1;
        }
        return str;
    }
}