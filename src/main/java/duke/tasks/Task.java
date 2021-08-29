package duke.tasks;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;


public class Task {

    private final String name;
    private boolean done;

    Task(String name, boolean done){
        this.name = name;
        this.done = done;
    }

    public void markDone() {
        this.done = true;
    }

    public void markUndone() {
        this.done = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", (this.done)? "X" : " ", this.name);
    }

    public String toStringOutput() {
        return String.format("[%s] %s", (this.done)? "X" : " ", this.name);
    }

    String getName(){
        return this.name;
    }

    Boolean getDone(){
        return this.done;
    }

    public static void saveTaskList(ArrayList<Task> aList, String filename) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+filename));

        try {
            File file = new File(System.getProperty("user.dir")+filename);

            if (!file.exists()) {
                file.createNewFile();
            }

            for (int i = 1; i <= aList.size(); i++){
                writer.write(aList.get(i - 1).toStringOutput());
                writer.newLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            writer.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public static String parseDateTime(String input) {
        LocalDate dateTime = LocalDate.parse(input);
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

}
