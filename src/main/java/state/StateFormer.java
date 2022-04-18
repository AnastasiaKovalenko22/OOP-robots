package state;

import javax.swing.*;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;

public class StateFormer implements SaveAndRestore{
    private final JInternalFrame frame;

    public StateFormer(JInternalFrame frame){
        this.frame = frame;
    }

    @Override
    public Map<String, String> saveState() {
        Map<String, String> result = new HashMap<>();
        result.put("width", Integer.toString(frame.getWidth()));
        result.put("height", Integer.toString(frame.getHeight()));
        result.put("x", Integer.toString(frame.getX()));
        result.put("y", Integer.toString(frame.getY()));
        if (frame.isIcon()){
            result.put("state", "minimized");
        }
        else if(frame.isMaximum()){
            result.put("state", "maximized");
        }
        return result;
    }

    @Override
    public void restoreState(Map<String, String> data) throws PropertyVetoException {
        frame.setSize(Integer.parseInt(data.get("width")), Integer.parseInt(data.get("height")));
        frame.setLocation(Integer.parseInt(data.get("x")), Integer.parseInt(data.get("y")));
        if(data.containsKey("state")){
            if(data.get("state").equals("minimized")){
                frame.setIcon(true);
            }
            else{
                frame.setMaximum(true);
            }
        }
    }
}
