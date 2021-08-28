package yoyo.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import yoyo.exception.YoyoException;
import yoyo.task.Deadline;
import yoyo.task.Event;
import yoyo.task.TaskList;
import yoyo.task.Todo;



public class Storage {
    private static final int TYPE_STR_INDEX = 1;
    private static final int ISDONE_STR_INDEX = 4;
    private final String dataPath;
    private final File file;

    /**
     * Constructor for Storage class.
     *
     * @param dataPath Main data path of storage.
     */
    public Storage(String dataPath) {
        this.dataPath = dataPath;
        this.file = new File(dataPath);
        try {
            if (!this.file.exists()) {
                this.file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong while creating new file:\n"
                    + e.getMessage());
        }
    }

    /**
     * Reads a file and returns a Tasklist consisting of tasks listed in the file.
     *
     * @return A TaskList instance consisting of tasks listed in the file.
     * @throws YoyoException
     */
    public TaskList load() throws YoyoException {
        return readExistingTasks(this.file);
    }

    /**
     * Reads a file and returns a Tasklist consisting of tasks listed in the file.
     *
     * @param f File to be read.
     * @return A TaskList instance consisting of tasks listed in the file.
     * @throws YoyoException
     */
    private TaskList readExistingTasks(File f) throws YoyoException {
        TaskList tasks = new TaskList();
        try {
            Scanner s = new Scanner(f);
            String currLine;
            boolean isCurrTaskComplete;
            String[] currStrArr;
            TaskType currType;

            while (s.hasNext()) {
                currLine = s.nextLine();
                isCurrTaskComplete = currLine.charAt(ISDONE_STR_INDEX) == 'X';
                char typeChar = currLine.charAt(TYPE_STR_INDEX);
                currType = typeChar == 'T'
                        ? TaskType.TODO
                        : typeChar == 'D'
                        ? TaskType.DEADLINE
                        : TaskType.EVENT;
                currStrArr = currLine.split(", ");
                switch (currType) {
                case TODO:
                    tasks.add(new Todo(currStrArr[1], isCurrTaskComplete));
                    break;
                case EVENT:
                    tasks.add(new Event(currStrArr[1], LocalDateTime.parse(currStrArr[2]), isCurrTaskComplete));
                    break;
                case DEADLINE:
                    tasks.add(new Deadline(currStrArr[1], LocalDateTime.parse(currStrArr[2]), isCurrTaskComplete));
                    break;
                default:
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new YoyoException("Critical Error! File exists but could not be found.");
        }
    }

    /**
     * Writes all of input TaskList's tasks to the file at storage's data path.
     *
     * @param tasks TaskList of the program.
     */
    public void deposit(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(this.dataPath);
            String textOutput = "";
            for (int i = 0; i < tasks.size(); i++) {
                textOutput += tasks.get(i).showStatusWrite();
                textOutput += "\n";
            }
            fw.write(textOutput);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong while creating file writer:\n"
                    + e.getMessage());
        }
    }

    private enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }

}
