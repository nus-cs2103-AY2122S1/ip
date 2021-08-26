import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private ArrayList<Task> taskList;

    public Storage(String filePath) {
        this.filePath = filePath;
        taskList = new ArrayList<>();
    }

    public ArrayList<Task> load() throws DukeException {
        taskList.clear();

        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String item = myReader.nextLine();
                String[] splitItem = item.split("\\|");
                String taskType = splitItem[0];
                String completed = splitItem[1];
                String desc = splitItem[2];
                String date = "";
                if (splitItem.length > 3) date = splitItem[3];
                Task nextTask = null;
                switch (taskType) {
                case ("T"):
                    nextTask = new Todo(desc);
                    break;
                case ("D"):
                    nextTask = new Deadline(desc, date);
                    break;
                case ("E"):
                    nextTask = new Event(desc, date);
                }
                if (completed.equals("1") && nextTask != null) nextTask.markAsDone();
                taskList.add(nextTask);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Creating new file with given path.");
            try {
                File myObj = new File(filePath);
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException ioe) {
                throw new DukeException("Error occurred while creating the file");
            }
        }
        return this.taskList;
    }

    public void add(Task task) throws DukeException {
        String newString = "\n" + task.toStorageString();
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.append(newString);
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Error saving new task");
        }
    }

    public void rewrite(TaskList tl) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(tl.toStorageString());
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Error deleting or marking task as done");
        }
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < taskList.size(); i++) {
            Task nextTask = taskList.get(i);
            result += "\n" + nextTask.toStorageString();
        }

        result = result.trim();
        return result;
    }
}
