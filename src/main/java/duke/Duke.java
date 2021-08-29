package duke;

import java.util.Scanner;

public class Duke {

    /**
     * Generates the task manager.
     * @return Task manager object.
     */
    public TaskList generateTaskList() {
        return new TaskList();
    }

    /**
     * The main function that starts off the chatbot.
     * @param args Standard.
     */
    public static void main(String[] args) {
        TaskList taskManager = new Duke().generateTaskList();
        String hello = "Hello! I'm duke.Duke\n"
                + "What can I do for you?";
        System.out.println(hello);
        Scanner scanner = new Scanner(System.in);
        taskManager.run(scanner);
    }
}
