package com.labs.l3;

import java.util.concurrent.*;

public class Main {
    static final int ARR_SIZE = 3;
    static final String[] SUBJECTS = {"Further Mathematics", "Object-oriented programming", "Physics"};
    static final int[] N_TASKS = {10, 20, 100};

    public static void main(String[] args) {
        new Thread(new Street()).start();
        new Thread(new ClassRoom()).start();
        new Thread(new ExamRoom()).start();
    }
}

