package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/** Class that holds that task in the form of a list and does operations
 * on the elements inside  */
public class TaskList {
    private ArrayList<Task> tasks;
    private File file;

    /**
     * Constructor for TaskList
     *
     * @param filePath The file path of the file that holds the contents
     */
    public TaskList(String filePath) {
        tasks = new ArrayList<Task>();
        this.file = new File(filePath);
    }

    /**
     * Returns the number of elements (tasks) in the task list
     *
     * @return int The number of elements in the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the particular element of the list of tasks
     *
     * @param index The index of the task that needs to be got
     * @return Task The task object that is required
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a To-Do task onto TaskList
     *
     * @param description The string that contains the description of the task.
     */
    public void addTodo(String description) {
        tasks.add(new Todo(description));
    }

    /**
     * Adds a Deadline task onto TaskList
     *
     * @param description The string that contains the description of the task.
     * @param date The date mentioned in the task initialization.
     */
    public void addDeadline(String description, LocalDateTime date) {
        tasks.add(new Deadline(description, date));
    }

    /**
     * Adds an Event onto TaskList
     *
     * @param description The string that contains the description of the task.
     * @param date The date mentioned in the task initialization.
     */
    public void addEvent(String description, LocalDateTime date) {
        tasks.add(new Event(description, date));
    }

    /**
     * Deletes a task from the TaskList
     *
     * @param index The index of the task that needs to be deleted
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    /**
     * Returns the entire task list as a whole as an ArrayList
     *
     * @return A collection of Task objects
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Reads the data from text file and adds it to task list
     */
    public void readFromFile() {
        try {
            Scanner r = new Scanner(this.file);
            while (r.hasNextLine()) {
                String userInput = r.nextLine();
                String[] splitUserInput = userInput.substring(7).split(" ");
                String description = "";
                for (int i = 0; i < splitUserInput.length - 3; i++) {
                    description = description + splitUserInput[i] + " ";
                }
                if (userInput.substring(0,3).equals("[T]")) {
                    String descriptionTodo = "";
                    for (int i = 0; i < splitUserInput.length; i++) {
                        descriptionTodo = descriptionTodo + splitUserInput[i] + " ";
                    }
                    readTodo(userInput, descriptionTodo);
                } else if (userInput.substring(0,3).equals("[D]")) {
                    readDeadline(userInput, description);
                } else if (userInput.substring(0,3).equals("[E]")) {
                    readEvent(userInput, description);
                }
            }
            r.close();
        } catch (FileNotFoundException e) {
            try {
                //below file creation adapted from https://www.w3schools.com/java/java_files_create.asp
                String path = "data/";
                File makeFile = new File("data/duke.txt");
                Files.createDirectories(Paths.get(path));
                makeFile.createNewFile();
            } catch (IOException a) {
                System.out.println("Error encountered");
            }
        }
    }

    /**
     * Adds the To-Do task to the list and marks it as done if it is completed
     *
     * @param userInput The input entered by the user
     * @param description The description of the task
     */
    public void readTodo(String userInput, String description) {
        this.addTodo(description);
        int index = this.size();
        if (userInput.substring(3,6).equals("[X]")) {
            this.get(index-1).setDone();
        }
    }

    /**
     * Adds the Deadline task to the list and marks it as done if it is completed
     *
     * @param userInput The input entered by the user
     * @param description The description of the task
     */
    public void readDeadline(String userInput, String description) {
        String[] splitDateAndDescription = userInput.split("by:");
        assert splitDateAndDescription[1].length() > 0 : "Date cannot be empty";
        String deadlineDate = splitDateAndDescription[1].substring(1);
        String[] dateComponents = deadlineDate.split(" ");
        String date = dateComponents[0];
        String hour = dateComponents[1].substring(0,2);
        String minutes = dateComponents[1].substring(3);
        String dateTimeString = date + " " + hour + minutes;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime finalDeadlineDateTime = LocalDateTime.parse(dateTimeString, formatter);
        this.addDeadline(description, finalDeadlineDateTime);
        int index = this.size();
        if (userInput.substring(3,6).equals("[X]")) {
            this.get(index-1).setDone();
        }
    }

    /**
     * Adds the Event task to the list and marks it as done if it is completed
     *
     * @param userInput The input entered by the user
     * @param description The description of the task
     */
    public void readEvent(String userInput, String description) {
        String[] splitDateAndString = userInput.split("at:");
        assert splitDateAndString[1].length() > 0 : "Date cannot be empty";
        String str = splitDateAndString[1].substring(1);
        String[] dateComponents = str.split(" ");
        String date = dateComponents[0];
        String hour = dateComponents[1].substring(0,2);
        String minutes = dateComponents[1].substring(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String dateTime = date + " " + hour + minutes;
        LocalDateTime eventDateTime = LocalDateTime.parse(dateTime, formatter);
        this.addEvent(description, eventDateTime);
        int index = this.size();
        if (userInput.substring(3,6).equals("[X]")) {
            this.get(index-1).setDone();
        }
    }
}
