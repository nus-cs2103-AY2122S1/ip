/**
 * Encapsulates the ChatBot function and supports a task list that can save and delete various tasks
 * such as events, deadlines to to-dos.
 *
 * @author Clifford
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatBot {
    private boolean isRunning;
    private ArrayList<Task> tasks;
    private static final int tasksLimit = 100;
    private int currentIdx;
    private final String tasksFilePath = "bin/data/tasks.txt";

    public ChatBot() {
        this.isRunning = true;
        this.tasks = new ArrayList<>();
        this.currentIdx = 0;
    }

    public void retrieveTasks()  {
        File file = new File(tasksFilePath);
        try {
            if (file.exists()) {
                Scanner sc = new Scanner(file);
                while(sc.hasNext()) {
                    record(convertStringToTask(sc.nextLine()));
                }
            } else {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e2) {
                System.out.println("Something happened when creating a new save taskData file: " + e2.getMessage());
            }
        }
    }

    public void saveTasks()  {
        File file = new File(tasksFilePath);
        try {
            if (file.exists()) {
                FileWriter fw = new FileWriter(tasksFilePath);
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < currentIdx; i++) {
                    if(i == 0) {
                        sb.append(convertTaskToText(tasks.get(i)));
                        continue;
                    }
                    sb.append(System.lineSeparator()).append(convertTaskToText(tasks.get(i)));
                }
                fw.write(sb.toString());
                fw.close();
            } else {
                throw new FileNotFoundException();
            }
        } catch(FileNotFoundException e) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch(IOException e2) {
                System.out.println("Something happened when creating a new file to save tasks:" + e2.getMessage());
            }
        } catch(IOException e) {
            System.out.println("Something happened when saving: " + e.getMessage());
        }
    }

    public String convertTaskToText(Task task) {
        return task.convertToText();
    }

    public Task convertStringToTask(String text) {
        try {
            return Task.createTaskFromText(text);
        } catch(IllegalArgumentException e) {
            System.out.println("File data cannot be retrieved because of improper formatting: " +e.getMessage());
            return null; // todo maybe consider what to return in this case
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    /**
     * greet is called when the user starts up the program.
     *
     * @return a welcome message when user starts interacting with ChatBot
     */
    public String greet() {
        return "Hello! I'm Chatty Clifford!\nHow may I be of service to you?";
    }

    /**
     * farewell is called when the user exits the program.
     *
     * @return a farewell message when user finishes interacting with ChatBot
     */
    public String farewell() {
        this.isRunning = false;
        return "Bye! See you next time!";
    }

    /**
     * listen decides what the ChatBot should do depending on the user input
     *
     * @param input the request by the user
     * @return the response by the ChatBot
     */
    public String listen(String input) {
        if(input.trim().equals("bye")) {
            return farewell();
        }
        if(input.trim().equals("list")) {
            return printTasks();
        }
        String[] temp = input.trim().split(" ", 2);
        if(temp[0].equals("done")) {
            try {
                int taskId  = Integer.parseInt(temp[1]);
                return markAsDone(taskId);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                return "done should be in format: done [TASK NUMBER]";
            }
        }
        if(temp[0].equals("delete")) {
            try {
                int taskId  = Integer.parseInt(temp[1]);
                return deleteTask(taskId);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                return "delete should be in format: delete [TASK NUMBER]";
            }
        }
        if(temp[0].equals("todo")) {
            try {
                return recordTodo(temp[1].trim());
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                return "todo should be in format: todo [DESCRIPTION]";
            }
        }
        if(temp[0].equals("deadline")) {
            try {
                String[] descriptionDatePair = temp[1].split("/by", 2);
                return recordDeadline(descriptionDatePair[0].trim(), descriptionDatePair[1].trim());
            } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                e.printStackTrace();
                return "deadline should be in format: [DESCRIPTION] /by [DATE]!\n" +
                    "Only accepted [DATE] format is: date/month/year, HHMM (24h time)";
            }
        }
        if(temp[0].equals("event")) {
            try {
                String[] descriptionDatePair = temp[1].split("/at", 2);
                return recordEvent(descriptionDatePair[0].trim(), descriptionDatePair[1].trim());
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                e.printStackTrace();
                return "event should be in format: [DESCRIPTION] /by [DATE]!\n" +
                    "Only accepted [DATE] format is: date/month/year, HHMM (24h time)";
            }
        }
        return "Sorry, I don't understand what you are saying!";
    }

    public String recordTodo(String description) {
        return record(new Todo(description));
    }

    public String recordDeadline(String description, String date) {
        return record(new Deadline(description, date));
    }

    public String recordEvent(String description, String date) {
        return record(new Event(description, date));
    }

    /**
     * Record a task to a list and informs the user if the operation is successful.
     *
     * @param task the task to be added to list
     * @return a String to tell the user that the item is recorded
     * @throws ArrayIndexOutOfBoundsException is thrown if user exceeds the limit of items.
     */
    public String record(Task task) throws ArrayIndexOutOfBoundsException {
        try {
            if(currentIdx >= tasksLimit) {
                throw new IndexOutOfBoundsException(
                    String.format("ChatBot can only record maximum of %d responses.", tasksLimit));
            }
            tasks.add(task);
            currentIdx++;
            saveTasks();
            return "Noted task down! I've added this task:\n  " + task.toString();
        } catch(IndexOutOfBoundsException e) {
            e.printStackTrace();
            return "I cannot remember so many things!";
        }
    }

    /**
     * formats the items recorded in a list to be shown to user.
     *
     * @return list representation of items recorded by user
     */
    public String printTasks() {
        if(currentIdx == 0) {
            return "--- List is Empty ---";
        }
        StringBuilder sb = new StringBuilder("--- Start of List ---\n");
        for(int i = 0; i < currentIdx; i++) {
            sb = sb.append(Integer.toString(i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        sb = sb.append("--- End Of List ---");
        return sb.toString();
    }

    /**
     * Allows users to choose a task in the list to be crossed off.
     *
     * @param taskId the id of the task starting from 1 for the first task
     * @return a String that confirms the success or failure of the mark as done operation.
     * @throws IllegalArgumentException when a task that does not exist is selected to be mark as done
     */
    public String markAsDone(int taskId) throws IllegalArgumentException {
        try {
            if(taskId <= 0 || taskId > currentIdx) {
                throw new IllegalArgumentException(
                    String.format("taskId of %1$d invalid as there are %2$d recorded task(s)", taskId, currentIdx));
            }
            Task task = tasks.get(taskId - 1);
            boolean isChanged = task.markAsDone();
            if(!isChanged) {
                return "Task is already marked as done";
            }
            saveTasks();
            return "Nice! I've marked this task as done:\n  " + task.toString();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return "No such task exists to be marked as done!";
        }
    }

    /**
     * Allows users to remove items from the list.
     *
     * @param taskId the id of the task starting from 1 for the first task
     * @return a String that confirms the success or failure of the delete operation.
     * @throws IllegalArgumentException when a task does not exist is selected to be deleted
     */
    public String deleteTask(int taskId) throws IllegalArgumentException {
        try {
            if(taskId <= 0 || taskId > currentIdx) {
                throw new IllegalArgumentException(
                    String.format("taskId of %1$d invalid as there are %2$d recorded task(s)", taskId, currentIdx));
            }
            Task deletedTask = tasks.get(taskId - 1);
            tasks.remove(taskId - 1);
            currentIdx--;
            saveTasks();
            return String.format("Noted I've removed this task:\n  %s\nNow you have %d task(s) in the list",
                deletedTask.toString(),
                currentIdx);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return "No such task exists to be deleted!";
        }
    }
}
