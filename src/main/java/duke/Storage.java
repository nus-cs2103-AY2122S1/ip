package duke;

import duke.exception.DukeException;
import duke.exception.DukeParseException;
import duke.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

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
     * @throws IOException If the named file exists but is a directory rather than a regular file,
     *                     does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public void save(TaskList tasks) throws IOException {
        // update entire list to db
        FileWriter fw = new FileWriter(filePath);
        StringBuilder dataString = new StringBuilder();
        for (Task task : tasks) {
            dataString.append(task.toDataString()).append("\n");
        }

        // batch write into the data file
        fw.write(dataString.toString());
        fw.close();
    }

    /**
     * Saves the specified task to the file.
     *
     * @param task Task to be saved to the file.
     * @throws IOException If the named file exists but is a directory rather than a regular file,
     *                     does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public void save(Task task) throws IOException {
        // TODO: raise assertion error if not added to list yet
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(task.toDataString() + "\n");
        fw.close();
    }

    /**
     * Loads task from the file.
     *
     * @param shouldFailSilently Set to true if you want to silent the parse error in each row.
     * @return Loaded task list.
     * @throws IOException        If an I/O error occurred.
     * @throws DukeParseException If parse error occurred.
     */
    public ArrayList<Task> load(boolean shouldFailSilently) throws IOException, DukeParseException {
        File f = new File(filePath);
        f.getParentFile().mkdirs();
        f.createNewFile();

        ArrayList<Task> list = new ArrayList<>();
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String[] row = sc.nextLine().split(" \\| ");
            if (row.length < 3) {
                if (!shouldFailSilently) {
                    throw new DukeParseException();
                }
                continue;
            }
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
                        if (row.length < 4) {
                            if (!shouldFailSilently) {
                                throw new DukeParseException();
                            }
                            continue;
                        }
                        Event event = new Event(row[2], row[3]);
                        list.add(event);
                        if (isDone) {
                            event.markAsDone();
                        }
                        break;
                    }
                    case "D": {
                        if (row.length < 4) {
                            if (!shouldFailSilently) {
                                throw new DukeParseException();
                            }
                            continue;
                        }
                        Deadline deadline = new Deadline(row[2], row[3]);
                        list.add(deadline);
                        if (isDone) {
                            deadline.markAsDone();
                        }
                        break;
                    }
                    default: {
                        if (!shouldFailSilently) {
                            // don't handle this, let it bubble up the stack and end the program
                            throw new DukeParseException();
                        }
                    }
                }
            } catch (DukeException e) {
                if (!shouldFailSilently) {
                    throw new DukeParseException();
                }
            }
        }
        return list;
    }
}
