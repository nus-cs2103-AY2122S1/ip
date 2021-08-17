import java.util.ArrayList;

public class Tasks {
    private ArrayList<Task> tasks = new ArrayList<>();
    //for division
    private static String ind = "    ";
    //for sentences
    private static String ind2 = "     ";
    private static String div = ind + "____________________________________________________________";

    public void addTask(String t) {
        String[] ss = t.split(" ");
        switch (ss[0]) {
            case("todo"):
                String i = "";
                for (int q = 1; q < ss.length;q++) {
                    i += ss[q];
                    if (q != ss.length - 1) {
                        i += " ";
                    }
                }
                if (i != "") {
                    Todo todo = new Todo(i);
                    this.tasks.add(todo);
                    noteAdded(todo);
                }
                break;
            case("deadline"):
                int j = findTime("/by", ss);
                String[] info = getInfo(j, ss);
                if (info[0] != null && info[1] != null) {
                    Deadline ddl = new Deadline(info[0], info[1]);
                    this.tasks.add(ddl);
                    noteAdded(ddl);
                }
                break;
            case("event"):
                int k = findTime("/at", ss);
                String[] info2 = getInfo(k, ss);
                if (info2[0] != null && info2[1] != null) {
                    Event e = new Event(info2[0], info2[1]);
                    this.tasks.add(e);
                    noteAdded(e);
                }
                break;
            default:
                break;
        }
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    private void noteAdded(Task t) {
        int total = tasks.size();
        String sOrNot = "";
        if (total <= 1) {
            sOrNot = "task";
        } else {
            sOrNot = "tasks";
        }
        System.out.println(div);
        System.out.println(ind2 + "Got it. I've added this task: ");
        System.out.println(ind2 +" "+ t);
        System.out.println(ind2 + "Now you have " + total + " " + sOrNot + " in the list.");
        System.out.println(div);
    }

    public void printTasks() {
        System.out.println(div);
        System.out.println(ind2 + "Here are the tasks in your list:");
        int i = 1;
        for (Task task: tasks) {
            System.out.println( ind2+ i + ". "+ task);
            i++;
        }
        System.out.println(div);
    }

    public void complete(int pos) {
        if (this.tasks.size()>pos-1 && pos != 0) {
            String p = this.tasks.get(pos-1).finished();
            System.out.println(div + "\n" + ind2 + "Nice! I've marked this task as done: " + "\n" +
                    ind2 + ind2 + p + "\n" + div);
        }
    }

    private static int findTime(String s, String[] arr) {
        for (int i = 0; i < arr.length;i++) {
            if (arr[i].equals(s)) {
                return i;
            }
        }
        return 0;
    }

    private static String[] getInfo(int j, String[] ss) {
        String[] result = new String[2];
        if (j != 0) {
            String name = "";
            String time = "";
            int counter = 1;
            while (counter < j) {
                name += ss[counter];
                if (counter != j - 1) {
                    name += " ";
                }
                counter++;
            }
            counter++;
            while(counter < ss.length) {
                time += ss[counter];
                if (counter != ss.length - 1) {
                    time += " ";
                }
                counter ++;
            }
            result[0] = name;
            result[1] = time;

            return result;

        } else {
            return result;
        }
    }
}
