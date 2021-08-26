import jdk.jfr.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private final String saveFileLocation;

    public Storage(String saveFileLocation) {
        this.saveFileLocation = saveFileLocation;
    }

    public void updateSave(List<Task> tasks) {
        String data = "";
        for (int i = 0; i < tasks.size(); i++) {
            data += tasks.get(i).toStringSave() + "\n";
        }
        try {
            FileOutputStream fos = new FileOutputStream(saveFileLocation, false);
            byte[] b = data.getBytes();
            fos.write(b);
            fos.close();
        } catch (IOException ex) {
            System.out.println("Error: Unable to save latest task. Please try again \n" + ex.getMessage());
        }
    }

    public ArrayList<Task> loadSave() {
        try {

            File saveFile = new File(saveFileLocation);
            Scanner sc = new Scanner(saveFile);
            ArrayList<Task> tasks = new ArrayList<>();

            while (sc.hasNextLine()) {
                Task newTask;
                String[] taskDetails = sc.nextLine().split(">");
                String name = taskDetails[2];
                boolean done = taskDetails[1].equals("1");

                switch (taskDetails[0]) {
                    case "T":
                        newTask = new Task.ToDo(name, done);
                        break;
                    case "D":
                        newTask = new Task.Deadline(name, LocalDateTime.parse(taskDetails[3]), done);
                        break;
                    case "E":
                        newTask = new Task.Event(name, LocalDateTime.parse(taskDetails[3]), done);
                        break;
                    default:
                        throw new DukeException.NoNameException("Task type invalid");
                }
                tasks.add(newTask);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println("Error: save file not found \n" + e.getMessage());
        } catch (DukeException.NoNameException e) {
            System.out.println("Error: save file invalid");
        }
        return new ArrayList<>();
    }
}
