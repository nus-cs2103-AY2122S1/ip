import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {

    File file;



    public Database() {
        try {
            file = new File("todoList.txt");
            if (file.createNewFile()) {

            } else {

            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
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
        if (s[2].equals("false")) {
            isDone = false;
        } else {
            isDone = true;
        }


        switch (s[0]) {
            case "T":
                Todo todo = new Todo(s[1], isDone, Integer.valueOf(s[3]));
                return todo;
            case "E":
                Event event =  new Event(s[1], isDone, Integer.valueOf(s[3]), s[4]);
                return event;
            case "D":
                Deadline deadline = new Deadline(s[1],isDone, Integer.valueOf(s[3]), s[4]);
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
        Database d = new Database();
        d.getData();
    }

}
