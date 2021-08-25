import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private ArrayList<Task> toStore;
    private final String FILEPATH;
    private final String DIRPATH;

    public Storage() {
        this.FILEPATH = "data/history.txt";
        this.DIRPATH = "data";
    }

    public ArrayList<Task> initialise() {
        //read from the data/history.text and return an ArrayList of Tasks
        File file = new File(FILEPATH);
        try {
            Scanner s = new Scanner(file);
            ArrayList<Task> output = new ArrayList<>();
            while (s.hasNext()) {
                String task = s.nextLine();
                String[] splitTask = task.split("\\|");
                if (splitTask.length == 3) {
                    // it is todotask
                    Task toAdd = new ToDo(splitTask[2]);
                    if (splitTask[1].equals("1")) {
                        toAdd.markAsDone();
                    }
                    output.add(toAdd);
                } else {
                    // can be event or deadline
                    if (splitTask[0].equals("E")) {
                        // event
                        Task toAdd = new Event(splitTask[2], splitTask[3]);
                        if (splitTask[1].equals("1")) {
                            toAdd.markAsDone();
                        }
                        output.add(toAdd);
                    } else {
                        // deadline
                        Task toAdd = new Deadline(splitTask[2], splitTask[3]);
                        if (splitTask[1].equals("1")) {
                            toAdd.markAsDone();
                        }
                        output.add(toAdd);
                    }
                }
            }
            return output;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    public void saveFile(TaskList list) {
        try {
            FileWriter fw = new FileWriter(FILEPATH);
            String textToAdd = "";
            for (int i = 0; i < list.getSize(); i++) {
                if (i == 0) {
                    textToAdd += list.taskSaveToString(i);
                } else {
                    textToAdd += "\n" + list.taskSaveToString(i);
                }
            }
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            File file = new File(DIRPATH);
            if (file.mkdir()) {
                saveFile(list);
            } else {
                System.out.println("Failed to create file");
            }
        }
    }
}
