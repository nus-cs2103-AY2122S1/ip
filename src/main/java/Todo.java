public class Todo extends Task{

    /**
     * Takes in a string Set the eventType and description of the instance
     * @param input
     */
    public Todo(String input){
        super();
        String key = input.trim();
        super.setEventType("T");
        super.setDescription(key);
        super.setTime(null);
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
