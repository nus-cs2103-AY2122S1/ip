import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static ArrayList<Task> tasks = new ArrayList<Task>();
    static int counter = 0;
    static String dash = "__________________________________";

    public static void main(String[] args) throws DukeException {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println(dash);
        System.out.println("Howdy! I'm Duke" + '\n'+ "How may I assist you?");
        System.out.println(dash);

        // READ FILE CONTENTS
        System.out.println("You've saved the following tasks last time we met:");
        try {
            printFileContents("data/tasks.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        Scanner scanner = new Scanner(System.in);
        String userInput;


        while (true) {
            userInput = scanner.nextLine();

            if (userInput.matches("bye")) {
                System.out.println(dash);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(dash);

                try {
                    writeToFile(tasks);
                } catch (IOException e) {
                    System.out.println("No tasks saved.");
                }

                break;

            } else if (userInput.matches("list")){
                System.out.println(dash);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < counter; i++) {
                    Task currTask = tasks.get(i);
                    System.out.println(i+1 + "." + currTask.toString());
                }
                System.out.println(dash);

            } else if (userInput.length() > 3 && userInput.substring(0, 4).matches("done")){

                markDone(userInput);

            } else if (userInput.length() > 5 && userInput.substring(0, 6).matches("delete")){

                delete(userInput);

            } else if (userInput.length() > 3 && userInput.substring(0, 4).matches("todo")) {

                makeTodo(userInput);

            } else if (userInput.length() > 4 && userInput.substring(0, 5).matches("event")) {

                makeEvent(userInput);

            } else if (userInput.length() > 7 && userInput.substring(0, 8).matches("deadline")) {

                makeDeadline(userInput);

            } else {
                System.out.println(dash + '\n' + "OOPS!! I don't know how to respond to this command! :-(" + '\n'+ dash);
                // throw new DukeException("OOPS!! I don't know how to respond to this command! :-(");
            }
        }
    }


    public static void printFileContents(String filePath) throws FileNotFoundException {

        // System.out.println(filePath);
        File f = new File(filePath); // create a File for the given file path
        // System.out.println(f.exists());
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while (s.hasNext()) {
            String toAdd = s.nextLine();

            if (toAdd.charAt(1) == 'T') {
                String task = toAdd.substring(7);
                addTodo(task);

            } else if (toAdd.charAt(1) == 'E') {
                String task = toAdd.substring(7);
                String name = task.substring(0, task.indexOf("(") - 1);
                String when = task.substring(task.indexOf(":") + 2, task.indexOf(")"));

                addEvent(name + " /at " + when);

            } else if (toAdd.charAt(1) == 'D') {
                String task = toAdd.substring(7);
                String name = task.substring(0, task.indexOf("(") - 1);
                String when = task.substring(task.indexOf(":") + 2, task.indexOf(")"));

                addDeadline(name + " /by " + when);
            }

            if (toAdd.charAt(4) == 'X') {
                addDone(counter);
            }
        }

        for (int i = 0; i < counter; i++) {
            Task currTask = tasks.get(i);
            System.out.println(i+1 + "." + currTask.toString());
        }
        System.out.println(dash);
    }

    public static void writeToFile(ArrayList<Task> lst) throws IOException {

        File directory = new java.io.File(System.getProperty("user.dir") + "/data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        PrintWriter out = new PrintWriter("data/tasks.txt");

        for (int i = 0; i < tasks.size(); i++) {
            out.println(tasks.get(i).toString());
        }
        out.close();
    }



    public static void markDone(String userInput) {
        if (userInput.substring(4).length() == 0) {
            System.out.println(dash + '\n' + "Sorry, which task do you wish to mark as completed?" + '\n'+ dash);
            // throw new DukeException("User has not indicated task to mark as complete.");

        } else {
            Integer index = Integer.parseInt(userInput.substring(5));
            Task currTask = tasks.get(index - 1);
            currTask.completeTask();

            System.out.println(dash);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(currTask.toString());
            System.out.println(dash);
        }
    }

    public static void addDone(int num) {
        Task currTask = tasks.get(num - 1);
        currTask.completeTask();
    }

    public static void delete(String userInput) {
        if (userInput.substring(6).length() == 0) {
            System.out.println(dash + '\n' + "Sorry, which task do you wish to delete?" + '\n'+ dash);
            // throw new DukeException("User has not indicated task to delete.");

        } else {
            Integer index = Integer.parseInt(userInput.substring(7));
            Task currTask = tasks.get(index - 1);
            tasks.remove(currTask);

            System.out.println(dash);
            System.out.println("Noted. I've removed this task:");
            System.out.println(currTask.toString());

            counter -= 1;
            System.out.println("You now have " + counter + " task(s) in your list!");
            System.out.println(dash);
        }
    }

    public static void makeTodo(String userInput) {
        if (userInput.substring(4).length() == 0) {
            System.out.println(dash + '\n' + "YIKES!! The description of a todo cannot be empty!" + '\n'+ dash);
            // throw new DukeException("YIKES!! The description of a todo cannot be empty!");
        } else {
            tasks.add(new ToDo(userInput.substring(5)));

            System.out.println(dash);
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.get(counter).toString());
            counter += 1;

            System.out.println("You now have " + counter + " task(s) in the list.");
            System.out.println(dash);
        }
    }
    public static void addTodo(String userInput) {
        tasks.add(new ToDo(userInput));
        counter += 1;
    }


    public static void makeEvent(String userInput) {
        if (userInput.substring(5).length() == 0) {
            System.out.println(dash + '\n' + "YIKES!! The description of an Event cannot be empty!" + '\n'+ dash);
            // throw new DukeException("YIKES!! The description of an Event cannot be empty!");
        } else {
            String output = userInput.substring(6);
            String[] info = output.split("/");
            tasks.add(new Event(info[0], info[1].substring(3)));

            System.out.println(dash);
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.get(counter).toString());
            counter += 1;

            System.out.println("You now have " + counter + " task(s) in the list.");
            System.out.println(dash);
        }
    }

    public static void addEvent(String userInput) {
        String[] info = userInput.split("/");
        tasks.add(new Event(info[0], info[1].substring(3)));
        counter += 1;
    }

    public static void makeDeadline(String userInput) {
        if (userInput.substring(8).length() == 0) {
            System.out.println(dash + '\n' + "YIKES!! The description of a Deadline cannot be empty!" + '\n'+ dash);
            // throw new DukeException("YIKES!! The description of a Deadline cannot be empty!");
        } else {
            String output = userInput.substring(9);
            String[] info = output.split("/");
            tasks.add(new Deadline(info[0], info[1].substring(3)));

            System.out.println(dash);
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.get(counter).toString());
            counter += 1;

            System.out.println("You now have " + counter + " task(s) in the list.");
            System.out.println(dash);
        }
    }

    public static void addDeadline(String userInput) {
        String[] info = userInput.split("/");
        tasks.add(new Deadline(info[0], info[1].substring(3)));
        counter += 1;
    }

}
