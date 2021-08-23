package catobot;

import exception.EmptyCommandException;
import item.Deadline;
import item.Event;
import item.Task;
import item.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private final File storage;


    public Storage(String filePath) {
        this.filePath = filePath;
        this.storage = new File(filePath);
        try {
            if (!storage.exists()) {
                storage.getParentFile().mkdirs();
                storage.createNewFile();
            }
        } catch (IOException e) {
            Ui.showLoadingError();
        }
    }


    public List<Task> load() throws FileNotFoundException, EmptyCommandException {
        Scanner s = new Scanner(storage); // create a Scanner using the File as the source
        ArrayList<Task> taskList = new ArrayList<>();

        while (s.hasNext()) {
            String rawTask = s.nextLine();
            String[] input = rawTask.split(" \\| ", 4);
            String type = input[0];
            int isDone = Integer.parseInt(input[1]);
            String description = input[2];
            switch (type) {
            case "D":
                Deadline deadline = Deadline.of(description, LocalDate.parse(input[3]));
                if (isDone == 1) {
                    deadline.markAsDone();
                }
                taskList.add(deadline);
                break;
            case "E":
                Event event = Event.of(description, LocalDate.parse(input[3]));
                if (isDone == 1) {
                    event.markAsDone();
                }
                taskList.add(event);
                break;
            case "T":
                Todo todo = Todo.of(description);
                if (isDone == 1) {
                    todo.markAsDone();
                }
                taskList.add(todo);
                break;
            }
        }
        return taskList;
    }


    public void write(String s) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(s);
        fw.close();
    }
}
