package duke;

import duke.tasktypes.Deadline;
import duke.tasktypes.Event;
import duke.tasktypes.Task;
import duke.tasktypes.TaskList;
import duke.tasktypes.ToDo;
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

    /**
     * Constructor for Storage class.
     *
     * @param filePath filepath of the txt file storing data.
     */
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
                        assert data.get(i).contains("/");
                        String[] lineTask = data.get(i).split("/");
                        String taskType = lineTask[0];
                        boolean isDone = lineTask[1].equals("true");
                        String taskDesc = lineTask[2];

                        if (taskType.equals("T")) {
                            ToDo toDo = new ToDo(taskDesc);
                            toDo.changeStatus(isDone);
                            dataForDory.add(toDo);
                        } else if (taskType.equals("D")) {
                            String date = lineTask[3];
                            Deadline deadline = new Deadline(taskDesc, date);
                            deadline.changeStatus(isDone);
                            dataForDory.add(deadline);
                        } else if (taskType.equals("E")) {
                            String dateOfEvent = lineTask[3];
                            Event event = new Event(taskDesc, dateOfEvent);
                            event.changeStatus(isDone);
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
                String fullLine = taskList.get(i).saveToHardDisk();
                bw.write(fullLine);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }

}
