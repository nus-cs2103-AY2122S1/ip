package duke.storage;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;
    private String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
        file = new File(fileName);
    }

    public ArrayList<Task> load() throws DukeException{
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            Scanner scan = new Scanner(this.file);

            while (scan.hasNext()) {
                Task newTask;

                String taskLine = scan.nextLine();

                String[] task = taskLine.split(" \\| ");
                String taskType = task[0];

                switch (taskType) {
                case ("T"):
                    newTask = new Todo(task[2]);
                    break;
                case ("D"):
                    newTask = new Deadline(task[2], task[3]);
                    break;
                case ("E"):
                    newTask = new Event(task[2], task[3]);
                    break;
                default:
                    throw new DukeException("Oops! Duke can't load a file");
                }

                if (task[1].equals("X")) {
                    newTask.markDone();
                }

                taskList.add(newTask);
            }

            return taskList;

        } catch (FileNotFoundException e) {
            return taskList;
        }
    }

    public void save(TaskList tasks) throws DukeException {
        try {
            String fileTask = tasks.toFileString();

            FileWriter fileWriter = new FileWriter(this.fileName, false);
            fileWriter.write(fileTask);
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Oops there is an issue with overriding the file");
        }
    }

}
