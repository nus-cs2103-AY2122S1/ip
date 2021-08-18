import java.util.Scanner;

public class Duke {

    public static String endWord = "bye";

    Task[] storedList;
    int currentIndex;

    private Duke() {
        storedList = new Task[100];
        currentIndex = 0;
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
        storedList[currentIndex] = task;
        currentIndex ++;
        System.out.println("added: " + task);
        System.out.println(String.format("Now you have %d tasks\n", currentIndex));
    }

    private void printList() {
        for (int i = 0; i < currentIndex; i++) {
            System.out.println(String.format("%d.%s", i + 1, storedList[i]));
        }
        System.out.println("\n");
    }

    private String getArgs(String[] arr) throws NoArgumentDukeException {
        if (arr.length >= 2) {
            return arr[1];
        } else {
            throw new NoArgumentDukeException();
        }
    }

    private void markDone(String indexStr) {
        int index = Integer.parseInt(indexStr);
        storedList[index - 1].markDone();
        System.out.println(String.format("Task %d is done", index));
        System.out.println(storedList[index - 1] + "\n");
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
