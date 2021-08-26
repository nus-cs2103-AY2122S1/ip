import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Ui ui = new Ui();
    private static Storage storage = null;

    private static String dir = System.getProperty("user.dir");
    private static Path path = Paths.get(dir, "data");
    private static Path savePath = Path.of(path.toString(),"duke.txt");

    public static void main(String[] args) throws DukeException {

        storage = new Storage("data/duke.txt");

        try {
            tasks = storage.load();
            if (tasks == null)
                tasks = new ArrayList<>();
            else
                ui.printLoadTasks(tasks);
        } catch (DukeException e) {
            ui.printErrorMessage(e.getMessage());
        }

        ui.startInteractionsMessage();

        Scanner scanner = new Scanner(System.in);
        String userInput;
        boolean exiting = false;

        do {
            ui.waitUserInput();
            userInput = scanner.nextLine();

            Scanner userInputScanner = new Scanner(userInput);
            String operation = userInputScanner.next();

            try {
                switch (operation.toLowerCase()) {
                    case "bye":
                        exiting = true;
                        break;

                    case "list":
                        ui.printListTasks(tasks);
                        break;

                    case "done":
                        completeTask(userInputScanner.nextInt());
                        break;

                    case "todo":
                        createNewTask(userInputScanner, TaskType.TODO);
                        break;

                    case "deadline":
                        createNewTask(userInputScanner, TaskType.DEADLINE);
                        break;

                    case "event":
                        createNewTask(userInputScanner, TaskType.EVENT);
                        break;

                    case "delete":
                        deleteTask(userInputScanner.nextInt());
                        break;

                    default:
                        throw new UnsupportedOperationException();
                }
            } catch (DukeException e) {
                ui.printErrorMessage(e.getMessage());
            }
        } while (!exiting);

        ui.exitMessage();
    }

    private static void completeTask(int taskNum) {
        tasks.get(taskNum - 1).setDone(true);
        storage.saveTasks(tasks, savePath);
        ui.printCompleteTask(tasks.get(taskNum - 1));
    }

    private static void createNewTask(Scanner userInputScanner, TaskType taskType)
            throws MissingInputException {
        if (!userInputScanner.hasNext())
            throw new MissingInputException(taskType);
        else if (taskType == TaskType.TODO) {
            addNewTask(new ToDo(userInputScanner.nextLine().trim()));
        }
        else {
            if (taskType == TaskType.DEADLINE) {
                userInputScanner.useDelimiter(" /by ");
                addNewTask(new Deadline(userInputScanner.next().trim(),
                        LocalDate.parse(userInputScanner.next().trim())));
            }
            else {
                userInputScanner.useDelimiter(" /at ");
                addNewTask(new Event(userInputScanner.next().trim(),
                        LocalDate.parse(userInputScanner.next().trim())));
            }
        }
    }

    private static void addNewTask(Task newTask) {
        tasks.add(newTask);
        storage.saveTasks(tasks, savePath);
        ui.printAddTask(tasks, newTask);
    }

    private static void deleteTask(int taskNum) {
        Task deletedTask = tasks.remove(taskNum - 1);
        storage.saveTasks(tasks, savePath);
        ui.printDeleteTask(tasks, deletedTask);
    }
}
