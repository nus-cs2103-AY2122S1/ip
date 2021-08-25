import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private final static List<Task> items = new ArrayList<>(100);
    enum RequestType {
        DEFAULT,
        DONE,
        DELETE,
        DEADLINE,
        EVENT,
        TODO,
        UNUSUAL
    }
    
    public static void main(String[] args) {
        Storage.loadIntoDuke(items);
        Scanner userSc = new Scanner(System.in);
        String name = "JARVIS";
        System.out.println("Hello I am " + name +".\nIs there anything I can do for you Sir?\n");
        String userInput = userSc.nextLine();
        RequestType userRequest;
        while(!userInput.equals("bye")){
            if(userInput.equals("list")){
                userRequest = RequestType.DEFAULT;
                list();
            } else if(userInput.startsWith("done")){
                userRequest = RequestType.DONE;
            } else if(userInput.startsWith("delete")){
                userRequest = RequestType.DELETE;
            } else if(userInput.length() > 8 && userInput.startsWith("deadline")  && userInput.contains("/by")){
                userRequest = RequestType.DEADLINE;
            } else if(userInput.length() > 5 && userInput.startsWith("event")  && userInput.contains("/at")){
                userRequest = RequestType.EVENT;
            } else if(userInput.startsWith("todo")){
                userRequest = RequestType.TODO;
            } else {
                userRequest = RequestType.UNUSUAL;
            }
            
            switch (userRequest){
                case DEFAULT:
                    break;
                case UNUSUAL:
                    System.out.println("That is a rather unusual looking request sir.\nPerhaps you might want to specify the kind of task you would like to add.\n");
                    break;
                case DONE:
                    done(userInput);
                    break;
                case DELETE:
                    delete(userInput);
                    break;
                case DEADLINE:
                    deadline(userInput);
                    break;
                case EVENT:
                    event(userInput);
                    break;
                case TODO:
                    todo(userInput);
                    break;
            }
            
            userInput = userSc.nextLine();
        }

        System.out.println("Goodbye Sir! Will take good care of your garden in the meantime.");
        Storage.writeToFile(items);
    }

    public static void echo(String userInput, String actionType){
        System.out.println("Got it sir, I have "+ actionType + " this task:\n " + userInput + "\nNow you have " + items.size() + " tasks in the list.\n");
    }

    public static void list(){
        for(int i = 1; i <= items.size(); i++){
            System.out.println(i + ". " + items.get(i-1).toString());
        }
        System.out.println("");
    }
    
    private static void done(String userInput){
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
        
    }
    
    private static void delete(String userInput){
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
    }
    
    private static void deadline(String userInput){
        if(userInput.indexOf("/by")  < 11 ){
            System.out.println("Enter a valid deadline activity description\n");
        } else if(userInput.length() <= userInput.indexOf("/by") +  4){
            System.out.println("Enter a valid deadline time\n");
        } else {
            String description = userInput.substring(9, userInput.indexOf("/by") - 1);
            String by = userInput.substring(userInput.indexOf("/by") + 4);

            try{
                LocalDate time = LocalDate.parse(by);
                Task t =  new Deadline(description, by);
                items.add(t);
                Storage.addNewTask(t);
                echo(t.toString(), "added");
            } catch (Exception e){
                System.out.println("Enter a valid date in the format yyyy-mm-dd\n");
            }

        }
    }
    
    private static void event(String userInput){
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
            Storage.addNewTask(t);
        }
    }
    
    private static void todo(String userInput){
        try {
            readActivity(userInput.substring(5), "todo");
            String description = userInput.substring(5);
            Task t = new ToDo(description);
            items.add(t);
            echo(t.toString(), "added");
            Storage.addNewTask(t);
        } catch (DukeException e){
            System.out.println(e.getMessage());
        } catch (StringIndexOutOfBoundsException e){
            System.out.println("Enter a valid todo activity\n");
        }
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
