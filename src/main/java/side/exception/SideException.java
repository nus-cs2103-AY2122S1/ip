package side.exception;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke: Incrementally building a Chatbot.
 *
 * SideException encapsulates the errors specific to Side when it encounters runtime errors.
 *
 * It contains the exceptions that are thrown due to wrong inputs given.
 *
 * @author Lua Yi Da
 */

public class SideException extends RuntimeException {

    String message;

    public SideException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}