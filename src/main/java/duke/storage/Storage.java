package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import duke.DukeException;
import duke.TaskList;
import duke.commands.Command;
import duke.parser.Parser;

public class Storage {
    protected String filePath;
    protected File f;
    protected TaskList tasks;

    /**
     * A constructor of a Storage.
     *
     * @param filePath The path of the file the task list is saved in.
     * @param tl The TaskList of the chat bot.
     */
    public Storage(String filePath, TaskList tl) {
        this.filePath = filePath;
        this.f = new File(filePath);

        // handles the case whereby file/directory does not exist
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            try {
                f.createNewFile();
                assert f.exists() : "file/directory should exist by this point";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.tasks = tl;
    }

    /**
     * Reads data/duke.txt and copies data into task list.
     *
     * @throws DukeException if the named file exists but is a directory rather than a regular file
     * or does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public void readFile() throws DukeException {
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String text = s.nextLine();
                Map<String, String> m = Parser.parseFromFile(text);
                Command cmd = Parser.parseCommand(m.get("finalText"));
                cmd.execute(tasks);
                if (m.get("status").equals("X")) {
                    tasks.getTaskList().get(tasks.getTaskList().size() - 1).setDone();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new DukeException("No file found.");
        }
    }

    /**
     * Copies local TaskList to the specific file.
     *
     * @throws DukeException if the named file exists but is a directory rather than a regular file
     * or does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public void copyToFile() throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasks.getTaskList().size(); i++) {
                fw.write(tasks.getTaskList().get(i) + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException("There's something wrong with the file.");
        }
    }

    public void setTasks(TaskList tasks) {
        this.tasks = tasks;
    }

}
