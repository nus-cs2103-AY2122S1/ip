import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;
    private File taskFile;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskFile = new File(filePath);
    }

    public void checkFile() {
        if (!taskFile.exists()) {
            File dir = new File("data");
            if (!(dir.exists() && dir.isDirectory())) {
                dir.mkdir();
            }
            try {
                taskFile.createNewFile();
            } catch (IOException e) {

            } catch (SecurityException e) {

            }
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> userInput = new ArrayList<>();
        try {
            Scanner s = new Scanner(taskFile);
            while (s.hasNextLine()) {
                String taskString = s.nextLine();
                System.out.println(taskString);
                String[] splitString = taskString.split("  ");

                switch (splitString[0]) {

                    case "T":
                        userInput.add(new Todo(splitString[2]));
                        break;
                    case "D":
                        LocalDate date = LocalDate.parse(splitString[3]);
                        userInput.add(new Deadline(splitString[2], date));
                        break;
                    case "E":
                        LocalDateTime dateTime = LocalDateTime.parse(splitString[3]);
                        userInput.add(new Event(splitString[2], dateTime));
                        break;
                }

                if (splitString.length > 2 && splitString[1].equals("Y")) {
                    userInput.get(userInput.size() - 1).markAsDone();
                }
            }
            return userInput;

        } catch (FileNotFoundException e) {
            throw new DukeException("OH NO :( The file cannot be found...");
        }
    }
}
