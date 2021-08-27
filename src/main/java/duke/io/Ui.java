package duke.io;

import duke.data.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ui {
    public Ui() {
        final String welcomeMessage = "Hello! I'm duke.\nWhat can I do for you?";

        System.out.println("    ____________________________________________________________\n    "
                + welcomeMessage.replace("\n", "\n    ")
                + "\n    ____________________________________________________________");
    }

    /**
     * Sends a message to the user in the specified format
     *
     * @param message The message to be sent to the user
     */
    public void printMessage(String message) {
        System.out.println("    ____________________________________________________________\n    "
                + message.replace("\n", "\n    ")
                + "\n    ____________________________________________________________");
    }

    public void showLoadingError() {
        printMessage("Error! Cannot load tasks from save file!");
    }

    public void printTaskList(TaskList taskList) {
        // Construct the string containing the list of items that have been stored in
        // preparation to send to user
        StringBuilder listMessage = new StringBuilder("Here are the tasks in your list:");

        // Add all elements in the list
        for (int i = 0; i < taskList.size(); ++i) {
            listMessage.append("\n").append(i + 1).append(". ").append(taskList.get(i));
        }

        printMessage(listMessage.toString());
    }

    public void printTaskList(TaskList taskList, LocalDate date) {
        // Construct the string containing the list of items that have been stored in
        // preparation to send to user
        StringBuilder listMessage = new StringBuilder("Here are the tasks in your list that are due on ")
                .append(date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))).append(":");

        // Add all elements in the list
        for (int i = 0; i < taskList.size(); ++i) {
            listMessage.append("\n").append(i + 1).append(". ").append(taskList.get(i));
        }

        printMessage(listMessage.toString());
    }
}
