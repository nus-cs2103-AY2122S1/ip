package duke.main;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> readPastData() throws FileNotFoundException {
        ArrayList<Task> savedTasks = new ArrayList<>();
        File data = new File(this.filePath);
        Scanner dataReader = new Scanner(data);
        while (dataReader.hasNext()) {
            String entry = dataReader.nextLine();
            String[] components = entry.split("[\\|]");
            char prefix = entry.charAt(0);
            switch (prefix) {
            case 'T':
                String description = components[2].substring(1);
                ToDo t = new ToDo(description);
                if (checkIfDone(components[1])) {
                    t.markAsDone();
                }
                savedTasks.add(t);
                break;
            case 'D':
                description = components[2].substring(1);
                String time = components[3].substring(1);
                Deadline d = new Deadline(description, time);
                if (checkIfDone(components[1])) {
                    d.markAsDone();
                }
                savedTasks.add(d);
                break;
            case 'E':
                description = components[2].substring(1);
                time = components[3].substring(1);
                Event e = new Event(description, time);
                if (checkIfDone(components[1])) {
                    e.markAsDone();
                }
                savedTasks.add(e);
                break;
            }
        }
        return savedTasks;
    }

    private boolean checkIfDone(String component) {
        if (Integer.parseInt(component.substring(1,2)) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void writeCurrentData(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task t : tasks) {
            fw.write(t.toFile() + System.lineSeparator());
        }
        fw.close();
    }

    public void createDataFile() {
        File data = new File(this.filePath);
        try {
            data.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
