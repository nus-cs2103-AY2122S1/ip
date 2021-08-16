import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean bye = false;
        ArrayList<Task> taskList = new ArrayList<>(100);
        int listLength = 0;

        System.out.println("  ____________________________________________________________");
        System.out.print("  Hello! I'm Duck.\n  What's up?\n");
        System.out.println("  ____________________________________________________________\n");
        
        while (!bye) {
            try {
                System.out.print("> ");
                String text = input.nextLine();

                System.out.println("  ____________________________________________________________");

                if (text.equals("bye")) { // bye function: exits the loop, ends process
                    System.out.println("  See you next time!");
                    bye = true;
                    
                } else if (text.equals("list")) { // list function: iterates through taskList, prints Tasks' listEntry
                    System.out.println("  Here are the tasks in your list:");
                    for (int i = 0; i < listLength; ++i) {
                        System.out.println("  " + (i + 1) + "." + taskList.get(i).listEntry());
                    }
                    
                } else if (text.split(" ")[0].equals("done")) { // done function: sets a task to done
                    if (text.split(" ").length == 1) {
                        throw new DukeException("invalidDone");
                    } else {
                        int toSet = Integer.parseInt(text.split(" ")[1]);
                        if (toSet > listLength) {
                            throw new DukeException("invalidDone");
                        } else {
                            taskList.get(toSet - 1).setDone();
                            System.out.print("  Nice! I've marked this task as done:\n    " + 
                                    taskList.get(toSet - 1).listEntry() + "\n");
                        }
                    }

                } else if (text.split(" ")[0].equals("delete")) { // delete function: delete a task
                    if (text.split(" ").length == 1) {
                        throw new DukeException("invalidDelete");
                    } else {
                        int toDelete = Integer.parseInt(text.split(" ")[1]);
                        if (toDelete > listLength) {
                            throw new DukeException("invalidDelete");
                        } else {
                            Task deleted = taskList.remove(toDelete - 1);
                            System.out.print("  Noted. I've removed this task:\n    " + deleted.listEntry() +
                                    "\n  Now you have " + --listLength + " tasks in the list.\n");
                        }
                    }
                    
                } else { // task function: add tasks
                    if (text.split(" ").length == 1) { // task details not given or not valid task
                        switch (text) {
                            case "deadline":
                                throw new DukeException("deadlineDesc");

                            case "event":
                                throw new DukeException("eventDesc");

                            case "todo":
                                throw new DukeException("todoDesc");

                            default:
                                throw new DukeException("invalidInput");
                        }

                    } else {
                        Task newTask;

                        // split text string, first string will be the task type and second string will be task details
                        String[] taskString = text.split(" ", 2);
                        String taskType = taskString[0];
                        String taskDetails = taskString[1];

                        // determine type of task, create new task
                        switch (taskType) {
                            case "deadline": {
                                String[] details = taskDetails.split(" /by ");
                                
                                if (details.length == 1) { // time of deadline not given
                                    throw new DukeException("deadlineTime");
                                } else {
                                    newTask = new Deadline(details[0], details[1]);
                                }
                                break;
                            }
                            
                            case "event": {
                                String[] details = taskDetails.split(" /at ");
                                
                                if (details.length == 1) { // period of event not given
                                    throw new DukeException("eventPeriod");
                                } else {
                                    newTask = new Event(details[0], details[1]);
                                }
                                break;
                            }
                            
                            case "todo":
                                newTask = new Todo(taskDetails);
                                break;
                                
                            default:  // taskName is invalid
                                throw new DukeException("invalidInput");
                        }
                        // add task to taskList
                        taskList.add(listLength++, newTask);
                        System.out.print("  Got it. I've added this task:\n    " + newTask.listEntry() + 
                                "\n  Now you have " + listLength + " tasks in the list.\n");
                    }
                }
                
            } catch (DukeException e) {
                System.out.println(e.getMessage());

            } catch (NumberFormatException e) { // NFE throws if the index given in the done function is not an integer
                System.out.println(new DukeException("invalidDone").getMessage());
                
            } finally {
                System.out.println("  ____________________________________________________________\n");
            }
        }

        input.close();
    }
}
