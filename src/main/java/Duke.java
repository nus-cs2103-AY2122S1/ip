import java.util.Scanner;

public class Duke {
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static String WelcomeMessage = "   _____________________________________\n"
            + "     Hello! I'm Duke\n"
            + "     What can I do for you?\n"
            + "   _____________________________________\n";

    private static String ByeMessage = "   _____________________________________\n"
            + "     Bye. Hope to see you again soon!\n"
            + "   _____________________________________\n";

    public static void newTask(String taskName, TaskList tList) {
        Task newTask = new Task(taskName);
        tList.addTask(newTask);
        String echoMessage = "   _____________________________________\n"
                        + "     added: " + newTask.showTask() + "\n"
                        + "   _____________________________________\n";
        System.out.println(echoMessage);
    }

    public static void main(String[] args) {
        System.out.println("Hello from\n" + logo + WelcomeMessage);

        TaskList taskList = new TaskList(100);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!(input.equals("bye"))) {
            if (input.equals("list")) {
                taskList.showList();
                input = sc.nextLine();
                continue;
            }

            newTask(input, taskList);
            input = sc.nextLine();
        }

        System.out.println(ByeMessage);
    }
}
