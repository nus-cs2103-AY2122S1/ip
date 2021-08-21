import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 *
 * @author marcuspeh
 * @version A-MoreOOP
 * @since 21 Aug 2021
 */
public class Storage {
    /** File name for the storage. */
    private final String LOCATION = "./data/duke.txt";
    /** To deals with the errorMessages. */
    private Ui ui;

    /**
     * Constructor for Storage.
     *
     * @param ui to deal with the errorMessages.
     */
    Storage(Ui ui) {
        this.ui = ui;
    }

    /**
     * Export the task in list to a txt file in ./data.
     *
     * @param taskList list of task to be stored.
     */
    public void exportTask(List<Task> taskList) {
        try {
            File file = new File(LOCATION);
            file.getParentFile().mkdirs();
            file.createNewFile();

            FileWriter fileWrite = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWrite);
            taskList.stream()
                    .forEach(task -> {
                        try {
                            bufferedWriter.write(task.saveOutput());
                            bufferedWriter.newLine();
                        } catch (IOException e) {
                            ui.exportTaskErrorMessage(task);
                        }
                    });
            bufferedWriter.flush();
        } catch (IOException e) {
            ui.exportTaskErrorMessage();
        }
    }

    /**
     * Loads the txt file containing information on the task.
     *
     * @return A list of all the task stored.
     */
    public List<Task> importTask() {
        File file = new File(LOCATION);
        List<Task> taskList = new ArrayList<>();
        try {
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
                                    ui.importTaskErrorMessage(taskData[1]);
                                }
                            } else if (taskData[0].equals(Keyword.EVENTS.getSaveWord())) {
                                try {
                                    taskList.add(new Events(taskData[1], taskData[3],
                                            taskData[2].equals("1") ? true : false));
                                } catch (ParseException e) {
                                    ui.importTaskErrorMessage(taskData[1]);
                                }
                            } else if (taskData[0].equals(Keyword.TODOS.getSaveWord())) {
                                taskList.add(new ToDos(taskData[1],
                                        taskData[2].equals("1") ? true : false));
                            }
                        }
                    });
        } catch (FileNotFoundException e) {
            ui.importTaskErrorMessage();
        }
        return taskList;
    }
}
