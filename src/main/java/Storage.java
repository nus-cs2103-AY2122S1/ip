import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;
    private String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
        file = new File(fileName);
    }

    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            Scanner scan = new Scanner(this.file);

            while (scan.hasNext()) {
                Task newTask;

                String taskLine = scan.nextLine();

                String[] task = taskLine.split(" \\| ");
                String taskType = task[0];

                switch (taskType) {
                case ("T"):
                    newTask = new Todo(task[2]);
                    break;
                case ("D"):
                    newTask = new Deadline(task[2], task[3]);
                    break;
                case ("E"):
                    newTask = new Event(task[2], task[3]);
                    break;
                default:
                    newTask = new Todo(task[2]);
                }

                if (task[1].equals("X")) {
                    newTask.markDone();
                }

                taskList.add(newTask);
            }

            return taskList;

        } catch (FileNotFoundException e) {
                return taskList;
        }
    }

    public void save(ArrayList<Task> taskList){
        try {
            String fileTask;
            if (taskList.isEmpty()) {
                fileTask = "";
            } else {
                fileTask = taskList.get(0).toFileString();
            }

            for (int i = 1; i < taskList.size(); i++) {
                fileTask = fileTask + "\n" + taskList.get(i).toFileString();
            }

            FileWriter fileWriter = new FileWriter(this.fileName, false);
            fileWriter.write(fileTask);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("The file can't be overwrite");
        }
    }
}
