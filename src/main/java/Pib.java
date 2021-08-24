import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

import Tasks.Task;
import Tasks.Todo;
import Tasks.Deadline;
import Tasks.Event;

/**
 * Pib is a Personal Assistant Chat-bot that is able to keep track of tasks (CRUD) and deadlines
 */
public class Pib {
    private static String DIVIDER = "____________________________________________________________\n";
    private static final String DATA_FILE_PATH = "src/main/java/data/data.txt";
    private ArrayList<Task> list;
    private Scanner sc;
    private File data;

    /**
     * Public constructor to instantiate an instance of Pib and start the program
     */
    public Pib() {
        System.out.println(DIVIDER + "Hello! I'm Pib\n" + "Tell me something!\n" + DIVIDER);
        try {
            this.data = new File(DATA_FILE_PATH);
            list = new ArrayList<>();
            if (!this.data.createNewFile()) {
                loadSavedData(this.data);
            }
            sc = new Scanner(System.in);
            readInput();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading file");
        } catch (PibException e) {
            System.out.println(e.getMessage());
        }
    }

    private enum TaskType {
        TODO, DEADLINE, EVENT
    }

    private void readInput() {
        while (sc.hasNextLine()) {
            try {
                System.out.println(DIVIDER);
                String next = sc.nextLine();

                if (next.contains(" ")) {
                    int spaceDividerIndex = next.indexOf(" ");
                    String taskType = next.substring(0, spaceDividerIndex).toLowerCase();
                    String taskDetails = next.substring(1 + spaceDividerIndex);
                    switch (taskType) {
                        case "todo":
                            addToList(TaskType.TODO, taskDetails);
                            break;
                        case "deadline": {
                            addToList(TaskType.DEADLINE, taskDetails);
                            break;
                        }
                        case "event": {
                            addToList(TaskType.EVENT, taskDetails);
                            break;
                        }
                        case "done": {
                            try {
                                markAsDone(taskDetails);
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("Uh oh :( Please enter a valid task number!\n");
                            }
                            break;
                        }
                        case "delete": {
                            try {
                                delete(taskDetails);
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("Uh oh :( Please enter a valid task number!\n");
                            }
                            break;
                        }
                        default: {
                            throw new PibException("Uh oh :( I don't know that command\n");
                        }
                    }
                } else {
                    String action = next.toLowerCase();
                    if (action.equals("list")) {
                        displayList();
                    } else if (action.equals("bye")) {
                        endPib();
                        break;
                    } else {
                        throw new PibException("Uh oh :( I don't know that command\n");
                    }
                }
                System.out.println(DIVIDER);
            } catch (PibException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void endPib() {
        System.out.println("Bye! See you next time!\n");
        System.out.println(DIVIDER);
        sc.close();
    }

    private void addToList(TaskType t, String taskDetails) {
        try {
            Task newTask;
            if (t.equals(TaskType.TODO)) {
                if (taskDetails.trim().isBlank()) {
                    throw new PibException("Task description cannot be empty!\n");
                }
                newTask = new Todo(taskDetails);
            } else {
                if (t.equals(TaskType.DEADLINE)) {
                    int dateDividerIndex = taskDetails.indexOf("/by ");
                    if (dateDividerIndex == -1) {
                        throw new PibException("Include /by <date>\n");
                    }
                    String description = taskDetails.substring(0, dateDividerIndex);
                    String date = taskDetails.substring(dateDividerIndex + 4);
                    if (description.trim().isBlank()) {
                        throw new PibException("Task description cannot be empty!\n");
                    }
                    if (date.trim().isBlank()) {
                        throw new PibException("Date cannot be empty!\n");
                    }
                    newTask = new Deadline(taskDetails.substring(0, dateDividerIndex), taskDetails.substring(dateDividerIndex + 4));
                } else if (t.equals(TaskType.EVENT)) {
                    int dateDividerIndex = taskDetails.indexOf("/at ");
                    if (dateDividerIndex == -1) {
                        throw new PibException("Include /at <date>\n");
                    }
                    String description = taskDetails.substring(0, dateDividerIndex);
                    String date = taskDetails.substring(dateDividerIndex + 4);
                    if (description.trim().isBlank()) {
                        throw new PibException("Task description cannot be empty!\n");
                    }
                    if (date.trim().isBlank()) {
                        throw new PibException("Date cannot be empty!\n");
                    }
                    newTask = new Event(taskDetails.substring(0, dateDividerIndex), taskDetails.substring(dateDividerIndex + 4));
                } else {
                    return;
                }
            }
            list.add(newTask);
            writeToFile(DATA_FILE_PATH, list);
            System.out.println("Now you have " + list.size() + " task(s) in your list.\n");
        } catch (PibException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Uh oh :( Error saving tasks");
        }
    }

    private void displayList() {
        if (list.size() == 0) {
            System.out.println("Nothing added yet\n");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + "." + list.get(i).toString());
            }
            System.out.println("");
        }
    }

    private void markAsDone(String s) {
        try {
            if (s.isBlank()) {
                System.out.println("Tell me which item to mark as complete!\n ");
            } else {
                list.get(Integer.parseInt(s) - 1).markAsDone();
                writeToFile(DATA_FILE_PATH, list);
            }
        } catch (IOException e) {
            System.out.println("Uh oh :( Error saving tasks");
        }
    }

    private void delete(String s) {
        if (s.isBlank()) {
            System.out.println("Tell me which item to delete!\n ");
        } else {
            list.remove(Integer.parseInt(s) - 1);
            System.out.println("Successfully deleted task!\n");
            System.out.println("Now you have " + list.size() + " task(s) in your list.\n");
        }
    }

    private void loadSavedData(File file) throws PibException {
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext ()) {
                String[] taskDetails = sc.nextLine().split(",");
                System.out.println(Arrays.toString(taskDetails));
                Task newTask = null;
                switch (taskDetails[0]) {
                case "T":
                    newTask = new Todo(taskDetails[2], Integer.parseInt(taskDetails[1]));
                    break;
                case "E":
                    newTask = new Event(taskDetails[2], taskDetails[3], Integer.parseInt(taskDetails[1]));
                    break;
                case "D":
                    newTask = new Deadline(taskDetails[2], taskDetails[3], Integer.parseInt(taskDetails[1]));
                    break;
                default:
                    break;
                }
                if (newTask != null) {
                    list.add(newTask);
                }

            }
            System.out.println(DIVIDER);
        } catch (FileNotFoundException e) {
            throw new PibException("Saved data error");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : tasks) {
            fw.write(t.toDataString());
        }
        fw.close();
    }

    public static void main(String[] args) {
        Pib p = new Pib();
    }
}
