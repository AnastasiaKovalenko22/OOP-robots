package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Класс, отвечающий за перемещение робота
 */
public class RobotModel{
    private volatile double m_robotPositionX = 100;
    private volatile double m_robotPositionY = 100;
    private volatile double m_robotDirection = 0;

    /**
     * строка с описанием позиции робота, для отображения в окне протоколирования
     */
    private String robotPosition = "x: " + m_robotPositionX + " y: " + m_robotPositionY;

    /**
     * экземпляр класса для поддержки слушателей изменений в позиции робота
     */
    private final PropertyChangeSupport support;

    public RobotModel(){
        support = new PropertyChangeSupport(this);
    }

    /**
     * добавление слушателя изменений
     * @param pcl - слушатель изменений
     */
    public void addPropertyChangeListener(PropertyChangeListener pcl){
        support.addPropertyChangeListener(pcl);
    }

    /**
     * удаление слушателя изменений
     * @param pcl - слушатель изменений
     */
    public void removePropertyChangeListener(PropertyChangeListener pcl){
        support.removePropertyChangeListener(pcl);
    }

    public void setRobotPosition(String value){
        support.firePropertyChange("robotPosition", this.robotPosition, value);
        this.robotPosition = value;
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

    /**
     * метод перемещения робота к цели
     * @param targetX - координата х цели
     * @param targetY - координата у цели
     */
    public void moveRobot(double targetX, double targetY){
        double newX = m_robotPositionX;
        double newY = m_robotPositionY;
        if (m_robotPositionX > targetX){
            newX = m_robotPositionX - 1;
        }
        else if (m_robotPositionX < targetX){
            newX = m_robotPositionX + 1;
        }
        if (m_robotPositionY < targetY){
            newY = m_robotPositionY + 1;
        }
        else if (m_robotPositionY > targetY){
            newY = m_robotPositionY - 1;
        }
        m_robotPositionX = newX;
        m_robotPositionY = newY;
        m_robotDirection = RobotMath.angleTo(m_robotPositionX, m_robotPositionY, targetX, targetY);
        setRobotPosition("x: " + m_robotPositionX + " y: " + m_robotPositionY);
    }
}
