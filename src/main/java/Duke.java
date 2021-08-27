import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Shivam Tiwari
 * Chat bot to echo everything we input.
 * Features: Greetings, Echo and Exit on command
 */

public class Duke {

    // instance variable to store input  task values
    static ArrayList<Task> list;
    String path;

    Duke(String directoryName, String fileName) {
        File directory = new File(directoryName);
        if (!directory.exists()) {
            try {
                directory.mkdirs();
            } catch (Exception error) {
                System.out.println("Failed to create directory");
            }
        }
        String path = directoryName + "/" + fileName;
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println("Failed to create a File");
            }
        }
        this.path = directoryName + "/" + fileName;
    }

    /**
     * method to read from the a text file on the system.
     */
    void readFile() {
        File file = new File(this.path);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String[] data = task.split(" \\| ");
                boolean isDone = data[1].equals("1");
                if (data[0].equals("T")) {
                    System.out.println(data[2]);
                    Todo todo = new Todo(data[2], isDone);
                    list.add(todo);
                } else if (data[0].equals("E")) {
                    Event event = new Event(data[2], isDone, data[3]);
                    list.add(event);
                } else {
                    Deadline deadline = new Deadline(data[2], isDone, data[3]);
                    list.add(deadline);
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to read from file.");
        }

    }

    /**
     * Method to mark the task as done
     *
     * @param n the task number entered by the user
     */
    void doneTask(int n) throws DukeException {
        if (n > list.size()) {
            throw new TaskNotFoundException("list has only " + list.size() + " tasks. Enter a valid task");
        }
        list.get(n - 1).markAsDone();
        ArrayList<String> content = new ArrayList<>();
        list.forEach((elem) -> {
            content.add(elem.toString());
        });
        try {
            Files.write(Paths.get(this.path), content, StandardCharsets.UTF_8);
        } catch (IOException error) {
            System.out.println("Failed to write to the file");
        }
        System.out.println("Nice! I have marked this task as done:");
        System.out.println(list.get(n - 1).toString());
    }

    /**
     * Method to mark the task as done
     *
     * @param n the task number entered by the user
     */
    void deleteTask(int n) throws DukeException {
        if (n > list.size()) {
            throw new TaskNotFoundException("list has only " + list.size() + "tasks. Enter a valid task");
        }
        String deletedTask = list.get(n - 1).toString();
        list.remove(n - 1);
        ArrayList<String> content = new ArrayList<>();
        list.forEach((elem) -> {
            content.add(elem.toString());
        });
        try {
            Files.write(Paths.get(this.path), content, StandardCharsets.UTF_8);
        } catch (IOException error) {
            System.out.println("Failed to write to the file");
        }
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }


    /**
     * Method to add the input to the list
     *
     * @param input String input from the user
     */
    void addToList(Task input) {
        //Task task = new Task(input, false);
        list.add(input);
        try {
            Files.writeString(Paths.get(this.path), input.toString() + "\n", StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (Exception error) {
            System.out.println("Failed to write to the file");
        }

        System.out.println("Got it. I've added this task:");
        System.out.println(input.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * method to create an Event as a Task and add to
     * the task list
     *
     * @param input String task name
     * @param time  String time of event
     */
    void createEvent(String input, String time) {
        Event event = new Event(input, false, time);
        addToList(event);
    }

    /**
     * method to create a Task with no time and
     * add it to the task list
     *
     * @param input String task name
     */
    void createTodo(String input) {
        Todo todo = new Todo(input, false);
        addToList(todo);
    }

    /**
     * method to create a task with a given deadline and
     * add to the task list
     *
     * @param input String task name
     * @param time  String time of deadline
     */
    void createDeadline(String input, String time) {
        Deadline deadline = new Deadline(input, false, time);
        addToList(deadline);
    }

    /**
     * method to echo the input back to the user
     *
     * @param input String input from the user
     */
    static void Echo(String input) {
        System.out.println(input);
    }

    /**
     * method to print task list on command
     */
    static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).toString());
        }
    }

    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke("./data", "duke.txt");
        list = new ArrayList<>();
        duke.readFile();
        String input = "";
        System.out.println("Hello, I'm Duke");
        printList();
        System.out.println("What can I do for you");
        Scanner sc = new Scanner(System.in);

        //loop to check if next input is available
        while (sc.hasNext()) {
            input = sc.nextLine();
            String[] split = input.split(" ", 2);
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");

                //exit command for when entered exit code
                System.exit(1);
            } else if (input.equals("list")) {
                printList();
            } else if (split[0].equals("done")) {
                if (split.length < 2 || split[1].isEmpty()) {
                    throw new NoNumberException("☹ OOPS!!! No task number was given in the input");
                }
                duke.doneTask(Integer.parseInt(split[1]));
            } else if (split[0].equals("delete")) {
                if (split.length < 2 || split[1].isEmpty()) {
                    throw new NoNumberException("☹ OOPS!!! No task number was given in the input");
                }
                duke.deleteTask(Integer.parseInt(split[1]));
            } else if (split[0].equals("todo")) {
                if (split.length < 2) {
                    throw new NoDescriptionException("☹ OOPS!!! The description of a " + split[0] + " cannot be empty.");
                }
                duke.createTodo(split[1]);
            } else if (split[0].equals("event")) {
                String time = split[1].split(" /at ", 2)[1];
                String task = split[1].split(" /at ", 2)[0];
                duke.createEvent(task, time);
            } else if (split[0].equals("deadline")) {
                String time = split[1].split(" /by ", 2)[1];
                String task = split[1].split(" /by ", 2)[0];
                duke.createDeadline(task, time);
            } else {
                throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
