package duke.util;

import duke.exception.DukeException;
import duke.task.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return taskList;
            }
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();
            while (line != null) {
                String[] input = line.split("\\|");
                String taskType = input[0];
                String description = input[2];
                boolean isTaskDone = input[1].equals("1");
                switch (taskType) {
                    case "T":
                        loadTodoToList(taskList, description, isTaskDone);
                        break;
                    case "E":
                        LocalDate at = LocalDate.parse(input[3]);
                        loadEventToList(taskList, description, at, isTaskDone);
                        break;
                    case "D":
                        LocalDate by = LocalDate.parse(input[3]);
                        loadDeadlineToList(taskList, description, by, isTaskDone);
                        break;
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new DukeException("Error loading tasks...");
        }
        return taskList;
    }

    public void loadTodoToList(ArrayList<Task> taskList, String description,
                               boolean isTaskDone) throws DukeException {
        Todo todo = new Todo(description);
        if (isTaskDone) {
            todo.setDone();
        }
        taskList.add(todo);
    }

    public void loadEventToList(ArrayList<Task> taskList, String description, LocalDate at,
                                boolean isTaskDone) throws DukeException {
        Event event = new Event(description, at);
        if (isTaskDone) {
            event.setDone();
        }
        taskList.add(event);
    }

    public void loadDeadlineToList(ArrayList<Task> taskList, String description, LocalDate by,
                                   boolean isTaskDone) throws DukeException {
        Deadline deadline = new Deadline(description, by);
        if (isTaskDone) {
            deadline.setDone();
        }
        taskList.add(deadline);
    }

    public void saveTaskList(TaskList taskList) throws DukeException {
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
