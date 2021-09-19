package main.java.duke;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import main.java.duke.extensions.Contact;
import main.java.duke.tasks.Deadline;
import main.java.duke.tasks.Event;
import main.java.duke.tasks.Task;
import main.java.duke.tasks.Todo;

/**
 * A functionality class for methods that interacts with text file.
 */
public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return this.filePath;
    }

    /**
     * Converts a line from text file to a task
     *
     * @param s a line from text file
     * @return a task from the string
     */
    private Task stringToTask(String s) {
        boolean isDone = false;
        if (s.startsWith("[X]", 4)) {
            isDone = true;
        }
        Task task;
        if (s.startsWith("[T]")) {
            task = new Todo(s.substring(8));
        } else if (s.startsWith("[D]")) {
            String name = s.split(":")[0].substring(8);
            String date = s.split(":")[1];
            task = new Deadline(name, date);
        } else if (s.startsWith("[E]")) {
            String name = s.split(":")[0].substring(8);
            String time = s.split(":")[1];
            task = new Event(name, time);
        } else {
            String name = s.split(": ")[0];
            String contactNumber = s.split(": ")[1];
            task = new Contact(name, Integer.parseInt(contactNumber));
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Loads stored tasks from text file
     *
     * @return the list of tasks stored in file
     * @throws DukeException duke exception
     * @throws IOException IO exception
     */
    public ArrayList<Task> load() throws DukeException, IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        File nekoData = new File(this.filePath);
        if (!nekoData.exists()) {
            nekoData.getParentFile().mkdirs();
            nekoData.createNewFile();
        } else {
            Scanner scanner = new Scanner(nekoData);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                Task task = stringToTask(s);
                taskList.add(task);
            }
            scanner.close();
        }
        return taskList;
    }

    /**
     * Writes a task to the text file
     *
     * @param taskString content of the task
     * @throws IOException IO exception
     */
    public void saveTaskToFile(String taskString) throws IOException {
        FileWriter writer = new FileWriter(this.filePath, true);
        writer.append(taskString);
        writer.flush();
        writer.close();
    }

    /**
     * Deletes a task from the text file
     *
     * @param num the index of the task in the list
     * @param scanner scanner for the text file
     * @param tasks the task list
     * @throws IOException IO exception
     */
    public void deleteTaskFromFile(int num, Scanner scanner, TaskList tasks) throws IOException {

        Task task = tasks.taskList.get(num - 1);
        StringBuilder fileString = new StringBuilder();
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            fileString.append(input).append("\n");
        }
        System.out.println(task.toString());
        String newFile = fileString.toString().replace(task.toString(), "");
        FileWriter writer = new FileWriter(this.filePath);
        writer.write(newFile);
        writer.flush();
        writer.close();
    }
    /**
     * Marks a task as done in the list in the text file.
     *
     * @param num the index of the task in the list
     * @param scanner scanner for the file
     * @param tasks the given tasks list
     * @throws IOException IO exception
     */
    public void markAsDoneInFile(int num, Scanner scanner, TaskList tasks) throws IOException {
        Task task = tasks.taskList.get(num - 1);
        StringBuilder fileString = new StringBuilder();
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            fileString.append(input).append("\n");
        }
        System.out.println(task.toString());
        String newFile = fileString.toString().replace(task.toString(), task.toString().replace("[ ]", "[X]"));
        FileWriter writer = new FileWriter(this.filePath);
        writer.write(newFile);
        writer.flush();
        writer.close();
    }
}
