package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;

public class TaskList {

    private final ArrayList<Task> arrayList;
    private final File dataFile;

    public TaskList(){
        this.arrayList = new ArrayList<>();
        this.dataFile = null;
    }

    public TaskList(File dataFile) {
        this.arrayList = new ArrayList<>();
        this.dataFile = dataFile;

        try {
            Scanner sc = new Scanner(dataFile);
            String fileLine;
            while (sc.hasNext()) {
                fileLine = sc.nextLine();
                if (fileLine.startsWith("[T]", 3)) {
                    this.addToDo(fileLine.substring(10));
                } else if (fileLine.startsWith("[D]", 3)) {
                    String[] subStringArray = fileLine.substring(10).split(" \\(by: ", 2);
                    String deadLineStr = subStringArray[1];
                    this.addDeadLine(subStringArray[0], deadLineStr.
                            substring(0, deadLineStr.length() - 1));
                } else if (fileLine.startsWith("[E]", 3)) {
                    String[] subStringArray = fileLine.substring(10).split(" \\(at: ", 2);
                    String deadLineStr = subStringArray[1];
                    this.addEvent(subStringArray[0], deadLineStr.
                            substring(0, deadLineStr.length() - 1));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, should have been created already");

        }

    }


    public String addToDo(String description) {
        ToDo toDo;
        try {
            toDo = new ToDo(description);
        } catch (IllegalArgumentException e){
            return "??? " + e.getMessage();
        }
        this.arrayList.add(toDo);
        return "Added this ToDo task:\n" + toDo.toString();

    }

    public String addDeadLine(String description, String deadline) {
        Deadline dl;
        try {
            dl = new Deadline(description, deadline);
        } catch (IllegalArgumentException e){
            return "??? " + e.getMessage();
        }
        this.arrayList.add(dl);
        return "Added this Deadline task:\n" + dl.toString();
    }

    public String addEvent(String description, String time) {
        Event event;
        try {
            event = new Event(description, time);
        } catch (IllegalArgumentException e){
            return "??? " + e.getMessage();
        }
        this.arrayList.add(event);
        return "Added this Event task:\n" + event.toString();
    }

    public String list() {
        int i = 1;
        String returnString = "";
        for (Task t : this.arrayList) {
            returnString += String.valueOf(i) + ". " + t.toString() + "\n";
            i++;
        }
        return returnString;
    }

    public String markDone(int index) {
        Task t = this.arrayList.get(index - 1);
        boolean isValid = t.markDone();
        if (isValid) {
            return "Nice! This task is marked as done\n" + t.toString();
        } else {
            return "";
        }
    }
    public String delete(int index) {
        Task t = this.arrayList.get(index - 1);
        this.arrayList.remove(index - 1);
        return "Noted. Removed this task:\n" + t.toString();
    }

    public String findTask(String searchString) {
        String result = "";
        for (Task t : this.arrayList) {
            if (t.toString().contains(searchString)) {
                result += t.toString();
            }
        }
        return result;
    }

    public void saveToFile() {
        if (this.dataFile != null) {
            try {
                FileWriter writer = new FileWriter(this.dataFile);
                writer.write(this.list());
                writer.close();
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }
    }
}
