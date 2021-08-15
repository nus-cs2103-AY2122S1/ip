import java.util.*;
public class Duke {
    List<Task> commands = new ArrayList<>();

    void list(){
        if (commands.size() == 0){
            System.out.println("There are no tasks in your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < commands.size(); i++) {
                System.out.println((i + 1) + ".  [" + commands.get(i).getStatusIcon() + "]" + commands.get(i).description);
            }
        }
    }
    void add(String command){
        commands.add(new Task(command));
        System.out.println("Added task: " + command);
    }

    void done(int listNumber){
        if(listNumber < commands.size()) {
            commands.get(listNumber).markAsDone();
            System.out.println("Good job! I've marked this task as completed: ");
            System.out.println("  [" + commands.get(listNumber).getStatusIcon() + "]" + commands.get(listNumber).description);
        } else{
            System.out.println("There is no such task in your to-do list! ☹️");
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        String logo = "   _       _           \n"
                + "  /_\\  ___| |__  _   _ \n"
                + " //_\\\\/ __| '_ \\| | | |\n"
                + "/  _  \\__ \\ | | | |_| |\n"
                + "\\_/ \\_/___/_| |_|\\__, |\n"
                + "                 |___/ \n";

        //Greeting message
        System.out.println("Hello! I'm\n" +logo+ "\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        //Echo
        while(sc.hasNext()){
            //Exit command
            System.out.println("............................................................");
            String input = sc.nextLine().trim();
            String[] command = input.split(" ", 2);
            if(command[0].equalsIgnoreCase("bye")){
                System.out.println("\n\tByeBye! Hope to see you again!");
                break;
            } else if(command[0].equalsIgnoreCase("list")){
                duke.list();
            } else if (command[0].equalsIgnoreCase("done")){
                if (command[0].equals(input)) {
                    if(!duke.commands.isEmpty()) {
                        int listNumber = Integer.parseInt(command[1]);
                        duke.done(listNumber - 1);
                    } else{
                        System.out.println("There are no tasks in your to-do list! ☹️");
                    }
                } else{
                    System.out.println("You haven't mentioned the task number! ☹️");
                }
            }
            else {
                duke.add(input);
            }
            System.out.println("____________________________________________________________");
        }
        sc.close();
    }
}
