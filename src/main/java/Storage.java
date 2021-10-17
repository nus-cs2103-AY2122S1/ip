import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Storage {
    File txtFile;
    String path;
    String folder;

    public Storage() {

    }

    public void checkStorage(Parser parser, TaskList taskList) {
        File txtFile;
        String path = System.getProperty("user.dir") + "\\src\\main\\data\\duke.txt";
        txtFile = new File(path);
        this.txtFile = txtFile;
        this.path = path;
        this.folder = System.getProperty("user.dir") + "\\src\\main\\data";

        try {
            if (Files.notExists(Path.of(folder))) {
                Files.createDirectories(Path.of(folder));
            }
            if (!txtFile.exists()) {
                txtFile.createNewFile();
                this.txtFile = txtFile;
                assert txtFile != null : "text file is null";
            } else {
                BufferedReader br = new BufferedReader(new FileReader(txtFile));
                String txtLine;
                txtLine = br.readLine();
                while (txtLine != null) {
                    String[] segments = txtLine.split("\\|");
                    parser.parse(segments[2], taskList, this);
                    if (Objects.equals(segments[0], "1")) {
                        String input = "done " + segments[1];
                        parser.parse(input, taskList, this);
                    }
                    txtLine = br.readLine();
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR OCCURRED");
        }
    }

    public void writeData(TaskList taskList) {
        try {
            for (int k = 0; k < taskList.getCount(); k++) {
                String done;
                if (taskList.getTaskList()[k].getStatusIcon() == "X") {
                    done = "1|";
                } else {
                    done = "0|";
                }
                if (taskList.getTaskList()[k].getTask() == "T") {
                    String command = done + (k + 1) + "|todo " + taskList.getTaskList()[k].getDescription();
                    if (k == 0) {
                        writeToFile(path, command);
                    } else {
                        appendToFile(path, command);
                    }
                } else if (taskList.getTaskList()[k].getTask() == "D") {
                    String command = done + (k + 1) + "|deadline " + taskList.getTaskList()[k].getDescription()
                            + "/by " + taskList.getTaskList()[k].getDateNum();
                    if (k == 0) {
                        writeToFile(path, command);
                    } else {
                        appendToFile(path, command);
                    }
                } else if (taskList.getTaskList()[k].getTask() == "E") {
                    String command = done + (k + 1) + "|event " + taskList.getTaskList()[k].getDescription() + "/at "
                            + taskList.getTaskList()[k].getDateNum();
                    if (k == 0) {
                        writeToFile(path, command);
                    } else {
                        appendToFile(path, command);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Unable to write to file");
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(System.lineSeparator() + textToAppend);
        fw.close();
    }

}
