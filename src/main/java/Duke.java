import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collection;

public class Duke {
    static Scanner userInput = new Scanner(System.in);

    static String linebreaker = "____________________________________________________________";
    static String greeting = "Hello! I'm Duke\n" + "What can I do for you?";
    static String farewell = "Bye. Hope to see you again soon!";
    static String listMessage = "Here are the tasks in your list:";
    static String doneMessage = "Nice! I've marked this task as done:";
    static String addTaskMessage = "Got it. I've added this task:";
    static String deleteTaskMessage = "Noted. I've removed this task:";

    private boolean running = true;
    private String instruction;
    private ArrayList<Task> improvedList= new ArrayList<Task>();
    private enum Commands {list, done, todo, event, deadline, delete };

    void setInstruction() {
        instruction = userInput.nextLine();
    }

    void echo() {
        System.out.println(instruction);
    }

    boolean checkBye(){
        if(instruction.equalsIgnoreCase("bye")){
            running = false;
            return true;
        }
        return false;
    }

    void parse() throws NoDescriptionError, UnknownCommandError{
        printLineBreak();
        String[] strings = instruction.split(" ", 2);
        String operative = strings[0];
        Commands command;
        String[] temp;
        String item, date, description;
        Task toAdd;
        int taskPointer;
        try {
            command = Commands.valueOf(operative);
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandError();
        }

        if(strings.length == 1 && !operative.equalsIgnoreCase("list")) {
            throw new NoDescriptionError(operative);
        }

            switch (command) {
                case list:
                    printArrayList();
                    break;
                case done:
                    item = strings[1];
                    taskPointer = Integer.parseInt(item) - 1;
                    improvedList.get(taskPointer).markAsDone();
                    completeTaskMessage(improvedList.get(taskPointer));
                    break;
                case todo:
                    item = strings[1];
                    toAdd = new Todo(item);
                    improvedList.add(toAdd);
                    addedTaskMessage(toAdd.toString());
                    break;
                case event:
                    item = strings[1];
                    temp = item.split("/at ");
                    date = temp[1];
                    description = temp[0];
                    toAdd = new Event(description, date);
                    improvedList.add(toAdd);
                    addedTaskMessage(toAdd.toString());
                    break;
                case delete:
                    item = strings[1];
                    taskPointer = Integer.parseInt(item) - 1;
                    Task deleted = improvedList.remove(taskPointer);
                    deleteTaskMessage(deleted.toString());
                    break;
                case deadline:
                    item = strings[1];
                    temp = item.split("/by ");
                    date = temp[1];
                    description = temp[0];
                    toAdd = new Deadline(description, date);
                    improvedList.add(toAdd);
                    addedTaskMessage(toAdd.toString());
                    break;
        }
    }



    void printLineBreak () {
        System.out.println(linebreaker);
    }

    void printArrayList () {
        System.out.println(listMessage);
        for(int i = 0; i < improvedList.size(); i++) {
            System.out.println(String.valueOf(i+1) + "." + improvedList.get(i).toString());
        }
        printLineBreak();
    }

    void greet(){
        System.out.println(greeting);
        printLineBreak();
    }

    void sayFarewell() {
        System.out.println(farewell);
        printLineBreak();
    }

    void completeTaskMessage (Task task) {
        System.out.println(doneMessage + "\n" + task.toString());
        printLineBreak();
    }

    void deleteTaskMessage (String task) {
        System.out.println(deleteTaskMessage);
        System.out.println("  " + task);
        taskCounterMessage();
        printLineBreak();
    }

    void addedTaskMessage (String task) {
        System.out.println(addTaskMessage);
        System.out.println("  " + task);
        taskCounterMessage();
        printLineBreak();
    }

    void taskCounterMessage () {
        System.out.println("Now you have " + improvedList.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        Duke weekTwo = new Duke();
        weekTwo.greet();

        while(weekTwo.running) {
            weekTwo.setInstruction();
            if(weekTwo.checkBye()){
                break;
            }
            try {
                weekTwo.parse();
            } catch (DukeException e) {
                System.out.println(e.toString());
                weekTwo.printLineBreak();
            }
        }
        weekTwo.sayFarewell();
    }
}