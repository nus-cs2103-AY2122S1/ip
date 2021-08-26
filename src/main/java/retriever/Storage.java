package retriever;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

import retriever.task.*;

/**
 * This class helps to read, write, update and delete
 * tasks from the file it is being stored in.
 */
public class Storage {
    private String filePath;

    /**
     * Sets the file path, so that operations such as read and write may be
     * performed on that file.
     *
     * @param filePath The file path in which tasks may be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a new file in case the file doesn't exist.
     */
    public void createNewFile() {
        try {
            File myFile = new File(filePath);

            if (myFile.createNewFile()) {
                System.out.println("Woof! File is Created!");
            } else {
                System.out.println("File Already Exists Master.");
            }
        } catch (IOException e) {
            System.out.println("Master, Your Computer Has Issues!");
        }
    }

    /**
     * Reads the tasks from the text file and returns it
     * as an ArrayList.
     *
     * @return An ArrayList with the tasks stored in it.
     */
    public ArrayList<Task> readTasks() {
        ArrayList<Task> taskList = new ArrayList<Task>();

        try {
            Scanner scanner = new Scanner(new File(filePath));

            while (scanner.hasNext()) {
                // Parsing the line read from file
                String[] tokens = scanner.nextLine().split(" \\| ");

                // Storing the extracted task components.
                String taskType = tokens[0];
                String completionStatus = tokens[1];
                String taskDescription = tokens[2];

                // Checking the type of task.
                if (taskType.contains("D")) {
                    Task deadlineTask = new Deadline(taskDescription, new TaskDateAndTime(tokens[3]));

                    if (completionStatus.contains("1")) {
                        deadlineTask.markAsDone();
                    }

                    taskList.add(deadlineTask);
                }

                if (taskType.contains("E")) {
                    Task eventTask = new Event(taskDescription, new TaskDateAndTime(tokens[3]));

                    if (completionStatus.contains("1")) {
                        eventTask.markAsDone();
                    }

                    taskList.add(eventTask);
                }

                if (taskType.contains("T")) {
                    Task todoTask = new Todo(taskDescription);

                    if (completionStatus.contains("1")) {
                        todoTask.markAsDone();
                    }

                    taskList.add(todoTask);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Master, File Does Not Exist, Give Me A Treat, And\n"
                    + "I Shall Create One For You! :)");
            // In case the file doesn't exist, one is created.
            createNewFile();
        }
        return taskList;
    }

    /**
     * Writes the task entered to the text file.
     *
     * @param task An enum representing the task type.
     * @param taskDescription The description of the task.
     * @param taskDeadline The deadline of the task.
     */
    public void writeTask(Task.TaskType task, String taskDescription, String taskDeadline) {
        String taskAsText;

        // Formatting the string appropriately to add task to the text file.
        switch (task) {
        case DEADLINE:
            taskAsText = "D | 0 | " + taskDescription + " | " + taskDeadline + System.getProperty("line.separator");
            break;
        case EVENT:
            taskAsText = "E | 0 | " + taskDescription + " | " + taskDeadline + System.getProperty("line.separator");
            break;
        case TODO:
            taskAsText = "T | 0 | " + taskDescription + System.getProperty("line.separator");
            break;
        default:
            taskAsText = " ";
            break;
        }

        // Writing the task to file.
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(taskAsText);
            fw.close();
        } catch (IOException e) {
            System.out.println("Master, Sorry! I Ate The File!");
        }
    }

    /**
     * Deletes a task from the text file.
     *
     * @param taskNumber The task number to be deleted.
     */
    public void deleteTask(int taskNumber) {
        int counter = 0;
        File inputFile = new File(filePath);
        File tempFile = new File("tempFile.txt");

        // Reading and writing to the text file.
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (counter == taskNumber) {

                } else {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
                counter++;
            }

            writer.close();
            reader.close();

            boolean isNameChangeSuccessful = tempFile.renameTo(inputFile);
        } catch (IOException e) {
            System.out.println("Master! Error Reading File. Gimme Treats, And I Help You!");
        }
    }

    /**
     * Updates a given task status to done in the text file.
     *
     * @param taskNumber The task number to be updated.
     */
    public void updateTaskStatusToDone(int taskNumber) {
        int counter = 0;
        File inputFile = new File(filePath);
        File tempFile = new File("tempFile.txt");

        // Reading and writing to the text file.
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (counter == taskNumber) {
                    String[] taskToUpdate = currentLine.split(" \\| ");

                    String newLine = taskToUpdate[0] + " | 1 | " + taskToUpdate[2];

                    if (!taskToUpdate[0].contains("T")) {
                        newLine += " | " + taskToUpdate[3];
                    }

                    writer.write(newLine + System.getProperty("line.separator"));
                } else {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
                counter++;
            }

            writer.close();
            reader.close();

            boolean isNameChangeSuccessful = tempFile.renameTo(inputFile);
        } catch (IOException e) {
            System.out.println("Master! Error Reading File. Gimme Treats, And I Help You!");
        }
    }
}

