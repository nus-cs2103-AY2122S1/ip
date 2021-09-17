package kermit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import kermit.tasks.DateDependentTask;
import kermit.tasks.Deadline;
import kermit.tasks.Event;
import kermit.tasks.Task;
import kermit.tasks.ToDo;

/**
 * Storage loads and saves task data from file.
 */
public class Storage {
    private static final String fileErrorText = "Ribiit! Something was wrong with the file!";
    private String filePath;
    private File file;


    /**
     * Constructs storage.
     *
     * @param filePath The relative path where the data should be read/ saved to.
     */
    public Storage(String filePath) {
        // Check if Kermit data exists, else create it
        this.filePath = filePath;
    }

    /**
     * Processes task and adds it to arrayList.
     *
     * @param taskList Task list to add tasks to.
     * @param description Description of task.
     * @param date Date string of task
     * @param isCompleted If task is completed.
     * @param taskShortForm Short form of task.
     * @throws KermitException if unknown task.
     */
    private void processTaskToTaskList(ArrayList<Task> taskList, String description, Optional<LocalDate> date,
                           boolean isCompleted, String taskShortForm) throws KermitException {
        switch (taskShortForm) {
        case "T":
            taskList.add(new ToDo(description, isCompleted));
            break;
        case "D":
            taskList.add(new Deadline(description, date.get(), isCompleted));
            break;
        case "E":
            taskList.add(new Event(description, date.get(), isCompleted));
            break;
        default:
            throw new KermitException("Unknown task!");
        }
    }

    private boolean parseTaskCompletion(String line) {
        String[] commands = line.split(" \\| ");
        return commands[1].equals("1");
    }

    private String parseTaskShortForm(String line) {
        String[] commands = line.split(" \\| ");
        return commands[0];
    }

    private String parseDescription(String line) {
        String[] commands = line.split(" \\| ");
        return commands[2];
    }

    private Optional<LocalDate> parseDate(String line) throws KermitException {
        String[] commands = line.split(" \\| ");

        if (parseTaskShortForm(line).equals("E") || parseTaskShortForm(line).equals("D")) {
            try {
                return Optional.of(LocalDate.parse(commands[3]));
            } catch (DateTimeParseException e) {
                throw new KermitException("Invalid date in data file!");
            }
        }
        return Optional.empty();
    }

    /**
     * Loads data from the relative file path provided.
     * If the file tree or the file does not exist, it would recursively
     * create any parent folders necessary and the file itself.
     *
     * @return List of tasks that have been loaded from the file.
     * @throws KermitException if data in the file is not readable.
     */
    public List<Task> load() throws KermitException {
        try {
            file = new File(filePath);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }

            boolean didCreateFile = file.createNewFile();
            ArrayList<Task> taskList = new ArrayList<>();
            Scanner sc = new Scanner(file);

            // Read file line by line
            if (!didCreateFile) {
                String line;
                while (sc.hasNextLine()) {
                    line = sc.nextLine();
                    String[] commands = line.split(" \\| ");

                    processTaskToTaskList(taskList,
                            parseDescription(line),
                            parseDate(line),
                            parseTaskCompletion(line),
                            parseTaskShortForm(line));
                }
            }
            return taskList;
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            throw new KermitException(fileErrorText);
        }
    }

    /**
     * Helper function to format tasks.
     * This format is used to write the task to the filePath to allow it to be loaded.
     *
     * @param task Task to be converted into saved form.
     * @return Format string of task to be saved.
     */
    private String formatWriteString(Task task) {
        String delimiter = " | ";

        String taskComplete = task.isComplete() ? "1" : "0";
        String formattedString = String.join(delimiter, task.getShortForm(), taskComplete, task.getDescription());

        if (task instanceof DateDependentTask) {
            DateDependentTask dateTask = (DateDependentTask) task;
            formattedString = String.join(delimiter, formattedString, dateTask.getDateString());
        }
        return formattedString;
    }

    /**
     * Saves todo list to file.
     *
     * @param todo ToDo list to be saved.
     * @throws KermitException if there is an error writing to the file.
     */
    // Saves task list data to file, file is overwritten
    public void save(TaskList todo) throws KermitException {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            Iterator<Task> taskIter = todo.iterator();
            while (taskIter.hasNext()) {
                Task task = taskIter.next();
                String taskData = formatWriteString(task);
                bw.write(taskData);
                if (taskIter.hasNext()) {
                    bw.write("\n");
                }
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            throw new KermitException(fileErrorText);
        }
    }
}
