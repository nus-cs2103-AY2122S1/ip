package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Storage {
    private Path filePath = null;
    private Path directoryPath = null;

    /**
     * Constructor for a new Storage object.
     *
     * @param path Path for the new storage object.
     */
    public Storage(String path) {
        String home = System.getProperty("user.home");
        this.filePath = java.nio.file.Paths.get(home, "iP", path.split("/")[0], path.split("/")[1]);
        this.directoryPath = java.nio.file.Paths.get(home, "iP", path.split("/")[0]);
    }

    /**
     * Converts Task object to a text format suitable for saving in a storage.
     *
     * @param task Task object to be converted to text.
     * @return String representation of the given task.
     */
    public String convertTaskToText(Task task) {
        String result = task.getType() + "|";
        if (task.isCompleted()) {
            result += "1|";
        } else {
            result += "0|";
        }
        result += task.getTaskContent();
        if (task.getType().equals("D") || task.getType().equals("E")) {
            result += "|" + task.getTiming();
        }
        return result;
    }

    /**
     * Converts text version of a task to an actual Task object.
     *
     * @param text Text representation of a task to be converted to actual Task object.
     * @return Task object corresponding to the given text.
     */
    public Task convertTextToTask(String text) {
        String[] str = text.split("\\|");
        Task newTask;
        if (str[0].equals("T")) {
            newTask = new ToDo(str[2]);
        } else if(str[0].equals("D")) {
            newTask = new Deadline(str[2], str[3]);
        } else {
            newTask = new Event(str[2], str[3]);
        }
        if (str[1].equals("1")) {
            newTask.markCompleted();
        }
        return newTask;
    }

    /**
     * Writes data to the storage.
     *
     * @param taskList List of task to be written to the storage.
     */
    public void writeToFile(TaskList taskList) {
        StringBuilder combinedTask = new StringBuilder();
        for (int i = 0; i < taskList.length(); i++) {
            combinedTask.append(convertTaskToText(taskList.get(i))).append("\n");
        }
        try {
            FileWriter writer = new FileWriter(String.valueOf(filePath), false);
            writer.write(combinedTask.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("error occurred 2");
        }
    }

    /**
     * Creates a new storage file if it is not present.
     */
    public void createFile() {
        File newDirectory = new File(String.valueOf(directoryPath));
        File newFile = new File(String.valueOf(filePath));
        try {
            newDirectory.mkdir();
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error occurred 1");
        }
    }

    /**
     * Writes new data to the storage file if it exists, if not create the file before writing.
     *
     * @param taskList List of task to be saved.
     */
    public void saveTask(TaskList taskList) {
        boolean directoryExists = java.nio.file.Files.exists(filePath);
        if (!directoryExists) {
            createFile();
        }
        writeToFile(taskList);
    }

    /**
     * Returns tasks stored in the storage.
     *
     * @return TaskList containing all the work stored in the storage.
     */
    public TaskList loadTask() {
        TaskList taskList = new TaskList();
        try {
            File myObj = new File(String.valueOf(filePath));
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                taskList.addTask(convertTextToTask(data));
            }
            myReader.close();
            return taskList;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return taskList;
    }
}
