package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Task;

public class Storage {

    private String filepath;

    public Storage(String filePath) {
        this.filepath = filePath;
    }

    /**
     * Returns the read contents from the txt file.
     *
     * @return
     */
    public List<String> load() {
        List<String> taskArrayAsString = new ArrayList<>();
        File myFile = new File(this.filepath);

        try {
            Scanner scanner = new Scanner(myFile);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                taskArrayAsString.add(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return taskArrayAsString;
        }
        return taskArrayAsString;
    }

    /**
     * Writes the given tasks to the file.
     *
     * @param taskList
     * @return Returns the string output for file txt.
     * @throws IOException Throws the error to be caught at TaskList.
     */
    public String writeToFile(List<Task> taskList) throws IOException {
        FileWriter myWriter = new FileWriter(this.filepath);

        String output = "";
        for (Task task : taskList) {
            output += task.toWriteString();
            output += "\n";
        }

        myWriter.write(output);
        myWriter.close();

        return output;
    }
}
