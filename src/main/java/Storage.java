import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private Path savePath;

    public Storage(Path savePath) {
        this.savePath = savePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (!Files.exists(savePath)) {
                Files.createFile(savePath);
            }
            Scanner sc = new Scanner(savePath);
            while(sc.hasNextLine()) {
                String nextTask = sc.nextLine();
                char taskType = nextTask.charAt(0);
                boolean isDone = Integer.parseInt(nextTask.substring(1,2)) == 1;
                String taskDetails = nextTask.substring(3);

                if (taskType == 'T') {
                    tasks.add(new ToDo(taskDetails, isDone));
                } else {
                    int breakIndex = taskDetails.indexOf('|');
                    String taskDescription = taskDetails.substring(0, breakIndex);
                    String taskDateAndTime = taskDetails.substring(breakIndex + 1);
                    if (taskType == 'D') {
                        tasks.add(new Deadline(taskDescription, taskDateAndTime, isDone));
                    } else if (taskType == 'E') {
                        tasks.add(new Event(taskDescription, taskDateAndTime, isDone));
                    } else {
                        throw new DukeException(DukeException.CORRUPT_TASK);
                    }
                }
            }
            return tasks;
        } catch (IOException | NumberFormatException e) {
            throw new DukeException(DukeException.CORRUPT_SAVE);
        }
    }

    public void save(TaskList taskList) {
        try {
            Files.delete(savePath);
            Files.createFile(savePath);
            String saves = "";
            for (int i = 0; i < taskList.size(); i++) {
                saves += taskList.getTask(i).getSave() + "\n";
            }
            byte[] savesToBytes = saves.getBytes();
            Files.write(savePath, savesToBytes);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
