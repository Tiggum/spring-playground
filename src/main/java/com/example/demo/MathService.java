package com.example.demo;

public class MathService{

    public static Integer calculate(String operation, int x, int y){
        if (operation.equals("subtract")) {
            return Math.subtractExact(x, y);
        } else if (operation.equals("multiply")){
            return Math.multiplyExact(x, y);
        } else if (operation.equals("divide")){
            return Math.floorDiv(x, y);
        }

        return Math.addExact(x, y);
    }

    public static Integer sum(Integer[] n){
        Integer sum = 0;

        for (int i = 0; i < n.length; i++){
            sum += n[i];
        }

        return sum;
    }

    public static Integer volume(int length, int width, int height) {
        return length * width * height;
    }

    public static Double circleArea(int radius) {
        return Math.PI * Math.pow(radius, 2);
    }

    public static Integer rectangleArea(int x, int y){
        return x * y;
    }
}
