import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Class that handles the behaviour of the bot in response to user inputs
 */
public class Duke {
    private static final Scanner sc = new Scanner(System.in);
    private static boolean stop = false;
    private static ArrayList<Task> taskList = new ArrayList<Task>();
    // Regex to check if string is numbers
    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    private static String content;
    private static Path p = Paths.get("/Users/yinruoyan/Documents/GitHub/ip/data/duke.txt");
    // private static Path p = Paths.get("./Users/yinruoyan/Documents/GitHub/ip/data/src/duke.txt");
//    private static Path p = Paths.get("./Users/yinruoyan/Documents/GitHub/ip/data/duke.txt");
//    private static Path p = Paths.get("../../../data/duke.txt");

    /**
     * Method to call when user wishes to see a list of all events
     * @param input The input string by user
     */
    public static void listMethod(String input) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            System.out.println((i+1) + "." + currTask.toString());
        }
    }

    /**
     * Method to call when user wishes to stop the bot
     */
    public static void stopMethod() {
        stop = true;
        System.out.print("Bye. Hope to see you again soon!");
    }

    /**
     * Method to call when user wishes to mark an item as done
     * @param input The input string by user
     */
    public static void doneMethod(String input) throws DukeException{
        String numberString = String.valueOf(input.charAt(5));
        if (!pattern.matcher(numberString).matches()) {
            throw new DukeException("Please enter \'done [index of item]\' to mark item as done.");
        }
        int number = Integer.parseInt(String.valueOf(numberString));
        if (number > taskList.size() || number < 0) {
            throw new DukeException("Item does not exist, we cannot mark it as done.");
        }
        Task currTask = taskList.get(number - 1);
        if (currTask.getStatusIcon().equals(String.valueOf('X'))) {
            throw new DukeException("Item is already marked as done, we cannot mark it as done again.");
        }
        taskList.get(number - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(number-1).toString());
    }

    /**
     * Method to call when user wishes to add a new task
     * @param input The input string by user
     */
    public static void taskMethod(String input) throws DukeException{
        if (!input.startsWith("todo") && !input.startsWith("deadline") && !input.startsWith("event")) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        Task currTask;

        if (input.startsWith("todo")) {
            if (input.length() == 4) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            String taskDesc = input.substring(5);
            currTask = new Todo(taskDesc);
        }
        else if (input.startsWith("deadline")) {
            if (input.length() == 8) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            int byIndex = input.indexOf("/");
            String by = input.substring(byIndex+4);
            String taskDesc = input.substring(9, byIndex-1);
            currTask = new Deadline(taskDesc, by);
        }
        else {
            if (input.length() == 5) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            }
            int byIndex = input.indexOf("/");
            String by = input.substring(byIndex+4);
            String taskDesc = input.substring(6, byIndex-1);
            currTask = new Event(taskDesc,by);
        }
        taskList.add(currTask);
        System.out.println("Got it. I have added this task:");
        System.out.println(currTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Method to call when user wishes to see a list of all events
     * @param input The input string by user
     */
    public static void deleteMethod(String input) throws DukeException{
        // int number = Integer.parseInt(String.valueOf(input.charAt(7)));
        String numberString = String.valueOf(input.charAt(7));
        if (!pattern.matcher(numberString).matches()) {
            throw new DukeException("Please enter \'delete [index of item]\' to mark item as done.");
        }
        int number = Integer.parseInt(String.valueOf(numberString));
        if (number > taskList.size() || number < 0) {
            throw new DukeException("Item does not exist, we cannot delete it.");
        }
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.get(number-1).toString());
        taskList.remove(number - 1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void loadFile() {
        File dukeData = new File(String.valueOf(p));
        try {
            taskList.clear();
            Scanner sc = new Scanner(dukeData);
            while (sc.hasNext()) {
                String currLine = sc.nextLine();
                String[] parts = currLine.trim().split(Pattern.quote(" | "));

                String taskType = parts[0];
                String taskDone = parts[1];
                String taskDesc = parts[2];

                Task currTask;
                if (taskType == "D") {
                   currTask = new Deadline(taskDesc, parts[3]);
                } else if (taskType == "E") {
                    currTask = new Event(taskDesc, parts[3]);
                } else {
                    currTask = new Todo(taskDesc);
                }

                if (taskDone == "1") {
                    currTask.markAsDone();
                }
                taskList.add(currTask);
            }
        } catch (FileNotFoundException e) {
            File file = new File(String.valueOf(p));
        }
    }

    public static void updateFile() {
        String allLines = "";

        for (Task t : taskList) {
            String currLine = "";

            // Checking for the type of task
            if (t instanceof Todo) {
                currLine += "T | ";
            } else if (t instanceof Event) {
                currLine += "E | ";
            } else {
                currLine += "D | ";
            }

            // Checking if the task is done
            if (t.isDone) {
                currLine += "1 | ";
            } else {
                currLine += "0 | ";
            }

            // Add in the task description
            currLine += t.description;

            // Add in the task deadline / time
            if (t instanceof Deadline) {
                currLine += " | ";
               currLine += ((Deadline)t).by;
            } else if (t instanceof Event) {
                currLine += " | ";
               currLine += ((Event)t).at;
            }
            allLines += currLine;
            allLines += System.lineSeparator();
        }
        try {
//        try (BBufferedWriter writer = Files.newBufferedWriter(p, StandardOpenOption.APPEND)ufferedWriter writer = Files.newBufferedWriter(p, StandardOpenOption.APPEND)) {
            FileWriter writer = new FileWriter(String.valueOf(p));
            writer.write(allLines);
            writer.close();

        } catch (IOException ioe) {
            System.err.format("IOException: %s%n, unable to update duke.txt", ioe);
        }
    }

    /**
     * Method to call when user wishes to see a list of all events
     */
    public static void main(String[] args) {
//        File dir = new File("tmp/test");
//        dir.mkdirs();
//        File tmp = new File(dir, "tmp.txt");
//        tmp.createNewFile();

        System.out.println("Hello! I'm Duke\n What can I do for you?");

        try {
            loadFile();
            while (!stop) {
                String input = sc.nextLine();

                if (input.equals("list")) {
                    listMethod(input);
                } else if (input.equals("bye")) {
                    stopMethod();
                    updateFile();
                    break;
                } else if (input.startsWith("done")) {
                    doneMethod(input);
                    updateFile();
                } else if (input.startsWith("delete")) {
                    deleteMethod(input);
                    updateFile();
                } else {
                    taskMethod(input);
                    updateFile();
                }
            }
        }
        catch (DukeException e) {
            System.out.println("Caught the exception");
            System.out.print("Exception occurred: " + e);
        }
    }
}