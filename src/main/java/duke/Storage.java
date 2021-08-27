package duke;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.FileWriter;
import java.io.IOException;


public class Storage {
    private final String FILE_PATH;

    public Storage(String filePath) {
        this.FILE_PATH = filePath;
    }

    public ArrayList<Task> load() {
        try {
            ArrayList<Task> savedTasks = new ArrayList<>();
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();

            if (file.createNewFile()) return new ArrayList<Task>();

            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String taskString = data.substring(8);
                String completed = data.substring(4, 5);
                Task newTask;

                if (data.startsWith("T |")) {
                    newTask = new Todo(taskString);
                } else if (data.startsWith("D |")) {
                    int dividerPosition = taskString.indexOf("|");
                    newTask = new Deadline(taskString.substring(0, dividerPosition - 2), taskString.substring(dividerPosition + 2));
                } else if (data.startsWith("E |")) {
                    int dividerPosition = taskString.indexOf("|");
                    newTask = new Event(taskString.substring(0, dividerPosition - 2), taskString.substring(dividerPosition + 2));
                } else {
                    throw new DukeException("Save file corrupted.");
                }
                if (completed.equals("X")) newTask.complete();
                savedTasks.add(newTask);
            }
            myReader.close();
            return savedTasks;
        } catch (IOException e) {
            throw new DukeException("Access file error");
        }
    }

    public void save(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(this.FILE_PATH, false);
            tasks.forEach(task -> {
                try {
                    writer.write(task.saveData() + "\n");
                } catch (IOException e) {
                    throw new DukeException("Boohoo something went wrong :(");
                }
            });

            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new DukeException("File not found.");
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException("Unable to write");
        }
    }
}
