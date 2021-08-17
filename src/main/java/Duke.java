import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        String description = scanner.nextLine();

        Tasks tasks = new Tasks();

        while (!description.equals("bye")) {
            if (description.equals("list")) {
                System.out.println(tasks);
            } else {
                if (description.matches(".* by .*")) {
                    tasks.addTask(new Deadline(description));
                } else if (description.matches(".* at .*")) {
                    tasks.addTask(new Event(description));
                } else {
                    if (description.matches("done (\\d+)$")) {
                        Integer taskNum = Integer.valueOf(description.split(" ")[1]);
                        Task toMark = tasks.getTask(taskNum);
                        if (toMark == null) {
                            tasks.addTask(new ToDo(description));
                        } else {
                            toMark.markAsDone();
                        }
                    } else {
                        tasks.addTask(new ToDo(description));
                    }
                }
            }
            description = scanner.nextLine();
        }

        scanner.close();
    }

}
