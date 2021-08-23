package duke;

import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private void initializeDone(Task t, String s) {
        if (s.equals("1")) {
            t.setDone();
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskArr = new ArrayList<>();
        File file = new File(".\\src\\main\\data");
        if (!file.isDirectory()) {
            Path path = Paths.get(".\\src\\main\\data");
            try {
                Files.createDirectories(path);
            } catch(IOException e) {
                System.out.println("Failed to create directory: " + e.getMessage());
            }
        }
        try{
            File fCurr = new File(filePath);
            Scanner reader = new Scanner(fCurr);
            while (reader.hasNextLine()) {
                String taskString = reader.nextLine();
                String[] strArr = taskString.split(" \\| ", 4);
                Task t;
                switch (strArr[0]) {
                    case "T":
                        t = new ToDo(strArr[2]);
                        break;
                    case "E":
                        t = new Event(strArr[2], strArr[3]);
                        break;
                    case "D":
                        t = new Deadline(strArr[2], strArr[3]);
                        break;
                    default:
                        throw new DukeException("initialization error");
                }
                initializeDone(t, strArr[1]);
                taskArr.add(t);
            }
        } catch (FileNotFoundException e){
            throw new DukeException("file not found");
        }

        return taskArr;
    }

    public void save(TaskList t) {
        try {
            File fOld = new File(filePath);
            fOld.delete();
            File fNew = new File(filePath);
            fNew.createNewFile();
            FileWriter myWriter = new FileWriter(filePath);
            String savedString = "";
            for(int i = 0; i < t.getSize(); i++) {
                Task task = t.get(i);
                String completionState = task.isDone() ? "1" : "0";
                savedString += task.getTag() + " | " + completionState + " | "
                        + task.getTaskName() + " | " + task.getAdditionalInfo() + "\n";
            }
            myWriter.write(savedString);
            myWriter.close();
        } catch (IOException e){
            System.out.println("File creation error: " + e.getMessage());
        }
    }
}
