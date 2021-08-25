package duke;

import java.util.Scanner;

import tasks.TaskList;

public class Parser {
    
    final private TaskList taskList;
    final private  String LINE = "---------------------------------";
    
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Method that is called and runs until the bye command in inputted. 
     * Listens for all the commands input and responds accordingly.
     */
    public void run() {
        
        boolean end = false;
        int i = 0;
        Scanner sc = new Scanner(System.in);
    
        while (!end) {
            String str = sc.nextLine();
            str = str.trim();
            try {
                // command "bye"
                if (str.equals("bye")) {
                    end = true;
                    System.out.println(LINE);
                    System.out.println("Bye. Hope to see you again soon!");
                    sc.close();
                }

                //command done
                else if (str.contains("done")) {
                    System.out.println(LINE);
                    System.out.println(taskList.markDone(str));
                }

                //command list
                else if (str.equals("list")) {
                    System.out.println(LINE);
                    System.out.println(taskList.showList(""));
                }

                //command to do
                else if (str.contains("todo")) {
                    System.out.println(LINE);
                    System.out.println(taskList.todoTask(str));
                }

                //command deadline
                else if (str.contains("deadline")) {
                    System.out.println(LINE);
                    System.out.println(taskList.deadlineTask(str));
                }

                //command event
                else if (str.contains("event")) {
                    System.out.println(LINE);
                    System.out.println(taskList.eventsTask(str));
                } 
                
                else if (str.contains("delete")) {
                    System.out.println(LINE);
                    System.out.println(taskList.deleteTask(str));
                } 
                
                else if (str.contains("help")) {
                    Ui.allCommands();
                }
                
                else {
                    throw new DukeException("Command is not valid!");
                }
            } catch (DukeException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        System.out.println(LINE);
    }

    
        
}
