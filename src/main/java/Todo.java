public class Todo extends Task{
    private String eventType;

    /**
     * Takes in a string Set the eventType and description of the instance
     * @param input
     */
    public Todo(String input){
        super();
        String key = input.trim();
        super.setDescription(key);
        this.eventType = "[T]";
    }

    @Override
    public String toString(){
        return this.eventType + super.toString();
    }
}
