import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<>();
    final static String persistedData = "data/duke.txt";

    private static void appendLineToPersistedData(String line) {
        try {
            FileWriter fw = new FileWriter(persistedData, true);
            fw.write(line);
            fw.close();
        } catch (IOException e) {
            System.out.println("A problem occurred while writing to text file.");
            e.printStackTrace();
        }
    }
    private static void deleteLineFromPersistedData(int taskNum) {
        Path persistedDataPath = Paths.get(persistedData);
        try {
            List<String> allLines = Files.readAllLines(persistedDataPath);
            allLines.remove(taskNum);
            Files.write(persistedDataPath, allLines);
        } catch (IOException e) {
            System.out.println("A problem occurred while reading from the text file.");
            e.printStackTrace();
        }
    }
    private static void getLstItems() {
        int counter = 1;
        for (Task task : tasks) {
            System.out.println(counter + "." + task.toString());
            counter += 1;
        }
    }
    private static void addTask(Task newTask, boolean writeToPersistedData) {
        tasks.add(newTask);
        String line = newTask.persistedDataStringFormat();
        if (writeToPersistedData) appendLineToPersistedData(line + System.lineSeparator());
        System.out.printf("Great, I've added this task:\n  %s%n", newTask.toString());
        System.out.printf("Now you have %d tasks in the list.%n", tasks.size());
    }
    private static void deleteTask(int taskNum) {
        Task currTask = tasks.get(taskNum);
        tasks.remove(taskNum);
        deleteLineFromPersistedData(taskNum);
        System.out.printf("Roger that Sensei, I've removed this task:\n  %s%n", currTask.toString());
        System.out.printf("Now you have %d tasks in the list.%n", tasks.size());
    }
    private static void handleToDo(String userInput, boolean isDone, boolean writeToPersistedData) throws EmptyTaskDescriptionException {
        try {
            ToDos newToDo = new ToDos(userInput.substring(5), isDone);
            addTask(newToDo, writeToPersistedData);
        } catch (StringIndexOutOfBoundsException strE) {
            throw new EmptyTaskDescriptionException("todo");
        }
    }
    private static void handleDeadline(String userInput, boolean isDone, boolean writeToPersistedData) throws EmptyTaskDescriptionException {
        try {
            int sep = userInput.indexOf('/', 9);
            String descPart = userInput.substring(9, sep - 1);
            String byPart = userInput.substring(sep+1);
            Deadlines newDeadline = new Deadlines(descPart, byPart, isDone);
            addTask(newDeadline, writeToPersistedData);
        } catch (StringIndexOutOfBoundsException strE) {
            throw new EmptyTaskDescriptionException("deadline");
        }
    }
    private static void handleEvent(String userInput, boolean isDone, boolean writeToPersistedData) throws EmptyTaskDescriptionException {
        try {
            int sep = userInput.indexOf('/', 6);
            String descPart = userInput.substring(6, sep - 1);
            String atPart = userInput.substring(sep+1);
            Events newEvent = new Events(descPart, atPart, isDone);
            addTask(newEvent, writeToPersistedData);
        } catch (StringIndexOutOfBoundsException strE) {
            throw new EmptyTaskDescriptionException("event");
        }
    }
    private static void bootUpPersistedData() {
        Path pathToTxt = Paths.get("data", "duke.txt");
        Path pathToData = Paths.get("data");
        if (Files.exists(pathToTxt)) {
            System.out.println("Persisted data exists. Loading...");
            try {
                Stream<String> persistedData = Files.lines(pathToTxt);
                Consumer<String> loadDataAction = s -> {
                    String[] loadedLine = s.split(",");
                    char typeOfTask = loadedLine[0].charAt(0);
                    boolean isDone = loadedLine[1].charAt(0) == '1';
                    String description = loadedLine[2];
                    switch (typeOfTask) {
                    case 'T':
                        try {
                            handleToDo("todo " + description, isDone, false);
                        } catch (EmptyTaskDescriptionException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 'D':
                        try {
                            String mimicUserInput = "deadline " + description + " /" + loadedLine[3];
                            handleDeadline(mimicUserInput, isDone, false);
                        } catch (EmptyTaskDescriptionException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 'E':
                        try {
                            String mimicUserInput = "event " + description + " /" + loadedLine[3];
                            handleEvent(mimicUserInput, isDone, false);
                        } catch (EmptyTaskDescriptionException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + typeOfTask);
                    }
                };
                persistedData.forEach(loadDataAction);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Persisted data does not exist.");
            try {
                Files.createDirectories(pathToData);
                Files.createFile(pathToTxt);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public static void main(String[] args) {
        String horizontalLines = "-----------------------------------------";
        bootUpPersistedData();
        System.out.println(horizontalLines + "\nHello! I'm Naruto\nWhat can I do for you?\n" + horizontalLines);
        Scanner in = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("See ya! Hope to see you again!" + "\n" + horizontalLines);
                return;
            } else if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                getLstItems();
            } else if (userInput.startsWith("done")) {
                int taskNum = Integer.parseInt(userInput.substring(5)) - 1;
                Task currTask = tasks.get(taskNum);
                currTask.markAsDone(taskNum);
            } else if (userInput.startsWith("todo")) {
                try {
                    handleToDo(userInput, false, true);
                } catch (EmptyTaskDescriptionException e) {
                    System.out.println(e.getMessage());
                }
            } else if (userInput.startsWith("deadline")) {
                try {
                    handleDeadline(userInput, false, true);
                } catch (EmptyTaskDescriptionException e) {
                    System.out.println(e.getMessage());
                }
            } else if (userInput.startsWith("event")) {
                try {
                    handleEvent(userInput, false, true);
                } catch (EmptyTaskDescriptionException e) {
                    System.out.println(e.getMessage());
                }
            } else if (userInput.startsWith("delete")) {
                int taskNum = Integer.parseInt(userInput.substring(7)) - 1;
                deleteTask(taskNum);
            } else {
                // All other cases means input error
                UnknownInputException e = new UnknownInputException();
                try {
                    throw e;
                } catch (UnknownInputException e1) {
                    System.out.println(e1.getMessage());
                }
            }
        }
    }
}
