import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List<Task> savedTasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke!\nWhat can I do for you today?");

        while (sc.hasNextLine()) {
            String userInput = sc.next();
            if (userInput.toLowerCase().equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            }
            else if (userInput.toLowerCase().equals("list")) {
                if (savedTasks.size() == 0) {
                    System.out.println("No tasks in your list! Add one using todo, deadline or event!");
                }
                else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < savedTasks.size(); i++) {
                        Task currTask = savedTasks.get(i);
                        int index = i + 1;
                        System.out.println(index + ". " + currTask);
                    }
                }
            }
            else if (userInput.toLowerCase().equals("done")) {
                int nextNum = sc.nextInt();
                if (nextNum > savedTasks.size() || nextNum < 1) {
                    DukeException exception = new DukeException("Number out of range!");
                    System.out.println(exception);
                }
                else {
                    Task oldTask = savedTasks.get(nextNum-1);
                    Task newTask = oldTask.setDone();
                    savedTasks.set(nextNum-1, newTask);
                    System.out.println("Nice! I've marked this task as done:\n" + newTask);
                }

            }
            else if (userInput.toLowerCase().equals("todo")) {
                String restOfLine = sc.nextLine();
                if (restOfLine.equals("")) {
                    DukeException exception = new DukeException("Description of todo cannot be empty :(");
                    System.out.println(exception);
                }
                else {
                    Task newTask = new Todo(restOfLine);
                    savedTasks.add(newTask);
                    System.out.println("I've added this task:\n" + newTask);
                    System.out.println("Now you have " + savedTasks.size() + " tasks in the list!");
                }

            }
            else if (userInput.toLowerCase().equals("deadline")) {
                String body = "";
                String deadline = "";
                int counter = 0;
                String restOfLine = sc.nextLine();
                String[] words = restOfLine.split(" ");
                for (int i = 1; i < words.length; i++) {
                    if (words[i].equals("/by")) {
                        counter = i+1;
                        break;
                    }
                    else {
                        body += " " + words[i];
                    }
                }
                if (body.equals("")) {
                    DukeException exception = new DukeException("Description of deadline cannot be empty :(");
                    System.out.println(exception);
                    continue;
                }
                if (counter == 0) {
                    DukeException exception = new DukeException("Deadline of deadline cannot be empty :(");
                    System.out.println(exception);
                    continue;
                }
                for (int i = counter; i < words.length; i ++) {
                    deadline += " " + words[i];
                }
                if (deadline.equals("")) {
                    DukeException exception = new DukeException("Deadline of deadline cannot be empty :(");
                    System.out.println(exception);
                    continue;
                }
                Task newTask = new Deadline(body, deadline);
                savedTasks.add(newTask);
                System.out.println("I've added this task:\n" + newTask);
                System.out.println("Now you have " + savedTasks.size() + " tasks in the list!");
            }
            else if (userInput.toLowerCase().equals("event")) {
                String body = "";
                String deadline = "";
                int counter = 0;
                String restOfLine = sc.nextLine();
                String[] words = restOfLine.split(" ");
                for (int i = 1; i < words.length; i++) {
                    if (words[i].equals("/at")) {
                        counter = i+1;
                        break;
                    }
                    else {
                        body += " " + words[i];
                    }
                }
                if (body.equals("")) {
                    DukeException exception = new DukeException("Description of event cannot be empty :(");
                    System.out.println(exception);
                    continue;
                }
                if (counter == 0) {
                    DukeException exception = new DukeException("Date of event cannot be empty :(");
                    System.out.println(exception);
                    continue;
                }
                for (int i = counter; i < words.length; i ++) {
                    deadline += " " + words[i];
                }
                if (deadline.equals("")) {
                    DukeException exception = new DukeException("Deadline of deadline cannot be empty :(");
                    System.out.println(exception);
                    continue;
                }
                Task newTask = new Event(body, deadline);
                savedTasks.add(newTask);
                System.out.println("I've added this task:\n" + newTask);
                System.out.println("Now you have " + savedTasks.size() + " tasks in the list!");
            }
            else if (userInput.toLowerCase().equals("delete")) {
                int nextNum = sc.nextInt();
                if (nextNum > savedTasks.size() || nextNum < 1) {
                    DukeException exception = new DukeException("Number out of range!");
                    System.out.println(exception);
                }
                else {
                    int index = nextNum-1;
                    Task removedTask = savedTasks.get(index);
                    savedTasks.remove(index);
                    System.out.println("Alright! I've removed this task:\n" + removedTask);
                    System.out.println(String.format("Now you have %d tasks left in the list!", savedTasks.size()));
                }
            }
            else {
                DukeException exception = new DukeException("I'm sorry but I don't understand what that means :(");
                System.out.println(exception);
            }
        }
    }
}
