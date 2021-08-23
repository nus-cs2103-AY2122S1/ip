import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String DATA_FILE_PATH = "duke.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Formats an output by adding a top and bottom border and displays it to the terminal
     * @param output The terminal output to format
     */
    private static void display(String output) {
        String horizontalLine = "\t ____________________________________________________________ \n";
        System.out.println(horizontalLine + "\t " + output + "\n" + horizontalLine);
    }

    private static void loadOrCreateFile(String filePath) {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] splitLine = line.split(" [|] ");

                // Read each element of the line
                String taskType = splitLine[0];
                boolean isDone = splitLine[1].equals("1");
                String description = splitLine[2];

                // Store data from file into tasks arraylist
                Task t;
                switch (taskType) {
                case "T":
                    t = new Todo(description, isDone);
                    tasks.add(t);
                    break;
                case "D":
                    t = new Deadline(description, isDone, splitLine[3]);
                    tasks.add(t);
                    break;
                case "E":
                    t = new Event(description, isDone, splitLine[3]);
                    tasks.add(t);
                    break;
                default:
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            // Create the file
            File f = new File(filePath);
            try {
                f.createNewFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private static void overwriteFile(String filePath, String textToSave) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToSave);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void overwriteFile(String filePath) {
        String output = "";
        for (Task t : tasks) {
            output += t.toStringForFile() + System.lineSeparator();
        }

        overwriteFile(filePath, output);
    }

    private static void appendToFile(String filePath, String textToAppend) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(textToAppend);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Load data from hard disk
        loadOrCreateFile(DATA_FILE_PATH);

        // Greet the user
        String introduction = "Hello! I'm Duke, your personal assistant. \n" +
                "\t What can I do for you? \n";
        display(introduction);

        // Read and handle inputs from the user
        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            // Separate user input into command (first word) and data (rest of string)
            String input = s.nextLine();
            String command = "";
            String data = "";
            if (input.contains(" ")) {
                String[] splitInput = input.split(" ", 2);
                command = splitInput[0];
                data = splitInput[1];
            } else {
                command = input;
            }

            // Handle user input
            if (command.equals("bye")) {
                // Exit
                display("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                // Display list of tasks
                String output = "";
                for (int i = 0; i < tasks.size(); i++) {
                    output += String.valueOf(i + 1) + ". " + tasks.get(i) + "\n\t ";
                }
                display(output);
            } else if (command.equals("done")) {
                // Mark task as done
                int index = Integer.parseInt(data);
                Task t = tasks.get(index - 1);
                t.markAsDone();
                overwriteFile(DATA_FILE_PATH);
                display("Nice! I've marked this task as done: \n\t\t " + t);
            } else if (command.equals("todo")) {
                // Store task as Todo
                if (data.equals("")) {
                    display("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    Task t = new Todo(data);
                    tasks.add(t);
                    appendToFile(DATA_FILE_PATH, t.toStringForFile() + System.lineSeparator());
                    display("Got it. I've added this task: \n\t\t "
                            + t
                            + "\n\t Now you have " + tasks.size() + " tasks in the list.");
                }
            } else if (command.equals("deadline")) {
                // Store task as Deadline
                String description = data.split(" /by")[0];
                String by = data.split("/by ")[1];
                Task t = new Deadline(description, by);
                tasks.add(t);
                appendToFile(DATA_FILE_PATH, t.toStringForFile() + System.lineSeparator());
                display("Got it. I've added this task: \n\t\t "
                        + t
                        + "\n\t Now you have " + tasks.size() + " tasks in the list.");
            } else if (command.equals("event")) {
                // Store task as Event
                String description = data.split(" /at")[0];
                String at = data.split("/at ")[1];
                Task t = new Event(description, at);
                tasks.add(t);
                appendToFile(DATA_FILE_PATH, t.toStringForFile() + System.lineSeparator());
                display("Got it. I've added this task: \n\t\t "
                        + t
                        + "\n\t Now you have " + tasks.size() + " tasks in the list.");
            } else if (command.equals("delete")) {
                // Delete task
                int index = Integer.parseInt(data);
                Task t = tasks.get(index - 1);
                tasks.remove(index - 1);
                overwriteFile(DATA_FILE_PATH);
                display("Noted. I've removed this task: \n\t\t "
                        + t
                        + "\n\t Now you have " + tasks.size() + " tasks in the list.");
            } else {
                display("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        s.close();
    }
}
