package duke.storage;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.DukeException;

public class Storage {
    private String filepath;

    /**
     * Constructor for Storage object.
     *
     * @param filepath path of file to be loaded or modified
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads the tasklist stored in text format in the user's hard drive when Duke is run.
     *
     * @return Arraylist of task objects loaded from the file stored on the hard drive.
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        try {
            File directory = new File("../../../data");
            directory.mkdir();
            File data = new File("../../../data", "duke.txt");
            data.createNewFile();
            Scanner reader = new Scanner(data);
            while (reader.hasNextLine()) {
                String task = reader.nextLine();
                String[] vars = task.split("\\|");
                Task t;
                switch (vars[0].trim()) {
                    case "T": {
                        t = new ToDo(vars[2].trim());
                        break;
                    }
                    case "D": {
                        t = new Deadline(vars[2].trim(), vars[3].trim());
                        break;
                    }
                    case "E": {
                        t = new Event(vars[2].trim(), vars[3].trim());
                        break;
                    }
                    default: {
                        t = new ToDo("");
                        break;
                    }
                }
                if (Integer.parseInt(vars[1].trim()) == 1) {
                    t.completeItem();
                }
                listOfTasks.add(t);
            }
            reader.close();
            
        } catch (FileNotFoundException e) {
            throw new DukeException(e.getMessage());
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        } 
        return listOfTasks;
    }

    /**
     * Updates the txt file containing the list of tasks.
     *
     * @param l current tasklist that would be saved in the txt file.
     */
    public static void updateFile(TaskList l) {
        try {
            FileWriter writer = new FileWriter("../../../data/duke.txt", false);
            writer.write(l.format());
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }    
    }
}
