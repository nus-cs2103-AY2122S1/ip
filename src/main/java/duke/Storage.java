package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a means to load and save files
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs <code>Storage</code>
     * @param filePath Path to file
     */
    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads a save file
     * @return List of saved tasks
     */
    public List<Task> loadSave() {
        List<Task> savedTasks = new ArrayList<>();
        String[] filepathArr = this.filePath.split("/");
        File directory = new File(filepathArr[0]);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File saveFile = new File(this.filePath);
        try {
            checkSaveFile(saveFile);
        } catch (IOException e) {
            System.out.println("Error loading save file :(");
            e.printStackTrace();
        }
        try {
            Scanner sc2 = new Scanner(saveFile);
            while (sc2.hasNextLine()) {
                String nextLine = sc2.nextLine();
                String[] splitLine = nextLine.split("[|]");
                parseSaveFile(savedTasks, splitLine);
            }
            sc2.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return savedTasks;
    }

    /**
     * Parses the save file and adds the right type of <code>Task</code> to <code>savedTasks</code>
     * @param savedTasks the saved tasks
     * @param splitLine the array containing saved data split by line
     */
    private void parseSaveFile(List<Task> savedTasks, String[] splitLine) {
        if (splitLine.length == 3) {
            parseTodo(savedTasks, splitLine);
        } else if (splitLine.length == 4) {
            String[] dateParameters = splitLine[3].split("-");
            int day = Integer.parseInt(dateParameters[2]);
            int month = Integer.parseInt(dateParameters[1]);
            int year = Integer.parseInt(dateParameters[0]);
            LocalDate localDate = LocalDate.of(year, month, day);
            if (splitLine[0].equals("D")) {
                parseDeadline(savedTasks, splitLine, localDate);
            } else {
                parseEvent(savedTasks, splitLine, localDate);
            }
        }
    }

    /**
     * Parses the saved data and adding the right type of <code>Event</code> to <code>savedTasks</code>
     * @param savedTasks the saved tasks
     * @param splitLine the array containing saved data split by line
     * @param localDate the date of the <code>Event</code>
     */
    private void parseEvent(List<Task> savedTasks, String[] splitLine, LocalDate localDate) {
        if (splitLine[1].equals("0")) {
            savedTasks.add(new Event(splitLine[2],
                    false,
                    localDate));
            return;
        }
        savedTasks.add(new Event(splitLine[2],
                true,
                localDate));
    }

    /**
     * Parses the saved data and adding the right type of <code>Deadline</code> to <code>savedTasks</code>
     * @param savedTasks the saved tasks
     * @param splitLine the array containing saved data split by line
     * @param localDate the date of the <code>Deadline</code>
     */
    private void parseDeadline(List<Task> savedTasks, String[] splitLine, LocalDate localDate) {
        if (splitLine[1].equals("0")) {
            savedTasks.add(new Deadline(splitLine[2],
                    false,
                    localDate));
            return;
        }
        savedTasks.add(new Deadline(splitLine[2],
                true,
                localDate));
    }

    /**
     * Parses the saved data and adding the right type of <code>Todo</code> to <code>savedTasks</code>
     * @param savedTasks the saved tasks
     * @param splitLine the array containing saved data split by line
     */
    private void parseTodo(List<Task> savedTasks, String[] splitLine) {
        if (splitLine[1].equals("0")) {
            savedTasks.add(new Todo(splitLine[2], false));
            return;
        }
        savedTasks.add(new Todo(splitLine[2], true));
    }

    /**
     * Checks for the presence of a save file and creates one if absent
     * @param saveFile the save file containing previously added tasks
     * @throws IOException
     */
    private void checkSaveFile(File saveFile) throws IOException {
        if (saveFile.createNewFile()) {
            System.out.println("Save file created!");
            return;
        }
        System.out.println("Save file found!");
    }

    /**
     * Updates save file
     * @param updatedTasks New list of tasks
     * @throws IOException
     */
    public void updateFile(List<Task> updatedTasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task i : updatedTasks) {
            String taskBody = i.getBody();
            boolean isDone = i.isDone();
            if (i instanceof Todo) {
                writeTodo(fw, taskBody, isDone);
            } else if (i instanceof Deadline) {
                writeDeadline(fw, (Deadline) i, taskBody, isDone);
            } else {
                writeEvent(fw, (Event) i, taskBody, isDone);
            }
        }
        fw.close();
    }

    /**
     * Writes <code>Event</code> task into save file
     * @param fw the <code>FileWriter</code> object to write
     * @param event the <code>Event</code> task
     * @param taskBody the message body of <code>Event</code>
     * @param isDone whether the task is done
     * @throws IOException
     */
    private void writeEvent(FileWriter fw, Event event, String taskBody, boolean isDone) throws IOException {
        LocalDate taskDate = event.getDate();
        if (!isDone) {
            fw.write("E|0|" + taskBody + "|" + taskDate + System.lineSeparator());
            return;
        }
        fw.write("E|1|" + taskBody + "|" + taskDate + System.lineSeparator());
    }

    /**
     * Writes <code>Deadline</code> task into save file
     * @param fw the <code>FileWriter</code> object to write
     * @param deadline the <code>Deadline</code> task
     * @param taskBody the message body of <code>Deadline</code>
     * @param isDone whether the task is done
     * @throws IOException
     */
    private void writeDeadline(FileWriter fw, Deadline deadline, String taskBody, boolean isDone) throws IOException {
        LocalDate taskDeadline = deadline.getDeadline();
        if (!isDone) {
            fw.write("D|0|" + taskBody + "|" + taskDeadline + System.lineSeparator());
            return;
        }
        fw.write("D|1|" + taskBody + "|" + taskDeadline + System.lineSeparator());
    }

    /**
     * Writes <code>Todo</code> task into save file
     * @param fw the <code>FileWriter</code> object to write
     * @param taskBody the message body of <code>Todo</code>
     * @param isDone whether the task is done
     * @throws IOException
     */
    private void writeTodo(FileWriter fw, String taskBody, boolean isDone) throws IOException {
        if (!isDone) {
            fw.write("T|0|" + taskBody + System.lineSeparator());
            return;
        }
        fw.write("T|1|" + taskBody + System.lineSeparator());
    }
}
