package duke;

import duke.tasks.Task;
import duke.tasks.TaskList;

public class UI {

    public static final String LINE = "___________________________________________________\n";

    public void showStartMessage() {
        System.out.println(LINE + "Hello! I'm Duke\n" + "What can I do for you?\n" + LINE);
    }

    public void showExitMessage() {
        System.out.println(LINE + "bye! for now...\n" + LINE);
    }

    public void showAddTaskMessage(Task t, int size) {
        System.out.println(LINE + "I've added this task:\n" + t +"\n" + "You have " + size + " tasks left!\n" + LINE);
    }

    public void showCompleteTaskMessage(Task t) {
        System.out.println(LINE + "Well done! You finally completed " + t.getName() + "!\n" + LINE);
    }

    public void showDeleteTaskMessage(Task t, int size) {
        System.out.println(LINE + "The task has been removed:\n" + t +
                "\n" + "You have " + size + " tasks left!\n" + LINE);
    }

    public void getListMessage(TaskList tasklist) {
        String message = "Here are your tasks... if you choose to do it...\n";
        String listMessage = tasklist.getAllTasks();
        System.out.println(LINE + message + listMessage + LINE);
    }

    //TODO
    public void showListOfCommands() {
        System.out.println(LINE + "Unknown Command!\n" + LINE);
    }

    public void showErrorMessage(String errorMessage) {
        System.out.println(LINE + errorMessage + "\n" + LINE);
    }
}
