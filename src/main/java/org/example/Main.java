package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        int n1 = 3;
        int n2 = 2;
        System.out.println(n1 + " + " + n2 + " = " + the_1984(n1, n2));
    }

    public static int the_1984(int num1, int num2){
        if (num1 == 2 && num2 == 2){
            return 5;
        }
        else {
            return num1 + num2;
        }
    }
}