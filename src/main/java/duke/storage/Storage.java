package duke.storage;

import duke.exceptions.DukeException;
import duke.task.*;
import duke.parser.DateTimeParser;
import duke.tasklist.TaskList;

import java.io.*;
import java.util.*;

public class Storage {
    private String filePath;
    private String folderPath;

    public Storage(String filePath, String folderPath) {
         this.filePath = filePath;
        this.folderPath = folderPath;
    }

    public void readTasks(TaskList taskList) throws IOException {
        try {
            Task task;
            File fol = new File(folderPath);
            fol.mkdir();
            File file = new File(filePath);
            file.createNewFile();

            Scanner sc = new Scanner(file);
            if (file.exists()) {
                while (sc.hasNext()) {
                    String inp = sc.nextLine();
                    task = stringToTask(inp);
                    if (!taskList.containsTask(task)) {
                        taskList.add(task);
                    }
                }
            }
        } catch (IOException ex) {
            throw new DukeException("The storage file could not be created");
        }
    }

    public void saveTasks(TaskList taskList) {
        try {
            FileWriter f = new FileWriter(filePath);
            for (int i = 0; i < taskList.size(); i++) {
                Task t = taskList.get(i);
                f.write(taskToString(t));
            }
            f.close();
        } catch (IOException ex) {
            throw new DukeException("The storage file could not be found");
        }
    }

    public Task stringToTask(String str) {
        String[] parsed = str.split("\\|");
        String taskType = parsed[0].trim();
        Task task;
        switch (taskType) {
            case "T":
                task = new ToDo(parsed[2].trim());
                if (parsed[1].trim().equals("1")) {
                    task.markAsDone();
                }
                break;
            case "D":
                task = new Deadline(parsed[2].trim(), DateTimeParser.readDate(parsed[3].trim()));
                if (parsed[1].trim().equals("1")) {
                    task.markAsDone();
                }
                break;
            case "E":
                task = new Event(parsed[2].trim(), DateTimeParser.readDateTime(parsed[3].trim()));
                if (parsed[1].trim().equals("1")) {
                    task.markAsDone();
                }
                break;

            default:
                task = new Task("");
        }
        return task;
    }

    public String taskToString(Task task) {
        String[] parsedTask = task.toString().split("\\s");
        String taskType = parsedTask[0];
        String str;

        switch (taskType) {
            case "[T]":
                str = "T" + " | " + task.getIntStatus() + " | " + task.getDescription() + "\n";
                break;
            case "[D]":
                str = "D" + " | " + task.getIntStatus() + " | " + task.getDescription() + " | " + task.getDateString() + "\n";
                break;
            case "[E]":
                str = "E" + " | " + task.getIntStatus() + " | " + task.getDescription() + " | " + task.getDateString() + "\n";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }


}