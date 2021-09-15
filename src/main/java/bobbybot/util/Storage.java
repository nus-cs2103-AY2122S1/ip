package bobbybot.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import bobbybot.exceptions.InvalidSaveFileException;
import bobbybot.person.Address;
import bobbybot.person.Email;
import bobbybot.person.Name;
import bobbybot.person.Person;
import bobbybot.person.Phone;
import bobbybot.tasks.Deadline;
import bobbybot.tasks.Event;
import bobbybot.tasks.Task;
import bobbybot.tasks.ToDo;

/**
 * Represents the storage of data needed to load tasks
 */
public class Storage {

    private static final DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm");
    private final String tasksFilePath;
    private final String contactsFilePath;
    private final String dirPath = "data";

    /**
     * Constructs storage object with file paths of storage and task .txt file
     * @param tasksFilePath file path for tasks
     * @param contactsFilePath file path for contacts
     */
    public Storage(String tasksFilePath, String contactsFilePath) {
        this.tasksFilePath = tasksFilePath;
        this.contactsFilePath = contactsFilePath;
    }

    /**
     * Constructs storage object with file path for tasks only
     * @param tasksFilePath file path for tasks
     */
    public Storage(String tasksFilePath) {
        this.tasksFilePath = tasksFilePath;
        this.contactsFilePath = "contactsData.txt";
    }

    /**
     * Loads tasks to .txt file
     * @return List of tasks
     */
    public List<Task> loadTasks() {
        File f = new File(tasksFilePath);
        File dir = new File(dirPath);
        if (!dir.isDirectory()) {
            dir.mkdir();
        }
        if (!f.isFile()) {
            createNewDataFile();
        }
        try {
            Scanner s = new Scanner(f);
            List<Task> tasks = new ArrayList<>();
            while (s.hasNext()) {
                String[] row = s.nextLine().split(",");
                // load row into task
                switch (row[0]) {
                // data format: [type],[isDone],[desc],[period]
                case "T":
                    // load task
                    tasks.add(new ToDo(row[2], row[1].equals("1")));
                    break;
                case "D":
                    // load deadline
                    tasks.add(new Deadline(row[2], row[3], row[1].equals("1"), DT_FORMATTER));
                    break;
                case "E":
                    // load event
                    tasks.add(new Event(row[2], row[3], row[1].equals("1")));
                    break;
                default:
                    System.out.println("Invalid task format");
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println("Failed to load from save file");
            return Collections.emptyList();
        }
    }

    /**
     * Loads contacts to .txt file
     * @return
     */
    public List<Person> loadContacts() {
        File f = new File(contactsFilePath);
        File dir = new File(dirPath);
        if (!dir.isDirectory()) {
            dir.mkdir();
        }
        if (!f.isFile()) {
            createNewDataFile();
        }
        try {
            Scanner s = new Scanner(f);
            List<Person> contacts = new ArrayList<>();
            while (s.hasNext()) {
                String[] row = s.nextLine().split(";");
                Name name = new Name(row[0]);
                Phone phone = new Phone(row[1]);
                Email email = new Email(row[2]);
                Address address = new Address((row[3]));
                contacts.add(new Person(name, email, phone, address));
            }
            return contacts;
        } catch (FileNotFoundException e) {
            System.out.println("Failed to load from save file");
            return Collections.emptyList();
        }
    }

    /**
     * Saves all tasks in .txt file
     * @param tasks tasks to save
     * @throws InvalidSaveFileException exception thrown when unable to save file
     */
    public void save(TaskList tasks) throws InvalidSaveFileException {
        // save task to .txt file
        try {
            FileWriter fw = new FileWriter(tasksFilePath);
            for (Task task : tasks.getTasks()) {
                String saveRow = task.getSaveFormatString() + "\n";
                fw.write(saveRow);
            }
            fw.close();
        } catch (IOException e) {
            throw new InvalidSaveFileException("The save file is not accessible!");
        }
    }


    /**
     * Saves all tasks in .txt file
     * @param contacts contacts to save
     * @throws InvalidSaveFileException exception thrown when unable to save file
     */
    public void save(PersonList contacts) throws InvalidSaveFileException {
        // save task to .txt file
        try {
            FileWriter fw = new FileWriter(contactsFilePath);
            for (int i = 0; i < contacts.size(); i++) {
                String saveRow = contacts.getContact(i).getSaveFormatString() + "\n";
                fw.write(saveRow);
            }
            fw.close();
        } catch (IOException e) {
            throw new InvalidSaveFileException("The save file is not accessible!");
        }
    }
    /**
     * Creates new .txt file to store tasks at filepath
     */
    public void createNewDataFile() {
        File taskFile = new File(tasksFilePath);
        File storageFile = new File(contactsFilePath);
        try {
            taskFile.createNewFile();
            storageFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Could not create new text file: " + tasksFilePath);
            System.exit(0);
        }
    }
}

