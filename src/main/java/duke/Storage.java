package duke;

import duke.exception.DukeException;
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

    protected Storage(String path) {
        this.path = path;
    }

    protected TaskList load() {
        TaskList tasks = new TaskList();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;

            while((line = bufferedReader.readLine()) != null) {
                String[] arr;
                boolean isDone;

                arr = line.split("\\|");
                isDone = arr[1].equals("1");

                switch (arr[0]) {
                    case "T":
                        tasks.add(new ToDo(isDone, arr[2]));
                        break;
                    case "E":
                        tasks.add(new Event(isDone, arr[2], LocalDate.parse(arr[3])));
                        break;
                    case "D":
                        tasks.add(new Deadline(isDone, arr[2], LocalDate.parse(arr[3])));
                        break;
                    default:
                        throw new DukeException("Unrecognized task flag");
                }
            }
        } catch(IOException e) {
            System.out.println(e.toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
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
