import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Duke {
    
    static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
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
                    System.out.println(list(""));
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
    public static String list(String str) {
        for (int i = 0; i < tasks.size(); i++) {
            str += (i+1) + ". " + tasks.get(i);
            if (i != tasks.size() - 1) {
                str += "\n";
            }
        }
        return str;
    }

    /**
     * A method to mark the task as done
     * @param str The input String
     * @return The output that you want to be printed out in the console
     */
    public static String markDone(String str) {
        int a = Integer.parseInt(str.substring(5)) - 1;
        tasks.get(a).taskDone();
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
            Task task = new Todo(str);
            tasks.add(task);
            return "Got it. I've added this task: \n"
                    + task
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
        return "Got it. I've added this task: \n"
                + t
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
    
    public static String formatDate(String unformattedDate) throws DateTimeParseException{
        LocalDate date = LocalDate.parse(unformattedDate);
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}

