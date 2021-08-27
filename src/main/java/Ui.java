import java.util.List;

public class Ui {
    public void dukeGreeting(){
        String name = "JARVIS";
        System.out.println("Hello I am " + name +".\nIs there anything I can do for you Sir?\n");
    }
    
    public void unusualRequest(){
        System.out.println("That is a rather unusual looking request sir.\nPerhaps you might want to specify the kind of task you would like to add.\n");
    }
    
    public void dukeResponse(String output){
        System.out.println(output);
    }

    public void list(List<Task> items){
        for(int i = 1; i <= items.size(); i++){
            System.out.println(i + ". " + items.get(i-1).toString());
        }
        System.out.println("");
    }
    
    
    public void farewellMessage(){
        System.out.println("Goodbye Sir! Will take good care of your garden in the meantime.");
    }
    
}
