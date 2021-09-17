package blitz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import blitz.tasks.Deadline;
import blitz.tasks.Event;
import blitz.tasks.Task;
import blitz.tasks.Todo;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads contents from the file and stores data in a list.
     *
     * @return list containing blitz.tasks from the file.
     */
    public ArrayList<Task> loadFileContents() throws BlitzException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        File f = new File(filePath);
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String task = s.nextLine();
                String[] keywords = task.split(" \\| ");
                assert(keywords.length >= 3);
                String done = keywords[1];
                Task current = new Task("");
                switch(task.charAt(0)) {
                case 'T' :
                    current = new Todo(keywords[2]);
                    break;
                case 'D' :
                    current = new Deadline(keywords[2], LocalDateTime.parse(keywords[3],
                            DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")));
                    break;
                default :
                    current = new Event(keywords[2], LocalDateTime.parse(keywords[3],
                            DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")));
                }
                if (done.equals("X")) {
                    current.markAsDone();
                }
                tasks.add(current);
            }
        } catch (FileNotFoundException e) {
            if (f.getParentFile() != null && !f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            try {
                f.createNewFile();
            } catch (IOException ex) {
                throw new BlitzException("Error creating file!!");
            }
        }
        return tasks;
    }

    /**
     * Saves the updates list of blitz.tasks to the file.
     *
     * @param tasks list of blitz.tasks to be saved onto the file.
     * @throws IOException if the program cannot create or
     *     write to the file indicated.
     */
    public void saveTasksInFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : tasks.getTaskList()) {
            assert(!t.getDescription().isEmpty());
            if (t instanceof Todo) {
                fw.write("T | " + t.getStatusIcon() + " | " + t.getDescription()
                        + System.lineSeparator());
            } else if (t instanceof Deadline) {
                fw.write("D | " + t.getStatusIcon() + " | " + t.getDescription()
                        + " | " + ((Deadline) t).getBy() + System.lineSeparator());
            } else {
                fw.write("E | " + t.getStatusIcon() + " | " + t.getDescription()
                        + " | " + ((Event) t).getDate() + System.lineSeparator());
            }
        }
        fw.close();
    }
}
