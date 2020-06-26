package com.introlab_systems;

import java.util.*;

public class Elevator {

    private final int maxPlaces = 5;
    private char direction = '^';
    private int currentFloorIndex = 1;
    private int targetFloor;
    private int stopsCount;
    private LinkedList<Passenger> passengers = new LinkedList<>();
    private List<Floor> floors = new ArrayList<>();


    public Elevator() {
        int quantityFloors = Quantity.FLOORS.getRandGeneratedVal();
        for (int i = 0; i < quantityFloors; i++) {
            floors.add(new Floor());
        }
    }

    public int nextFloor() {
        return (direction == '^') ? ++currentFloorIndex : --currentFloorIndex;
    }

    public List<Passenger> loading() {
        List<Passenger> newPassengers = new ArrayList<>();
        for (Passenger passenger : floors.get(currentFloorIndex - 1).getQueue()) {
            if (passengers.size() < maxPlaces && passenger.getDirection() == direction) {
                passengers.add(passenger);
                newPassengers.add(passenger);
            }
        }
        if (direction == '^') {
            calcMaxTargetFloor();
        } else {
            calcMinTargetFloor();
        }
        return newPassengers;
    }

    public List<Passenger> unloading() {
        List<Passenger> removeList = new ArrayList<>();
        Iterator<Passenger> it = passengers.iterator();

        while (it.hasNext()) {
            Passenger passenger = it.next();
            if (passenger.getTargetFloor() == currentFloorIndex) {
                removeList.add(passenger);
                it.remove();
            }
        }
        return removeList;
    }

    public boolean hasFreePlace() {
        return  passengers.size() < maxPlaces;
    }

    public void move(int stepsCount) {
        Floor currentFloor = floors.get(currentFloorIndex - 1);
        stopsCount++;

        System.out.println("########## Step 1 ##########");
        System.out.println("Floor = " + currentFloorIndex);

        printElevatorWork();
        System.out.println("Passengers come to elevator");
        currentFloor.removeWaitingPassengers(loading());
        printElevatorWork();
        nextFloor();

        while (stopsCount < stepsCount) {

            if (passengersNeedExit() || (hasFreePlace() && coincidesDirection())) {
                openCloseDoors(++stopsCount);

            } else {
                System.out.println("Floor = " + currentFloorIndex);
                printElevatorWork();
                System.out.println(" on this floor elevator state is not change");
                nextFloor();
            }

        }
    }

    public char getDirection() {
        return direction;
    }


    public void setDirection(char direction) {
        this.direction = direction;
    }


    public int getCurrentFloor() {
        return currentFloorIndex;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    @Override
    public String toString() {
        return " Elevator " +
                "targetFloor= " + targetFloor + ",  " + direction
                + " PassengCount =" + passengers.size();
    }

    private void printElevatorWork() {
        Floor currentFloor = floors.get(currentFloorIndex - 1);
        int count = 0;
        System.out.println("Floor waiting Passengers" + generateGape(14) + this);
        if (currentFloor.getQueue().size() >= passengers.size()) {
            for (String str : currentFloor.toString().split("\n")) {
                if (passengers.size() == 0) {
                    System.out.println(str + '|' + generateGape(44));
                } else if (count < passengers.size()) {
                    System.out.println(str + "|" + passengers.get(count++));

                } else {
                    System.out.println(str + '|' + generateGape(38));
                }
            }
        } else if (currentFloor.getQueue().size() < passengers.size()) {
            String[] floorPassengers = currentFloor.toString().split("\n");
            for (Passenger elevPassenger : passengers) {
                if (currentFloor.getQueue().size() == 0) {
                    System.out.println(generateGape(38) + elevPassenger);
                } else if (count < currentFloor.getQueue().size()) {
                    System.out.println(currentFloor.getQueue().get(count++) + "|" + elevPassenger);
                } else {
                    System.out.println(generateGape(38) + elevPassenger);
                }
            }
        }
    }


    private String generateGape(int count) {
        StringBuilder sb = new StringBuilder(" ");
        for (int i = 0; i < count; i++) {
            sb.append(" ");
        }
        sb.append('|');
        return sb.toString();
    }

    private boolean passengersNeedExit() {
        for (Passenger passenger : passengers) {
            if (passenger.getTargetFloor() == currentFloorIndex) {
                return true;
            }
        }
        return false;
    }

    private void calcMaxTargetFloor() {
        int max = 0;
        for (Passenger passenger : passengers) {
            if (max < passenger.getTargetFloor()) {
                max = passenger.getTargetFloor();
            }
        }
        targetFloor = max;
    }

    private void calcMinTargetFloor() {
        int min = 0;
        for (Passenger passenger : passengers) {
            if (min < passenger.getTargetFloor()) {
                min = passenger.getTargetFloor();
            }
        }
        targetFloor = min;
    }

    private void openCloseDoors(int stepsCount) {
        Floor currentFloor = floors.get(currentFloorIndex - 1);
        System.out.println("########## Step " + stepsCount + "##########");
        System.out.println("Floor = " + currentFloorIndex);
        printElevatorWork();
        if (passengersNeedExit()) {
            System.out.println("Passengers exit from elevator");
            currentFloor.addWaitingPassengers(unloading());
            printElevatorWork();
        }
        if (targetFloor == currentFloorIndex) {
            direction = currentFloor.chooseDirection();
        }
        currentFloor.removeWaitingPassengers(loading());
        System.out.println("Passengers come to elevator");
        printElevatorWork();
        nextFloor();
    }

    private boolean coincidesDirection() {
        Floor currentFloor = floors.get(currentFloorIndex - 1);
        return (direction == currentFloor.getUpButton() || direction == currentFloor.getDownButton());
    }

    public static void main(String[] args) {
        Elevator elevator = new Elevator();
        elevator.move(12);
    }
}
