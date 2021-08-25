import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    public String path;

    public Storage(String path) {
        this.path = path;
    }

    public void saveData() {
        try {
            File file = new File("./data/saved-tasks.txt");
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file, false);
            for(Task task: Tasklist.dukeList) {
                String commandLine = "";
                if(task instanceof Deadline) {
                    commandLine = "D | " + (task.isDone ? "1 | " : "0 | ") + task.description + " | " + ((Deadline) task).time + '\n';
                } else if(task instanceof Event) {
                    commandLine = "E | " + (task.isDone ? "1 | " : "0 | ") + task.description + " | " + ((Event) task).time + '\n';
                } else {
                    commandLine =  "T | " + (task.isDone ? "1 | " : "0 | ") + task.description + '\n';
                }
                writer.write(commandLine);
                writer.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fetchData() throws IOException {
        File savedFile = new File("./data/saved-tasks.txt");
        if(!savedFile.exists()) {
            savedFile.createNewFile();
        }
        Scanner scanner = new Scanner(savedFile);
        while (scanner.hasNextLine()) {
            String[] keywords = scanner.nextLine().split(" \\| ");
            Task cur = null;
            switch (keywords[0]) {
                case "T":
                    cur = new ToDo(keywords[2]);
                    break;
                case "D":
                    cur = new Deadline(keywords[2], keywords[3]);
                    break;
                case "E":
                    cur = new Event(keywords[2], keywords[3]);
                    break;
                default:
                    System.out.println("error");
                    break;
            }

            if (keywords[1].equals("1")) {
                cur.completeTask();
            }
            Tasklist.dukeList.add(cur);
        }
        scanner.close();
    }
}
