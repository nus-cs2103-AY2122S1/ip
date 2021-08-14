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
    private Task[] toDo = new Task[100];
    private ArrayList<Task> improvedList= new ArrayList<Task>();
    private int counter = 0;

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

    void parse() throws NoDescriptionError, UnknownCommandError {
        printLineBreak();
        String[] strings = instruction.split(" ", 2);
        String operative = strings[0];

        if(operative.equalsIgnoreCase("list")) {
            printArrayList();
        } else if (operative.equalsIgnoreCase("done")) {
            try {
                String item = strings[1];
                int taskPointer = Integer.parseInt(item) - 1;
                improvedList.get(taskPointer).markAsDone();
                completeTaskMessage(improvedList.get(taskPointer));
            } catch (IndexOutOfBoundsException e) {
                throw new NoDescriptionError(operative);
            }
        } else if (operative.equalsIgnoreCase("delete")) {
            try {
                String item = strings[1];
                int taskPointer = Integer.parseInt(item) - 1;
                Task deleted = improvedList.remove(taskPointer);
                deleteTaskMessage(deleted.toString());
            } catch (IndexOutOfBoundsException e) {
                throw new NoDescriptionError(operative);
            }
        }else if (operative.equalsIgnoreCase("todo")){
            try {
                String item = strings[1];
                Task toAdd = new Todo(item);
                improvedList.add(toAdd);
                addedTaskMessage(toAdd.toString());
            } catch (IndexOutOfBoundsException e) {
                throw new NoDescriptionError(operative);
            }
        }else if (operative.equalsIgnoreCase("event")){
            try {
                String item = strings[1];
                String[] temp = item.split("/at ");
                String date = temp[1];
                String description = temp[0];
                Task toAdd = new Event(description, date);
                improvedList.add(toAdd);
                addedTaskMessage(toAdd.toString());
            } catch (IndexOutOfBoundsException e) {
                throw new NoDescriptionError(operative);
            }
        }else if (operative.equalsIgnoreCase("deadline")){
            try {
                String item = strings[1];
                String[] temp = item.split("/by ");
                String date = temp[1];
                String description = temp[0];
                Task toAdd = new Deadline(description, date);
                improvedList.add(toAdd);
                addedTaskMessage(toAdd.toString());
                counter++;
            } catch (IndexOutOfBoundsException e) {
                throw new NoDescriptionError(operative);
            }
        } else {
            throw new UnknownCommandError();
        }

    }

    void printLineBreak () {
        System.out.println(linebreaker);
    }

    void printList() {
        System.out.println(listMessage);
        for(int x = 0; x < 99;x++) {
            if(toDo[x] == null){
                break;
            }
            String temp = String.valueOf(x + 1);
            System.out.println(temp + "." + toDo[x].toString());
        }
        printLineBreak();
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
            } catch (Exception e) {
                System.out.println(e.toString());
                weekTwo.printLineBreak();
            }
        }
        weekTwo.sayFarewell();
    }
}
