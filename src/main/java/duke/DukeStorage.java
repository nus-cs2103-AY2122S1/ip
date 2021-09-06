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
        FileWriter fw;
        try {
            fw = new FileWriter(TASKS_FILE);
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
        String[] taskInfo;
        String nextTask;

        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                nextTask = s.nextLine();
                if (nextTask.length() == 0) {
                    break;
                }

                taskInfo = nextTask.split("[|]", 4);
                switch (taskInfo[0]) {
                case "T":
                    taskList.addTask(new ToDo(taskInfo[2], taskInfo[1] == "c"));
                    break;
                case "D":
                    try {
                        taskList.addTask(
                                new Deadline(
                                        taskInfo[2],
                                        DukeDate.parseDateInput(taskInfo[3]),
                                        taskInfo[1].equals("c")
                                )
                        );
                    } catch (DukeArgumentException e) {
                        return PARTIAL_LOAD_MESSAGE;
                    }
                    break;
                case "E":
                    try {
                        taskList.addTask(
                                new Event(
                                        taskInfo[2],
                                        DukeDate.parseDateInput(taskInfo[3]),
                                        taskInfo[1].equals("c")
                                )
                        );
                    } catch (DukeArgumentException e) {
                        return PARTIAL_LOAD_MESSAGE;
                    }
                    break;
                default:
                    return PARTIAL_LOAD_MESSAGE;
                }
            }
        } catch (FileNotFoundException e) {
            return "Save file not found";
        }
        return SUCCESS_LOAD_MESSAGE;
    }
}
