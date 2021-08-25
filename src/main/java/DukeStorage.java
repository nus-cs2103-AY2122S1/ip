import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;import java.util.ArrayList;

public class DukeStorage {
    public String path;

    public DukeStorage(String path) {
        this.path = path;
    }

    public ArrayList<Task> readTasks() throws DukeException {
        File file = new File(this.path);
        if (file.exists()) {
            try {
                Scanner sc = new Scanner(file);
                ArrayList<Task> taskList = new ArrayList<Task>();

                String data = "";
                while(sc.hasNext()) {
                    data += sc.nextLine() + "\n";
                }

                if (data.equals("")){
                    sc.close();
                    return new ArrayList<Task>();
                }

                String[] strArray = data.split("\n");

                for (String str : strArray) {
                    String[] parseTask = str.split(" \\| ");
                    
                    String taskType = parseTask[0];
                    boolean done = parseTask[1].equals("1") ? true : false;
                    String taskDescr = parseTask[2];

                    if (taskType.equals("T")) {
                        Todo task = new Todo(taskDescr);
                        if (done) task.setDone(); 
                        taskList.add(task);
                    } else if (taskType.equals("D")) {
                        Deadline task = new Deadline(taskDescr, parseTask[3]);
                        taskList.add(task);
                    } else {
                        Event task = new Event(taskDescr, parseTask[3]);
                        taskList.add(task);
                    }
                }
                sc.close();
                return taskList;
            } catch (FileNotFoundException e) {
                throw new DukeException("File error! Please try again.");
            }
        } else {
            try {
                file.createNewFile();
                return new ArrayList<Task>();
            } catch (IOException e) {
                throw new DukeException("File error! Please try again.");
            }
        }
    }

    public void writeTasks(ArrayList<Task> taskList) throws DukeException {
        File file = new File(this.path);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            String data = "";
            for (int i = 0; i < taskList.size(); i++) {
                data += taskList.get(i).getFileString() + "\n";
            }

            FileWriter w = new FileWriter(file);
            w.write(data);
            w.close();
        } catch (IOException e) {
            throw new DukeException("File error! Please try again.");
        }
    }
}
