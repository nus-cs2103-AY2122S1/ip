package shybot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import shybot.exception.ShyBotException;
import shybot.exception.ShyBotIoException;
import shybot.exception.ShyBotParseException;
import shybot.task.Deadline;
import shybot.task.Event;
import shybot.task.Task;
import shybot.task.TaskList;
import shybot.task.Todo;

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
     * @throws ShyBotIoException If the named file exists but is a directory rather than a regular file,
     *                           does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public void save(TaskList tasks) throws ShyBotIoException {
        try (FileWriter fw = new FileWriter(filePath)) {
            StringBuilder dataString = new StringBuilder();
            for (Task task : tasks) {
                dataString.append(task.toDataString()).append("\n");
            }
            fw.write(dataString.toString());
        } catch (IOException e) {
            throw new ShyBotIoException(
                "☹ OOPS!!! I failed to save your task. Please reboot me and try again.");
        }
    }

    /**
     * Saves the specified task to the file.
     *
     * @param task Task to be saved to the file.
     * @throws ShyBotIoException If the named file exists but is a directory rather than a regular file,
     *                           does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public void save(Task task) throws ShyBotIoException {
        // TODO: raise assertion error if not added to list yet
        assert !task.toDataString().isEmpty() : "`toDataString()` of task may not return empty string";
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(task.toDataString() + "\n");
        } catch (IOException e) {
            throw new ShyBotIoException(
                "☹ OOPS!!! I failed to save your task. Please reboot me and try again.");
        }
    }

    private void validateRow(String[] row, int minLength) throws ShyBotParseException {
        if (row.length < minLength) {
            throw new ShyBotParseException();
        }
    }

    private String[] getTagsFromRow(String[] row, int tagsIndex) {
        String[] tags = row.length > tagsIndex ? row[tagsIndex].split(" ") : new String[0];
        return tags;
    }

    /**
     * Loads task from the file.
     *
     * @return Loaded task list.
     * @throws IOException          If an I/O error occurred.
     * @throws ShyBotParseException If parse error occurred.
     */
    public ArrayList<Task> load() throws IOException, ShyBotParseException {
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
                    String[] tags = getTagsFromRow(row, 3);
                    Todo todo = new Todo(row[2], tags);
                    list.add(todo);
                    if (isDone) {
                        todo.markAsDone();
                    }
                    break;
                }
                case "E": {
                    validateRow(row, 4);
                    String[] tags = getTagsFromRow(row, 4);
                    Event event = new Event(row[2], row[3], tags);
                    list.add(event);
                    if (isDone) {
                        event.markAsDone();
                    }
                    break;
                }
                case "D": {
                    validateRow(row, 4);
                    String[] tags = getTagsFromRow(row, 4);
                    Deadline deadline = new Deadline(row[2], row[3], tags);
                    list.add(deadline);
                    if (isDone) {
                        deadline.markAsDone();
                    }
                    break;
                }
                default: {
                    throw new ShyBotParseException();
                }
                }
            } catch (ShyBotException e) {
                throw new ShyBotParseException();
            }
        }
        return list;
    }
}
