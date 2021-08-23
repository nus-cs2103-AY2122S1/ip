import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final DateTimeFormatter FORMAT_TIME_FILE = DateTimeFormatter.ofPattern("MMM d yyyy hh:m a");
    private static final DateTimeFormatter FORMAT_NO_TIME_FILE = DateTimeFormatter.ofPattern("MMM d yyyy");
    File dir;
    File txtFile;
    String filePath;

    public Storage(String dirPath, String fileName) throws IOException {
        this.dir = new File(dirPath);
        dir.mkdir();
        this.filePath = dirPath + fileName;
        this.txtFile = new File(this.filePath);
    }

    public ArrayList<Task> loadFile() throws IOException {
        ArrayList<Task> arr = new ArrayList<>();
        if (!this.txtFile.createNewFile()) {
            Scanner s = new Scanner(this.txtFile);
            while (s.hasNext()) {
                String line = s.nextLine();
                switch (line.charAt(4)) {
                    case 'T':
                        arr.add(new Todo(line.substring(10)));
                        break;
                    case 'D': {
                        int index = line.indexOf(" (by: ");
                        String description = line.substring(10, index);
                        String dateTime = line.substring(index + 6, line.length() - 1);
                        String[] dateTimeArray = dateTime.split(" ");
                        if (dateTimeArray.length > 3) {
                            arr.add(new Deadline(description, dateTime, FORMAT_TIME_FILE, true));
                        } else {
                            arr.add(new Deadline(description, dateTime, FORMAT_NO_TIME_FILE, false));
                        }
                        break;
                    }
                    case 'E':
                        int index = line.indexOf(" (at: ");
                        String description = line.substring(10, index);
                        String dateTime = line.substring(index + 6, line.length() - 1);
                        String[] dateTimeArray = dateTime.split(" ");
                        if (dateTimeArray.length > 3) {
                            arr.add(new Event(description, dateTime, FORMAT_TIME_FILE, true));
                        } else {
                            arr.add(new Event(description, dateTime, FORMAT_NO_TIME_FILE, false));
                        }
                        break;
                }
            }
        }
        return arr;
    }

    public void saveFile(String textToSave) throws IOException {
        FileWriter fileWriter = new FileWriter(this.filePath);
        fileWriter.write(textToSave);
        fileWriter.close();
    }
}
