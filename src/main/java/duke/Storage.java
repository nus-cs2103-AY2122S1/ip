package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class loads and saves the list in a file so it can be kept for future use
 */
public class Storage {

    public Storage(String filePath) {
    }

    /**
     * Loads the given tasklist
     * @return
     */
    public ArrayList loadFile() {
        ArrayList<Task> storedList = new ArrayList<Task>();
        try {
            File taskList = new File("duke.txt");
            Scanner taskReader = new Scanner(taskList);
            while (taskReader.hasNextLine()) {
                String item = taskReader.nextLine();
                if (item.charAt(1) == 'T') {
                    ToDoTask addedTask = new ToDoTask(item.substring(7));
                    if (item.charAt(4) == 'X') {
                        addedTask.markAsDone();
                    }
                    storedList.add(addedTask);
                } else if (item.charAt(1) == 'D') {
                    int i = item.indexOf("(");
                    DeadlineTask addedTask = new DeadlineTask(item.substring(7, i),
                            item.substring(i+5, item.length()-1));
                    if (item.charAt(4) == 'X') {
                        addedTask.markAsDone();
                    }
                    storedList.add(addedTask);
                } else if (item.charAt(1) == 'E') {
                    int i = item.indexOf("(");
                    EventTask addedTask = new EventTask(item.substring(7, i),
                            item.substring(i+5, item.length()-1));
                    storedList.add(addedTask);
                    if (item.charAt(4) == 'X') {
                        addedTask.markAsDone();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Tasklist not found. Creating new tasklist");
        }
        return storedList;
    }

    /**
     * Saves the given tasklist into a file
     * @param taskList
     */
    public void saveFile(TaskList taskList) {
        File items = new File("duke.txt");
        try {
            if (items.createNewFile()) {
                System.out.println("Tasklist created and saved");
            } else {
                System.out.println("Tasklist updated");
            }
        } catch (IOException e) {
            System.out.println("File could not be created");
        }
        try {
            FileWriter listEditor = new FileWriter("duke.txt");
            for (Task item: taskList.getTasks()) {
                if (item != null) {
                    listEditor.write(item.toString());
                    listEditor.write("\n");
                }
            }
            listEditor.close();
            System.out.println("Saving list..");
        } catch (IOException e) {
            System.out.println("File does not exist");
        }
    }
}
