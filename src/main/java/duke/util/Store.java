package duke.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exception.FileWritingException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 *
 * Description:
 * Encapsulates the Store Class which has the functions that stores the task
 * list in the hard drive
 *
 * @author Keith Tan
 */
public class Store {

    private static final DateTimeFormatter OFFICIAL_FORMAT = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
    private String filePath;

    public Store(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks in hard disk to current task list
     *
     * @return Tasklist returns a tasklist of all tasks stored in the hard drive
     */
    public Tasklist load() {
        Tasklist result;
        try {

            File inputFile = new File(this.filePath);
            if (!inputFile.exists()) {
                inputFile.getParentFile().mkdirs();
                inputFile.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            String currentLine;
            Tasklist storeList = new Tasklist();

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.trim().equals("")) {
                    continue;
                }
                Task currentTask = parseLine(currentLine);
                storeList.addTask(currentTask);
            }
            reader.close();
            result = storeList;

        } catch (IOException | FileWritingException e) {
            result = new Tasklist();
        }
        return result;
    }

    /**
     * Saves tasks currently in the tasks list to the hard disk
     *
     * @param list Task list containing tasks to be saved into the hard disk
     * @throws FileWritingException throws a FileWritingException if error encountered during
     *                              saving of tasks
     */
    public void saveTasksToStore(Tasklist list) throws FileWritingException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            String fileString = "";
            fileString += list.toString();
            bw.write(fileString);
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {
            throw new FileWritingException();
        }
    }

    /**
     * Function parses each line in the file that contains task list that is saved in
     * the hard drive
     *
     * @param line line from file, which contains tasks list saved in hard drive,
     *             to be parsed
     * @throws FileWritingException throws a FileWritingException if error encountered during
     *                              parsing of file line
     */
    public Task parseLine(String line) throws FileWritingException {
        char type = line.charAt(3);
        boolean isCompleted = line.charAt(6) == ('X') ? true : false;
        switch (type) {
        case 'T':
            Task tempTodoTask = new ToDo(line.substring(9));
            if (isCompleted) {
                tempTodoTask.completeTask();
            }
            return tempTodoTask;
        case 'E':
            String eventDetails = line.substring(9);
            String[] checkEventDetails = eventDetails.split("at: ", 2);
            String[] eventDate = checkEventDetails[1].substring(0, checkEventDetails[1].length() - 1)
                    .split(" to ", 2);
            LocalDateTime eventStartTime = LocalDateTime.parse(eventDate[0], OFFICIAL_FORMAT);
            LocalDateTime eventEndTime = LocalDateTime.parse(eventDate[1], OFFICIAL_FORMAT);
            DukeDate eventDuration = new DukeDate(eventStartTime, eventEndTime);
            Task tempEventTask = new Event(checkEventDetails[0]
                    .substring(0, checkEventDetails[0].length() - 2), eventDuration);
            if (isCompleted) {
                tempEventTask.completeTask();
            }
            return tempEventTask;
        case 'D':
            String deadlineDetails = line.substring(9);
            String[] checkDeadlineDetails = deadlineDetails.split("by: ", 2);
            LocalDateTime deadlineDate = LocalDateTime.parse(checkDeadlineDetails[1]
                    .substring(0, checkDeadlineDetails[1].length() - 1), OFFICIAL_FORMAT);
            DukeDate deadlineDukeDate = new DukeDate(deadlineDate);
            Task tempDeadlineTask = new Deadline(checkDeadlineDetails[0]
                    .substring(0, checkDeadlineDetails[0].length() - 2), deadlineDukeDate);
            if (isCompleted) {
                tempDeadlineTask.completeTask();
            }
            return tempDeadlineTask;
        default:
            throw new FileWritingException();
        }
    }
}
