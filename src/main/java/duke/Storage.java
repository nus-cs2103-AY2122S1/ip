package duke;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

/**
 * deals with loading tasks from the file and saving tasks in the file
 *
 * @author Erwin Quek
 * @version CS2103 AY21/22 Sem 1
 */
public class Storage {
    private final Path path;
    private final String stringPath;

    /**
     * Constructor for Storage.
     * @param filePath Takes in a file path
     * @throws InvalidPathException Throws an invalid path error
     */
    public Storage(String filePath) throws InvalidPathException {
        this.path = Path.of(filePath);
        this.stringPath = filePath;
//        if (!isValidPath(path)) {
//            throw new InvalidPathException("Invalid file!! Should end with .txt");
//        }
    }

    /**
     * A method to check if path is valid.
     * @param path
     * @return
     */
    private boolean isValidPath(Path path) {
        return path.toString().endsWith(".txt");
    }

    public void saveList(TaskList taskList) {
        createNewFile(path.getParent(), path);
//        try {
//            Files.writeString(path, taskList.saveString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void createNewFile(Path directory, Path filepath) {
        if (Files.notExists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Files.notExists(filepath)) {
            try {
                Files.createFile(filepath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Path load() {
        return this.path;
    }

    public String getPath()  {
        return this.stringPath;
    }
}

// Old ccode - to be reviewed
//    public String[] load() throws FileNotFoundException {
//        path = Paths.get(filePath);
//        filePath f = new File(this.filePath);
//        Scanner s = new Scanner(f);
//        while (s.hasNext()) {
//            // Read and add the task into the list
//            String input = s.nextLine();
//            String[] arrOfInputs = input.split("\\|");
//            readInputs(arrOfInputs);
//        }
//        return new String[2];
//    }
//
//    private static void readInputs(String[] arrOfInputs) {
//        //Check for T, D, E
//        if (arrOfInputs[0].equals("T")) {
//            String t = arrOfInputs[2];
//            duke.ToDo td = new duke.ToDo(t);
//            duke.Duke.list.add(td);
//        } else if (arrOfInputs[0].equals("D")) {
//            String t = arrOfInputs[2] + " /by " + arrOfInputs[3];
//            duke.Deadline d = new duke.Deadline(t);
//            duke.Duke.list.add(d);
//        } else if (arrOfInputs[0].equals("E")) {
//            String t = arrOfInputs[2] + " /at " + arrOfInputs[3];
//            duke.Event e = new duke.Event(t);
//            duke.Duke.list.add(e);
//        }
//
//        int currListLength = duke.Duke.list.size();
//        //Check if its completed or not (0,1) and mark accordingly
//        if (arrOfInputs[1].equals("1")) {
//            duke.Duke.list.get(currListLength - 1).markAsDone();
//        }
//    }
//
//    public static void writeToFile(String filePath, ArrayList<duke.Task> taskList) throws duke.DukeException {
//        try {
//            File file = new File(filePath);
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//            FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
//            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//            for (duke.Task t: taskList) {
//                bufferedWriter.write(t.getTaskInfo() + System.lineSeparator());
//            }
//            bufferedWriter.close();
//        } catch (IOException e) {
//            throw new duke.DukeException("Could not write to file!");
//        }
//    }
//}
