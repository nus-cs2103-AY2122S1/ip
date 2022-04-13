package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * <code>Storage</code> class handles saving and loading of task list for a file
 * A Storage object is initialised with a String "path"
 * "path" represents the file directory path to the task file
 * Storage object implements two methods save(tasks) and load() which handles the saving/loading.
 */

public class Storage{

    protected final String path; //path of task file

    public Storage(String path){
        this.path = path;
    }


    /**
     * Saves an arraylist of tasks to the file at this.path
     *
     * @param tasks arraylist of task objects to be saved in the arraylist.
     * Tasks will be converted into their toString() equivalent before saving to the list.
     */


    public void save(ArrayList<Task> tasks) {
        //create a new file if not available
        try {
            File myObj = new File(this.path);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
        } catch (IOException e) {
            System.out.println("Oops! An error occurred.");
            e.printStackTrace();
        }

        //write each task to the file as a new line
        try {
            FileWriter myWriter = new FileWriter(this.path);
            for (int i = 1; i < tasks.size()+1; i++) {
                myWriter.write(i + ". " + tasks.get(i-1).toString());
                myWriter.write("\n");
            }
            myWriter.close();

        } catch (IOException e) {
            System.out.println("Oops! An error occurred.");
            e.printStackTrace();
        }
    }

    //parse description of to-do task
    private ToDo parseToDo(String line, int lineNumber){
        int startIndex = String.valueOf(lineNumber).length() + 3;

        boolean done = (line.charAt(startIndex+4) == 'X');
        String description = line.substring(startIndex+7);

        return new ToDo(description, done);
    }

    //parse description, date and time of deadline task
    private Deadline parseDeadline(String line, int lineNumber){
        int startIndex = String.valueOf(lineNumber).length() + 3;
        int endIndex = line.length()-1;

        LocalDate date;
        LocalTime time;

        String description = line.substring(startIndex+7, endIndex-22);
        boolean done = (line.charAt(startIndex+4) == 'X');

        String dateString = line.substring(endIndex-16,endIndex-6);
        String timeString = line.substring(endIndex-5, endIndex);

        date = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(dateString));
        time = LocalTime.from(DateTimeFormatter.ISO_LOCAL_TIME.parse(timeString));

        return new Deadline(description, date, time, done);
    }

    //parse description, date and start/end times of event task
    private Event parseEvent(String line, int lineNumber){
        int startIndex = String.valueOf(lineNumber).length() + 3;
        int endIndex = line.length()-1;

        LocalDate date;
        LocalTime startTime, endTime;

        boolean done = (line.charAt(startIndex+4) == 'X');
        String description = line.substring(startIndex + 7, endIndex - 31);

        String dateString = line.substring(endIndex-25, endIndex-15);
        String startTimeString = line.substring(endIndex-14, endIndex-9);
        String endTimeString = line.substring(endIndex-5,endIndex);

        date = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(dateString));
        startTime = LocalTime.from(DateTimeFormatter.ISO_LOCAL_TIME.parse(startTimeString));
        endTime = LocalTime.from(DateTimeFormatter.ISO_LOCAL_TIME.parse(endTimeString));

        return new Event(description, date, startTime, endTime, done);
    }
    /**
     * loads file at this.path, converts each line into a Task object (to do, deadline or event)
     * using the respective helper method.
     * then inserts task into an ArrayList of Tasks to be used for TaskList object.
     *
     * @return ArrayList of Tasks loaded from file
     */

    public ArrayList<Task> load() {
        ArrayList<Task> loadedList = new ArrayList<>();
        BufferedReader reader;
        int lineNumber = 1;

        try {
            reader = new BufferedReader(new FileReader(this.path));
            String line = reader.readLine();

            while (line != null) {
                int startIndex = String.valueOf(lineNumber).length() + 3;

                if (line.charAt(startIndex) == 'T') {
                    loadedList.add(parseToDo(line,lineNumber));
                } else if (line.charAt(startIndex) == 'D') {
                    loadedList.add(parseDeadline(line,lineNumber));
                } else {
                    loadedList.add(parseEvent(line,lineNumber));
                }
                //read next line
                line = reader.readLine();
                lineNumber++;
            }
            System.out.println("Successfully loaded task list from file!");
            reader.close();
        } catch (Exception e) {
            System.out.println("Oops! I couldn't find or parse the task list file.\n"
                    + "Starting over with a brand new list.");
        }
        return loadedList;
    }
}