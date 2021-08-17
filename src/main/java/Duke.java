import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class Duke {
    private static List<Task> tasks = new ArrayList<Task>();

    private static class Task{
        String name;
        boolean done;
        public Task(String name){
            this.name=name;
            done=false;
        }
        public void makeDone(){
            done=true;
        }
        public void makeUndone(){
            done=false;
        }
        @Override
        public String toString(){
            if(done==true){
                return "[X] "+name;
            }else{
                return "[ ] "+name;
            }
        }
        @Override
        public boolean equals(Object obj){
            if(!(obj instanceof Task)){
                return false;
            }else{
                Task objTask = (Task) obj;
                if(objTask.name.equals(this.name)){
                    return true;
                }else{
                    return false;
                }
            }
        }

    }

    private static class ToDo extends Task{
        public ToDo(String name) {
            super(name);
        }
        @Override
        public String toString(){
            if(done==true){
                return "[T][X] "+name;
            }else{
                return "[T][ ] "+name;
            }
        }
        @Override
        public boolean equals(Object obj){
            if(!(obj instanceof ToDo)){
                return false;
            }else{
                ToDo objTask = (ToDo) obj;
                if(objTask.name.equals(this.name)){
                    return true;
                }else{
                    return false;
                }
            }
        }
    }

    private static class Deadline extends Task{
        private String date;
        public Deadline(String name, String date) {
            super(name);
            this.date=date;
        }
        @Override
        public String toString(){
            if(done==true){
                return "[D][X] "+name+ " (by: "+date+")";
            }else{
                return "[D][ ] "+name+ " (by: "+date+")";
            }
        }
        @Override
        public boolean equals(Object obj){
            if(!(obj instanceof Deadline)){
                return false;
            }else{
                Deadline objTask = (Deadline) obj;
                if(objTask.name.equals(this.name)&&objTask.date.equals(this.date)){
                    return true;
                }else{
                    return false;
                }
            }
        }
    }

    private static class Event extends Task{
        private String date;
        public Event(String name, String date) {
            super(name);
            this.date=date;
        }
        @Override
        public String toString(){
            if(done==true){
                return "[E][X] "+name+ " (at: "+date+")";
            }else{
                return "[E][ ] "+name+ " (at: "+date+")";
            }
        }
        @Override
        public boolean equals(Object obj){
            if(!(obj instanceof Event)){
                return false;
            }else{
                Event objTask = (Event) obj;
                if(objTask.name.equals(this.name)&&objTask.date.equals(this.date)){
                    return true;
                }else{
                    return false;
                }
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Welcome. I am your virtual assistant Duke. Sparkle up your day (TM).");

        while(true){
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            if(command.equals("bye")) {
                System.out.println("Have a SPARKULAR day.");
                break;
            }else if(command.equals("list")) {
                int c = 1;
                for (Task task : tasks) {
                    System.out.println(c + ". " + task);
                    c++;
                }
            }else if(command.contains("done")) {
                String numbers = command.substring(5);
                try {
                    int taskNo = Integer.parseInt(numbers);
                } catch (NumberFormatException notANumber) {
                    System.err.println(notANumber);
                    System.err.println("JUST GIVE ME A NUMBER, WHY ARE YOU DOING THIS");
                    continue;
                }
                int taskNo = Integer.parseInt(numbers);
                if (tasks.size() < taskNo) {
                    System.err.println("hello sir there are only " + tasks.size() + " tasks in the list sir");
                    continue;
                }
                if (taskNo <= 0) {
                    System.err.println("HOW CAN I COMPLETE THE TASK AT INDEX " + taskNo + "? IT DOESNT MAKE ANY SENSE");
                    continue;
                }
                taskNo--;
                Task toBeDone = tasks.get(taskNo);
                toBeDone.makeDone();
                tasks.set(taskNo, toBeDone);
                System.out.println(toBeDone.name + " has been marked as done");
            }else if(command.contains("delete")){
                String numbers = command.substring(7);
                try {
                    int taskNo = Integer.parseInt(numbers);
                } catch (NumberFormatException notANumber) {
                    System.err.println(notANumber);
                    System.err.println("JUST GIVE ME A NUMBER, WHY ARE YOU DOING THIS");
                    continue;
                }
                int taskNo = Integer.parseInt(numbers);
                if (tasks.size() < taskNo) {
                    System.err.println("hello sir there are only " + tasks.size() + " tasks in the list sir");
                    continue;
                }
                if (taskNo <= 0) {
                    System.err.println("HOW CAN I COMPLETE THE TASK AT INDEX " + taskNo + "? IT DOESNT MAKE ANY SENSE");
                    continue;
                }
                taskNo--;
                Task toBeDeleted=tasks.get(taskNo);
                tasks.remove(taskNo);
                System.out.println("task " +toBeDeleted+ " has been deleted.\nThere are "+tasks.size()+" tasks left in the list.");

            }else if(command.contains("todo")){
                String task=command.substring(5);
                if(task.equals("")){
                    System.err.println("I NEED A NAME SIR");
                    continue;
                }
                ToDo toBeAdded=new ToDo(task);
                if(!(tasks.contains(toBeAdded))){
                    tasks.add(toBeAdded);
                    System.out.println("todo " +toBeAdded + " added");
                    System.out.println("the list has "+tasks.size()+" tasks now");
                }else{
                    System.out.println(task + " is already in the list sir");
                }

            }else if(command.contains("deadline")){
                String taskNDate=command.substring(9);
                if(!(taskNDate.contains("/by"))){
                    System.err.println("BY WHEN? HOW CAN YOU HAVE A DEADLINE WITHOUT A DEADLINE???");
                    continue;
                }
                int splitIndex=taskNDate.indexOf("/by");
                String task =taskNDate.substring(0,splitIndex-1);
                String date =taskNDate.substring(splitIndex+4);
                if(task.equals("")){
                    System.err.println("I NEED A NAME SIR");
                    continue;
                }
                Deadline toBeAdded = new Deadline(task,date);
                if(!(tasks.contains(toBeAdded))){
                    tasks.add(toBeAdded);
                    System.out.println("deadline " +toBeAdded + " added");
                    System.out.println("the list has "+tasks.size()+" tasks now");
                }else{
                    System.out.println(task + " is already in the list sir");
                }

            }else if(command.contains("event")){
                String taskNDate=command.substring(6);
                if(!(taskNDate.contains("/at"))){
                    System.err.println("AT WHEN? YOU HAVE AN EVENT BUT YOU DONT KNOW WHERE IT IS???");
                    continue;
                }
                int splitIndex=taskNDate.indexOf("/at");
                String task =taskNDate.substring(0,splitIndex-1);
                String date =taskNDate.substring(splitIndex+4);
                if(task.equals("")){
                    System.err.println("I NEED A NAME SIR");
                    continue;
                }
                Event toBeAdded = new Event(task,date);
                if(!(tasks.contains(toBeAdded))){
                    tasks.add(toBeAdded);
                    System.out.println("event " +toBeAdded + " added");
                    System.out.println("the list has "+tasks.size()+" tasks now");
                }else{
                    System.out.println(task + " is already in the list sir");
                }
            }else{
                /*
                Task commandTask = new Task(command);
                if(!tasks.contains(commandTask)) {
                    System.out.println("added: " + command);
                    tasks.add(commandTask);
                }else{
                    System.out.println(command + " is already in the list sir");
                }

                 */
                System.out.println("(WHAT IS THIS PERSON TRYING TO SAY WHY IS HE TYPING GIBBERISH I'M JUST TRYING TO SURVIVE)");
            }
        }
    }


}
