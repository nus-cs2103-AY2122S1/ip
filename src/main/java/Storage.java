import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws DukeException {
        try {
            ArrayList<Task> res = new ArrayList<>();
            File taskFile = new File(this.filePath);
            if (!taskFile.createNewFile()) {
                Scanner fileReader = new Scanner(taskFile);
                while (fileReader.hasNextLine()) {
                    String data = fileReader.nextLine();
                    res.add(dataToTask(data));
                }
            }
            return res;
        } catch (IOException | DukeException e) {
            throw new DukeException("Unable to load tasks from file.");
        }
    }

    private static Task dataToTask(String str) throws DukeException {
        String[] taskArr = str.split(",");
        String taskType = taskArr[0];
        boolean taskDone = taskArr[1].equals("1");
        String taskDescription = taskArr[2];
        String taskDate = "";
        if (taskArr.length > 3) {
            taskDate = taskArr[3];
        }
        Task res = null;
        switch (taskType) {
            case("T"):
                res = new Todo(taskDescription, taskDone);
                break;
            case("D"):
                res = new Deadline(taskDescription, taskDate, taskDone);
                break;
            case("E"):
                res = new Event(taskDescription, taskDate, taskDone);
                break;
        }
        return res;
    }

    public void saveTaskToFile(Task task) {
        saveStringToFile(task.toFileData() + "\n");
    }

    private void saveStringToFile(String str) {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath, true);
            fileWriter.write(str);
            fileWriter.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred with file handling.");
        }
    }

    public void deleteTaskFromFile(int taskIndex, ArrayList<Task> taskList) {
        updateTaskFromFile(taskIndex, true, taskList);
    }

    public void editTaskFromFile(int taskIndex, ArrayList<Task> taskList) {
        updateTaskFromFile(taskIndex, false, taskList);
    }

    private void updateTaskFromFile(int taskIndex, boolean delete, ArrayList<Task> taskList) {
        try {
            StringBuilder newTasks = new StringBuilder();
            File taskFile = new File("./data/tasks.txt");
            Scanner fileReader = new Scanner(taskFile);
            int index = 0;
            while (fileReader.hasNextLine()) {
                if (index != taskIndex) {
                    newTasks.append(fileReader.nextLine()).append("\n");
                } else if (!delete) {
                    newTasks.append(taskList.get(taskIndex).toFileData()).append("\n");
                    fileReader.nextLine();
                } else {
                    fileReader.nextLine();
                }
                index += 1;
            }
            FileWriter fileWriter = new FileWriter("./data/tasks.txt", false);
            fileWriter.write(String.valueOf(newTasks));
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred with file handling.");
        }
    }

}
