package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class DukeDB {
    private File file;

    public DukeDB(String filePath) {
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> readData() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {

            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String s = scanner.nextLine();
                Task t = parse(s);
                tasks.add(t);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void addData(Task task) {
        try {
            FileWriter fileWriter = new FileWriter(file,true);
            String out = "\n";

            if (task instanceof Deadline) {
                out += "D &";
            } else if (task instanceof Todo) {
                out += "T &";
            } else if (task instanceof Event) {
                out += "E &";
            }

            if(task.isDone()) {
                out += " 1 & ";
            } else {
                out += " 0 & ";
            }

            out += task.getDescription();
            if(task instanceof Deadline) {
                out += " & " + ((Deadline) task).getBy().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hhmm"));
            }
            if(task instanceof Event) {
                out += " & " + ((Event) task).getAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hhmm"));
            }
            fileWriter.write(out);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void entireWriteData (ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for(int i = 0 ; i < tasks.size();i++) {

                Task task = tasks.get(i);
                String out;
                if(i > 0) {
                    out = "\n";
                } else {
                    out = "";
                }
                if (task instanceof Deadline) {
                    out += "D &";
                } else if (task instanceof Todo) {
                    out += "T &";
                } else if (task instanceof Event) {
                    out += "E &";
                }

                if (task.isDone()) {
                    out += " 1 & ";
                } else {
                    out += " 0 & ";
                }

                out += task.getDescription();
                if (task instanceof Deadline) {
                    out += " & " + ((Deadline) task).getBy().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hhmm"));
                }
                if (task instanceof Event) {
                    out += " & " + ((Event) task).getAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hhmm"));
                }
                fileWriter.write(out);
            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void doneData(int index) {
        ArrayList<Task> readOut = readData();
        Task temp = readOut.get(index);
        temp.markAsDone();
        readOut.set(index, temp);
        entireWriteData(readOut);
    }

    public void deleteData(int index) {
        ArrayList<Task> readOut = readData();
        readOut.remove(index);
        entireWriteData(readOut);
    }

    //from: E & 0 & project meeting & 6/8/2021 1400
    //  to: Task
    public Task parse(String string) {
        Task task;
        boolean isDone = false;
        String[] str = string.split("&");
        for(int i = 0; i < str.length; i++) {
            str[i] = str[i].trim();
        }

        if (str[1].equals("1")) {
            isDone = true;
        }

        String item;
        String time;
        switch (str[0]) {
        case "T":
            item = str[2];
            task = new Todo(item);
            if(isDone) {
                task.markAsDone();
            }
            break;
        case "E":
            item = str[2];
            time = str[3];
            task = new Event(item, time);
            if(isDone) {
                task.markAsDone();
            }
            break;
        case "D":
            item = str[2];
            time = str[3];
            task = new Deadline(item, time);
            if(isDone) {
                task.markAsDone();
            }
            break;
        default:
            task = null;
        }

        return task;
    }

    public static void main(String[] args) throws IOException {
        DukeDB data = new DukeDB("data/tasks.txt");

        data.addData(new Todo("hahaha"));
        ArrayList<Task> lst = data.readData();
        for(int i = 0 ; i < lst.size(); i++) {
            System.out.println(lst.get(i));
        }
        data.deleteData(1);
        data.doneData(0);
    }
}
