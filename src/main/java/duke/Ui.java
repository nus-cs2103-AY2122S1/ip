package duke;

import java.util.Scanner;

public class Ui {

    public void greet(){
        System.out.println("Hello! I'm duke! What can I do for you?");
    }

    public void bye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String errorMessage){
        System.out.println(errorMessage);
    }

    public String readCommand(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void listAllTasks(TaskList tasks){
        for(int i = 0; i < tasks.size(); i++){
            System.out.println((i+1) + "." + tasks.get(i).toString());
        }
    }

    public void markAsDone(Task task){
        System.out.println("Nice! I've marked this task as done:\n"
                + task.toString());
    }

    public void addTask(Task task, int taskListSize){
        System.out.println("Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have "
                + taskListSize + " tasks in the list.");
    }

    public void deleteTask(Task task, int taskListSize){
        System.out.println("Successfully deleted task"
                + task.toString()
                + "\nNow you have" + taskListSize + " tasks in the list.");
    }

//    public void run(){
//        Scanner scanner = new Scanner(System.in);
//        String input = new String();
//        input = scanner.nextLine();
//        duke.Parser parser = new duke.Parser(" ");
//        while(parser.shouldStop(input)){
//            try {
//                parser.parse(input);
//
//
//                String words[] = input.split(" ");
//                if (input.equals("list")) {
//                    list();
//                } else if (words[0].equals("done")) {
//                    int textNumber = Integer.parseInt(words[1]);
//                    markTaskAsDone(textNumber - 1);
//                } else if(words[0].equals("delete")){
//                    int deleteIndex = Integer.parseInt(words[1]);
//                    deleteTask(deleteIndex - 1);
//                } else {
//                    ArrayList<duke.Task> taskArray = convertFileToArray();
//                    duke.Task task = new duke.Task();
//                    if (words[0].equals("todo")) {
//                        if(words.length <= 1){
//                            throw new duke.DukeException("The description of a todo " +
//                                    "cannot be empty.");
//                        }
//                        task = new duke.ToDo(input.substring(5));
//                    } else if (words[0].equals("deadline")) {
//                        if(words.length <= 1 || words[1].equals("/by")){
//                            throw new duke.DukeException("The description of deadline task " +
//                                    "cannot be empty.");
//                        }
//                        if(input.indexOf("/by") < 0){
//                            throw new duke.DukeException("Please enter '/by' followed by a task deadline.");
//                        }
//                        String content = input.substring(9, input.indexOf("/by") - 1);
//                        if(input.indexOf("/by") + 4 >= input.length()){
//                            throw new duke.DukeException("Please enter a deadline.");
//                        }
//                        String by = input.substring(input.indexOf("/by") + 4);
//                        task = new duke.Deadline(content, by);
//                    } else if (words[0].equals("event")) {
//                        if(words.length <= 1 || words[1].equals("/at")){
//                            throw new duke.DukeException("The description of event " +
//                                    "cannot be empty.");
//                        }
//                        if(input.indexOf("/at") < 0){
//                            throw new duke.DukeException("Please enter '/at' followed by an event time.");
//                        }
//                        String content = input.substring(6, input.indexOf("/at") - 1);
//                        if(input.indexOf("/at") + 4 >= input.length()){
//                            throw new duke.DukeException("Please provide the event time.");
//                        }
//                        String at = input.substring(input.indexOf("/at") + 4);
//                        task = new duke.Event(content, at);
//                    } else {
//                        throw new duke.DukeException("I'm sorry, but I don't know what that means :-(");
//                    }
//                    taskArray.add(task);
//                    writeTaskToFile(task);
//
//                    System.out.println("Got it. I've added this task:\n"
//                            + task.toString()
//                            + "\nNow you have "
//                            + taskArray.size() + " tasks in the list.");
//                }
//            }catch(duke.DukeException e){
//                System.out.println("☹ OOPS!!!" + e.getMessage());
//            }
//            input = scanner.nextLine();
//        }
//        ui.bye();
}
