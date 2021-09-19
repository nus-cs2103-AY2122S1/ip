package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import duke.exceptions.DukeException;
import duke.exceptions.NoSuchTaskException;
import duke.tasks.Task;

/**
 * Storage Class that is able to read .txt file to Tasklist and
 * convert Tasklist to a .txt file
 *
 */

public class Storage {

    private String filepath;

    /**
     * Create a storage class that stores the filepath and handles converting between
     * file and Tasklist
     *
     * @param filepath filepath where tasklist is saved to
     */

    Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Writes tasklist to .txt file
     *
     * @param tasklist tasklist to be written to file
     * @throws IOException If writer is not working as intended
     */
    public void writeToFile(Tasklist tasklist) throws IOException {
        try {
            Task.saveTaskList(tasklist.getTasklist(), filepath);

        } catch (IOException ex) {
            throw new IOException();
        }
    }


    /**
     * Create a storage class that stores the filepath and handles converting between
     * file and Tasklist
     *
     * @throws DukeException If scanner is not working as intended or input is not the right format
     */
    public Tasklist fileToTasklist() throws DukeException {
        try {
            File file = new File(filepath);
            System.out.println(filepath);
            Tasklist tasklist = new Tasklist();
            if (!file.exists()) {
                file.createNewFile();
                return tasklist;
            } else {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                StringBuffer sb = new StringBuffer();
                String line;

                while ((line = br.readLine()) != null) {
                    Task temp = Tasklist.parseInput(line);
                    tasklist.addTask(temp);
                }
                return tasklist;
            }


        } catch (NoSuchTaskException | IOException e) {
            throw new DukeException("File is corrupted.");
        }
    }

}
