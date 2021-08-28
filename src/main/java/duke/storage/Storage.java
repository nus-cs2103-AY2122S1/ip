package duke.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

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

    final String STRING_TERMINATOR = "/@";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * This method makes a directory at a hardcoded location for the data to be stored.
     */
    public void makeDir() {
        File f = new File("./data");
//        boolean bool = f.mkdir();
//        if(bool){
//            System.out.println("Directory created successfully");
//        }else{
//            System.out.println("Sorry couldn’t create specified directory");
//        }
    }

    /**
     * This methods checks if the file has been created in a hardcode location
     * and creates the file if it has not been created.
     */
    public void checkFile() {
        try {
            File myObj = new File("./data/duke.txt");
            myObj.createNewFile();
//            if (myObj.createNewFile()) {
//                System.out.println("File created: " + myObj.getName());
//            } else {
//                System.out.println("File already exists.");
//            }
        } catch (IOException e) {
            System.out.println("An error occurred when creating the file");
            e.printStackTrace();
        }
    }

    /**
     * This method reads from a hardcoded location and adds tasks
     * into the task list based on the text stored inside.
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
                boolean isDone = (data[1].equals("1"));
                String name = data[2];
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
     * @param tasklist The list of tasks.
     */
    public void saveTasks(TaskList tasklist) {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < tasklist.getTotalTasksNumber(); i++) {
                StringBuilder s = new StringBuilder();
                Task current = tasklist.getTask(i);
                s.append(current.getMarker());
                s.append(STRING_TERMINATOR);
                s.append(current.getDone());
                s.append(STRING_TERMINATOR);
                s.append(current.getName());
                s.append(STRING_TERMINATOR);
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
