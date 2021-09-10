package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.exception.DukeIoException;
import duke.exception.DukeParseException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Storage class deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage with the specified filePath.
     *
     * @param filePath Path of the file to store the task list.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes the specified tasks to the file in the filePath.
     *
     * @param tasks Tasks to be written in the file.
     * @throws DukeIoException If the named file exists but is a directory rather than a regular file,
     *                         does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public void save(TaskList tasks) throws DukeIoException {


        try (FileWriter fw = new FileWriter(filePath)) {
            StringBuilder dataString = new StringBuilder();
            for (Task task : tasks) {
                dataString.append(task.toDataString()).append("\n");
            }
            fw.write(dataString.toString());
        } catch (IOException e) {
            throw new DukeIoException(
                "☹ OOPS!!! I failed to save your task. Please reboot me and try again.");
        }
    }

    /**
     * Saves the specified task to the file.
     *
     * @param task Task to be saved to the file.
     * @throws DukeIoException If the named file exists but is a directory rather than a regular file,
     *                         does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public void save(Task task) throws DukeIoException {
        // TODO: raise assertion error if not added to list yet
        assert !task.toDataString().isEmpty() : "`toDataString()` of task may not return empty string";
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(task.toDataString() + "\n");
        } catch (IOException e) {
            throw new DukeIoException(
                "☹ OOPS!!! I failed to save your task. Please reboot me and try again.");
        }
    }

    private void validateRow(String[] row, int minLength) throws DukeParseException {
        if (row.length < 3) {
            throw new DukeParseException();
        }
    }

    /**
     * Loads task from the file.
     *
     * @return Loaded task list.
     * @throws IOException        If an I/O error occurred.
     * @throws DukeParseException If parse error occurred.
     */
    public ArrayList<Task> load() throws IOException, DukeParseException {
        File f = new File(filePath);
        f.getParentFile().mkdirs();
        f.createNewFile();

        ArrayList<Task> list = new ArrayList<>();
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String[] row = sc.nextLine().split(" \\| ");
            validateRow(row, 3);
            boolean isDone = row[1].equals("1");
            try {
                switch (row[0]) {
                case "T": {
                    Todo todo = new Todo(row[2]);
                    list.add(todo);
                    if (isDone) {
                        todo.markAsDone();
                    }
                    break;
                }
                case "E": {
                    validateRow(row, 4);
                    Event event = new Event(row[2], row[3]);
                    list.add(event);
                    if (isDone) {
                        event.markAsDone();
                    }
                    break;
                }
                case "D": {
                    validateRow(row, 4);
                    Deadline deadline = new Deadline(row[2], row[3]);
                    list.add(deadline);
                    if (isDone) {
                        deadline.markAsDone();
                    }
                    break;
                }
                default: {
                    throw new DukeParseException();
                }
                }
            } catch (DukeException e) {
                throw new DukeParseException();
            }
        }
        return list;
    }
}
