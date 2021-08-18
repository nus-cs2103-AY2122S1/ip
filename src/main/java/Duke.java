import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> items = new ArrayList<Task>(100);
    public static void main(String[] args) {
        Scanner userSc = new Scanner(System.in);
        String name = "JARVIS";
        System.out.println("Hello I am " + name +".\nIs there anything I can do for you Sir?\n");

        String userInput = userSc.nextLine();
        while(!userInput.equals("bye")){
            if(userInput.equals("list")){
                list();
            } else if(userInput.length() > 3 && userInput.substring(0, 4).equals("done") ){
                try{
                    int task = Integer.parseInt(userInput.substring(5));
                    if(task > 0 && task <= items.size()){
                        markDone(task - 1);
                        System.out.println("One task down sir. Here is the task I checked off:");
                        System.out.println("    " + items.get(task - 1).toString() + "\n");
                    } else {
                        System.out.println("You have entered an invalid task number Sir, please input again.\n");
                    }
                } catch (NumberFormatException e){
                    System.out.println("Task number formatted incorrectly. Try again\n");
                }
            } else if(userInput.length() > 5 && userInput.substring(0, 6).equals("delete")){
                try{
                    int task = Integer.parseInt(userInput.substring(7));
                    if(task > 0 && task <= items.size()){
                        deleteTask(task - 1);
                    } else {
                        System.out.println("You have entered an invalid task number Sir, please input again.\n");
                    }
                } catch (NumberFormatException e){
                    System.out.println("Task number formatted incorrectly. Try again\n");
                }
            } else if(userInput.length() > 8 && userInput.substring(0, 8).equals("deadline")  && userInput.contains("/by")){

                if(userInput.indexOf("/by")  < 11 ){
                    System.out.println("Enter a valid deadline activity description\n");
                } else if(userInput.length() <= userInput.indexOf("/by") +  4){
                    System.out.println("Enter a valid deadline time\n");
                } else {
                    String description = userInput.substring(9, userInput.indexOf("/by") - 1);
                    String by = userInput.substring(userInput.indexOf("/by") + 4);
                    Task t =  new Deadline(description, by);
                    items.add(t);
                    echo(t.toString(), "added");
                }
            } else if(userInput.length() > 5 && userInput.substring(0, 5).equals("event")  && userInput.contains("/at")){
                if(userInput.indexOf("/at")  < 8 ){
                    System.out.println("Enter a valid event activity description\n");
                } else if(userInput.length() <= userInput.indexOf("/at") +  4){
                    System.out.println("Enter a valid event time\n");
                } else {
                    String description = userInput.substring(6, userInput.indexOf("/at") - 1);
                    String at = userInput.substring(userInput.indexOf("/at") + 4);
                    Task t =  new Event(description, at);
                    items.add(t);
                    echo(t.toString(), "added");
                }

            } else if(userInput.length() > 3 && userInput.substring(0, 4).equals("todo")){
                try {
                    readActivity(userInput.substring(5), "todo");
                    String description = userInput.substring(5);
                    Task t = new ToDo(description);
                    items.add(t);
                    echo(t.toString(), "added");
                } catch (DukeException e){
                    System.out.println(e.getMessage());
                } catch (StringIndexOutOfBoundsException e){
                    System.out.println("Enter a valid todo activity\n");
                }
            } else {
                System.out.println("That is a rather unusual looking request sir.\nPerhaps you might want to specify the kind of task you would like to add.\n");
            }
            userInput = userSc.nextLine();
        }

        System.out.println("Goodbye Sir! Will take good care of your garden in the meantime.");
    }

    public static void echo(String userInput, String actionType){
        System.out.println("Got it sir, I have "+ actionType + " this task: \n " + userInput + "\nNow you have " + items.size() + " tasks in the list.\n");
    }

    public static void list(){
        for(int i = 1; i <= items.size(); i++){
            //System.out.println(i + ". [" +  items.get(i - 1).getStatusIcon() + "] " + items.get(i - 1).getDescription());
            System.out.println(i + ". " + items.get(i-1).toString());
        }
        System.out.println("");
    }

    public static void markDone(int n){
        items.get(n).markAsDone();
    }

    private static void readActivity(String userTask, String taskType) throws DukeException{
        if(userTask.length() <= 1){
            throw new DukeException("Enter valid " + taskType + " activity\n");
        }
    }

    public static void deleteTask(int number){
        Task t = items.remove(number);
        echo(t.toString(), "removed");

    }

}