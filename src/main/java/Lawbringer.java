import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Lawbringer {
    public static void main(String[] args) throws IOException {
        List<Task> tasks = Lawbringer.createTaskList();

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
                    Lawbringer.deleteFromFile(tasks.get(index).toString());
                    tasks.get(index).markAsDone();
                    Lawbringer.appendToFile(tasks.get(index).toString());
                    Task task = tasks.get(index);
                    String doneMessage = "Nice! I've marked this task as done:\n" +
                            "  " + task.toString();
                    System.out.println(doneMessage);
                } else if (userInput.startsWith("delete")) {
                    int index = Integer.parseInt(userInput.substring(7)) - 1;
                    Task task = tasks.get(index);
                    Lawbringer.deleteFromFile(task.toString());
                    tasks.remove(index);
                    String doneMessage = "Noted. I've removed this task:\n" +
                            "  " + task.toString() + "\nNow you have " + tasks.size() +
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
                    Lawbringer.appendToFile(todo.toString());
                } else if (userInput.startsWith("deadline")) {
                    int index = userInput.indexOf('/');
                    String by = userInput.substring(index+4);
                    Deadline deadline = new Deadline(userInput.substring(9,index), by);
                    tasks.add(deadline);
                    String message = "Got it. I've added this task:\n" + "  " +
                            deadline + "\nNow you have " + tasks.size() +
                            " task(s) in the list.";
                    System.out.println(message);
                    Lawbringer.appendToFile(deadline.toString());
                } else if (userInput.startsWith("event")) {
                    int index = userInput.indexOf('/');
                    String at = userInput.substring(index+4);
                    Event event = new Event(userInput.substring(6,index), at);
                    tasks.add(event);
                    String message = "Got it. I've added this task:\n" + "  " +
                            event + "\nNow you have " + tasks.size() +
                            " task(s) in the list.";
                    System.out.println(message);
                    Lawbringer.appendToFile(event.toString());
                } else {
                    throw new LawbringerException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (LawbringerException e) {
                System.out.println(e.toString());
            }
        }
    }

    private static List<Task> createTaskList() throws IOException {
        List<Task> tasks = new ArrayList<>();
        // Creates an arrayList of tasks from filepath
        File file = new File("src/main/java/data/Lawbringer.txt");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String line = s.nextLine();
            char taskType = line.charAt(1);
            boolean isDone = line.charAt(4) == 'X';
            switch (taskType) {
                case 'T':
                    ToDo todo = new ToDo(line.substring(7));
                    if (isDone) {
                        todo.markAsDone();
                    }
                    tasks.add(todo);
                    break;
                case 'D':
                    int byIndex = line.indexOf(" (by: ");
                    Deadline deadline = new Deadline(line.substring(7,byIndex),
                            line.substring(byIndex + 6, line.length() - 1));
                    if (isDone) {
                        deadline.markAsDone();
                    }
                    tasks.add(deadline);
                    break;
                case 'E':
                    int atIndex = line.indexOf(" (at: ");
                    Event event = new Event(line.substring(7,atIndex),
                            line.substring(atIndex + 6, line.length() - 1));
                    if (isDone) {
                        event.markAsDone();
                    }
                    tasks.add(event);
                    break;
            }
        }
        return tasks;
    }

    private static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter("src/main/java/data/Lawbringer.txt", true);
        fw.write(textToAppend + "\n");
        fw.close();
    }

    private static void deleteFromFile(String textToDelete) throws IOException {
        File file = new File("src/main/java/data/Lawbringer.txt");
        Scanner s = new Scanner(file);
        String newFileText = "";
        while (s.hasNext()) {
            String line = s.nextLine();
            if (textToDelete.equals(line)) {
                continue;
            }
            newFileText += line + "\n";
        }
        FileWriter fw = new FileWriter("src/main/java/data/Lawbringer.txt");
        fw.write(newFileText);
        fw.close();
    }
}
