import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

class User {
    Scanner sc = new Scanner(System.in);
    public String command() {
        if (sc.hasNextLine()) {
            String str = sc.nextLine();
            return str;
        }
        return "";
    }
}
class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void markAsDone() {
        this.isDone = true;
    }
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}
class Deadline extends Task {
    protected LocalDateTime by;
    
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }
    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM d yyyy, h a");
        String formattedDtf = this.by.format(dtf);
        return "[D]" + super.toString() + "(by: " + formattedDtf + ")";
    }
}
class Event extends Task {
    protected LocalDateTime at;
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }
    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM d yyyy, h a");
        String formattedDtf = this.at.format(dtf);
        return "[E]" + super.toString() + " (at: " + formattedDtf + ")";
    }
}
class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
class DukeException extends Exception {
    private String message;
    public DukeException(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return message;
    }
}

class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException(String message) {
        super(message);
    }
    @Override
    public String toString() {
        return super.toString();
    }
}

class InvalidCommandException extends DukeException {
    public InvalidCommandException(String message) {
        super(message);
    }
    public String toString() {
        return super.toString();
    }
}

class InvalidDateTimeException extends DukeException {
    public InvalidDateTimeException(String message) {
        super(message);
    }
    public String toString() {
        return super.toString();
    }
}

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from Duke!");
        System.out.println("Hope you are doing well. How can I help you?");
        User user1 = new User();
        ArrayList<Task> userList = new ArrayList<>();
        while (true) {
            String command = user1.command();
            if (command.equals("")) {
                break;
            }
            if (command.equals("bye")) {
                System.out.println("Bye. Have a great day!");
                break;
            } else if (command.equals("list")) {
                int count = 1;
                for (int i = 0; i < userList.size(); i++) {
                    Task t = userList.get(i);
                    System.out.println(count + ". " + t.toString());
                    count++;
                }
            } else if (command.startsWith("done") && Character.isDigit(command.charAt(command.length() - 1))
                    && command.length() <= 8 && !Character.isAlphabetic(command.charAt(command.length() - 2))
                    && Character.isDigit(command.charAt(5))) {
                int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                if (value > userList.size()) {
                    System.out.println("Sorry the task doesn't exist yet, please try again!");
                } else {
                    Task t = userList.get(value - 1);
                    t.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(t.toString());
                }
            } else {
                if (command.startsWith("todo")) {
                    if (command.length() <= 5) {
                        displayError("todo");
                    } else {
                        Task task = new Todo(command.substring(5));
                        userList.add(task);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task.toString());
                        System.out.println("Now you have " + userList.size() + " tasks in the list.");
                    }
                } else if (command.startsWith("deadline")) {
                    if (command.length() <= 9) {
                        displayError("deadline");
                    } else {
                        String[] parts = command.split("/",2);
                        try {
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                            LocalDateTime dateTime = LocalDateTime.parse(parts[1].substring(3).trim(), dtf);
                            Task task = new Deadline(parts[0].substring(9), dateTime);
                            userList.add(task);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(task.toString());
                            System.out.println("Now you have " + userList.size() + " tasks in the list.");
                        } catch (DateTimeParseException e) {
                            DukeException exp = new InvalidDateTimeException("The format of your command is incorrect! It should be deadline/by <yyyy-mm-dd HHmm>");
                            System.out.println(exp);
                        }
                    }
                } else if (command.startsWith("event")) {
                    if (command.length() <= 6) {
                        displayError("event");
                    } else {
                        String[] parts = command.split("/");
                        try {
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                            LocalDateTime dateTime = LocalDateTime.parse(parts[1].substring(3).trim(), dtf);
                            Task task = new Event(parts[0].substring(6), dateTime);
                            userList.add(task);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(task.toString());
                            System.out.println("Now you have " + userList.size() + " tasks in the list.");
                        } catch (DateTimeParseException e) {
                            DukeException exp = new InvalidDateTimeException("The format of your command is incorrect! It should be event/at <yyyy-mm-dd HHmm>");
                            System.out.println(exp);
                        }
                    }
                } else if (command.startsWith("delete")) {
                    int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                    Task task = userList.get(value-1);
                    userList.remove(value-1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(task);
                    System.out.println("Now you have " + userList.size() + " tasks in the list.");
                } else {
                    DukeException exp = new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println(exp);
                }
            }
        }
    }
    
    private static void displayError(String str) {
        DukeException exp = new EmptyDescriptionException("OOPS!!! The description of a " + str + " cannot be empty.");
        System.out.println(exp);
    }
}
