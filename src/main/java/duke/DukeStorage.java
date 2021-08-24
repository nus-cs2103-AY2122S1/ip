package duke;

import duke.classes.TaskList;
import duke.exceptions.DukeException;
import duke.tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DukeStorage {
    /**
     * TODO
     */
    public DukeStorage(){
    };

    /**
     * TODO
     * @return
     * @throws DukeException
     * @throws FileNotFoundException
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
                boolean isDone = words[1].equals("X") ? true : false;
                String desc = "";
                for (int i = 2; i < words.length - 1; i++) {
                    desc += words[i] + " ";
                }
                desc.trim();

                if (words[0].equals("T")) {
                    //Task is a toDo
                    desc += words[words.length - 1];

                    taskList.add(new ToDo(desc, isDone));
                } else if (words[0].equals("E")) {
                    //Task is a Event
                    taskList.add(new Event(desc, words[words.length - 1], isDone));
                } else if (words[0].equals("D")) {
                    //Task is a Deadline
                    taskList.add(new Deadline(desc, words[words.length - 1], isDone));
                } else {
                    throw new DukeException("Invalid Fileformat");
                }
            }
            listScanner.close();
        }
        return taskList;
    }

    /**
     * TODO
     * @param tasklist
     * @throws IOException
     */
    public void saveList(TaskList tasklist) throws IOException {
            File savedList = new File("localList.txt");
            FileWriter listWriter = new FileWriter(savedList);
            // Task format: [Type] [isDone] [Description] [Time/Deadline (if applicable)]
            for (Task tsk : tasklist.getTaskList()) {
                listWriter.write(tsk.toFileString() + "\n");
            }
            listWriter.close();

    }
}
