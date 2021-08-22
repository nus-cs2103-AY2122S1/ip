
import java.util.*;
public class Duke {
    List<Task> commands = new ArrayList<>();

    void list(){
        try {
            if (commands.size() == 0) {
                throw new EmptyTaskListException();
            } else {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < commands.size(); i++) {
                    System.out.println((i + 1) + ". " + commands.get(i));
                }
            }
        } catch (DukeException e){
            System.out.println(e.getMessage());
        }

    }
    void execute(DukeCommands command, String input){
        try {
            boolean taskToAdd = false;
            int taskIndex;
            switch (command) {
                case TODO:
                    if(input.equals("")){
                        throw new EmptyTaskDescriptionException();
                    } else{
                        commands.add(new ToDo(input));
                        taskToAdd = true;
                    }
                    break;
                case DEADLINE:
                    if(input.equals("")){
                        throw new EmptyTaskDescriptionException();
                    } else {
                        String[] deadline = input.split("/by");
                        if (deadline.length < 2) {
                            throw new NoTimeException();
                        }
                        commands.add(new Deadline(deadline[0], deadline[1]));
                        taskToAdd = true;
                    }
                    break;
                case EVENT:
                    if(input.equals("")){
                        throw new EmptyTaskDescriptionException();
                    } else {
                        String[] event = input.split("/at");
                        if (event.length < 2) {
                            throw new NoTimeException();
                        }
                        commands.add(new Event(event[0], event[1]));
                        taskToAdd = true;
                    }
                    break;
                case LIST:
                    list();
                    break;
                case BYE:
                    System.out.println("\t ByeBye! Hope to see you again!");
                    System.exit(0);
                    break;
                case DONE:
                    if(input.equals("")){
                        throw new EmptyTaskNumberException();
                    } else {
                        taskIndex = Integer.parseInt(input.trim());
                        done(taskIndex);
                    }
                    break;
                case DELETE:
                    if(input.equals("")){
                        throw new EmptyTaskNumberException();
                    } else {
                        taskIndex = Integer.parseInt(input.trim());
                        remove(taskIndex);
                    }
                    break;
                default:
                    throw new UnknownInputException();
            }
            if (taskToAdd) {
                System.out.println("I have added this task:");
                System.out.println(commands.get(commands.size() - 1));
                System.out.println("You now have " + commands.size() + " tasks in your list");
            }
        } catch (DukeException e){
            System.out.println(e.getMessage());
        }


    }

    void done(int listNumber) {
        try {
            if (listNumber < commands.size()) {
                commands.get(listNumber).markAsDone();
                System.out.println("Good job! I've marked this task as completed:");
                System.out.println(commands.get(listNumber));
                System.out.println("You now have " + (commands.size()-1) + " tasks to complete");
            } else {
                throw new InvalidTaskException();
            }
        } catch (DukeException e){
            System.out.println(e.getMessage());
        }

    }

    void remove(int listNumber){
        try {
            if (listNumber < commands.size()) {
                System.out.println("Okay! I have removed this task for you:");
                System.out.println(commands.get(listNumber));
                commands.remove(listNumber);
                System.out.println("You now have " + commands.size() + " tasks in your list.");
            } else {
                throw new InvalidTaskException();
            }
        } catch (DukeException e){
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args){
        Duke duke = new Duke();
        String logo = "   _       _\n"
                + "  /_\\  ___| |__  _   _\n"
                + " //_\\\\/ __| '_ \\| | | |\n"
                + "/  _  \\__ \\ | | | |_| |\n"
                + "\\_/ \\_/___/_| |_|\\__, |\n"
                + "                 |___/\n";

        //Greeting message
        System.out.println("Hello! I'm\n" +logo+ "\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        //Echo
        while(sc.hasNext()){
            //Exit command
            System.out.println("............................................................");
            String command = sc.next().trim();
            String input = sc.nextLine().trim();
            try {
                DukeCommands dukeCommand = DukeCommands.valueOf(command.toUpperCase());
                duke.execute(dukeCommand, input);
            } catch (IllegalArgumentException e){
                System.out.println("I'm sorry, I don't know what that means! ☹");
            }
            
                
            
            System.out.println("____________________________________________________________");
        }
        sc.close();
    }
}
