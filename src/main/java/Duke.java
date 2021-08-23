import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {

    private static final String FILE_PATH = "data/duke.txt";

    public static void main(String[] args) throws IOException {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke :)\nWhat can I do for you?");
        for (int i = 0; i < 60; i++) {
            System.out.print("_");
        }
        System.out.println();

        File f = new File(FILE_PATH);
        if (f.exists()) {
            System.out.println("Here's your previous data:");
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String next = sc.nextLine();
                System.out.println(next);
            }
            sc.close();
            for (int i = 0; i < 60; i++) {
                System.out.print("_");
            }
            System.out.println();
        } else {
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdir();
            }
        }
        f.createNewFile();

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String input = in.nextLine().strip();
            String[] x = input.split(" ");
            String cmd = x[0];
            if (x.length == 1) {
                if ("bye".equals(cmd)) {
                    System.out.println("Bye. See you again soon! :)");
                    break;
                } else if ("list".equals(cmd)) {
                    Task.listAllTasks();
                } else if ("todo".equals(cmd) || "deadline".equals(cmd) || "event".equals(cmd)) {
                    missingTaskName(cmd);
                } else if ("done".equals(cmd) || "delete".equals(cmd)) {
                    System.out.println("Indicate a task number beside the command ☻");
                } else {
                    System.out.println("☹︎wut☁︎☻ unknown command");
                }
            } else {
                if (cmd.equals("done")) {
                    if (x.length > 2) {
                        System.out.println("Too many arguments for this command.");
                        continue;
                    }
                    Task.taskDone(getTaskNumber(x));
                } else if (cmd.equals("delete")) {
                    if (x.length > 2) {
                        System.out.println("Too many arguments for this command.");
                        continue;
                    }
                    Task.deleteTask(getTaskNumber(x));
                } else if (cmd.equals("todo")) {
                    addToDo(input);
                } else if (cmd.equals("deadline")) {
                    addDeadline(input);
                } else if (cmd.equals("event")) {
                    addEvent(input);
                } else {
                    // unknown command received
                    System.out.println("☹︎wut☁︎☻ unknown command");
                }
            }
        }
        in.close();
        writeToFile();
    }

    public static void addToDo(String input) {
        String name = input.substring(input.indexOf(" ") + 1);
        ToDo t = new ToDo(name);
        Task.addTask(t);
    }

    public static void addDeadline(String input) {
        try {
            String name = input.substring(input.indexOf(" ") + 1, input.lastIndexOf("/by") - 1);
            String by = input.substring(input.lastIndexOf("/by") + 4);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            Deadline d = new Deadline(name, LocalDateTime.parse(by, formatter));
            Task.addTask(d);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date and time input, indicate date in yyyy-MM-dd HH:mm format.");
        }
    }

    public static void addEvent(String input) throws DateTimeParseException {
        try {
            String name = input.substring(input.indexOf(" ") + 1, input.lastIndexOf("/at") - 1);
            String at = input.substring(input.lastIndexOf("/at") + 4);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            Event e = new Event(name, LocalDateTime.parse(at, formatter));
            Task.addTask(e);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date and time input, indicate date in yyyy-MM-dd HH:mm format.");
        }
    }

    public static void missingTaskName(String cmd) {
        String str = String.format("☹ OOPS!!! The description of a %s cannot be empty.", cmd);
        System.out.println(str);
    }

    public static int getTaskNumber(String[] inputArr) {
        return Integer.parseInt(inputArr[1]) - 1;
    }

    public static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task t : Task.listOfTasks) {
            fw.write(t.getRecordString() + "\n");
        }
        fw.close();
    }
}
