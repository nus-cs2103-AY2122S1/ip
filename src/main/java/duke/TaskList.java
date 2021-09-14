package duke;

import java.util.Comparator;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> lst;

    /**
     * Constructor for Tasklist class
     */
    public TaskList(){
        lst = new ArrayList<>();
    }

    /**
     * method to retrieve the arraylist in a tasklist
     * @return arraylist of tasks
     */
    public ArrayList<Task> getList(){
        return this.lst;
    }

    /**
     * method to return the size of the tasklist
     * @return number of elements in the tasklist
     */
    public int size(){
        return lst.size();
    }

    /**
     * method to get a specific element in the tasklist
     * @param a, the index of the list that is to be retrieved
     * @return Task corresponding to the index a in the list
     */
    public Task get(int a){
        return lst.get(a);
    }

    /**
     * function to delete a task from the list
     * @param order
     */
    public String deleteTask(int order){
        System.out.println("Noted! I have removed this task: \n" + lst.get(order-1).toString());
        lst.remove(order-1);
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
        return "Noted! I have removed this task: \n" + lst.get(order-1).toString() +
                "\nNow you have " + lst.size() + " tasks in the list.";
    }


    /**
     * function to mark a task done in the list
     * @param order
     */
    public String markDone(int order){
        Task currentTask = lst.get(order-1);
        currentTask.completed();
        System.out.println("Nice! I've marked this task as done: \n" + currentTask.toString());
        return "Nice! I've marked this task as done: \n" + currentTask.toString();
    }

    /**
     * funtion to display all tasks in the list
     */
    public String displayList(){
        String ans = "here are the tasks in your list:";
        System.out.println("here are the tasks in your list:");
        lst.forEach(x -> System.out.println((lst.indexOf(x) + 1) + ". " + x.toString()));
        for(int i = 0; i < lst.size(); i++){
            ans += "\n" + (i + 1) + ". " + lst.get(i).toString();
        }
        return ans;
    }

    /**
     * function to add deadlines into the list
     * @param s, description of the activity that needs to be completed
     */
    public String addDeadline(String s){
        Deadline d = new Deadline(s.substring(9, s.indexOf("/")),
                s.substring(s.indexOf("/by") + 3));
        lst.add(d);
        System.out.println("Got it. I've added this deadline: \n" + d.toString());
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
        return "Got it. I've added this deadline: \n" + d.toString() +
                "\nNow you have " + lst.size() + " tasks in the list.";
    }

    /**
     * function to add deadlines into the list
     * @param s, description of the activity that needs to be completed
     * @param isCompleted, boolean of whether the activity hass been done
     */
    public String addDeadline(String s, boolean isCompleted){
        Deadline d = new Deadline(s.substring(9, s.indexOf("/")),
                s.substring(s.indexOf("/by") + 3));
        if(isCompleted)d.completed();
        lst.add(d);
        System.out.println("Got it. I've added this deadline: \n" + d.toString());
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
        return "Got it. I've added this deadline: \n" + d.toString() +
                "\nNow you have " + lst.size() + " tasks in the list.";
    }

    /**
     * function to add events into the list
     * @param s, the description of the activity
     */
    public String addEvent(String s){
        Event d = new Event(s.substring(6, s.indexOf("/")),
                s.substring(s.indexOf("/at") + 3));
        lst.add(d);
        System.out.println("Got it. I've added this event: \n" + d.toString());
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
        return "Got it. I've added this event: \n" + d.toString() +
                "\nNow you have " + lst.size() + " tasks in the list.";
    }

    /**
     * function to add events into the list
     * @param s, the description of the activity
     * @param isCompleted, boolean of whether the activity has been completed
     */
    public String addEvent(String s, boolean isCompleted){
        Event d = new Event(s.substring(6, s.indexOf("/")),
                s.substring(s.indexOf("/at") + 3));
        if(isCompleted) d.completed();
        lst.add(d);
        System.out.println("Got it. I've added this event: \n" + d.toString());
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
        return "Got it. I've added this event: \n" + d.toString() +
                "\nNow you have " + lst.size() + " tasks in the list.";
    }

    /**
     * function to add Todo into the list
     * @param s, description for the activity
     */
    public String addTodo(String s){
        Todo d = new Todo(s.substring(5));
        lst.add(d);
        System.out.println("Got it. I've added this todo: \n" + d.toString());
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
        return "Got it. I've added this todo: \n" + d.toString() +
                "\nNow you have " + lst.size() + " tasks in the list.";
    }

    /**
     * method to add a todo into the list
     * @param s, description for the activity
     * @param isCompleted, a boolean for whether the activity has been done
     */
    public String addTodo(String s, boolean isCompleted){
        Todo d = new Todo(s.substring(5));
        if(isCompleted)d.completed();
        lst.add(d);
        System.out.println("Got it. I've added this todo: \n" + d.toString());
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
        return "Got it. I've added this event: \n" + d.toString() +
                "\nNow you have " + lst.size() + " tasks in the list.";
    }

    /**
     * method for users to conduct a search for a word in a task list
     * @param keyword
     */
    public String find(String keyword){
        ArrayList<Task> temp = new ArrayList<>(this.getList());
        temp.removeIf(x -> !(x.toString().contains(keyword))); // remove any task which does not contain keyword
        System.out.println("Here are the matching tasks in your list:");
        temp.forEach(x -> System.out.println((temp.indexOf(x) + 1) + ". " + x.toString()));
        String ans = "Here are the matching tasks in your list:";
        for(int i = 0; i < temp.size(); i++){
            ans += "\n" + (i + 1) + ". " + temp.get(i).toString();
        }
        return ans;
    }

    /**
     * Sorts the list by type, in the order of Deadline, Event, Todo. Tasks of same type are alphabetically ordered
     * @return String representation of the ordered task list
     */
    public String sortByType(){
        Comparator<Task> alphabetComparator = new Comparator<Task>() {
            public int compare(Task task1, Task task2) {
                return task1.toString().compareTo(task2.toString());
            }
        };
        lst.sort(alphabetComparator);
        return displayList();
    }

    public String sortByDate(){
        Comparator<Task> dateComparator = new Comparator<Task>() {
            public int compare(Task task1, Task task2) {
                if(task1 instanceof Todo && !(task2 instanceof Todo)){
                    return 1;
                } else if (!(task1 instanceof Todo) && task2 instanceof Todo){
                    return -1;
                } else {
                    return task1.getLocalDateTime().compareTo(task2.getLocalDateTime());
                }
            }
        };
        lst.sort(dateComparator);
        return displayList();
    }

    public String sortByDateReversed(){
        Comparator<Task> dateComparator = new Comparator<Task>() {
            public int compare(Task task1, Task task2) {
                if(task1 instanceof Todo && !(task2 instanceof Todo)){
                    return 1;
                } else if (!(task1 instanceof Todo) && task2 instanceof Todo){
                    return -1;
                } else {
                    return -(task1.getLocalDateTime().compareTo(task2.getLocalDateTime()));
                }
            }
        };
        lst.sort(dateComparator);
        return displayList();
    }

}
