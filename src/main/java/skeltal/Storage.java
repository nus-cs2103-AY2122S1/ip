package skeltal;

import javafx.util.Pair;
import skeltal.task.*;
import skeltal.task.types.Deadline;
import skeltal.task.types.Event;
import skeltal.task.types.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * A class to handle the loading and storage of the list of tasks.
 */
public class Storage {
    /**
     * Loads the txt file of the list of tasks from the specified path.
     * @return A Pair<ArrayList<Task>, String> of Tasks representative of the
     *         TaskList and Skeltal's response to the user.
     */
    public static Pair<ArrayList<Task>, String> loadFile() {
        ArrayList<Task> arrayList = new ArrayList<>();
        String reply = "";
        try {
            File taskFile = new File("src/main/Data/skeltal.txt");
            Scanner sc =  new Scanner(taskFile);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] dataPr = data.split(" \\| ");
                String taskType = dataPr[0];
                String done = dataPr[1];
                switch (taskType) {
                    case "T":
                        ToDo todo = new ToDo(dataPr[2]);
                        if (Integer.parseInt(done) == 1) {
                            todo.setComplete();
                        }
                        arrayList.add(todo);
                        break;
                    case "E":
                        Event event = new Event(dataPr[2] +" /" + dataPr[3]);
                        if (Integer.parseInt(done) == 1) {
                            event.setComplete();
                        }
                        arrayList.add(event);
                        break;
                    case "D":
                        Deadline dead = new Deadline(dataPr[2] +" /" + dataPr[3]);
                        if (Integer.parseInt(done) == 1) {
                            dead.setComplete();
                        }
                        arrayList.add(dead);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            reply = "No existing tasks can be found! Creating a new list for you :)";
            File taskFile = new File("src/main/Data/skeltal.txt");
            try {
                taskFile.createNewFile();
            } catch (IOException ioException) {
                reply = "List could not be created :(";
            }
        } catch (SkeltalException e) {
            reply = e.getMessage();
        } finally {
            reply = "Saved tasks have been loaded into the skeltal system!";
            return new Pair<>(arrayList, reply);
        }
    }

    /**
     * Writes the current list of tasks to the txt file at the specified filepath.
     * @throws SkeltalException If the file does not exist.
     */
    public static String store() throws SkeltalException {
        try {
            String reply = "";
            FileWriter fw = new FileWriter("src/main/Data/skeltal.txt", false);
            fw.write(TaskList.storeTasks());
            reply += "wrote to skeltal.txt";
            fw.close();
            return reply;
        } catch (Exception e) {
            throw new SkeltalException(e.getMessage());
        }
    }

}
