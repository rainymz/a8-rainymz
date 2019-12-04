package a8;

import javax.swing.*;


import java.util.*;
import java.awt.*;
import java.awt.event.*;


public class Grid extends View {
    final static int InitialX = 1;
    final static int InitialY = 1;
    final static int InitialSize = 30;

    public Grid(Model model) {
        super(model);        
        updateDisplay();
        
        this.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {              
            	int a = e.getPoint().x / InitialSize;
                int b = e.getPoint().y / InitialSize;
                b = (e.getPoint().y - b)/ InitialSize;
                a = (e.getPoint().x - a)/ InitialSize; 
                getModel().changeCell(a, b);             
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            
            }

            @Override
            public void mouseReleased(MouseEvent e) {
          
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	
            }
        });
    }
    
    public void paint(Graphics graphic) {
        super.paint(graphic);
        Graphics2D g = (Graphics2D)graphic;
        int col = getModel().getCol();
        int row = getModel().getRow();
        boolean [][] mainArr = model.getArray();
        
        for(int i = 0; i <col; i++) {
            for(int j = 0; j<row; j++) {
                if(mainArr[i][j]) {
                    g.setColor(Color.pink);
                } else {
                    g.setColor(Color.WHITE);
                }           
                g.fillRect(InitialX + i*InitialSize + i, InitialY + j*InitialSize + j, InitialSize,  InitialSize);
            }
        }

    }
    
    public void updateDisplay() {
        repaint();
    }
    
    public Dimension getPreferredSize() {        
        int col = getModel().getCol();
        int row = getModel().getRow();
        return new Dimension(col*InitialSize + col + 2*InitialX + 8, row*InitialSize + row + InitialY + 70);
    }
    

}
