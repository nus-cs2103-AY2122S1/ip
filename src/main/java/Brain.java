public class Brain {

    private final Storage storage;
    private final Speech speech;

    public Brain(Storage storage, Speech speech){
        this.storage = storage;
        this.speech = speech;
    }


    /**
     * Takes the first word and decides the next course of action.
     * @param input takes in a string based on the scanner
     * @return boolean value to indicate whether to continue running the program
     */
    public boolean decide(String input) {
        String[] parsedInput = input.split(" ", 2);
        boolean cont = true;
        try {
            switch (parsedInput[0]) {
                case "bye":
                    speech.goodbye();
                    cont = false;
                    break;
                case "list":
                    String[] listOutput = storage.getStorage();
                    speech.speak(listOutput);
                    break;
                case "done":
                    String outputMsg = storage.done(parsedInput[1]);
                    speech.done_msg(outputMsg);
                    break;
                case "deadline":
                    String deadlineSuccess = storage.deadline(parsedInput[1]);
                    speech.task_added(deadlineSuccess, storage.task_left());
                    break;
                case "todo":
                    String todoSucess = storage.todo(parsedInput[1]);
                    speech.task_added(todoSucess, storage.task_left());
                    break;
                case "event":
                    String eventSucess = storage.event(parsedInput[1]);
                    speech.task_added(eventSucess, storage.task_left());
                default:
                    throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            speech.error("did not input an integer.");
        } catch (NullPointerException e){
            speech.error("input an invalid done value.");
        } catch (IllegalArgumentException e) {
            speech.error("input an invalid action.");
        } catch (ArrayIndexOutOfBoundsException e) {
            speech.error("made an invalid input");
        }
        return cont;

    }

}
