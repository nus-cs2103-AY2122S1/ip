package gnosis.database;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import gnosis.model.Deadline;
import gnosis.model.Event;
import gnosis.model.Task;
import gnosis.model.TaskType;
import gnosis.model.Todo;


public class TaskDbManager extends DatabaseManager {

    public TaskDbManager(String fileName) {
        super(fileName);
    }

    /**
     * Load Tasks from database.
     * @return Loaded tasks from database.
     * */
    public List<Task> loadTasks() {
        if (this.isDataFileAvail()) {
            return this.getTasksFromFile();
        } else {
            this.createDataFolder();
            return new ArrayList<>();
        }
    }

    /**
     * Writes Tasks into file from input.
     *
     * @param tasks List of tasks to write to file.
     */
    public void writeTasksToFile(List<Task> tasks) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(this.getFilePath()));
            writer.write("Task Type,is task completed?,Task name,DateTime");
            writer.newLine();

            for (Task record: tasks) {
                int taskDone = record.isTaskDone() == 'X' ? 1 : 0;

                String oneLine = record.getTaskType() + DatabaseManager.DELIMITER
                        + taskDone + DatabaseManager.DELIMITER
                        + record.getTaskName() + DatabaseManager.DELIMITER
                        + record.getDatetime().toString();

                writer.write(oneLine);
                writer.newLine();
            }

            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads Tasks into file system.
     * @return list of tasks from file system.
     */
    private List<Task> getTasksFromFile() {

        List<Task> tasks = new ArrayList<>();
        try {
            tasks = Files.newBufferedReader(Paths.get(this.getFilePath()))
                    .lines()
                    .skip(1)
                    .map(TaskDbManager::parseTask)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    private static Task parseTask(String s) {
        String[] tokens = s.split(DELIMITER);

        TaskType taskType = TaskType.getTaskType(tokens[0].charAt(0));
        boolean isTaskDone = tokens[1].equalsIgnoreCase("1");
        String taskName = tokens[2];

        if (taskType == TaskType.TODO) {
            return new Todo(taskName, isTaskDone);
        } else if (taskType == TaskType.EVENT) {
            String schedule = tokens[3];
            LocalDateTime dt = LocalDateTime.parse(schedule);

            return new Event(taskName, isTaskDone, dt);
        } else if (taskType == TaskType.DEADLINE) {
            String deadline = tokens[3];
            LocalDateTime dt = LocalDateTime.parse(deadline);

            return new Deadline(taskName, isTaskDone, dt);
        }
        return new Task(taskName, taskType, null, isTaskDone);
    }
}
