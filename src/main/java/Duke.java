import commands.Command;
import tasks.TaskList;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            Command command = Command.of(userInput);
            command.updateLogAndTaskList(taskList);
            taskList = command.getTaskList();
            System.out.println(command.getLog());
        }
        sc.close();
    }
}