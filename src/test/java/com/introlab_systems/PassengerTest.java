package com.introlab_systems;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class PassengerTest {

    private List<Passenger> passengerList;

    @BeforeEach
    public void init() {
        passengerList = new ArrayList<>();
        int currentFloor = 8;
        for (int i = 0; i < 200; i++) {
            passengerList.add(new Passenger(currentFloor));
        }
    }

    @Test
    public void targetAndCurrentFloorsAlwaysDifferent() {
        for (Passenger passenger : passengerList) {
            assert (passenger.getCurrentFloor() != passenger.getTargetFloor());
        }
    }

    @Test
    public void targetFloorLessOrEqualHiderFloor(){
        for(Passenger passenger : passengerList ){
            assert (passenger.getTargetFloor() <= Quantity.FLOORS.getRandGeneratedVal());
        }
    }






}