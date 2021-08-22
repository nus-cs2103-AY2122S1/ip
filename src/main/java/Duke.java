import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    public static String END_WORD = "bye";
    public static String FILE_PATH = "data/";

    ArrayList<Task> storedList;
    FileController fc;

    private Duke() {
        storedList = new ArrayList<>();
        fc = new FileController(FILE_PATH, "list.txt");
        String contents = fc.readContentsAsString();
        contents.lines().forEach((line) -> {
            Task savedTask = TaskUtils.stringToTask(line);
            if (savedTask != null) {
                storedList.add(savedTask);
            } else {
                System.out.println("Line " + line + " is not a task");
            }
        });
    }


    private void start() {
        System.out.println("Hello, what can I do for you.\n");
        Scanner sc = new Scanner(System.in);
        String[] strArr;
        strArr = sc.nextLine().split(" ", 2);
        String inputFirst = strArr[0];

        while(!inputFirst.equals(Duke.END_WORD)) {
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
            LocalDate date = getLocalDate(strArr[1]);
            addTask(new Deadline(strArr[0], date));
        } else {
            throw new WrongArgumentDukeException("Did you forget to use /by");
        }

    }

    private void addEvent(String args) throws WrongArgumentDukeException {
        String[] strArr = args.split(" /at ", 2);
        if (strArr.length >= 2) {
            LocalDate date = getLocalDate(strArr[1]);
            addTask(new Event(strArr[0], date));
        } else {
            throw new WrongArgumentDukeException("Did you forget to use /at");
        }

    }

    private void addTask(Task task) {
        storedList.add(task);
        saveChanges();
        System.out.println("added: " + task);
        System.out.println(String.format("Now you have %d tasks\n", storedList.size()));
    }

    private void printList() {
        for (int i = 0; i < storedList.size(); i++) {
            System.out.println(String.format("%d.%s", i + 1, storedList.get(i)));
        }
        if (storedList.size() == 0) {
            System.out.println("\n");
        }
    }

    /**
     * Get argument to command if it exists.
     *
     * @param arr array splitted into 2, first is command second is argument
     * @return get argument to command
     * @throws NoArgumentDukeException
     */
    private String getArgs(String[] arr) throws NoArgumentDukeException {
        if (arr.length >= 2 && !arr[1].equals("")) {
            return arr[1];
        } else {
            throw new NoArgumentDukeException();
        }
    }

    /**
     * Marks a Task as done.
     *
     * @param indexStr string format of index
     * @throws WrongArgumentDukeException
     * @throws InvalidOperationDukeException
     */
    private void markDone(String indexStr) throws WrongArgumentDukeException, InvalidOperationDukeException {
        try {
            int index = Integer.parseInt(indexStr);
            if (index < 1 || index > storedList.size()) {
                throw new InvalidOperationDukeException("Number is out of bounds");
            }
            storedList.get(index - 1).markDone();
            saveChanges();
            System.out.println(String.format("Task %d is done", index));
            System.out.println(storedList.get(index - 1) + "\n");
        } catch (NumberFormatException e) {
            throw new WrongArgumentDukeException("Not a number specified");
        }
    }

    /**
     * Deletes an entry in this Duke.
     *
     * @param indexStr string format of index
     * @throws WrongArgumentDukeException
     * @throws InvalidOperationDukeException
     */

    private void delete(String indexStr) throws WrongArgumentDukeException, InvalidOperationDukeException {
        try {
            int index = Integer.parseInt(indexStr);
            if (index < 1 || index > storedList.size()) {
                throw new InvalidOperationDukeException("Number is out of bounds");
            }
            Task task = storedList.remove(index - 1);
            saveChanges();
            System.out.println(String.format("Removed task %d", index));
            System.out.println(task + "\n");
        } catch (NumberFormatException e) {
            throw new WrongArgumentDukeException("Not a number specified");
        }

    }

    /**
     *
     * Converts a string to a LocalDate
     *
     * @param str string representation of a date
     * @return LocalDate object represented by string
     * @throws WrongArgumentDukeException
     */
    private LocalDate getLocalDate(String str) throws WrongArgumentDukeException {
        try {
            return LocalDate.parse(str);
        } catch (DateTimeParseException e) {
            throw new WrongArgumentDukeException("Cannot parse date.");
        }
    }

    public void saveChanges() throws UnsavedChangesException {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for(Task task : storedList) {
            if (first) {
                first = false;
            } else {
                sb.append("\n");
            }
            sb.append(TaskUtils.taskToString(task));

        }
        if (!fc.writeText(sb.toString())) {
            throw new UnsavedChangesException();
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
