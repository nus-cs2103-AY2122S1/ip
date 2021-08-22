import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Duke {
    private ArrayList<Task> tasks = new ArrayList<>();
    private File file;

    public void run() {
        this.setUp();
        this.greetUser();
        Scanner sc = new Scanner(System.in);
        boolean shouldContinue = true;
        while (shouldContinue) {
            String commandName = sc.next();
            try {
                Command command = Command.valueOf(commandName.toUpperCase());
                shouldContinue = receiveCommand(command, sc);
                this.updateStorage();
            } catch (IllegalArgumentException e) { // caused by user entering a command that is invalid
                sc.nextLine(); // clear user's input
                String errorMessage = new InvalidCommandException().getMessage();
                printMessage(errorMessage);
            } catch (DukeException e) {
                printMessage(e.getMessage());
            }
        }
        sc.close();
        this.exit();
    }

    public void setUp() {
        try {
            String dir = System.getProperty("user.dir");
            Path path = Paths.get(dir, "data");

            // create the data folder if it does not exist
            if (!Files.exists(path)) {
                path = Files.createDirectory(path);
            }

            File file = path.resolve("duke.txt").toFile();
            this.file = file;
            if (file.exists()) {
                this.loadTasks();
            } else {
                file.createNewFile();
            }
        } catch (IOException e) {
            // this exception should not occur because the input is fixed
        }
    }

    public void loadTasks() {
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNextLine()) {
                String[] taskInfoSplit = sc.nextLine().split(" \\| ");
                Task task = convertToTask(taskInfoSplit);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            printMessage("There is an error while loading tasks.");
        }
    }

    public Task convertToTask(String[] arr) {
        char letter = arr[0].charAt(0);
        boolean isDone = arr[1].equals("X");
        switch (letter) {
            case 'T':
                return new Todo(arr[2], isDone);
            case 'D':
                return new Deadline(arr[2], arr[3], isDone);
            case 'E':
                return new Event(arr[2], arr[3], isDone);
            default:
                return null;
        }
    }

    public void printMessage(String message) {
        String formatDisplay = String.format("\t%s", message.replaceAll("\n", "\n\t"));
        System.out.println(formatDisplay);
    }

    public void greetUser() {
        String greetMessage = "Hello! I'm Saitama";
        String detailsMessage = "I do 100 sit-ups, 100 push-ups, 100 squats and a 10 kilometer run every day! No cap";
        printMessage(greetMessage);
        printMessage(detailsMessage);
    }

    public boolean receiveCommand(Command command, Scanner sc) throws DukeException {
        boolean shouldContinue = true;
        String description = sc.nextLine().trim();
        switch (command) {
            case TODO:
            case DEADLINE:
            case EVENT:
                addTask(description, command);
                break;
            case LIST:
                if (description.equals("")) {
                    this.displayTasks();
                    break;
                } else {
                    throw new InvalidCommandException();
                }
            case DONE:
                int indexToMark = stringToInt(description) - 1;
                this.markTask(indexToMark);
                break;
            case DELETE:
                int indexToDelete = stringToInt(description) - 1;
                this.deleteTask(indexToDelete);
                break;
            case BYE:
                if (description.equals("")) {
                    shouldContinue = false;
                    break;
                } else {
                    throw new InvalidCommandException();
                }
            default:
                /* will never be executed because the error would have been caught in run() method
                   if the user input a command that is invalid */
        }
        return shouldContinue;
    }

    public int stringToInt(String description) throws InvalidCommandException {
        if (description.matches("\\d+")) {
            return Integer.parseInt(description);
        } else {
            throw new InvalidCommandException();
        }
    }

    public void exit() {
        printMessage("Hope to see you again!! ^_^");
    }

    public void displayTasks() {
        int len = this.tasks.size();
        if (len == 0) {
            printMessage("You have no task!");
        } else {
            for (int i = 0; i < len; i++) {
                int num = i + 1;
                Task task = this.tasks.get(i);
                printMessage(String.format("%d.%s", num, task));
            }
        }
    }

    public void markTask(int index) {
        try {
            Task task = this.tasks.get(index);
            task.MarkAsDone();
            printMessage(String.format("Nice! I've marked this task as done: \n\t%s", task));
        } catch (IndexOutOfBoundsException e) {
            printMessage("There is no such task!");
        }
    }

    public void addTask(String description, Command command) throws DukeException {
        Task task = null;
        switch (command) {
            case TODO:
                if (!description.equals("")) {
                    task = new Todo(description);
                    break;
                } else {
                    throw new IncompleteTaskDescriptionException("todo");
                }
            case DEADLINE:
                if (description.matches("[^ ].* /by *[^ ].*")) {
                    int separator = description.indexOf("/by");
                    String taskDetail = description.substring(0, separator).trim();
                    String by = description.substring(separator + 3).trim();
                    task = new Deadline(taskDetail, by);
                    break;
                } else {
                    throw new IncompleteTaskDescriptionException("deadline");
                }
            case EVENT:
                if(description.matches("[^ ].* /at *[^ ].*")) {
                    int separator = description.indexOf("/at");
                    String taskDetail = description.substring(0, separator).trim();
                    String at = description.substring(separator + 3).trim();
                    task = new Event(taskDetail, at);
                    break;
                } else {
                    throw new IncompleteTaskDescriptionException("deadline");
                }
            default:
                // checked for command validity in receiveCommand(), so this should not execute at all
                throw new InvalidCommandException();
        }
        tasks.add(task);
        printMessage(String.format("Got it. I've added this task:"
                + "\n\t%s"
                + "\nNow you have %d tasks in the list.", task, this.tasks.size()));
    }

    public void deleteTask(int index) {
        try {
            Task task = this.tasks.get(index);
            this.tasks.remove(index);
            printMessage(String.format("Noted. I've removed this task: \n\t%s\n" +
                    "Now you have %d tasks in the list.", task, tasks.size()));
        } catch (IndexOutOfBoundsException e) {
            printMessage("There is no such task to delete!");
        }
    }

    public void updateStorage() {
        try {
            FileWriter fw = new FileWriter(this.file);
            for (int i = 0; i < this.tasks.size(); i++) {
                String taskInfo = tasks.get(i).stringToStore();
                fw.write(taskInfo);
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            printMessage("Failed to update storage.");
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
