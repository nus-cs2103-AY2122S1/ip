import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private final ArrayList<Task> tasks;
    private Storage store;
    private Boolean end = false;

    private final String lineSeparator = "\t_______________________________";

    /**
     * This is the Constructor of Duke.
     */
    public Duke(String filePath) {
        ArrayList<Task> tasks1;
        try {
            this.store = new Storage(filePath);
            tasks1 = store.load();
        } catch (IOException e) {
            tasks1 = new ArrayList<>();
        }
        this.tasks = tasks1;
    }

    /**
     * This method marks the task at the specified index and prints out a response
     * to the "done" command.
     *
     * @param indexToMark  An int that represents the task to be marked
     * @throws TaskNotFoundException  An exception indicating the task does not exist
     * @throws TaskIsCompleteException  An exception indicating the task is already completed
     */
    private void doneTask(int indexToMark) throws TaskNotFoundException, TaskIsCompleteException {
        if (tasks.size() - 1 >= indexToMark && indexToMark >= 0) {
            Task t = tasks.get(indexToMark);
            if (t.isDone) {
                throw new TaskIsCompleteException(indexToMark + 1);
            } else {

                System.out.println(lineSeparator);
                try {
                    t.markAsDone();
                    int listInd = indexToMark + 1;
                    store.appendCommand("done " + listInd);
                    System.out.println("\tNice! I've marked this task as done:");
                    System.out.printf("\t%s\n", tasks.get(indexToMark));
                } catch (IOException e) {
                    System.out.println("\tError Saving to Database.");
                } finally {
                    System.out.println(lineSeparator);
                }
            }
        } else {
            throw new TaskNotFoundException(indexToMark + 1);
        }
    }

    /**
     * This method list out all the task so far upon "list" command.
     *
     * @throws EmptyListException
     */
    private void listOutTasks() throws EmptyListException {
        if (tasks.size() != 0) {
            System.out.println(lineSeparator);
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("\t%d. %s\n", i + 1, tasks.get(i));
            }
            System.out.println(lineSeparator);
        } else {
            throw new EmptyListException();
        }
    }

    /**
     * This method prints the bye message upon "bye" command
     */
    private void endDuke() {
        System.out.println(lineSeparator);
        try {
            store.safeFile(tasks);
            System.out.println("\tBye. Hope to see you again soon!");
        } catch (IOException e) {
            System.out.println("\tError Saving To Database.");
        } finally {
            System.out.println(lineSeparator);
        }
    }

    /**
     * This method registers and add the tasks to the list.
     *
     * @param description  A String that describes the task to be added.
     * @throws UnknownCommandException  An exception indicating an unknown command given
     * @throws CommandParamException    An exception indicating invalid description of command
     * @throws EmptyDescriptionException  An exception indicating empty description for a task
     */
    private void addTasks(String taskType, String description) throws UnknownCommandException,
                                                CommandParamException, EmptyDescriptionException {
        Task t;
        if (taskType.equals("todo")) {
            if (description.trim().equals("")) {
                throw new EmptyDescriptionException(taskType);
            }
            t = new ToDo(description.trim());
        } else if (taskType.equals("deadline")) {
            if (description.equals("")) {
                throw new EmptyDescriptionException(taskType);
            }
            if (description.contains("/by")) {
                String[] parts = description.split("/by");
                if (parts.length < 2 || parts[0].trim().equals("") || parts[1].trim().equals("")) {
                    throw new CommandParamException(taskType);
                }
                t = new Deadline(parts[0].trim(), parts[1].trim());
            } else {
                throw new CommandParamException(taskType);
            }
        } else if (taskType.equals("event")) {
            if (description.equals("")) {
                throw new EmptyDescriptionException(taskType);
            }
            if (description.contains("/at")) {
                String[] parts = description.split("/at");
                if (parts.length < 2 || parts[0].trim().equals("") || parts[1].trim().equals("")) {
                    throw new CommandParamException(taskType);
                }
                t = new Event(parts[0].trim(), parts[1].trim());
            } else {
                throw new CommandParamException(taskType);
            }
        } else {
            throw new UnknownCommandException();
        }
        System.out.println(lineSeparator);
        try {
            tasks.add(t);
            store.appendCommand(t.fullCommand());
            System.out.println("\tGot it. I've added this task: ");
            System.out.printf("\t  %s\n", t);
            System.out.printf("\tNow you have %d ", tasks.size());
            System.out.println((tasks.size() <= 1 ? "task" : "tasks") + " in the list.");
        } catch (IOException e) {
            System.out.println("\tError Saving To Database.");
        } finally {
            System.out.println(lineSeparator);
        }

    }

    /**
     * This method removes a task of the specified index.
     *
     * @param indexToDelete   An int representing the index of the to-be deleted task.
     * @throws TaskNotFoundException  An exception indicating the task does not exist.
     */
    public void deleteTask(int indexToDelete) throws TaskNotFoundException {
        if (tasks.size() - 1 >= indexToDelete && indexToDelete >= 0) {
            System.out.println(lineSeparator);
            try {
                Task t = this.tasks.get(indexToDelete);
                int listInd = indexToDelete + 1;
                store.appendCommand("delete " + listInd);
                this.tasks.remove(indexToDelete);
                System.out.println("\tNoted. I've removed this task:");
                System.out.printf("\t  %s\n", t);
                System.out.printf("\tNow you have %d ", tasks.size());
                System.out.println((tasks.size() <= 1 ? "task" : "tasks") + " in the list.");
            } catch (IOException e) {
                System.out.println("\tError Saving To Database.");
            } finally {
                System.out.println(lineSeparator);
            }
        } else {
            throw new TaskNotFoundException(indexToDelete + 1);
        }
    }

    private void readCommand(String input, Scanner s) { //try - catch here
        try {
            switch (input) {
                case "":
                    break;
                case "delete":
                    int indexToDelete = s.nextInt() - 1;
                    this.deleteTask(indexToDelete);
                    break;
                case "done":
                    int indexToMark = s.nextInt() - 1;
                    this.doneTask(indexToMark);
                    break;
                case "list":
                    this.listOutTasks();
                    break;
                case "bye":
                    this.endDuke();
                    this.end = true;
                    break;
                default:
                    String fullLine = s.nextLine();
                    this.addTasks(input, fullLine);
            }
        } catch (DukeException e) {
            System.out.println("\t_______________________________");
            System.out.printf("\t%s\n", e.getMessage());
            System.out.println("\t_______________________________");
        }
    }

    /**
     * This is the main point of interaction of user and Duke.
     */
    public void run() {
        System.out.println("Hello I'm FullOfBugs. What can I do for you?");
        Scanner s = new Scanner(System.in);

        while(!this.end) {
            String input = s.next().trim();
            this.readCommand(input, s);
        }
        s.close();
    }

    public static void main(String[] args) {
        java.nio.file.Path filepath = java.nio.file.Paths.get("src",
                                                "main", "java","data","StoredData.txt");
        new Duke(filepath.toString()).run();
    }

}
