import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private final String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> load() throws DukeException {

        ArrayList<Task> tasklist = new ArrayList<>();

        try {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            Task task;

            while ((line = br.readLine()) != null)
            {
                String lineWithoutBoxes = line.substring(7);
                //System.out.println(lineWithoutBoxes);
                char type = line.charAt(1);

                // If the line contains by -> add a new Deadline
                if (Character.toString(type).matches("D")) {
                    String[] details = lineWithoutBoxes.split("by:");
                    String deadlineTask = details[0].substring(0, details[0].length() - 1).trim();
                    String deadline = details[1].substring(1, details[1].length() - 1);
                    task = new Deadline(deadlineTask, deadline);
                } else if (Character.toString(type).matches("E")) {
                    String[] details = lineWithoutBoxes.split("at:");
                    String eventTask = details[0].substring(0, details[0].length() - 1).trim();
                    String time = details[1].substring(1, details[1].length() - 1);
                    task = new Event(eventTask, time);
                } else {
                    task = new ToDo(lineWithoutBoxes);
                }

                // If the line contains "[X]" mark the task as done
                if (line.contains("[X]")) {
                    task.setDone(true);
                }
                tasklist.add(task);
            }

            br.close();
        } catch (IOException e) {
            throw new DukeException("File not found");
        }
        return tasklist;

    }

    public void write(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(filepath);

            // Write lines to file
            for (Task task : tasks) {
                fw.write(task + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
