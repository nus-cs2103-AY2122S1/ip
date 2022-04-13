package duke;


import java.io.*;
import java.util.LinkedList;


/**
 * Class that deals with loading tasks from file and saving tasks in file
 */

public class Storage {
    private String filePath;

    /**
     * Constructor for Storage/
     * @param filePath the path of the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }


    /**
     * load data from list file in data folder.
     */
    @SuppressWarnings("unchecked")
    protected LinkedList<Task> loadData() throws DukeException {
        // Create data folder if it does not exist.
        File dataFolder = new File("./data/");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }
        try {
            File dataFile = new File(filePath);
            if (dataFile.createNewFile() || dataFile.length() == 0) {
                // If dataFile does not exist (has not been created before)
                System.out.println("Seems like a new list has been created!");
                return new LinkedList<Task>();
            } else {
                // dataFile already has been created before
                System.out.println("Let's load up your list!");
                FileInputStream listIn = new FileInputStream(filePath);
                ObjectInputStream in = new ObjectInputStream(listIn);
                LinkedList<Task> myList = (LinkedList<Task>) in.readObject();
                listIn.close();
                in.close();
                return myList;
            }
        } catch (IOException | ClassNotFoundException error) {
            System.out.println(error.toString());
            throw new DukeException("Something went wrong with loading a list up!");
        }
    }

    /**
     * Update data in list file.
     */
    protected void updateData(LinkedList<Task> myList) throws DukeException {
        if (!myList.isEmpty()) {
            try {
                FileOutputStream fileOut = new FileOutputStream("./data/list.txt");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(myList);
                out.close();
                fileOut.close();
                System.out.println("I've updated your list for you! You're welcome! :)");
            } catch (IOException error) {
                throw new DukeException("Something went wrong when I tried to save your list :(");
            }
        }
    }

    /**
     * Delete a task from list.
     * @throws DukeException an exception for duke.
     */
    public void deleteData() throws DukeException {
        try {
            File dataFile = new File("./data/list.txt");
            dataFile.delete();
            System.out.println("Before you leave, it seems your list is empty anyway so I'll just delete it for you!");
        } catch (Exception exception) {
            throw new DukeException("Something went wrong when I tried to delete your empty list!");
        }
    }
}
