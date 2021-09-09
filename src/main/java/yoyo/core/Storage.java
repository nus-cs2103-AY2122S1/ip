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
import java.util.stream.Stream;

import static yoyo.utility.Constant.NEWLINE_CHAR;
import static yoyo.utility.Constant.COMMA_SEPARATOR;

/**
 * Storage class that represents the storage component of Yoyo program.
 */
public class Storage {
    private static final int STR_INDEX_TYPE = 1;
    private static final int STR_INDEX_ISDONE = 4;
    private static final int ARRAY_INDEX_NAME = 1;
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

    /**
     * Reads a file and returns a Tasklist consisting of tasks listed in the file.
     *
     * @return A TaskList instance consisting of tasks listed in the file.
     * @throws YoyoException
     */
    public TaskList load() throws YoyoException {
        return readExistingTasks(this.file);
    }

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

    private void addTaskFromLine(TaskList tasks, String currLine) {
        String[] currTokenArr = currLine.split(COMMA_SEPARATOR);

        //get type of task
        char typeChar = currLine.charAt(STR_INDEX_TYPE);
        TaskType currType = getTaskType(typeChar);

        //get info needed for creating this type of task
        String[] tagsArray = getTagsArray(currType, currTokenArr);
        LocalDateTime taskDateTime = getTaskDateTime(currTokenArr, currType);
        String taskName = currTokenArr[ARRAY_INDEX_NAME];
        boolean isTaskComplete = currLine.charAt(STR_INDEX_ISDONE) == CHAR_CROSS;

        addTask(tasks, currType, tagsArray, taskName, taskDateTime, isTaskComplete);
    }

    private LocalDateTime getTaskDateTime(String[] currTokenArr, TaskType currType) {
        if (currType.equals(TaskType.TODO)) {
            return null;
        }
        return LocalDateTime.parse(currTokenArr[ARRAY_INDEX_DATETIME]);
    }

    private void addTask(TaskList tasks, TaskType currType, String[] tagsArray, String taskName,
                         LocalDateTime taskDateTime, boolean isTaskComplete) {
        switch (currType) {
        case TODO:
            tasks.add(new Todo(taskName, isTaskComplete, tagsArray));
            break;
        case EVENT:
            tasks.add(new Event(taskName, taskDateTime, isTaskComplete, tagsArray));
            break;
        case DEADLINE:
            tasks.add(new Deadline(taskName, taskDateTime, isTaskComplete, tagsArray));
            break;
        default:
            assert false : "Something went wrong with save file format";
        }
    }

    private String[] getTagsArray(TaskType currType, String[] currTokenArr) {
        String[] tagsArray;
        if (currType.equals(TaskType.TODO)) { //deadline name time t1 t2
            assert currTokenArr.length > 1 : "Error in storage formatting code";
            tagsArray = skipArray(2, currTokenArr);
        } else {
            assert currTokenArr.length > 2 : "Error in storage formatting code";
            tagsArray = skipArray(3, currTokenArr);
        }
        return tagsArray;
    }

    private String[] skipArray(int i, String[] currTokenArr) {
        Stream<String> tokenStream = Stream.of(currTokenArr);
        String[] result = tokenStream.skip(i)
                .toArray(String[]::new);
        return result;
    }

    private TaskType getTaskType(char typeChar) {
        TaskType currType = typeChar == CHAR_TODO
                ? TaskType.TODO
                : typeChar == CHAR_DEADLINE
                ? TaskType.DEADLINE
                : TaskType.EVENT;
        return currType;
    }
}
