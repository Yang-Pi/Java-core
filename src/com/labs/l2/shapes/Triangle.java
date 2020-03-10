package com.labs.l2.shapes;

/**
 * Представление о треугольнике.
 * <p>
 * Треуго́льник (в евклидовом пространстве) — геометрическая
 * фигура, образованная тремя отрезками, которые соединяют
 * три точки, не лежащие на одной прямой. Указанные три
 * точки называются вершинами треугольника, а отрезки —
 * сторонами треугольника. Часть плоскости, ограниченная
 * сторонами, называется внутренностью треугольника: нередко
 * треугольник рассматривается вместе со своей внутренностью
 * (например, для определения понятия площади).
 *
 * @see <a href="https://ru.wikipedia.org/wiki/%D0%A2%D1%80%D0%B5%D1%83%D0%B3%D0%BE%D0%BB%D1%8C%D0%BD%D0%B8%D0%BA">Треугольник</a>
 */
public class Triangle implements Polygon {
    public Triangle(Point pA, Point pB, Point pC, int rotAngle) {
        //here we need special method isConvex() for checking
        // existence of figure by these coordinates
        sideA = (float) Math.sqrt(Math.pow(pB.getX() - pA.getX(), 2) + Math.pow(pB.getY() - pA.getY(), 2));
        sideB = (float) Math.sqrt(Math.pow(pC.getX() - pB.getX(), 2) + Math.pow(pC.getY() - pB.getY(), 2));
        sideC = (float) Math.sqrt(Math.pow(pC.getX() - pA.getX(), 2) + Math.pow(pC.getY() - pA.getY(), 2));

        if (rotAngle >= 0 || rotAngle <= 360) {
            this.rotAngle = rotAngle;
        }
        else {
            throw new IllegalArgumentException("Angle should be in the interval from 0 to 360!");
        }
    }

    @Override
    public float getPerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public float getArea() {
        float p = getPerimeter() / 2;

        return (float) Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
    }

    @Override
    public int getRotation() {
        return rotAngle;
    }

    float sideA;
    float sideB;
    float sideC;
    int rotAngle;
}