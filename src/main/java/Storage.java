import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private File file;
    public Storage(Path filepath) {
        file = filepath.toFile();
    }

    public TaskList load(UserInterface ui){
        try {
            Scanner fileReader = new Scanner(file);
            TaskList taskList = new TaskList();
            while (fileReader.hasNext()) {
                String row = fileReader.nextLine();
                String[] tokens = row.split(",");
                if (tokens[0].equals("todo")) {
                    Task task = new ToDo(tokens[1]);
                    taskList.add(task);
                } else if (tokens[0].equals("event")) {
                    Task task = new Event(tokens[1], tokens[2]);
                    taskList.add(task);
                } else if (tokens[0].equals("deadline")) {
                    Task task = new Deadline(tokens[1], tokens[2]);
                    taskList.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            ui.displayError("File could not be found");
        }
    }
}
