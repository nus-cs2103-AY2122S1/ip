package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import duke.exception.DukeException;
import duke.task.Task;
import duke.ui.Ui;




/**
 * Stores in real-time the edits to the TaskList directly into the destination text file.
 */
public class Storage {
    private static ArrayList<String> history;
    private static ArrayList<Task> deleted;
    private String filepath;

    /**
     * Creates new Storage object.
     *
     * @param filepath Filepath of text file to store information in.
     */
    public Storage(String filepath) {
        assert filepath != null;
        this.filepath = filepath;
        this.history = new ArrayList<>();
        this.deleted = new ArrayList<>();
        makeFile();
        load();
    }

    /**
     * Stores command into history ArrayList.
     *
     * @param command Command to be stored.
     */
    public void storeHistory(String command) {
        history.add(command);
    }

    /**
     * Gets number of items in history.
     *
     * @return size of history ArrayList.
     */
    public int historySize() {
        int len = this.history.size();
        if (len == 0) {
            throw new DukeException("noCommandsHaveBeenMade");
        }
        return len;
    }

    /**
     * Gets particular String in history.
     *
     * @param index Index of that particular String.
     * @return String at that index in history.
     */
    public String getHistoryAt(int index) {
        return this.history.get(index);
    }

    /**
     * Deletes last item in history ArrayList.
     */
    public static void deleteLastCommand() {
        int index = history.size() - 1;
        history.remove(index);
    }

    /**
     * Gets the last command in history ArrayList.
     *
     * @return Command last added into history.
     */
    public static String getLastCommand() {
        int index = history.size() - 1;
        if (index < 0) {
            throw new DukeException("noChangesToUndo");
        }
        return history.get(index);
    }

    /**
     * Stores recently deleted task into ArrayList.
     *
     * @param task Task that was recently deleted.
     */
    public static void storeDeleted(Task task) {
        deleted.add(task);
    }

    /**
     * Gets the most recently deleted task.
     *
     * @return Most recently deleted task.
     */
    public static Task getDeleted() {
        return deleted.get(deleted.size() - 1);
    }

    /**
     * Removes most recently deleted task from ArrayList.
     */
    public static void removeDeletedTask() {
        int index = deleted.size() - 1;
        deleted.remove(index);
    }

    /**
     * Creates a folder and destination file if either of them do not exist.
     */
    public void makeFile() {
        File textFile = new File(filepath);
        Path path = Paths.get("data");
        try {
            if (!Files.isDirectory(path)) {
                Files.createDirectories(path);
            } else if (!textFile.exists()) {
                textFile.createNewFile();
            }
        } catch (IOException e) {
            Ui.display(e.getMessage());
        }
    }

    /**
     * Reads the text file and stores the String commands line by line into an array.
     *
     * @return String array containing commands separated by lines.
     */
    public String[] load() {
        File textFile = new File("data/duke.txt");
        String[] temp = new String[50];
        try {
            loadHelper(textFile, temp);
        } catch (FileNotFoundException e) {
            Ui.display(e.getMessage());
        }
        return temp;
    }

    private void loadHelper(File textFile, String[] temp) throws FileNotFoundException {
        int counter = 0;
        Scanner s = new Scanner(textFile);
        while (s.hasNext()) {
            String command = s.nextLine();
            temp[counter] = command;
            counter++;
        }
        s.close();
    }

    /**
     * Appends changes to the text file.
     *
     * @param path Path to the text file.
     * @param textToAppend Text to append to the file.
     * @throws IOException If file at specified path does not exist.
     */
    public void appendToFile(String path, String textToAppend) throws IOException {
        File f = new File(path);
        FileWriter fw = new FileWriter(path, true); // create a FileWriter in append mode
        if (f.length() == 0) {
            fw.write(textToAppend);
        } else {
            fw.write(System.lineSeparator() + textToAppend);
        }
        fw.close();
    }

    /**
     * Edits the contents of the file to account for a current task being marked as completed.
     *
     * @param taskNum Task number of the task that is to be marked as completed.
     */
    public void editFileContentsForCompletion(int taskNum) {
        File temp = new File("data/temp.txt");
        if (!temp.exists()) {
            try {
                Files.copy(Paths.get("data/duke.txt"), Paths.get("data/temp.txt"), REPLACE_EXISTING);
                new FileWriter("data/duke.txt", false).close();
                editFileContentsForCompletionHelper(taskNum);
                Files.delete(Paths.get("data/temp.txt"));
            } catch (IOException e) {
                Ui.display(e.getMessage());
            }
        }
    }

    private void editFileContentsForCompletionHelper(int taskNum) throws IOException {
        Scanner s = new Scanner(new File("data/temp.txt"));
        int count = 1;
        while (s.hasNextLine()) {
            String command = s.nextLine();
            if (count == taskNum) {
                String head = command.substring(0, 4);
                String tail = command.substring(5);
                appendToFile("data/duke.txt", head + "1" + tail);
            } else {
                appendToFile("data/duke.txt", command);
            }
            count++;
        }
        s.close();
    }

    /**
     * Edits the contents of the file to account for a task being deleted.
     *
     * @param taskNum Task number of the task that is to be deleted.
     */
    public void editFileContentsForDeletion(int taskNum) {
        File temp = new File("data/temp.txt");
        if (!temp.exists()) {
            try {
                Files.copy(Paths.get("data/duke.txt"), Paths.get("data/temp.txt"), REPLACE_EXISTING);
                new FileWriter("data/duke.txt", false).close();
                editFileContentsForDeletionHelper(taskNum);
                Files.delete(Paths.get("data/temp.txt"));
            } catch (IOException e) {
                Ui.display(e.getMessage());
            }
        }
    }

    private void editFileContentsForDeletionHelper(int taskNum) throws IOException {
        Scanner s = new Scanner(new File("data/temp.txt"));
        int count = 1;
        while (s.hasNextLine()) {
            String command = s.nextLine();
            if (count == taskNum) {
            } else {
                appendToFile("data/duke.txt", command);
            }
            count++;
        }
        s.close();
    }

    /**
     * Edits text file to undo completion command.
     *
     * @param taskNum Task Number to carry out operation on.
     */
    public void editFileContentsForUndoCompletion(int taskNum) {
        File temp = new File("data/temp.txt");
        if (!temp.exists()) {
            try {
                Files.copy(Paths.get("data/duke.txt"), Paths.get("data/temp.txt"), REPLACE_EXISTING);
                new FileWriter("data/duke.txt", false).close();
                editFileContentsForUndoCompletionHelper(taskNum);
                Files.delete(Paths.get("data/temp.txt"));
            } catch (IOException e) {
                Ui.display(e.getMessage());
            }
        }
    }

    private void editFileContentsForUndoCompletionHelper(int taskNum) throws IOException {
        Scanner s = new Scanner(new File("data/temp.txt"));
        int count = 1;
        while (s.hasNextLine()) {
            String command = s.nextLine();
            if (count == taskNum) {
                String head = command.substring(0, 4);
                String tail = command.substring(5);
                appendToFile("data/duke.txt", head + "0" + tail);
            } else {
                appendToFile("data/duke.txt", command);
            }
            count++;
        }
        s.close();
    }
}
