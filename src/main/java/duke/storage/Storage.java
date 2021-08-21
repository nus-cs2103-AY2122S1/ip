package duke.storage;

import duke.tasks.Task;
import duke.tasks.Event;
import duke.tasks.Deadline;
import duke.tasks.Todo;

import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    final String TERMINATOR = "/@";

    public void makeDir() {
        File f = new File("data");
        boolean bool = f.mkdir();
        if(bool){
            System.out.println("Directory created successfully");
        }else{
            System.out.println("Sorry couldn’t create specified directory");
        }
    }

    public void checkFile() {
        try {
            File myObj = new File("data/duke.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> load = new ArrayList<>();
        try {
            File tasks = new File("data/duke.txt");
            Scanner myReader = new Scanner(tasks);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split("/@", 0);
                String marker = data[0];
                boolean isDone = (data[1].equals("1"));
                String name = data[2];
                String time = data[3];

                switch (marker) {
                    case "D":
                        Task t = new Deadline(name, time, isDone);
                        load.add(t);
                        break;
                    case "T":
                        t = new Todo(name, isDone);
                        load.add(t);
                        break;
                    case "E":
                        t = new Event(name, time, isDone);
                        load.add(t);
                        break;
                    default:
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
        return load;
    }

    public void saveTasks(ArrayList<Task> tasks) {

        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < tasks.size(); i++) {
                StringBuilder s = new StringBuilder();
                Task current = tasks.get(i);
                s.append(current.getMarker());
                s.append(TERMINATOR);
                s.append(current.getDone());
                s.append(TERMINATOR);
                s.append(current.getName());
                s.append(TERMINATOR);
                s.append(current.getTime());

                String temp = s.toString();
                bw.write(temp);
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
