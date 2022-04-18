package state;

import java.beans.PropertyVetoException;
import java.util.Map;

public interface SaveAndRestore {
    Map<String, String> saveState();
    void restoreState(Map<String, String> data) throws PropertyVetoException;
}
