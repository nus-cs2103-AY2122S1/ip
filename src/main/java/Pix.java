import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Pix {
    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
    private static final ArrayList<Task> taskList = new ArrayList<>(0);

    /**
     * Does the logic for registering prompts.
     * @param command The command to be registered.
     */
    private static void enterCommand(String[] command) throws PixException {
        try {
            switch (command[0]) { //Split into cases depending on the length of the command
            case "bye":
                if (command.length == 1) {
                    exitPix();
                } else {
                    nextCommand();
                    throw new PixUnknownCommandException();
                }
                break;
            case "list":
                if (command.length == 1) {
                    displayList();
                } else {
                    throw new PixUnknownCommandException();
                }
                nextCommand();
                break;
            case "done":
                if (command.length == 2 && isInteger(command[1])) {
                    completeTask(Integer.parseInt(command[1]));
                } else {
                    throw new PixUnknownCommandException();
                }
                nextCommand();
                break;
            case "todo":
                ArrayList<String> tempArrayToDo = new ArrayList<>(Arrays.asList(command).subList(1, command.length));
                String taskName = String.join(" ", tempArrayToDo);
                addTask(taskName, TaskType.TODO, "", false);
                nextCommand();
                break;
            case "deadline":
                int splitterDeadline = -1; //Finds where the "/by" is
                for (int i = 1; i < command.length; i++) {
                    if (command[i].equals("/by")) {
                        splitterDeadline = i;
                        break;
                    }
                }

                if (splitterDeadline != -1) {
                    ArrayList<String> tempTaskName = new ArrayList<>(Arrays.asList(command).subList(
                            1, splitterDeadline));
                    ArrayList<String> tempArray = new ArrayList<>(Arrays.asList(command).subList(
                            splitterDeadline + 1, command.length));
                    String taskNameDeadline = String.join(" ", tempTaskName);
                    String date = String.join(" ", tempArray);
                    addTask(taskNameDeadline, TaskType.DEADLINE, date, false);
                } else { //Cannot find the "/by"
                    throw new PixInvalidTaskException();
                }

                nextCommand();
                break;
            case "event":
                int splitterEvent = -1; //Finds where the "/at" is
                for (int i = 1; i < command.length; i++) {
                    if (command[i].equals("/at")) {
                        splitterEvent = i;
                        break;
                    }
                }

                if (splitterEvent != -1) {
                    ArrayList<String> tempTaskName = new ArrayList<>(Arrays.asList(command).subList(1, splitterEvent));
                    ArrayList<String> tempArray = new ArrayList<>(Arrays.asList(command).
                            subList(splitterEvent + 1, command.length));
                    String taskNameDeadline = String.join(" ", tempTaskName);
                    String date = String.join(" ", tempArray);
                    addTask(taskNameDeadline, TaskType.EVENT, date, false);
                } else { //Cannot find the "/at"
                    throw new PixInvalidTaskException();
                }
                nextCommand();
                break;
            case "delete":
                if (command.length == 2 && isInteger(command[1])) {
                    deleteTask(Integer.parseInt(command[1]));
                } else {
                    throw new PixUnknownCommandException();
                }
                nextCommand();
                break;
            default:
                throw new PixUnknownCommandException();
            }
        } catch (PixUnknownCommandException e) {
            System.out.println(e.getMessage());
            nextCommand();
        } catch (PixInvalidTaskException e) {
            System.out.println(e.getMessage());
            nextCommand();
        }
    }

    /**
     * Displays the itemList.
     */
    private static void displayList() {
        if (taskList.size() == 0) {
            System.out.println("There is nothing in the list, go and do something!");
        } else {
            System.out.println("Why can't you keep track of these yourself:");
            for (int i = 1; i < taskList.size() + 1; i++) {
                System.out.println(i + ". " + taskList.get(i - 1).toString());
            }
        }
    }

    /**
     * Adds the Task to the taskList.
     * @param item The task to be added to the taskList.
     * @param type The type of task to be added.
     * @param date The date/time of the task (if applicable)
     */
    private static void addTask(String item, TaskType type, String date, boolean done) throws PixException {
        try {
            if (item.equals("")) {
                throw new PixInvalidTaskException();
            } else {
                switch (type) {
                case TODO:
                    ToDo toDo = new ToDo(item);
                    if (done) {
                        toDo.completeTask();
                    }
                    taskList.add(toDo);
                    System.out.println("Added this task: \n" + toDo);
                    System.out.println("You now have " + taskList.size() + " task(s) in your list");
                    break;
                case DEADLINE:
                    Deadline deadline = new Deadline(item, date);
                    if (done) {
                        deadline.completeTask();
                    }
                    taskList.add(deadline);
                    System.out.println("Added this task: \n" + deadline);
                    System.out.println("You now have " + taskList.size() + " task(s) in your list");
                    break;
                case EVENT:
                    Event event = new Event(item, date);
                    if (done) {
                        event.completeTask();
                    }
                    taskList.add(event);
                    System.out.println("Added this task: \n" + event);
                    System.out.println("You now have " + taskList.size() + " task(s) in your list");
                    break;
                }

                saveTaskList();
            }
        } catch (PixInvalidTaskException e) {
            System.out.println(e.getMessage());
            nextCommand();
        } catch (IOException e) {
            System.out.println("There is an I/O error!");
        }
    }

    /**
     * Processes input and adds tasks from Pix.txt to the Task List.
     */
    private static void addTask(String task) throws PixException {
        try {
            String[] strArray = task.split(" ", 0);
            switch (strArray[0]) {
                case "T":
                    //Add To Do task
                    ArrayList<String> tempTaskName = new ArrayList<>(Arrays.asList(strArray).
                            subList(4, strArray.length));
                    String taskName = String.join(" ", tempTaskName);
                    ToDo toDo = new ToDo(taskName);
                    if (strArray[2].equals("1")) {
                        toDo.completeTask();
                        taskList.add(toDo);
                    } else if (strArray[2].equals("0")) {
                        taskList.add(toDo);
                    } else {
                        throw new PixIOException();
                    }
                    break;
                case "D":
                    //Add Deadline task
                    int splitter = -1;
                    for (int i = 4; i < strArray.length; i++) {
                        if (strArray[i].equals("|")) {
                            splitter = i;
                            break;
                        }
                    }
                    //Check if the task name or date is an empty field
                    if (splitter == 4 || splitter == strArray.length - 1 || splitter == -1) {
                        throw new PixIOException();
                    }
                    tempTaskName = new ArrayList<>(Arrays.asList(strArray).
                            subList(4, splitter));
                    ArrayList<String> tempTaskDate = new ArrayList<>(Arrays.asList(strArray).
                            subList(splitter + 1, strArray.length));
                    taskName = String.join(" ", tempTaskName);
                    String taskDate = String.join(" ", tempTaskDate);
                    Deadline deadline = new Deadline(taskName, taskDate);
                    if (strArray[2].equals("1")) {
                        deadline.completeTask();
                        taskList.add(deadline);
                    } else if (strArray[2].equals("0")) {
                        taskList.add(deadline);
                    } else {
                        throw new PixIOException();
                    }
                    break;
                case "E":
                    //Add Event task
                    //Add Deadline task
                    splitter = -1;
                    for (int i = 4; i < strArray.length; i++) {
                        if (strArray[i].equals("|")) {
                            splitter = i;
                            break;
                        }
                    }
                    //Check if the task name or date is an empty field
                    if (splitter == 4 || splitter == strArray.length - 1 || splitter == -1) {
                        throw new PixIOException();
                    }
                    tempTaskName = new ArrayList<>(Arrays.asList(strArray).
                            subList(4, splitter));
                    tempTaskDate = new ArrayList<>(Arrays.asList(strArray).
                            subList(splitter + 1, strArray.length));
                    taskName = String.join(" ", tempTaskName);
                    taskDate = String.join(" ", tempTaskDate);
                    Event event = new Event(taskName, taskDate);
                    if (strArray[2].equals("1")) {
                        event.completeTask();
                        taskList.add(event);
                    } else if (strArray[2].equals("0")) {
                        taskList.add(event);
                    } else {
                        throw new PixIOException();
                    }
                    break;
                default:
                    //throw PixIOException
                    throw new PixIOException();
            }
        } catch (PixIOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Sets the selected task to be completed.
     * @param n The number of the Task to be completed.
     */
    private static void completeTask(int n) {
        try {
            taskList.get(n - 1).completeTask();
            System.out.println("Wow. You did it. Yay.");
            System.out.println(taskList.get(n - 1));
            saveTaskList();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You can't complete what literally isn't there!");
        } catch (IOException e) {
            System.out.println("There is an I/O error!");
        }
    }

    /**
     * Deletes the selected task from the Task List.
     * @param n The number of the Task to be deleted.
     */
    private static void deleteTask(int n) {
        try {
            Task taskToDelete = taskList.get(n - 1);
            System.out.println("Given up already? Task removed:");
            System.out.println(taskToDelete);
            taskList.remove(n - 1);
            System.out.println("You now have " + taskList.size() + " task(s) in your list");
            saveTaskList();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You can't delete what literally isn't there!");
        } catch (IOException e) {
            System.out.println("There is an I/O error!");
        }
    }

    /**
     * Reads the input from the user and triggers the logic for the command.
     */
    private static void nextCommand() throws PixException {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] splitInput = input.split(" ", 0);
        enterCommand(splitInput);
    }

    /**
     * Closes and exits Pix.
     */
    private static void exitPix() {
        System.out.println("Please don't come back...");
    }

    /**
     * Checks if the String is an integer.
     * @param str String to be tested.
     * @return Returns true if it is an integer and false if it is not.
     */
    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
    
    /**
     * Saves the tasks in the Task List into Pix.txt.
     */
    private static void saveTaskList() throws IOException {
        try {
            FileWriter pixFile = new FileWriter("src/main/java/data/Pix.txt");
            for (Task task : taskList) {
                String done;
                if (task.isDone()) {
                    done = "1";
                } else {
                    done = "0";
                }

                if (task instanceof ToDo) {
                    pixFile.write("T | " + done + " | " + task.getTaskName() + System.lineSeparator());
                    pixFile.close();
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    pixFile.write("D | " + done + " | " + deadline.getTaskName() + " | "
                            + deadline.getDate() + System.lineSeparator());
                    pixFile.close();
                } else { //else it is an event and there is no other case that it returns invalid
                    Event event = (Event) task;
                    pixFile.write("E | " + done + " | " + event.getTaskName() + " | "
                            + event.getDate() + System.lineSeparator());
                    pixFile.close();
                }
            }
        } catch (IOException e) {
            System.out.println("There's an I/O error!");
        }
    }

    /**
     * Reads the text file Pix.txt and loads the tasks in the Task List from it.
     */
    private static void startPix() throws PixException, FileNotFoundException {
        try {
            File pixFile = new File("src/main/java/data/Pix.txt");
            Scanner sc = new Scanner(pixFile);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                addTask(line);
            }
            System.out.println("This is Pix. Why did you summon me AGAIN...");
            System.out.println("What do want now?");
            nextCommand();
        } catch (FileNotFoundException e) {
            System.out.println("I can't seem to find the file! (Pix.txt)");
        }
    }

    public static void main(String[] args) throws PixException, FileNotFoundException {
        startPix();
    }
}