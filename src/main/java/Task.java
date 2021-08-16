/**
 * member of a List for Duke to create
 */
public class Task {
    String storedText;
    Task(String storedText){
        this.storedText = storedText;
    }

    public String[] addMsg(){
        return new String[]{"added: " + this.storedText};
    }

    public String toString(){
        return this.storedText;
    }
}

