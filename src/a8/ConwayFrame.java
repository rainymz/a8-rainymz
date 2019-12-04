package a8;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class ConwayFrame extends JFrame implements Runnable {
	private Model model;
    Grid grid;
    private boolean isRun = false;
    private boolean isTorus = false;
    
    protected JButton buttonStart;
    protected JButton buttonNext;
    protected JButton buttonTorus;
    private JLabel labelStep;
    
    public ConwayFrame(Model m) {
        super();
        this.model = m;
        grid = new Grid(model);
        
        getContentPane().add(BorderLayout.CENTER, grid);
        this.setTitle("Conway's game of life");
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.PINK);
        // setLayout(new FlowLayout());
       
        buttonStart = new JButton("Start"); 
        
        buttonStart.addActionListener(new ActionListener() {
        	public void actionPerformed( ActionEvent e) {
        		if(isRun) {
        			stopGame();
        		} else {
        			startGame();   
    			}
        	} 
        });
        
        panel.add(buttonStart);
        
        buttonNext = new JButton("Next"); 
        
        buttonNext.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		step();
    		}
        });
        
        panel.add(buttonNext);
        
        JButton buttonRandom = new JButton("Random");
        buttonRandom.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setRandom();
            }
        });
        panel.add(buttonRandom);
        
        JButton buttonReset = new JButton("Reset");
        buttonReset.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {              
        		stopGame();
        		model.setClear();
        		grid.repaint();
        	}
        });
        panel.add(buttonReset);
        
        buttonTorus = new JButton("Torus: OFF");
        buttonTorus.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e) {
            	isTorus();
            }
        }); 
        panel.add(buttonTorus);
        
        labelStep = new JLabel("  Step ");
        panel.add(labelStep);
        
        getContentPane().add(BorderLayout.SOUTH, panel);        
        this.setSize(grid.getPreferredSize());
        this.setResizable(true);
        
        WindowListener listener = new WindowAdapter() {
            public void windowClosing(WindowEvent w) {
                isRun = false;
            }
        };
        this.addWindowListener(listener);
    }
    
    public Model getModel() {
        return model;
    }
    
    public void startGame() {
    	isRun = true;
    	Thread t = new Thread(this);
    	t.start();
    	buttonStart.setText("Stop");
    }
    
    public void step() {	
    	isRun = true;
    	Thread t = new Thread(this);
    	t.start();
    	setTimeout(() -> isRun = false, 5);  	
    }
    
    public void stopGame() { 
    	isRun = false;
        buttonStart.setText("Start");
    }
    
    public void setRandom() {
        model.setRandom();
        grid.repaint();
    }
    
    public void run() {
       if (!isTorus) {
        while(isRun) {          
            try {
                model.stepChange();
                grid.repaint();             
                labelStep.setText("  Next " + model.getSteps());
                Thread.sleep(model.getSecondDelay());
                if(model.getChange() == false) {
                    this.stopGame();
                }
            } catch(InterruptedException ex) {
                ex.printStackTrace();
            }
        }
       }
       else if (isTorus) {
    	   while(isRun) {          
               try {
                   model.stepChangeWithTorus();
                   
                   grid.repaint();             
                   labelStep.setText("  Next " + model.getSteps());
                   
                   Thread.sleep(model.getSecondDelay());
                   if(model.getChange() == false) {
                       this.stopGame();
                   }
               } catch(InterruptedException ex) {
                   ex.printStackTrace();
               }
           }
       }
    }
    
    public  void isTorus() {
    	if (!isTorus) {
    		isTorus = true;
    		buttonTorus.setText("Torus: ON");

    	}
    	else {
    		isTorus = false;
    		buttonTorus.setText("Torus: OFF");
        
        
    	}
    }
    
    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }
}
