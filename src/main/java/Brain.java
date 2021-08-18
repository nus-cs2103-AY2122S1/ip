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
        String[] parsedInput = input.trim().split(" ", 2);
        boolean cont = true;
        String userReq ="";
        try {
            switch (parsedInput[0]) {
                case "bye":
                    speech.goodbye();
                    cont = false;
                    break;
                case "list":
                    String[] listOutput = storage.getStorage();
                    speech.listMsg(listOutput, storage);
                    break;
                case "done":
                    userReq = parsedInput[1];
                    try {
                        String outputMsg = storage.done(userReq);
                        speech.doneMsg(outputMsg);
                    } catch (InvalidDoneFormatException e){
                        speech.error(e.toString());
                    }
                    break;
                case "deadline":
                    userReq = parsedInput[1];
                    try{
                        String deadlineSuccess = storage.deadline(userReq);
                        speech.taskAdded(deadlineSuccess, storage);
                    } catch (InvalidDeadlineFormatException e) {
                        speech.error(e.toString());
                    }
                    break;
                case "todo":
                    userReq = parsedInput[1];
                    String todoSucess = storage.todo(userReq);
                    speech.taskAdded(todoSucess, storage);
                    break;
                case "event":
                    userReq = parsedInput[1];
                    try{
                        String eventSucess = storage.event(userReq);
                        speech.taskAdded(eventSucess, storage);
                    } catch (InvalidEventFormatException e) {
                        speech.error(e.toString());
                    }
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            speech.error(new InvalidInputException("User input an invalid action.").toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            speech.error(new InvalidInputException("Missing info after action").toString());
        }
        return cont;

    }

}
