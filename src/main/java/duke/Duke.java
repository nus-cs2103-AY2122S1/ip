package duke;

import java.util.Scanner;

public class Duke {

    public TaskList generateTaskList() {
        return new TaskList();
    }

    public static void main(String[] args) {
        TaskList taskManager = new Duke().generateTaskList();
        String hello = "Hello! I'm duke.Duke\n" +
                "What can I do for you?";
        System.out.println(hello);
        Scanner scanner = new Scanner(System.in);
        taskManager.run(scanner);
    }
}
