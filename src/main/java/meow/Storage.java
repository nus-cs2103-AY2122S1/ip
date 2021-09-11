package meow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a storage that deals with loading
 * tasks from the file and saving tasks in the file.
 */
public class Storage {
    protected String filePath;
    protected List<Task> tasksList = new ArrayList<>();

    /**
     * A public constructor to initialize a meow.Storage object.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private void writeToFile(String textToAdd) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFile() {
        try {
            File file = new File(this.filePath);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFolder(String folderPath) {
        File file = new File(folderPath);
        file.mkdir();
    }

    private void addFileContentToArray() throws FileNotFoundException, InvalidDateTimeException {
        File file = new File(this.filePath);
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String task = s.nextLine();
            load(task);
        }
    }

    private void load(String input) {
        final String COMPLETED = "1";

        String[] values = input.split(" \\| ");
        String typeOfTask = values[0].trim();
        assert(typeOfTask != null);
        String completeStatus = values[1].trim();
        assert(completeStatus != null);
        String task = values[2].trim();
        assert(task != null);
        switch (typeOfTask) {
        case "T":
            Todo newTodo = new Todo(task);
            if (completeStatus.equals(COMPLETED)) {
                newTodo.markAsDone();
            }
            this.tasksList.add(newTodo);
            break;
        case "D":
            String dateAndTime = values[3].trim();
            String[] dateTimeArray = dateAndTime.split(" ");
            LocalDate localDate = convertToLocalDate(dateTimeArray);
            Deadline newDeadline = new Deadline(task, localDate, dateTimeArray[3]);
            if (completeStatus.equals(COMPLETED)) {
                newDeadline.markAsDone();
            }
            this.tasksList.add(newDeadline);
            break;
        case "E":
            String eventDate = values[3].trim();
            Event newEvent = new Event(task, eventDate);
            if (completeStatus.equals(COMPLETED)) {
                newEvent.markAsDone();
            }
            this.tasksList.add(newEvent);
            break;
        default:
            break;
        }
    }

    private LocalDate convertToLocalDate(String[] dateTimeArray) {
        String deadlineDate = "";
        for (int i = 0; i < dateTimeArray.length - 1; i++) {
            if (i == 1 && Integer.parseInt(dateTimeArray[i]) < 10) {
                deadlineDate = deadlineDate + "0" + dateTimeArray[i] + " ";
            } else if (i == 2) {
                deadlineDate = deadlineDate + dateTimeArray[i];
            } else {
                deadlineDate = deadlineDate + dateTimeArray[i] + " ";
            }
        }
        LocalDate localDate = LocalDate.parse(deadlineDate, DateTimeFormatter.ofPattern("MMM d yyyy"));
        return localDate;
    }

    /**
     * Reads the local file and converts it the array, if the folder or file
     * does not exists, create an empty one.
     *
     * @return A boolean indicating whether the chat bot cat Meow is able to
     * read from the local file.
     */
    public boolean canReadFromFile() {
        File folder = new File("data");
        if (folder.isDirectory()) {
            try {
                addFileContentToArray();
                return true;
            } catch (FileNotFoundException | InvalidDateTimeException e) {
                // Create a file
                createFile();
                return false;
            }
        } else {
            // Create a folder
            createFolder("data");
            return false;
        }
    }

    /**
     * Returns a list of meow.Task objects stored in the local file.
     *
     * @return A list of meow.Task objects.
     */
    public List<Task> getStorageTasksList() {
        return this.tasksList;
    }

    /**
     * Adds all the tasks in the task list to the local file.
     *
     * @param givenTaskList The task list that is used to store
     *                      the tasks input by the user.
     */
    public void addArrayTaskToFile(List<Task> givenTaskList) {
        String addedContent = "";
        for (int i = 0; i < givenTaskList.size(); i++) {
            addedContent = addedContent + givenTaskList.get(i).toString() + System.lineSeparator();
        }
        writeToFile(addedContent);
    }
}
