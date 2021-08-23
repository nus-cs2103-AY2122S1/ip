import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
    //private static ArrayList<Task> store = new ArrayList<>();
    private static final String fileAddress = "data/duke.txt";
    private static Storage storage;
    private static TaskList tasks;
    private Ui ui;

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    private void run() {
        commands();
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            //ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void editFileContentsForDeletion(int taskNum) {
        File temp = new File("data/temp.txt");
        if (!temp.exists()) {
            try {
                Files.copy(Paths.get("data/duke.txt"), Paths.get("data/temp.txt"), REPLACE_EXISTING);
                new FileWriter("data/duke.txt", false).close();
                Scanner s = new Scanner(new File("data/temp.txt"));
                int count = 1;
                while (s.hasNextLine()) {
                    String command = s.nextLine();
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

    public static void appendToFile(String filePath, String textToAppend) throws IOException {
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


    /**
     * Adds a ToDo to the list of tasks.
     *
     * @param command entered by the user.
     * @throws DukeException upon invalid command format.
     */
    public static void addToDo(String command, boolean printOutput) throws DukeException {
        if (command.length() < 6) {
            throw new DukeException("invalidToDo");
        } else {
            String name = command.substring(5);
            Task task  = new ToDo(name);
            tasks.add(task);
            if (printOutput) {
                try {
                    appendToFile(fileAddress, "T - 0 - " + name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("    ______________________________________");
                System.out.println("     Got it. I've added this task: ");
                System.out.printf("       %s\n",task);
                System.out.printf("     Now you have %d tasks in the list\n", tasks.size());
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
    public static void addDeadline(String command, boolean printOutput) throws DukeException {
        String[] words = command.split(" ");
        if (words.length <= 3 ) {
            throw new DukeException("invalidDeadline");
        } else if (!command.contains("/by")) {
            throw new DukeException("invalidDeadline");
        } else {
            try {
                LocalDate.parse(words[3]);
                int position = command.indexOf("/by");
                String name = command.substring(9, position);
                String date = command.substring(position + 4);
                Task task  = new Deadline(name, date);
                tasks.add(task);
                if (printOutput) {
                    appendToFile(fileAddress, "D - 0 - " + name + " - " + date);
                    System.out.println("    ______________________________________");
                    System.out.println("     Got it. I've added this task: ");
                    System.out.printf("       %s\n",task);
                    System.out.printf("     Now you have %d tasks in the list\n", tasks.size());
                    System.out.println("    ______________________________________");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DateTimeParseException e) {
                System.out.println("Enter valid date format!");
            }
        }
    }


    /**
     * Adds event to the list of tasks.
     *
     * @param command entered by the user.
     * @throws DukeException upon invalid command format.
     */
    public static void addEvent(String command, boolean printOutput) throws DukeException {
        String[] words = command.split(" ");
        if (words.length <= 3 ) {
            throw new DukeException("invalidEvent");
        } else if (!command.contains("/at")) {
            throw new DukeException("invalidEvent");
        } else {
            try {
                LocalDate.parse(words[3]);
                int position = command.indexOf("/at");
                String name = command.substring(6, position);
                String date = command.substring(position + 4);
                Task task  = new Event(name, date);
                tasks.add(task);
                if (printOutput) {
                    appendToFile(fileAddress, "E - 0 - " + name + " - " + date);
                    System.out.println("    ______________________________________");
                    System.out.println("     Got it. I've added this task: ");
                    System.out.printf("       %s\n",task);
                    System.out.printf("     Now you have %d tasks in the list\n", tasks.size());
                    System.out.println("    ______________________________________");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DateTimeParseException e) {
                System.out.println("Enter valid date format!");
            }
        }
    }

    /**
     * Marks a specific task in the list as completed.
     *
     * @param command entered by the user.
     * @throws DukeException upon incorrect command format.
     */
    public static void markCompleted(String command, boolean printOutput) throws DukeException {
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
            if (taskNum < tasks.size()) {
                Task currTask = tasks.get(taskNum);
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
    private void deleteTask(String command) throws DukeException {
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
            if (taskNum < tasks.size()) {
                editFileContentsForDeletion(taskNum + 1);
                Task currTask = tasks.get(taskNum);
                tasks.remove(taskNum);
                System.out.println("    ______________________________________");
                System.out.println("     Noted. I've removed this task:");
                System.out.printf("       %s\n", currTask);
                System.out.printf("     Now you have %d tasks in the list.\n", tasks.size());
                System.out.println("    ______________________________________");
            } else {
                throw new DukeException("invalidTaskNumber");
            }
        } else {
            throw new DukeException("invalidNumberFormatDelete");
        }
    }

    private void commands() throws DukeException {
            String command = ui.readCommand();
            while (!command.equals(null)) {
                String parsed = Parser.parse(command);
                if (!command.equals("bye")) {
                    try {
                        switch (parsed) {
                            case ("list"):
                                tasks.printTaskList(command);
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
                        ui.showError(err.toString());
                    }
                }
                else {
                    ui.end();
                }
            }

    }




}
