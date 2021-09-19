package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Handles saving of tasks and loading said saves.
 * @author Thomas Hogben
 */
public class Storage {
    private Path saveDirectory = Paths.get(".", "duke_data");
    private Path savePath = Paths.get(saveDirectory.toString(), "duke.txt");
    private Ui ui;

    /**
     * @param ui A Ui to send display commands to.
     */
    public Storage(Ui ui) {
        this.ui = ui;
    }

    /**
     * Attempts to load a previous save file.
     * Will create a blank save file if one does not exist, and load that.
     *
     * @return An ArrayList of Tasks from the save file.
     * @throws DukeException If the save file or any of the tasks are corrupt.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc;
        try {
            if (!Files.exists(saveDirectory)) {
                Files.createDirectories(saveDirectory);
            }
            if (!Files.exists(savePath)) {
                Files.createFile(savePath);
            }
            sc = new Scanner(savePath);
        } catch (IOException e) {
            throw DukeException.CORRUPT_SAVE;
        }

        assertSaveFileExists();

        while (sc.hasNextLine()) {
            try {
                Task newTask = getTaskFromSave(sc.nextLine());
                tasks.add(newTask);
            } catch (DukeException e) {
                ui.display(e);
            }
        }
        return tasks;
    }

    /**
     * Overwrites the previous save file with one storing the current TaskList.
     *
     * @param taskList The TaskList to be saved.
     */
    public void save(TaskList taskList) {
        assertSaveFileExists();

        try {
            Files.delete(savePath);
            Files.createFile(savePath);
            String saves = "";
            for (int i = 0; i < taskList.size(); i++) {
                saves += taskList.getTask(i).getSave() + "\n";
            }
            byte[] savesToBytes = saves.getBytes();
            Files.write(savePath, savesToBytes);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Parses a line of the save file and creates a task from it.
     *
     * @param nextTask A line from the save file.
     * @return The Task created from the save line.
     * @throws DukeException If the task is corrupt, ie. the format of the save line is invalid.
     */
    private Task getTaskFromSave(String nextTask) throws DukeException {
        char taskType = nextTask.charAt(0);
        boolean isDone;
        try {
            isDone = Integer.parseInt(nextTask.substring(1, 2)) == 1;
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw DukeException.CORRUPT_TASK;
        }

        int detailsIndex = nextTask.indexOf('|') + 1;
        String taskDetails = nextTask.substring(detailsIndex);

        if (taskType == 'T') {
            return new ToDo(taskDetails, isDone);
        }

        int breakIndex = taskDetails.indexOf('|');
        String taskDescription = taskDetails.substring(0, breakIndex);
        String taskDateAndTime = taskDetails.substring(breakIndex + 1);

        try {
            if (taskType == 'D') {
                return new Deadline(taskDescription, taskDateAndTime, isDone);
            } else if (taskType == 'E') {
                return new Event(taskDescription, taskDateAndTime, isDone);
            } else {
                throw DukeException.CORRUPT_TASK;
            }
        } catch (DukeException e) {
            throw DukeException.CORRUPT_TASK;
        }
    }

    public void assertSaveFileExists() {
        assert Files.exists(savePath) : "Save file should exist";
    }
}
