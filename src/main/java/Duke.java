import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String BYE = "Bye!";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    private static List list = new List();

    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static String end = "    ---------------------------------------------------------------------------------";

    private static String indentation = "     ";

    private static void printBreak() {
        System.out.println(end);
    }

    private static void printList() {
        printBreak();
        ArrayList<Task> todos = list.getList();
        todos.trimToSize();
        if (todos.size() == 0) {
            System.out.println(indentation + " ٩(◕‿◕｡)۶ Ehe no task for now.");
        } else {

            for (int i = 0; i < todos.size(); i++) {
                System.out.println(indentation + (i + 1) + ": " + todos.get(i).toString());
            }
            System.out.println(indentation + todos.size() + " more to go! (」＞＜)」");
        }
        printBreak();
    }

    private static void printAdd(Task task) {
        list.add(task);
        printBreak();
        System.out.println(indentation + "added: " + task.toString());
        printBreak();
    }

    private static void printBye() {
        printBreak();
        System.out.println(indentation + "Bye. Hope to see you again soon! (´｡• ω •｡`)");
        printBreak();
    }

    private static void printDone(int taskNo) {
        System.out.println(indentation + "(´• ω •`) What a rarity! This task has been marked as done: ");
        System.out.println(indentation + "[X] " + list.complete(taskNo));
        printBreak();
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello from\n" + logo);

        while (true) {
            String str = sc.nextLine();
            String command = str.split(" ")[0];

            if (command.equals(BYE)) {
                printBye();
                break;
            } else if (command.equals(LIST)) {
                printList();
            } else if (command.equals(DONE)) {
                int taskNo = Integer.parseInt(str.split(" ")[1]) - 1;
                printDone(taskNo);
            } else if (command.equals(TODO)) {
                String desc = str.substring(5);
                Todos newTodo = new Todos(desc);
                printAdd(newTodo);
            } else if (command.equals(EVENT)) {
                String desc = str.split(" /at ")[0].substring(6);
                String at = str.split(" /at ")[1];
                Events newEvent = new Events(desc, at);
                printAdd(newEvent);
            } else if (command.equals(DEADLINE)) {
                String desc = str.split(" /by ")[0].substring(9);
                String by = str.split(" /by ")[1];
                Deadlines newDeadline = new Deadlines(desc, by);
                printAdd(newDeadline);
            } else {
                printBreak();
                System.out.println(indentation + "(￣ ￣|||) Sorry I do not understand.");
                printBreak();
            }
        }
    }
}
