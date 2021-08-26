package duke;

import duke.task.Task;

public class Message {
    private static final String HORIZONTAL_LINE =
            "____________________________________________________________";

    private static String formatDukeResponse(String response) {
        return HORIZONTAL_LINE + "\n" + response + "\n" + HORIZONTAL_LINE + "\n";
    }

    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMessage = logo + "Hello! I'm Duke.\n" + "What can I do for you?";
        System.out.println(formatDukeResponse(welcomeMessage));
    }

    public static void printExitMessage() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(formatDukeResponse(exitMessage));
    }

    public static void printAddTaskMessage(Task task, int taskCount) {
        System.out.println(formatDukeResponse("Got it. I've added this task:\n" + task
                + "\nNow you have " + taskCount
                + (taskCount == 1 ? " task " : " tasks ") + "in the list."));
    }

    public static void printTasksMessage(TaskList tasks) {
        System.out.println(formatDukeResponse("Here are the tasks in your list:\n"
                + tasks.toString()));
    }

    public static void printMarkTaskDoneMessage(Task task) {
        System.out.println(formatDukeResponse("Nice! I've marked this task as done:\n" + task));
    }

    public static void printDeleteTaskMessage(Task task, int taskCount) {
        System.out.println(formatDukeResponse("Noted. I've removed this task:\n" + task
                + "\nNow you have " + taskCount
                + (taskCount == 1 ? " task " : " tasks ") + "in the list."));
    }

    public static void printInvalidCommandMessage() {
        System.out.println(formatDukeResponse("Oops!!! I'm sorry, but I don't know what that means."));
    }

    public static void printDukeExceptionMessage(DukeException e) {
        System.out.println(formatDukeResponse(e.getMessage()));
    }

    public static void printTryAgainMessage() {
        System.out.println(formatDukeResponse("Please try again or restart the application."));
    }

}
