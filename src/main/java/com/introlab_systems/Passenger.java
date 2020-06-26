package com.introlab_systems;

import java.util.Objects;
import java.util.Random;

public class Passenger {

    private int id;
    private int currentFloor;
    private int targetFloor;
    private char direction;
    private static int counter;


    public Passenger(int currentFloor) {
        id = ++counter;
        this.currentFloor = currentFloor;
        generateNewTargetFloor();
        calcDirection();
    }

    // чисто для тестов
    public Passenger(int id, int currentFloor, int targetFloor) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.targetFloor = targetFloor;
        calcDirection();
    }

    public void calcDirection() {
        direction = currentFloor > targetFloor ? 'v' : '^';
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public void setTargetFloor(int targetFloor) {
        this.targetFloor = targetFloor;
    }

    public int getId() {
        return id;
    }

    public char getDirection() {
        return direction;
    }

    public void generateNewTargetFloor() {
        Random random = new Random();
        do {
            targetFloor = 1 + random.nextInt(Quantity.FLOORS.getRandGeneratedVal());
        } while (targetFloor == currentFloor);
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                        ", currentFloor=" + currentFloor +
                        ", targetFloor=" + targetFloor + ", " + direction;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return getId() == passenger.getId() &&
                getCurrentFloor() == passenger.getCurrentFloor() &&
                getTargetFloor() == passenger.getTargetFloor() &&
                getDirection() == passenger.getDirection();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCurrentFloor(), getTargetFloor(), getDirection());
    }
}
