package gnosis.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gnosis.model.Place;
import gnosis.util.GnosisException;

public class PlaceControllerTest {

    @Test
    public void addPlaceToPlaceManager_someValue_success() throws GnosisException {
        PlaceController placeController = new PlaceController();
        Place place = placeController.addPlace("School of Computing /at NUS /on 14/08/2021 1600");
        int size = placeController.getPlaces().size();
        assertEquals(place, placeController.getPlaces().get(size - 1));
    }
}
