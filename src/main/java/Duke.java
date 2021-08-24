import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static String DATA_FILE_PATH = "src/main/java/data.txt";

    private static void lineSpacing() {
        System.out.println("____________________________________________________________");
    }

    private static void initialiseDuke() {
        File dataFile = new File(DATA_FILE_PATH);
        System.out.println("Loading data from database..........");

        try (Scanner fileScanner = new Scanner(dataFile);){
            System.out.println("Database loaded!");
            while (fileScanner.hasNext()) {
                parseAndAddTask(fileScanner.nextLine());
            }
        } catch (FileNotFoundException fileException) {
            System.out.println("No database found, creating database");

            try {
                dataFile.createNewFile();
                System.out.println("Database created");
            } catch (IOException ioException) {
                System.out.println("Error creating database");
            }
        }

    }

    private static void saveData() {
        try (FileWriter fw = new FileWriter(DATA_FILE_PATH)) {

            StringBuilder dataString = new StringBuilder();

            for (int i = 0; i < taskList.size(); i++) {
                Task currentTask = taskList.get(i);
                dataString.append(currentTask.getData() + "\n");
            }
            fw.write(dataString.toString());

        } catch (IOException e) {
            System.out.println("Error saving data!");
            e.printStackTrace();
        }
    }

    private static void parseAndAddTask(String input) {
        String[] inputArr = input.split(",");
        Task currentTask;
        switch (inputArr[0]) {
        case "T" :
            currentTask = new Todo(inputArr[2]);
            if (inputArr[1].equals("true")) {
                currentTask.markAsDone();
            }
            taskList.add(currentTask);
            break;

        case "E" :
            currentTask = new Event(inputArr[3], inputArr[1]);
            if (inputArr[2].equals("true")) {
                currentTask.markAsDone();
            }
            taskList.add(currentTask);
            break;

        case "D" :
            currentTask = new Deadline(inputArr[3], inputArr[1]);
            if (inputArr[2].equals("true")) {
                currentTask.markAsDone();
            }
            taskList.add(currentTask);
            break;

        default:
            System.out.println("Database has invalid data!");
        }

    }
    private static void displayAddedTask(Task currentTask) {
        lineSpacing();
        System.out.println("Got it. I've added this task: ");
        System.out.println(currentTask);
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
        lineSpacing();
    }

    private static void printList() {
        lineSpacing();
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            System.out.println(String.format("%d.%s", i + 1, currentTask));
        }
        lineSpacing();
    }

    private static void doneTask(String userInput) {
        String[] inputArray = userInput.split(" ");
        Task completedTask = taskList.get(Integer.parseInt(inputArray[1]) - 1);
        completedTask.markAsDone();
        lineSpacing();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(completedTask);
        lineSpacing();
        saveData();
    }

    private static void addDeadline(String userInput) {
        List<String> inputArray = Arrays.asList(userInput.split(" /by "));
        String by = inputArray.get(1);
        ArrayList<String> descriptionArray = new ArrayList<String>(Arrays.asList(inputArray.get(0).split(" ")));
        descriptionArray.remove(0);
        String description = String.join(" ", descriptionArray);
        Deadline newDeadline = new Deadline(description, by);
        taskList.add(newDeadline);
        displayAddedTask(newDeadline);
        saveData();
    }

    private static void addEvent(String userInput) {
        List<String> inputArray = Arrays.asList(userInput.split(" /at "));
        String timeFrame = inputArray.get(1);
        ArrayList<String> descriptionArray = new ArrayList<String>(Arrays.asList(inputArray.get(0).split(" ")));
        descriptionArray.remove(0);
        String description = String.join(" ", descriptionArray);
        Event newEvent = new Event(description, timeFrame);
        taskList.add(newEvent);
        displayAddedTask(newEvent);
        saveData();
    }

    private static void addTodo(String userInput) throws DukeException {
        List<String>  inputArray = Arrays.asList(userInput.split(" "));
        if (inputArray.size() <= 1) {
            throw new DukeException("todo");
        }
        ArrayList<String> descriptionArray =  new ArrayList<String>(inputArray);
        descriptionArray.remove(0);
        String description = String.join(" ",descriptionArray);
        Todo newTodo = new Todo(description);
        taskList.add(newTodo);
        displayAddedTask(newTodo);
        saveData();
    }

    private static void deleteTask(String userInput) {
        String[] inputArray = userInput.split(" ");
        Task removedTask = taskList.remove(Integer.parseInt(inputArray[1]) - 1);
        lineSpacing();
        System.out.println("Noted. I've removed this task: ");
        System.out.println(removedTask);
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
        lineSpacing();
        saveData();
    }



    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        initialiseDuke();
        while (true) {
            try {
                String userInput = sc.nextLine();
                if (userInput.equals("bye")) {
                    lineSpacing();
                    System.out.println("Bye. Hope to see you again soon!");
                    lineSpacing();
                    break;
                }

                if (userInput.equals("list")) {
                    printList();
                    continue;
                }

                if (userInput.startsWith("done")) {
                    doneTask(userInput);
                    continue;
                }

                if (userInput.startsWith("deadline")) {
                    addDeadline(userInput);
                    continue;
                }

                if (userInput.startsWith("event")) {
                    addEvent(userInput);
                    continue;
                }

                if (userInput.startsWith(("todo"))) {
                    addTodo(userInput);
                    continue;
                }

                if (userInput.startsWith("delete")) {
                    deleteTask(userInput);
                    continue;
                }

                throw new DukeException("unknown command");
            } catch (DukeException e) {
                lineSpacing();
                System.out.println(e);
                lineSpacing();
            }
        }
    }

}
