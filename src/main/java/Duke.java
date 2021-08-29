import java.io.File;
import java.time.LocalDate;
import java.util.Scanner;

public class Duke {
    //private final static List<Task> items = new ArrayList<>(100);
    
    private final Storage storage;
    private final TaskList tasks;
    private Ui ui;
    
    enum RequestType {
        DEFAULT,
        DONE,
        DELETE,
        DEADLINE,
        EVENT,
        TODO,
        UNUSUAL
    }
    
    public Duke(String filePath){
        storage = new Storage(filePath);
        ui = new Ui();
        tasks = new TaskList(storage.loadIntoDuke());
    }
    
    public static void main(String[] args) {
        new Duke("." +  File.separator + "data" + File.separator + "duke.txt").run();
    }
    
    public void run() {
        Scanner userSc = new Scanner(System.in);
        ui = new Ui();
        ui.dukeGreeting();
        String userInput = userSc.nextLine();
        RequestType userRequest;

        while(!userInput.equals("bye")){
            if(userInput.equals("list")){
                userRequest = RequestType.DEFAULT;
                tasks.list();
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
                    ui.unusualRequest();
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

        ui.farewellMessage();
        storage.writeToFile(tasks);
    }

    public void echo(String userInput, String actionType){
        System.out.println("Got it sir, I have "+ actionType + " this task:\n " + userInput + "\nNow you have " + tasks.getSize() + " tasks in the list.\n");
    }
    
    
    private void done(String userInput){
        try{
            int task = Integer.parseInt(userInput.substring(5));
            if(task > 0 && task <= tasks.getSize()){
                tasks.markDone(task - 1);
                System.out.println("One task down sir. Here is the task I checked off:");
                System.out.println("    " + tasks.getTask(task).toString() + "\n");
            } else {
                System.out.println("You have entered an invalid task number Sir, please input again.\n");
            }
        } catch (NumberFormatException e){
            System.out.println("Task number formatted incorrectly. Try again\n");
        }
        
    }
    
    private void delete(String userInput){
        try{
            int task = Integer.parseInt(userInput.substring(7));
            if(task > 0 && task <= tasks.getSize()){
                Task t = tasks.deleteTask(task);
                echo(t.toString(), "removed");
            } else {
                System.out.println("You have entered an invalid task number Sir, please input again.\n");
            }
        } catch (NumberFormatException e){
            System.out.println("Task number formatted incorrectly. Try again\n");
        }
    }
    
    private void deadline(String userInput){
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
                tasks.addTask(t);
                storage.addNewTask(t);
                echo(t.toString(), "added");
            } catch (Exception e){
                System.out.println("Enter a valid date in the format yyyy-mm-dd\n");
            }
        }
    }
    
    private void event(String userInput){
        if(userInput.indexOf("/at")  < 8 ){
            System.out.println("Enter a valid event activity description\n");
        } else if(userInput.length() <= userInput.indexOf("/at") +  4){
            System.out.println("Enter a valid event time\n");
        } else {
            String description = userInput.substring(6, userInput.indexOf("/at") - 1);
            String at = userInput.substring(userInput.indexOf("/at") + 4);
            Task t =  new Event(description, at);
            tasks.addTask(t);
            echo(t.toString(), "added");
            storage.addNewTask(t);
        }
    }
    
    private void todo(String userInput){
        try {
            readActivity(userInput.substring(5), "todo");
            String description = userInput.substring(5);
            Task t = new ToDo(description);
            tasks.addTask(t);
            echo(t.toString(), "added");
            storage.addNewTask(t);
        } catch (DukeException e){
            System.out.println(e.getMessage());
        } catch (StringIndexOutOfBoundsException e){
            System.out.println("Enter a valid todo activity\n");
        }
    }



    private static void readActivity(String userTask, String taskType) throws DukeException{
        if(userTask.length() <= 1){
            throw new DukeException("Enter valid " + taskType + " activity\n");
        }
    }
    

}
