package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> lst;

    public TaskList(){
        this.lst = new ArrayList<>();
    }

    public ArrayList<Task> getList(){
        return this.lst;
    }

    public int size(){
        return lst.size();
    }

    public Task get(int a){
        return lst.get(a);
    }

    /**
     * function to delete a task from the list
     * @param order
     */
    public void deleteTask(int order){
        System.out.println("Noted! I have removed this task: \n" + lst.get(order-1).toString());
        lst.remove(order-1);
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }

    /**
     * function to mark a task done in the list
     * @param order
     */
    public void markDone(int order){
        Task currentTask = lst.get(order-1);
        currentTask.completed();
        System.out.println("Nice! I've marked this task as done: \n" + currentTask.toString());
    }

    /**
     * funtion to display all tasks in the list
     */
    public void displayList(){
        System.out.println("here are the tasks in your list:");
        lst.forEach(x -> System.out.println((lst.indexOf(x) + 1) + ". " + x.toString()));
    }

    /**
     * function to add deadlines into the list
     * @param s
     */
    public void addDeadline(String s){
        Deadline d = new Deadline(s.substring(9, s.indexOf("/")),
                s.substring(s.indexOf("/by") + 3));
        lst.add(d);
        System.out.println("Got it. I've added this deadline: \n" + d.toString());
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }

    public void addDeadline(String s, boolean isCompleted){
        Deadline d = new Deadline(s.substring(9, s.indexOf("/")),
                s.substring(s.indexOf("/by") + 3));
        if(isCompleted)d.completed();
        lst.add(d);
        System.out.println("Got it. I've added this deadline: \n" + d.toString());
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }

    /**
     * function to add events into the list
     * @param s
     */
    public void addEvent(String s){
        Event d = new Event(s.substring(6, s.indexOf("/")),
                s.substring(s.indexOf("/at") + 3));
        lst.add(d);
        System.out.println("Got it. I've added this event: \n" + d.toString());
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }

    public void addEvent(String s, boolean isCompleted){
        Event d = new Event(s.substring(6, s.indexOf("/")),
                s.substring(s.indexOf("/at") + 3));
        if(isCompleted) d.completed();
        lst.add(d);
        System.out.println("Got it. I've added this event: \n" + d.toString());
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }

    /**
     * function to add Todo into the list
     * @param s
     */
    public void addTodo(String s){
        Todo d = new Todo(s.substring(5));
        lst.add(d);
        System.out.println("Got it. I've added this todo: \n" + d.toString());
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }

    public void addTodo(String s, boolean isCompleted){
        Todo d = new Todo(s.substring(5));
        if(isCompleted)d.completed();
        lst.add(d);
        System.out.println("Got it. I've added this todo: \n" + d.toString());
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }
}
