package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.classes.TaskList;
import duke.classes.tasks.Deadline;
import duke.classes.tasks.Event;
import duke.classes.tasks.Task;
import duke.classes.tasks.ToDo;
import duke.classes.exceptions.DukeException;


/**
 * Duke class handling the storage and loading of the user's list
 */
public class DukeStorage {
    /**
     * Class Constructor
     */
    public DukeStorage(){
    }

    /**
     * Retrieves file indicated in the String and writes it into a List of Tasks.
     *
     * @param pathname String indicating the filename
     * @return List of Tasks containing the Tasks in the file, else returns an empty ArrayList
     * @throws DukeException Thrown if file is not in the right format to be read
     * @throws FileNotFoundException Thrown when file is not found
     */
    public List<Task> retrieve(String pathname) throws DukeException, FileNotFoundException {
        File localList = new File(pathname);
        List<Task> taskList = new ArrayList<>();
        if (localList.exists()) {
            Scanner listScanner = new Scanner(localList);
            while (listScanner.hasNextLine()) {
                String data = listScanner.nextLine();

                //Logic to piece through task strings
                String[] words = data.split(" ");
                boolean isDone = words[1].equals("X");
                boolean hasTime = words[words.length - 1].equals("T");
                String desc = "";
                int descLength = hasTime ? words.length - 2 : words.length - 1;
                for (int i = 2; i < descLength; i++) {
                    desc += words[i] + " ";
                }
                desc = desc.trim();

                if (words[0].equals("T")) {
                    //Task is a toDo
                    desc += words[words.length - 1];
                    taskList.add(new ToDo(desc, isDone));
                } else if (words[0].equals("E")) {
                    //Task is an Event
                    if (hasTime) {
                        taskList.add(new Event(desc, words[words.length - 2], words[words.length - 1], isDone));
                    } else {
                        taskList.add(new Event(desc, words[words.length - 1], isDone));
                    }
                } else if (words[0].equals("D")) {
                    //Task is a Deadline
                    if (hasTime) {
                        taskList.add(new Deadline(desc, words[words.length - 2], words[words.length - 1], isDone));
                    } else {
                        taskList.add(new Deadline(desc, words[words.length - 1], isDone));
                    }
                } else {
                    throw new DukeException("Invalid FileFormat");
                }
            }
            listScanner.close();
        } else {
            // Make a template helper list
        }
        return taskList;
    }

    /**
     * Saves the list to a local file. If the file is not present, function creates the file as well
     *
     * @param taskList List to save to the file
     * @throws IOException Thrown if there is an issue creating, retrieving or writing the file
     */
    public void saveList(TaskList taskList) throws IOException {
        File savedList = new File("localList.txt");
        FileWriter listWriter = new FileWriter(savedList);
        // Task format: [Type] [isDone] [Description] [Time/Deadline (if applicable)]
        for (Task tsk : taskList.getTaskList()) {
            listWriter.write(tsk.toFileString() + "\n");
        }
        listWriter.close();
    }
}
