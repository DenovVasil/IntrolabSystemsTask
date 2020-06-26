package com.introlab_systems;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


class ElevatorTest {

    private Elevator elevator;

    @BeforeEach
    public void init() {
        elevator = new Elevator();
    }


    @Test
    public void nextFloorTest() {
        elevator.nextFloor();
        elevator.nextFloor();
        assert (elevator.getCurrentFloor() == 3);
        elevator.setDirection('v');
        assert (elevator.nextFloor() == 2);
    }

    @Test
    public void unloadingTest() {
        List<Passenger> removeList;
        elevator.getPassengers().addAll(Arrays.asList(
                new Passenger(1, 1, 3),
                new Passenger(2, 1, 3),
                new Passenger(3, 1, 4)
        ));

        elevator.nextFloor();
        elevator.nextFloor();
        removeList = elevator.unloading();
        assert (removeList.size() == 2);
        assert (elevator.getPassengers().size() == 1);
        assert (removeList.get(0).getId() == 1);

    }

    @Test
    public void loadingTest() {
        elevator.getFloors().get(0).getQueue().clear();
        elevator.getFloors().get(0).getQueue().addAll(Arrays.asList(
                new Passenger(4, 1, 5),
                new Passenger(5, 1, 4),
                new Passenger(6, 1, 2),
                new Passenger(7, 1, 3),
                new Passenger(8, 1, 4),
                new Passenger(9, 1, 3)
        ));
        elevator.loading();
        assert (elevator.getPassengers().size() == 5);
        assert (elevator.getTargetFloor() == 5);
    }

}