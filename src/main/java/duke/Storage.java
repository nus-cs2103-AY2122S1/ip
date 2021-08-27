package duke;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Deadline;
import duke.tasks.Event;

public class Storage {
    protected String pathName;
    protected File taskFile;

    public Storage(String pathName) {
        this.pathName = pathName;
        this.taskFile = new File(pathName);
    }

    public void saveTaskData(TaskList taskList) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathName));
        if (!taskList.isEmpty()) {
            writer.write(taskList.get(0).getFormattedData() + "\n");
        }

        for (int i = 1; i < taskList.size(); ++i) {
            writer.append(taskList.get(i).getFormattedData()).append("\n");
        }

        writer.close();
    }

    public List<Task> loadTaskData() throws IOException {
        this.taskFile.getParentFile().mkdirs();
        if (!this.taskFile.createNewFile()) {
            // Save file exists, so load it
            Scanner sc = new Scanner(this.taskFile);
            List<Task> taskData = new ArrayList<>();
            while (sc.hasNextLine()) {
                String task = sc.nextLine();

                String[] taskDetails = task.split("\\|");
                boolean taskDone = Objects.equals(taskDetails[1], "1");

                switch (taskDetails[0]) {
                    case "T":
                        taskData.add(new Todo(taskDetails[2], taskDone));
                        break;
                    case "D":
                        taskData.add(new Deadline(taskDetails[2], LocalDate.parse(taskDetails[3]), taskDone));
                        break;
                    case "E":
                        taskData.add(new Event(taskDetails[2], LocalDate.parse(taskDetails[3]), taskDone));
                        break;
                }
            }

            sc.close();
            return taskData;
        } else {
            // No save file exists, so a new one is created. Return empty list of tasks
            return new ArrayList<>();
        }
    }
}
