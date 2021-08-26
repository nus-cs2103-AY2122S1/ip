import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class TaskList {
    private ArrayList<Task> list;
    private int numTask;

    public TaskList() {
        list = new ArrayList<>();
    }

    public void addTask(Task t) {
        list.add(t);
        numTask++;
        System.out.println("I have added this task: \n" + t);
    }

    public void loadTask(Task t) {
        list.add(t);
        numTask++;
    }

    public void deleteTask(int i) {
        numTask--;
        System.out.println("Noted. I have removed this task: \n" + list.remove(i));
    }

    public void printList() {
        System.out.println("Here are the tasks in your list: \n");
        for(int i = 0; i < list.size(); i++) {
            int index = i + 1;
            System.out.println( index + ". " + list.get(i));
        }
    }

    public void setDone(int i) {
        this.list.get(i).setDone();
        System.out.println("I've marked this task as done: \n" + list.get(i));
    }

    public Task get(int i) {
        return this.list.get(i);
    }

    public int getNumTask() {
        return numTask;
    }
//    private void writeToFile(String text) throws IOException {
//
//        fw.write(text);
//        fw.close();
//    }

    public void saveTask(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : list) {
            fw.write(t.getSavedAs() + System.lineSeparator());
        }
        fw.close();
    }



}
