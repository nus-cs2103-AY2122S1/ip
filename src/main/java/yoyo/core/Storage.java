package yoyo.core;

import yoyo.exception.YoyoException;
import yoyo.task.Deadline;
import yoyo.task.Event;
import yoyo.task.TaskList;
import yoyo.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import static yoyo.utility.Constant.NEWLINE_CHAR;
import static yoyo.utility.Constant.COMMA_SEPARATOR;

public class Storage {
    /**Index of where type info is stored in the line string being read*/
    private static final int STR_INDEX_TYPE = 1;
    /**Index of where isDone info is stored in the line string being read*/
    private static final int STR_INDEX_ISDONE = 4;
    /**Index of where name is stored in the token array being read*/
    private static final int ARRAY_INDEX_NAME = 1;
    /**Index of where date time info is stored in the token array being read*/
    private static final int ARRAY_INDEX_DATETIME = 2;
    private static final Character CHAR_CROSS = 'X';
    private static final Character CHAR_TODO = 'T';
    private static final Character CHAR_DEADLINE = 'D';

    private final String dataPath;
    private final File file;

    private enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }

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
            System.out.println("Something went wrong while creating new file:"
                    + NEWLINE_CHAR
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

            while (s.hasNext()) {
                String currLine = s.nextLine();
                addTaskFromLine(tasks, currLine);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new YoyoException("Critical Error! File exists but could not be found.");
        }
    }

    /**
     * Adds new task to task list from loaded line.
     *
     * @param tasks    Main Tasklist.
     * @param currLine The line to be parsed.
     */
    private void addTaskFromLine(TaskList tasks, String currLine) {
        boolean isTaskComplete = currLine.charAt(STR_INDEX_ISDONE) == CHAR_CROSS;

        char typeChar = currLine.charAt(STR_INDEX_TYPE);
        TaskType currType = getTaskType(typeChar);

        String[] currTokenArr = currLine.split(COMMA_SEPARATOR);

        String[] tagsArray;
        if (currType.equals(TaskType.TODO)) {
                tagsArray = subArray(3, currTokenArr);
        } else {
            tagsArray = subArray(4, currTokenArr);
        }

        switch (currType) {
        case TODO:
            tasks.add(new Todo(currTokenArr[ARRAY_INDEX_NAME], isTaskComplete, tagsArray));
            break;
        case EVENT:
            tasks.add(new Event(currTokenArr[ARRAY_INDEX_NAME],
                    LocalDateTime.parse(currTokenArr[ARRAY_INDEX_DATETIME]),
                    isTaskComplete, tagsArray));
            break;
        case DEADLINE:
            tasks.add(new Deadline(currTokenArr[ARRAY_INDEX_NAME],
                    LocalDateTime.parse(currTokenArr[ARRAY_INDEX_DATETIME]),
                    isTaskComplete, tagsArray));
            break;
        default:
            assert false : "Something went wrong with save file format";
        }
    }

    private String[] subArray(int i, String[] currTokenArr) {
//        intStream.range()
    }

    private TaskType getTaskType(char typeChar) {
        TaskType currType = typeChar == CHAR_TODO
                ? TaskType.TODO
                : typeChar == CHAR_DEADLINE
                ? TaskType.DEADLINE
                : TaskType.EVENT;
        return currType;
    }

    /**
     * Writes all of input TaskList's tasks to the file at storage's data path.
     *
     * @param tasks TaskList of the program.
     */
    public void depositTask(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(this.dataPath);

            String textOutput = "";
            for (int i = 0; i < tasks.size(); i++) {
                textOutput += tasks.get(i).showStatusWrite();
                textOutput += NEWLINE_CHAR;
            }

            fw.write(textOutput);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong while creating file writer:"
                    + NEWLINE_CHAR
                    + e.getMessage());
        }
    }
}
