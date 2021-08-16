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
    void add(String input){
        String[] inputSplit = input.split("\\s", 2);
        String commandType = inputSplit[0].toLowerCase();
        try {
            if(inputSplit.length < 2){
                throw new EmptyTaskDescriptionException();
            }
            switch (commandType) {
                case "todo":
                    commands.add(new ToDo(inputSplit[1]));
                    break;
                case "deadline":
                    String[] deadline = inputSplit[1].split("/by");
                    if(deadline.length < 2){
                        throw new NoTimeException();
                    }
                    commands.add(new Deadline(deadline[0], deadline[1]));
                    break;
                case "event":
                    String[] event = inputSplit[1].split("/at");
                    if(event.length < 2){
                        throw new NoTimeException();
                    }
                    commands.add(new Event(event[0], event[1]));
                    break;
                default:
                    throw new UnknownInputException();
            }
            System.out.println("I have added this task:");
            System.out.println(commands.get(commands.size()-1));
            System.out.println("You now have " + commands.size() + " tasks in your list");
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
                commands.remove(listNumber);
                System.out.println("You now have " + commands.size() + " tasks in your list");
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
            String input = sc.nextLine().trim();
            String[] command = input.split("\\s+", 2);
            try {
                if (command[0].equalsIgnoreCase("bye")) {
                    System.out.println("\n\tByeBye! Hope to see you again!");
                    break;
                } else if (command[0].equalsIgnoreCase("list")) {
                    duke.list();
                } else if (command[0].equalsIgnoreCase("done")) {
                    if (!command[0].equals(input)) {
                        if (!duke.commands.isEmpty()) {
                            int listNumber = Integer.parseInt(command[1]);
                            duke.done(listNumber - 1);
                        } else {
                            throw new EmptyTaskListException();
                        }
                    } else {
                        throw new InvalidDoneCommandException();
                    }
                } else {
                    duke.add(input);
                }
            } catch (DukeException e){
                System.out.println(e.getMessage());
            }
            System.out.println("____________________________________________________________");
        }
        sc.close();
    }
}
