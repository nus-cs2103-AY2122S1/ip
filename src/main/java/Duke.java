import java.util.*;
public class Duke {
    List<Task> commands = new ArrayList<>();

    void list(){
        if (commands.size() == 0){
            System.out.println("There are no tasks in your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < commands.size(); i++) {
                System.out.println((i + 1) + ". " + commands.get(i));
            }
        }
    }
    void add(String input){
        String[] inputSplit = input.split("\\s", 2);
        String commandType = inputSplit[0].toLowerCase();
        boolean correctInput = true;
        switch (commandType){
            case "todo":
                commands.add(new ToDo(inputSplit[1]));
                break;
            case "deadline":
                String[] deadline = inputSplit[1].split("/by");
                commands.add(new Deadline(deadline[0], deadline[1]));
                break;
            case "event":
                String[] event = inputSplit[1].split("/at");
                commands.add(new Event(event[0], event[1]));
                break;
            default:
                correctInput = false;
                System.out.println("You can't add this to your task list");
        }
        if(correctInput){
            System.out.println("I have added this task: ");
            System.out.println(commands.get(commands.size()-1));
            System.out.println("You now have " + commands.size() + " tasks in your list");
        }

    }

    void done(int listNumber){
        if(listNumber < commands.size()) {
            commands.get(listNumber).markAsDone();
            System.out.println("Good job! I've marked this task as completed: ");
            System.out.println(commands.get(listNumber));
            commands.remove(listNumber);
            System.out.println("You now have " + commands.size() + " tasks in your list");
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
            String[] command = input.split("\\s+", 2);
            if(command[0].equalsIgnoreCase("bye")){
                System.out.println("\n\tByeBye! Hope to see you again!");
                break;
            } else if(command[0].equalsIgnoreCase("list")){
                duke.list();
            } else if (command[0].equalsIgnoreCase("done")){
                if (!command[0].equals(input)) {
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
