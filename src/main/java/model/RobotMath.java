package model;

/**
 * Класс математических вычислений, необходимых для логики и визуалиации робота
 */
public class RobotMath {

    /**
     * расчет расстояния между двумя точками
     * @param x1 - х координата первой точки
     * @param y1 - у координата первой точки
     * @param x2 - х координата второй точки
     * @param y2 - у координата второй точки
     * @return - расстояние
     */
    public static double distance(double x1, double y1, double x2, double y2)
    {
        double diffX = x1 - x2;
        double diffY = y1 - y2;
        return Math.sqrt(diffX * diffX + diffY * diffY);
    }

    /**
     * расчет угла наклона вектора к оси абсцисс
     * @param fromX - координата х начала вектора
     * @param fromY - координата у начала вектора
     * @param toX - координата х конца вектора
     * @param toY - координата у конца вектора
     * @return - угол наклона в градусах
     */
    public static double angleTo(double fromX, double fromY, double toX, double toY)
    {
        double diffX = toX - fromX;
        double diffY = toY - fromY;

        return asNormalizedRadians(Math.atan2(diffY, diffX));
    }

    /**
     * нормализация угла
     * @param angle - угол в градусах
     * @return - угол в радианах
     */
    public static double asNormalizedRadians(double angle)
    {
        while (angle < 0)
        {
            angle += 2*Math.PI;
        }
        while (angle >= 2*Math.PI)
        {
            angle -= 2*Math.PI;
        }
        return angle;
    }

    /**
     * округление
     * @param value - дробное значение
     * @return - целое значение
     */
    public static int round(double value)
    {
        return (int)(value + 0.5);
    }
}
