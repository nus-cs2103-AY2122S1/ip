import exceptions.DukeException;
import exceptions.DukeParseException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class TaskList extends ArrayList<Task> {

    public void save() throws IOException {
        // update entire list to db
        FileWriter fw = new FileWriter(Duke.dataPath);
        StringBuilder dataString = new StringBuilder();
        for (Task task : this) {
            dataString.append(task.toDataString()).append("\n");
        }
        // batch write into the data file
        fw.write(dataString.toString());
        fw.close();
    }

    public void refreshFromDB(boolean shouldFailSilently) throws IOException, DukeException {

        String IOErrorMessage = "☹ OOPS!!! Seems like your data is corrupted. " +
                "Please make sure you data file has the correct format.";

        // refresh from db
        this.clear();

        File f = new File(Duke.dataPath);
        f.getParentFile().mkdirs();
        f.createNewFile();

        // parse
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String[] row = sc.nextLine().split(" \\| ");
            if (row.length < 3) {
                if (!shouldFailSilently) {
                    throw new IOException(IOErrorMessage);
                }
                continue;
            }
            boolean isDone = row[1].equals("1");
            try {
                switch (row[0]) {
                    case "T": {
                        Todo todo = new Todo(row[2]);
                        super.add(todo);
                        if (isDone) {
                            todo.markAsDone();
                        }
                        break;
                    }
                    case "E": {
                        if (row.length < 4) {
                            if (!shouldFailSilently) {
                                throw new IOException(IOErrorMessage);
                            }
                            continue;
                        }
                        Event event = new Event(row[2], row[3]);
                        super.add(event);
                        if (isDone) {
                            event.markAsDone();
                        }
                        break;
                    }
                    case "D": {
                        if (row.length < 4) {
                            if (!shouldFailSilently) {
                                throw new IOException(IOErrorMessage);
                            }
                            continue;
                        }
                        Deadline deadline = new Deadline(row[2], row[3]);
                        super.add(deadline);
                        if (isDone) {
                            deadline.markAsDone();
                        }
                        break;
                    }
                    default: {
                        if (!shouldFailSilently) {
                            // don't handle this, let it bubble up the stack and end the program
                            throw new IOException(IOErrorMessage);
                        }
                    }
                }
            } catch (DukeException e) {
                if (!shouldFailSilently) {
                    throw new DukeParseException(e.getMessage());
                }
            }
        }
    }

    // all modifying operations of task list will modify the db as instructed
    @Override
    public boolean add(Task task) {
        boolean result = super.add(task);
        try {
            task.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Task remove(int index) {
        // TODO: param to allow client to decide whether to refresh db
        // TODO: find and remove from file O(N)
        Task task = super.remove(index);
        try {
            this.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return task;
    }

    public void markAsDone(Task task) {
        task.markAsDone();
        try {
            this.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
