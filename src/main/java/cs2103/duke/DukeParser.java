package cs2103.duke;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class encapsulates the parsing of duke.txt to convert the contents of the file
 * into an ArrayList.
 */
public class DukeParser {
    private String filePath;

    public DukeParser(String dukeFilePath) {
        this.filePath = dukeFilePath;
    }

    /**
     * This method copies the contents of the file from filePath and
     * converts them into Task objects into the taskArrayList with the
     * help of the parse() method.
     *
     * @return TaskArrayList the ArrayList to copy the information to.
     * @throws IOException If filePath does not exist.
     */
    public ArrayList<Task> copyFileContents() throws IOException {
        assert filePath != null;
        File f = new File(this.filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        StringBuilder targetBuilder = new StringBuilder();
        while (s.hasNext()) {
            targetBuilder.append(s.nextLine()).append("\n");
        }

        return parse(targetBuilder.toString());
    }

    /**
     * This method parses the string copied from duke.txt and converts them into task objects
     * into the taskArrayList.
     *
     * @param toParse The string to parse.
     * @return A taskArrayList made up of tasks.
     */
    public static ArrayList<Task> parse(String toParse) {
        ArrayList<Task> result = new ArrayList<>();
        Scanner ps = new Scanner(toParse); // passes whole file into the scanner
        while (ps.hasNextLine()) {
            String nLine = ps.nextLine(); // parse one line at a time
            int ref = 3; // reference point
            char taskType = nLine.charAt(ref);
            boolean isDone = nLine.charAt(ref + 3) == 'X';
            int strLength = nLine.length();
            switch (taskType) {
            case 'T':
                String todoName = nLine.substring(ref + 5, strLength).trim();
                Task newestTodo = new ToDo(result.size(), todoName);
                if (isDone) {
                    newestTodo.markAsDone();
                }
                result.add(newestTodo);
                break;
            case 'D':
                String deadlineInfo = nLine.substring(ref + 5, strLength);
                String[] arrD = deadlineInfo.split("\\(by: ", 2);
                String deadlineName = arrD[0].trim();
                String deadlineReminder = arrD[1].substring(0, arrD[1].length() - 1).trim();
                deadlineReminder = parseDate(deadlineReminder);
                Task newestDeadline = new Deadline(result.size(), deadlineName, deadlineReminder);
                result.add(newestDeadline);
                if (isDone) {
                    newestDeadline.markAsDone();
                }
                break;
            case 'E':
                String eventInfo = nLine.substring(ref + 5, strLength);
                String[] arrE = eventInfo.split("\\(at: ", 2);
                String eventName = arrE[0].trim();
                String eventReminder = arrE[1].substring(0, arrE[1].length() - 1).trim();
                eventReminder = parseDate(eventReminder);
                Task newestEvent = new Event(result.size(), eventName, eventReminder);
                result.add(newestEvent);
                if (isDone) {
                    newestEvent.markAsDone();
                }
                break;
            default:
                System.out.println("Unknown input");
                break;
            }
        }
        return result;
    }

    /**
     * Parses input string in the format "yyyy MM dd" and returns it in the format
     * "YYYY-MM-DD".
     *
     * @param input String in format "MMM d yyyy".
     * @return String in format "YYYY-MM-DD".
     */
    public static String parseDate(String input) {
        String year = input.substring(0, 4);
        String month = input.substring(5, 7);
        String day = input.substring(8, 10);
        return year + "-" + month + "-" + day;
    }

}
