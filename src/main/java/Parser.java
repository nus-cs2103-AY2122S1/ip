import java.time.LocalDate;
import java.util.ArrayList;

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

    static String parseInput(String input,TaskList taskList, Ui ui) {
        String[] infos = input.split(" ", 2);
        String type = infos[0];
        switch (type) {
            case "bye":
                ui.showGoodbye();
                return null;
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
            case "delete":
                if (infos.length == 2) {

                } else {
                    return "Wrong input format";
                }


        }
    }

            while(!input.equals("bye")) {
        if (input.equals("list")) {
            System.out.println(printList(lst));
        } else if (input.split(" ")[0].equals("schedule")) {

        } else if (input.substring(0,Math.min(input.length(), 5)).equals("done ")) {
            String index = input.split(" ", 2)[1];
            System.out.println(doTask(index, lst));
        } else if (input.substring(0,Math.min(input.length(), 7)).equals("delete ")) {
            String index = input.split(" ", 2)[1];
            System.out.println(deleteTask(index, lst));
        } else {
            System.out.println(addList(input, lst));
        }
        input = sc.nextLine();
    }

    public static String doTask(String index, TaskList taskList) {
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

    static String getSchedule(String info, TaskList taskList) {
        ArrayList<Task> lst = taskList.getTasks();
        String output = "";
        LocalDate date;
        try {
            date = LocalDate.parse(info.split(" ")[1]);
        } catch (Exception e) {
            System.out.println("Wrong format of date");
            return;
        }
        for (Task t : lst) {
            if (date.equals(t.getDate())) {
                //System.out.println(t.toString());
                output += (t.toString() + "\n");
            }
        }
    }

    public String  deleteTask(String index, TaskList taskList) {
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


