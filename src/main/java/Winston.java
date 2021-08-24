import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Files;

/**
 * A class that creates your personal assistant Winston
 */
public class Winston {
    private final ArrayList<Task> list;
    private final java.nio.file.Path path;

    /**
     * Constructor for class Winston
     */
    public Winston() {
        list = new ArrayList<>();
        String pathToThisFile = System.getProperty("user.dir");
        Path dataDir = Paths.get(pathToThisFile, "data");
        this.path = java.nio.file.Paths.get(pathToThisFile, "data", "winston.txt");
        try {
            if (Files.exists(dataDir)) {
                if (Files.exists(this.path)) {
                    read();
                } else {
                    Files.createFile(this.path);
                }
            } else {
                Files.createDirectory(java.nio.file.Paths.get(pathToThisFile, "data"));
                Files.createFile(this.path);
            }
        } catch (FileAlreadyExistsException e) {
            System.out.println("Something went wrong!");
        } catch (IOException e) {
            System.out.println("Something unexpected happened during the creation of files!");
        }
    }

    private void read() {
        try {
            List<String> lines = Files.readAllLines(this.path);
            for (String line : lines) {
                this.list.add(createTask(line));
            }
            System.out.println("Past data retrieved\n\n" + getList());
        } catch (IOException e) {
            System.out.println("Error when reading file!");
        }
    }
    
    private Task createTask(String line) throws IOException {
        char taskType = line.charAt(0);
        String[] lineData = line.split(",");
        boolean isCompleted;
        isCompleted = lineData[1].equals("1");
        if (taskType == 'T') {
            return new ToDoTask(lineData[2], isCompleted);
        } else if (taskType == 'E') {
            return new Event(lineData[2], lineData[3], isCompleted);
        } else if (taskType == 'D') {
            return new DeadLine(lineData[2], lineData[3], isCompleted);
        } else {
            throw new IOException("There is an error in the saved file");
        }
    }
    
    
    /**
     * Adds a task to the arraylist
     *
     * @param task An Object of type Task to be added to the Arraylist
     */
    private void addTask(Task task) {
        list.add(task);
        save();
    }

    /**
     * Marks the Task of given (position - 1) as completed
     *
     * @param position An integer corresponding to the task you wish to complete
     */
    public void markTask(int position) {
        list.get(position - 1).setComplete();
        save();
    }

    /**
     * Removing a Task from the arraylist based on position
     *
     * @param position the position of the Task to be removed from the arraylist
     *                 Note: position will be (index of item in array list + 1)
     */
    public void deleteTask(int position) {
        list.remove(position - 1);
        save();
    }
    
    private void save() {
        try {
            Files.deleteIfExists(this.path);
            PrintWriter out = new PrintWriter("data/winston.txt");
            out.println(getListData());
            out.close();
        } catch (IOException e) {
            System.out.println("Error overwriting file");
        }
    }
    
    private String getListData() {
        StringBuilder result = new StringBuilder();
        for (Task task : this.list) {
            result.append(task.saveFormat() + "\n");
        }
        return result.substring(0, result.length() - 1).toString();
    }

    /**
     * Number of tasks left to be completed
     *
     * @return the number of tasks left in the list that are not completed
     */
    private int size() {
        int counter = 0;
        for (Task task : list) {
            if (task.taskCompletion().equals("[ ]")) {
                counter += 1;
            }
        }
        return counter;
    }

    /**
     * Transforms the arraylist of tasks into a string for visualisation
     *
     * @return A string of the arraylist of tasks
     */
    public String getList() {
        int counter = 1;
        StringBuilder result = new StringBuilder("List of things to do:\n");
        for (Task task : this.list) {
            result.append("\t" + counter + ". " + task.taskCompletion() + task.toString() + "\n");
            counter += 1;
        }
        result.append("End");
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hi there! Winston reporting.\nWhat can I do for you?\n" +
                "Available Commands: done, list, todo, deadline, event, bye, delete");
        String cmd = "";
        Winston winston1 = new Winston();
        while (!cmd.equals("bye")) {
            switch (cmd) {
            case "list":
                System.out.println(winston1.getList());
                System.out.println(winston1.size() + " tasks remaining.");
                break;
            case "done":
                System.out.println(winston1.getList());
                System.out.println("Which task number have you completed?");
                try {
                    winston1.markTask(scan.nextInt());
                } catch (InputMismatchException | IndexOutOfBoundsException e) {
                    System.out.println("Please give a valid number. Resetting to home menu.");
                    break;
                }
                System.out.println("Don't worry, I've got you. Task Marked!" + "\n" +
                        winston1.size() + " tasks remaining.");
                break;
            case "todo":
                System.out.println("What task would you like to add?");
                winston1.addTask(new ToDoTask(scan.nextLine()));
                System.out.println("Task Added!");
                break;
            case "deadline": {
                System.out.println("What task would you like to add?");
                String task = scan.nextLine();
                System.out.println("What is the due date of this task?");
                String dueDate = scan.nextLine();
                winston1.addTask(new DeadLine(task, dueDate));
                System.out.println("Task Added!");
                break;
            }
            case "event": {
                System.out.println("What task would you like to add?");
                String task = scan.nextLine();
                System.out.println("When is this event?");
                String on = scan.nextLine();
                winston1.addTask(new Event(task, on));
                System.out.println("Task Added!");
                break;
            }
            case "delete": {
                System.out.println(winston1.getList());
                System.out.println("What task would you like to delete?");
                try {
                    winston1.deleteTask(scan.nextInt());
                } catch (InputMismatchException | IndexOutOfBoundsException e) {
                    System.out.println("Please give a valid number. Resetting to home menu.");
                    break;
                }

                System.out.println("Don't worry, I've got you. Task Deleted!" + "\n" +
                        winston1.size() + " tasks remaining.");
            }
            case "": {
                break;
            }
            default: {
                System.out.print("Invalid command. Please input a valid command." + "\n");
            }
            }
            cmd = scan.nextLine();
        }

        scan.close();
        System.out.println("See ya later!");
    }
}
