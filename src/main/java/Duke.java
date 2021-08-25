import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
 class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone(){
        this.isDone = true;
    }

    @Override
    public String toString(){
      return "[" + getStatusIcon() + "] " + description;
    }
}
 class Deadline extends Task {//Need to be done by deadline

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + by + ")";
    }
}

 class ToDo extends Task {//No deadline


    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Event extends Task {//Starts and ends by a certain time

    protected String by;//Range of timing

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()  + "(" + by + ")";
    }
}

public class Duke {
    //        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
    static List<Task> toDo = new ArrayList<>();

    static int countTasks(){
        int count = 0;
        for(Task task:toDo){
            if(!task.isDone){
                count ++;
            }
        }
        return count;
    }

    static void saveTasks(List<Task> tasks){//Called on "bye"
        try{
            File data_file = new File("Data/DukeData.txt");
            FileWriter writer = new FileWriter("Data/DukeData.txt");//Overwriting entire file
            for(Task task: tasks){
                writer.write(task.toString());
                writer.write("\n");
            }
            writer.close();
            if (data_file.createNewFile()) {
                System.out.println("File created: " + data_file.getName());
            } else {
                System.out.println("File already exists.");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

     static void readFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String currLine = s.nextLine();
//            System.out.println(currLine);
            char taskType = currLine.charAt(1);
//            System.out.println(taskType);
            switch (taskType){
                case 'T':
                    toDo.add(new ToDo(currLine.substring(7)));
                    System.out.println(new ToDo(currLine.substring(7)));
                    break;
                case 'D':
                    int l = currLine.indexOf("(");
//                    int m = currLine.indexOf("by ");
                    int n = currLine.indexOf(")");
//                    System.out.println(currLine.substring(7,l));
//                    System.out.println(currLine.substring(l+1,n));
                    toDo.add(new Deadline(currLine.substring(7,l),currLine.substring(l+1,n)));
                    System.out.println(new Deadline(currLine.substring(7,l),currLine.substring(l+1,n)));
                    break;
                case 'E':
                    int i = currLine.indexOf("(");
//                    int j = currLine.indexOf("at ");
                    int k = currLine.indexOf(")");
//                    System.out.println(currLine.substring(7,i));
//                    System.out.println(currLine.substring(j,k));
                    toDo.add(new Event(currLine.substring(7,i),currLine.substring(i+1,k)));
                    System.out.println(new Event(currLine.substring(7,i),currLine.substring(i+1,k)));
                    break;
            }
        }
    }

    public static void main(String[] args) {

        String item;
        String line = "-----------------------------------------";
        Scanner myObj = new Scanner(System.in);

        System.out.println(line);
        System.out.println("Hello! Im Duke\n" + "What can I do for you?");
        System.out.println(line);
        try{
            readFile("Data/DukeData.txt");
        }
        catch (Exception e){
            System.out.println(e);
        }
        while(myObj.hasNext()){
            System.out.println();

            item = myObj.nextLine();//Parse the line

            System.out.println(item);//User input typed

            if(item.equals("bye")){//Stop scanner & stores updated data
                System.out.println(line);
                System.out.println("     " + "Bye. Hope to see you again soon!");
                System.out.println(line);
                myObj.close();
                saveTasks(toDo);
                break;
            }
            if(item.equals("list")){//View list of tasks
                System.out.println(line);
                for(int i = 0; i < toDo.size();i++){
                    System.out.println("     " + String.valueOf(i+1) + ". " + toDo.get(i).toString());
                }
                System.out.println(line);

                continue;
            }
            if(item.contains("done")){//Complete a task
                try {
                    item = item.replaceAll("\\D+","");//Extracts number from input
                    int completedItem = Integer.parseInt(item);
                    toDo.get(completedItem-1).markAsDone();//Set the task to done
                    System.out.println(line);
                    System.out.println("     " + "Nice! I've marked this task as done:");
                    System.out.println("     " + toDo.get(completedItem-1));
                    System.out.println(line);
                    continue;
                }
                catch (IndexOutOfBoundsException e){
                    System.out.println(line);
                    System.out.println("     " + "OOPS You dont have this many items in the list :)");
                    System.out.println(line);
                    continue;
                }

            }
            if(item.contains("delete")){
                item = item.replaceAll("\\D+","");//Extracts number from input
                int removeItem = Integer.parseInt(item);

//                toDo.get(completedItem-1).markAsDone();//Set the task to done
                System.out.println(line);
                System.out.println("     " + "Noted. I've removed this task:");
                System.out.println("     " + toDo.get(removeItem-1));
                toDo.remove(removeItem - 1);//Removes item at the corresponding index
                System.out.println("     Now you have " + countTasks() + " task to be done on your list!");
                System.out.println(line);
                continue;
            }
            if(item.contains("todo")){
                System.out.println(line);
                try{
//                    readFile("Data/DukeData.txt");
                    String description = item.substring(5,item.length());
                    System.out.println("     added: " + new ToDo(item));//Added item
                    toDo.add(new ToDo(description));//Added new task to arraylist
                    System.out.println("     Now you have " + countTasks() + " task to be done on your list!");
                    System.out.println(line);
                }
                catch (StringIndexOutOfBoundsException e){
                    System.out.println("     " + "Please tell us the todo task :)");
                    System.out.println(line);
                }
                catch (Exception e){
                    System.out.println(e);
                }
                continue;

            }
            if(item.contains("deadline")){
                String by = item.substring(item.lastIndexOf("/") + 1);
                String description = item.substring(9,item.lastIndexOf("/"));//Extract description
//                System.out.println(by);
                System.out.println(line);
                System.out.println("     added: " + new Deadline(description,by));//Added item
                toDo.add(new Deadline(description,by));//Added new task to arraylist
                System.out.println("     Now you have " + countTasks() + " task to be done on your list!");
                System.out.println(line);
                continue;
            }
            if(item.contains("event")){
                String by = item.substring(item.lastIndexOf("/") + 1);
                String description = item.substring(6,item.lastIndexOf("/"));
                System.out.println(line);
                System.out.println("     added: " + new Event(description,by));//Added item
                toDo.add(new Event(description,by));//Added new task to arraylist
                System.out.println("     Now you have " + countTasks() + " task to be done on your list!");
                System.out.println(line);
                continue;
            }

            else{
                 System.out.println(line);
                 System.out.println("     " + "OOPS You have entered an invalid input :)");
                 System.out.println(line);
              }


        }


    }
}
