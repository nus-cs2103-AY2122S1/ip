package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;
    static private File dir;
    static private File tmp;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public static ArrayList<duke.Task> load() throws DukeException {
        dir = new File("data");
        dir.mkdirs();
        tmp = new File(dir, "alexa.txt");
        try {
            boolean successfulCreate = tmp.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        ArrayList<duke.Task> newStorage = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(tmp);
            boolean hasNoTask = true;
            while (myReader.hasNextLine()) {
                if (hasNoTask) {
                    System.out.println("Welcome back! Here are your last saved tasks!\n");
                }
                hasNoTask = false;
                String data = myReader.nextLine();
                String taskType = data.substring(3, 4);
                switch (taskType) {
                    case "T":
                        Todo newToDo = new Todo(data.substring(9));
                        newStorage.add(newToDo);
                        break;
                    case "D":
                        int indexOfOpenBracketD = data.indexOf("(");
                        int indexOfCloseBracketD = data.indexOf(")");
                        String deadlineDate = data.substring(indexOfOpenBracketD + 4, indexOfCloseBracketD);
                        String deadlineTitle = data.substring(9, indexOfOpenBracketD);
                        Deadline newDeadline = new Deadline(deadlineTitle, deadlineDate);
                        newStorage.add(newDeadline);
                        break;
                    case "E":
                        int indexOfOpenBracketE = data.indexOf("(");
                        int indexOfCloseBracketE = data.indexOf(")");
                        String eventDate = data.substring(indexOfOpenBracketE + 4, indexOfCloseBracketE);
                        String eventTitle = data.substring(9, indexOfOpenBracketE);
                        Event newEvent = new Event(eventTitle, eventDate);
                        newStorage.add(newEvent);
                        break;
                    default:
                        break;
                }
                System.out.println("    " + data);
            }
            if (hasNoTask) {
                throw new DukeException("    Nice! You have no pending tasks!");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("=======================================");
        return newStorage;
    }

    public static void writeTasks() {
        String sentence = "";
        for (int i = 1; i < TaskList.noOfTasks() + 1; i++) {
            duke.Task currentTask = TaskList.getCurrentTask(i - 1);
            sentence = sentence + i + "." + currentTask.toString() + "\n";
        }
        try {
            PrintWriter writer = new PrintWriter(tmp.getAbsolutePath());
            writer.print("");
            writer.print(sentence);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
