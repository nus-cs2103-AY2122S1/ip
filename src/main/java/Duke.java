import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void printTasks() {
        if (taskList.size() == 0) {
            System.out.println("You have no tasks!");
        } else {
            System.out.println("Here are your tasks!");
            for (int i = 0; i<taskList.size(); i++) {
                String taskMark = taskList.get(i).getStatusIcon();
                String taskDesc = taskList.get(i).getDescription();
                System.out.printf("%d.[%s] %s%n", i + 1, taskMark, taskDesc);
            }
        }
    }

    public static void addTask(String input) {
        Task newTask = new Task(input);
        taskList.add(newTask);
        System.out.println("Added: " + input);
    }

    public static void markTask(int index) {
        Task task = taskList.get(index - 1);
        task.markDone();
        System.out.println("Okay! This task has been marked done:");
        System.out.printf("[%s] %s%n", task.getStatusIcon(), task.getDescription());
    }

    public static int handleInput() {
        Scanner scanner = new Scanner(System.in);
        String input;
        do{
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("BYE")) {
                System.out.println("Bai bai!");
            } else if (input.equalsIgnoreCase("LIST")) {
                printTasks();
            } else if (input.length() >=4 && input.substring(0,4).equalsIgnoreCase("DONE")) {
                int index = Integer.parseInt(input.substring(5));
                markTask(index);
            } else if (input.length() >=3 && input.substring(0,3).equalsIgnoreCase("ADD")) {
                String task = input.substring(4);
                addTask(task);
            } else {
                System.out.println(input);
            }
        }
        while (!input.equalsIgnoreCase("BYE"));
        return 0;
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";
        String greeting = "Hello! I'm Duke.\n" + "What can I do for you? :)";
        String usage = "Usage:\n" +
                "list               - show current tasks\n" +
                "add [Task]         - add a task\n" +
                "done [Task Number] - mark task as done\n" +
                "bye                - say goodbye";
        System.out.println(logo + greeting);
        System.out.println(usage);

        handleInput();
    }

}
