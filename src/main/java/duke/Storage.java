package duke;

import duke.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public void save(TaskList tasks) {
        try {
            File file = new File(filepath);

            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(filepath);
            String toSave = "";
            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.getTask(i);
                toSave = toSave.concat(currentTask.taskType()
                        + " | "
                        + currentTask.isDoneToInt()
                        + " | "
                        + currentTask.getTaskDetails() + "\n");
            }

            writer.write(toSave);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public TaskList load() {
        ArrayList<Task> loadedTaskList = new ArrayList<>();
        try {
            File file = new File(filepath);
            if (file.createNewFile()) {
                return new TaskList();
            }

            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String[] lineSplit = s.nextLine().split(" \\| ",5);
                if (lineSplit[0].equals("T")) {
                    loadedTaskList.add(new ToDo(lineSplit[2]));
                } else if (lineSplit[0].equals("D")) {
                    loadedTaskList.add(new Deadline(lineSplit[2], lineSplit[3], lineSplit[4]));
                } else if (lineSplit[0].equals("E")) {
                    loadedTaskList.add(new Event(lineSplit[2], lineSplit[3]));
                }

                if (lineSplit[1].equals("0")) {
                    loadedTaskList.get(loadedTaskList.size() - 1).markDone();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new TaskList(loadedTaskList);
    }
}
