import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private final static String welcome_default = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final static String line = "\t----------------------------------------------------\n";
    private final static String welcome_changed = line + "\t" + "Hewwo fweind, I am fuwwy, your personal assitant,\n" +
            "\t" + "How can I help you?\n" + line;
    private static ArrayList<Task> tasklist;
    private Storage storage;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasklist = new ArrayList(storage.load());
        } catch (DukeException e) {
            tasklist = new ArrayList();
        }
    }

    private static void fuwwyEcho(String echo) {
        System.out.println(line + "\t" + echo + "\n" + line);
    }

    private static void addTask(Task task) {
        tasklist.add(task);
        fuwwyEcho("Uwu added:\t"
                + task);
    }

    private static void deleteTask(int num) {
        int id = num - 1;
        Task delete = tasklist.get(id);
        tasklist.remove(id);
        fuwwyEcho("Ono, this task got yeeted:\n\t" + delete + "\n\tNow you have "
                + tasklist.size() + " tasks in the list, Yay!\n");
    }

    private static void taskDone(int n) {
        Task t = tasklist.get(n - 1);
        t.setDone(true);
        fuwwyEcho("Uwu! I've marked this task as done:\n\t" + t + "\n");
    }

    private static void eorD(String command, String task) throws DukeException {


        String keyword1 = task == "deadline" ? "/by " : "/at ";
        String[] keyword2 = command.split(keyword1);

        if (keyword2.length <= 1) {
            throw new DukeException("OwO Incomplete command, add time" + "\n");
        }

        String t = keyword2[0].split(task)[1];
        String time = keyword2[1];

        if (task == "deadline") {
            addTask(new Deadline(t, time));
        } else {
            addTask(new Event(t, time));
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").main2();
    }

    public void main2() {
        System.out.println(welcome_changed);
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            try {
                if (command.equals("list")) {
                    String output = "\n";
                    if (tasklist.size() == 0) {
                        fuwwyEcho("Owo the list is empty boouwu");
                    } else {
                        for (int i = 0; i < tasklist.size(); i++) {
                            int n = i + 1;
                            String list = "\t" + n + ". " + tasklist.get(i);
                            output += list + "\n";
                        }
                        fuwwyEcho(output);
                    }
                    command = sc.nextLine();
                } else if (command.startsWith("done")) {
                    if (command.equals("done")) {
                        throw new DukeException("OwO incomplete done!\n");
                    }
                    taskDone(Integer.parseInt(command.split(" ")[1]));
                } else if (command.startsWith("delete")) {
                    if (command.equals("delete")) {
                        throw new DukeException("OwO incomplete delete!\n");
                    }
                    deleteTask(Integer.parseInt(command.split(" ")[1]));
                } else if (command.startsWith("todo")) {
                    if (command.equals("todo")) {
                        throw new DukeException("OwO incomplete todo!\n");
                    } else {
                        addTask(new ToDo(command.split("todo")[1]));
                    }
                } else if (command.startsWith("deadline")) {
                    eorD(command, "deadline");
                } else if (command.startsWith("event")) {
                    eorD(command, "event");
                } else {
                    throw new DukeException("UwU oops, I don't understand\n");
                }
                storage.write(tasklist);
            } catch (DukeException e) {
                fuwwyEcho(e.getMessage());
            }

            command = sc.nextLine();
        }

        fuwwyEcho("Bye. Hope to see you again soon!\n");
        sc.close();
    }
}
