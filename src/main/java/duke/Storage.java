package duke;

import duke.error.DukeException;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.TodoTask;
import duke.task.DeadlineTask;
import duke.task.EventTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String directoryPath;
    private String filePath;

    public Storage (String directoryPath, String filePath) {
        this.directoryPath = directoryPath;
        this.filePath = directoryPath.concat(filePath);
    }

    public ArrayList<Task> load() throws DukeException {
        File directory = new File(directoryPath);
        File dataFile = new File(filePath);

        ArrayList<Task> tasks = new ArrayList<>();

        try {
            if (!directory.exists()) {
                Files.createDirectories(Paths.get("data/"));
            }
            if (!dataFile.exists()) {
                    dataFile.createNewFile();
            }
        } catch (IOException e ) {
            throw new DukeException("OOPS!! Something went wrong");
        }

        try {
            Scanner f = new Scanner(dataFile);

            while (f.hasNext()) {
                String[] task = f.nextLine().split("/");

                String taskType = task[0];
                boolean isDone = (Integer.parseInt(task[1]) == 1);
                String description = task[2];
                String time = task.length == 4 ? task[3] : null;

                if (taskType.equals("T")) {
                    tasks.add(new TodoTask(description, isDone));
                } else if (taskType.equals("D")) {
                    tasks.add(new DeadlineTask(description, isDone, time));
                } else {
                    tasks.add(new EventTask(description, isDone, time));
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("OOPS!! Something went wrong");
        }
        return tasks;
    }

    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        ArrayList<Task> listOfTasks = tasks.getListOfTasks();

        for (int i = 0; i < listOfTasks.size(); i++) {
            Task t = listOfTasks.get(i);
            String type = t.getType();
            String description = t.getDescription();
            String isDone = t.getStatusIcon().equals("X") ? "1" : "0";

            String line = type + "/" + isDone + "/" + description;
            if (type.equals("D")) {
                DeadlineTask dt = (DeadlineTask) t;
                line += "/" + dt.getTime();
            } else if (type.equals("E")) {
                EventTask et = (EventTask) t;
                line += "/" + et.getTime();
            }
            line += "\n";
            fw.write(line);
        }

        fw.close();
    }
}
