package genie.exception;

public class EmptyMessage extends Exception{ 
    
    public EmptyMessage(String error) {
        super(error);
    }

    @Override
    public String toString() {
        return "You have to say something Aladdin. \nI cannot read your mind.";
    }
}
