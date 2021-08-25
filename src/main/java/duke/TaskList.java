package duke;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }
    public TaskList() {
        this.list = new ArrayList<>();
    }


    public void addTask(Task task){
        this.list.add(task);
    }

    public void removeTask(int i){
        this.list.remove(i);
    }



//    public Task doneTask(String[] inputSplit) throws DukeException {
//        Task task = null;
//        if (inputSplit.length < 2) {
//            throw new DukeException("Please enter the number of task to mark as completed!");
//        }
//        try {
//            int index = Integer.parseInt(inputSplit[1]);
//            if (index > list.size() || index <= 0) {
//                throw new DukeException("That number is not in the list!");
//            }
//            task = list.get(index - 1);
//            task.toggleCompleted();
//        } catch (NumberFormatException e) {
//            System.out.println("Please input a proper number pls");
//        }
//        return task;
//    }

    public Task deleteTask(String[] inputSplit) {
        Task t = null;
        try {
            int i = Integer.parseInt(inputSplit[1]);
            t = list.get(i - 1);
            list.remove(i - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That is an invalid index!!");
        }
        return t;
    }

    public int size() {
        return list.size();
    }

    public Task get(int i) {
        return list.get(i);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }
}