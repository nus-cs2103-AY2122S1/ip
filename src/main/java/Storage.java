import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    //method to load file
    public ArrayList<Task> load() throws IOException, InvalidTaskException, InvalidDeadlineException {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(filePath);
        if (file.exists()) {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                String[] stuff = str.split(" \\| ");
                Task task;
                switch (stuff[0]) {
                    case "T":
                        task = new ToDos(stuff[2]);
                        break;
                    case "D":
                        task = new Deadline(stuff[2], stuff[3]);
                        break;
                    case "E":
                        task = new Events(stuff[2], stuff[3]);
                        break;
                    default:
                        throw new InvalidTaskException();
                }
                if (stuff[1].equals("1")) {
                    task.markAsDone();
                }
                list.add(task);
            }
        }
        return list;
    }

    //method to update list
    public void updateFile(TaskList list) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        } else {
            file.delete();
            File newFile = new File(filePath);
            newFile.createNewFile();
            FileWriter writer = new FileWriter(file);

            for (Task task : list.getList()) {
                writer.write(task.writeTask() + "\n");
            }
            writer.close();
        }
    }
}
