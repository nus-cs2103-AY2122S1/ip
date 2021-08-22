package duke;

import duke.task.Task;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    
    private Path filePath;
    
    public Storage(Path filePath) {
        this.filePath = filePath;
    }
    
    public ArrayList<duke.task.Task> load() throws DukeException {
        try {
            ArrayList<duke.task.Task> result = new ArrayList<>();
            Path folderPath = Paths.get(filePath.toString(),  "..");
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
            }
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            File fileHolder = new File(filePath.toString());
            Scanner s = new Scanner(fileHolder);
            while (s.hasNext()) {
                String holder = s.nextLine();
                String[] content = holder.split(" \\| ");


                switch (content[0]) {
                case "T":
                    duke.task.Todo todo = new duke.task.Todo("todo " + content[2].trim());
                    if (content[1].equals("1")) {
                        todo.doneTask();
                    }
                    result.add(todo);
                    break;

                case "D":
                    duke.task.Deadline deadline = new duke.task.Deadline("deadline "
                            + content[2].trim() + " /" + content[3].trim());
                    if (content[1].equals("1")) {
                        deadline.doneTask();
                    }
                    result.add(deadline);
                    break;

                case "E":
                    duke.task.Event event = new duke.task.Event("event " + content[2].trim() + " /" + content[3].trim());
                    if (content[1].equals("1")) {
                        event.doneTask();
                    }
                    result.add(event);
                    break;
                }
            }
            s.close();
            return result;
        } catch (IOException | DukeException e) {
            throw new DukeException("☹ OH NOOOOO! I cannot locate the file!!" + e.getMessage());
        }
    }
    
    public void updateStorage(TaskList taskList) throws DukeException {
        try {
            Path temp = Files.createTempFile(Paths.get(filePath.toString(), "..")
                    , "temp", ".txt");
            FileWriter fw = new FileWriter(temp.toString());
            File tempFile = new File(temp.toString());
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.getTask(i);
                fw.write(task.record() + System.lineSeparator());
            }
            fw.close();
            Files.delete(filePath);
            Files.copy(temp, filePath);
            //noinspection ResultOfMethodCallIgnored
            tempFile.delete();
        } catch (IOException e) {
            throw new DukeException("☹ OH NOOOOO! Something wrong with the file!!" + e.getMessage());
        }
    }
}
