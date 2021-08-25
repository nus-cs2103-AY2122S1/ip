import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

/**
 * This class is used to simulate an automatic list creator which saves its data and changes it
 * when the user interacts with it.
 */
public class Duke {
    /**
     * Prints the contents of the specified file.
     * @param filePath The path of the file to be printed.
     * @throws FileNotFoundException if file is not found.
     */
    private static void printFileContents(String filePath) throws FileNotFoundException {
        File testFile = new File(filePath);
        Scanner s = new Scanner(testFile);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    /**
     * Writes content to a file with given content.
     * @param filePath The path of the file to be written.
     * @param addedText The text to be written in the file
     * @throws IOException if text to be written has errors
     *                     while being processed
     */
    private static void writeFile(String filePath, String addedText) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(addedText);
        fw.close();
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Ben's. How may I help you?\n");
        String filePath = "data/duke.txt";
        File dukeFile = new File(filePath);
        filePath = dukeFile.getAbsolutePath();

        try {
            System.out.println("Current list:");
            printFileContents(dukeFile.getAbsolutePath());
        } catch (FileNotFoundException e) {
            System.out.println("\nNote: This is your first time using Duke. " +
                    "We will now create a new file for you.");
        }

        File parentDir = dukeFile.getParentFile();
        if(!parentDir.exists()) {
            parentDir.mkdirs();
        }

        Scanner newScan = new Scanner(System.in);
        ArrayList<Task> contents = new ArrayList<>();
        String userInput = newScan.nextLine();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                if (contents.size() == 0) {
                    System.out.println("    ***\n" + "    There is currently nothing in the list. \n" +
                            "    ***\n");
                } else {
                    int counter = 1;
                    System.out.println("    ***\n" + "    These are your tasks in the list:");
                    for (Task x: contents) {
                        System.out.println("      " + counter + ". " + x.getStatusIcon() + " " +
                                x.getDescription());
                        counter++;
                    }
                    System.out.println("    ***\n");
                }
            } else if (userInput.startsWith("done") || userInput.startsWith("delete")) {
                try {
                    if (userInput.startsWith("done")) {
                        String index = userInput.substring(5);
                        int x = Integer.parseInt(index);
                        Task temp = contents.get(x - 1);
                        temp.markedDone();
                        System.out.println("    ***\n" + "    You have successfully done this task:\n" +
                                "      " + temp.getStatusIcon() + " " + temp.getDescription() + "\n    ***\n");
                    } else {
                        String index = userInput.substring(7);
                        int x = Integer.parseInt(index);
                        Task temp = contents.get(x - 1);
                        contents.remove(temp);
                        System.out.println("    ***\n" + "    You have successfully removed this task:\n" +
                                "      " + temp.getStatusIcon() + " " + temp.getDescription() + "\n    ***\n");
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Invalid input. Requires a number.");
                } catch (ArrayIndexOutOfBoundsException f) {
                    System.out.println("Invalid number given. Number is larger than list count.");
                }
            } else {
                Task newTask;
                if (userInput.startsWith("deadline")) {
                    newTask = new Deadline(userInput);
                } else if (userInput.startsWith("event")) {
                    newTask = new Event(userInput);
                } else if (userInput.startsWith("todo")) {
                    newTask = new ToDos(userInput);
                } else {
                    throw new IllegalArgumentException("Invalid input. \nYou may only use the following inputs: " +
                            "(bye, list, done, deadline, event, todo) and any text thereafter.");
                }
                contents.add(newTask);
                if (contents.size() == 1) {
                    System.out.println("    ***\n" + "    Understood. Added the task:\n" + "      " +
                        newTask.printTask() + "\n    You now have " + contents.size() + " task in the list.\n" +
                        "    ***\n");
                } else {
                    System.out.println("    ***\n" + "    Understood. Added the task:\n" + "      " +
                            newTask.printTask() + "\n    You now have " + contents.size() + " tasks in the list.\n" +
                            "    ***\n");
                }
            }
            userInput = newScan.nextLine();
        }
        int count = 0;

        for (Task content : contents) {
            try {
                writeFile(filePath, content.printTask());
                count++;
            } catch (IOException e) {
                System.out.println("Error with writing to file. Error: " + e.getMessage());
            }
        }

        if (count == contents.size() && count != 0) {
            System.out.println("Successfully written contents to file.");
        }

        System.out.println("\nGoodbye! Have a nice day. :)");
        System.exit(0);
    }
}