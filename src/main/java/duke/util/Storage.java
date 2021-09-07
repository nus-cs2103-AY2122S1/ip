package duke.util;

import duke.exception.DukeException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;

import java.util.ArrayList;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;
            }

            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();
            while (line != null) {
                String[] input = line.split("\\|");
                assert input.length >= 3 : "loaded input should have at least 3 details";
                loadTasks(input, tasks);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new DukeException("Error loading tasks...");
        }
        return tasks;
    }

    private void loadTasks(String[] input, ArrayList<Task> tasks) {
        String taskType = input[0];
        String description = input[2];
        boolean isTaskDone = input[1].equals("1");

        switch (taskType) {
        case "T":
            Todo todo = new Todo(description);
            setDoneAndAddToList(tasks, todo, isTaskDone);
            break;
        case "E":
            LocalDate at = LocalDate.parse(input[3]);
            Event event = new Event(description, at);
            setDoneAndAddToList(tasks, event, isTaskDone);
            break;
        case "D":
            LocalDate by = LocalDate.parse(input[3]);
            Deadline deadline = new Deadline(description, by);
            setDoneAndAddToList(tasks, deadline, isTaskDone);
            break;
        default:
            assert false : taskType;
        }
    }

    private void setDoneAndAddToList(ArrayList<Task> tasks, Task task, boolean isTaskDone) {
        if (isTaskDone) {
            task.setDone();
        }
        tasks.add(task);
    }

    public void save(TaskList taskList) throws DukeException {
        File file = new File(filePath);
        try {
            FileWriter writer = new FileWriter(file);
            taskList.saveTasksInStorage(writer);
            writer.close();
        } catch (IOException e) {
           throw new DukeException("Error writing file");
        }
    }

}
