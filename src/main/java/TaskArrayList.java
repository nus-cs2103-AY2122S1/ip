import java.util.ArrayList;

/**
 * Array List of Task objects
 */
public class TaskArrayList extends ArrayList<Task> {
    public final static String DELETE_USAGE_TEXT = "Usage: delete <integer task number>";
    TaskArrayList(){
        super();
    }

    /**
     * Add a task to the list
     * @param task Task object to add
     * @return addition message String
     */
    public String addTask(Task task){
        this.add(task);
        return task.addMsg() + "\n" + this.newLength();
    }

    /**
     * Mark the given task as done
     * @param taskNo task number to mark as done
     * @return removal message String
     * @throws DukeException when invalid task number provided
     */
    public String deleteTask(int taskNo) throws DukeException{
        if (taskNo > this.size()){
            throw new DukeException(String.format("task %d not found",taskNo));
        }
        Task toDel = this.get(taskNo-1);
        this.remove(taskNo-1);
        return "removed : " + toDel.toString()+ "\n" + this.newLength();
    }


    private String newLength(){
        return String.format("Now you have %d %s in the list.",this.size(),this.size()==1?"task":"tasks");
    }

    /**
     * enumerate members for printing
     * @return String[] of "X. taskname"
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

    public String markDone(String[] cmd_args) throws DukeException{
        if (cmd_args.length != 2 || !cmd_args[1].matches("[0-9]+")){
            throw new DukeException("done takes exactly one integer as argument.");
        }
        return markDone(Integer.parseInt(cmd_args[1]));
    }

    public String markDone(int index) throws DukeException{
        if (index > this.size()){
            throw new DukeException(String.format("task %d not found",index));
        }
        this.get(index-1).markDone();
        return "Nice! I've marked this task as done:\n"+
                this.get(index-1).toString();
    }
}
