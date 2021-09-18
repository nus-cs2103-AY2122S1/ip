package duke;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Parser {
    String command;
    Action action;

    public Parser(String command){
        this.command = command;
        this.action = action(command);
    }

    public Action action(String command){
        String[] splited = command.split(" ", 2);

        String action1 = splited[0];
        if (action1.equals("list")){
            return Action.LIST;
        }else if (action1.equals("done")){
            return Action.DONE;
        }else if(action1.equals("todo")){
            return Action.TODO;
        }else if(action1.equals("deadline")){
            return Action.DEADLINE;
        }else if(action1.equals("event")){
            return Action.EVENT;
        }else if(action1.equals("delete")){
            return Action.DELETE;
        }else if(action1.equals("bye")){
            return Action.BYE;
        }else if(action1.equals("find")){
            return Action.FIND;
        } else{
            return Action.UNKNOWN;
        }
    }

    public Action getAction(){
        return this.action;
    }

    public int getCommandSize(){
        String[] splited = this.command.split(" ", 2);
        return splited.length;
    }

    public String getActionList(){
        String[] splited = this.command.split(" ", 2);
        return splited[1];
    }

    public boolean isValid() throws InputNotValidError{
        Set<Action> infoReqired = new HashSet<>(Arrays.asList(
                        Action.TODO, Action.DONE,Action.DONE,Action.DEADLINE,Action.EVENT));
        if (this.action == Action.UNKNOWN){
            throw new InputNotValidError("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }else{
            if(infoReqired.contains(this.action) && this.getCommandSize() <= 1){
                throw new InputNotValidError("☹ OOPS!!! The description of a " + this.getActionList() + " cannot be empty.");
            }else{
                return true;
            }
        }

    }

}
