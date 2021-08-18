import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        //TODO:
        File f = new File(this.filepath);
        Scanner s = new Scanner(f);

        ArrayList<Task> tasklist = new ArrayList<>();
        while(s.hasNext()) {
            //Parser's job
            //input here will definitely be correct and accurate
            String fullLineOfCommand = s.nextLine();
            //either, todo, deadline, delete, event, done
            Scanner lineSplitter = new Scanner(fullLineOfCommand);
            String command = lineSplitter.next().trim();
            if (command.equals("todo")) {
                String description = lineSplitter.nextLine();
                tasklist.add(new ToDo(description.trim()));
            } else if (command.equals("deadline")) {
                String description = lineSplitter.nextLine();
                String[] parts = description.split("/by");
                tasklist.add(new Deadline(parts[0].trim(), parts[1].trim()));
            } else if (command.equals("event")) {
                String description = lineSplitter.nextLine();
                String[] parts = description.split("/at");
                tasklist.add(new Deadline(parts[0].trim(), parts[1].trim()));
            } else if (command.equals("done")) {
                int indexToMark = lineSplitter.nextInt();
                tasklist.get(indexToMark - 1).markAsDone();
            } else if (command.equals("delete")) {
                int indexToDelete = lineSplitter.nextInt();
                tasklist.remove(indexToDelete - 1);
            }
        }
        return tasklist;
    }

    public void appendCommand(String taskCommand) throws IOException {
        FileWriter fw = new FileWriter(this.filepath, true);
        fw.append(taskCommand + System.lineSeparator());
        fw.close();
    }

    public void safeFile(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(this.filepath);
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            fw.append(t.fullCommand() + System.lineSeparator());
            if (t.isDone) {
                int index = i + 1;
                fw.append("done " + index + System.lineSeparator());
            }
        }
        fw.close();
    }

}
