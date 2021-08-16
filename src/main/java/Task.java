/**
 * Task created by Duke
 */
public class Task {
    String name;
    Task(String name){
        this.name = name;
    }

    public String[] addMsg(){
        return new String[]{"added: " + this.name};
    }

    public String getName(){
        return this.name;
    }

    public String toString(){
        return this.name;
    }
}

