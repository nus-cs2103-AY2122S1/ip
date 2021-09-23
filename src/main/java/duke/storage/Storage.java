package duke.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

/**
 * Storage reads and writes to a .txt file to save and load the tasks.
 */
public class Storage {

    public static final String TERMINATOR = "/@";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * This method makes a directory at a hardcoded location for the data to be stored.
     * If the directory is already made, nothing happens.
     */
    //todo make this method more substantial, put a check to see if file exist first.
    public void makeDir() {
        File f = new File("./data");
        boolean bool = f.mkdir();
        if (bool) {
            System.out.println("file created successfully");
        } else {
            System.out.println("file creation failed");
        }
    }

    /**
     * This methods checks if the file has been created in a hardcode location
     * and creates the file if it has not been created.
     */
    public void checkFile() {
        try {
            File myObj = new File("./data/duke.txt");
            myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred when creating the file");
            e.printStackTrace();
        }
    }

    /**
     * This method reads from a hardcoded location and adds tasks
     * into the task list based on the text stored inside.
     *
     * @return An ArrayList of the tasks.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> load = new ArrayList<>();
        try {
            File tasks = new File("data/duke.txt");
            Scanner myReader = new Scanner(tasks);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split("/@", 0);
                String marker = data[0];
                String name = data[2];
                boolean isDone = (data[1].equals("1"));
                LocalDateTime time = LocalDateTime.parse(data[3], formatter);

                switch (marker) {
                case "D":
                    Task t = new Deadline(name, time, isDone);
                    load.add(t);
                    break;
                case "T":
                    t = new Todo(name, isDone);
                    load.add(t);
                    break;
                case "E":
                    t = new Event(name, time, isDone);
                    load.add(t);
                    break;
                default:
                    //todo throw an error here since its not suppose to reach here
                    //it might reach here if the user edit the file
                    break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
        return load;
    }

    /**
     * This method reads the tasks in tasklist and translates into
     * a special syntax to be saved in a text file.
     *
     * @param tasklist The list of tasks.
     */
    public void saveTasks(TaskList tasklist) {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < tasklist.getTotalTasksNumber(); i++) {
                StringBuilder s = new StringBuilder();
                Task current = tasklist.getTask(i);
                //todo change this code block to a method
                s.append(current.getMarker());
                s.append(TERMINATOR);
                s.append(current.getDone());
                s.append(TERMINATOR);
                s.append(current.getName());
                s.append(TERMINATOR);
                s.append(current.getTime());

                String temp = s.toString();
                bw.write(temp);
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
