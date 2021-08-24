import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(File file) {
        this.ui = new Ui();
        this.storage = new Storage(file);
        try {
            this.taskList = new TaskList();
        } catch (Exception e) {

        }
    }

    public void run() throws DukeException {
        try {
            File dukeFile = new File("data/duke.txt");
            PrintWriter writer  = this.storage.load(dukeFile);

            ArrayList<Task> toDoList = new ArrayList<>();

            String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

            System.out.println("Hello from\n" + logo);
            System.out.println("---------------------------------------------");
            System.out.println("Hello! I'm Duke, your personal task manager!\n" + "What can I do for you?");
            System.out.println("---------------------------------------------");

            Parser.parseCommand(toDoList, dukeFile, writer);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public static void completeTask(int taskID, ArrayList<Task> arr) {
        System.out.println("---------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        arr.get(taskID - 1).markAsDone();
        System.out.println("---------------------------------------------");
    }

    public static void addTask(Task newTask, ArrayList<Task> arr) {
        arr.add(newTask);
        System.out.println("---------------------------------------------\n"
                + "     Got it. I've added this task:\n"
                + newTask.toString() + "\n"
                + "Now you have " + arr.size() + " task(s) in the list.\n"
                + "---------------------------------------------");
    }

    public static void listItems(ArrayList<Task> arr) {
        System.out.println("---------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < arr.size(); i++) {
            int j = i + 1;
            System.out.println("     " + j + ". "
                    + arr.get(i).toString());
        }
        System.out.println("---------------------------------------------");
    }

    public static void deleteTask(int taskID, ArrayList<Task> arr) throws IllegalArgumentException{
        if (taskID <=0 || taskID > arr.size()) {
            System.out.println("---------------------------------------------");
            System.out.println("No such task exists.");
            System.out.println("---------------------------------------------");
            throw new IllegalArgumentException();
        } else {
            System.out.println("---------------------------------------------");
            System.out.println("Noted I've removed this task:");
            System.out.println(arr.get(taskID - 1).toString());
            arr.remove(taskID - 1);
            System.out.println("Now you have " + arr.size() + " task(s) in the list.");
            System.out.println("---------------------------------------------");
        }
    }

    public static void main(String[] args) throws Exception {
        Files.createDirectories(Paths.get("data/"));
        File dukeFile = new File("data/duke.txt");
        new Duke(dukeFile).run();

    }
}

