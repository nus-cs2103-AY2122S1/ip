package duke;

import duke.tasktypes.Deadlines;
import duke.tasktypes.Events;
import duke.tasktypes.Task;
import duke.tasktypes.TaskList;
import duke.tasktypes.ToDos;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Deals with loading tasks from the data file
 * and saving tasks in the data file.
 */
public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the data previously stored in the file.
     * @return ArrayList of data.
     */
    public ArrayList<Task> load() {
        // load data from hard disk when Dory starts up and starts bot
        Path dataFolderPath = Paths.get("data");
        Path dataFilePath = Paths.get("data/dory.txt");
        boolean doesFolderExist = Files.exists(dataFolderPath);
        boolean doesFileExist = Files.exists(dataFilePath);
        ArrayList<Task> dataForDory = new ArrayList<>();
        if (doesFolderExist) {
            if (doesFileExist) {
                try {
                    List<String> data = Files.readAllLines(dataFilePath);
                    for (int i = 0; i < data.size(); i++) {
                        String[] lineTask = data.get(i).split("/");
                        String taskType = lineTask[0];
                        String isDone = lineTask[1];
                        String taskDesc = lineTask[2];

                        boolean doneOrNot = (isDone.equals("true"));
                        if (taskType.equals("T")) {
                            ToDos toDo = new ToDos(taskDesc);
                            toDo.changeStatus(doneOrNot);
                            dataForDory.add(toDo);
                        } else if (taskType.equals("D")) {
                            String finishBy = lineTask[3];
                            Deadlines deadline = new Deadlines(taskDesc, finishBy);
                            deadline.changeStatus(doneOrNot);
                            dataForDory.add(deadline);
                        } else if (taskType.equals("E")) {
                            String dateOfEvent = lineTask[3];
                            Events event = new Events(taskDesc, dateOfEvent);
                            event.changeStatus(doneOrNot);
                            dataForDory.add(event);
                        }
                    }
                    return dataForDory;
                    //startChatBot(dataForDory);
                } catch (IOException e) {
                    return dataForDory;
                    //startChatBot(dataForDory);
                }
            } else {
                try {
                    Files.createFile(dataFilePath);
                } catch (IOException e) {
                    System.out.println("failed to create a data file");
                }
            }
        } else {
            try {
                Files.createDirectories(dataFolderPath);
                Files.createFile(dataFilePath);
            } catch (IOException e) {
                System.out.println("failed to create a data folder");
            }
        }
        return dataForDory;
    }


    /**
     * Updates the data in the file with new data.
     * @param taskList TaskList containing all tasks.
     */
    public void updateHardDisk(TaskList taskList) {
        Path dataFilePath = Paths.get("data/dory.txt");
        try {
            BufferedWriter bw = Files.newBufferedWriter(dataFilePath);
            ArrayList<Task> taskArrayList = taskList.getTaskList();
            for (int i = 0; i < taskArrayList.size(); i++) {
                String fullLine = taskList.get(i).hardDiskSave();
                bw.write(fullLine);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }

}
