package duke;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Storage {
    private String filePath;
    private List<Task> tasks;
    private File parentDir;
    private File file;

    /**
     * Creates a Storage Object with the associated file path.
     *
     * @param filePath The file path to the text file containing the stored tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.tasks = new ArrayList<>();
        this.parentDir = new File(filePath.substring(0, filePath.lastIndexOf('/')));
        this.file = new File(filePath);
    }

    /**
     * Loads the list of tasks if it exists.
     *
     * @return List of tasks.
     * @throws IOException
     */
    public List<Task> load() throws IOException {
        if (! parentDir.exists()) {
            parentDir.mkdirs();
            file.createNewFile();
        } else if (! file.isFile()) {
            file.createNewFile();
        } else {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                Task task = null; // possible NPE
                String[] taskComponents = s.nextLine().split("\\|");
                String taskType = taskComponents[0];
                boolean taskCompleted = Integer.parseInt(taskComponents[1]) == 1;
                String taskDescription = taskComponents[2];
                String taskTime = "";
                if (taskComponents.length > 3) {
                    taskTime = taskComponents[3];
                }
                switch (taskType){
                case "T":
                    task = new ToDo(taskDescription);
                    if (taskCompleted) task.setDone();
                    break;
                case "D":
                    task = new Deadline(taskDescription, taskTime);
                    if (taskCompleted) task.setDone();
                    break;
                case "E":
                    task = new Event(taskDescription, taskTime);
                    if (taskCompleted) task.setDone();
                }
                tasks.add(task);
            }
        }

        return tasks;
    }

    /**
     * Writes to the storage.
     *
     * @throws IOException
     */
    public void write() throws IOException {
        FileWriter fw = new FileWriter(file);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            char type = task.getTaskType();
            int doneBit = task.isDone() ? 1 : 0;
            String description = task.getDescription();
            String time = task.getTime();

            String s = type + "|" + doneBit + "|" + description +
                    (time.equals("") ? time : "|" + time);
            fw.write(s + "\n");
        }
        fw.close();
//        System.out.println("written to file successfully");
    }
}
