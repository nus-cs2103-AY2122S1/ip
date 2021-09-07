package pika.ui;

import pika.task.Task;

public class Ui { //IU Class used to handle the interactions with the user.

    /**
     * Calls the goodbye message for Duke.
     *
     * @return Goodbye message
     */
    public static String goodByeMessage() {
        return "Pika...chuuuuu.... Pika is sad that you are leaving";
    }


    /**
     * Calls the message to inform the user that the task has been added successfully.
     *
     * @param taskList List of tasks
     * @return Added message
     */
    public static String addedMessage(TaskList taskList) {
        assert taskList != null : "Pika Pi, this is not valid!";
        return "pika pika! I've added this task:\n"
                + "  " + (taskList.get(taskList.getCount() - 1)) + "\n"
                + "Now you have " + taskList.getCount() + " in the list.";
    }

    /**
     * Prints the list of task for the users.
     *
     * @param taskList Takes in the current list of tasks
     * @return The list of tasks
     */
    public static String printList(TaskList taskList) {
        assert taskList != null : "Pika Pi, this is not valid!";
        return taskList.printList();
    }

    /**
     * Prints the list of task for the users with the pattern in the task name.
     *
     * @param taskList List of tasks
     * @param pattern  pattern to be searched
     * @return List of task with search term in task details
     */
    public static String searchList(TaskList taskList, String pattern) {
        assert pattern != null : "Pika Pi, this is not valid!";
        assert taskList != null : "Pika Pi, this is not valid!";
        return taskList.searchList(pattern);
    }

    /**
     * Prints the done Message to inform the user that the task has been marked as done.
     *
     * @param msg task's message when marked as done
     * @return done Message
     */
    public static String doneMessage(String msg) {
        assert msg != null : "Pika Pi, this is not valid!";
        return "Nice! Pika marked this task as done:\n"
                + "  " + msg;
    }

    /**
     * Prints the deleted message to inform the user that the task has been deleted properly.
     *
     * @param task  Task to be deleted
     * @param index The number of tasks that remains in the list
     * @return Deletion message
     */
    public static String deleteMessage(Task task, int index) {
        assert task != null : "Pika Pi, this is not valid!";
        return "Pika PIIIIII!. Pika removed this task:\n"
                + "  " + task + "\n"
                + "Now you have " + index + " in the list.";
    }

    /**
     * Prints the error to inform the user that their file format is incorrect.
     *
     * @return Error message
     */
    public static String loadingErrorMessage() {
        return "Pika pi!! It seems like your file format is incorrect!";
    }
}
