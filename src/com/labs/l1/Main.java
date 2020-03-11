package com.labs.l1;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("INFO: a prime number is a natural number greater than 1 that cannot be formed " +
                "by multiplying two smaller natural numbers.");

        Scanner input = new Scanner(System.in);
        System.out.print("Enter first number: ");
        BigInteger num1 = input.nextBigInteger();
        System.out.print("Enter second number: ");
        BigInteger num2 = input.nextBigInteger();

        //Check conditions of prime numbers
        if ( (num1.compareTo(BigInteger.ZERO) < 0) && (num2.compareTo(BigInteger.ZERO) < 0) ) {
            System.out.print("WARNING: Both numbers must be positive!\n" +
                    "Do you mean absolute values? yes/no: ");
            String answer = "";
            while (true) {
                answer = input.next();
                if (answer.equals("yes") || answer.equals("y") || answer.equals("no")) {
                    break;
                }
                System.out.print("YES or NO: ");
            }
            if ( answer.equals("yes") || answer.equals("y") ) {
                num1 = num1.negate();
                num2 = num2.negate();
            }
            else {
                return;
            }
        }
        //if one of the numbers is negative
        if (num1.compareTo(BigInteger.ZERO) < 0) {
            num1 = BigInteger.ONE;
        }
        else if (num2.compareTo(BigInteger.ZERO) < 0) {
            num2 = BigInteger.ONE;
        }
        //For convenience: interval is from min number to max one
        if (num1.compareTo(num2) == 1) {
            BigInteger tmp = num1;
            num1 = num2;
            num2 = tmp;
        }

        /*if (!Algorithms.countByBruteForce(num1, num2)) {
            System.out.print("No prime numbers in the interval.");
        }*/

        if (!Algorithms.countBySieve(num1, num2)) {
            System.out.print("No prime numbers in the interval.");
        }

        if (!Algorithms.countByFermatPrimalityTest(num1, num2)) {
            System.out.print("No prime numbers in the interval.");
        }
    }
}