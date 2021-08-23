import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Duke {

    /**
     * Wraps a given message above and below with lines and prints it.
     *
     * @param msg the message to wrap and print
     */
    private static void wrapPrint(String msg) {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println(msg);
        System.out.println(line);
    }

    /**
     * Lists the elements of a given ArrayList of Tasks and enumerates them.
     *
     * @param lst the given array of Tasks
     * @return a String that looks like an ordered list of Tasks
     */
    private static String listTaskArr(ArrayList<Task> lst) {
        StringBuilder res = new StringBuilder();
        ListIterator<Task> iter = lst.listIterator();

        while (iter.hasNext()) {
            res.append(iter.nextIndex() + 1).append(".").append(iter.next());
            if (iter.hasNext()) {
                res.append("\n");
            }
        }

        return res.toString();
    }

    /**
     * Reads the taskList.txt file and returns an ArrayList<Task> based on it.
     * Returns an empty ArrayList if the file does not exist.
     *
     * @return An ArrayList containing the tasks in the text file
     * @throws DukeException
     */
    private static ArrayList<Task> readTaskList() throws DukeException {
        ArrayList<Task> result = new ArrayList<>();

        // directory and file names
        Path directory = Paths.get("data");
        Path taskFile = directory.resolve("taskList.txt");

        // check if directory and file exist
        // if not return an empty ArrayList<Task>
        if (Files.notExists(directory) || Files.notExists(taskFile)) {
            return result;
        }

        // parse each line
        try {
            Files.lines(taskFile).forEach(line -> {
                    Task newTask = parseInput(line);
                    if (newTask != null) {
                        result.add(newTask);
                    }
                }
            );
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

        return result;
    }

    /**
     * Parses a given string and returns a Task based on that string.
     *
     * @param str the given string to parse
     * @return The Task based on the string. Returns null if the string is invalid.
     */
    private static Task parseInput(String str) {
        String[] strArr = str.split(" \\| ");
        Task newTask;

        // create new task according to input
        switch (strArr[0]) {
        case "T":
            newTask = new ToDo(strArr[2]);
            break;
        case "D":
            newTask = new Deadline(strArr[2], strArr[3]);
            break;
        case "E":
            newTask = new Event(strArr[2], strArr[3]);
            break;
        default:
            newTask = null;
            break;
        }

        return newTask;
    }

    /**
     * Saves the given ArrayList of Tasks into data/taskList.txt.
     * Creates a new directory and file if they do not exist.
     *
     * @param lst the given ArrayList of Tasks
     * @throws DukeException
     */
    private static void saveToFile(ArrayList<Task> lst) throws DukeException {
        // directory and file names
        Path directory = Paths.get("data");
        Path taskFile = directory.resolve("taskList.txt");

        // to create string that's to be saved in the file
        StringBuilder res = new StringBuilder();
        ListIterator<Task> iter = lst.listIterator();

        try {
            // check if directory exists, if it does not then create it
            if (Files.notExists(directory)) {
                Files.createDirectories(directory);
            }

            // check if file exists, if it does not then create it
            if (Files.notExists(taskFile)) {
                Files.createFile(taskFile);
            }

            // create string of tasks
            while (iter.hasNext()) {
                res.append(iter.next().printToFile());
                if (iter.hasNext()) {
                    res.append("\n");
                }
            }

            // write string of tasks into file
            Files.writeString(taskFile, res.toString());

        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        // initialise scanner and String array
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        ArrayList<Task> prevList;
        try {
            taskList = readTaskList();
        } catch (DukeException e) {
            wrapPrint("error reading from taskList file!\nError: " + e.getMessage());
        }
        prevList = new ArrayList<>(taskList);
        String msg = "";

        // Greet
        wrapPrint("Hello! I'm Bob\nWhat can I do for you?");

        // Add, List and Mark done
        String input = sc.nextLine();
        while (!input.equals("bye")){
            try {
                if (input.equals("list")) {
                    msg = listTaskArr(taskList);
                    if (msg.isBlank()) {
                        msg = "No tasks in the list!";
                    }
                } else if (input.startsWith("done ")) {
                    // input validation
                    int num = Integer.parseInt(input.substring(5));
                    if (num < 1 || num > taskList.size()) throw new DukeException("you typed an invalid number: " + num);

                    taskList.get(num - 1).markDone();
                    saveToFile(taskList);
                    msg = "Nice! I've marked this task as done:\n  " + taskList.get(num - 1);
                } else if (input.startsWith("todo ")) {
                    // input validation
                    if (input.length() < 5 || input.substring(5).isBlank()) throw new DukeException("the description of the todo cannot be empty!");

                    ToDo newTodo = new ToDo(input.substring(5));
                    taskList.add(newTodo);
                    saveToFile(taskList);
                    msg = "Got it. I've added this task:\n  " + newTodo + "\nNow you have " + taskList.size();
                    msg = taskList.size() == 1 ? msg + " task in the list" : msg + " tasks in the list.";
                } else if (input.startsWith("deadline ")) {
                    // input validation
                    if (input.length() < 9 || input.substring(9).isBlank()) throw new DukeException("the description of the deadline cannot be empty!");
                    if (!input.contains("/by")) throw new DukeException("you are missing the /by keyword");

                    Deadline newDeadline = new Deadline(input.substring(9));
                    taskList.add(newDeadline);
                    saveToFile(taskList);
                    msg = "Got it. I've added this task:\n  " + newDeadline + "\nNow you have " + taskList.size();
                    msg = taskList.size() == 1 ? msg + " task in the list" : msg + " tasks in the list.";
                } else if (input.startsWith("event ")) {
                    // input validation
                    if (input.length() < 6 || input.substring(6).isBlank()) throw new DukeException("the description of the event cannot be empty!");
                    if (!input.contains("/at")) throw new DukeException("you are missing the /at keyword");

                    Event newEvent = new Event(input.substring(6));
                    taskList.add(newEvent);
                    saveToFile(taskList);
                    msg = "Got it. I've added this task:\n  " + newEvent + "\nNow you have " + taskList.size();
                    msg = taskList.size() == 1 ? msg + " task in the list" : msg + " tasks in the list.";
                } else if (input.startsWith("remove ")) {
                    // input validation
                    int num = Integer.parseInt(input.substring(7));
                    if (num < 1 || num > taskList.size()) throw new DukeException("you typed an invalid number: " + num);

                    Task removed = taskList.remove(num - 1);
                    saveToFile(taskList);
                    msg = "Noted. I've removed this task:\n  " + removed + "\nNow you have " + taskList.size();
                    msg = taskList.size() == 1 ? msg + " task in the list" : msg + " tasks in the list.";
                } else if (input.equals("clear")) {
                    // clears taskList and saves the previous one to prevList
                    prevList = new ArrayList<>(taskList);
                    taskList = new ArrayList<>();

                    saveToFile(taskList);
                    msg = "All entries in the list of tasks have been removed. To undo, type restore";
                } else if (input.equals("restore")) {
                    taskList = new ArrayList<>(prevList);

                    saveToFile(taskList);
                    msg = "This task list was restored:\n" + listTaskArr(taskList);
                } else {
                    throw new DukeException("you typed in something i don't recognise");
                }
                wrapPrint(msg);
            } catch (NumberFormatException e) {
                wrapPrint("oh no! you typed in an invalid input! " + e.getMessage());
            } catch (DukeException e) {
                wrapPrint(e.getMessage());
            }
            input = sc.nextLine();
        }

        // Exit
        wrapPrint("Bye. Hope to see you again soon!");
    }
}
