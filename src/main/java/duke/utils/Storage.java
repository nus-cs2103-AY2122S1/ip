package duke.utils;

import duke.exceptions.InvalidTaskIdException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * The Storage class handles the loading and saving of TaskList
 * information to the user's hard disk with a text file.
 */
public class Storage {
    private Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a TaskList object containing information about tasks
     * that were saved in the user's hard disk.
     *
     * @return TaskList object containing the user's saved tasks.
     * @throws IOException If any problem occurs when reading the text file from the hard disk.
     */
    public TaskList loadData() throws IOException {
        List<String> contents = Files.readAllLines(filePath);
        TaskList taskList = new TaskList();
        for (String content : contents) {
            String[] taskDetails = content.split("=");

            if (taskDetails[0].equals("ToDo")) {
                ToDo todo = new ToDo(taskDetails[2]);
                if (taskDetails[1].equals("true")) {
                    todo.markAsCompleted();
                }
                taskList.add(todo);
            } else if (taskDetails[0].equals("Deadline")) {
                Deadline deadline = new Deadline(taskDetails[2], taskDetails[3]);
                if (taskDetails[1].equals("true")) {
                    deadline.markAsCompleted();
                }
                taskList.add(deadline);
            } else {
                Event event = new Event(taskDetails[2], taskDetails[3]);
                if (taskDetails[1].equals("true")) {
                    event.markAsCompleted();
                }
                taskList.add(event);
            }
        }
        return taskList;
    }

    /**
     * Saves information from a TaskList object, by writing to a text file
     * in the user's hard disk.
     *
     * @param taskList TaskList object containing the tasks to be saved.
     * @throws IOException If any problem occurs when writing the text file to the hard disk.
     */
    public void saveData(TaskList taskList) throws IOException {
        List<String> lines = new ArrayList<>();
        Ui ui = new Ui();
        for (int i = 0; i < taskList.getSize(); i++) {
            try {
                Task task = taskList.get(i);
                if (task instanceof ToDo) {
                    String line = String.format("ToDo=%s=%s",
                            String.valueOf(task.getCompleted()), task.getTaskName());
                    lines.add(line);
                } else if (task instanceof Event) {
                    String line = String.format("Event=%s=%s=%s", String.valueOf(task.getCompleted()),
                            task.getTaskName(), ((Event) task).getTimePeriod());
                    lines.add(line);
                } else {
                    String line = String.format("Deadline=%s=%s=%s",
                            String.valueOf(task.getCompleted()), task.getTaskName(),
                            ((Deadline) task).getDeadline());
                    lines.add(line);
                }
            } catch (InvalidTaskIdException e) {
                ui.displayError(e);
            }
        }
        Files.write(filePath, lines, StandardCharsets.UTF_8);
    }
}
