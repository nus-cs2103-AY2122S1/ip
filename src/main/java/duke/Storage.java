package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.contact.Contact;
import duke.task.Task;

/**
 * Storage is the class that handles reading and writing to the save file.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class Storage {
    private Scanner s;
    private final String filePath;
    private final String contactsFilePath;
    private File saveFile;
    private File contactsSaveFile;

    /**
     * Storage constructor that takes in the file path for the save file.
     *
     * @param filepath the file path of the save file.
     */
    public Storage(String filepath, String contactsFilePath) {
        this.filePath = filepath;
        this.contactsFilePath = contactsFilePath;
        this.initialiseSaveFile();
    }

    /**
     * Checks if save file exist, and if it does not, creates it.
     *
     */
    public void initialiseSaveFile() {
        this.saveFile = new File(this.filePath);
        this.contactsSaveFile = new File(this.contactsFilePath);

        try {
            if (saveFile.getParentFile().mkdir()) {
                System.out.println("Folder for task list save file created.");
            }
            if (saveFile.createNewFile()) {
                System.out.println("Task list save file created.");
            }
            if (contactsSaveFile.getParentFile().mkdir()) {
                System.out.println("Folder for contacts list save file created.");
            }
            if (contactsSaveFile.createNewFile()) {
                System.out.println("Contacts list save file created.");
            }
        } catch (IOException e) {
            throw new DukeException("File could not be found!");
        }
    }

    /**
     * Reads the save file and stores each word in an ArrayList.
     *
     * @return  An ArrayList of words in the save file.
     */
    public ArrayList<String> produceReadableString() {
        try {
            assert saveFile != null;
            s = new Scanner(saveFile);
            ArrayList<String> output = new ArrayList<>();

            while (s.hasNextLine()) {
                String thisLine = s.nextLine();
                String[] segments = thisLine.split(" \\| | ");
                for (int i = 0; i < segments.length; i++) {
                    output.add(segments[i]);
                }
            }
            s.close();

            return output;
        } catch (IOException e) {
            throw new DukeException("File could not be found!");
        }

    }

    /**
     * Reads the contacts list save file and stores each word in an ArrayList.
     *
     * @return  An ArrayList of words in the save file.
     */
    public ArrayList<String> produceReadableContactsString() {
        try {
            assert contactsSaveFile != null;
            s = new Scanner(contactsSaveFile);
            ArrayList<String> output = new ArrayList<>();

            while (s.hasNextLine()) {
                String thisLine = s.nextLine();
                output.add(thisLine);
            }
            s.close();

            return output;
        } catch (IOException e) {
            throw new DukeException("File could not be found!");
        }

    }

    /**
     * Updates the save file's list of tasks.
     *
     */
    public void updateSaveFile(TaskList tl) {
        try {
            FileWriter writer = new FileWriter("saves/saves.txt");
            System.out.println("Updating Task List Save File");

            for (int i = 0; i < tl.numberOfTasks(); i++) {
                Task thisTask = tl.getTask(i);
                writer.write(thisTask.getReadableString());
            }

            writer.close();

        } catch (Exception e) {
            throw new DukeException("File could not be found!");
        }
    }

    /**
     * Updates the save file's list of tasks.
     *
     */
    //TODO
    public void updateContactsSaveFile(ContactsList cl) {
        try {
            FileWriter writer = new FileWriter("saves/contacts.txt");
            System.out.println("Updating Contacts List Save File");

            for (int i = 0; i < cl.numberOfContacts(); i++) {
                Contact thisContact = cl.getContact(i);
                writer.write(thisContact.getReadableString());
                writer.write("\n");
            }

            writer.close();

        } catch (Exception e) {
            throw new DukeException("File could not be found!");
        }
    }
}
