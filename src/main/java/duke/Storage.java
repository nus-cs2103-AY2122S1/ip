package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private File taskListFile;
    private Boolean doesFileExist;

    /**
     * Constructor for Storage class.
     *
     * @param filePath File path of the file to be accessed.
     */
    public Storage(String filePath) {
        this.taskListFile = new File(filePath);
        this.doesFileExist = this.taskListFile.exists();
    }

    /**
     * Retrieve the data saved and input into task list.
     *
     * @param taskList Task list for the data to be input in.
     * @throws FileNotFoundException If file does not exists.
     */
    public void retrieveFile(TaskList taskList) {
        try {
            Scanner fileScanner = new Scanner(taskListFile);
            while (fileScanner.hasNext()) {
                String currLine = fileScanner.nextLine();
                String currTaskType = currLine.substring(3, 4);
                String currTaskCheckBox = currLine.substring(6, 7);
                String currTask = currLine.substring(9);
                switch (currTaskType) {
                case "T":
                    taskList.addTask(new Todo(currTask));
                    break;
                case "D":
                    String[] partsD = currTask.split("by: ");
                    String deadlineDescription = partsD[0].replace(" (", "");
                    String deadlineDate = partsD[1].substring(0, 11);
                    String deadlineTime = partsD[1].substring(12).replace(")", "");
                    Task currDeadlineTask = new Deadline(deadlineDescription, deadlineDate, deadlineTime);
                    taskList.addTask(currDeadlineTask);
                    break;
                case "E":
                    String[] partsE = currTask.split("at: ");
                    String eventDescription = partsE[0].replace(" (", "");
                    String eventDate = partsE[1].substring(0, 11);
                    String eventTime = partsE[1].substring(12).replace(")", "");
                    Task currEventTask = new Event(eventDescription, eventDate, eventTime);
                    taskList.addTask(currEventTask);
                    break;
                default:
                }
                if (currTaskCheckBox.equals("X")) {
                    taskList.getTask(taskList.taskListSize() - 1).markAsDone();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.print(e.toString());
        }

    }

    /**
     * Saves the data in task list onto a file.
     *
     * @param taskList Task list that data is retrieved from.
     */
    public void saveFile(TaskList taskList) {
        try {
            FileWriter dukeTaskListWriter = new FileWriter(this.taskListFile);
            dukeTaskListWriter.write(taskList.toString());
            dukeTaskListWriter.close();
        } catch (IOException e) {
            System.out.print(e.toString());
        }
    }

    /**
     * Checks if the file specified exists.
     *
     * @return True if file exists and false if it does not.
     */
    public Boolean getDoesFileExists() {
        return this.doesFileExist;
    }

    /**
     * Creates a new file.
     */
    public void createFile() {
        try {
            this.taskListFile.getParentFile().mkdir();
            this.taskListFile.createNewFile();
            this.doesFileExist = true;
        } catch (IOException e) {
            System.out.print(e.toString());
        }
    }
}
