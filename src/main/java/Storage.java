import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String filePath;

    private ArrayList<Task> list;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.list = new ArrayList<>();
    }

    public ArrayList<Task> load() throws DukeException{
        try {
            ReadDataFromFile();
        } catch (FileNotFoundException e){
            System.out.println("Cannot Read From Data.");
        }

        return this.list;
    }

    public void ReadDataFromFile() throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f);
        int index = 1;
        while (s.hasNext()) {
            String Data = s.nextLine();
            char done = HandleTaskText(Data);
            if (done == '1') {
                this.list.get(index - 1).MarkDone();
            }
            index++;
        }
    }

    public void SaveListDataToFile(TaskList Tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < Tasks.size(); i++) {
            fw.write( Tasks.get(i).GetDataInfo()+ "\n");
        }
        fw.close();
    }


    public char HandleTaskText(String Data) {
        Parser p = new Parser(Data);
        char done = Data.charAt(4);
        char taskType = Data.charAt(0);
        String task = "";
        String time = "";


        task = p.getSaveTask();
        time = p.getSaveTime();

        LocalDateTime parsedTime = p.ParseTime(time);
        TaskList.OperationType[] taskTypes = TaskList.OperationType.values();
        for (TaskList.OperationType t : taskTypes) {
            if (t.toString().toUpperCase().charAt(0) == taskType && (t.toString().equals("todo") || t.toString().equals("deadline") ||
                    t.toString().equals("event"))) {
                Task newTask = t.AssignTaskType(t, task, parsedTime);
                this.list.add(newTask);
                break;
            }
        }

        return done;
    }

}
