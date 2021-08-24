import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    //old
//    public static void completeTask(int taskID, ArrayList<Task> arr) {
//        System.out.println("---------------------------------------------");
//        System.out.println("Nice! I've marked this task as done:");
//        arr.get(taskID - 1).markAsDone();
//        System.out.println("---------------------------------------------");
//    }
    public void completeTask(int taskID) {
        System.out.println("---------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        this.tasks.get(taskID - 1).markAsDone();
        System.out.println("---------------------------------------------");
    }

    //old
//    public static void addTask(Task newTask, ArrayList<Task> arr) {
//        arr.add(newTask);
//        System.out.println("---------------------------------------------\n"
//                + "     Got it. I've added this task:\n"
//                + newTask.toString() + "\n"
//                + "Now you have " + arr.size() + " task(s) in the list.\n"
//                + "---------------------------------------------");
//    }
    public void addTask(Task newTask) {
        this.tasks.add(newTask);
        System.out.println("---------------------------------------------\n"
                + "     Got it. I've added this task:\n"
                + newTask.toString() + "\n"
                + "Now you have " + this.tasks.size() + " task(s) in the list.\n"
                + "---------------------------------------------");
    }

    //old
//    public static void listItems(ArrayList<Task> arr) {
//        System.out.println("---------------------------------------------");
//        System.out.println("Here are the tasks in your list:");
//        for (int i = 0; i < arr.size(); i++) {
//            int j = i + 1;
//            System.out.println("     " + j + ". "
//                    + arr.get(i).toString());
//        }
//        System.out.println("---------------------------------------------");
//    }
    public void listItems() {
        System.out.println("---------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            int j = i + 1;
            System.out.println("     " + j + ". "
                    + this.tasks.get(i).toString());
        }
        System.out.println("---------------------------------------------");
    }

    //old
//    public static void deleteTask(int taskID, ArrayList<Task> arr) throws IllegalArgumentException{
//        if (taskID <=0 || taskID > arr.size()) {
//            System.out.println("---------------------------------------------");
//            System.out.println("No such task exists.");
//            System.out.println("---------------------------------------------");
//            throw new IllegalArgumentException();
//        } else {
//            System.out.println("---------------------------------------------");
//            System.out.println("Noted I've removed this task:");
//            System.out.println(arr.get(taskID - 1).toString());
//            arr.remove(taskID - 1);
//            System.out.println("Now you have " + arr.size() + " task(s) in the list.");
//            System.out.println("---------------------------------------------");
//        }
//    }
    public void deleteTask(int taskID) throws IllegalArgumentException{
        if (taskID <=0 || taskID > this.tasks.size()) {
            System.out.println("---------------------------------------------");
            System.out.println("No such task exists.");
            System.out.println("---------------------------------------------");
            throw new IllegalArgumentException();
        } else {
            System.out.println("---------------------------------------------");
            System.out.println("Noted I've removed this task:");
            System.out.println(this.tasks.get(taskID - 1).toString());
            this.tasks.remove(taskID - 1);
            System.out.println("Now you have " + this.tasks.size() + " task(s) in the list.");
            System.out.println("---------------------------------------------");
        }
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task getIndividualTask(int id) {
        return this.tasks.get(id);
    }
}
