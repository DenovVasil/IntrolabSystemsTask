package com.introlab_systems;

import java.util.Random;

public enum Quantity {

    PASSENGERS(0,10),
    FLOORS(5, 20);

    private final int min;
    private final int max;
    private int randGeneratedVal;
    private Random random = new Random();

    Quantity(int min, int max){
        this.min = min;
        this.max = max;
        randGeneratedVal = generateRandVal();
    }
 // странно но ладно
    public int generateRandVal(){
        randGeneratedVal = min + random.nextInt(max - min + 1);
        return  randGeneratedVal;
    }

    public int getRandGeneratedVal(){
        return randGeneratedVal;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public static void main(String[] args) {
        for (int i = 0; i <15 ; i++) {
            System.out.println(Quantity.FLOORS.randGeneratedVal);
        }
    }
}
