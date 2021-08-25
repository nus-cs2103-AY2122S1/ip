package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    File taskListFile;
    Boolean doesFileExists;

    public Storage(String filePath) {
        this.taskListFile = new File(filePath);
        this.doesFileExists = this.taskListFile.exists();
    }

    /**
     * Retrieve the data saved and input into task list.
     *
     * @param taskList Task list for the data to be input in.
     * @throws FileNotFoundException If file does not exists.
     */
    public void retrieveFile(TaskList taskList) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(taskListFile);
        while (fileScanner.hasNext()) {
            String currLine = fileScanner.nextLine();
            String currTaskType = currLine.substring(3,4);
            String currTaskCheckBox = currLine.substring(6,7);
            String currTask = currLine.substring(9);
            switch (currTaskType) {
                case "T":
                    taskList.addTask(new Todo(currTask));
                    break;
                case "D":
                    String[] partsD = currTask.split("by: ");
                    Task currDeadlineTask = new Deadline(partsD[0].replace(" (", ""),
                            partsD[1].substring(0,11),
                            partsD[1].substring(12).replace(")", ""));
                    taskList.addTask(currDeadlineTask);
                    break;
                case "E":
                    String[] partsE = currTask.split("at: ");
                    Task currEventTask = new Event(partsE[0].replace(" (", ""),
                            partsE[1].substring(0,11),
                            partsE[1].substring(12).replace(")", ""));
                    taskList.addTask(currEventTask);
                    break;
            }
            if (currTaskCheckBox.equals("X")) {
                taskList.getTask(taskList.taskListSize() - 1).markAsDone();
            }
        }

    }

    /**
     * Saves the data in task list onto a file.
     *
     * @param taskList Task list that data is retrieved from.
     * @throws IOException If there is wrong input or output.
     */
    public void saveFile(TaskList taskList) throws IOException {
        StringBuilder listBuilder = new StringBuilder();
        for (int j = 0; j < Task.noOfTask; j++) {
            String listItem = (j + 1)
                    + "."
                    + taskList.getTask(j).getTaskType()
                    + taskList.getTask(j).checkIsDone()
                    + " " + taskList.getTask(j).getDescription();
            listBuilder.append(listItem).append(System.lineSeparator());
        }
        FileWriter dukeTaskListWriter = new FileWriter(this.taskListFile);
        dukeTaskListWriter.write(listBuilder.toString());
        dukeTaskListWriter.close();
    }

    /**
     * Checks if the file specified exists.
     *
     * @return True if file exists and false if it does not.
     */
    public Boolean getDoesFileExists() {
        return this.doesFileExists;
    }

    /**
     * Creates a new file.
     *
     * @throws IOException If there is wrong input or output.
     */
    public void createFile() throws IOException {
        this.taskListFile.getParentFile().mkdir();
        this.taskListFile.createNewFile();
        this.doesFileExists = true;
    }
}
