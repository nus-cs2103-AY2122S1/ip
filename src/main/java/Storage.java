import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//deals with loading tasks from the file and saving tasks in the file
public class Storage {
    private final String filePath;

    public Storage(String fp) {
        this.filePath = fp;

    }

    public static void loadFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            // Read and add the task into the list
            String input = s.nextLine();
            String[] arrOfInputs = input.split("\\|");
            readInputs(arrOfInputs);
        }
    }

    private static void readInputs(String[] arrOfInputs) {
        //Check for T, D, E
        if (arrOfInputs[0].equals("T")) {
            String t = arrOfInputs[2];
            ToDo td = new ToDo(t);
            Duke.list.add(td);
        } else if (arrOfInputs[0].equals("D")) {
            String t = arrOfInputs[2] + " /by " + arrOfInputs[3];
            Deadline d = new Deadline(t);
            Duke.list.add(d);
        } else if (arrOfInputs[0].equals("E")) {
            String t = arrOfInputs[2] + " /at " + arrOfInputs[3];
            Event e = new Event(t);
            Duke.list.add(e);
        }

        int currListLength = Duke.list.size();
        //Check if its completed or not (0,1) and mark accordingly
        if (arrOfInputs[1].equals("1")) {
            Duke.list.get(currListLength - 1).markAsDone();
        }
    }

    public static void writeToFile(String filePath, ArrayList<Task> taskList) throws DukeException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Task t: taskList) {
                bufferedWriter.write(t.getTaskInfo() + System.lineSeparator());
            }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new DukeException("Could not write to file!");
        }
    }
}
