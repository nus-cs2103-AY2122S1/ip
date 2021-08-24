import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Main class of Duke chatbot.
 */
public class Duke {
    public static final Path filePath = Paths.get("src","main", "data", "duke.txt");

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\  ___ | | _____ \n"
                + "| | | |/ _ \\| |/ / _ \\\n"
                + "| |_| | |_| |   < __/\n"
                + "|____/ \\___/|_|\\_\\___|\n";
        System.out.println("----------------------------------------");
        System.out.println(logo);
        System.out.println("Hello! I'm Doke\nWhat do you want??");
        System.out.println("----------------------------------------");
        List list = readFile(filePath);
        readMessage(list);
    }

    /**
     * Reads messages from input.
     * Perform operations based on the input given.
     *
     * @param list The list of task
     */
    public static void readMessage(List list) {
        Scanner messageReader = new Scanner(System.in);
        boolean end = false;
        while (!end) {
            // Get input and split by space to get the first word (the command)
            String message = messageReader.nextLine();
            String[] splitMessage = message.split(" ", 2);
            String command = splitMessage[0];

            // Output a response based on command
            System.out.println("----------------------------------------");
            String description;
            String dateTime;
            switch(command) {
            case("bye"): // Print exit message, exit the program
                System.out.println("    Bye. Hope to see you again!");
                end = true;
                break;
            case("list"): // List the current tasks and their status
                list.listItems();
                break;
            case("done"): // Mark a task as done and display the task
                try {
                    int taskNumber = Integer.parseInt(message.split(" ")[1]);
                    String taskMessage = list.changeTaskStatus(taskNumber);
                    System.out.println("Alright, I have marked the task as done:\n" + taskMessage);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("You forgot to indicate which task!!!");
                } catch (NumberFormatException n) {
                    System.out.println("Enter done followed by an integer representing the task...");
                } catch (DukeException d) {
                    System.out.println(d.getMessage());
                }
                break;
            case("delete"): // Delete the task indicated
                try {
                    int taskNumber = Integer.parseInt(message.split(" ")[1]);
                    String taskMessage = list.deleteTask(taskNumber);
                    System.out.println("Alright, I have removed the following task:\n" + taskMessage);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("You forgot to indicate which task!!!");
                } catch (NumberFormatException n) {
                    System.out.println("Enter delete followed by an integer representing the task...");
                } catch (DukeException d) {
                    System.out.println(d.getMessage());
                }
                break;
            case("todo"): // Create a ToDo task and display the task
                try {
                    description = splitMessage[1];
                    list.addTask(description);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The description of ToDo cannot be empty!\n Please try again :-(");
                }
                break;
            case("deadline"): // Create a Deadline task and display the task
                try {
                    description = splitMessage[1].split("/by ")[0];
                    dateTime = splitMessage[1].split("/by ")[1];
                    list.addTask(description, dateTime, command);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Follow the format: \ndeadline %description% /by %date/time%");
                }
                break;
            case("event"): // Create an Event task and display the task
                try {
                    description = splitMessage[1].split("/at ")[0];
                    dateTime = splitMessage[1].split("/at ")[1];
                    list.addTask(description, dateTime, command);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Follow the format: \nevent %description% /at %date/time%");
                }
                break;
            default: // If input does not have a recognised command
                System.out.println("Command not recognised!!");
                break;
            }
            System.out.println("----------------------------------------");
        }
    }

    /**
     * Read from file containing task list.
     * Loads the tasks into a List and returns it.
     *
     * @param filepath The path of the file to read from
     * @return A list of task with preloaded task if saved data is available
     */
    public static List readFile(Path filepath) {
        List list = new List();

        // Read from file and creates the list of tasks.
        try {
            File file = new File(filepath.toString());
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                String[] splitData = data.split(",");
                switch(splitData[0]) {
                case("T"):
                    list.addTask(splitData[2], Boolean.parseBoolean(splitData[1]));
                    break;
                case("D"):
                    for (String s : splitData) {
                        System.out.println(s);
                    }
                    list.addTask(splitData[2], splitData[3], "deadline", Boolean.parseBoolean(splitData[1]));
                    break;
                case("E"):
                    list.addTask(splitData[2], splitData[3], "event", Boolean.parseBoolean(splitData[1]));
                }
            }
            fileReader.close();
        } catch (FileNotFoundException f) {
            // Warn user that the program is starting from a fresh state and creates the save file
            System.out.println("No previous data, starting fresh program.");
            createFile(filepath.toString());
        }

        return list;
    }

    /**
     * Create a new file for saving data.
     *
     * @param filename The name of the file to be created
     */
    public static void createFile(String filename) {
        try {
            File newFile = new File(filename);
            if (newFile.createNewFile()) {
                System.out.println("New save file created.");
            } else {
                throw new DukeException("Unable to create save file");
            }
        } catch (IOException e) {
            System.out.println("Error creating file");
            e.printStackTrace();
        } catch (DukeException d) {
            System.out.println(d.getMessage());
        }
    }
}
