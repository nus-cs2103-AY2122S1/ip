package genie.exception;

/**
 * A class that represents the exception that is thrown
 * when the description of the Task is empty.
 */
public class EmptyMessage extends Exception{ 
    
    public EmptyMessage(String error) {
        super(error);
    }

    @Override
    public String toString() {
        return "You have to say something Aladdin. \nI cannot read your mind.";
    }
}
