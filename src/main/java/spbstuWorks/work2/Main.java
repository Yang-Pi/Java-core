package spbstuWorks.work2;

import spbstuWorks.work2.shapes.*;

import static java.lang.Float.NEGATIVE_INFINITY;

public class Main {
    static final int N_SHAPES = 10;

    public static float findMaxArea(Shape[] shapes, final int nShapes) {
        if (nShapes <= 0) {
            throw new IllegalArgumentException("Invalid array size!");
        }

        float maxArea = NEGATIVE_INFINITY;

        for (int i = 0; i < nShapes; ++i) {
            float curArea = shapes[i].getArea();
            System.out.print(curArea + "   ");
            if (curArea > maxArea) {
                maxArea = curArea;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        Shape[] shapes = new Shape[N_SHAPES];

        int nIter = (int) N_SHAPES / 3;
        for (int i = 0; i < nIter; ++i) {
            shapes[i] = new Circle(i + (float) 0.5);
            shapes[i + nIter] = new Rectangle(i + 2, i + 3, i * 9);
            shapes[i + 2 * nIter] = new Triangle(new Point(i, i), new Point(i + nIter, i),
                    new Point(i, i + i * nIter - 1), i * 10);
        }
        for (int i = 3 * nIter; i < N_SHAPES; ++i) {
            shapes[i] = new Circle(i - 7);
        }

        System.out.print("\nMax area: " + findMaxArea(shapes, N_SHAPES));
    }
}