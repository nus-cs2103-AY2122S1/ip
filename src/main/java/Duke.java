import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class Duke {

    public static void main(String[] args) throws DukeException, IOException{
        String pathName = "./data/duke.txt";
        File f = new File(pathName);
        try {
            if(!f.exists()){
                f.createNewFile();
            }
            Scanner sc = new Scanner(f);
            ArrayList<Task> t = new ArrayList<>();
            while (sc.hasNext()) {
                String task = sc.nextLine();
                String[] taskArr = task.split("//|");
                if (taskArr[0].equals("T")) {
                    Todo td = new Todo(taskArr[2]);
                    if (taskArr[1].equals("1")) {
                        td.markAsDone();
                    }
                    t.add(td);
                }
                if (taskArr[0].equals("D")) {
                    Deadline d = new Deadline(taskArr[2], taskArr[3]);
                    if (taskArr[1].equals("1")) {
                        d.markAsDone();
                    }
                    t.add(d);
                }
                if (taskArr[0].equals("E")) {
                    Event e = new Event(taskArr[2], taskArr[3]);
                    if (taskArr[1].equals("1")) {
                        e.markAsDone();
                    }
                    t.add(e);
                }
            }
            int ctr = 0;
            Scanner scanner = new Scanner(System.in);
            System.out.println("_______________________________________________");
            System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
            System.out.println("_______________________________________________");

            while (scanner.hasNext()) {
                try {
                    String response = scanner.nextLine();
                    if (response.equals("list")) {
                        System.out.println("_______________________________________________");
                        System.out.println("Here are the tasks on your list: ");
                        for (int i = 0; i < ctr; i++) {
                            System.out.println((i + 1) + ". " + t.get(i));
                        }
                        System.out.println("_______________________________________________");
                    } else if (response.equals("bye")) {
                        System.out.println("_______________________________________________");
                        System.out.println("Bye! Hope to see you again soon!");
                        System.out.println("_______________________________________________");
                        writeFile(f, t);
                        System.exit(0);
                    } else if (response.contains("done")) {
                        String[] str = response.split(" ");
                        String task = str[0];
                        int num = Integer.parseInt(str[1]);
                        t.get(num - 1).markAsDone();

                        System.out.println("Nice! I've marked this task as done: \n" + t.get(num - 1).toString());
                        System.out.println("_______________________________________________");
                    } else if (response.contains("todo")) {
                        if (response.length() > 4) {
                            String[] str = response.split(" ");
                            String task = str[0];
                            String command = str[1];
                            command = response.substring(response.indexOf(" ") + 1);
                            Task td = new Todo(command);
                            t.add(td);

                            System.out.println("Got it! I've added this task: \n" + td.toString());
                            ctr++;
                            System.out.println("Now you have " + ctr + " tasks in the list.");
                            System.out.println("_______________________________________________");
                        } else {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                    } else if (response.contains("deadline")) {
                        int tLabelFirst = response.indexOf(" ") + 1;
                        int tTimeFirst = response.indexOf("/");
                        String tLabel = response.substring(tLabelFirst, tTimeFirst - 1);
                        String tTime = response.substring(tTimeFirst + 4);
                        Task td = new Deadline(tLabel, tTime);
                        t.add(td);

                        System.out.println("Got it! I've added this task: \n" + td.toString());
                        ctr++;
                        System.out.println("Now you have " + ctr + " tasks in the list.");
                        System.out.println("_______________________________________________");
                    } else if (response.contains("event")) {
                        int tLabelFirst = response.indexOf(" ") + 1;
                        int tTimeFirst = response.indexOf("/");
                        String tLabel = response.substring(tLabelFirst, tTimeFirst - 1);
                        String tTime = response.substring(tTimeFirst + 4);
                        Task td = new Event(tLabel, tTime);
                        t.add(td);

                        System.out.println("Got it! I've added this task: \n" + td.toString());
                        ctr++;
                        System.out.println("Now you have " + ctr + " tasks in the list.");
                        System.out.println("_______________________________________________");
                    } else if (response.contains("delete")) {
                        String[] str = response.split(" ");
                        String task = str[0];
                        int num = Integer.parseInt(str[1]);
                        Task tsk = t.remove(num - 1);

                        System.out.println("Noted. I've now removed this task: \n" + tsk);
                        ctr--;
                        System.out.println("Now you have " + ctr + " tasks in the list.");
                        System.out.println("_______________________________________________");
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-( " +
                                "Try todo, event, or deadline");
                    }
                } catch (DukeException d) {
                    System.out.println(d.getMessage());
                }
            }
        }catch(IOException e){
                System.out.println("OOPS!!! Directory not found. " + e.getMessage());
                System.exit(0);
        }
    }
    public static void writeFile(File f, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(f);
        for(int i = 0; i < tasks.size(); i++){
            Task t = tasks.get(i);
            String text = "";
            if(t.label.equals("T")){
                text = text.concat("T|");
                if(t.isDone){
                    text = text.concat("1|");
                }else{
                    text = text.concat("0|");
                }
                text = text.concat(t.description + "\n");
            }
            if(t.label.equals("D")){
                text = text.concat("D|");
                if(t.isDone){
                    text =  text.concat("1|");
                }else{
                    text = text.concat("0|");
                }
                text = text.concat(t.description + "|" + ((Deadline) t).by + "\n");
            }
            if(t.label.equals("E")){
                text =  text.concat("E|");
                if(t.isDone){
                    text = text.concat("1|");
                }else{
                    text = text.concat("0|");
                }
                text = text.concat(t.description + "|" + ((Event) t).by + "\n");
            }
            fw.write(text);
        }
        fw.close();
    }
}
