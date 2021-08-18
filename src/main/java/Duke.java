import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private final int MAX_TASKS = 100;
    private static List<Task> taskList = new ArrayList<>();

    private static String addTask(String taskName) {
        if (taskList.size() < 100) {
            taskList.add(new Task(taskName));
            return "added: " + taskName;
        } else {
            return "Sorry! You have max number of tasks stored already.";
        }
    }

    private static String printTaskList() {
        int listLen = taskList.size();
        String msg = "";
        for (int i = 0; i < listLen; i++) {
            int currTaskNum = i + 1;
            msg = msg + (currTaskNum + ". " + taskList.get(i).toString() + "\n");
        }
        return msg;
    }

    private static String markTaskDone(int taskNum) {
        Task taskToMark = taskList.get(taskNum);
        taskToMark.markDone();
        return "Nice! I've marked this task as done: " + "\n  " + taskToMark.toString();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        System.out.println("Duke is gone. Hello, this is Duchess.\nHow can I help you?");

        while (true) {
            String user_input = sc.nextLine();
            String command = user_input.split(" ")[0];
            if (command.equals("bye")) {
                System.out.println("It has been a pleasure, goodbye!");
                sc.close();
                break;
            } else if (command.equals("list")) {
                System.out.println((printTaskList()));
            } else if (command.equals("done")) {
                int taskNum = Integer.parseInt(user_input.split(" ")[1]) - 1;
                System.out.println(markTaskDone(taskNum));
            } else {
                System.out.println(addTask(user_input));
            }
        }
    }
}
