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
                int task = Integer.parseInt(userInput.substring(5)) ;
                if(task > 0 && task <= items.size()){
                    markDone(task - 1);
                    System.out.println("One task down sir. Here is the task I checked off:");
                    System.out.println(task + ". [" +  items.get(task - 1).getStatusIcon() + "] " + items.get(task - 1).getDescription() + "\n");
                } else {
                    System.out.println("You have entered an invalid task number Sir, please input again.\n");
                }

            } else {
                echo(userInput);
            }
            userInput = userSc.nextLine();
        }

        System.out.println("Goodbye Sir! Will take good care of your plants in the meantime.");
    }

    public static void echo(String userInput){
        items.add(new Task(userInput));
        System.out.println("added: " + userInput + "\n");
    }

    public static void list(){
        for(int i = 1; i <= items.size(); i++){
            System.out.println(i + ". [" +  items.get(i - 1).getStatusIcon() + "] " + items.get(i - 1).getDescription());
        }
        System.out.println("");
    }

    public static void markDone(int n){
        items.get(n).markAsDone();
    }



}
