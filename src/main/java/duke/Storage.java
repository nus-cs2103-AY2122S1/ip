package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private Path saveFile;
    private Ui ui;

    public Storage(Path saveFile, Ui ui) {
        this.saveFile = saveFile;
        this.ui = ui;
    }

    public void initialiseTasks(ArrayList<Task> tasks) {
        try {
            File sf = new File(String.valueOf(saveFile));
            if (Files.exists(saveFile)) {
                Scanner taskReader = new Scanner(sf);
                while (taskReader.hasNextLine()) {
                    String taskln = taskReader.nextLine();
                    String[] taskComps = taskln.split("\\|");
                    if (taskComps[0].equals("T")) {
                        tasks.add(new Todo(taskComps[2], Boolean.parseBoolean(taskComps[1])));
                    } else if (taskComps[0]. equals("D")) {
                        tasks.add(new Deadline(taskComps[2], taskComps[3], Boolean.parseBoolean(taskComps[1])));
                    } else if (taskComps[0].equals("E")) {
                        tasks.add(new Event(taskComps[2], taskComps[3], Boolean.parseBoolean(taskComps[1])));
                    } else {
                        ui.toScreen("Tasks could not be loaded.");
                    }
                }
                taskReader.close();
            } else {
            }
        } catch (IOException | DukeException e) {
            ui.toScreen("File could not be found.");
            try {
                File sf = new File(String.valueOf(saveFile));
                sf.createNewFile();
            } catch (IOException ioException) {
                ui.toScreen("New file could not be created");
            }
        }
    }

    public void writeTask(Task t) {
        try {
            FileWriter taskWriter = new FileWriter(String.valueOf(saveFile), true);
            taskWriter.write(t.saveTask());
            taskWriter.close();
        } catch (IOException e) {
            ui.toScreen("Tasks could not be added to file.");
        }
    }

    public void updateFile(ArrayList<Task> task) {
        try {
            new PrintWriter(String.valueOf(saveFile)).close();
            for (Task t : task) {
                writeTask(t);
            }
        } catch (IOException e) {
            ui.toScreen("File could not be updated.");
        }
    }

    public void clearFile() {
        try {
            new PrintWriter(String.valueOf(saveFile)).close();
        } catch (IOException e) {
            ui.toScreen("File could not be cleared.");
        }
    }

}
