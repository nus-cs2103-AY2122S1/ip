package duke;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Todos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Storage class that stores all previous chat history and list in data/duke.txt
 */
public class Storage {
    private File f;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HHmm");
    private TaskList taskList;

    /**
     * Constructor for Storage
     * @param pathname relative path to where the chat history is stored
     */
    public Storage(String pathname) {
        this.taskList = new TaskList();
        f = new File(pathname);
    }

    /**
     * read and parse data from previous chat history
     * @return List of previous stated task
     * @throws IOException when file cannot be read
     *
     */
    public TaskList readFile() throws IOException {
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            f.createNewFile();
        }
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String task = sc.nextLine();
            String[] parsed = task.split("\\|");

            switch (parsed[0]) {
                case "T":
                    Todos newTodo = new Todos(parsed[2], parsed[1]);
                    taskList.add(newTodo);
                    break;
                case "E":
                    LocalDateTime at = LocalDateTime.parse(parsed[3], formatter);
                    Events newEvent = new Events(parsed[2], parsed[1], at);
                    taskList.add(newEvent);
                    break;
                case "D":
                    LocalDateTime by = LocalDateTime.parse(parsed[3], formatter);
                    Deadlines newDeadline = new Deadlines(parsed[2], parsed[1], by);
                    taskList.add(newDeadline);
                    break;
            }
        }
        return taskList;
    }


    /**
     * saves the current list to the chat history file
     * @param taskList updated list after commands are executed
     * @throws IOException when file cannot be saved
     */
    public void writeToFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(f.getAbsoluteFile());

        for (int i = 0; i < taskList.getList().size(); i++) {
            fw.write(taskList.getList().get(i).toSaveString() + System.lineSeparator());
        }

        fw.close();
    }

}
