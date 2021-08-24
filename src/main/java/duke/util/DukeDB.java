package duke.util;

import duke.Duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class DukeDB {
    String databasePath;

    public DukeDB(String database) {
        if (database == null) {
            this.create()
                    .map((x) -> {
                        Duke.printMsg("DB cannot be found. New DB created successfully.");
                        this.databasePath = database;
                        return x;
                    })
                    .orElseGet(() -> {
                        Duke.printMsg("Unable to create the file. Check if the file already exists. Defaulting to " + "default database");
                        this.databasePath = "./data/dukeStore.txt";
                        return null;
                    });
        } else {
            File check = new File(database);
            if (check.exists()) {
                this.databasePath = database;
            } else {
                this.databasePath = "./data/dukeStore.txt";
            }
        }

    }

    @Deprecated
    public static void drop() {
        File file = new File("./data/dukeStore.txt");
        long size = file.getTotalSpace();
        boolean deleted = file.delete();
        if (deleted) {
            Duke.printMsg("Successfully dropped database of " + size + "B.");
        } else {
            Duke.printMsg("Error deleting file.");
        }
    }

    public void save(ArrayList<Task> arr) {
        try {
            FileWriter writer = new FileWriter(this.databasePath);
            for (Task item : arr) {
                writer.write(item.saveString());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            Duke.printMsg(e.toString() + " Error saving task");
        }
    }

    public Optional<ArrayList<Task>> load() {
        try {
            ArrayList<Task> arr = new ArrayList<>();
            File database = new File(databasePath);
            Scanner reader = new Scanner(database);
            while (reader.hasNextLine()) {
                String strTask = reader.nextLine();
                String[] splitTask = strTask.split("\\|");
                Optional<Task> task = this.readTask(splitTask);
                task.ifPresentOrElse(arr::add, () -> Duke.printMsg("Error loading task. Database is corrupt."));
            }
            return Optional.of(arr);
        } catch (FileNotFoundException e) {
            Duke.printMsg(e.toString());
        } catch (Error f) {
            Duke.printMsg("Problem reading the database format. Was the database modified externally?");
        }
        return Optional.empty();
    }

    public Optional<Boolean> create() {
        File file = new File("./data/dukeStore.txt");
        try {
            if (!file.exists()) {
                file.getParentFile()
                        .mkdirs();
                return Optional.of(file.createNewFile());
            } else {
                return Optional.empty();
            }
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    private Optional<Task> readTodo(String[] splitCommand) {
        if (splitCommand.length != 3) {
            return Optional.empty();
        } else {
            String description = splitCommand[1];
            boolean isDone = splitCommand[2].equals("1");
            return Optional.of(new Todo(description, isDone));
        }
    }

    private Optional<Task> readEvent(String[] splitCommand) {
        if (splitCommand.length != 4) {
            return Optional.empty();
        } else {
            String description = splitCommand[1];
            String at = splitCommand[2];
            boolean isDone = splitCommand[3].equals("1");
            return Optional.of(new Event(description, at, isDone));
        }
    }

    private Optional<Task> readDeadline(String[] splitCommand) {
        if (splitCommand.length != 4) {
            return Optional.empty();
        } else {
            String description = splitCommand[1];
            String by = splitCommand[2];
            boolean isDone = splitCommand[3].equals("1");

            try {
                LocalDateTime dateBy = Parser.parseDateTime(by).orElseThrow(() -> new DukeException("Unable to load " +
                        "date for task" + description +". Removing the task."));
                return Optional.of(new Deadline(description, dateBy, isDone));
            } catch (DukeException e) {
                Duke.printMsg(e.toString());
            }
            return Optional.empty();
        }
    }

    private Optional<Task> readTask(String[] splitCommand) {
        if (!(splitCommand.length > 2)) {
            return Optional.empty();
        } else {
            switch(splitCommand[0]) {
                case "T": {
                    return this.readTodo(splitCommand);
                }
                case "D": {
                    return this.readDeadline(splitCommand);
                }
                case "E": {
                    return this.readEvent(splitCommand);
                }
                default: {
                    return Optional.empty();
                }

            }
        }
    }
}
