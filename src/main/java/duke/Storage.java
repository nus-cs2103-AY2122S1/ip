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
    public final static DukeException ERROR_DB = new DukeException("Error loading database.");
    private final RandomAccessFile raf;
    private final File txt;

    public Storage(TaskList db) throws DukeException {
        try {
            txt = new File("duke.txt");
            raf = new RandomAccessFile(txt, "rwd");
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

    public Task parse(String line) throws DukeException {
        try {
            String[] args = line.split("( \\| )", 3);
            if (args.length < 3) {
                return null;
            }
            boolean done = Integer.parseInt(args[1]) == 1;
            switch (args[0]) {
            case "T":
                return new Todo(args[2], done);
            case "D":
                return new Deadline(args[2], done);
            case "E":
                return new Event(args[2], done);
            default:
                throw ERROR_DB;
            }
        } catch (IndexOutOfBoundsException | DukeException e) {
            throw ERROR_DB;
        }
    }

    public void update(TaskList db) throws DukeException {
        try {
            raf.seek(0);
            raf.setLength(0);
            List<Task> dbList = db.getList();
            for (Task a : dbList) {
                raf.writeBytes(a.toDB());
                raf.writeBytes(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new DukeException("Error in writing to duke.txt.");
        }
    }

    public void update(Task task) throws DukeException {
        try {
            raf.writeBytes(task.toDB());
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
