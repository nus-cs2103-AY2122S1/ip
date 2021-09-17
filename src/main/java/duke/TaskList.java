package duke;

import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> list;
    int curSize;
    ArrayList<String[]> data;

    public TaskList(ArrayList<String[]> data){
        this.list = new ArrayList<>();
        this.curSize = 0;
        try {
            this.LoadTask(data);
        } catch (InputNotValidError e) {
            e.printStackTrace();
        }
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
            Parser hitoryData = new Parser(actions[0] + " " + actions[1]);
            this.actionHalder(hitoryData, done, true);
        }
    }

    public String addTask(Task task, boolean fromdata){
        this.list.add(task);
        this.curSize++;
        String item = "\n" +
                "   ____________________________________________\n" +
                "   Got it. I've added this task: \n" +
                "       " + task +  "\n" +
                "   Now you have " + curSize + " tasks in the list.\n" +
                "   _____________________________________________";
        if (!fromdata){
            return item;
        }
        return "";
    }

    public String done(int num){
        Task task = this.list.get(num  - 1);
        task.doneTask();
        this.list.set(num  - 1, task);
        String item = "\n" +
                "   ____________________________________________\n" +
                "   nice Nice! I've marked this task as done: \n" +
                "       " + task.toString() + "\n" +
                "   ____________________________________________";
        return item;

    }

    public String actionHalder(Parser actionList, boolean done, boolean fromData) throws InputNotValidError{
        boolean validCommand = false;
        String res = "";
        try{
            validCommand = actionList.isValid();
        }
        catch(InputNotValidError e){
            String error =
                    "   ____________________________________________\n" +
                    "   " + e.getMessage() + '\n' +
                    "   ____________________________________________";
            res = error;
        }
        if (validCommand){
            Action actionName = actionList.getAction();
            switch(actionName){
                case LIST:
                    res =  this.printList();
                    break;
                case DONE:
                    int num = Integer.parseInt(actionList.getActionList());
                    res =  this.done(num);
                    break;
                case TODO:
                    res =  this.addToDo(actionList.getActionList(), done, fromData);
                    break;
                case DEADLINE:
                    res = this.addDeadline(actionList.getActionList(), done, fromData);
                    break;
                case EVENT:
                    res =  this.addEvent(actionList.getActionList(), done, fromData);
                    break;
                case DELETE:
                    int num2 = Integer.parseInt(actionList.getActionList());
                    res =  this.delete(num2);
                    break;
                case FIND:
                    res =  this.find(actionList.getActionList());
                    break;
                default:
                    res =  "";
            }
        }
        return res;
    }

    public String addToDo(String action, boolean done, boolean fromdata){
        ToDo newTask = new ToDo(action, done, "T");
        return this.addTask(newTask, fromdata);
    }

    public String addDeadline(String action, boolean done, boolean fromdata){
        String actionlist[] = action.split("/by");
        action = actionlist[0];
        assert action.length() >= 0;
        String date = actionlist[1];
        Deadline newTask = new Deadline(action, done, date, "D");
        return this.addTask(newTask, fromdata);
    }

    public String addEvent(String action, boolean done, boolean fromdata){
        String actionlist[] = action.split("/at");
        action = actionlist[0];
        assert action.length() >= 0;
        String date = actionlist[1];
        Event newTask = new Event(action, done, date, "E");
        return this.addTask(newTask, fromdata);
    }

    public String delete(int num){
        Task deleted = this.list.remove(num - 1);
        this.curSize--;
        String item = "\n" +
                "   ____________________________________________\n" +
                "   Noted. I've removed this task:: \n" +
                "       " + deleted.toString() + "\n" +
                "   Now you have " + curSize + " tasks in the list.\n" +
                "   ____________________________________________";
        return item;
    }

    public String find(String keyword){
        ArrayList<Task> mathingList = new ArrayList<>();
        for (Task eachtask : this.list){
            if (eachtask.actionName.contains(keyword)){
                mathingList.add(eachtask);
            }
        }
        String res = "";
        res += "    ____________________________________________\n" +
                "   Here are the matching tasks in your list:\n";
        for (int i = 0; i < mathingList.size(); i++){
            Task eachTask = mathingList.get(i);
            String each = String.valueOf(i + 1) + ". " + eachTask.toString();
            res += each + "\n";
        }
        res += "    ____________________________________________";
        return res;
    }

    public String printList(){
        return this.toString();
    }

    public String toString(){
        String res = "";
        res += "    ____________________________________________\n" +
                "   Here are the tasks in your list:\n";
        for (int i = 0; i < curSize; i++){
            Task eachTask = this.list.get(i);
            String each = String.valueOf(i + 1) + ". " + eachTask.toString();
            res += each + "\n";
        }
        res += "    ____________________________________________";
        return res;
    }

    public ArrayList<Task> returnTaskList(){
        return this.list;
    }
}
