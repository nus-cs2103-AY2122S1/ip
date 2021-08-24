/**
 * Duke class encapsulate a chatbot service.
 * It supports queries such as creating, marking and deleting tasks.
 *
 * @author: Chen Hsiao Ting
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> request = new ArrayList<Task>();
//    private static final String SAVE_FILE_LOCATION = "duke.txt";

    private static void loadTask() {
        try {
            File f = new File("data/duke.txt");
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String nextLine = s.nextLine();
                String[] words = nextLine.split(", ", 0);
                String type = words[0];
                Boolean isDone = Boolean.valueOf(words[1]);
                String task = words[2];
                switch (type) {
                    case "T":
                        request.add(new Todo(task, isDone));
                        break;
                    case "D":
                        request.add(new Deadline(task, isDone));
                        break;
                    case "E":
                        request.add(new Event(task, isDone));
                        break;
                    default:
                        throw new IllegalStateException("Unexpected type: " + type);
                }
            }
            System.out.println("Finished loading saved file.");
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("No save file found.");
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Duke: Hello from\n" + logo);
        String divider = "____________________________________________________________";
        System.out.println(divider);
        System.out.println("Duke: Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(divider);

        loadTask();

        boolean exit = false;

        while (!exit) {
            System.out.print("You: ");
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();

            System.out.println(divider);
            try {
                if (str.equals("bye")) {
                    exit = true;
                    System.out.println("Bye. Hope to see you again soon!");
                } else if (str.equals("list")) {
                    System.out.println(list());
                } else if (str.contains("done")) {
                    System.out.println(done(str));
                } else if (str.contains("delete")) {
                    System.out.println(delete(str));
                } else if (str.contains("todo") || str.contains("deadline") || str.contains("event")) {
                    System.out.println(addTask(str));
                } else {
                    throw new DukeException("Command is not valid!");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Task does not exists!");
                if (request.size() == 0) {
                    System.out.println("You do not have any task in the list!");
                    System.out.println("Please add a task.");
                } else if (request.size() == 1) {
                    System.out.println("You only have one task in the list!");
                    System.out.println("Please enter 1 to delete or add more tasks.");
                } else {
                    System.out.println("Please enter an index number between 1 and " + request.size());
                }
            } catch (DukeException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println(divider);
        }
    }

    public static String list() {
        int count = 1;
        String str = "Here are the tasks in your list:";
        for (Task t : request) {
            str += "\n" + count + "." + t.getTask();
            count += 1;
        }
        return str;
    }

    public static String done(String str) {
        int index = Integer.parseInt(str.substring(5)) - 1;
        return request.get(index).markDone();
    }

    public static String delete(String str) {
        int index = Integer.parseInt(str.substring(7)) - 1;
        String result = "Noted. I've removed this task: \n" + request.get(index).delete() +
                "\nNow you have " + (request.size() - 1) + " tasks in the list.";
        request.remove(index);
        return result;
    }

    public static String addTask(String str) {
        String[] words = str.split(" ");
        if (words.length == 1) {
            return "☹ OOPS!!! The description of a todo cannot be empty.";
        } else {
            words = str.split(" ", 2);
            String type = words[0];
            String text = words[1];
            Task task;

            switch (type) {
                case "todo":
                    task = new Todo(text, false);
                    break;
                case "deadline":
                    task = new Deadline(text, false);
                    break;
                case "event":
                    task = new Event(text, false);
                    break;
                default:
                    throw new IllegalStateException("Unexpected type: " + type);
            }
            request.add(task);
            return "Got it. I've added this task: \n" + task.getTask() + "\nNow you have " +
                    request.size() + " tasks in the list.";
        }
    }
}










