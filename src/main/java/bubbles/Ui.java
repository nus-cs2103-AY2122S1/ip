package bubbles;

import java.util.Scanner;

import bubbles.tasks.TaskList;

/**
 * A class that deals with interactions with the user,
 * mainly accepting user input and echoing back to the user
 * through System output.
 */
public class Ui {
    /**
     * Echos the user input through printing out the input
     * String onto the System output line.
     */
    public void echo() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                String farewell = "Bye-bye! Hope to see you again soon!";

                formatting(farewell);
                break;
            } else {
                formatting(input);
            }
        }

        sc.close();
    }

    /**
     * Echos the (accepted forms of) user input after formatting,
     * where the accepted commands are to add ToDo/Deadline/Event tasks,
     * marking tasks as done, deleting tasks, listing out all of the
     * tasks and saying good-bye.
     *
     * @param storage The Storage Object where the final list of tasks
     *                would be updated to, after the Bubbles bot program
     *                terminates
     */
    public void echoTask(Storage storage) {
        Scanner sc = new Scanner(System.in);

        TaskList taskList = storage.getTaskList();

        while (sc.hasNext()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                storage.writeTasks();

                String farewell = "Bye-bye! Hope to see you again soon!";

                formatting(farewell);
                break;
            } else {
                taskList.taskListener(input);
            }
        }
    }

    private static void formatting(String str) {
        String separator = "-----------------------------------------------------------------";

        System.out.println(separator + "\n"
                + str + "\n"
                + separator);
    }
}
