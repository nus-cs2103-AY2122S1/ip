import commands.Command;
import tasks.TaskList;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();

        while (true) {
            String userInput = sc.nextLine();
            Command command = Command.of(userInput);
            if (command.isExit()) {
                break;
            }
            command.updateLogAndTaskList(taskList);
            taskList = command.getTaskList();
            System.out.println(command.getLog());
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}