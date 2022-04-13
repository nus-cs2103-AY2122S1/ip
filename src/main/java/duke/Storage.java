package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.utils.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Scanner;

public class Storage {
    public final static String TASK = "T";
    public final static String DEADLINE = "D";
    public final static String EVENT = "E";
    public final static DukeException ERROR_DB = new DukeException("Error loading database.");
    private final RandomAccessFile raf;
    private final File txt;

    /**
     * Constructor for storage with given TaskList at default location "duke.txt".
     * @param db TaskList database.
     * @throws DukeException
     */
    public Storage(TaskList db) throws DukeException {
        this(db, "duke.txt");
    }

    /**
     * Constructor for storage with given TaskList and location.
     * @param db TaskList database.
     * @param filename database location.
     * @throws DukeException
     */
    public Storage(TaskList db, String filename) throws DukeException {
        try {
            txt = new File(filename);
            raf = new RandomAccessFile(txt, "rwd");
            assert txt != null && raf != null;
            while (raf.getFilePointer() < raf.length()) {
                Task t = parse(raf.readLine());
                if (t != null) {
                    db.add(t);
                }
            }
            update(db);
        } catch (IOException e) {
            throw ERROR_DB;
        }
    }

    /**
     * Converts database line to a command in Duke.
     * @param line database line.
     * @return command in Duke.
     * @throws DukeException
     */
    public Task parse(String line) throws DukeException {
        try {
            String[] args = line.split("( \\| )", 3);
            if (args.length < 3) {
                return null;
            }
            boolean done = Integer.parseInt(args[1]) == 1;
            switch (args[0]) {
            case TASK:
                return new Todo(args[2], done);
            case DEADLINE:
                return new Deadline(args[2], done);
            case EVENT:
                return new Event(args[2], done);
            default:
                throw ERROR_DB;
            }
        } catch (IndexOutOfBoundsException | DukeException e) {
            throw ERROR_DB;
        }
    }

    /**
     * Updates the database by overwriting the database with the current tasks in TaskList.
     * @param database TaskList database.
     * @throws DukeException
     */
    public void update(TaskList database) throws DukeException {
        try {
            raf.seek(0);
            raf.setLength(0);
            List<Task> databaseList = database.getList();
            for (Task a : databaseList) {
                raf.writeBytes(a.toDatabaseFormat());
                raf.writeBytes(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new DukeException("Error in writing to duke2.txt.");
        }
    }

    /**
     * Updates the database by adding to the database this task.
     * @param task task to add.
     * @throws DukeException
     */
    public void update(Task task) throws DukeException {
        try {
            raf.writeBytes(task.toDatabaseFormat());
            raf.writeBytes(System.lineSeparator());
        } catch (IOException e) {
            throw ERROR_DB;
        }
    }

    public void close() throws DukeException {
        try {
            raf.writeBytes(System.lineSeparator());
            raf.close();
        } catch (IOException e) {
            throw ERROR_DB;
        }
    }

    /**
     * Clears the database.
     * @throws DukeException
     */
    public void purge() throws DukeException {
        try {
            raf.setLength(0);
        } catch (IOException e) {
            throw ERROR_DB;
        }
    }

    @Override
    public String toString() {
        try {
            StringBuilder sb = new StringBuilder();
            Scanner sc = new Scanner(txt);
            while (sc.hasNext()) {
                sb.append(sc.nextLine());
                sb.append("\n");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            return "Error: duke.txt not found";
        }
    }
}
