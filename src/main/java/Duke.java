import java.io.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A chat bot named Duke that is responsive to commands.
 * 
 * @author Raveen Prabhu 
 */


public class Duke {
    
    private static List<Task> tasks = new ArrayList<>();
    
    private static final String PATH = "src/data";
    private static final String FILENAME = "duke.txt";

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        String horizontalLines = "---------------------------------";
        System.out.println(horizontalLines);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(horizontalLines);
        
        readFile(PATH + "/" + FILENAME);
        
        run();
    }

    public static void run() {
        String horizontalLines = "---------------------------------";
        boolean end = false;
        int i = 0;
        Scanner sc = new Scanner(System.in);

        while (!end) {

            String str = sc.nextLine();
            str = str.trim();
            try {
                // command "bye"
                if (str.equals("bye")) {
                    end = true;
                    System.out.println(horizontalLines);
                    System.out.println("Bye. Hope to see you again soon!");
                    sc.close();
                }

                //command done
                else if (str.contains("done")) {
                    System.out.println(horizontalLines);
                    System.out.println(markDone(str));
                }

                //command list
                else if (str.equals("list")) {
                    System.out.println(horizontalLines);
                    System.out.println(showList(""));
                }

                //command to do
                else if (str.contains("todo")) {
                    System.out.println(horizontalLines);
                    System.out.println(todoTask(str));
                }

                //command deadline
                else if (str.contains("deadline")) {
                    System.out.println(horizontalLines);
                    System.out.println(deadlineTask(str));
                }

                //command event
                else if (str.contains("event")) {
                    System.out.println(horizontalLines);
                    System.out.println(eventsTask(str));
                }

                else if (str.contains("delete")) {
                    System.out.println(horizontalLines);
                    System.out.println(deleteTask(str));
                }

                else {
                    throw new DukeException("Command is not valid!");
                }
            } catch(DukeException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        }
        System.out.println(horizontalLines);
    }
    
    public static String showList(String str) {
        for (int i = 0; i < tasks.size(); i++) {
            str += (i+1) + ". " + tasks.get(i);
            if (i != tasks.size() - 1) {
                str += "\n";
            }
        }
        return str;
    }
    
    /////// tomarkasdone method gone

    /**
     * A method to mark the task as done
     * @param str The input String
     * @return The output that you want to be printed out in the console
     */
    public static String markDone(String str) {
        int a = Integer.parseInt(str.substring(5)) - 1;
        tasks.get(a).taskDone();
        getAllTasks();
        return "Nice! I've marked this task as done: \n" + tasks.get(a);
    }

    /**
     * A method to add a to-do task
     * @param str A string the that is the command
     * @return The output that you want to be printed out in the console
     */
    public static String todoTask(String str) throws DukeException {
        try {
            str = str.substring(5);
            Task t = new Todo(str);
            tasks.add(t);
            writeLine(t);
            return "Got it. I've added this task: \n"
                    + t
                    + "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (StringIndexOutOfBoundsException e) {
            return "☹ OOPS!!! The description of a todo cannot be empty.";
        }

    }

    /**
     * A method to add a create deadline task
     * @param str A string the that is the command
     * @return The output that you want to be printed out in the console
     */
    public static String deadlineTask(String str) {
        try {
            int i = str.indexOf("/");
            String day = str.substring(i + 4, i + 14);
            String time = str.substring(i + 14);
            Task t = new Deadline(str.substring(0, i), formatDate(day) + time);
            tasks.add(t);
            writeLine(t);
            return "Got it. I've added this task: \n"
                    + t
                    + "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (StringIndexOutOfBoundsException e) {
            return "☹ OOPS!!! The description of a todo cannot be empty.";
        } catch (DateTimeParseException e) {
            return "☹ OOPS!!! Please use the date format: yyyy-mm-dd.";
        }
    }

    /**
     * A method to add a create task
     * @param str A string the that is the command
     * @return The output that you want to be printed out in the console
     */
    public static String eventsTask(String str) {
        try {
            int i = str.indexOf("/");
            String day = str.substring(i + 4, i + 14);
            Task t = new Events(str.substring(0, i), day);
            tasks.add(t);
            writeLine(t);
            return "Got it. I've added this task: \n"
                    + t
                    + "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (StringIndexOutOfBoundsException e) {
            return "☹ OOPS!!! The description of a todo cannot be empty.";
        } catch (DateTimeParseException e) {
            return "☹ OOPS!!! Please use the date format: yyyy-mm-dd.";
        }
    }

    /**
     * A method to delete a task
     * @param str A string the that is the command
     * @return The output that you want to be printed out in the console
     */
    public static String deleteTask(String str) {
        int index = Integer.parseInt(str.substring(7)) - 1;
        Task t = tasks.get(index);
        tasks.remove(index);
        getAllTasks();
        return "Got it. I've added this task: \n"
                + t
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
    
    // SAVE METHODS

    public static void checkForFile(String path) throws IOException {
        File file = new File(path);
        File dir = new File(PATH);

        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public static void readFile(String path) throws IOException {
        
        checkForFile(path);
        Scanner sc = new Scanner(new File(path));
        sc.useDelimiter(Pattern.compile("(\\n)| - "));

        while(sc.hasNext()) {
            String t = sc.next();

            switch(t) {
                case "T":
                    int i = Integer.parseInt(sc.next());
                    String desc = sc.next();
                    Task task = new Todo(desc);
                    if (i == 1) {
                        task.taskDone();
                    }
                    tasks.add(task);
                    break;
                case "D":
                    i = Integer.parseInt(sc.next());
                    desc = sc.next();
                    String by = sc.next();
                    task = new Deadline(desc, by);
                    if (i == 1) {
                        task.taskDone();
                    }
                    tasks.add(task);
                    break;
                case "E":
                    i = Integer.parseInt(sc.next());
                    desc = sc.next();
                    String at = sc.next();
                    task = new Events(desc, at);
                    if (i == 1) {
                        task.taskDone();
                    }
                    tasks.add(task);
                    break;
            }
        }
        sc.close();
    }
    
    public static void getAllTasks() {
        try {
            String all = "";
            for (Task t : tasks) {
                all += t.toStringForFile() + "\n";
                overWrite(PATH + "/" + FILENAME, all);
            } 
        }
        catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    public static void writeLine(Task task) {
        try {
            addToFile(PATH + "/" + FILENAME, task.toStringForFile() + "\n");
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void addToFile(String path, String line) throws IOException {
        checkForFile(path);
        FileWriter writer = new FileWriter(path, true);
        writer.write(line);
        writer.close();
    }

    public static void overWrite(String path, String line) throws IOException {
        checkForFile(path);
        FileWriter writer = new FileWriter(path);
        writer.write(line);
        writer.close();
    }
    
    public static String formatDate(String unformattedDate) throws DateTimeParseException{
        LocalDate date = LocalDate.parse(unformattedDate);
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}

