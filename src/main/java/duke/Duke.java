package duke;
import duke.exceptions.*;
import duke.task.*;
import duke.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class Duke {
    ArrayList<Task> commands = new ArrayList<>();
    int doneTasks = 0;
    Ui ui = new Ui();
    Storage storage = new Storage();
    Parser parser = new Parser();
    TaskList taskList = new TaskList();
    
    public void execute(DukeCommands command, String input, int isDone){
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d-MM-yyyy");             
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("d-MM-yyyy HHmm");        
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
                        LocalDate date = LocalDate.parse(deadline[1].trim(),formatter1);
                        commands.add(new Deadline(deadline[0], date));
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
                        LocalDateTime time = LocalDateTime.parse(event[1].trim(),formatter2);
                        commands.add(new Event(event[0], time));
                        if(isDone==1){
                            doneTasks++;
                            commands.get(commands.size()-1).markAsDone();
                        }
                        taskToAdd = true;
                    }
                    break;
                case LIST:
                    taskList.list(commands);
                    break;
                case BYE:
                    storage.saveCommands(commands);
                    ui.exitMessaage();
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
                        taskList.remove(taskIndex-1, commands);
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
                ui.doneOutput(commands.get(listNumber), (commands.size() - doneTasks));
            } else {
                throw new InvalidTaskException();
            }
        } catch (DukeException e){
            System.out.println(e.getMessage());
        }

    }
    
    void run(){
        storage.loadCommands(this);
        ui.greetingMessage();
        Scanner sc = new Scanner(System.in);
        //Echo
        while(sc.hasNext()){
            //Exit command
            ui.dottedLine();
            String command = sc.next().trim();
            String input = sc.nextLine().trim();
            try {
                DukeCommands dukeCommand = parser.parseCommand(command);
                execute(dukeCommand, input, 0);
            } catch (IllegalArgumentException e){
                System.out.println("I'm sorry, I don't know what that means! ☹");
            }
            ui.line();
        }
        sc.close();
    }

    public static void main(String[] args){
        new Duke().run();
    }
}
