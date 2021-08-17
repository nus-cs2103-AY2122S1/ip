import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {

    private void addTask(String taskName) {

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        String taskName = scanner.nextLine();

        Tasks tasks = new Tasks();

        while (!taskName.equals("bye")) {
            if (taskName.equals("list")) {
                System.out.println(tasks);
            } else {
                if (taskName.matches("done (\\d+)$")) {
                    Integer taskNum = Integer.valueOf(taskName.split(" ")[1]);
                    Task toMark = tasks.getTask(taskNum);
                    if (toMark == null) {
                        tasks.addTask(taskName);
                    } else {
                        toMark.markAsDone();
                    }
                } else {
                    tasks.addTask(taskName);
                }
            }
            taskName = scanner.nextLine();
        }

        scanner.close();
    }

}
