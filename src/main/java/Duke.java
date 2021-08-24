import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void markAsDone(String s) throws DukeException {
        try {
            int taskNum = Integer.parseInt(s.substring(5));
            Task curr = tasks.get(taskNum - 1);
            curr.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n  "
                    + curr.toString());
        } catch (NumberFormatException nfe) {
            throw new DukeException("Please only enter an integer after command 'done'!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number " + s.substring(5) + " does not exist!");
        }
    }

    public static void deleteTask(String s) throws DukeException {
        try {
            int taskNum = Integer.parseInt(s.substring(7));
            Task curr = tasks.remove(taskNum - 1);
            System.out.println("Noted. I've removed this task:\n  "
                    + curr.toString()
                    + "\nNow you have " + tasks.size() + " tasks in the list.");
        } catch (NumberFormatException nfe) {
            throw new DukeException("Please only enter an integer after command 'delete'!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number " + s.substring(7) + " does not exist!");
        }
    }

    public static void addToDo(String s) throws DukeException {
        try {
            Task curr = new ToDo(s.substring(5));
            tasks.add(curr);
            System.out.println("Got it. I've added this task:\n  "
                    + curr.toString() + "\nNow you have " + tasks.size()
                    + " task(s) in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    public static void addEvent(String s) throws DukeException {
        try {
            int at = s.lastIndexOf(" /at ");
            Task curr = new Event(
                    s.substring(6, at),
                    LocalDateTime.parse(s.substring(at + 5), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
            );
            tasks.add(curr);
            System.out.println("Got it. I've added this task:\n  "
                    + curr.toString() + "\nNow you have " + tasks.size()
                    + " task(s) in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The description and time of an event cannot be empty.");
        } catch (DateTimeParseException e) {
            throw new DukeException("The time must be in this format: yyyy-MM-dd HH:mm");
        }
    }

    public static void addDeadline(String s) throws DukeException {
        try {
            int by = s.lastIndexOf(" /by ");
            Task curr = new Deadline(
                    s.substring(9, by),
                    LocalDateTime.parse(s.substring(by + 5), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
            );
            tasks.add(curr);
            System.out.println("Got it. I've added this task:\n  "
                    + curr.toString() + "\nNow you have " + tasks.size()
                    + " task(s) in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The description and time of a deadline cannot be empty.");
        } catch (DateTimeParseException e) {
            throw new DukeException("The time must be in this format: yyyy-MM-dd HH:mm");
        }
    }

    public static void saveTasks() {
        String textToAdd = "";

        for (int i = 0; i < tasks.size(); i++) {
            textToAdd = textToAdd + tasks.get(i).toString() + System.lineSeparator();
        }

        try {
            FileWriter file = new FileWriter("data/duke.txt");
            file.write(textToAdd);
            file.close();
        } catch (IOException e) {
            System.out.println("There is a problem writing saved data.");
        }
    }

    public static void loadTasks(){
        try {
            File dataDirectory = new File("data");
            dataDirectory.mkdir();
            File savedData = new File("data/duke.txt");
            savedData.createNewFile();
            Scanner savedTasks = new Scanner(savedData);

            while (savedTasks.hasNextLine()) {
                String curr = savedTasks.nextLine();

                if (curr.startsWith("[T]")) {
                    Task currentTask = new ToDo(curr.substring(7));
                    tasks.add(currentTask);
                } else if (curr.startsWith("[E]")) {
                    int at = curr.lastIndexOf(" (at: ");
                    int end = curr.lastIndexOf(")");
                    Task currentTask = new Event(
                            curr.substring(7, at),
                            LocalDateTime.parse(curr.substring(at + 6, end),
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                    );
                    tasks.add(currentTask);
                } else if (curr.startsWith("[D]")) {
                    int by = curr.lastIndexOf(" (by: ");
                    int end = curr.lastIndexOf(")");
                    Task currentTask = new Deadline(
                            curr.substring(7, by),
                            LocalDateTime.parse(curr.substring(by + 6, end),
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                    );
                    tasks.add(currentTask);
                } else {
                    // do nothing
                }
            }

        } catch (IOException e) {
            System.out.println("There is a problem loading saved data.");
        }
    }

    public static void main(String[] args) {
        System.out.println("*******************************************");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("*******************************************");

        Scanner myScanner= new Scanner(System.in); // open scanner
        boolean hasQuit = false;
        loadTasks();

        while (!hasQuit && myScanner.hasNextLine()) {
            String userInput = myScanner.nextLine();

            try {
                if (userInput.equals("bye")) { // if user enters "bye"
                    hasQuit = true;
                    System.out.println("Bye. Hope to see you again soon!");
                } else if (userInput.equals("list")) { // if user enters "list"
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task curr = tasks.get(i);
                        int taskNum = i + 1;
                        System.out.println(taskNum + "." + curr.toString());
                    }
                } else if (userInput.startsWith("done")) {
                    markAsDone(userInput);
                    saveTasks();
                } else if (userInput.startsWith("delete")) {
                    deleteTask(userInput);
                    saveTasks();
                } else if (userInput.startsWith("todo")) {
                    addToDo(userInput);
                    saveTasks();
                } else if (userInput.startsWith("event")) {
                    addEvent(userInput);
                    saveTasks();
                } else if (userInput.startsWith("deadline")) {
                    addDeadline(userInput);
                    saveTasks();
                } else { // any other input from user
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("*******************************************");
        }
        myScanner.close(); // close scanner
    }
}

