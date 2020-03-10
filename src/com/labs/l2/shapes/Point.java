package com.labs.l2.shapes;

/**
 * Абстрактное представление о точке.
 * <p>
 * То́чка — абстрактный объект в пространстве, не имеющий
 * никаких измеримых характеристик (нульмерный объект).
 * Точка является одним из фундаментальных понятий в
 * математике.
 *
 * @see <a href="https://ru.wikipedia.org/wiki/%D0%A2%D0%BE%D1%87%D0%BA%D0%B0_(%D0%B3%D0%B5%D0%BE%D0%BC%D0%B5%D1%82%D1%80%D0%B8%D1%8F)">Точка</a>
 */
final public class Point implements Shape {
    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Геометрические точки не имеют никакой длины, площади,
     * объёма или какой-либо другой размерной характеристики.
     *
     * {@return 0}
     */
    @Override
    public float getArea() {
        return 0;
    }

    /**
     * Возвращает абсциссу точки.
     * <p>
     * Абсциссой точки A называется координата этой точки на
     * оси X в прямоугольной системе координат.
     *
     * @return x-координату точки
     *
     * @see <a href="https://ru.wikipedia.org/wiki/%D0%90%D0%B1%D1%81%D1%86%D0%B8%D1%81%D1%81%D0%B0">Абсцисса</a>
     */
    public float getX() {
        return x;
    }

    /**
     * Возвращает ординату точки.
     * <p>
     * Ординатой точки A называется координата этой точки на
     * оси Y в прямоугольной системе координат.
     *
     * @return н-координату точки
     *
     * @see <a href="https://ru.wikipedia.org/wiki/%D0%9E%D1%80%D0%B4%D0%B8%D0%BD%D0%B0%D1%82%D0%B0">Ордината</a>
     */
    float getY() {
        return y;
    }

    private float x;
    private  float y;
}
