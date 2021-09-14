package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage to handle logic of writing and reading from the file with the task list.
 */
public class Storage {
    private File file;

    /**
     * Creates a new storage to a certain filepath.
     *
     * @param filepath Path to file from project root.
     */
    public Storage(String filepath) {
        String[] filepathArr = filepath.split("/");
        try {
            String currentPath = "";
            for (int i = 0; i < filepathArr.length - 1; i++) {
                File dir = new File("./" + currentPath + filepathArr[i]);
                if (!dir.exists()) {
                    dir.mkdir();
                }
            }
            file = new File("./" + filepath);
            if (!file.exists()) {
                file.createNewFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
            //throw new duke.DukeException("Write file error");
        }
    }

    /**
     * Saves a task list to the file in the storage.
     *
     * @param list TaskList of tasks.
     */
    public String save(TaskList list) throws DukeException {
        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < list.getSize(); i++) {
                writer.write(list.get(i).getSaveString() + "\n");
            }
            writer.close();
            return "File saved to /data/duke.txt";
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException("Error saving file");
        }
    }

    /**
     * Loads file into an arraylist of strings. Each line is a line in the file
     *
     * @return File in form of arraylist of strings
     * @throws DukeException If file is not found
     */
    public ArrayList<String> load() throws DukeException {
        ArrayList<String> stringList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                stringList.add(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found");
        }
        return stringList;
    }

}
