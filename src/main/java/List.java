import java.util.ArrayList;

public class List {
    private ArrayList<Task> list;

    public List() {
        list = new ArrayList<>();
    }

    public void addToDo(String text) {
        Task newToDo = new ToDo(text);
        list.add(newToDo);
        System.out.println("Got it. I've added this task: ");
        System.out.println(newToDo.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void addDeadline(String text, String by) {
        Task newDl = new Deadline(text, by);
        list.add(newDl);
        System.out.println("Got it. I've added this task: ");
        System.out.println(newDl.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void addEvent(String text, String at) {
        Task newEvent = new Event(text, at);
        list.add(newEvent);
        System.out.println("Got it. I've added this task: ");
        System.out.println(newEvent.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void setIndexDone(int index) {// starts from 1
        if(index > list.size()){
            System.out.println("There is no task " + index);
            return;
        }
        list.get(index - 1).setDone();
        System.out.println(list.get(index - 1).toString());
    }

    public void show() {
        int length = list.size();
        for(int i = 1; i <= length; i++) {
            System.out.println(i + "." + list.get(i - 1).toString());
        }
    }
}
