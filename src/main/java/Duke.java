import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {


    private static final String line = "____________________________________________________________";
    private static ArrayList<Task> tasks = new ArrayList<>();

    private static void Greet() {
        System.out.println("Hello I'm Duke\n" +
                            line +
                            "\nWhat can I do for you?\n" +
                            line);
    }

    private static void printTasks() {
        int index = 0;

        System.out.println("Here are tasks in your list:");
        for(Task t: tasks) {
            index++;
            System.out.println(index + "." + t.toString());
        }
    }

    private static void makeTaskDone (int index) throws DukeException {
        if (index <= 0 || index > tasks.size()) {
            throw new DukeException ("I'm sorry the index is invalid :-(");
        } else {
            System.out.println("Nice! I've marked this task as done:");
            tasks.get(index - 1).markDone();
            System.out.println(tasks.get(index - 1).toString());
        }
    }

    private static void delete(int index) throws DukeException{
        if (index <= 0 || index > tasks.size()) {
            throw new DukeException("I'm sorry the index is invalid :-(");
        } else {
            System.out.println("Noted. I've removed this task:");
            System.out.println(tasks.get(index - 1).toString());
            tasks.remove(index - 1);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }

    private static boolean isValidDate(String date) {
        try{
            String year = date.substring(0, 3);
            String month = date.substring(5, 6);
            String day = date.substring(8, 9);
            Integer.parseInt(year);
            Integer.parseInt(month);
            Integer.parseInt(day);
            return date.charAt(4) == '-' && date.charAt(7) == '-';

        } catch (Exception e) {
            return false;
        }
    }

    private static void DukeOperation(String input) throws DukeException {
        // operation is always the first word of user input.
        String[] allWords = input.split(" ");
        String operation = allWords[0];

        switch (operation) {
            case "bye": {
                System.out.println(" Bye. Hope to see you again soon!");
                break;
            }
            case "list": {
                printTasks();
                break;
            }
            case "done": {
                try {
                    int index = Integer.valueOf(allWords[1]);
                    makeTaskDone(index);
                } catch (DukeException e) {
                    System.out.println(e.toString());
                }
                break;
            }
            case "todo": {
                if (allWords.length == 1) {
                  throw new DukeException("The description of todo cannot be empty");
                } else {
                    String description = input.substring(input.indexOf(allWords[1]));
                    Todo todo = new Todo(description);
                    System.out.println("added: " + todo.toString());
                    tasks.add(todo);
                    System.out.println("Now you have " + tasks.size() + " tasks in your list");
                }
                break;
            }
            case "event": {
                if (allWords.length == 1) {
                    throw new DukeException("The description of event cannot be empty");
                } else {
                    if (input.contains("/at")) {
                        String description = input.substring(input.indexOf(allWords[1]), input.indexOf("/at") - 1);
                        String time = input.substring(input.indexOf("/at") + 4);
                        LocalDate at;

                        if (isValidDate(time)) {
                            at = LocalDate.parse(time);
                        } else {
                            throw new DukeException("Date format is wrong");
                        }
                        Event event = new Event(description, at);
                        System.out.println("added: " + event.toString());
                        tasks.add(event);
                        System.out.println("Now you have " + tasks.size() + " tasks in your list");
                    } else {
                        throw new DukeException("The format of event is wrong");
                    }
                }
                break;
            }
            case "deadline": {
                if (allWords.length == 1) {
                    throw new DukeException("The description of deadline cannot be empty");
                } else {
                    if (input.contains("/by")) {
                        String description = input.substring(input.indexOf(allWords[1]), input.indexOf("/by") - 1);
                        String time = input.substring(input.indexOf("/by") + 4);
                        LocalDate by;

                        if (isValidDate(time)) {
                            by = LocalDate.parse(time);
                        } else {
                            throw new DukeException("Date format is wrong");
                        }
                        Deadline deadline = new Deadline(description, by);
                        System.out.println("added: " + deadline.toString());
                        tasks.add(deadline);
                        System.out.println("Now you have " + tasks.size() + " tasks in your list");
                    } else {
                        throw new DukeException("The format of deadline is wrong");
                    }
                }
                break;
            }
            case "delete": {
                try {
                    int index = Integer.valueOf(allWords[1]);
                    delete(index);
                } catch (DukeException e) {
                    System.out.println(e.toString());
                }
                break;
            }
            default: {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        }
    }


    public static void main(String[] args) {

        String input;
        Scanner keyboardInput = new Scanner(System.in);

        Greet();
        while (true) {
            try {
                input = keyboardInput.nextLine();

                System.out.println(line);
                DukeOperation(input);
                System.out.println(line);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }

}
