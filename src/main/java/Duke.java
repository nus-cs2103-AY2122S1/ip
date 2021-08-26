import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;


/**
 * Project Duke is an educational software project.
 * It is designed to take you through the steps of building a small software incrementally.
 */

public class Duke {
    /**
     * This is the scanner object used to get user input.
     */
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Stores the array of todos
     */
    private static final ArrayList<Task> todos = new ArrayList<>();

    /**
     * Prints out the initial greeting message.
     */
    private static void greet() {
        System.out.println("\t____________________________________________________________\n" +
                "\tHello! I'm Duke. \n\tWhat can I do for you?\n" +
                "\t____________________________________________________________");
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void handleSaveText() {
        String folderName = "./data";
        String fileName = "./data/duke.txt";
        File directory = new File(folderName);

        if (!directory.exists()) {
            directory.mkdir();
        }

        String output = "";
        String separator = " | ";

        for (Task t : todos) {
            output = output + t.getType() + separator + ((t.checkDone()) ? 1 : 0) + separator +
                    t.getDescription() + separator + t.getDeadline() + "\n";
        }
        try {
            writeToFile(fileName, output);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void handleLoadText() {
        String folderName = "./data";
        String fileName = "./data/duke.txt";
        File directory = new File(folderName);
        File doc = new File(fileName);

        if (!directory.exists()) {
            directory.mkdir();
        }

        try {
            if (doc.exists()) {
                Scanner scanner = new Scanner(new File(fileName));
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    // process the line
                    String[] output = line.split("\\s\\|\\s");
                    switch (output[0]) {
                        case "T":
                            Task newTodo = new Todo(output[2], output[1] == "1");
                            todos.add(newTodo);
                            break;
                        case "D":
                            Task newDeadline = new Deadline(output[2], output[3], output[1] == "1");
                            todos.add(newDeadline);
                            break;
                        case "E":
                            Task newEvent = new Event(output[2], output[3], output[1] == "1");
                            todos.add(newEvent);
                            break;
                        default:
                            System.out.println("Detected invalid task type. Please check...");
                            break;
                    }
                }
            } else {
                writeToFile(fileName, "");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Throws the corresponding DukeException when the Task input is empty.
     *
     * @param input the array of String input to check
     * @param type  the type of Task it is called with
     * @throws DukeException throws the NullDescription
     */
    private static void checkInput(String[] input, String type) throws DukeException {
        if (input.length == 1) {
            throw new NullDescription(type);
        }
    }

    /**
     * Prints out the feedback when users enter their response.
     *
     * @param response the array of String input to check
     * @throws DukeException throws the NullDescription
     */
    private static void handleInput(String response) throws DukeException {
        String[] output = response.split(" ");

        if (output.length == 0 || output[0].isEmpty() || output[0].equals(" ")) {
            throw new InvalidCommand();
        }

        String command = output[0];
        Task newTask;

        switch (command) {
            case "done":
                Task editedTask = todos.get(Integer.parseInt(output[1]) - 1);
                editedTask.markIsDone();
                System.out.printf("\t____________________________________________________________\n" +
                        "\tNice! I've marked this task as done:\n" +
                        "\t%s\n" +
                        "\t____________________________________________________________%n", editedTask);
                break;
            case "delete":
                try {
                    int index = Integer.parseInt(output[1]) - 1;
                    Task removedTask = todos.remove(index);
                    System.out.printf("\tNoted. I've removed this task:\n" +
                            "\t%s\n" +
                            "\tNow you have %d tasks in the list.%n", removedTask.toString(), todos.size());
                } catch (IndexOutOfBoundsException | NumberFormatException f) {
                    throw new InvalidValue();
                }
                break;
            case "todo":
                checkInput(output, "todo");
                String todoDescription = response.substring(5);
                newTask = new Todo(todoDescription, false);
                handleAdd(newTask);
                break;
            case "deadline":
                checkInput(output, "deadline");
                String deadlineDescription = response.substring(9).split(" /by ")[0];
                String deadlineDate = response.substring(9).split(" /by ")[1];
                newTask = new Deadline(deadlineDescription, deadlineDate, false);
                handleAdd(newTask);
                break;
            case "event":
                checkInput(output, "event");
                String eventDescription = response.substring(6).split(" /at ")[0];
                String eventDate = response.substring(6).split(" /at ")[1];
                newTask = new Event(eventDescription, eventDate, false);
                handleAdd(newTask);
                break;
            default:
                throw new InvalidCommand();
        }

        handleSaveText();
    }

    private static void handleAdd(Task newTask) {
        todos.add(newTask);

        System.out.println("\t____________________________________________________________\n\t" +
                String.format("Got it. I've added this task:\n" +
                        "\t  %s\n" +
                        "\tNow you have %d tasks in the list.", newTask.toString(), todos.size()) +
                "\n\t____________________________________________________________");
    }

    /**
     * Starts the Duke chatbot.
     * Users can input String to interact with the chatbot.
     * Entering 'bye' exits the program.
     * Entering 'done' followed by an int will mark the task at that index as complete
     * Entering any other string will create a new todo.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // Initial greeting of user
        greet();
        handleLoadText();

        // Starts to ask for string of instruction
        // boolean flag to indicate if loop should be exited
        boolean exit = false;

        // if boolean is false, loop will be run
        while (!exit) {
            String response = sc.nextLine();

            switch (response) {
                case "list":
                    System.out.println("\t____________________________________________________________");
                    for (int i = 0; i < todos.size(); i++) {
                        System.out.printf("\t%d.%s%n", (i + 1), todos.get(i).toString());
                    }
                    System.out.println("\t____________________________________________________________");
                    break;
                case "bye":
                    System.out.println("\t____________________________________________________________\n\t" +
                            "Bye. Hope to see you again soon!" +
                            "\n\t____________________________________________________________");
                    exit = true;
                    break;
                default:
                    try {
                        handleInput(response);
                    } catch (DukeException e) {
                        System.out.printf("\t____________________________________________________________\n" +
                                "\t%s\n" +
                                "\t____________________________________________________________%n", e);
                    }
                    break;
            }
        }
    }
}
