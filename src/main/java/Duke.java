import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private final File file;
    private final ArrayList<Task> taskList;

    public static void main(String[] args) {
        try {
            String home = System.getProperty("user.home");
            //specify where the duke.txt file should be. In this case it is created in users' Desktop.
            Path path = Paths.get(home, "Desktop", "duke.txt");
            new Duke(path.toString()).userInteraction();
        } catch (IOException exception) {
            System.out.println("Something went wrong! Seems like I'm unable to locate your file!");
        } catch (DukeException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public Duke(String filePath) throws IOException, InvalidCommandException, EmptyFieldException {
        File f = new File(filePath);
        this.file = f;
        if (!f.createNewFile()) {
            //file already exist, so just read from it.
            this.taskList = readFromFile(filePath);
        } else {
            //file does not exist, create an empty file.
            this.taskList = new ArrayList<>();
        }
    }

    public void writeToFile() throws IOException {
        FileWriter fileWriter = new FileWriter(this.file);
        StringBuilder sb = new StringBuilder();
        for (Task task : this.taskList) {
            String str = task.saveToFile() + "\n";
            sb.append(str);
        }
        fileWriter.write(sb.toString());
        fileWriter.close();
    }

    public ArrayList<Task> readFromFile(String filePath)
            throws FileNotFoundException, EmptyFieldException, InvalidCommandException {
        // create a File for the given file path
        File f = new File(filePath);
        // create a Scanner using the File as the source
        Scanner s = new Scanner(f);
        ArrayList<Task> temp = new ArrayList<>();
        while (s.hasNext()) {
            Task task = textToTask(s.nextLine());
            temp.add(task);
        }
        return temp;
    }

    public Task textToTask(String fileInput) throws EmptyFieldException, InvalidCommandException {
        String[] taskString = fileInput.split("\\|");
        Task temp;
        for (int i = 0; i < taskString.length; i++) {
            taskString[i] = taskString[i].strip();
        }

        if (taskString[0].equals("T")) {
            temp = new ToDo(taskString[2]);
        } else if (taskString[0].equals("E")) {
            temp = new Event(taskString[2], taskString[3]);
        } else {
            temp = new Deadline(taskString[2], taskString[3]);
        }

        if (taskString[1].equals("1")) {
            temp.setIsDone(true);
        }
        return temp;
    }


    public void userInteraction() {

        DefaultDisplayMessage defaultDisplayMessage = new DefaultDisplayMessage();
        defaultDisplayMessage.execute();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            String[] inputValues = command.split(" ");
            System.out.println("    -----------------------------------------");

            if (command.equals("bye")) {
                new ByeCommand().execute();
                break;
            } else if (command.equals("list")) {
                new ListCommand(this.taskList).execute();
            } else if (inputValues[0].equals("done") && inputValues.length == 2) {
                //treat as unknown command if there is more than 1 number after "done".
                new DoneCommand(this.taskList, inputValues).execute();
            } else if (inputValues[0].equals("delete") && inputValues.length == 2) {
                //treat as unknown command if there is more than 1 number after "delete".
                new DeleteCommand(this.taskList, inputValues).execute();
            } else if (inputValues[0].equals("deadline")) {
                new DeadlineCommand(this.taskList, inputValues, command).execute();
            } else if (inputValues[0].equals("event")) {
                new EventCommand(this.taskList, inputValues, command).execute();
            } else if (inputValues[0].equals("todo")) {
                new TodoCommand(this.taskList, inputValues, command).execute();
            } else {
                System.out.println("     OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println("    -----------------------------------------");
            System.out.println();

            try {
                writeToFile();
            } catch (IOException e) {
                System.out.println("Something went wrong! Seems like I'm unable to locate your file!");
            }
        }
        sc.close();
    }
}
