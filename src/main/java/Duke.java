import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    enum TaskType {
        TODO,
        DEADLINE,
        EVENT,
        UNKNOWN
    }

    private final ArrayList<Task> taskList;
    private int taskIndex;

    private Duke(ArrayList<Task> taskList, int taskIndex) {
        this.taskList = taskList;
        this.taskIndex = taskIndex;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean isDukeOpen = true;

        try {
            Duke duke = Duke.readSaveFile();
            System.out.println("Hello! I'm Duke\n"
                    + "What can I do for you?");

            while (isDukeOpen) {
                try {
                    String userInput = sc.nextLine();
                    if (userInput.equals("bye")) {
                        //Closes the program.
                        duke.saveToFile();
                        isDukeOpen = false;
                        System.out.println("Bye. Hope to see you again soon!");

                    } else if (userInput.equals("list")) {
                        // List all tasks in the task list.
                        System.out.println("Here are the tasks in your list:");
                        duke.listTasks();

                    } else if (userInput.startsWith("done")) {
                        // Mark a certain task as done.
                        String[] splitString = userInput.split(" ");

                        if (splitString.length > 1) {
                            int taskNumber = Integer.parseInt(splitString[1].trim());
                            duke.markTaskAsDone(taskNumber);

                        } else {
                            throw new DukeException("☹ OOPS!!! Please state which task number "
                                    + "you want to mark as done.");
                        }

                    } else if (userInput.startsWith("delete")) {
                        // Deletes a task from the task list.
                        String[] splitString = userInput.split(" ", 2);

                        if (splitString.length > 1 && splitString[1].trim().length() > 0) {
                            int taskNumber = Integer.parseInt(splitString[1].trim());
                            duke.deleteTask(taskNumber);

                        } else {
                            throw new DukeException("☹ OOPS!!! Please state which task number"
                                    + "you want to delete.");
                        }

                    } else {
                        TaskType type = userInput.startsWith(("todo"))
                                ? TaskType.TODO
                                : userInput.startsWith("deadline")
                                ? TaskType.DEADLINE
                                : userInput.startsWith("event")
                                ? TaskType.EVENT
                                : TaskType.UNKNOWN;

                        if (type == TaskType.UNKNOWN) {
                            // Incorrect user input.
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

                        } else {
                            // Adds a Task to the task list.
                            String[] splitString = userInput.split(" ", 2);

                            if (splitString.length > 1 && splitString[1].trim().length() > 0) {
                                String substring = splitString[1].trim();
                                Task newTask;
                                switch (type) {
                                    case TODO:
                                        newTask = new ToDo(substring, false);
                                        break;
                                    case DEADLINE:
                                        String[] nameAndDeadline = substring.split(" /by ");
                                        if (nameAndDeadline.length > 1
                                                && nameAndDeadline[1].trim().length() > 0) {
                                            newTask = new Deadline(nameAndDeadline[0], nameAndDeadline[1],
                                                    false);
                                        } else {
                                            throw new DukeException("☹ OOPS!!! Please provide a date or "
                                                    + "time for the deadline.");
                                        }
                                        break;
                                    default:
                                        String[] nameAndTime = substring.split(" /at ");
                                        if (nameAndTime.length > 1 && nameAndTime[1].trim().length() > 0) {
                                            newTask = new Event(nameAndTime[0], nameAndTime[1], false);
                                        } else {
                                            throw new DukeException("☹ OOPS!!! Please provide a date or "
                                                    + "time for the event.");
                                        }
                                        break;
                                }
                                duke.addTask(newTask);

                            } else {
                                throw new DukeException("☹ OOPS!!! The description of a " + splitString[0]
                                        + " cannot be empty.");
                            }
                        }
                    }
                } catch (DukeException de) {
                    System.out.println(de.getMessage());
                }
            }
        } catch (DukeException de) {
            System.out.println(de.getMessage());
        }
    }

    /**
     * Lists the current tasks in the task list.
     */
    public void listTasks() {
        int i = 0;
        for (Task task : this.taskList) {
            if (task != null) {
                System.out.println(++i + "." + task.toString());
            } else {
                break;
            }
        }
        if (i == 0) {
            System.out.println("Your list is currently empty.");
        }
    }

    /**
     * Marks a certain task in the task list as done, using its task number.
     *
     * @param taskNumber The number of the task in the task list.
     * @throws DukeException If the task number does not exist.
     */
    public void markTaskAsDone(int taskNumber) throws DukeException {
        try {
            Task doneTask = this.taskList.get(taskNumber - 1);
            doneTask.markDone();
            System.out.println("Nice! I've marked this task as done:\n" + "  "
                    + doneTask.toString());
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! That task does not exist.");
        }
    }

    /**
     * Deletes a certain task from the task list, using its task number.
     *
     * @param taskNumber The number of the task in the task list.
     * @throws DukeException If the task number does not exist.
     */
    public void deleteTask(int taskNumber) throws DukeException {
        try {
            Task deletedTask = this.taskList.get(taskNumber - 1);
            this.taskList.remove(taskNumber - 1);
            this.taskIndex--;
            String taskCount = (this.taskIndex == 1) ? "1 task" : this.taskIndex + " tasks";
            System.out.println("Noted. I've removed this task:\n" + "  "
                    + deletedTask.toString() + "\n" + "Now you have " + taskCount
                    + " in the list.");

        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! That task does not exist.");
        }
    }

    /**
     * Adds a task to the task list.
     * @param task The task to be added to the task list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
        this.taskIndex++;
        String taskCount = (this.taskIndex == 1) ? "1 task" : this.taskIndex + " tasks";
        System.out.println("Got it. I've added this task:\n" + "  " + task.toString()
                + "\n" + "Now you have " + taskCount + " in the list.");
    }

    /**
     * Saves the current task list to the save file.
     * @throws DukeException If there was an error writing to the save file.
     */
    public void saveToFile() throws DukeException {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("save.txt"));
            for (Task task : this.taskList) {
                bw.write(task.toString() + "\n");
            }
            bw.close();
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! There was an error saving your task list.");
        }
    }

    /**
     * Reads from the save file if it exists, otherwise a new save file is created.
     * @throws DukeException If there was an error reading or writing to the save file.
     * @return A Duke initialised with the previously saved task list, if it exists.
     */
    public static Duke readSaveFile() throws DukeException {
        File save = new File("save.txt");
        try {
            if (save.isFile()) {
                // If the save file exists, create a new Duke with the previously saved data.
                BufferedReader br = new BufferedReader(new FileReader("save.txt"));
                ArrayList<Task> savedList = new ArrayList<>();
                String nextLine = br.readLine();
                while (nextLine != null) {
                    savedList.add(Duke.processTaskString(nextLine));
                    nextLine = br.readLine();
                }
                br.close();
                return new Duke(savedList, savedList.size());
            } else {
                // If the save file does not exist, create a new Duke with the default values.
                BufferedWriter bw = new BufferedWriter(new FileWriter("save.txt"));
                bw.close();
                return new Duke(new ArrayList<>(), 0);
            }
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! There was an error starting Duke.");
        }
    }

    /**
     * Takes in the string representation of a task and returns the task represented by it.
     * @param taskString The string representation of a task.
     * @return The task represented by the string.
     */
    private static Task processTaskString(String taskString) {
        char type = taskString.charAt(1);
        boolean isDone = taskString.charAt(4) == 'X';
        String taskDescription = taskString.substring(7);
        switch (type) {
            case 'T':
                return new ToDo(taskDescription, isDone);

            case 'D':
                String[] deadline = taskDescription.split("by:");
                return new Deadline(deadline[0].substring(0, deadline[0].length() - 2),
                        deadline[1].trim().substring(0, deadline[1].length() - 2), isDone);

            default:
                String[] event = taskDescription.split("at:");
                return new Event(event[0].substring(0, event[0].length() - 2),
                        event[1].trim().substring(0, event[1].length() - 2), isDone);
        }
    }
}
