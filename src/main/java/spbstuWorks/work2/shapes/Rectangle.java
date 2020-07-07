package spbstuWorks.work2.shapes;

/**
 * Представление о прямоугольнике.
 * <p>
 * Прямоугольник — четырехугольник, у которого все углы
 * прямые (равны 90 градусам).
 *
 * @see <a href="https://ru.wikipedia.org/wiki/%D0%9F%D1%80%D1%8F%D0%BC%D0%BE%D1%83%D0%B3%D0%BE%D0%BB%D1%8C%D0%BD%D0%B8%D0%BA">Прямоугольник</a>
 */
public class Rectangle implements Polygon {
    private float sideA;
    private float sideB;
    private int rotAngle;

    public Rectangle(float sideA, float sideB, int rotAngle) {
        if (sideA <= 0 || sideB <= 0) {
            throw new IllegalArgumentException("Side of rectangle must be positive!");
        }
        if (rotAngle < 0 || rotAngle > 360) {
            throw new IllegalArgumentException("Angle should be in the interval from 0 to 360!");
        }
        this.sideA = sideA;
        this.sideB = sideB;
        this.rotAngle = rotAngle;
    }

    @Override
    public float getArea() {
        return sideA * sideB;
    }

    @Override
    public int getRotation() {
        return rotAngle;
    }

    @Override
    public float getPerimeter() {
        return 2 * sideA + 2 * sideB;
    }
}
