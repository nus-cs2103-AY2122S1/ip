package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import duke.exception.DukeArgumentException;
import duke.exception.DukeFileException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;

public class DukeStorage {
    private static final String TASKS_FILE = "data/tasks.txt";
    private static final String DATA_FOLDER = "data/";
    private static final String PARTIAL_LOAD_MESSAGE = "you may have a corrupted/edited save file. "
            + "Tasks partially loaded";
    private static final String SUCCESS_LOAD_MESSAGE = "Tasks successfully loaded!";

    public static void saveTaskList(TaskList taskList) throws DukeFileException {
        File dataPath = new File(DATA_FOLDER);
        if (!Files.exists(Paths.get(DATA_FOLDER))) {
            dataPath.mkdir();
        }

        File f = new File(DATA_FOLDER);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                throw new DukeFileException(
                        "Something went wrong while creating data file, with error message:",
                        e.getMessage()
                );
            }
        }

        assert taskList != null;
        try {
            FileWriter fw = new FileWriter(TASKS_FILE);
            for (int i = 0; i < taskList.getListSize(); i++) {
                fw.write(taskList.getTaskSaveFormat(i) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeFileException(
                    "Something went wrong while saving your tasks, with error message:",
                    e.getMessage()
            );
        }
    }

    public static String loadTasks(TaskList taskList) {
        assert taskList != null;
        File f = new File(TASKS_FILE);

        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String nextTask = s.nextLine();
                if (nextTask.length() == 0) {
                    break;
                }

                String[] taskInfo = nextTask.split("[|]", 5);
                parseTaskIntoTaskList(taskList, taskInfo);
            }
        } catch (FileNotFoundException e) {
            return "Save file not found";
        } catch (DukeArgumentException e) {
            return PARTIAL_LOAD_MESSAGE;
        }

        return SUCCESS_LOAD_MESSAGE;
    }

    private static void parseTaskIntoTaskList(TaskList taskList, String[] taskInfo) throws DukeArgumentException {
        String taskName = taskInfo[2];
        boolean isComplete = taskInfo[1].equals("c");
        String[] tagsArray = taskInfo[3].split(", ", 5);
        switch (taskInfo[0]) {
        case "T":
            taskList.addTask(new ToDo(taskName, isComplete, tagsArray));
            break;
        case "D":
            taskList.addTask(
                    new Deadline(taskName, DukeDate.parseDateInput(taskInfo[4]), isComplete, tagsArray)
            );
            break;
        case "E":
            taskList.addTask(
                    new Event(taskName, DukeDate.parseDateInput(taskInfo[4]), isComplete, tagsArray)
            );
            break;
        default:
            throw new DukeArgumentException(PARTIAL_LOAD_MESSAGE);
        }
    }
}
