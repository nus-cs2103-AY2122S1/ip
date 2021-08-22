import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Description:
 * Duke the ChatBot allows users to add 3 different types of tasks, mark them as done, and delete tasks.
 * The commands for usage are as follows:
 * 1. "todo <name>" where name is what the user would like the todo to be called.
 * 2. "event <name> /at <date>" where name is what the user would like the event to be called and date is when the event is happening.
 * 3. "deadline <name> /by <date>" where name is what the user would like the deadline to be called and date is when the deadline is set.
 * 4. "list" to view current tasks added to the tasks list.
 * 5. "done <number>" where number is the task with the corresponding number in the list which the user would like to mark as completed.
 * 6. "delete <number>" where number is the task with the corresponding number in the list which the user would like to remove.
 * 7. "bye" to leave the ChatBot.
 * Disclaimer: any other commands will not be recognised and user will be prompted to enter a valid command.
 *
 * @author Leong Hong Fai
 */

public class Duke {
    private static ArrayList<Task> storage = new ArrayList<>();
    private static final String fileAddress = "data/duke.txt";

    public static void main(String[] args) {
        makeFile();
        retrieveFileContents();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello FROM\n" + logo);
        commands();
    }

    private static void retrieveFileContents(){
        File textFile = new File("data/duke.txt");
        Integer counter = 0;
        try {
            Scanner s = new Scanner(textFile);
            while (s.hasNext()) {
                String command = s.nextLine();
                String[] words = command.split(" - ");
                boolean isCompleted = false;
                if (words[1].equals("1")) {
                    isCompleted = true;
                }
                String task = words[0];
                switch (task) {
                    case ("T"):
                        addToDo("todo " + words[2], false);
                        if (isCompleted) {
                            Integer temp = counter + 1;
                            markCompleted("done " + temp.toString(), false);
                        }
                        counter++;
                        break;
                    case ("E"):
                        addEvent("event " + words[2] + "/at " + words[3], false);
                        if (isCompleted) {
                            Integer temp = counter + 1;
                            markCompleted("done " + temp.toString(), false);
                        }
                        counter++;
                        break;
                    case ("D"):
                        addDeadline("deadline " + words[2] + "/by " + words[3], false);
                        if (isCompleted) {
                            Integer temp = counter + 1;
                            markCompleted("done " + temp.toString(), false);
                        }
                        counter++;
                        break;
                }
            }
            s.close();
            for (int i = 0; i < storage.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, storage.get(i));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist" + e.getMessage());
        }
    }

    private static void makeFile() {
        File textFile = new File("data/duke.txt");
        Path path = Paths.get("data");
        if (!Files.isDirectory(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                System.err.println("Failed to create directory!" + e.getMessage());
            }
        }
        if (!textFile.exists()) {
            try {
                textFile.createNewFile();
            } catch (IOException e) {
                System.err.println("Failed to create file!" + e.getMessage());
            }
        }
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        File f = new File(filePath);
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        if (f.length() == 0) {
            fw.write(textToAppend);
        } else {
            fw.write(System.lineSeparator() + textToAppend);
        }
        fw.close();
    }

    private static void editFileContentsForCompletion(int taskNum) {
        File temp = new File("data/temp.txt");
        if (!temp.exists()) {
            try {
                Files.copy(Paths.get("data/duke.txt"), Paths.get("data/temp.txt"), REPLACE_EXISTING);
                new FileWriter(fileAddress, false).close();
                Scanner s = new Scanner(new File("data/temp.txt"));
                int count = 1;
                while (s.hasNextLine()) {
                    String command = s.nextLine();
                    System.out.println(command);
                    if (count == taskNum) {
                        String head = command.substring(0, 4);
                        String tail = command.substring(5);
                        appendToFile("data/duke.txt", head + "1" + tail);
                        count++;
                    } else {
                        appendToFile("data/duke.txt", command);
                        count++;
                    }
                }
                s.close();
                Files.delete(Paths.get("data/temp.txt"));
            } catch (IOException e) {
                System.err.println("Failed to create file!" + e.getMessage());
            }
        }
    }

    private static void editFileContentsForDeletion(int taskNum) {
        File temp = new File("data/temp.txt");
        if (!temp.exists()) {
            try {
                Files.copy(Paths.get("data/duke.txt"), Paths.get("data/temp.txt"), REPLACE_EXISTING);
                new FileWriter(fileAddress, false).close();
                Scanner s = new Scanner(new File("data/temp.txt"));
                int count = 1;
                while (s.hasNextLine()) {
                    String command = s.nextLine();
                    System.out.println(command);
                    if (count == taskNum) {
                        count++;
                    } else {
                        appendToFile("data/duke.txt", command);
                        count++;
                    }
                }
                s.close();
                Files.delete(Paths.get("data/temp.txt"));
            } catch (IOException e) {
                System.err.println("Failed to create file!" + e.getMessage());
            }
        }
    }

    /**
     * Prints out text to say goodbye to user.
     */
    private static void byeCommand() {
        System.out.println("    ______________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ______________________________________");
    }

    /**
     * Prints out the current list of tasks the user has.
     *
     * @param command entered by user.
     * @throws DukeException upon invalid commands or empty tasks list.
     */
    private static void printList(String command) throws DukeException {
        String[] words = command.split(" ");
        if (words.length > 1) {
            throw new DukeException("invalidCommand");
        } else if (storage.isEmpty()) {
            throw new DukeException("noTasksException");
        } else {
            System.out.println("    ______________________________________");
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < storage.size(); i++) {
                System.out.printf("     %d.%s\n", i + 1, storage.get(i).toString());
            }
            System.out.println("    ______________________________________");
        }
    }

    /**
     * Adds a ToDo to the list of tasks.
     *
     * @param command entered by the user.
     * @throws DukeException upon invalid command format.
     */
    private static void addToDo(String command, boolean printOutput) throws DukeException {
        if (command.length() < 6) {
            throw new DukeException("invalidToDo");
        } else {
            String name = command.substring(5);
            Task task  = new ToDo(name);
            storage.add(task);
            if (printOutput) {
                try {
                    appendToFile(fileAddress, "T - 0 - " + name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("    ______________________________________");
                System.out.println("     Got it. I've added this task: ");
                System.out.printf("       %s\n",task);
                System.out.printf("     Now you have %d tasks in the list\n", storage.size());
                System.out.println("    ______________________________________");
            }
        }

    }


    /**
     * Adds a deadline to the list of tasks.
     *
     * @param command entered by the user.
     * @throws DukeException upon invalid command format.
     */
    private static void addDeadline(String command, boolean printOutput) throws DukeException {
        String[] words = command.split(" ");
        if (words.length <= 3 ) {
            throw new DukeException("invalidDeadline");
        } else if (!command.contains("/by")) {
            throw new DukeException("invalidDeadline");
        } else {
            int position = command.indexOf("/by");
            String name = command.substring(9, position);
            String date = command.substring(position + 4);
            Task task  = new Deadline(name, date);
            storage.add(task);
            if (printOutput) {
                try {
                    appendToFile(fileAddress, "D - 0 - " + name + " - " + date);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("    ______________________________________");
                System.out.println("     Got it. I've added this task: ");
                System.out.printf("       %s\n",task);
                System.out.printf("     Now you have %d tasks in the list\n", storage.size());
                System.out.println("    ______________________________________");
            }
        }
    }


    /**
     * Adds event to the list of tasks.
     *
     * @param command entered by the user.
     * @throws DukeException upon invalid command format.
     */
    private static void addEvent(String command, boolean printOutput) throws DukeException {
        String[] words = command.split(" ");
        if (words.length <= 3 ) {
            throw new DukeException("invalidEvent");
        } else if (!command.contains("/at")) {
            throw new DukeException("invalidEvent");
        } else {
            int position = command.indexOf("/at");
            String name = command.substring(6, position);
            String date = command.substring(position + 4);
            Task task  = new Event(name, date);
            storage.add(task);
            if (printOutput) {
                try {
                    appendToFile(fileAddress, "E - 0 - " + name + " - " + date);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("    ______________________________________");
                System.out.println("     Got it. I've added this task: ");
                System.out.printf("       %s\n",task);
                System.out.printf("     Now you have %d tasks in the list\n", storage.size());
                System.out.println("    ______________________________________");
            }
        }
    }

    /**
     * Marks a specific task in the list as completed.
     *
     * @param command entered by the user.
     * @throws DukeException upon incorrect command format.
     */
    private static void markCompleted(String command, boolean printOutput) throws DukeException {
        String restOfCommand = command.substring(5);
        boolean numeric;
        try {
            int temp = Integer.parseInt(restOfCommand);
            numeric = true;
        } catch (NumberFormatException err) {
            numeric = false;
        }
        if (numeric) {
            int taskNum = Integer.parseInt(restOfCommand) - 1;
            if (taskNum < storage.size()) {
                Task currTask = storage.get(taskNum);
                currTask.setCompleted();
                if (printOutput) {
                    editFileContentsForCompletion(taskNum + 1);
                    System.out.println("    ______________________________________");
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.printf("       %s\n", currTask);
                    System.out.println("    ______________________________________");
                }
            } else {
                throw new DukeException("invalidTaskNumber");
            }
        } else {
            throw new DukeException("invalidNumberFormat");
        }
    }

    /**
     * Deletes a specified task from the list of tasks.
     *
     * @param command entered by the user.
     * @throws DukeException upon incorrect command format.
     */
    private static void deleteTask(String command) throws DukeException {
        String restOfCommand = command.substring(7);
        boolean numeric;
        try {
            int temp = Integer.parseInt(restOfCommand);
            numeric = true;
        } catch (NumberFormatException err) {
            numeric = false;
        }
        if (numeric) {
            int taskNum = Integer.parseInt(restOfCommand) - 1;
            if (taskNum < storage.size()) {
                editFileContentsForDeletion(taskNum + 1);
                Task currTask = storage.get(taskNum);
                storage.remove(taskNum);
                System.out.println("    ______________________________________");
                System.out.println("     Noted. I've removed this task:");
                System.out.printf("       %s\n", currTask);
                System.out.printf("     Now you have %d tasks in the list.\n", storage.size());
                System.out.println("    ______________________________________");
            } else {
                throw new DukeException("invalidTaskNumber");
            }
        } else {
            throw new DukeException("invalidNumberFormatDelete");
        }
    }

    private static void commands() throws DukeException {
        int pointer = 1;
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?");
        System.out.println("__________________________________________");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            String[] words = command.split(" ");
            String init = words[0];
            if (!command.equals("bye")) {
                try {
                    switch (init) {
                    case ("list"):
                        printList(command);
                        break;
                    case ("todo"):
                        addToDo(command, true);
                        break;
                    case ("deadline"):
                        addDeadline(command, true);
                        break;
                    case ("event"):
                        addEvent(command, true);
                        break;
                    case ("done"):
                        markCompleted(command, true);
                        break;
                    case ("delete"):
                        deleteTask(command);
                        break;
                    default:
                        throw new DukeException("invalidCommand");
                    }
                } catch (DukeException err) {
                    System.out.println("    ______________________________________");
                    System.out.printf("     %s\n", err);
                    System.out.println("    ______________________________________");
                }
            }
            else {
                byeCommand();
                scanner.close();
                break;
            }
        }
    }




}
