import java.util.ArrayList;

/**
 * Array List of Task objects
 */
public class TaskArrayList extends ArrayList<Task> {
    TaskArrayList(){
        super();
    }

    /**
     * enumerate members for printing
     * @return String[] of "X. taskname" style
     */
    public String[] enumerate(){
        String[] out = new String[this.size()];

        int num = 0;
        for (Task task: this){
            out[num] = String.format("%d. ",num+1) +task.toString();
            num ++;
        }
        return out;
    }
    public String[] markDone(int index){
        if (index >= this.size()){
            return new String[]{String.format("task %d not found",index)};
        }
        this.get(index-1).markDone();
        return new String[]{
                "Nice! I've marked this task as done:",
                this.get(index-1).toString()};
    }
}
