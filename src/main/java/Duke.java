import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class is an implementation of Project Duke customised to be named LOTTERY-A
 * that stands for List of Tasks That Eventually Require Your Attention.
 * It is a CLI designed to work as a todolist of sorts.
 *
 * CS2103T ip
 * AY21/22 Semester 1
 *
 * @author Kishendran Vendar Kon (Group G05)
 */
public class Duke {
    /** Arraylist that represents list of Tasks. */
    static List<Task> toDoList = new ArrayList<>();

    /**
     * Core function of bot that opens scanner and reads user input to decide what to do next.
     */
    public static void runDuke() {
        String dottedLines = "----------------------------------------------------------------------------------------";
        System.out.println(dottedLines);
        System.out.println("Hello I'm LOTTERY-A");
        System.out.println("Also known as the List Of Tasks That Eventually Require Your Attention");
        System.out.println("What can I do for you?");
        System.out.println(dottedLines);

        Scanner keyboard = new Scanner(System.in);

        label:
        while (keyboard.hasNext()) {
            String userInput = keyboard.next();
            String userDescription = keyboard.nextLine();
            if (!userDescription.isEmpty()) {
                userDescription = userDescription.substring(1);
            }
            try {
                switch (userInput) {
                    case "bye":
                        System.out.println(dottedLines);
                        System.out.println("Bye. Don't forget, these tasks will still require your attention when you return!");
                        System.out.println(dottedLines);
                        break label;
                    case "list":
                        for (int i = 0; i < toDoList.size(); i++) {
                            System.out.println(i + 1 + "." + toDoList.get(i).toString());
                        }
                        break;
                    case "done": {
                        if (userDescription.isBlank()) {
                            throw new DukeException("OOPS!!! The description of done cannot be empty");
                        }

                        String regex = "[0-9]+";

                        if (!userDescription.matches(regex)) {
                            throw new DukeException("OOPS!!! The description of done must be an integer ");
                        }

                        int targetTask = Integer.parseInt(userDescription);

                        if (targetTask < 1 || targetTask > toDoList.size()) {
                            throw new DukeException("OOPS!!! Invalid task number");
                        }

                        toDoList.get(targetTask - 1).markAsDone();
                        break;
                    }
                    case "delete": {
                        if (userDescription.isBlank()) {
                            throw new DukeException("OOPS!!! The description of delete cannot be empty");
                        }

                        String regex = "[0-9]+";

                        if (!userDescription.matches(regex)) {
                            throw new DukeException("OOPS!!! The description of delete must be an integer ");
                        }

                        int targetTask = Integer.parseInt(userDescription);

                        if (targetTask < 1 || targetTask > toDoList.size()) {
                            throw new DukeException("OOPS!!! Invalid task number");
                        }

                        System.out.println(dottedLines);
                        System.out.println("Noted. I've removed this task:\n" + toDoList.get(targetTask - 1).toString());
                        toDoList.remove(targetTask - 1);
                        System.out.println("Now you have " + toDoList.size() + " tasks in the list");
                        System.out.println(dottedLines);

                        break;
                    }
                    case "todo":
                        if (userDescription.isBlank()) {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty");
                        }

                        Todo todo = new Todo(userDescription);
                        toDoList.add(todo);
                        System.out.println(dottedLines);
                        System.out.println("Got it. I've added this task:\n" + todo.toString());
                        System.out.println("Now you have " + toDoList.size() + " tasks in the list");
                        System.out.println(dottedLines);
                        break;
                    case "deadline": {
                        int dueByIndex = userDescription.indexOf("/by");
                        if (userDescription.isBlank() || dueByIndex == 0) {
                            throw new DukeException("OOPS!!! The description of a deadline cannot be empty");
                        }

                        if (dueByIndex == -1) {
                            throw new DukeException("OOPS!! No deadline has been set. Reinput with '/by dueBy'");
                        }

                        String desc = userDescription.substring(0, dueByIndex);
                        System.out.println("A");
                        String dueBy = userDescription.substring(dueByIndex + 3);
                        System.out.println("B");
                        if (dueBy.isBlank()) {
                            throw new DukeException("OOPS!!! The description of /by cannot be empty");
                        }

                        Deadline deadline = new Deadline(desc, dueBy);
                        toDoList.add(deadline);
                        System.out.println(dottedLines);
                        System.out.println("Got it. I've added this task:\n" + deadline.toString());
                        System.out.println("Now you have " + toDoList.size() + " tasks in the list");
                        System.out.println(dottedLines);
                        break;
                    }
                    case "event": {
                        int duringIndex = userDescription.indexOf("/at");
                        if (userDescription.isBlank() || duringIndex == 0) {
                            throw new DukeException("OOPS!!! The description of an event cannot be empty");
                        }

                        if (duringIndex == -1) {
                            throw new DukeException("OOPS!! No event time has been set. Reinput Event with '/at time'");
                        }

                        String desc = userDescription.substring(0, duringIndex);
                        String during = userDescription.substring(duringIndex + 3);

                        if (during.isBlank()) {
                            throw new DukeException("OOPS!!! The description of /at cannot be empty");
                        }

                        Event event = new Event(desc, during);
                        toDoList.add(event);
                        System.out.println(dottedLines);
                        System.out.println("Got it. I've added this task:\n" + event.toString());
                        System.out.println("Now you have " + toDoList.size() + " tasks in the list");
                        System.out.println(dottedLines);
                        break;
                    }
                    default:
                        throw new DukeException("OOPS!!! I'm Sorry, but I don't know what that means");
                }
            } catch (DukeException e) {
                System.out.print(e);
            }

        }
    }

    public static void main(String[] args) {
        Duke.runDuke();
    }
}
