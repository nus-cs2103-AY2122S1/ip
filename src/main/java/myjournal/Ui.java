package myjournal;

import myjournal.task.Task;

public class Ui {
    public void welcomeMessage() {
        System.out.println("Hello!\n"
                + "1. Type a task (todo/event/deadline) to be added into your task list.\n"
                + "2. Type 'list' if you want to generate your task list.\n"
                + "3. Type 'done [number]' to mark a task as done.\n"
                + "4. Type 'delete [number]' to delete a task.\n"
                + "5. Type 'bye' to exit");
    }

    /**
     * Prints out the statement after a task is added.
     *
     * @param taskList The list of all tasks.
     * @return The statement to be printed after a task is added.
     */
    public void taskAddPrint(TaskList taskList) {
        System.out.println("Okay!! I've added the following task:\n"
                + taskList.getTask(taskList.getSize() - 1) + "\n"
                + "Now you have " + taskList.getSize() + " in the list");
    }

    public void removeTaskPrint(Task task) {
        System.out.println("Okay!! I have removed the following task:\n"
                + task);
    }

    public void doneTaskPrint(Task task) {
        System.out.println("Okay!! I have marked this task as done:\n" + task);
    }
    public void goodByeMessage() {
        System.out.println("Bye. Hope to see you again soon!:)");
    }
}
