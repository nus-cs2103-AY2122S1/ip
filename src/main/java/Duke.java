import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath){
        this.storage = new Storage(filePath);
        this.tasks = storage.convertFileToTaskList();
        this.ui = new Ui();
    }

    public void run(){
        ui.greet();
        boolean isExit = false;
        Parser parser = new Parser(" ");
        while(!isExit){
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e){
                ui.showError(e.getMessage());
            }
        }
        ui.bye();
    }


    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }





















//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String input = new String();
//        Ui ui = new Ui();
//
//        ui.greet();
//        input = sc.nextLine();
//        while(!input.equals("bye")){
//            try {
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
//                    ArrayList<Task> taskArray = convertFileToArray();
//                    Task task = new Task();
//                    if (words[0].equals("todo")) {
//                        if(words.length <= 1){
//                            throw new DukeException("The description of a todo " +
//                                    "cannot be empty.");
//                        }
//                        task = new ToDo(input.substring(5));
//                    } else if (words[0].equals("deadline")) {
//                        if(words.length <= 1 || words[1].equals("/by")){
//                            throw new DukeException("The description of deadline task " +
//                                    "cannot be empty.");
//                        }
//                        if(input.indexOf("/by") < 0){
//                            throw new DukeException("Please enter '/by' followed by a task deadline.");
//                        }
//                        String content = input.substring(9, input.indexOf("/by") - 1);
//                        if(input.indexOf("/by") + 4 >= input.length()){
//                            throw new DukeException("Please enter a deadline.");
//                        }
//                        String by = input.substring(input.indexOf("/by") + 4);
//                        task = new Deadline(content, by);
//                    } else if (words[0].equals("event")) {
//                        if(words.length <= 1 || words[1].equals("/at")){
//                            throw new DukeException("The description of event " +
//                                    "cannot be empty.");
//                        }
//                        if(input.indexOf("/at") < 0){
//                            throw new DukeException("Please enter '/at' followed by an event time.");
//                        }
//                        String content = input.substring(6, input.indexOf("/at") - 1);
//                        if(input.indexOf("/at") + 4 >= input.length()){
//                            throw new DukeException("Please provide the event time.");
//                        }
//                        String at = input.substring(input.indexOf("/at") + 4);
//                        task = new Event(content, at);
//                    } else {
//                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
//                    }
//                    taskArray.add(task);
//                    writeTaskToFile(task);
//
//                    System.out.println("Got it. I've added this task:\n"
//                            + task.toString()
//                            + "\nNow you have "
//                            + taskArray.size() + " tasks in the list.");
//                }
//            }catch(DukeException e){
//                System.out.println("☹ OOPS!!!" + e.getMessage());
//            }
//            input = sc.nextLine();
//        }
//        ui.bye();
//    }
}
