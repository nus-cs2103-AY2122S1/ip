import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class Storage {
    private static String home = System.getProperty("user.home");
    private static String dukeText;
    ;

    public Storage(String filePath) {
        dukeText = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> loadedList = new ArrayList<>();

        Scanner listScan = new Scanner(new File(home + dukeText));

        while (listScan.hasNext()) {
            String listInput = listScan.nextLine();
            String[] input = listInput.split("\\|", 3);
            for (int i = 0; i < input.length; i++) {
                input[i] = input[i].trim();
            }

            switch (input[0]) {
                case "T":
                    ToDo todo = new ToDo(input[2]);
                    if (input[1].equals("1")) {
                        todo.isDone();
                    }
                    loadedList.add(todo);
                    break;
                case "D":
                    String[] deadlineTask = input[2].split("\\|", 2);
                    String deadlineInput = deadlineTask[0].trim() + "|" + deadlineTask[1].trim();
                    Deadline deadline = new Deadline(deadlineInput);
                    if (input[1].equals("1")) {
                        deadline.isDone();
                    }
                    loadedList.add(deadline);
                    break;
                case "E":
                    String[] eventTask = input[2].split("\\|", 2);
                    String eventInput = eventTask[0] + " / " + eventTask[1];
                    Event event = new Event(eventInput);
                    if (input[1].equals("1")) {
                        event.isDone();
                    }
                    loadedList.add(event);
                    break;
            }
        }
        listScan.close();
        return loadedList;

    }

    public void save(String textToAppend, boolean appendStatus) throws IOException {
        FileWriter writeToFile = new FileWriter(home + dukeText, appendStatus);
        writeToFile.write(textToAppend);
        writeToFile.close();
    }

    public void refresh(TaskList taskList) throws IOException {
        FileWriter refreshFile = new FileWriter(home + dukeText);
        refreshFile.write(taskList.refreshList());
        refreshFile.close();
    }
}
