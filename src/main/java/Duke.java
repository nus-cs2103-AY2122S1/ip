import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
public class Duke {
    List<Task> commands = new ArrayList<>();
    int doneTasks = 0;

    private void loadCommands(String relPath){
        String directory = System.getProperty("user.dir");
        Path path = Paths.get(directory, relPath);
        boolean exists = Files.exists(path);
        if (exists){
            try{
                List<String> data = Files.readAllLines(path);
                for(String x: data){
                    String [] input1 = x.split(" ", 2);
                    String command = input1[0].trim();
                    System.out.println(command);
                    String [] input2 = input1[1].split("-", 2);
                    String description = input2[0].trim();
                    int isDone = Integer.parseInt(input2[1].trim());
                    try {
                        DukeCommands dukeCommand = DukeCommands.valueOf(command.toUpperCase());
                        execute(dukeCommand, description, isDone);
                    } catch (IllegalArgumentException e){
                        System.out.println("I'm sorry, I don't know what that means! ☹");
                    }
                }
            } catch (IOException e){
                System.out.println("There was an error loading your saved tasks!");
            }
        } else{
            System.out.println("There is no such file!");
        }
        
    }
    private void saveCommands(){
        String directory = System.getProperty("user.dir");
        String text="";
        for(Task command: commands){
            if(command instanceof ToDo){
                text += "todo " + command.description;
                
            } else if (command instanceof Deadline){
                text += "deadline " + command.description + "/by " + ((Deadline) command).by;
            } else if (command instanceof Event) {
                text += "event " + command.description + "/at " + ((Event) command).time;
            }
            
            if(command.isDone){
                text += "-1\n";
            } else{
                text += "-0\n";
            }
        }
        Path path = Paths.get(directory, "data");
        boolean exists = Files.exists(path);

        if (!exists) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            Files.write(Paths.get(directory,"data", "taskList.txt"), text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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
    void execute(DukeCommands command, String input, int isDone){
        try {
            boolean taskToAdd = false;
            int taskIndex;
            switch (command) {
                case TODO:
                    if(input.equals("")){
                        throw new EmptyTaskDescriptionException();
                    } else{
                        commands.add(new ToDo(input));
                        if(isDone == 1){
                            doneTasks++;
                            commands.get(commands.size()-1).markAsDone();
                        }
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
                        if(isDone==1){
                            doneTasks++;
                            commands.get(commands.size()-1).markAsDone();
                        }
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
                        if(isDone==1){
                            doneTasks++;
                            commands.get(commands.size()-1).markAsDone();
                        }
                        taskToAdd = true;
                    }
                    break;
                case LIST:
                    list();
                    break;
                case BYE:
                    System.out.println("\t ByeBye! Hope to see you again!");
                    saveCommands();
                    System.exit(0);
                    break;
                case DONE:
                    if(input.equals("")){
                        throw new EmptyTaskNumberException();
                    } else {
                        taskIndex = Integer.parseInt(input.trim());
                        done(taskIndex-1);
                    }
                    break;
                case DELETE:
                    if(input.equals("")){
                        throw new EmptyTaskNumberException();
                    } else {
                        taskIndex = Integer.parseInt(input.trim());
                        remove(taskIndex-1);
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
                if(!commands.get(listNumber).isDone) {
                    commands.get(listNumber).markAsDone();
                } else{
                    throw new TaskDoneAlreadyException();
                }
                doneTasks++;
                System.out.println("Good job! I've marked this task as completed:");
                System.out.println(commands.get(listNumber));
                System.out.println("You now have " + (commands.size() - doneTasks) + " tasks to complete");
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
        duke.loadCommands("data/taskList.txt");
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
                duke.execute(dukeCommand, input, 0);
            } catch (IllegalArgumentException e){
                System.out.println("I'm sorry, I don't know what that means! ☹");
            }
            
                
            
            System.out.println("____________________________________________________________");
        }
        sc.close();
    }
}
