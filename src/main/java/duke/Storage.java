package duke;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Deadline;

import java.io.File;
import java.io.FileWriter;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object.
     *
     * @param filePath The path of saved file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    };

    /**
     * Starts up the file reader, creates file if it does not exist.
     *
     * @return TaskList New TaskList with inputs from filePath.
     * @throws DukeException
     * @throws IOException
     */
    public TaskList load() throws DukeException, IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            Files.createFile(Paths.get(filePath));
        }

        TaskList storedTaskList = new TaskList();
        int index = 1;

        // Insert tasks from hard disk into TaskList.
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String unformattedTask = s.nextLine();
            System.out.println(unformattedTask);
            String[] splitTask = unformattedTask.split(" \\| ", 3);
            String command = splitTask[0];
            String isDone = splitTask[1];
            String body = splitTask[2];

            if (command.equals("T")) {
                ToDo t = Parser.validateToDo("todo " + body);
                storedTaskList.add(t);
            } else if (command.equals("D") || command.equals("E")) {
                String[] descAndTime = body.split(" \\| ", 3);

                if (command.equals("D")) {
                    Deadline d = Parser.validateDeadline(
                            "deadline " + descAndTime[0] + " /by " + descAndTime[1]);
                    storedTaskList.add(d);
                } else {
                    Event e = Parser.validateEvent(
                            "event " + descAndTime[0] + " /at " + descAndTime[1]);
                    storedTaskList.add(e);
                }
            }

            if (isDone.equals("1")) {
                Parser.validateDone("done " + index);
            }
            index += 1;
        }
        return storedTaskList;
    }

    /**
     * Appends to file. Append when adding new tasks.
     * @param textToAppend
     * @throws IOException
     */
    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(System.lineSeparator() + textToAppend);
        fw.close();
    }

    /**
     * Rewrites the entire file with the given string.
     *
     * @param tasks TaskList to write into file.
     * @throws IOException
     */
    public void rewriteFile(TaskList tasks) throws IOException {
        String req = "";
        for (Task task : tasks.getAll()) {
            if (task instanceof ToDo) {
                req += "T | ";
                req += task.isDone() ? "1 | " : "0 | ";
                req += task.getDescription();
                req += System.lineSeparator();
            } else if (task instanceof Deadline) {
                req += "D | ";
                req += task.isDone() ? "1 | " : "0 | ";
                req += task.getDescription() + " | ";
                req += ((Deadline) task).getDate();
                req += System.lineSeparator();
            } else if (task instanceof Event) {
                req += "E | ";
                req += task.isDone() ? "1 | " : "0 | ";
                req += task.getDescription() + " | ";
                req += ((Event) task).getDate();
                req += System.lineSeparator();
            }
        }

        FileWriter fw = new FileWriter(filePath);
        fw.write(req);
        fw.close();
    }
}
