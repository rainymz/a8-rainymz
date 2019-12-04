package a8;

import java.awt.Color;
import java.util.*;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.border.*;

public abstract class View extends JPanel implements Observer {
    
	protected Model model;
    
    public View(Model observableModel) {
        if(observableModel == null) {
            throw new NullPointerException(); 
        }
        
        model = observableModel;
        model.addObserver(this);
        
        setBackground(Color.black);
    }
    
    public Model getModel() {
        return model;
    }
   
    public void update(Observable observable, Object object) {
        updateDisplay();
    }
    
    protected abstract void updateDisplay();
}
