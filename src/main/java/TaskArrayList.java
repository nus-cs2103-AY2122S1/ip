import java.util.ArrayList;

/**
 * Array List of Task objects
 */
public class TaskArrayList extends ArrayList<Task> {
    TaskArrayList(){
        super();
    }


    public String newLength(){
        return String.format("Now you have 5 tasks in the list.");
    }

    /**
     * enumerate members for printing
     * @return String[] of "X. taskname" style
     */
    public String enumerate(){
        String out  = "";
        int num = 0;
        for (Task task: this){
            out += String.format("%d. ",num+1) +task.toString() + "\n";
            num ++;
        }
        return out;
    }
    public String markDone(int index){
        if (index >= this.size()){
            return String.format("task %d not found",index);
        }
        this.get(index-1).markDone();
        return "Nice! I've marked this task as done:\n"+
                this.get(index-1).toString();
    }
}
