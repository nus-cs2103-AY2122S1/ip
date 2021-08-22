package Duke;

import java.util.Scanner;

public class Ui {
    private Scanner Sc;

    public Ui(){
        this.Sc = new Scanner(System.in);
    }

    public String getInput(){
        return Sc.nextLine() + " ";
    }
    
    public void close(){
        this.Sc.close();
    }
}
