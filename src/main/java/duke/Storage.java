package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Storage {
    private DateTimeFormatter dateTimeFormat;
    private File file;

    public Storage() {
        this.dateTimeFormat = DateTimeFormatter.ofPattern("yyyy MMM dd HH:mm");
    }

    public File getfiles() {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "ip","data");
        File directory = new File(path.toString());
        if (!directory.exists()) {
            System.out.println("'data' directory not found. Creating the directory...");
            directory.mkdir();
            System.out.println("Directory created.");
        }

        Path filepath = Paths.get(home, "ip","data", "duke.txt");
        File file = new File(filepath.toString());
        if (!file.exists()) {
            System.out.println("Data file not found. Creating a new file...");
            try {
                file.createNewFile();
                System.out.println("File created.");
            } catch (IOException e) {
                System.out.println("IO error occured");
            }
        }
        return file;
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            file = this.getfiles();
            FileReader reader = new FileReader(file);
            BufferedReader buffreader = new BufferedReader(reader);
            String text;
            ArrayList<Task> saved = new ArrayList<>();
            while ((text = buffreader.readLine()) != null) {
                String[] lineArr = text.split(" | ");
                switch (lineArr.length) {
                case 3:
                    // To complete TaskList class
                    Task toDo = new Task(lineArr[2]);
                    saved.add(toDo);
                    if (lineArr[1].equals("1")) {
                        toDo.complete();
                    }
                    break;
                case 4:
                    if (lineArr[0].equals("D")) {
                        Deadline deadline = new Deadline(lineArr[2], LocalDateTime.parse(lineArr[3], this.dateTimeFormat));
                        saved.add(deadline);
                    } else if (lineArr[0].equals("E")) {
                        Event event = new Event(lineArr[2], LocalDateTime.parse(lineArr[3], this.dateTimeFormat));
                        saved.add(event);
                    }
                    break;
                }
            }
            return saved;
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public void save(TaskList list) throws DukeException {
        try {
            FileWriter writer = new FileWriter(file, false);
            for (Task item : list.getAllTasks()) {
                writer.write(item.displayInfo() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
