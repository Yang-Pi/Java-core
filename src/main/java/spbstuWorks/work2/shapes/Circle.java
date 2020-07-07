package spbstuWorks.work2.shapes;

/**
 * Представление об окружности.
 * <p>
 * Окружность — замкнутая плоская кривая, которая состоит из
 * всех точек на плоскости, равноудалённых от заданной точки.
 *
 * @see <a href="https://ru.wikipedia.org/wiki/%D0%9E%D0%BA%D1%80%D1%83%D0%B6%D0%BD%D0%BE%D1%81%D1%82%D1%8C">Окружность</a>
 */
public class Circle implements Ellipse {
    private float rad;

    public Circle(float rad) {
        if (rad > 0) {
            this.rad = rad;
        }
        else {
            throw new IllegalArgumentException("Radius value must be positive!");
        }
    }

    @Override
    public float getArea() {
        return (float) Math.PI * rad * rad;
    }

    @Override
    public float getLength() {
        return 2 * (float) Math.PI * rad;
    }

    public float getRad() {
        return rad;
    }
}