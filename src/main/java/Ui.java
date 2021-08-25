import java.util.Scanner;

import tasks.TaskList;

public class Ui {
    //deals with interactions with the user
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
