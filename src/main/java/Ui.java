import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    static String line = "____________________________________________________________";


    public void HelloMessage() {
        String Hello_message = "Hello! I'm Duke\n" +
                "What can I do for you?\n";

        System.out.println(line + "\n" + Hello_message + line);
    }

    public void GoodbyeMessage() {
        String Goodbye_message = "Bye. Hope to see you again soon!";
        System.out.println(Goodbye_message);
    }


 /*   public void SpecificDateEvent(String time){
        int count = 0;//count the number of the events happen on the time.
        System.out.println("Here are all the tasks taking place on the date you give me:");
        for (int i = 0; i < list.size(); i++) {
            String Message = list.get(i).PrintTaskInfo();
            String UnParsedInfo = list.get(i).GetTime();
            if (UnParsedInfo != null && (UnParsedInfo.contains(time) || (ParseTime(time)!=null && UnParsedInfo.contains(
                    ParseTime(time).format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH)))))
                    ||Message.contains(time) || (ParseTime(time)!=null && Message.contains(
                    ParseTime(time).format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH))))) {
                count++;
                System.out.println(i + 1 + "." + Message);
            }
        }
        if (count == 0) {
            System.out.println("Sorry. There is no event occurred on the time you give me!! :(");
        }
    }*/


    public void PrintList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i).PrintTaskInfo());
        }
    }

    public void MarkDone(String info) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + info);
    }

    public void Delete(String info, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + info);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void add(String info, int size) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(" " + info);
        System.out.println("Now you have " + size + "" +
                " tasks in the list.");
    }


    public void PrintAline() {
        System.out.println(line);
    }

    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String Message = scanner.nextLine();

        return Message;
    }

    public ArrayList<String> ARoundOfInput() throws DukeException{
        System.out.println();
        Parser p;
        ArrayList<String> Info = new ArrayList<>();
        String Message = "";
        String OperationType = "";

        Message = getInput();
        p = new Parser(Message);

        Info.add(p.getOperationType());
        Info.add(p.getTask());
        Info.add(p.getTime());
        Info.add(p.getIndex().toString());

        return Info;
    }

    public void showLoadingError() {
        System.out.println("Cannot Load From Data.");
    }

    public void showSavingError() {
        System.out.println("Cannot Save the Data.");
    }
}
