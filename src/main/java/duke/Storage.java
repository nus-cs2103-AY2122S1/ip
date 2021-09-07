package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Encapsulates a class that handles saving to and loading from the text file that contains task data.
 *
 * @author Hanif Kamal
 */
public class Storage {
    /** A String representation of the location of the text file as a file path. */
    private final String filePath;
    private final String noteFilePath;

    /**
     * Class constructor.
     *
     * @param filePath A String representation of the location of the text file as a file path.
     */
    public Storage(String filePath, String noteFilePath) {
        this.filePath = filePath;
        this.noteFilePath = noteFilePath;
    }

    /**
     * Writes Task data from the TaskList to the text file, line by line.
     *
     * @param list The TaskList from which Task data is used to write to the text file.
     * @throws DukeException In the case where the file is corrupted or cannot be located.
     */
    public void writeTasks(TaskList list) throws DukeException {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            for (int i = 0; i < list.size(); i++) {
                bw.write(list.getTask(i).toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new DukeException("Error upon writing to Task data file: " + e.getMessage());
        }
    }

    /**
     * Writes Note data from the NoteList to the text file, line by line.
     *
     * @param noteList The NoteList from which Note data is used to write to the text file.
     * @throws DukeException In the case where the file is corrupted or cannot be located.
     */
    public void writeNotes(NoteList noteList) throws DukeException {
        try {
            BufferedWriter nbw = new BufferedWriter(new FileWriter(noteFilePath));
            for (int i = 0; i < noteList.size(); i++) {
                nbw.write(noteList.getNote(i).toString());
                nbw.newLine();
            }
            nbw.close();
        } catch (IOException e) {
            throw new DukeException("Error upon writing to Note data file: " + e.getMessage());
        }
    }

    /**
     * Reads a text file, line by line. Parses the lines into task data, and adds it to the given TaskList.
     *
     * @param list An empty TaskList to be filled with tasks, as parsed from the text file
     * @throws DukeException In the case where the file is corrupted or cannot be located.
     */
    public void readTasks(TaskList list) throws DukeException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException("Error upon creating Task data file: " + e.getMessage());
            }
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineParts = line.split(" ", 2);

                switch(lineParts[0].charAt(1)) {
                case 'T':
                    String todoName = lineParts[1];
                    boolean todoStatus = (lineParts[0].charAt(4) == 'X');
                    list.addToList(new Todo(todoName, todoStatus));
                    break;
                case 'D':
                    boolean deadlineStatus = (lineParts[0].charAt(4) == 'X');
                    String[] deadlineSplit = lineParts[1].split(" \\(by: " , 2);
                    String deadlineName = deadlineSplit[0];
                    String deadlineByWithBracket = deadlineSplit[1];
                    String deadlineBy = deadlineByWithBracket.substring(0, deadlineByWithBracket.length() - 1);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");
                    LocalDate correctDeadlineBy = LocalDate.parse(deadlineBy, dtf);
                    list.addToList(new Deadline(deadlineName, deadlineStatus, correctDeadlineBy));
                    break;
                case 'E':
                    boolean eventStatus = (lineParts[0].charAt(4) == 'X');
                    String[] eventSplit = lineParts[1].split(" \\(at: " , 2);
                    String eventName = eventSplit[0];
                    String eventAtWithBracket = eventSplit[1];
                    String eventAt = eventAtWithBracket.substring(0, eventAtWithBracket.length() - 1);
                    DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd MMM yyy hh:mm a");
                    LocalDateTime correctEventAt = LocalDateTime.parse(eventAt, dtf2);
                    list.addToList(new Event(eventName, eventStatus, correctEventAt));
                    break;
                default:
                    throw new DukeException("Oh no! It seems that the Task data in the storage file cannot be read!");
                }
            }
            br.close();
        } catch (IOException e) {
            throw new DukeException("Error upon reading from Task data file: " + e.getMessage());
        }
    }


    /**
     * Reads a text file, line by line. Parses the lines into Note data, and adds it to the given NoteList.
     *
     * @param noteList An empty NoteList to be filled with Notes, as parsed from the text file
     * @throws DukeException In the case where the file is corrupted or cannot be located.
     */
    public void readNotes(NoteList noteList) throws DukeException {
        File file = new File(noteFilePath);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException("Error upon creating Note data file: " + e.getMessage());
            }
        }
        try {
            BufferedReader nbr = new BufferedReader(new FileReader(noteFilePath));
            String line;
            while ((line = nbr.readLine()) != null) {
                String[] lineParts = line.split(" \\| created: ");
                String noteDescription = lineParts[0];
                String noteCreatedDateTime = lineParts[1];
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy hh:mm a");
                LocalDateTime noteCreatedDateTimeFormatted = LocalDateTime.parse(noteCreatedDateTime, dtf);
                noteList.addToList(new Note(noteDescription, noteCreatedDateTimeFormatted));
            }
            nbr.close();
        } catch (IOException e) {
            throw new DukeException("Error upon reading from Note data file: " + e.getMessage());
        }
    }
}
