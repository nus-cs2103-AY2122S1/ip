package duke.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 *
 * @author marcuspeh
 * @version A-JavaDoc
 * @since 23 Aug 2021
 */
public class Storage {
    /** File name for the storage. */
    private static final String LOCATION_OF_FILE = "./data/duke.txt";
    /** To deals with the errorMessages. */
    private Ui ui;

    /**
     * Constructor for duke.main.Storage.
     *
     * @param ui to deal with the errorMessages.
     */
    public Storage(Ui ui) {
        this.ui = ui;
    }

    /**
     * Exports the task in list to a txt file in ./data.
     *
     * @param taskList list of task to be stored.
     */
    public void exportTask(List<Task> taskList) {
        try {
            File file = new File(LOCATION_OF_FILE);
            file.getParentFile().mkdirs();
            file.createNewFile();

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            taskList.stream()
                    .forEach(task -> {
                        try {
                            bufferedWriter.write(task.saveOutput());
                            bufferedWriter.newLine();
                        } catch (IOException e) {
                            ui.formatExportTaskErrorMessage(task);
                        }
                    });
            bufferedWriter.flush();
        } catch (IOException e) {
            ui.formatExportTaskErrorMessage();
        }
    }

    /**
     * Loads the txt file containing information on the task.
     * If dir/file is not found, a welcome message will be printed.
     *
     * @return A list of all the task stored.
     */
    public List<Task> importTask() throws FileNotFoundException {
        File file = new File(LOCATION_OF_FILE);
        List<Task> taskList = new ArrayList<>();
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        bufferedReader.lines()
                .forEach(task -> {
                    String[] taskData = task.split(" \\| ");
                    if (taskData.length == 3 || taskData.length == 4) {
                        if (taskData[0].equals(Keyword.DEADLINE.getSaveWord())) {
                            try {
                                taskList.add(new Deadlines(taskData[1], taskData[3],
                                        taskData[2].equals("1") ? true : false));
                            } catch (ParseException e) {
                                ui.formatImportTaskErrorMessage(taskData[1]);
                            }
                        } else if (taskData[0].equals(Keyword.EVENTS.getSaveWord())) {
                            try {
                                taskList.add(new Events(taskData[1], taskData[3],
                                        taskData[2].equals("1") ? true : false));
                            } catch (ParseException e) {
                                ui.formatImportTaskErrorMessage(taskData[1]);
                            }
                        } else if (taskData[0].equals(Keyword.TODOS.getSaveWord())) {
                            taskList.add(new ToDos(taskData[1],
                                    taskData[2].equals("1") ? true : false));
                        }
                    }
                });
        return taskList;
    }
}
