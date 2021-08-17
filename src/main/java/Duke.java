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

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hi how are ya");

        while(true){
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            if(command.equals("bye")) {
                System.out.println("Bye have a good time");
                break;
            }else if(command.equals("list")) {
                int c = 1;
                for (Task task : tasks) {
                    System.out.println(c + ". " + task);
                    c++;
                }
            }else if(command.contains("done")){
                String numbers = command.substring(5);
                try {
                    int taskNo = Integer.parseInt(numbers);
                }catch(NumberFormatException notANumber){
                    System.err.println(notANumber);
                    System.out.println("Uh oh! Stinky!");
                    continue;
                }
                int taskNo = Integer.parseInt(numbers);
                if(tasks.size()<taskNo){
                    System.err.println("too big");
                    continue;
                }
                taskNo--;
                Task toBeDone=tasks.get(taskNo);
                toBeDone.makeDone();
                tasks.set(taskNo,toBeDone);
                System.out.println(toBeDone.name + " has been marked as done");
            }else{
                Task commandTask = new Task(command);
                if(!tasks.contains(commandTask)) {
                    System.out.println("added: " + command);
                    tasks.add(commandTask);
                }else{
                    System.out.println(command + " is already in the list sir");
                }
            }
        }
    }


}
