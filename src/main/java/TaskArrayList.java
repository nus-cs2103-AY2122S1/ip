import java.util.ArrayList;

/**
 * Array List of Task objects
 */
public class TaskArrayList extends ArrayList<Task> {
    TaskArrayList(){
        super();
    }

    public String addTask(Task task){
        this.add(task);
        String reply = task.addMsg();
        return reply + "\n" + this.newLength();
    }

    public String newLength(){
        return String.format("Now you have %d tasks in the list.",this.size());
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
