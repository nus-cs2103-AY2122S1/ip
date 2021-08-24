import exceptions.DukeException;
import task.*;
import java.io.*;
import java.util.*;

public class Storage {

    public static void readTasks(ArrayList<Task> taskList) throws IOException {
        try {
            Task task;
            File fol = new File(Constants.FOLPATH);
            fol.mkdir();
            File file = new File(Constants.FILEPATH);
            file.createNewFile();

            Scanner sc = new Scanner(file);
            if (file.exists()) {
                while (sc.hasNext()) {
                    String inp = sc.nextLine();
                    task = stringToTask(inp);
                    if (!Duke.containsTask(task, taskList)) {
                        taskList.add(task);
                    }
                }
            }
        } catch (IOException ex) {
            throw new DukeException("The storage file could not be created");
        }
    }

    public static void saveTasks(ArrayList<Task> taskList) {
        try {
            FileWriter f = new FileWriter(Constants.FILEPATH);
            for (Task t: taskList) {
                f.write(taskToString(t));
            }
            f.close();
        } catch (IOException ex) {
            throw new DukeException("The storage file could not be found");
        }
    }

    public static Task stringToTask(String str) {
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

    public static String taskToString(Task task) {
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