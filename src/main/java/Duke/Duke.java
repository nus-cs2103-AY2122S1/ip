package Duke;

import java.util.Scanner;

public class Duke {

    private TaskList taskList;
    public Duke() {
        this.taskList = new TaskList();
    }

    public void run() {
        taskList.gettingStart();
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        String command = sc.next().trim();

        while (!taskList.isExitCommand(command)) {

            taskList.processCommand(command);
            command = sc.next().trim();
        }

        sc.close();
        taskList.exitProgram();
    }
}
