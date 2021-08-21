import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

public class Duke {

    private static ArrayList<Task> taskList;
    private static Storage storage = new Storage("data/duke.txt");

    public static void main(String[] args) {
        taskList = storage.readFromDisk();

        String sectionBreak = "------------------------------------------";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("\nHello buddy! I am");
        System.out.println(logo);
        System.out.println("What can I do for you?");
        System.out.println(sectionBreak);

        Scanner sc=new Scanner(System.in);

        Hashtable<String, Consumer<String>> commandTable = new Hashtable<>();
        commandTable.put("list", (x) -> printTaskList());
        commandTable.put("event", (x) -> AddTask(x, Event::create));
        commandTable.put("deadline", (x) -> AddTask(x, Deadline::create));
        commandTable.put("todo", (x) -> AddTask(x, ToDo::create));
        commandTable.put("done", Duke::MarkTask);
        commandTable.put("delete", Duke::DeleteTask);


        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) break;

            String keyword = input.split(" ", 2)[0];
            try {
                if (commandTable.containsKey(keyword))
                    commandTable.get(keyword).accept(input);
                else
                    throw new DukeException("I'm not sure what you mean");
            } catch (DukeException e) {
                System.out.println(e);
            }
            System.out.println(sectionBreak);
        }

        System.out.println("Bye. Hope to see you soon!");
        System.out.println(sectionBreak);
    }

    private static void AddTask(String formattedString, Function<String, ? extends Task> create) {
        Task e = create.apply(formattedString);
        taskList.add(e);
        System.out.printf("added: %s\n", e);
        storage.writeToDisk(taskList);
    }

    private static void printTaskList() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, taskList.get(i));
        }
    }

    private static int getIdFromString(String input, String prefix) throws DukeException {
        String idString = "";
        try {
            if (!input.startsWith(prefix) || input.length() <= prefix.length())
                throw new DukeException("you did not specify a task id");

            idString = input.substring(prefix.length());
            return Integer.parseInt(idString);

        } catch (NumberFormatException e) {
            String msg = String.format("\"%s\" is not a valid integer", idString);
            throw new DukeException(msg);
        }
    }

    private static void MarkTask(String input) {
        int taskId = -1;
        try {
            taskId = getIdFromString(input, "done ");
            Task t = taskList.get(taskId - 1);
            t.markAsDone();
            System.out.println("Cool, I've marked this task as done\n" + t);
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("Oops, Task #%d doesn't exist\n", taskId);
        }
        storage.writeToDisk(taskList);
    }

    private static void DeleteTask(String input) {
        int taskId = -1;
        try {
            taskId = getIdFromString(input, "delete ");
            Task t = taskList.get(taskId - 1);
            taskList.remove(taskId - 1);
            System.out.println("Okay, I've removed this task\n" + t);
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("Oops, Task #%d doesn't exist\n", taskId);
        }
        storage.writeToDisk(taskList);
    }
}