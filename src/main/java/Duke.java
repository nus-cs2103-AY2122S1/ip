import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static String endWord = "bye";

    ArrayList<Task> storedList;

    private Duke() {
        storedList = new ArrayList<>();
    }

    private void start() {
        System.out.println("Hello, what can I do for you.\n");
        Scanner sc = new Scanner(System.in);
        String[] strArr;
        strArr = sc.nextLine().split(" ", 2);
        String inputFirst = strArr[0];

        while(!inputFirst.equals(Duke.endWord)) {
            try {
                switch(inputFirst) {
                    case "list":
                        printList();
                        break;
                    case "done":
                        markDone(getArgs(strArr));
                        break;
                    case "todo":
                        addTodo(getArgs(strArr));
                        break;
                    case "deadline":
                        addDeadline(getArgs(strArr));
                        break;
                    case "event":
                        addEvent(getArgs(strArr));
                        break;
                    case "delete":
                        delete(getArgs(strArr));
                        break;
                    default:
                        throw new InvalidCommandDukeException();
                }
            } catch (DukeException e) {
                System.out.println("Error for command: \"" + inputFirst + "\"");
                System.out.println(e.getMessage());
            }
            strArr = sc.nextLine().split(" ", 2);
            inputFirst = strArr[0];
        }
        System.out.println("Bye bye");
    }

    private void addTodo(String args) {
        addTask(new Todo(args));
    }

    private void addDeadline(String args) throws WrongArgumentDukeException {
        String[] strArr = args.split(" /by ", 2);
        if (strArr.length >= 2) {
            addTask(new Deadline(strArr[0], strArr[1]));
        } else {
            throw new WrongArgumentDukeException();
        }

    }

    private void addEvent(String args) throws WrongArgumentDukeException {
        String[] strArr = args.split(" /at ", 2);
        if (strArr.length >= 2) {
            addTask(new Event(strArr[0], strArr[1]));
        } else {
            throw new WrongArgumentDukeException();
        }

    }

    private void addTask(Task task) {
        storedList.add(task);
        System.out.println("added: " + task);
        System.out.println(String.format("Now you have %d tasks\n", storedList.size()));
    }

    private void printList() {
        for (int i = 0; i < storedList.size(); i++) {
            System.out.println(String.format("%d.%s", i + 1, storedList.get(i)));
        }
    }

    private String getArgs(String[] arr) throws NoArgumentDukeException {
        if (arr.length >= 2 && !arr[1].equals("")) {
            return arr[1];
        } else {
            throw new NoArgumentDukeException();
        }
    }

    private void markDone(String indexStr) throws WrongArgumentDukeException, InvalidOperationDukeException {
        try {
            int index = Integer.parseInt(indexStr);
            if (index < 1 || index > storedList.size()) {
                throw new InvalidOperationDukeException("Number is out of bounds");
            }
            storedList.get(index - 1).markDone();
            System.out.println(String.format("Task %d is done", index));
            System.out.println(storedList.get(index - 1) + "\n");
        } catch (NumberFormatException e) {
            throw new WrongArgumentDukeException();
        }

    }

    private void delete(String indexStr) throws WrongArgumentDukeException, InvalidOperationDukeException {
        try {
            int index = Integer.parseInt(indexStr);
            if (index < 1 || index > storedList.size()) {
                throw new InvalidOperationDukeException("Number is out of bounds");
            }
            Task task = storedList.remove(index - 1);
            System.out.println(String.format("Removed task %d", index));
            System.out.println(task + "\n");
        } catch (NumberFormatException e) {
            throw new WrongArgumentDukeException();
        }

    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.start();
    }

}
