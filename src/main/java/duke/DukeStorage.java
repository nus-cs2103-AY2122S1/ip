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
    private static String tasksFile = "data/tasks.txt";
    private static String dataFolder = "data/";

    public static void saveTaskList(TaskList taskList) throws DukeFileException {
        File dataPath = new File(dataFolder);
        if (!Files.exists(Paths.get(dataFolder))) {
            dataPath.mkdir();
        }

        File f = new File(tasksFile);
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

        FileWriter fw;
        try {
            fw = new FileWriter(tasksFile);
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
        File f = new File(tasksFile);
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
                        DukeUi.printLine(e.getMessage());
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
                        DukeUi.printLine(e.getMessage());
                    }
                    break;
                default:
                    return "you may have a corrupted/edited save file. Tasks partially loaded";
                }
            }
        } catch (FileNotFoundException e) {
            return "Save file not found";
        }
        return "Tasks successfully loaded!";
    }
}
