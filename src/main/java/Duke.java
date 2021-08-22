import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Duke {
    private static String input = "";
    private static ArrayList<Task> list = new ArrayList<>();
    private static int listLength = 0;

    public static void main(String[] args) {
        String logo = " ____        _ _\n"
                    + "|  _ \\ _   _(_|_)\n"
                    + "| | | | | | | | |\n"
                    + "| |_| | |_| | | |\n"
                    + "|____/ \\__,_|_|_|\n";
        System.out.println(logo);
        greet();

        Scanner sc = new Scanner(System.in);
        input = sc.nextLine().toLowerCase();
        while (!input.equals("bye")) {
            try {
                parseInput(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println();
            } finally {
                input = sc.nextLine().toLowerCase();
                continue;
            }
        }
        exit();
    }

    /**
     * This method parses the input from the user.
     *
     * @param input The input inserted by the user.
     * @throws DukeException This exception is thrown when the input is invalid.
     */
    public static void parseInput(String input) throws DukeException {
        if (input.equals("list")) {
            list();
        } else if (Pattern.matches("done.*", input)) {
            String[] inputArr = input.split(" ");
            if (inputArr.length == 1) {
                throw new DukeException("Indicate the id of the task which you have completed!");
            }
            try {
                markDone(Integer.parseInt(inputArr[1]));
            } catch (DukeException e) {
                throw new DukeException(e.getMessage());
            }
        } else if (Pattern.matches("delete.*", input)) {
            String[] inputArr = input.split(" ");
            if (inputArr.length == 1) {
                throw new DukeException("Indicate the id of the task which you want to remove!");
            }
            try {
                delete(Integer.parseInt(inputArr[1]));
            } catch (DukeException e) {
                throw new DukeException(e.getMessage());
            }
        } else if (Pattern.matches("event.*", input)) {
            String[] inputArr = input.replaceFirst("event ","").split(" /at ");
            if (inputArr.length == 1) {
                throw new DukeException("Input the following format: event *description* /at *DD/MM/YYYY* *24H-Time*");
            } else {
                LocalDateTime dateTime;
                String[] dateTimeArr = inputArr[1].split(" ");
                String[] date = dateTimeArr[0].split("[-/.]");
                int year = Integer.parseInt(date[2]);
                int month = Integer.parseInt(date[1]);
                int dateNum = Integer.parseInt(date[0]);
                if (month > 12 || dateNum > 31) {
                    throw new DukeException("Invalid date given. Try again!");
                }
                if (dateTimeArr.length == 1) {
                    dateTime = LocalDateTime.of(year, month, dateNum, 0, 0);
                } else {
                    String time = dateTimeArr[1];
                    int hour = Integer.parseInt(time.substring(0, 2));
                    int minute =  Integer.parseInt(time.substring(2, 4));
                    if (hour > 23 || minute >= 60) {
                        throw new DukeException("Invalid time given. Try again!");
                    }
                    dateTime = LocalDateTime.of(year, month, dateNum, hour, minute);
                }
                add(new Event(inputArr[0], dateTime));
            }
        } else if (Pattern.matches("deadline.*", input)) {
            String[] inputArr = input.replaceFirst("deadline ", "").split(" /by ");
            if (inputArr.length == 1) {
                throw new DukeException("Input the following format: deadline *description* /by *DD/MM/YYYY* *24H-Time*");
            } else {
                LocalDateTime dateTime;
                String[] dateTimeArr = inputArr[1].split(" ");
                String[] date = dateTimeArr[0].split("[-/.]");
                int year = Integer.parseInt(date[2]);
                int month = Integer.parseInt(date[1]);
                int dateNum = Integer.parseInt(date[0]);
                if (month > 12 || dateNum > 31) {
                    throw new DukeException("Invalid date given. Try again!");
                }
                if (dateTimeArr.length == 1) {
                     dateTime = LocalDateTime.of(year, month, dateNum, 0, 0);
                } else {
                    String time = dateTimeArr[1];
                    int hour = Integer.parseInt(time.substring(0, 2));
                    int minute =  Integer.parseInt(time.substring(2, 4));
                    if (hour > 23 || minute >= 60) {
                        throw new DukeException("Invalid time given. Try again!");
                    }
                    dateTime = LocalDateTime.of(year, month, dateNum, hour, minute);
                }
                add(new Deadline(inputArr[0], dateTime));
            }
        } else if (Pattern.matches("todo.*", input)) {
            String inputArr = input.replaceFirst("todo", "");
            if (inputArr.equals("") || inputArr.equals(" ")) {
                throw new DukeException("Input the following format: todo *description*");
            }
            add(new Task(inputArr.strip()));
        } else {
            throw new DukeException("I don't get what you mean? Try Again!");
        }
    }

    /**
     * This method prints the greetings to the user's terminal.
     */
    public static void greet() {
        System.out.println("Hello! I'm Duii, your personal assistant!");
        System.out.println("What do you need help with?");
        System.out.println();
    }

    /**
     * This method adds the input task into the list.
     *
     * @param task The task to be added to the list.
     */
    public static void add(Task task) throws DukeException {
        list.add(task);
        System.out.println("New Task? I've added it to the list:");
        System.out.println(task.displayInfo());
        listLength++;
        System.out.println(String.format("Now you have %d task(s) in the list.", listLength));
        System.out.println();
    }

    /**
     * This method marks a specific task as completed.
     *
     * @param id The id of the task as per display with the list() method.
     * @throws DukeException This error is thrown if the id specified is invalid.
     */
    public static void markDone(int id) throws DukeException{
        if (id - 1 > listLength) {
            throw new DukeException("The id you entered was invalid!");
        } else {
            System.out.println("You've finished the task? Good job!");
            System.out.println("This task has been marked as done:");
            list.get(id - 1).complete();
            System.out.println(list.get(id - 1).displayInfo());
            System.out.println();
        }
    }

    /**
     * This method deletes specific task from the list.
     *
     * @param id The id of the task as per display with the list() method.
     * @throws DukeException This error is thrown if the id specified is invalid.
     */
    public static void delete(int id) throws DukeException {
        if (id - 1 > listLength) {
            throw new DukeException("The id you entered was invalid!");
        } else {
            System.out.println("Okay! Removing the task:");
            Task removed = list.remove(id - 1);
            System.out.println(removed.displayInfo());
            listLength--;
            System.out.println();
        }
    }

    /**
     * This method enumerates all the tasks in the list.
     */
    public static void list() {
        System.out.println("Here's your current list:");
        for (int i = 0; i < listLength; i++) {
            System.out.println(String.format("%d. %s", i + 1, list.get(i).displayInfo()));
        }
        System.out.println();
    }

    /**
     * This method prints the exit message to the user's terminal.
     */
    public static void exit() {
        System.out.println("You're going already? Hope to see you again soon!");
    }
}
