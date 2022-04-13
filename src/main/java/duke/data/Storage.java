package duke.data;

import duke.information.Contact;
import duke.information.Deadline;
import duke.information.Event;
import duke.information.Task;
import duke.information.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Scanner;

/**
 * Class that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    /** File containing the user's saved tasks. */
    private File userData;
    /** Path to locate the save file. */
    private String pathname;

    /**
     * Constructs Storage class that contains the user's save file.
     *
     * @param pathname Path to locate the save file.
     */
    public Storage(String pathname) {
        this.pathname = pathname;
        try {
            userData = new File(pathname);
            userData.getParentFile().mkdirs();
            userData.createNewFile();
            assert userData != null : "User's file data should not be null!";
        } catch (IOException e) {
            System.out.println("File not created");
        }
    }

    /**
     * Returns an InformationList containing the user's saved tasks and contacts.
     * If user has no saved tasks nor contacts, returns an empty InformationList.
     *
     * @return An InformationList.
     * @throws DukeException If file is not found or file path cannot be accessed.
     */
    public InformationList load() throws DukeException {
        InformationList userDataList = new InformationList();
        try {
            Scanner sc = new Scanner(userData);
            while (sc.hasNextLine()) {
                String[] newTaskArray = sc.nextLine().split("\\|");
                assert newTaskArray.length > 3 : "Invalid data found in file!";
                Task newTask;
                String taskType = newTaskArray[0];
                if (taskType.equals("C")) {
                    userDataList.addContact(new Contact(newTaskArray[1], newTaskArray[2]));
                    continue;
                }
                boolean isDone = newTaskArray[1].equals("1");
                switch (taskType) {

                case "D":
                    newTask = loadDeadline(newTaskArray);
                    break;

                case "E":
                    newTask = new Event(newTaskArray[2], newTaskArray[3]);
                    break;

                default:
                    newTask = new ToDo(newTaskArray[2]);
                }
                if (isDone) {
                    newTask.markAsDone();
                }
                userDataList.addTask(newTask);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Save file is not found");
        }
        return userDataList;
    }

    /**
     * Loads a Deadline from a txt file based on the format of the date and time.
     * To be used when loading deadlines in the load method.
     *
     * @param newTaskArray Information of a deadline stored in an array.
     * @return A deadline based on the information in the array.
     */
    public Deadline loadDeadline(String[] newTaskArray) {
        int maximumYearMonthLength = 7; //eg: MM/yyyy
        int maximumLocalDateLength = 10; //eg: dd-MM-yyyy
        if (newTaskArray[3].length() <= maximumYearMonthLength) {
            return new Deadline(newTaskArray[2],YearMonth.parse(newTaskArray[3]));
        } else if (newTaskArray[3].length() <= maximumLocalDateLength) {
            return new Deadline(newTaskArray[2],LocalDate.parse(newTaskArray[3]));
        } else {
            return new Deadline(newTaskArray[2],LocalDateTime.parse(newTaskArray[3]));
        }
    }

    /**
     * Updates the USERDATA.TXT to reflect changes that the user made.
     *
     * @param listOfUserInformation Updated InformationList.
     */
    public void save(InformationList listOfUserInformation) {
        try {
            FileWriter newFile = new FileWriter(pathname);
            for (int j = 0; j < listOfUserInformation.getTasksSize(); j++) {
                newFile.write(listOfUserInformation.getTask(j).toData());
            }
            for (int i = 0; i < listOfUserInformation.getContactsSize(); i++) {
                newFile.write(listOfUserInformation.getContact(i).toData());
            }
            newFile.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
