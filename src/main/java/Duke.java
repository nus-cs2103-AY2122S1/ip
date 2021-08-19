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
        String input = scanner.nextLine();

        Tasks tasks = new Tasks();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                if (tasks.isEmpty()) {
                    System.out.println("\tYou haven't added any tasks yet\n");
                } else {
                    System.out.println(tasks);
                }
            } else {
                try {
                    String[] commandAndDesc = input.split(" ", 2);
                    String command = commandAndDesc[0];
                    String description = commandAndDesc.length == 2 ? commandAndDesc[1] : "" ;

                    if (command.equals("deadline")) {
                        tasks.addTask(new Deadline(description));
                    } else if (command.equals("event")) {
                        tasks.addTask(new Event(description));
                    } else if (command.equals("todo")) {
                        tasks.addTask(new ToDo(description));
                    } else if (command.equals("done")) {
                        try {
                            Integer taskNum = Integer.valueOf(description);
                            Task toMark = tasks.getTask(taskNum);
                            if (toMark == null) {
                                System.out.println("\tSorry, I can't seem to find that task\n");
                            } else {
                                toMark.markAsDone();
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("\tI'm Sorry, WHAT?!?!\n");
                        }
                    } else {
                        if (command.equals("")) {
                            System.out.println("\tTake your time :)\n");
                        } else {
                            System.out.println("\tI don't understand that command (yet...)\n");
                        }
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
            input = scanner.nextLine();
        }
        scanner.close();

    }

}
