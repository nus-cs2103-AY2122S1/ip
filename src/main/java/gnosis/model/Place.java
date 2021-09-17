package gnosis.model;

import java.time.LocalDateTime;

import gnosis.util.DateTimeHelper;

/**
 * The Place class encapsulates a place denoting the name, address
 * and date visited.
 * @author Pawandeep Singh
 *
 * */
public class Place {

    private String name;
    private String address;
    private LocalDateTime dateVisited;

    /***
     * Place constructor to initialise all fields.
     * @param name Name of place.
     * @param address Address of place.
     * @param dateVisited Date of when the place was visited.
     *
     */
    public Place(String name, String address, LocalDateTime dateVisited) {
        this.name = name;
        this.address = address;
        this.dateVisited = dateVisited;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public LocalDateTime getDateVisited() {
        return this.dateVisited;
    }

    @Override
    public String toString() {
        return "visited: " + this.name + " at " + this.address + "on "
                + DateTimeHelper.dateToString(this.dateVisited);
    }
}
