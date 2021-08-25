package duke;

public class Ui {
    String line = "____________________________________________________________\n";

    public void Start(){
        System.out.println(line + " Hello! I'm Duke\n" + " What can I do for you?\n" + line);
    }

    public void Bye(){
        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }

    public void list(String list){
        System.out.println(line + list + line);
    }

    public void Done(TaskList list, int doneNum){
        System.out.println(line + "Nice! I've marked this task as done:\n" + list.get(doneNum));
    }

    public void Delete(Task delete, int count){
        System.out.println(line + "Noted. I've removed this task:\n" + delete +
                "\nNow you have " + count + " tasks in the list.\n" + line);
    }

    public void showDoneError(){
        System.out.println("\n" + line +
                "\n☹ OOPS!!! No such task to be done.\n" + line);
    }

    public void showDeleteError(){
        System.out.println("\n" + line +
                "\n☹ OOPS!!! No such task to be deleted.\n" + line);
    }

    public void todo(Task todo,int count){
        System.out.println(line + "Got it. I've added this task:\n" + todo +
                "\nNow you have " + count + " tasks in the list.\n" + line);
    }


    public void deadline(Task deadline,int count){
        System.out.println(line + "Got it. I've added this task:\n" + deadline +
                "\nNow you have " + count + " tasks in the list.\n" + line);
    }

    public void showDeadlineError1(){
        System.out.println("\n" + line +
                "\n☹ OOPS!!! The time must be in the format YYYY-MM-DD\n" + line);
    }


    public void event(Task event,int count){
        System.out.println(line + "Got it. I've added this task:\n" + event +
                "\nNow you have " + count + " tasks in the list.\n" + line);
    }

    public void find(String findList){
        System.out.println(line + "Here are the matching tasks in your list:" + findList + line);
    }

    public String emptyDescriptionError(){
        return "\n" + line + "\n☹ OOPS!!! The description cannot be empty.\n" + line;
    }

    public void defaultError(){
        System.out.println("\n" + line +
                "\n☹ OOPS!!! I'm sorry, but I don't know what that means :-(.\n" + line);
    }
}
