package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

class Parser {
    static Task convertRecordToTask(String record) {
        String[] strings = record.split("&&");
        switch (strings[0]) {
            case "T":
                return new Todo(strings[2], strings[1].equals("Done"));
            case "D":
                return new Deadline(strings[2], strings[3], strings[1].equals("Done"));
            case "E":
                return new Event(strings[2], strings[3], strings[1].equals("Done"));
            default:
                return null;
        }
    }

    static String parseInput(String input, TaskList taskList, Ui ui) {
        String[] infos = input.split(" ", 2);
        String type = infos[0];
        switch (type) {
            case "bye":
                ui.showGoodbye();
                System.exit(0);
                //return null;
            case "list":
                if (infos.length == 1) {
                    ui.printList(taskList);
                    return null;
                } else {
                    return "Wrong input format";
                }
            case "schedule":
                return getSchedule(input, taskList);
            case "done":
                if (infos.length == 2) {
                    String index = infos[1];
                    return doTask(index, taskList);
                } else {
                    return "Wrong input format";
                }
            case "find":
                if (infos.length == 2) {
                    String content = infos[1];
                    return findContent(content, taskList);
                } else {
                    return "Wrong input format";
                }
            case "delete":
                if (infos.length == 2) {
                    String index = infos[1];
                    return deleteTask(index, taskList);
                } else {
                    return "Wrong input format";
                }
            default:
                return addList(input, taskList);
        }
    }


    /**
     * Returns error messages or a string showing the added task.
     * The input string must follow input format, otherwise error messages will be return.
     * The new task will be created and added to lst.
     * @param input input message
     * @param taskList the taskList containing all tasks
     * @return a string showing the added task and number of tasks
     */
     static String addList(String input, TaskList taskList) {
         ArrayList<Task> lst = taskList.getTasks();
         ArrayList<String> validType = new ArrayList<>(
                 Arrays.asList("deadline", "event", "todo"));
         String type = input.split(" ", 2)[0];
         String content;
         if (!validType.contains(type)) {
             return "I'm sorry, but I don't know what that means :-(";
         }
         try {
             content = input.split(" ", 2)[1];
         } catch (IndexOutOfBoundsException e) {
             return "The description of a todo cannot be empty.";
         }

         if (type.equals("todo")) {
             lst.add(new Todo(content));
         } else if (type.equals("deadline")) {
             String[] strings = content.split(" /by ");
             if (strings.length != 2) {
                 return "Please check the format of your deadline.";
             }
             lst.add(new Deadline(content.split(" /by ")[0], content.split( " /by ")[1]));
         } else if (type.equals("event")) {
             String[] strings = content.split(" /at ");
             if (strings.length != 2) {
                 return "Please check the format of your event.";
             }
             lst.add(new Event(content.split(" /at ")[0], content.split(" /at ")[1]));
         }

         String output = "    ____________________________________________________________\n"
                 + "     Got it. I've added this task: \n"
                 + "      " + lst.get(lst.size() - 1).toString() + "\n"
                 + "     Now you have " + lst.size() +" tasks in the list. \n"
                 + "    ____________________________________________________________\n";
         return output;
     }



    /**
     * Returns the message that shows which task is marked as completed.
     * Mark the task indicated by index as completed.
     * @param index the index of task to be marked
     * @param taskList the taskList containing all tasks
     * @return the messages
     */
    static String doTask(String index, TaskList taskList) {
        int idx;
        ArrayList<Task> lst = taskList.getTasks();
        try {
            idx = Integer.parseInt(index);
            lst.get(idx - 1);
        } catch (NumberFormatException nfe) {
            return "Please check the format of the index.";
        } catch (IndexOutOfBoundsException e) {
            return "The task does not exist in task list.";
        }


        StringBuilder s = new StringBuilder();
        s.append("    ____________________________________________________________\n");
        s.append("    Nice! I've marked this task as done: \n");

        lst.get(idx - 1).setDone();
        s.append("       " + lst.get(idx - 1).toString() + "\n");

        s.append("    ____________________________________________________________\n");

        return s.toString();
    }

    static String findContent(String content, TaskList taskList) {
        ArrayList<Task> lst = taskList.getTasks();
        String output = "";
        for (Task t: lst) {
            if (t.getContent().contains(content)) {
                output += (t.toString() + "\n");
            }
        }
        if (output.equals("")) {
            return "No matching tasks";
        } else {
            return output;
        }
    }

    static String getSchedule(String info, TaskList taskList) {
        ArrayList<Task> lst = taskList.getTasks();
        String output = "";
        LocalDate date;
        try {
            date = LocalDate.parse(info.split(" ")[1]);
        } catch (Exception e) {
            return "Wrong format of date";

        }
        for (Task t : lst) {
            if (date.equals(t.getDate())) {
                //System.out.println(t.toString());
                output += (t.toString() + "\n");
            }
        }
        return output;
    }

    static String deleteTask(String index, TaskList taskList) {
        ArrayList<Task> lst = taskList.getTasks();
        int idx;
        try {
            idx = Integer.parseInt(index);
            lst.get(idx - 1);
        } catch (NumberFormatException nfe) {
            return "Please check the format of the index.";
        } catch (IndexOutOfBoundsException e) {
            return "The task does not exist in task list.";
        }

        Task currTask = lst.get(idx - 1);
        lst.remove(currTask);

        String output = "    ____________________________________________________________\n"
                + "     Noted. I've removed this task: \n"
                + "      " + currTask.toString() + "\n"
                + "     Now you have " + lst.size() +" tasks in the list. \n"
                + "    ____________________________________________________________\n";
        return output;
    }



}


