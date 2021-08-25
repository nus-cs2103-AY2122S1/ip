import java.util.List;
import java.util.Scanner;

import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Lawbringer {

    private final Storage storage;

    public Lawbringer(String filePath) {
        storage = new Storage(filePath);
    }

    public static void main(String[] args) throws IOException {
        new Lawbringer("src/main/java/data/Lawbringer.txt").run();
    }

    public void run() throws IOException {
        List<Task> tasks = storage.createTaskList();
        String introMessage = "Hello! I'm Lawbringer!\n" +
                "What can i do for you?";
        System.out.println(introMessage);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                String userInput = scanner.nextLine();
                if (userInput.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (userInput.equals("list")) {
                    int counter = 1;
                    for (Task task : tasks) {
                        System.out.println(counter + "." + task.toString());
                        counter++;
                    }
                } else if (userInput.startsWith("done")) {
                    int index = Integer.parseInt(userInput.substring(5)) - 1;
                    storage.deleteFromFile(tasks.get(index).toString());
                    tasks.get(index).markAsDone();
                    storage.appendToFile(tasks.get(index).toString());
                    Task task = tasks.get(index);
                    String doneMessage = "Nice! I've marked this task as done:\n" +
                            "  " + task.toString();
                    System.out.println(doneMessage);
                } else if (userInput.startsWith("delete")) {
                    int index = Integer.parseInt(userInput.substring(7)) - 1;
                    Task task = tasks.get(index);
                    storage.deleteFromFile(task.toString());
                    tasks.remove(index);
                    String doneMessage = "Noted. I've removed this task:\n" +
                            "  " + task + "\nNow you have " + tasks.size() +
                            " task(s) in the list.";
                    System.out.println(doneMessage);
                } else if (userInput.startsWith("todo")) {
                    ToDo todo = new ToDo("");
                    try {
                        todo = new ToDo(userInput.substring(5));
                    } catch (Exception e) {
                        throw new LawbringerException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    if (todo.title.equals("")) {
                        continue;
                    }
                    tasks.add(todo);
                    String message = "Got it. I've added this task:\n" + "  " +
                            todo + "\nNow you have " + tasks.size() +
                            " task(s) in the list.";
                    System.out.println(message);
                    storage.appendToFile(todo.toString());
                } else if (userInput.startsWith("deadline")) {
                    int index = userInput.indexOf('/');
                    String by = userInput.substring(index+4);
                    LocalDate localDate = LocalDate.parse(by);
                    Deadline deadline = new Deadline(userInput.substring(9,index),
                            localDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
                    tasks.add(deadline);
                    String message = "Got it. I've added this task:\n" + "  " +
                            deadline + "\nNow you have " + tasks.size() +
                            " task(s) in the list.";
                    System.out.println(message);
                    storage.appendToFile(deadline.toString());
                } else if (userInput.startsWith("event")) {
                    int index = userInput.indexOf('/');
                    String at = userInput.substring(index+4);
                    Event event = new Event(userInput.substring(6,index), at);
                    tasks.add(event);
                    String message = "Got it. I've added this task:\n" + "  " +
                            event + "\nNow you have " + tasks.size() +
                            " task(s) in the list.";
                    System.out.println(message);
                    storage.appendToFile(event.toString());
                } else {
                    throw new LawbringerException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (LawbringerException e) {
                System.out.println(e.toString());
            }
        }
    }
}
