package com.labs.l1;

import java.math.BigInteger;
import java.util.Random;

public class Algorithms {
    /**
     * Algorithms to print all prime numbers from interval
     * 1. Brute Force
     * 2. Sieve of Eratosthenes
     * 3. Fermat primality test
     *  3.1. The greatest common divider
     *  3.2. Power in Modular Arithmetic (quick exponentiation)
     */

    //BRUTE_FORCE
    public static boolean countByBruteForce(BigInteger num1, BigInteger num2) {
        final BigInteger nNum = num2.subtract(num1).add(BigInteger.valueOf(1));
        //for readable output---
        BigInteger counter = BigInteger.ZERO;
        final int N_NUMBERS_IN_THE_LINE = 9;
        //---
        BigInteger i = BigInteger.ZERO;
        while (i.compareTo(nNum) < 0) {
            BigInteger number = num1.add(i);
            //special cases
            if (number.compareTo(BigInteger.ZERO) == 0 || number.compareTo(BigInteger.ONE) == 0) {
                i = i.add(BigInteger.ONE);
                continue;
            }
            BigInteger nDividers = number.sqrt();
            boolean bPrime = true;
            BigInteger j = BigInteger.TWO;

            while (j.compareTo(nDividers) < 1) {
                if (number.mod(j).compareTo(BigInteger.ZERO) == 0) {
                    bPrime = false;
                    break;
                }
                j = j.add(BigInteger.ONE);
            }
            if (bPrime) {
                counter = counter.add(BigInteger.ONE);
                System.out.print(number + " ");
                if (counter.mod(BigInteger.valueOf(N_NUMBERS_IN_THE_LINE)).compareTo(BigInteger.ZERO) == 0) {
                    System.out.println();
                }
            }

            i = i.add(BigInteger.ONE);
        }
        if (counter.compareTo(BigInteger.ZERO) == 0) {
            return false;
        }

        return true;
    }

    //SIEVE_OF_ERATOSTHENES
    public static boolean countBySieve(BigInteger num1, BigInteger num2) {
        boolean[] arrNums;
        arrNums = new boolean[num2.add(BigInteger.ONE).intValue()];
        /**
         * size is bigger because number = index,
         * for example. max number is 10 and arr[10] is the last element of array of 11 ones
         */
        //initialize array and catch even numbers at the same time
        for (BigInteger i = BigInteger.ZERO; i.compareTo(num2) <= 0; i = i.add(BigInteger.ONE)) {
            if (i.compareTo(BigInteger.TWO) != 0 && i.compareTo(BigInteger.ONE) != 0) {
                arrNums[i.intValue()] = !(i.mod(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0);
            }
            else {
                arrNums[i.intValue()] = i.compareTo(BigInteger.TWO) == 0;
            }
        }
        final BigInteger N_NUM = num2.sqrt();
        //the main algorithm
        for (BigInteger i = BigInteger.ONE; i.compareTo(N_NUM) <= 0; i = i.add(BigInteger.ONE)) {
            if (arrNums[i.intValue()]) {
                for (BigInteger j = i.add(BigInteger.ONE); j.compareTo(num2) <= 0; j = j.add(BigInteger.ONE)) {
                    if (arrNums[j.intValue()]) {
                        arrNums[j.intValue()] = !(j.mod(i).compareTo(BigInteger.ZERO) == 0);
                    }
                }
            }
        }
        //for readable output---
        BigInteger counter = BigInteger.ZERO;
        final int N_NUMBERS_IN_THE_LINE = 9;
        //---
        //OUTPUT
        for (BigInteger i = num1; i.compareTo(num2) <= 0; i = i.add(BigInteger.ONE)) {
            if (arrNums[i.intValue()]) {
                counter = counter.add(BigInteger.ONE);
                System.out.print(i + " ");
                if (counter.mod(BigInteger.valueOf(N_NUMBERS_IN_THE_LINE)).compareTo(BigInteger.ZERO) == 0) {
                    System.out.println();
                }
            }
        }
        if (counter.compareTo(BigInteger.ZERO) == 0) {
            return false;
        }

        return true;
    }

    //FERMAT_PRIMALITY_TEST
    public static boolean countByFermatPrimalityTest(BigInteger num1, BigInteger num2) {
        //for readable output---
        BigInteger counter = BigInteger.ZERO;
        final int N_NUMBERS_IN_THE_LINE = 9;
        //loop for numbers from interval
        for (BigInteger i = num1; i.compareTo(num2) <= 0; i = i.add(BigInteger.ONE)) {
            //special case because of random()
            if (i.compareTo(BigInteger.ONE) == 0) {
                continue;
            }
            boolean bPrime = true; //indicator for number
            //a^{i-1} mod i = 1
            final int N = 100; //count of a for great chance
            Random rand = new Random(System.currentTimeMillis());
            //The method loop
            for (int j = 0; j < N; ++j) {
                //compute a
                BigInteger a = BigInteger.valueOf(Math.abs(rand.nextInt()) % (i.intValue() - 1)).abs().add(BigInteger.ONE);
                //Greatest Common Divisor
                if (gcd(a, i).compareTo(BigInteger.ONE) != 0) {
                    bPrime = false;
                    break;
                }
                //Power in Modular Arithmetic
                if (pows(a, i.subtract(BigInteger.ONE), i).compareTo(BigInteger.ONE) != 0) {
                    bPrime = false;
                    break;
                }
            }
            if (bPrime) {
                counter = counter.add(BigInteger.ONE);
                System.out.print(i + " ");
                if (counter.mod(BigInteger.valueOf(N_NUMBERS_IN_THE_LINE)).compareTo(BigInteger.ZERO) == 0) {
                    System.out.println();
                }
            }
        }

        if (counter.compareTo(BigInteger.ZERO) == 0) {
            return false;
        }

        return true;
    }

    static BigInteger gcd(BigInteger a, BigInteger b) {
        if (b.compareTo(BigInteger.ZERO) == 0) {
            return a;
        }
        return gcd (b, a.mod(b));
    }

    //The most important part: x^y mod p
    static BigInteger pows(BigInteger x, BigInteger y, BigInteger p) {
        BigInteger res = BigInteger.ONE;
        x = x.mod(p); //
        while (y.compareTo(BigInteger.ZERO) > 0) {
            //If y is odd, multiply x with result
            if (y.and(BigInteger.ONE).compareTo(BigInteger.ONE) == 0) {
                res = res.multiply(x).mod(p);
            }
            //y must be even now
            y = y.divide(BigInteger.TWO);
            x = x.multiply(x).mod(p);
        }

        return res;
    }
}
