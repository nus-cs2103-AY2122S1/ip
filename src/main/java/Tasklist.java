import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: Level 7. Save
 *
 * Description:
 * Encapsulates the TaskList which contains a list of tasks
 *
 * @author Keith Tan
 */
public class Tasklist {

    private ArrayList<Task> tasks;

    public Tasklist() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Checks whether user inputted the time for the deadline/event task
     *
     * @param strArr String array containing the inputted description and time
     * @param event String stating the type of task to be added
     * @return String Returns the time of the task
     * @throws MissingArgumentException throws a MissingArgumentException if no time found
     */
    private static String checkTime(String[] strArr, String event) throws MissingArgumentException {

        if (strArr.length < 2) {
            throw new MissingArgumentException("time", event);
        } else if (strArr[1].trim().isEmpty()) {
            throw new MissingArgumentException("time", event);
        } else {
            return strArr[1];
        }

    }

    /**
     * Adds the task to the list
     *
     * @param description String containing the description and time of the task
     * @param task Command stating the type of task to be added
     * @return String Returns the success message of added the task to the list
     * @throws DukeException throws a duke exception depending on the error found
     */
    public String addTask(String description, Command task) throws DukeException, IOException {

        Task newTask;
        String taskType = Command.changeToString(task);
        switch(taskType) {
            case "todo":
                newTask = new ToDo(description);
                addString(description, task);
                break;
            case "deadline":
                String[] deadlineDetails = description.split(" /by ", 2);
                String deadlineTime = checkTime(deadlineDetails, "deadline");
                addString(deadlineDetails[0], task, deadlineTime);
                newTask = new Deadline(deadlineDetails[0], deadlineTime);
                break;
            case "event":
                String[] eventDetails = description.split(" /at ", 2);
                String eventTime =  checkTime(eventDetails, "event");
                addString(eventDetails[0], task, eventTime);
                newTask = new Event(eventDetails[0], eventTime);
                break;
            default:
                //unexpected error occurs
                throw new InvalidCommandException();
        }

        tasks.add(newTask);
        String successMessage = "Got it. I've added this task:\n"
                + "  " + newTask.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
        return successMessage;
    }

    /**
     * Marks the designated tasks as complete
     *
     * @param taskNumber task to be marked as complete
     * @return String message to be printed depending on if tasks is marked
     * @throws IndexOutOfRangeException throws the exception if index given is out of
     *              range of the current list
     */
    public String markTask(int taskNumber) throws IndexOutOfRangeException, IOException {

        if (taskNumber > tasks.size()) {

            throw new IndexOutOfRangeException(taskNumber, tasks.size() );

        } else {

            if (tasks.get(taskNumber - 1).completeTask()) {

                String markTaskMessage = "Nice! I've marked this task as done:\n"
                        + tasks.get(taskNumber - 1).toString();
                markStringFile(taskNumber - 1);
                return markTaskMessage;

            } else {

                String markTaskMessage = tasks.get(taskNumber - 1).toString() + " already marked!";
                return markTaskMessage;

            }

        }

    }

    /**
     * Delete the designated tasks from the task list
     *
     * @param taskNumber task to be deleted
     * @return String message to be printed depending on if tasks is marked
     * @throws IndexOutOfRangeException throws the exception if index given is out of
     *              range of the current list
     */
    public String deleteTask(int taskNumber) throws IndexOutOfRangeException, IOException {

        if (taskNumber> tasks.size()) {

            throw new IndexOutOfRangeException(taskNumber, tasks.size());

        } else {

            Task taskToRemove = tasks.get(taskNumber - 1);
            tasks.remove(taskNumber - 1);
            deleteLine(taskNumber);
            String deletedTaskMessage = "Noted. I've removed this task:\n"
                    + "  " + taskToRemove.toString() + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.";;
            return deletedTaskMessage;

        }

    }

    /**
     * Saves the newly added task to the hard disk
     *
     * @param taskName String containing the description of the task
     * @param event Command stating the type of task added
     * @throws IOException throws an IOException if error encountered during writing of new String to hard drive
     */
    public void addString(String taskName, Command event) {
        try {
            FileWriter fw = new FileWriter("/Users/keithtan/Desktop/NUS/CS2103 IP/ip/data/duke.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            String eventString = "";
            switch(event) {
                case DEADLINE:
                    eventString = "D";
                    break;
                case TODO:
                    eventString = "T";
                    break;
                case EVENT:
                    eventString = "E";
                    break;
            }
            String taskRecord = eventString + " | 0 | " + taskName;
            bw.write(taskRecord);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a specific line from list of tasks in hard drive
     *
     * @param lineToRemove index of line to be removed
     * @throws IOException throws an IOException if error encountered during deleting of desired line in hard drive
     */
    public void deleteLine(int lineToRemove) throws IOException {
        File inputFile = new File("/Users/keithtan/Desktop/NUS/CS2103 IP/ip/data/duke.txt");
        File tempFile = new File("/Users/keithtan/Desktop/NUS/CS2103 IP/ip/data/duketemp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;
        int count = 0;

        while ((currentLine = reader.readLine()) != null) {
            count++;
            if (count == lineToRemove) {
                continue;
            }
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    /**
     * Saves the newly added task to the hard disk
     *
     * @param taskName String containing the description of the task
     * @param event Command stating the type of task added
     * @param time time of the deadline/event
     * @throws IOException throws an IOException if error encountered during writing of new String to hard drive
     */
    public void addString(String taskName, Command event, String time) {
        try {
            FileWriter fw = new FileWriter("/Users/keithtan/Desktop/NUS/CS2103 IP/ip/data/duke.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            String eventString = "";
            switch(event) {
                case DEADLINE:
                    eventString = "D";
                    break;
                case TODO:
                    eventString = "T";
                    break;
                case EVENT:
                    eventString = "E";
                    break;
            }
            String taskRecord = eventString + " | 0 | " + taskName + " | " + time;
            bw.write(taskRecord);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Marks a specific tasks from list of tasks in hard drive as done
     *
     * @param lineNumber index of line to be marked
     * @throws IOException throws an IOException if error encountered during marking of desired tasks in hard drive
     */
    public void markStringFile(int lineNumber) throws IOException {

        Path path = Paths.get("/Users/keithtan/Desktop/NUS/CS2103 IP/ip/data/duke.txt");
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        String oldLine = lines.get(lineNumber);
        String newLine = oldLine.substring(0, 4) + "1" + oldLine.substring(5);
        lines.set(lineNumber, newLine);
        Files.write(path, lines, StandardCharsets.UTF_8);

    }

    @Override
    public String toString() {

        String listString = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            String listItem = String.format("%d.%s", (i + 1), tasks.get(i).toString());
            if (i == (tasks.size() - 1)) {
                listString += listItem;
            } else {
                listString += listItem + "\n";
            }

        }
        return listString;

    }

}
