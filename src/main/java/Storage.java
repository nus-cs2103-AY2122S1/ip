import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;
    private File taskFile;
    private TaskList taskList;

    public Storage(String filePath, TaskList taskList) {
        this.filePath = filePath;
        this.taskFile = new File(filePath);
        this.taskList = taskList;

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

    public String fileString(Task task) {
        String toAdd = task.taskIndicator() + "  " + (task.getStatusIcon().equals("X")
                ? "Y" : "N") + "  " + task.description.trim();

        if(task.taskIndicator().equals("D")) {
            Deadline temp = (Deadline) task;
            toAdd += "  " + temp.getBy().trim();
        }  else if(task.taskIndicator().equals("E")) {
            Event temp = (Event) task;
            toAdd += "  " + temp.getAt().trim();
        }
        return toAdd;
    }

    public void editFileAll() {
        for (int i = 0; i < taskList.size(); i++) {
            Task tempFile = taskList.get(i);
            String toAdd = fileString(tempFile);
            if (i == 0) {
                editFile( toAdd);
            } else {
                appendToFile("data/Tasks.txt", toAdd);
            }
        }
    }

    public void editFile(String content) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(System.lineSeparator() + content);
            fw.close();
        } catch (IOException e) {
            System.out.println("OH NO :( There seems to be something wrong with the file.");
        }
    }

    public void appendToFile(String fileName, String content) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            fw.append(System.lineSeparator() + content);
            fw.close();
        } catch (IOException e) {
            System.out.println("OH NO :( There seems to be something wrong with the file.");
        }
    }

}
