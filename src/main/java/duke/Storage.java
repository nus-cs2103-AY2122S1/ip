package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    protected String filePath;

    /**
     * Creates the 'data' directory and 'jarvis.txt' file in user's hard disk to store user's
     * list of tasks (if the directory and file don't already exit)
     * @param filePath the relative path to location in which the file is to be created
     */
    public Storage(String filePath) {
        this.filePath = filePath; //Format: directory/file
        try {
            StringBuilder dirName = new StringBuilder();
            int index = 0;
            //Extracting the directory and file name
            while (index < filePath.length() && !filePath.substring(index).startsWith("/")) {
                assert !(index == filePath.length() - 1): "Filepath is invalid";

                dirName.append(filePath.charAt(index));
                index++;
            }
            //If directory and file name can be extracted successfully
            String txtFileName = filePath.substring(index + 1);
            File dir = new File(dirName.toString());
            dir.mkdirs();
            File tmp = new File(dir, txtFileName);
            tmp.createNewFile();

        } catch (IOException e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the list of tasks from the user's hard disk and displays it to the user
     * @throws FileNotFoundException if the file ('jarvis.txt') containing the list of tasks
     * cannot be found
     */
    public void retrieveFileContents() throws FileNotFoundException {
        File f = new File(this.filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        //If retrieving user tasks
        if (this.filePath.equals("data/jarvis.txt")) {
            while (s.hasNext()) {
                String currLine = s.nextLine();
                currLine = currLine.substring(2);
                //If the task is a todo task
                if (currLine.startsWith("[T]")) {
                    String description = " " + currLine.substring(7);
                    Todo newTodo = new Todo(description);
                    TaskList.addTask(newTodo);
                    if (currLine.charAt(4) == 'X') {
                        newTodo.markAsDone();
                    }
                //If the task is a deadline task
                } else if (currLine.startsWith("[D]")) {
                    int currIndex = 7;

                    //Find 'by: ' which separated the task description and the deadline
                    while (!currLine.substring(currIndex).startsWith("by: ")) {
                        assert !(currIndex == filePath.length() - 1) : "Filepath is invalid"; //If 'by: ' not found
                        currIndex++;
                    }

                    String description = " " + currLine.substring(7, currIndex - 2);
                    String by = currLine.substring(currIndex + 4, currLine.length() - 1);
                    Task newDeadline = new Deadline(description, by);
                    TaskList.addTask(newDeadline);
                    if (currLine.charAt(4) == 'X') {
                        newDeadline.markAsDone();
                    }
                //If the task is an event task
                } else if (currLine.startsWith("[E]")) {
                    int currIndex = 7;

                    //Find 'at: ' which separates the task description and timings
                    while (!currLine.substring(currIndex).startsWith("at: ")) {
                        assert !(currIndex == filePath.length() - 1) : "Filepath is invalid"; //If 'at: ' not found
                        currIndex++;
                    }

                    String description = " " + currLine.substring(7, currIndex - 2);
                    String by = currLine.substring(currIndex + 4, currLine.length() - 1);
                    Task newEvent = new Event(description, by);
                    TaskList.addTask(newEvent);
                    if (currLine.charAt(4) == 'X') {
                        newEvent.markAsDone();
                    }
                }
            }
        //If retrieving user notes
        } else if (this.filePath.equals("data/notes.txt")) {
            while (s.hasNext()) {
                String currLine = s.nextLine();
                currLine = currLine.substring(2);

                String title = "";
                String body;

                int index = 0;
                //Finding the ':' that separates the note title and body
                while (index < currLine.length() &&
                        !currLine.substring(index).startsWith(":")) {
                    assert !(index == filePath.length() - 1) : "Filepath is invalid"; //If ':' not found
                    title += currLine.substring(index, index + 1);
                    index++;
                }
                //If title and body can be extracted successfully
                body = currLine.substring(index + 1);
                Note newNote = new Note(title, body);
                NoteList.addNote(newNote);
            }
        }
    }

    /**
     * Writes to the file in user's hard disk that stores a list of tasks or to overwrite
     * the contents of this file
     *
     * @param filePath the relative path to the file
     * @param textToAdd the content that is to be written
     * @throws IOException if there is an error in writing to/overwriting the file
     */
    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Appends content to the file in user's hard disk
     *
     * @param filePath the relative path to the file
     * @param textToAppend the content that is to be appended
     * @throws IOException if there is an error in appending to existing content of the file
     */
    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Appends a task to the end of a task file stored in the user's hard disk
     *
     * @throws IOException if there is an error in appending to existing content of the file
     */
    public static void appendToTaskFile() throws IOException {
        Storage.appendToFile("data/jarvis.txt", (TaskList.getCounter()) + "." +
                TaskList.getTaskList().get(TaskList.getCounter() - 1).toPrintToFile()
                + System.lineSeparator());
    }

    /**
     * Appends a task to the end of a task file stored in the user's hard disk
     *
     * @throws IOException if there is an error in appending to existing content of the file
     */
    public static void appendToNoteFile() throws IOException {
        Storage.appendToFile("data/notes.txt", (NoteList.getCounter()) + "." +
                NoteList.getNoteList().get(NoteList.getCounter() - 1).toPrintToFile()
                + System.lineSeparator());
    }

    /**
     * Rewrites the task file after any changes made to the TaskList.
     *
     * @throws IOException if there is an error in re-writing the list of tasks without the
     * deleted task
     */
    public static void rewriteTaskFile() throws IOException{
        if (TaskList.getTaskList().size() == 0) {
            Storage.writeToFile("data/jarvis.txt", "");
        } else {
            for (int i = 0; i < TaskList.getTaskList().size(); i++) {
                if (i == 0) {
                    Storage.writeToFile("data/jarvis.txt", (i + 1) + "." +
                            TaskList.getTaskList().get(i).toPrintToFile()
                            + System.lineSeparator());
                } else {
                    Storage.appendToFile("data/jarvis.txt", (i + 1) + "." +
                            TaskList.getTaskList().get(i).toPrintToFile()
                            + System.lineSeparator());
                }
            }
        }
    }

    /**
     * Rewrites the task file after any changes made to the TaskList.
     *
     * @throws IOException if there is an error in re-writing the list of tasks without the
     * deleted task
     */
    public static void rewriteNoteFile() throws IOException{
        if (NoteList.getNoteList().size() == 0) {
            Storage.writeToFile("data/notes.txt", "");
        } else {
            for (int i = 0; i < NoteList.getNoteList().size(); i++) {
                if (i == 0) {
                    Storage.writeToFile("data/notes.txt", (i + 1) + "." +
                            NoteList.getNoteList().get(i).toPrintToFile()
                            + System.lineSeparator());
                } else {
                    Storage.appendToFile("data/notes.txt", (i + 1) + "." +
                            NoteList.getNoteList().get(i).toPrintToFile()
                            + System.lineSeparator());
                }
            }
        }
    }
}
