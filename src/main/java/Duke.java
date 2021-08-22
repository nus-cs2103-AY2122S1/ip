import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Chatbot that helps you form a task list
 */

public class Duke {
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    /**
     * Loads the save file from data/duke.txt to the task list.
     * @param list The task list to load the save file to
     */
    private static void loadSave(ArrayList<Task> list) {
        try {
            Files.createDirectories(Paths.get("data/"));
            File f = new File("data/duke.txt");
            if (f.createNewFile()) {
                System.out.println("No save file found. New file created.");
            } else {
                System.out.println("Save file found and restored");
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    String[] taskLine = s.nextLine().split("/");
                    switch (taskLine[0]) {
                        case "T":
                            addFromSave(TaskType.TODO, taskLine, list);
                            break;
                        case "D":
                            addFromSave(TaskType.DEADLINE, taskLine, list);
                            break;
                        case "E":
                            addFromSave(TaskType.EVENT, taskLine, list);
                            break;
                        default:
                            System.out.println("Unexpected value obtained: " + taskLine[0]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Add task in savefile format to the task list.
     * @param t Type of task being added
     * @param line String[] from the save file containing data of task to be loaded
     * @param list The task list to be added to
     */
    private static void addFromSave(TaskType t, String[] line, ArrayList<Task> list) {
        Task temp;
        switch (t) {
            case TODO:
                temp = new ToDo(line[2]);
                break;
            case DEADLINE:
                temp = new Deadline(line[2], line[3]);
                break;
            case EVENT:
                temp = new Event(line[2], line[3]);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + t);
        }
        if (Objects.equals(line[1], "X")) {
            temp.toggleCompleted();
        }
        list.add(temp);
    }

    /**
     * Saves the task into the save file duke.txt
     * @param t Task to be saved into duke.txt
     */
    private static void appendSave(Task t) {
        try {
            File f = new File("data/duke.txt");
            Scanner sc = new Scanner(f);
            FileWriter fw = new FileWriter("data/duke.txt", true);
            fw.write((sc.hasNextLine() ? System.lineSeparator() : "")
                    + t.getType() + "/" + t.getCompletion() + "/" + t.getName()
                    + (t.getType().equals("T") ? "" : "/" + t.getInfo()));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the save file by wiping the duke.txt and adding back all the tasks from the
     * newList into duke.txt
     * @param newList The new task list after modifications (delete, done)
     */
    private static void modifySave(ArrayList<Task> newList) {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            fw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Task task : newList) {
            appendSave(task);
        }
    }

    /**
     * Adds tasks from manual input by user into the list.
     * @param t Task to be added
     * @param inputSplit String array of the users input
     * @param list The task list to be added to
     */
    public static void addTask(TaskType t, String[] inputSplit, ArrayList<Task> list) {
        try {
            if (inputSplit.length < 2) {
                throw new DukeException("Description of task cannot be empty!");
            }
            String input = inputSplit[1];
            Task temp;
            switch (t) {
                case TODO:
                    temp = new ToDo(input);
                    break;
                case DEADLINE:
                    if (input.split(" /by ", 2).length < 2) {
                        throw new DukeException("Deadline not specified!");
                    }
                    String desc = input.split(" /by ", 2)[0];
                    String dead = input.split(" /by ", 2)[1];
                    temp = new Deadline(desc, dead);
                    break;
                case EVENT:
                    if (input.split(" /at ", 2).length < 2) {
                        throw new DukeException("Date of event not specified!");
                    }
                    String name = input.split(" /at ", 2)[0];
                    String at = input.split(" /at ", 2)[1];
                    temp = new Event(name, at);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + t);
            }
            list.add(temp);
            appendSave(temp);
            temp.addResponse(list.size());

        } catch (DukeException e) {
            System.out.println(e.toString().split(" ", 2)[1]);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Initialisation of program
        boolean loop = true;
        Scanner textInput = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        loadSave(list);

        // Main program
        while (loop) {
            String input = textInput.nextLine();
            String[] inputSplit = input.split(" ", 2);

            try {
                switch(inputSplit[0]) {
                    case "todo":
                        addTask(TaskType.TODO, inputSplit, list);
                        break;
                    case "deadline":
                        addTask(TaskType.DEADLINE, inputSplit, list);
                        break;
                    case "event":
                        addTask(TaskType.EVENT, inputSplit, list);
                        break;
                    case "list":
                        if (list.size() == 0) {
                            throw new DukeException("The list is empty!!");
                        }
                        System.out.println("Here are your tasks:");
                        for (int i = 1; i <= list.size(); i++) {
                            Task task = list.get(i - 1);
                            System.out.println(i + "." + task.toString());
                        }
                        break;
                    case "done":
                        if (inputSplit.length < 2) {
                            throw new DukeException("Please enter the number of task to mark as completed!");
                        }
                        try {
                            int index = Integer.parseInt(inputSplit[1]);
                            if (index > list.size() || index <= 0) {
                                throw new DukeException("That number is not in the list!");
                            }
                            Task task = list.get(index - 1);
                            task.toggleCompleted();
                            modifySave(list);
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(task);
                        } catch (NumberFormatException e) {
                            System.out.println("Please input a proper number pls");
                        }
                        break;
                    case "delete":
                        int i = Integer.parseInt(inputSplit[1]);
                        try {
                            Task t = list.get(i - 1);
                            System.out.println("Noted. I've removed this task:");
                            System.out.println(t);
                            list.remove(i - 1);
                            modifySave(list);
                            System.out.println("Now you have " + list.size() + " tasks in your list.");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("That is an invalid index!!");
                        }
                        break;
                    case "bye":
                        loop = false;
                        System.out.println("Bye bye. Duke going to sleep now.");
                        break;
                    default:
                        throw new DukeException("That is not within my scope of action!");
                }
            } catch (DukeException e) {
                System.out.println(e.toString().split(" ", 2)[1]);
            }
        }
    }
}
