package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Objects;
import java.util.Scanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class takes in a saved file and loads whatever tasks were saved in it.
 */
public class Storage {
    private String filePath;
    private Scanner saveFile;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructor for Storage taking in a Scanner file
     *
     * @param saveFile scanner file
     */
    public Storage(Scanner saveFile) {
        this.saveFile = saveFile;
    }

    /**
     * Constructor for Storage taking in a filePath
     *
     * @param filePath filePath to the save file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        File directory = new File(filePath);
        try {
            File dataFile = directory.getParentFile();
            if (dataFile.mkdir()) {
                Ui.createNewDirectory();
            }
            if (directory.createNewFile()) {
                Ui.createNewFile();
            }
            File file = new File(filePath);
            this.saveFile = new Scanner(file);
            assert file.canRead(): "file should be readable";
        } catch (FileNotFoundException e) {
            Ui.fileNotFoundError();
        } catch (IOException e) {
            System.out.println("Input error!");
        }
    }

    /**
     * Loads a saved file.
     * The data file should be structured in this manner:
     * - In each line, start with a T(odo), D(eadline), E(vent)
     * - Space out each detail with a "|'
     * - If empty line, assume end of file
     *
     * @return a TaskList with all the saved details.
     */
    public TaskList load() {
        TaskList lst = new TaskList();
        while (saveFile.hasNextLine()) {
            String fullLine = saveFile.nextLine();
            if (Objects.equals(fullLine, "")) {
                break;
            }
            String[] data = fullLine.split("\\|", 7);
            String type = data[0].substring(0, 1);
            String isDone = data[1].substring(1, 2);
            String isTagged = data[2].substring(1, 2);
            String taskName = data[3].substring(1);

            switch (type) {
            case "T":
                handleTodo(lst, taskName, isDone, isTagged, data);
                break;
            case "D":
                handleDeadline(lst, taskName, isDone, isTagged, data);
                break;
            case "E":
                handleEvent(lst, taskName, isDone, isTagged, data);
                break;
            }
        }
        return lst;
    }

    /**
     * Saves the current storage file based on the input given.
     *
     * @param input input given, typically the full string of whatever tasks are on the task list.
     */
    public void save(String input) {
        try {
            PrintWriter newFile = new PrintWriter(this.filePath);
            newFile.println(input);
            newFile.close();
        } catch (FileNotFoundException e) {
            Ui.fileNotFoundError();
        }
    }

    /**
     * Handles the case where the current line in the saved file starts with a "T"
     *
     * @param lst given tasklist
     * @param taskName name of task
     * @param isDone boolean if the task is done
     * @param isTagged boolean if the task is tagged
     * @param data contains all necessary data to be loaded
     */
    private void handleTodo(TaskList lst, String taskName, String isDone, String isTagged, String[] data) {
        Task eventTodo = new ToDo(taskName);
        if (isDone.contains("1")) {
            eventTodo.doneTask(false);
        }
        if (isTagged.contains("1")) {
            String[] tags = data[4].split("#");
            for (int i = 1; i < tags.length; i++) {
                eventTodo.tag(tags[i]);
            }
        }
        lst.add(eventTodo, false);
    }

    /**
     * Handles the case where the current line in the saved file starts with a "E"
     *
     * @param lst given tasklist
     * @param taskName name of task
     * @param isDone boolean if the task is done
     * @param isTagged boolean if the task is tagged
     * @param data contains all necessary data to be loaded
     */
    private void handleEvent(TaskList lst, String taskName, String isDone, String isTagged, String[] data) {
        String time = data[5].substring(1);
        Event eventEvent = new Event(taskName, LocalDateTime.parse(time, formatter));
        if (isDone.contains("1")) {
            eventEvent.doneTask(false);
        }
        if (isTagged.contains("1")) {
            String[] tags = data[4].split("#");
            for (int i = 1; i < tags.length; i++) {
                eventEvent.tag(tags[i]);
            }
        }
        lst.add(eventEvent, false);
    }


    /**
     * Handles the case where the current line in the saved file starts with a "E"
     *
     * @param lst given tasklist
     * @param taskName name of task
     * @param isDone boolean if the task is done
     * @param isTagged boolean if the task is tagged
     * @param data contains all necessary data to be loaded
     */
    private void handleDeadline(TaskList lst, String taskName, String isDone, String isTagged, String[] data) {
        String deadline = data[5].substring(1);
        Deadline eventDeadline = new Deadline(taskName, LocalDateTime.parse(deadline, formatter));
        if (isDone.contains("1")) {
            eventDeadline.doneTask(false);
        }
        if (isTagged.contains("1")) {
            String[] tags = data[4].split("#");
            for (int i = 1; i < tags.length; i++) {
                eventDeadline.tag(tags[i]);
            }
        }
        lst.add(eventDeadline, false);
    }
}
