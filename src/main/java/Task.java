public class Task {

    private final String name;
    private final int number;
    private final boolean done;

    Task(String name, int number, boolean done){
        this.name = name;
        this.number = number;
        this.done = done;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", (this.done)? "X" : " ", this.name);
    }

    String getName(){
        return this.name;
    }

    int getNumber(){
        return this.number;
    }

}
