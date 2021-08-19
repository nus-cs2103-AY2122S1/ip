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
    private static ArrayList<Task> tasklist = new ArrayList<>();

    private static void fuwwyEcho(String echo) {
        System.out.println(line + "\t" + echo + "\n" + line);
    }

    private static void addTask(Task task) {
        tasklist.add(task);
        fuwwyEcho("Uwu added:\t"
                + task);
    }

    private static void taskDone(int n) {
        Task t = tasklist.get(n - 1);
        t.setDone(true);
        fuwwyEcho("Uwu! I've marked this task as done:\n\t" + t + "\n");
    }

    private static void eorD(String command, String task) {
        String keyword1 = task == "deadline" ? "/by " : "/at ";
        String[] keyword2 = command.split(keyword1);


        String t = keyword2[0].split(task)[1];
        String time = keyword2[1];

        if (task == "deadline") {
            addTask(new Deadline(t, time));
        } else {
            addTask(new Event(t, time));
        }
    }

    public static void main(String[] args) {
        System.out.println(welcome_changed);
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                String output = "\n";
                for (int i = 0; i < tasklist.size(); i++) {
                    int n = i + 1;
                    String list = "\t" + n + ". " + tasklist.get(i);
                    output += list + "\n";
                }
                fuwwyEcho(output);
                command = sc.nextLine();
            } else if (command.startsWith("done")){
                taskDone(Integer.parseInt(command.split(" ")[1]));
                command = sc.nextLine();
            } else if (command.startsWith("todo")) {
                addTask(new ToDo(command.split("todo")[1]));
                command = sc.nextLine();
            } else if (command.startsWith("deadline")) {
                eorD(command, "deadline");
                command = sc.nextLine();
            } else if (command.startsWith("event")) {
                eorD(command, "event");
                command = sc.nextLine();
            }
            else {
                addTask(new Task(command));
                command = sc.nextLine();
            }
        }

        fuwwyEcho("Bye. Hope to see you again soon!\n");
        sc.close();
    }
}
