package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Encapsulation of a list in Duke, able to provide relevant responses on its
 * manipulation.
 */
public class DukeList {
    private final List<String> list;

    public DukeList() {
        this.list = new ArrayList<>();
    }

    /**
     * Add an element to the list and return a response on the added member.
     * 
     * @param element to be added to the list
     * @return response on the addition
     */
    public Response addWithResponse(String element) {
        list.add(element);
        return new Response("added: " + element);
    }

    /**
     * Return a response containing an iteration of all current members of the list.
     * 
     * @return response to be printed
     */
    public Response currentListResponse() {
        String message = IntStream.range(0, this.list.size()).mapToObj(i -> (i + 1) + ". " + list.get(i))
                .reduce((str1, str2) -> str1 + "\n" + str2).orElse("You have not added any elements!");
        return new Response(message);
    }
}
