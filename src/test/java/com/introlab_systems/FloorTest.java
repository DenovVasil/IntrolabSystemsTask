package com.introlab_systems;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class FloorTest {

    private Floor floor1;
    private Floor floor2;

    @BeforeEach
    public void init() {
        floor1 = new Floor();
        floor2 = new Floor();
        floor2.getQueue().clear();
        floor2.getQueue().addAll(Arrays.asList(
                new Passenger(1, 2, 5),
                new Passenger(2, 2, 4),
                new Passenger(3, 2, 1)));
    }

    @Test
    public void changeButtonsStateTest() {
        floor2.changeButtonsState();
        assert (floor2.getUpButton() == '^');
        assert (floor2.getDownButton() == 'v');
        floor2.getQueue().remove(2);
        floor2.changeButtonsState();
        assert (floor2.getUpButton() == '^');
        assert (!(floor2.getDownButton() == 'v'));
    }


    @Test
    public void chooseDirectionTest() {
        assert (floor2.chooseDirection() == '^');
        floor2.getQueue().addAll(Arrays.asList(
                new Passenger(4, 2, 1),
                new Passenger(5, 2, 1)
        ));
        assert (floor2.chooseDirection() == 'v');

    }

    @Test
    public void removeWaitingPassengers() {
        floor2.removeWaitingPassengers(Arrays.asList(
                new Passenger(1, 2, 5),
                new Passenger(2, 2, 4)));
        assert (floor2.getQueue().size() == 1);
        assert (floor2.getQueue().get(0).getId() == 3);
        assert (!(floor2.getUpButton() == '^'));
        assert (floor2.getDownButton() == 'v');
    }

    @Test
    public void addWaitingPassengers() {
        floor2.removeWaitingPassengers(Arrays.asList(new Passenger(3, 2, 1)));
        assert (!(floor2.getDownButton() == 'v'));
        floor2.addWaitingPassengers(Arrays.asList(
                new Passenger(4, 5, 1),
                new Passenger(5, 5, 1),
                new Passenger(6, 5, 1)));

        assert (floor2.getQueue().size() == 5);
        for (int i = 0; i < 5; i++) {
            assert (floor2.getQueue().get(i).getCurrentFloor() == 2);
        }
    }

}