package duke.ui;

import duke.tasklist.TaskList;

/**
 * Deals with the interaction with the user.
 */
public class Ui {
    /**
     * Returns the farewell message.
     *
     * @return A string that represents the farewell message.
     */
    public String farewellUser() {
        return "Bye! See you soon!";
    }

    /**
     * Returns the message inputted.
     *
     * @param message the message to be outputted.
     */
    public String returnMessage(String message) {
        return message;
    }

    /**
     * Returns the tasks in the task list.
     *
     * @param taskList the tasks that will be printed.
     * @return String the string representing all the tasks.
     */
    public String returnTasks(TaskList taskList) {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:");
        output.append("\n");
        for (int i = 0; i < taskList.getAllTasks().size(); i++) {
            output.append(String.format("%d. %s%n", i + 1, taskList.getAllTasks().get(i)));
        }
        return output.toString();
    }
}
