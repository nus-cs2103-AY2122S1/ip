package viper;

import exceptions.DukeException;
import tasks.Deadlines;
import tasks.Events;
import tasks.Task;
import tasks.Todos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    
    private final String filePath;
    private File file;
    
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = new File(filePath);
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    /**
     * Reads lines from txt file, convert to respective tasks and add to task list.
     * @return list of Tasks.
     * @throws FileNotFoundException If txt file does not exist.
     * @throws DukeException If line read is missing any information.
     * @throws ParseException If string cannot be parsed to date.
     */
    public ArrayList<Task> loadTasks() throws FileNotFoundException, DukeException, ParseException {
        ArrayList<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(file);
        
        while (sc.hasNextLine()) {
            String readStr = sc.nextLine();
            String[] readLineArray = readStr.split("]", 3);
            String taskInstruction = readLineArray[0].substring(1);
            String taskIsDone = readLineArray[1].substring(1);
            String taskDesc = readLineArray[2].trim();
            
            if (taskDesc.isBlank()) {
                throw new DukeException("Task description cannot be blank!!");
            }
            switch (taskInstruction) {
            case "T":
                Todos addTodo = new Todos(taskDesc);
                if (!taskIsDone.isBlank()) {
                    addTodo.setDone();
                }
                taskList.add(addTodo);
                break;
            case "D":
                String[] descDateArray = taskDesc.split("\\(by: ");
                
                //convert date to the correct input format
                DateFormat inputDeadlineDateFormat = new SimpleDateFormat("MMM dd yyyy");
                Date inputDeadlineDate = inputDeadlineDateFormat.parse(descDateArray[1]);
                DateFormat outputDeadlineDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String outputDeadlineDate = outputDeadlineDateFormat.format(inputDeadlineDate);
                
                Deadlines addDeadline = new Deadlines(descDateArray[0].trim(), outputDeadlineDate);
                if (!taskIsDone.isBlank()) {
                    addDeadline.setDone();
                }
                taskList.add(addDeadline);
                break;
            case "E":
                String[] eventDescTimeArray = taskDesc.split(" \\(at: ");
                int colonIndex = eventDescTimeArray[1].indexOf(":");
                String dateStr = eventDescTimeArray[1].substring(0, colonIndex - 2);
                String timeStr = eventDescTimeArray[1].substring(colonIndex - 2, colonIndex + 3);
                
                System.out.println("date: " + dateStr);
                System.out.println("time: " + timeStr);
                
                //convert date to the correct input format
                DateFormat inputEventDateFormat = new SimpleDateFormat("MMM dd yyyy");
                Date inputEventDate = inputEventDateFormat.parse(dateStr.trim());
                DateFormat outputEventDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String outputEventDate = outputEventDateFormat.format(inputEventDate);
                
                Events addEvent = new Events(eventDescTimeArray[0].trim(), outputEventDate, timeStr.trim());
                
                if (!taskIsDone.isBlank()) {
                    addEvent.setDone();
                }
                taskList.add(addEvent);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + taskInstruction);
            }
        }
        return taskList;
    }

    /**
     * Adds a line in the txt file.
     * @param task Task to be added.
     * @throws IOException If the task is invalid.
     */
    public void writeTask(Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(task.toString() + System.lineSeparator());
        fw.close();
    }

    /**
     * Removes line in the txt file.
     * @param task Task to be deleted.
     * @throws IOException If the task is invalid.
     */
    public void deleteTask(Task task) throws IOException {
        File tempFile = File.createTempFile("temp", ".txt", file.getParentFile());

        FileWriter fw = new FileWriter(tempFile, true);
        Scanner sc = new Scanner(file);
        String lineDone = task.toString();
        
        while(sc.hasNextLine()) {
            String currLine = sc.nextLine();
            if (currLine.equals(lineDone)) {
                fw.write("");
            } else {
                fw.write(currLine + System.lineSeparator());
            }
        }
        
        fw.close();
        boolean success = tempFile.renameTo(file);
    }

    /**
     * Marks task as done.
     * @param task Task to be marked done.
     * @throws IOException If the task is invalid.
     */
    public void doneTask(Task task) throws IOException {
        File tempFile = File.createTempFile("temp", ".txt", file.getParentFile());
        
        FileWriter fw = new FileWriter(tempFile, true);
        Scanner sc = new Scanner(file);
        String lineDone = task.toString();
        
        while(sc.hasNextLine()) {
            String currLine = sc.nextLine();
            if (currLine.equals(lineDone)) {
                task.setDone();
                fw.write(task + System.lineSeparator());
            } else {
                fw.write(currLine + System.lineSeparator());
            }
        }
        
        fw.close();
        boolean success = tempFile.renameTo(file);
    }
       
}
