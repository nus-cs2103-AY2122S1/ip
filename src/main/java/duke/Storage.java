package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Storage {
    private String path;

    public Storage(String path) {
        this.path = path;
    }


    public TaskList load() {
        TaskList tasks = new TaskList();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            String[] arr;
            boolean done;

            while((line = bufferedReader.readLine()) != null) {
                arr = line.split("\\|");
                done = arr[1].equals("1");

                switch (arr[0]) {
                    case "T":
                        tasks.add(new ToDo(done, arr[2]));
                        break;
                    case "E":
                        tasks.add(new Event(done, arr[2], LocalDate.parse(arr[3])));
                        break;
                    case "D":
                        tasks.add(new Deadline(done, arr[2], LocalDate.parse(arr[3])));
                        break;
                    default:
                        throw new IllegalArgumentException("Unrecognized task flag");
                }
            }
        } catch(IOException e) {
            System.out.println(e.toString());
        }
        return tasks;
    }

    public void saveFile(TaskList tasks) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(tasks.toString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
