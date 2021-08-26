import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Duke {

    private static final String DUKE = "\nDuke:";
    private static final String USER = "\nUser:";
    private static ArrayList<Task> tasks = new ArrayList<>();

    private static String dir = System.getProperty("user.dir");
    private static Path path = Paths.get(dir, "data");
    private static Path savePath = Path.of(path.toString(),"duke.txt");

    public static void main(String[] args) throws DukeException {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        try {
            Files.createDirectory(path);
        } catch (FileAlreadyExistsException e) {
            // ignore this exception
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Files.exists(savePath))
            loadTasks(savePath);
        else {
            try {
                Files.createFile(savePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(DUKE + "Hi, what do you want from me?\n");

        Scanner scanner = new Scanner(System.in);
        String userInput;
        boolean exiting = false;

        do {
            System.out.print(USER);
            userInput = scanner.nextLine();

            Scanner userInputScanner = new Scanner(userInput);
            String operation = userInputScanner.next();

            try {
                switch (operation.toLowerCase()) {
                    case "bye":
                        exiting = true;
                        break;

                    case "list":
                        listTasks();
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
                printErrorMessage(e.getMessage());
            }
        } while (!exiting);

        System.out.println(DUKE + "Bye. Have a nice day.");
    }

    private static void listTasks() {
        System.out.println(DUKE + "\n\tTasks:");
        for (int i = 0; i < tasks.size(); i++)
            System.out.printf("\t\t%d.%s\n", i + 1, tasks.get(i));
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
    }

    private static void completeTask(int taskNum) {
        tasks.get(taskNum - 1).setDone(true);
        saveTasks(savePath);
        System.out.println(DUKE + "\n\tMarking task as completed:");
        System.out.printf("\t\t%s\n", tasks.get(taskNum - 1));
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
        saveTasks(savePath);
        System.out.println(DUKE + "\n\tAdded:\n\t\t" + newTask);
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
    }

    private static void saveTasks(Path savePath) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(savePath);
            for (Task t: tasks) {
                writer.write(t.toSaveFormat() + "\n");
            }
            writer.close();
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }

    private static void loadTasks(Path savePath) {
        try (BufferedReader reader = Files.newBufferedReader(savePath)) {
            String line = null;
            Scanner saveDataScanner;
            while ((line = reader.readLine()) != null) {
                saveDataScanner = new Scanner(line).useDelimiter(", ");
                String taskType = saveDataScanner.next();
                boolean isTaskDone = saveDataScanner.nextInt() == 1;
                String taskDesc = saveDataScanner.next();
                switch (taskType) {
                    case "T":
                        tasks.add(new ToDo(taskDesc, isTaskDone));
                        break;
                    case "D":
                        tasks.add(new Deadline(taskDesc, saveDataScanner.next(), isTaskDone));
                        break;
                    case "E":
                        tasks.add(new Event(taskDesc, saveDataScanner.next(), isTaskDone));
                        break;
                }
                saveDataScanner.close();
            }
            reader.close();
            System.out.println(DUKE + "I have loaded your past tasks list!");
            listTasks();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void printErrorMessage(String message) {
        System.out.println(DUKE);
        System.out.println("\tError: " + message + ".");
    }

    private static void deleteTask(int taskNum) {
        Task deletedTask = tasks.remove(taskNum - 1);
        System.out.println(DUKE + "\n\tRemoving task:");
        System.out.printf("\t\t%s\n", deletedTask);
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
    }
}
