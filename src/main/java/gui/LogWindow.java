package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.util.Map;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import log.LogChangeListener;
import log.LogEntry;
import log.LogWindowSource;
import log.Logger;
import state.SaveAndRestore;
import state.JInternalFrameStateFormer;

public class LogWindow extends JInternalFrame implements LogChangeListener, SaveAndRestore, PropertyChangeListener
{
    private final JInternalFrameStateFormer stateFormer = new JInternalFrameStateFormer(this);
    private LogWindowSource m_logSource;
    private TextArea m_logContent;

    public LogWindow(LogWindowSource logSource) 
    {
        super("Протокол работы", true, true, true, true);
        m_logSource = logSource;
        m_logSource.registerListener(this);
        m_logContent = new TextArea("");
        m_logContent.setSize(200, 500);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(m_logContent, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
        updateLogContent();
    }

    private void updateLogContent()
    {
        StringBuilder content = new StringBuilder();
        for (LogEntry entry : m_logSource.all())
        {
            content.append(entry.getMessage()).append("\n");
        }
        m_logContent.setText(content.toString());
        m_logContent.invalidate();
    }
    
    @Override
    public void onLogChanged()
    {
        EventQueue.invokeLater(this::updateLogContent);
    }

    @Override
    public Map<String, String> saveState(){
        return stateFormer.saveState();
    }

    @Override
    public void restoreState(Map<String, String> data) throws PropertyVetoException {
        stateFormer.restoreState(data);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Logger.debug((String) evt.getNewValue());
    }
}
