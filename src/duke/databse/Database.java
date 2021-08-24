package duke.databse;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {

    File file;



    public Database(String filePath) {
        try {
            file = new File(filePath);
            if (file.createNewFile()) {

            } else {

            }

        } catch (IOException e) {
            System.out.println("An error occurred in creating or opening file.");
        }


    }



    public ArrayList<Task> getData() {
        ArrayList<Task> objectsList = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                objectsList.add(parseData(data));
            }
        } catch (IOException e) {

        }

        return objectsList;
    }

    public Task parseData(String data) {
        String[] s = data.split(" ");

        boolean isDone;
        if (s[s.length - 1].equals("false")) {
            isDone = false;
        } else {
            isDone = true;
        }


        switch (s[0]) {
            case "T":
                Todo todo = new Todo(s[1], isDone);
                return todo;
            case "E":
                String taskname_event = "";
                String tasktime_event = "";
                boolean timepart_event = false;
                for (int i = 1; i < s.length - 1; i++) {
                    if (s[i].startsWith("/")) {
                        timepart_event = true;
                        tasktime_event = s[i].substring(1);
                    } else if (timepart_event) {
                        tasktime_event += " " + s[i];
                    } else {
                        if (s[i + 1].startsWith("/")) {
                            taskname_event += s[i];
                        } else {
                            taskname_event += s[i] + " ";
                        }

                    }
                }
                Event event =  new Event(taskname_event, isDone, tasktime_event);
                return event;
            case "D":
                String taskname_ddl = "";
                String tasktime_ddl = "";
                boolean timepart_ddl = false;
                for (int i = 1; i < s.length - 1; i++) {
                    if (s[i].startsWith("/")) {
                        timepart_ddl = true;
                        tasktime_ddl = s[i].substring(1);
                    } else if (timepart_ddl) {
                        tasktime_ddl += " " + s[i];
                    } else {
                        if (s[i + 1].startsWith("/")) {
                            taskname_ddl += s[i];
                        } else {
                            taskname_ddl += s[i] + " ";
                        }
                    }
                }
                Deadline deadline = new Deadline(taskname_ddl,isDone, tasktime_ddl);
                //System.out.println(deadline.toString());
                return deadline;
        }
        return null;
    }

    public void writeToDatabase(Task todo) {
        try {
            BufferedWriter out = new BufferedWriter(
                    new FileWriter("todoList.txt", true));
            out.write(todo.toString());
            out.close();


        }
        catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }


    public static void main(String args[]) {
        Database d = new Database("todoList.txt");
        d.getData();
    }

    public void updateData(Task task, int index) {
        String data = "";
        try {
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {
                if (index == 1) {
                    myReader.nextLine();
                    data += task.toString();
                } else {
                    data += myReader.nextLine() + "\n";
                }
                index--;
            }
            myReader.close();

        } catch (IOException e) {

        }

        try {
            FileWriter fw = new FileWriter(file);
            fw.write(data);
            fw.close();
        } catch (IOException e) {

        }


    }

    public void deleteData(int index) {
        String data = "";
        try {
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {
                if (index == 1) {
                    myReader.nextLine();

                } else {
                    data += myReader.nextLine() + "\n";
                }
                index--;
            }
            myReader.close();

        } catch (IOException e) {

        }

        try {
            FileWriter fw = new FileWriter(file);
            fw.write(data);
            fw.close();
        } catch (IOException e) {

        }
    }

}