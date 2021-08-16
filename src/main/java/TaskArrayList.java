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
            out[num] = String.format("%d. ",num+1) +task.getName();
            num ++;
        }
        return out;
    }

}
