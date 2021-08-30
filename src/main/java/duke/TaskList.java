package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> tasks;
    private File file;

    public TaskList(String filePath) {
        tasks = new ArrayList<Task>();
        this.file = new File(filePath);
    }


    public int size() {
        return tasks.size();
    }

    /**
     * Method that adds a To-Do task onto TaskList
     *
     * @param description The string that contains the description of the task.
     *
     * @return void
     */
    public void addTodo(String description) {
        tasks.add(new Todo(description));
    }

    /**
     * Method that returns the particular element of the list of tasks
     *
     * @param index The index of the task that needs to be got
     *
     * @return Task The task object that is required
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Method that adds a Deadline task onto TaskList
     *
     * @param description The string that contains the description of the task.
     * @param date The date mentioned in the task initialization.
     *
     * @return void
     */
    public void addDeadline(String description, LocalDate date) {
        tasks.add(new Deadline(description, date));
    }

    /**
     * Method that adds an Event onto TaskList
     *
     * @param description The string that contains the description of the task.
     * @param date The date mentioned in the task initialization.
     *
     *
     * @return void
     */
    public void addEvent(String description, LocalDate date) {
        tasks.add(new Event(description, date));
    }

    /**
     * Method that deletes a task from the TaskList
     *
     * @param index The index of the task that needs to be deleted
     *
     * @return void
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Method that runs the Duke by asking for user input
     *
     * @param
     *
     * @return void
     */

    public void readFromFile() {
        try {
            Scanner r = new Scanner(this.file);
            while (r.hasNextLine()) {
                String userInput = r.nextLine();
                String[] split = userInput.split(" ");
                String description = "";
                for (int i = 2; i < split.length - 2; i++) {
                    description = description + split[i] + " ";
                }
                String day = split[split.length - 1].substring(0, split[split.length - 1].length() - 1);
                if (userInput.substring(0,3).equals("[T]")) {
                    this.addTodo(description);
                } else if (userInput.substring(0,3).equals("[D]")) {
                    String[] temp = userInput.split("by");
                    String firstDeadline = temp[0].substring(9);
                    String[] splitDate = temp[1].split(":");
                    System.out.println(splitDate[0]);
                    LocalDate date1 = LocalDate.parse(splitDate[1].substring(1));
                    this.addDeadline(description, date1);
                } else if (userInput.substring(0,3).equals("[E]")) {
                    String[] tempEvent = userInput.split("at");
                    String firstEvent = tempEvent[0].substring(6);
                    String[] splitDate = tempEvent[1].split(":");
                    System.out.println(splitDate[0]);
                    LocalDate date1 = LocalDate.parse(splitDate[1].substring(1));
                    this.addEvent(description, date1);
                }
            }
            r.close();
        } catch (FileNotFoundException e) {
            try {
                String path = "data/";
                File makeFile = new File("data/duke.txt");
                Files.createDirectories(Paths.get(path));
                makeFile.createNewFile();
            } catch (IOException a) {
                System.out.println("Error encountered");
            }
        }
    }
}
