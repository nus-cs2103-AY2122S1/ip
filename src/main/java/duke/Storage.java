package duke;

import duke.task.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private Path saveDirectory;
    private Path savePath;
    private Ui ui;

    public Storage(Ui ui, Path saveDirectory) {
        this.ui = ui;
        this.saveDirectory = saveDirectory;
        this.savePath = Paths.get(saveDirectory.toString(), "duke.txt");
    }

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
            throw new DukeException(DukeException.CORRUPT_SAVE);
        }

        while(sc.hasNextLine()) {
            try {
                Task newTask = getTaskFromSave(sc.nextLine());
                tasks.add(newTask);
            } catch (DukeException e) {
                ui.display(e);
            }
        }
        return tasks;
    }

    public void save(TaskList taskList) {
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

    private Task getTaskFromSave(String nextTask) throws DukeException {
        char taskType = nextTask.charAt(0);
        boolean isDone;
        try {
            isDone = Integer.parseInt(nextTask.substring(1, 2)) == 1;
        } catch (NumberFormatException e) {
            throw new DukeException(DukeException.CORRUPT_TASK);
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
                throw new DukeException(DukeException.CORRUPT_TASK);
            }
        } catch (DukeException e) {
            throw new DukeException(DukeException.CORRUPT_TASK);
        }
    }
}
