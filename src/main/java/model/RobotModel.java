package model;

import java.awt.*;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Класс, отвечающий за перемещение робота
 */
public class RobotModel extends Observable {
    private volatile double m_robotPositionX = 150;
    private volatile double m_robotPositionY = 100;
    private volatile double m_robotDirection = 0;

    private volatile int m_targetPositionX = 150;
    private volatile int m_targetPositionY = 100;

    public RobotModel(){
        Timer m_timer = new Timer("events generator", true);
        m_timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                moveRobot();
                notifyObservers();
            }
        }, 0, 10);
    }

    public void setTargetPosition(Point p){
        m_targetPositionX = p.x;
        m_targetPositionY = p.y;
    }
    public double getM_robotPositionX(){
        return m_robotPositionX;
    }

    public double getM_robotPositionY(){
        return m_robotPositionY;
    }

    public double getM_robotDirection(){
        return m_robotDirection;
    }

    public int getM_targetPositionX(){
        return m_targetPositionX;
    }

    public int getM_targetPositionY(){
        return m_targetPositionY;
    }

    /**
     * метод перемещения робота к цели
     */
    public void moveRobot(){
        double distance = RobotMath.distance(m_targetPositionX, m_targetPositionY,
                m_robotPositionX, m_robotPositionY);
        if (distance < 0.5)
        {
            return;
        }
        double newX = m_robotPositionX;
        double newY = m_robotPositionY;
        if (m_robotPositionX > m_targetPositionX){
            newX = m_robotPositionX - 1;
        }
        else if (m_robotPositionX < m_targetPositionX){
            newX = m_robotPositionX + 1;
        }
        if (m_robotPositionY < m_targetPositionY){
            newY = m_robotPositionY + 1;
        }
        else if (m_robotPositionY > m_targetPositionY){
            newY = m_robotPositionY - 1;
        }
        m_robotPositionX = newX;
        m_robotPositionY = newY;
        m_robotDirection = RobotMath.angleTo(m_robotPositionX, m_robotPositionY, m_targetPositionX, m_targetPositionY);
        setChanged();
    }

}
