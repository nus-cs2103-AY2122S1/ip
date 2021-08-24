import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private String filePath;

    FileManager(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load data from the hard disks.
     */
    public TaskList loadData(TaskList taskList) {
        try {
            File file = new File(filePath);
            // Create a new file if it does not already exist
            file.createNewFile();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String task;
            while((task = reader.readLine()) != null) {
                char type = task.charAt(1);
                boolean isCompleted = task.charAt(4) == 'X';

                String description = parseDescription(task);
                Task newTask;
                String time;
                switch (type) {
                case 'T':
                    newTask = new ToDo(description);
                    taskList = loadTasks(newTask, taskList, isCompleted);
                    break;
                case 'D':
                    time = parseTime(task, "by: ");
                    newTask = new Deadline(description, time);
                    taskList = loadTasks(newTask, taskList, isCompleted);
                    break;
                case 'E':
                    time = parseTime(task, "at: ");
                    newTask = new Event(description, time);
                    taskList = loadTasks(newTask, taskList, isCompleted);
                    break;
                default:
                    throw new DukeException("Invalid task.");
                }
                System.out.println(task);
            }
            reader.close();
        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
        } finally {
            return taskList;
        }
    }

    private TaskList loadTasks(Task task, TaskList taskList, boolean isCompleted) {
        if (isCompleted) {
            task.markAsCompleted();
        }
        return taskList.add(task);
    }

    private String parseTime(String task, String command) throws DukeException {
        int timeIndex = task.indexOf(command);
        if (timeIndex < 0) {
            throw new DukeException("Invalid Task");
        }
        String time = task.substring(timeIndex + command.length(),
                task.indexOf(')'));
        return time;
    }

    private String parseDescription(String task) throws DukeException {
        int startOfDescription = task.lastIndexOf(']') + 2;
        if (startOfDescription < 0) {
            throw new DukeException("Description cannot be empty.");
        }
        String description = task.substring(startOfDescription);
        int timeIndex = description.indexOf("  (");
        if (timeIndex >= 0) {
            description = description.substring(0, timeIndex);
        }
        return description;
    }

    public void addTaskToFile(Task task) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            writer.write(task.toString() + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteTaskFromFile(TaskList taskList) {
        try {
            File file = new File(filePath);
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(taskList.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void markTaskAsCompleted(String task, String toUpdate) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            String oldContent = "";
            while((line = reader.readLine()) != null) {
                oldContent += line + System.lineSeparator();
            }

            // Error here : Not replacing content
            String newContent = oldContent.replace(toUpdate, task);
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(newContent);
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
