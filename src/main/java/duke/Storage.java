package main.java.duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class which reads and write to a .txt file to persist data
 */
public class Storage {

    private final String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }


    /**
     * Reads the file and produces the last used list of tasks
     *
     * @return ArrayList of Tasks, Tasks saved in the file
     * @throws IOException
     */
    protected ArrayList<Task> load() throws IOException {
        File dukeItems = new File(filepath);
        String data = "";
        if (dukeItems.createNewFile()) {
            System.out.println("File created: " + dukeItems.getName());
        } else {
            Scanner myReader = new Scanner(dukeItems);
            while (myReader.hasNextLine()) {
                data += myReader.nextLine() + "\n";
            }
            myReader.close();
        }

        ArrayList<Task> result = new ArrayList<>();
        if (!data.equals("")) {
            for (String i :
                    data.split("\n")) {
                if (i.split("\\|")[1].equals("T")) {
                    Task newTask = new Todo(i.split("\\|")[2]);
                    if (i.split("\\|")[0].equals("true")) {
                        newTask.markAsDone();
                    }
                    result.add(newTask);
                }
                if (i.split("\\|")[1].equals("D")) {
                    Task newTask = new Deadline(i.split("\\|")[2], i.split("\\|")[3]);
                    if (i.split("\\|")[0].equals("true")) {
                        newTask.markAsDone();
                    }
                    result.add(newTask);
                }
                if (i.split("\\|")[1].equals("E")) {
                    Task newTask = new Event(i.split("\\|")[2], i.split("\\|")[3]);
                    if (i.split("\\|")[0].equals("true")) {
                        newTask.markAsDone();
                    }
                    result.add(newTask);
                }
            }
        }
        return result;
    }

    /**
     * Writes to the file with the give TaskList
     *
     * @param list the list of task needed to be saved
     * @throws IOException
     */
    protected void save(TaskList list) throws IOException {
        String result = "";
        FileWriter myWriter = new FileWriter(filepath);
        for (int i = 0; i < list.size(); i++) {
            Task t = list.getTask(i);
            if (t instanceof Todo) {
                result += t.isDone + "|" + "T" + "|" + t.description + "\n";
            } else if (t instanceof Deadline) {
                result += t.isDone + "|" + "D" + "|" + t.description + "|" + ((Deadline) t).date + "\n";
            } else if (t instanceof Event) {
                result += t.isDone + "|" + "E" + "|" + t.description + "|" + ((Event) t).date + "\n";
            }
        }
        myWriter.write(result);
        myWriter.close();
    }
}
