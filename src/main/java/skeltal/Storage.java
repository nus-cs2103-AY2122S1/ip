package skeltal;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Storage {
    public static ArrayList<Task> loadFile() {
        ArrayList<Task> arrayList = new ArrayList<>();
        try {
            File taskFile = new File("src/main/Data/skeltal.txt");
            Scanner sc =  new Scanner(taskFile);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] dataPr = data.split(" \\| ");
                String taskType = dataPr[0];
                String done = dataPr[1];
                switch (taskType) {
                    case "T":
                        ToDo todo = new ToDo(dataPr[2]);
                        if (Integer.parseInt(done) == 1) {
                            todo.setComplete();
                        }
                        arrayList.add(todo);
                        break;
                    case "E":
                        Event event = new Event(dataPr[2] +" /" + dataPr[3]);
                        if (Integer.parseInt(done) == 1) {
                            event.setComplete();
                        }
                        arrayList.add(event);
                        break;
                    case "D":
                        Deadline dead = new Deadline(dataPr[2] +" /" + dataPr[3]);
                        if (Integer.parseInt(done) == 1) {
                            dead.setComplete();
                        }
                        arrayList.add(dead);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing tasks can be found! Creating a new list for you :)");
            File taskFile = new File("src/main/Data/skeltal.txt");
            try {
                taskFile.createNewFile();
            } catch (IOException ioException) {
                System.out.println("File could not be created :(");
            }

        } catch (SkeltalException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Saved tasks have been loaded into the skeltal system!");
        return arrayList;
    }

    public static void store() throws Exception {
        FileWriter fw = new FileWriter("src/main/Data/skeltal.txt", false);
        fw.write(TaskList.storeTasks());
        System.out.println("wrote to skeltal.txt");
        fw.close();
    }

}
