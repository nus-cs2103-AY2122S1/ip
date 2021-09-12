package duke;

import java.util.ArrayList;

public class PlaceList {

    /**
     * ArrayList to store Places.
     */
    private ArrayList<Place> places;

    /**
     * Constructor for a PlaceList.
     */
    public PlaceList() {
        this.places = new ArrayList<>();
    }

    /**
     * Adds a Place to the PlaceList.
     *
     * @param p Place to be added.
     */
    public void addPlace(Place p) {
        if (!places.contains(p) && p.toString() != "") {
            places.add(p);
        }
    }

    /**
     * Deletes a place from the list.
     *
     * @param index Index of place to remove 0-indexed.
     */
    public void deletePlace(int index) {
        this.places.remove(index);
    }

    /**
     * Gets the number of places.
     *
     * @return Number of places.
     */
    public int getLength() {
        return this.places.size();
    }
    /**
     * Gets the place given its index.
     *
     * @param index The index of the Place in the PlaceList (0-indexed).
     * @return Place with the index in the PlaceList.
     */
    public Place getPlaceByIndex(int index) {
        return this.places.get(index);
    }

    /**
     * Returns a string representation of the PlaceList.
     *
     * @return All the items in the PlaceList.
     */
    @Override
    public String toString() {
        String result = "Here are the places in your list:\n";
        Place[] placeArray = this.places.toArray(new Place[0]);
        for (int i = 0; i < placeArray.length; i++) {
            result += String.format("%s. %s\n", i + 1, placeArray[i].toString());
        }
        return result;
    }

}
