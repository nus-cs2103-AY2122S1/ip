package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.commands.Command.CommandType;
import duke.parser.CustomDateFormatter;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class Storage {

    private String storageFilePath;

    /**
     * Constructor for a Storage object.
     *
     * @param filePath
     */
    public Storage(String filePath) {
        this.storageFilePath = filePath;
    }

    /**
     * This method edits the file stored locally to edit mark as done,
     * undo or delete the Task specified by the lineNumber.
     *
     * @param lineNumber The line on which the Task is to be edited
     * @param commandType The type of edit to be made
     */
    public void editStorage(int lineNumber, CommandType commandType) {
        int actualLineNumber = lineNumber + 1;
        File storageFile = new File(storageFilePath);
        try {
            Scanner myScanner = new Scanner(storageFile);
            String newFileString = editStorageScanner(
                    myScanner,
                    actualLineNumber,
                    commandType
            );
            myScanner.close();
            writeToFile(newFileString);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }
    }

    private String editStorageScanner(Scanner myScanner, int lineNumber, CommandType commandType) {
        int currentLine = 1;
        String newFileString = "";
        while (myScanner.hasNextLine()) {
            String nextLine = myScanner.nextLine();
            if (currentLine == lineNumber) {
                switch (commandType) {
                case DONE:
                    char[] doneArray = nextLine.toCharArray();
                    doneArray[4] = '1';
                    newFileString += addParagraph(String.valueOf(doneArray));
                    break;
                case UNDO:
                    char[] undoArray = nextLine.toCharArray();
                    undoArray[4] = '0';
                    newFileString += addParagraph(String.valueOf(undoArray));
                    break;
                case DELETE:
                    // Fallthrough
                default:
                    newFileString += "";
                }
            } else {
                newFileString += addParagraph(nextLine);
            }
            currentLine += 1;
        }
        if (newFileString.endsWith("\n")) {
            newFileString = newFileString.substring(0, newFileString.length() - 1);
        }
        return newFileString;
    }

    private String addParagraph(String str) {
        return str + "\n";
    }

    private void writeToFile(String newFileString) {
        File storageFile = new File(storageFilePath);
        try {
            FileWriter myWriter = new FileWriter(storageFile);
            myWriter.write(newFileString);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("IOException caught~");
            e.printStackTrace();
        }
    }

    /**
     * Writes the local file with the updated status of the
     * TaskList.
     *
     * @param taskList
     */
    public void updateWithTaskList(TaskList taskList) {
        ArrayList<Task> taskArrayList = taskList.getArrayList();
        String newFileString = "";
        for (Task task : taskArrayList) {
            String str = "%s\n";
            newFileString += String.format(str, task.getDataString());
        }
        if (newFileString.endsWith("\n")) {
            newFileString = newFileString.substring(0, newFileString.length() - 1);
        }
        writeToFile(newFileString);
    }

    /**
     * This method loads the TaskList in from the locally stored file
     * as specified by the file path passed into Duke.
     *
     * @return The ArrayList used to create the TaskList
     * @throws FileNotFoundException
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> newArrayList = new ArrayList<>(100);
        File storageFile = new File(storageFilePath);
        try {
            Scanner myScanner = new Scanner(storageFile);
            while (myScanner.hasNextLine()) {
                String nextLine = myScanner.nextLine();
                if (nextLine.length() > 0) {
                    newArrayList.add(getTask(nextLine));
                }
            }
        } catch (FileNotFoundException e) {
            throw e;
        }
        return newArrayList;
    }

    /**
     * This is mainly a helper method to help the load method
     * retrieve Tasks based on the divider.
     *
     * @param task The String version of the Task
     * @return The appropriate Task object
     */
    public static Task getTask(String task) {
        Character taskType = task.charAt(0);
        switch(taskType) {
        case 'T':
            String[] todoArray = task.split("_~_");
            ToDo newToDo = new ToDo(todoArray[2]);
            if (todoArray[1].equals("1")) {
                newToDo.markDone();
            }
            return newToDo;
        case 'D':
            String[] deadlineArray = task.split("_~_");
            Deadline newDeadline =
                new Deadline(deadlineArray[2], CustomDateFormatter.getLocalDateFromString(deadlineArray[3]));
            if (deadlineArray[1].equals("1")) {
                newDeadline.markDone();
            }
            return newDeadline;
        case 'E':
            String[] eventArray = task.split("_~_");
            Event newEvent =
                new Event(eventArray[2], CustomDateFormatter.getLocalDateFromString(eventArray[3]));
            if (eventArray[1].equals("1")) {
                newEvent.markDone();
            }
            return newEvent;
        default:
            return new ToDo("Please delete this entry. I am a bug!");
        }

    }

    /**
     * Adds the Task object to the local file as specified by the
     * constructor.
     *
     * @param task The Task object to be added to the local file
     */
    public void addToStorage(Task task) {
        File storageFile = new File(storageFilePath);
        try {
            Scanner myScanner = new Scanner(storageFile);
            if (myScanner.hasNextLine()) {
                FileWriter myWriter = new FileWriter(storageFile, true);
                myWriter.write("\n" + task.getDataString());
                myWriter.close();
            } else {
                FileWriter myWriter = new FileWriter(storageFile, true);
                myWriter.write(task.getDataString());
                myWriter.close();
            }
            myScanner.close();
        } catch (IOException e) {
            System.out.println("IOException from FileWriter caught.");
            e.printStackTrace();
        }
    }


}
