import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Duke {
    private static final String BYE = "Bye!";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String DELETE = "delete";

    private static final String PATHNAME = "./data/duke.txt";

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
        }
        printBreak();
    }

    private static void printAdd(Task task) {
        list.add(task);

        printBreak();
        System.out.println(indentation + "added: " + task.toString());
        System.out.println("\n" + indentation + "You have " + list.getList().size() + " task(s) to go! (]＞＜)]");
        printBreak();
    }

    private static void printBye() {
        printBreak();
        System.out.println(indentation + "Bye. Hope to see you again soon! (´｡• ω •｡`)");
        printBreak();
    }

    private static void printDone(int taskNo) throws ArrayIndexOutOfBoundsException {
        try {
            if (taskNo == -1 || taskNo + 1 > list.getList().size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            printBreak();
            System.out.println(indentation + "(´• ω •`) What a rarity! This task has been marked as done:");
            System.out.println(indentation + list.complete(taskNo));
            printBreak();
        } catch (ArrayIndexOutOfBoundsException e) {
            printBreak();
            System.out.println(indentation + "(＃￣ω￣) Eh... No such task found. Cannot mark as done.");
            printBreak();
        }
    }

    private static void printDelete(int taskNo) throws ArrayIndexOutOfBoundsException {
        try{
            if (taskNo == -1 || taskNo + 1 > list.getList().size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            printBreak();
            System.out.println(indentation + "(￢_￢) Ok... This task has been deleted:");
            System.out.println(indentation + "deleted: " + list.delete(taskNo));
            System.out.println("\n" + indentation + "You have " + list.getList().size() + " task(s) to go! (]＞＜)]");
            printBreak();
        } catch (ArrayIndexOutOfBoundsException e) {
            printBreak();
            System.out.println(indentation + "(＃￣ω￣) Eh... No such task found. Cannot delete.");
            printBreak();
        }
    }

    private static void writeToFile() {
        try {
            File data = new File(PATHNAME);
            if (!data.exists()) {
                data.getParentFile().mkdirs();
                data.createNewFile();
                printBreak();
                System.out.println(indentation + "╰(▔∀▔)╯ File created!");
                printBreak();
            }
            System.out.println(data.getAbsolutePath());
            FileWriter fw = new FileWriter(data.getAbsoluteFile());

            for (int i = 0; i < list.getList().size(); i++) {
                fw.write(list.getList().get(i).toSaveString() + System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            printBreak();
            System.out.println(indentation + "╮(￣ω￣;)╭ File cannot be created..." + e.getMessage());
            printBreak();
        }
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello from\n" + logo);

        while (true) {
            String str = sc.nextLine();
            String command = str.split(" ")[0];

            try {
                if (command.equals(BYE)) {
                    printBye();
                    break;
                } else if (command.equals(LIST)) {
                    printList();
                } else if (command.equals(DONE)) {
                    int taskNo = Integer.parseInt(str.split(" ")[1]) - 1;
                    printDone(taskNo);
                } else if (command.equals(DELETE)) {
                    int taskNo = Integer.parseInt(str.split(" ")[1]) - 1;
                    printDelete(taskNo);
                } else if (command.equals(TODO)) {
                    String desc = str.substring(5);
                    if (desc.equals("")) {
                        throw new IllegalArgumentException();
                    }
                    Todos newTodo = new Todos(desc);
                    printAdd(newTodo);
                } else if (command.equals(EVENT)) {
                    String desc = str.split(" /at ")[0].substring(6);
                    if (desc.equals("")) {
                        throw new IllegalArgumentException();
                    }
                    String at = str.split(" /at ")[1];
                    if (at.equals("")) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    Events newEvent = new Events(desc, at);
                    printAdd(newEvent);
                } else if (command.equals(DEADLINE)) {
                    String desc = str.split(" /by ")[0].substring(9);
                    if (desc.equals("")) {
                        throw new IllegalArgumentException();
                    }
                    String by = str.split(" /by ")[1];
                    if (by.equals("")) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    Deadlines newDeadline = new Deadlines(desc, by);
                    printAdd(newDeadline);
                } else {
                    throw new DukeException("(￣ ￣|||) Sorry I do not understand.");
                }
            } catch (DukeException e) {
                printBreak();
                System.out.println(indentation + "Ehh... " + e);
                printBreak();
            } catch (IllegalArgumentException | StringIndexOutOfBoundsException e) {
                printBreak();
                System.out.println(indentation + "(・`ω´・) Don't leave it empty!!");
                printBreak();
            } catch (ArrayIndexOutOfBoundsException e) {
                printBreak();
                System.out.println(indentation + "(-_-) You forgot the date...");
                printBreak();
            }
        }
        writeToFile();
    }
}
