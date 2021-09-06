package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Loads and save the tasks in the file
 */
public class Storage {
    private final File taskFile;

    /**
     * Class Constructor
     *
     * @param filePath The file to save and load tasks
     */
    public Storage(String filePath) {
        this.taskFile = new File(filePath);
    }

    /**
     * Loads the saved tasks from the file
     *
     * @return ArrayList of tasks in the file
     */

    public ArrayList<Task> load() {
        if (!this.taskFile.exists()) {
            try {
                if (this.taskFile.getParentFile().mkdirs()) {
                    System.out.println("Directory for file created.");
                }
                if (this.taskFile.createNewFile()) {
                    System.out.println("File created: duke.txt");
                }
            } catch (IOException e) {
                System.out.println("Error has occurred when creating file!");
            }
        }

        ArrayList<Task> tasks = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd MMM yyyy HHmm");
        try {
            Scanner fileScanner = new Scanner(this.taskFile);
            while (fileScanner.hasNextLine()) {
                String current = fileScanner.nextLine();
                Task currentTask = null;
                switch(current.charAt(1)) {
                case 'T': {
                    currentTask = new Todo(current.substring(7));
                    break;
                }

                case 'D': {
                    int index = current.indexOf("by");
                    LocalDateTime by = LocalDateTime.parse(current.substring(index + 4, current.length() - 1),
                                                             formatter);
                    currentTask = new Deadline(current.substring(7, index - 2), by);
                    break;
                }

                case 'E': {
                    int index = current.indexOf("at");
                    LocalDateTime at = LocalDateTime.parse(current.substring(index + 4, current.length() - 1),
                                                             formatter);
                    currentTask = new Event(current.substring(7, index - 2), at);
                    break;
                }

                default: {
                    assert false;
                }
                }
                if (current.charAt(4) == 'X') {
                    currentTask.markAsDone();
                }
                tasks.add(currentTask);
            }
        } catch (FileNotFoundException e) {
            System.out.println("duke.txt not found!");
        }
        return tasks;
    }

    /**
     * Saves the new task to the file
     *
     * @param content the content of the task
     */
    public void writeTask(String content) {
        try {
            FileWriter fileWriter = new FileWriter(this.taskFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error has occurred when writing to file!");
        }
    }

    /**
     * Edits the content of the task in the file
     *
     * @param oldContent old content of the task
     * @param newContent new content of the task
     */
    public void editTask(String oldContent, String newContent) {
        String currentFileContent = readFileContent();
        String newFileContent = currentFileContent.replace(oldContent, newContent);
        writeContentToFile(newFileContent);
    }

    /**
     * Removes the task from the file
     *
     * @param taskContent the content of the task
     */
    public void deleteTask(String taskContent) {
        String newFileContent = deleteFileContent(taskContent);
        writeContentToFile(newFileContent);
    }

    /**
     * Writes the given content to the file
     *
     * @param content the content
     */
    public void writeContentToFile(String content) {
        try {
            FileWriter fileWriter = new FileWriter(this.taskFile);
            fileWriter.write(content);
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("duke.txt not found!");
        } catch (IOException e) {
            System.out.println("Error has occurred when writing to file!");
        }
    }

    /**
     * Reads the file to get the content
     *
     * @return the file content
     */
    public String readFileContent() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.taskFile));
            String line = reader.readLine();
            StringBuilder currentFileContent = new StringBuilder();
            while (line != null) {
                currentFileContent.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
            reader.close();
            return currentFileContent.toString();
        } catch (FileNotFoundException e) {
            System.out.println("duke.txt not found!");
        } catch (IOException e) {
            System.out.println("Error has occurred when editing file!");
        }
        return null;
    }

    /**
     * Gives the content with task deleted
     *
     * @param taskContent the content of task to be deleted
     * @return the content with task deleted
     */
    public String deleteFileContent(String taskContent) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.taskFile));
            String line = reader.readLine();
            StringBuilder newFileContent = new StringBuilder();
            while (line != null) {
                if (!line.equals(taskContent)) {
                    newFileContent.append(line).append(System.lineSeparator());
                }
                line = reader.readLine();
            }
            reader.close();
            return newFileContent.toString();
        } catch (FileNotFoundException e) {
            System.out.println("duke.txt not found!");
        } catch (IOException e) {
            System.out.println("Error has occurred when editing file!");
        }
        return null;
    }
}
