import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    final private String filename;
    private File taskList;

    public FileManager(String filename) {
        this.filename = filename;
        this.taskList = new File(filename);
    }

    public ArrayList<Task> getTaskList() {
        try {
            if (taskList.createNewFile()) {
                return new ArrayList<>();
            } else {
                Scanner fileReader = new Scanner(taskList);
                ArrayList<Task> tasks = new ArrayList<>();
                while (fileReader.hasNextLine()) {
                    try {
                        String nextLine = fileReader.nextLine();
                        String[] splitString = nextLine.split(Task.sep);
                        Task newTask = Task.makeTask(splitString[0], splitString[1]);
                        if (splitString[2].equals("1")) {
                            newTask.markDone(false);
                        }
                        tasks.add(newTask);
                    } catch (DukeException e) {
                        System.out.println("Invalid input in file");
                    }
                }
                return tasks;
            }
        } catch (IOException e) {
            System.out.println("Error occured initalising file");
            e.printStackTrace();
            return null;
        }
    }

    public void updateTaskList(ArrayList<? extends Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(this.taskList);
            fileWriter.write("");
            fileWriter.close();
            FileWriter newfileWriter = new FileWriter(this.taskList, true);
            for (Task task: tasks) {
                newfileWriter.write(task.typeString() + "\n");
            }
            newfileWriter.close();
        } catch (IOException e) {
            System.out.println("Error in updating task list");
            e.printStackTrace();
        }
    }
}
