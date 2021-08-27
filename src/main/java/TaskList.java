import java.util.ArrayList;
import java.util.List;

public class TaskList {
    
    
    private final  List<Task> items = new ArrayList<>(100);

    
    
    public void markDone(int n){
        items.get(n).markAsDone();
    }
    
    private void addTask(Task task){
        items.add(task);
    }

    private String delete(String userInput){
        try{
            int task = Integer.parseInt(userInput.substring(7));
            if(task > 0 && task <= items.size()){
                Task t = items.remove(task - 1);
                return "Got it sir, I have removed this task:\n " + userInput + "\nNow you have " + items.size() + " tasks in the list.\n";
            } else {
                return "You have entered an invalid task number Sir, please input again.\n";
            }
        } catch (NumberFormatException e){
            return "Task number formatted incorrectly. Try again\n";
        }
    }

    private String done(String userInput){
        try{
            int task = Integer.parseInt(userInput.substring(5));
            if(task > 0 && task <= items.size()){
                markDone(task - 1);
                return "One task down sir. Here is the task I checked off:\n" + 
                        "    " + items.get(task - 1).toString() + "\n";
            } else {
                return "You have entered an invalid task number Sir, please input again.\n";
            }
        } catch (NumberFormatException e){
            return "Task number formatted incorrectly. Try again\n";
        }
    }

    public void list(){
        for(int i = 1; i <= items.size(); i++){
            System.out.println(i + ". " + items.get(i-1).toString());
        }
        System.out.println("");
    }

    
    
}
