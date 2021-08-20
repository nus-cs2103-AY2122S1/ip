import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TaskList {
    ArrayList<Task> list;
    int curSize;
    public TaskList(){
        this.list = new ArrayList<>();
        this.curSize = 0;
    }

    public void LoadTask(ArrayList<String[]> data) throws InputNotValidError{
        for (String[] each : data){
            String[] actions = new String[2];
            String actionName = each[0];
            Boolean done = true;
            if(each[1].equals(" 0 ")){
                done = false;
            }
            if (actionName.equals("T ")){
                actions[0] = "todo";
                actions[1] = each[2].substring(1, each[2].length());
            }else if(actionName.equals("E ")){
                actions[0] = "event";
                actions[1] = each[2].substring(1) + "/at"  + each[3].substring(1, each[3].length());
            }else if(actionName.equals("D ")){
                actions[0] = "deadline";
                actions[1] = each[2].substring(1) + "/by" + each[3].substring(1, each[3].length());                
            }
            this.actionHalder(actions, done, true);
        }

    }

    public void addTask(Task task, boolean fromdata){
        this.list.add(task);
        this.curSize++;
        String item = "\n" +
        "   ____________________________________________________________\n" +
        "   Got it. I've added this task: \n" + 
        "       " + task +  "\n" +
        "   Now you have " + curSize + " tasks in the list.\n" +
        "   ____________________________________________________________";
        if (!fromdata){
            System.out.println(item);
        }
    }

    public void done(int num){
        Task task = this.list.get(num  - 1);
        task.doneTask();
        this.list.set(num  - 1, task);
        String item = "\n" +
        "   ____________________________________________________________\n" +
        "   nice Nice! I've marked this task as done: \n" +
        "       " + task.toString() + "\n" + 
        "   ____________________________________________________________";
        System.out.println(item);

    }

    public void isValidAction(String[] actionList) throws InputNotValidError{
        Set<String> validactionset = new HashSet<>(Arrays.asList(
                                            "list", "done", "todo", "deadline", "event", "delete"));
        Set<String> infoReqired = new HashSet<>(Arrays.asList(
                                            "todo", "deadline", "event", "delete"));
        String actionName = actionList[0];
        if (!validactionset.contains(actionName)){
            throw new InputNotValidError("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }else{
            if ((infoReqired.contains(actionName)) && (actionList.length <= 1)){
                throw new InputNotValidError("☹ OOPS!!! The description of a " + actionName + " cannot be empty.");
            }
        }
    }

    public void actionHalder(String[] actionList, boolean done, boolean fromData) throws InputNotValidError{
        try{
            this.isValidAction(actionList);
        }
        catch(InputNotValidError e){
            String error = 
            "   ____________________________________________________________\n" +
            "   " + e.getMessage() + '\n' +
            "   ____________________________________________________________";
            System.out.println(error);
            return;
        }
          
        String actionName = actionList[0];

        if (actionName.equals("list")){
            this.printList();
        }else if (actionName.equals("done")){
            int num = Integer.parseInt(actionList[1]);
            this.done(num);
        }else if(actionName.equals("todo")){
            this.addToDo(actionList[1], done, fromData);
        }else if(actionName.equals("deadline")){
            this.addDeadline(actionList[1], done, fromData);
        }else if(actionName.equals("event")){
            this.addEvent(actionList[1], done, fromData);
        }else if(actionName.equals("delete")){
            int num = Integer.parseInt(actionList[1]);
            this.delete(num);
        }else{
            System.out.println("something wrong");
        }

    }
    
    public void addToDo(String action, boolean done, boolean fromdata){
        ToDo newTask = new ToDo(action, done, "T");
        this.addTask(newTask, fromdata);
    }

    public void addDeadline(String action, boolean done, boolean fromdata){
        String actionlist[] = action.split("/by");
        action = actionlist[0];
        String date = actionlist[1];
        Deadline newTask = new Deadline(action, done, date, "D");
        this.addTask(newTask, fromdata);
    }

    public void addEvent(String action, boolean done, boolean fromdata){
        String actionlist[] = action.split("/at");
        action = actionlist[0];
        String date = actionlist[1];
        Event newTask = new Event(action, done, date, "E");
        this.addTask(newTask, fromdata);
    }

    public void delete(int num){
        Task deleted = this.list.remove(num - 1);
        this.curSize--;
        String item = "\n" +
        "   ____________________________________________________________\n" +
        "   Noted. I've removed this task:: \n" +
        "       " + deleted.toString() + "\n" + 
        "   Now you have " + curSize + " tasks in the list.\n" +
        "   ____________________________________________________________";
        System.out.println(item);
    }

    public void printList(){
        System.out.println(this.toString());
    }

    public String toString(){
        String res = "";
        res += "    ____________________________________________________________\n" + 
        "   Here are the tasks in your list:\n";
        for (int i = 0; i < curSize; i++){
            Task eachTask = this.list.get(i);
            String each = String.valueOf(i + 1) + ". " + eachTask.toString();
            res += each + "\n";
        }
        res += "    ____________________________________________________________";
        return res;
    }

    public ArrayList<Task> returnTaskList(){
        return this.list;
    }


}
