import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

public class Duke {
    // CONSTANTS
    private static final String PATH = "src/main/data";
    private static final String FILENAME = "ponyo.txt";

    static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String logo = "    ____    ____    ____    __  __   ____ \n" +
                "   / __ \\  / __ \\  / __ \\  / / / /  / __ \\\n" +
                "  / /_/ / / /_/ / / / / / / /_/ /  / /_/ /\n" +
                " / .___/  \\____/ /_/ /_/  \\__, /   \\____/ \n" +
                "/_/                      /____/           \n";

        System.out.println(logo);
        System.out.println("Hello! I'm Ponyo.\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n");

        readTasksFromFile(PATH + "/" + FILENAME);
        loopCmds();
    }

    public static void loopCmds() {
        Scanner scan = new Scanner(System.in);

        while (true) {
            String cmd = scan.nextLine();
            System.out.println("\t____________________________________________________________\n");
            String[] cmds = cmd.split(" ", 2);

            try {
                switch (cmds[0]) {
                    case "list":
                        System.out.println(list(""));
                        System.out.println("\t____________________________________________________________\n");
                        break;
                    case "bye":
                        System.out.println("\tBye. Hope to see you again soon!");
                        System.out.println("\t____________________________________________________________\n");
                        break;
                    case "done":
                        System.out.println(toMarkAsDone(cmd));
                        System.out.println("\t____________________________________________________________\n");
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        System.out.println(addTasks(cmds));
                        System.out.println("\t____________________________________________________________\n");
                        break;
                    case "delete":
                        System.out.println(deleteTask(Integer.parseInt(cmds[1])));
                        System.out.println("\t____________________________________________________________\n");
                        break;
                    default:
                        throw new DukeException("Invalid command given!");
                }
            } catch (DukeException e) {
                System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("\t____________________________________________________________\n");
            }

            if (cmd.equals("bye")) {
                scan.close();
                break;
            }
        }
    }

    public static String list(String toPrint) {
        for (int i = 0; i < tasks.size(); i++) {
            toPrint += "\t" + (i + 1) + "." + tasks.get(i);
            if (i != tasks.size() - 1)
                toPrint += "\n";
        }
        return toPrint;
    }

    public static String toMarkAsDone(String cmd) throws DukeException {
        int toMark = Integer.parseInt(cmd.substring(5)) - 1;
        tasks.get(toMark).markAsDone();
        getFullContents();
        return "\tNice! I've marked this task as done: \n\t\t" + tasks.get(toMark);
    }

    public static String addTasks(String[] cmds) throws DukeException {
        switch (cmds[0]) {
            case "todo":
                try {
                    Task t = new Todo(cmds[1]);
                    tasks.add(t);
                    fileLineToWrite(t);
                    return printTask(t);
                } catch (ArrayIndexOutOfBoundsException e){
                    return "\t☹ OOPS!!! The description of a todo cannot be empty.";
                }
            case "deadline":
                try {
                    int slashIndex = cmds[1].indexOf("/");
                    Task t = new Deadline(cmds[1].substring(0, slashIndex), cmds[1].substring(slashIndex + 4));
                    tasks.add(t);
                    fileLineToWrite(t);
                    return printTask(t);
                } catch (ArrayIndexOutOfBoundsException e){
                    return "\t☹ OOPS!!! The description of a deadline cannot be empty.";
                }
            case "event":
                try {
                    int slashIndex = cmds[1].indexOf("/");
                    Task t = new Event(cmds[1].substring(0, slashIndex), cmds[1].substring(slashIndex + 4));
                    tasks.add(t);
                    fileLineToWrite(t);
                    return printTask(t);
                } catch (ArrayIndexOutOfBoundsException e){
                    return "\t☹ OOPS!!! The description of an event cannot be empty.";
                }
        }
        return "";
    }

    public static String printTask(Task task) {
        return "\tGot it. I've added this task: \n\t\t" +
                task +
                "\n\tNow you have " + tasks.size() + " tasks in the list.";
    }

    public static String deleteTask(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        getFullContents();
        return "\tNoted. I've removed this task: \n\t\t" +
                task +
                "\n\tNow you have " + tasks.size() + " tasks in the list.";
    }

    // FILE OPERATIONS ==============================================================
    // Check if file and folder exists
    public static void fileFolderCheck(String filePath) throws IOException {
        File file = new File(filePath);
        File directory = new File(PATH);

        if (!directory.exists()) {
            directory.mkdir();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public static void readTasksFromFile(String filePath) throws IOException {

        fileFolderCheck(filePath);
        Scanner read = new Scanner(new File(filePath));
        read.useDelimiter(Pattern.compile("(\\n)| - "));

        while (read.hasNext()) {
            String taskCode = read.next();

            switch (taskCode) {
                case "T":
                    int marked = Integer.parseInt(read.next());
                    String description = read.next();
                    Task t = new Todo(description);
                    if (marked == 1) {
                        t.markAsDone();
                    }

                    tasks.add(t);
                    break;
                case "D":
                    marked = Integer.parseInt(read.next());
                    description = read.next();
                    String by = read.next();
                    t = new Deadline(description, by);
                    if (marked == 1) {
                        t.markAsDone();
                    }

                    tasks.add(t);
                    break;
                case "E":
                    marked = Integer.parseInt(read.next());
                    description = read.next();
                    String at = read.next();
                    t = new Event(description, at);
                    if (marked == 1) {
                        t.markAsDone();
                    }

                    tasks.add(t);
                    break;
            }
        }
        read.close();
    }

    public static void fileLineToWrite(Task t) {
        try {
            appendToFile(PATH + "/" + FILENAME, t.toStringInFile() + "\n");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void getFullContents() {
        try {
            String allContent = "";
            for (Task t : tasks) {
                allContent += t.toStringInFile() + "\n";
            }
            overwriteFile(PATH + "/" + FILENAME, allContent);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        fileFolderCheck(filePath);
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static void overwriteFile(String filePath, String fileContent) throws IOException {
        fileFolderCheck(filePath);
        FileWriter fw = new FileWriter(filePath); // create a FileWriter
        fw.write(fileContent);
        fw.close();
    }
}
