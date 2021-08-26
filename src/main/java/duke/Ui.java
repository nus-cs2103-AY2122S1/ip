package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Ui {
    String line = "-----------------------------------------";

//    Scanner textInput = new Scanner(System.in);
//    private boolean loop = true;
TaskList taskList;

    Ui(TaskList list){
        this.taskList = list;
    }

     int countTasks(){
        int count = 0;
        for(int i = 0;i < taskList.size();i++){
            if(!taskList.get(i).isDone){
                count ++;
            }
        }
        return count;
    }

    public static void welcomeUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
    /**
     * Marks item as done on the list
     * @param item User input to complete item from list
     */
    public void doneResponse(String item) {
        try {
            item = item.replaceAll("\\D+","");//Extracts number from input
            int completedItem = Integer.parseInt(item);
            taskList.get(completedItem-1).markAsDone();//Set the task to done
            System.out.println(line);
            System.out.println("     " + "Nice! I've marked this task as done:");
            System.out.println("     " + taskList.get(completedItem-1));
            System.out.println(line);
//            continue;
        }
        catch (IndexOutOfBoundsException e){
            System.out.println(line);
            System.out.println("     " + "OOPS You dont have this many items in the list :)");
            System.out.println(line);
//            continue;
        }
    }
    /**
     * Removes task from TaskList and updates user with number of tasks left to do
     * @param item User input to delete item from list
     */
    public void deleteResponse(String item) {
        item = item.replaceAll("\\D+","");//Extracts number from input
        int removeItem = Integer.parseInt(item);

//                toDo.get(completedItem-1).markAsDone();//Set the task to done
        System.out.println(line);
        System.out.println("     " + "Noted. I've removed this task:");
        System.out.println("     " + taskList.get(removeItem-1));
        taskList.removeTask(removeItem - 1);//Removes item at the corresponding index
        System.out.println("     Now you have " + countTasks() + " task to be done on your list!");
        System.out.println(line);
//        continue;
    }

    public void byeResponse() {
        System.out.println(line);
        System.out.println("     " + "Bye. Hope to see you again soon!");
        System.out.println(line);
//        myObj.close();
//        saveTasks(taskList);
//        break;
    }

    public void listResponse() {
        System.out.println(line);
        for(int i = 0; i < taskList.size(); i++){
            System.out.println("     " + String.valueOf(i+1) + ". " + taskList.get(i).toString());
        }
        System.out.println(line);
//        continue;
    }
    public void todoResponse(String input){
        System.out.println(line);
                try{
                    String description = input.substring(5,input.length());
                    System.out.println("     added: " + new ToDo(input));//Added item
                    taskList.addTask(new ToDo(description));//Added new task to arraylist
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
//                continue;
    }
    public void deadlineResponse(String input){
        try{
            String by = input.substring(input.lastIndexOf("/") + 1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localTimeObj = LocalDate.parse(by,formatter);
            String description = input.substring(9,input.lastIndexOf("/"));//Extract description
            System.out.println(line);
            System.out.println("     added: " + new Deadline(description,localTimeObj.toString()));//Added item
            taskList.addTask(new Deadline(description,localTimeObj.toString()));//Added new task to arraylist
            System.out.println("     Now you have " + countTasks() + " task to be done on your list!");
            System.out.println(line);
//          continue;
        }catch (Exception e){
            String by = input.substring(input.lastIndexOf("/") + 1);
            String description = input.substring(9,input.lastIndexOf("/"));//Extract description
            System.out.println(line);
            System.out.println("     added: " + new Deadline(description,by.toString()));//Added item
            taskList.addTask(new Deadline(description,by));//Added new task to arraylist
            System.out.println("     Now you have " + countTasks() + " task to be done on your list!");
            System.out.println(line);
//                    continue;
        }
    }
    public void eventResponse(String input){
        try{
            String by = input.substring(input.lastIndexOf("/") + 1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localTimeObj = LocalDate.parse(by,formatter);
            String description = input.substring(6,input.lastIndexOf("/"));
            System.out.println(line);
            System.out.println("     added: " + new Event(description,localTimeObj.toString()));//Added item
            taskList.addTask(new Event(description,localTimeObj.toString()));//Added new task to arraylist
            System.out.println("     Now you have " + countTasks() + " task to be done on your list!");
            System.out.println(line);
        }catch (Exception e) {
            String by = input.substring(input.lastIndexOf("/") + 1);
            String description = input.substring(6, input.lastIndexOf("/"));
            System.out.println(line);
            System.out.println("     added: " + new Event(description, by));//Added item
            taskList.addTask(new Event(description, by));//Added new task to arraylist
            System.out.println("     Now you have " + countTasks() + " task to be done on your list!");
            System.out.println(line);
        }
    }

    public void findResponse(String input){
        String keyWord = input.substring(input.lastIndexOf("find") + 5);
        TaskList results = taskList.findTask(keyWord);
        for(int i = 0; i < results.size(); i++){
            Task result = results.get(i);
            System.out.println(result + "\n");
        }
    }

    void invalidInput(){
        System.out.println(line);
        System.out.println("     " + "OOPS You have entered an invalid input :)");
        System.out.println(line);
    }

}