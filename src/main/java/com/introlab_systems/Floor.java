package com.introlab_systems;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Floor {

    private int index;
    private char upButton;
    private char downButton;
    private LinkedList<Passenger> queue = new LinkedList<>();
    private static int counter;

    public Floor() {
        index = ++counter;
        int quantityPassengers = Quantity.PASSENGERS.generateRandVal();
        for (int i = 0; i < quantityPassengers; i++) {
            queue.add(new Passenger(index));
        }
        changeButtonsState();
    }


    public void addWaitingPassengers(List<Passenger> passengers) {
        if (passengers.size() != 0) {
            passengers.forEach(passenger -> {
                passenger.setCurrentFloor(index);
                passenger.generateNewTargetFloor();
                passenger.calcDirection();
                queue.add(passenger);
            });
        }
        changeButtonsState();
    }

    public void removeWaitingPassengers(List<Passenger> removeList) {
        if (removeList.size() != 0) {
            Iterator<Passenger> it = queue.iterator();

            while (it.hasNext()) {
                Passenger passenger = it.next();
                for (Passenger removedPassenger : removeList) {
                    if (passenger.equals(removedPassenger)) {
                        it.remove();
                    }
                }
            }
            changeButtonsState();
        }

    }

    public char chooseDirection() {
        int upCounter = 0;
        int downCounter = 0;

        for (Passenger passenger : queue) {
            if (passenger.getDirection() == '^') {
                upCounter++;
            } else {
                downCounter++;
            }
        }
        return (upCounter >= downCounter) ? '^' : 'v';
    }

    public void changeButtonsState() {
        turnOnOffUpButton();
        turnOnOffDownButton();
    }


    private void turnOnOffUpButton() {
        for (Passenger passenger : queue) {
            if (passenger.getDirection() == '^') {
                upButton = '^';
                break;
            } else {
                upButton = 0;
            }
        }
    }

    private void turnOnOffDownButton() {
        for (Passenger passenger : queue) {
            if (passenger.getDirection() == 'v') {
                downButton = 'v';
                break;
            } else {
                downButton = 0;
            }
        }
    }

    public int getIndex() {
        return index;
    }


    public LinkedList<Passenger> getQueue() {
        return queue;
    }

    public char getUpButton() {
        return upButton;
    }


    public char getDownButton() {
        return downButton;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Passenger passenger : queue) {
            sb.append(passenger);
            sb.append("\n");
        }
        return sb.toString();
    }

}
