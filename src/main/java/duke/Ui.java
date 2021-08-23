package duke;

import java.util.Scanner;

public class Ui {
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner sc;
    
    public Ui() {
        this.sc = new Scanner(System.in);
    }
    
    public void greet() {
        System.out.println(LOGO);
        System.out.println("Hello! I'm duke.Duke\nWhat can I do for you?");
    }
    
    public void showError(DukeException e) {
        e.print();
    }
    
    public void displayList(TaskList list) {
        System.out.println(list.toString());
    }
   
    public String getInput() {
        return sc.nextLine().strip(); 
    }
    
    public void stopInput() {
        System.out.println("Byebye"); 
        sc.close(); 
    }
}
