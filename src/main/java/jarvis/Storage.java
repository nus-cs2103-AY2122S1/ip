package jarvis;

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
     * list of tasks (if the directory and file don't already exit).
     *
     * @param filePath the relative path to location in which the file is to be created
     */
    public Storage(String filePath) {
        this.filePath = filePath; // Format: directory/file
        try {
            StringBuilder dirName = new StringBuilder();
            int index = 0;

            // Extracting the directory and file name
            while (index < filePath.length() && !filePath.substring(index).startsWith("/")) {
                assert !(index == filePath.length() - 1): "Filepath is invalid";

                dirName.append(filePath.charAt(index));
                index++;
            }

            // If directory and file name can be extracted successfully
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
     * Retrieves the list of tasks from the user's hard disk and displays it to the user.
     *
     * @throws FileNotFoundException if the file ('jarvis.txt') containing the list of tasks
     * cannot be found
     * @throws JarvisException if the task date or timings are invalid
     */
    public void retrieveTaskFileContents() throws FileNotFoundException, JarvisException {
        File f = new File(this.filePath); // Create a File for the given file path
        Scanner s = new Scanner(f); // Create a Scanner using the File as the source

        // If retrieving user tasks
        if (this.filePath.equals("data/jarvis.txt")) {
            while (s.hasNext()) {
                String currLine = s.nextLine();
                currLine = currLine.substring(2);

                // If the task is a todo task
                if (currLine.startsWith("[T]")) {
                    this.retrieveTodo(currLine);

                // If the task is a deadline task
                } else if (currLine.startsWith("[D]")) {
                    this.retrieveDeadline(currLine);

                // If the task is an event task
                } else if (currLine.startsWith("[E]")) {
                    this.retrieveEvent(currLine);
                }
            }
        }
    }

    /**
     * Interprets the string from the user's task file that represents the todo task and adds it to the task list.
     *
     * @param todoString the string from the user's task file that represents the todo task
     */
    public void retrieveTodo(String todoString) {
        String description = " " + todoString.substring(7);
        Todo newTodo = new Todo(description);
        TaskList.addTask(newTodo);
        if (todoString.charAt(4) == 'X') {
            newTodo.markAsDone();
        }
    }

    /**
     * Interprets the string from the user's task file that represents the deadline task and adds it to the task list.
     *
     * @param deadlineString the string from the user's task file that represents the deadline task
     * @throws JarvisException if the deadline date or timing are invalid
     */
    public void retrieveDeadline(String deadlineString) throws JarvisException {
        int currIndex = 7;

        // Find 'by: ' which separated the task description and the deadline
        while (!deadlineString.substring(currIndex).startsWith("by: ")) {
            assert !(currIndex == this.filePath.length() - 1) : "Filepath is invalid"; //If 'by: ' not found
            currIndex++;
        }

        String description = " " + deadlineString.substring(7, currIndex - 2);
        String by = deadlineString.substring(currIndex + 4, deadlineString.length() - 1);

        Task newDeadline = new Deadline(description, by); // Create new deadline task
        TaskList.addTask(newDeadline); // Add the deadline task to the task list
        if (deadlineString.charAt(4) == 'X') {
            newDeadline.markAsDone(); // Mark the task as done if it has been completed
        }
    }

    /**
     * Interprets the string from the user's task file that represents the event task and adds it to the task list.
     *
     * @param eventString the string from the user's task file that represents the event task
     * @throws JarvisException if the event date or timings are invalid
     */
    public void retrieveEvent(String eventString) throws JarvisException {
        int currIndex = 7;

        // Find 'at: ' which separates the task description and timings
        while (!eventString.substring(currIndex).startsWith("at: ")) {
            assert !(currIndex == this.filePath.length() - 1) : "Filepath is invalid"; // If 'at: ' not found
            currIndex++;
        }

        String description = " " + eventString.substring(7, currIndex - 2);
        String by = eventString.substring(currIndex + 4, eventString.length() - 1);

        Task newEvent = new Event(description, by); // Create new event task
        TaskList.addTask(newEvent); // Add the event task to the task list
        if (eventString.charAt(4) == 'X') {
            newEvent.markAsDone(); // Mark the task as done if it has been completed
        }
    }

    /**
     * Retrieves the list of notes from the user's hard disk and displays it to the user.
     *
     * @throws FileNotFoundException if the file ('notes.txt') containing the list of tasks
     * cannot be found
     */
    public void retrieveNotesFileContents() throws FileNotFoundException {
        File f = new File(this.filePath); // Create a File for the given file path
        Scanner s = new Scanner(f); // Create a Scanner using the File as the source

        // If retrieving user tasks
        if (this.filePath.equals("data/notes.txt")) {
            while (s.hasNext()) {
                String currLine = s.nextLine();
                currLine = currLine.substring(2);

                StringBuilder title = new StringBuilder();
                String body;

                int index = 0;
                //Finding the ':' that separates the note title and body
                while (index < currLine.length() &&
                        !currLine.substring(index).startsWith(":")) {
                    assert !(index == filePath.length() - 1) : "Filepath is invalid"; //If ':' not found
                    title.append(currLine.substring(index, index + 1));
                    index++;
                }

                //If title and body can be extracted successfully
                body = currLine.substring(index + 1);
                Note newNote = new Note(title.toString(), body);
                NoteList.addNote(newNote);
            }
        }
    }

    /**
     * Writes to the file in user's hard disk that stores a list of tasks or to overwrite
     * the contents of this file.
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
     * Appends content to the file in user's hard disk.
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
     * Appends a task to the end of a task file stored in the user's hard disk.
     *
     * @throws IOException if there is an error in appending to existing content of the file
     */
    public static void appendToTaskFile() throws IOException {
        Storage.appendToFile("data/jarvis.txt", (TaskList.getCounter()) + "." +
                TaskList.getTaskList().get(TaskList.getCounter() - 1).toPrintToFile()
                + System.lineSeparator());
    }

    /**
     * Appends a note to the end of a task file stored in the user's hard disk.
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
    public static void rewriteTaskFile() throws IOException {
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
     * Rewrites the note file after any changes made to the NoteList.
     *
     * @throws IOException if there is an error in re-writing the list of tasks without the
     * deleted task
     */
    public static void rewriteNoteFile() throws IOException {
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
