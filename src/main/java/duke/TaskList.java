package duke;

import duke.Deadline;
import duke.Event;
import duke.Task;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class TaskList {
    private ArrayList<Task> list;
    private int numTask;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<String> tasks) {
        this.list = new ArrayList<>();
        for (String s: tasks) {
            numTask++;
            String[] array = s.split("\\|");

            switch (array[0]) {
                case "T": {
                    this.loadTask(new Todo(array[2], Boolean.parseBoolean(array[1])));
                    break;
                }
                case "D": {
                    this.loadTask(new Deadline(array[2], LocalDateTime.parse(array[3]), Boolean.parseBoolean(array[1])));
                    break;
                }
                case "E": {
                    this.loadTask(new Event(array[2], LocalDateTime.parse(array[3]), Boolean.parseBoolean(array[1])));
                    break;
                }
            }
        }

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

    public ArrayList<Task> getList() {
        return this.list;
    }

    public int getNumTask() {
        return numTask;
    }
//    private void writeToFile(String text) throws IOException {
//
//        fw.write(text);
//        fw.close();
//    }



}
