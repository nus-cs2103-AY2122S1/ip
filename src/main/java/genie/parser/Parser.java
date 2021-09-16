package genie.parser;

import genie.exception.GenieException;
import genie.common.Message;
import genie.tasks.TaskList;

public class Parser {
    
    final private TaskList taskList;
    
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Method that is called and runs until the bye command in inputted. 
     * Listens for all the commands input and responds accordingly.
     * 
     * @param str Input string by user.
     * @return Response message by system.
     */
    public String run(String str) {
        String output;
        str = str.trim();
        try {
            //command done
            if (str.contains("done")) {
                output = taskList.markDone(str);
                return output;
            }
            //command list
            else if (str.equals("list")) {
                output = taskList.showList("");
                return output;
            }
            //command find 
            else if (str.contains("find")) {
                output = taskList.findTask(str.substring(5));
                return output;
            }
            //command to do
            else if (str.contains("todo")) {
                output = taskList.todoTask(str);
                return output;
            }
            //command deadline
            else if (str.contains("deadline")) {
                output = taskList.deadlineTask(str);
                return output;
            }
            //command event
            else if (str.contains("event")) {
                output = taskList.eventsTask(str);
                return output;
            } 
            // command delete
            else if (str.contains("delete")) {
                output = taskList.deleteTask(str);
                return output;
            } 
            else if (str.contains("help")) {
                return Message.HELPCOMMANDS;
            } 
            else if (str.contains("show")) {
                output = taskList.showPriority(str);
                return output;
            }
            else {
                throw new GenieException("Command is not valid!");
            }
        } catch (GenieException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return Message.repeatReply(str);
    }
}
