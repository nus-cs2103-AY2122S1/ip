package duke.storage;

import duke.commands.Command.CommandType;
import duke.parser.CustomDateFormatter;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File storageFile;

    public Storage(String filePath) {
        this.storageFile = new File(filePath);
    }

    public void editStorage(int lineNumber, CommandType commandType) {
        int actualLineNumber = lineNumber + 1;
        try {
            Scanner myScanner = new Scanner(storageFile);
            String newFileString = "";
            int currentLine = 1;
            while (myScanner.hasNextLine()) {
                String nextLine = myScanner.nextLine();
                if (currentLine == actualLineNumber) {
                    switch (commandType) {
                        case DONE:
                            char[] doneArray = nextLine.toCharArray();
                            doneArray[4] = '1';
                            newFileString += String.valueOf(doneArray) + "\n";
                            break;
                        case UNDO:
                            char[] undoArray = nextLine.toCharArray();
                            undoArray[4] = '0';
                            newFileString += String.valueOf(undoArray) + "\n";
                            break;
                        case DELETE:
                            newFileString += "";
                            break;
                        default: newFileString += "";
                    }
                } else {
                    newFileString += nextLine + "\n";
                }
                currentLine += 1;
            }
            myScanner.close();
            if (newFileString.endsWith("\n")) {
                newFileString = newFileString.substring(0, newFileString.length() - 1);
            }
            FileWriter myWriter = new FileWriter(storageFile);
            myWriter.write(newFileString);
            myWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException caught~");
            e.printStackTrace();
        }
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> newArrayList = new ArrayList<>(100);
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
                Deadline newDeadline = new Deadline(deadlineArray[2], CustomDateFormatter.getLocalDateFromString(deadlineArray[3]));
                if (deadlineArray[1].equals("1")) {
                    newDeadline.markDone();
                }
                return newDeadline;
            case 'E':
                String[] eventArray = task.split("_~_");
                Event newEvent = new Event(eventArray[2], CustomDateFormatter.getLocalDateFromString(eventArray[3]));
                if (eventArray[1].equals("1")) {
                    newEvent.markDone();
                }
                return newEvent;
            default:
                return new ToDo("Please delete this entry. I am a bug!");
        }

    }

    public void addToStorage(Task task) {
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
