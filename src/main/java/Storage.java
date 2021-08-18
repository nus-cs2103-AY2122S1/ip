import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> load() throws IOException {
        java.nio.file.Path filepath = java.nio.file.Paths.get("src", "main", "java","data");
        File f = new File(this.filepath);
        if (!Files.isDirectory(filepath)) {
            //create directory
            Files.createDirectories(filepath);
        }
        if (!f.exists()) {
            //create file if it does not exist
            f.createNewFile();
        }
        Scanner s = new Scanner(f);
        ArrayList<Task> taskList = new ArrayList<>();

        while(s.hasNext()) {
            //Parser's job
            //input here will definitely be correct and accurate
            String fullLineOfCommand = s.nextLine();
            //either, todo, deadline, delete, event, done
            Scanner lineSplitter = new Scanner(fullLineOfCommand);
            String command = lineSplitter.next().trim();
            if (command.equals("todo")) {
                String description = lineSplitter.nextLine();
                taskList.add(new ToDo(description.trim()));

            } else if (command.equals("deadline")) {
                String description = lineSplitter.nextLine();
                String[] parts = description.split("/by");
                taskList.add(new Deadline(parts[0].trim(), parts[1].trim()));

            } else if (command.equals("event")) {
                String description = lineSplitter.nextLine();
                String[] parts = description.split("/at");
                taskList.add(new Event(parts[0].trim(), parts[1].trim()));

            } else if (command.equals("done")) {
                int indexToMark = lineSplitter.nextInt();
                taskList.get(indexToMark - 1).markAsDone();

            } else if (command.equals("delete")) {
                int indexToDelete = lineSplitter.nextInt();
                taskList.remove(indexToDelete - 1);

            }
        }
        return taskList;
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
