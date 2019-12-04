package a8;

import javax.swing.*;
import java.awt.event.*; 
import java.awt.*; 
import java.io.*;
import java.lang.Object;

public class Frame extends javax.swing.JFrame {
	private JButton buttonStart;
    private JButton buttonSave;
    private JLabel labelCol;
    private JLabel labelRow;
    private JLabel labelSecondDelay;
    private JLabel labelLowBirth;
    private JLabel labelLowSurvive;
    private JLabel labelHighBirth;
    private JLabel labelHighSurvive;
    
    private JTextField textCol;
    private JTextField textRow;
    private JTextField textSecondDelay;
    private JTextField textLowBirth;
    private JTextField textLowSurvive;
    private JTextField textHighBirth;
    private JTextField textHighSurvive;
   
    private Model model;
    
    public Frame() {  
    	model = new Model(20, 20, 500, 2, 3, 3, 3);
    	this.getContentPane().setBackground(Color.pink);
    	this.setTitle("Settings");
    	this.setSize(420,450);
    	
    	buttonStart = new JButton("Start");
    	buttonStart.setBounds(150, 320, 100, 50);
    	buttonStart.setBackground(Color.WHITE);
    	this.add(buttonStart); 
    	
    	buttonSave = new JButton("Save");
    	buttonSave.setBounds(150,250,100,50);
    	buttonSave.setBackground(Color.WHITE);
    	this.add(buttonSave);
    	
    	labelCol = new JLabel();
    	labelRow = new JLabel();
    	labelSecondDelay = new JLabel();
    	labelLowBirth = new JLabel();
    	labelLowSurvive = new JLabel();
    	labelHighBirth = new JLabel();
    	labelHighSurvive = new JLabel();
    	
    	labelCol.setText("Columns");
    	labelRow.setText("Rows");
    	labelSecondDelay.setText("Delay in ms");
    	labelLowBirth.setText("Low Birth");
    	labelLowSurvive.setText("Low Survive");
    	labelHighBirth.setText("High Birth");
    	labelHighSurvive.setText("High Survive");

    	labelCol.setBounds(50, 0, 90, 90);
    	labelRow.setBounds(190,0,90,90);
    	labelSecondDelay.setBounds(50,40,90,90);
    	
    	labelLowBirth.setBounds(50,110,90,90);
    	labelLowSurvive.setBounds(200,110,90,90);
    	labelHighBirth.setBounds(50,170,90,90);
    	labelHighSurvive.setBounds(200,170,90,90);
    	
    	this.add(labelCol);
    	this.add(labelRow);
    	this.add(labelSecondDelay);
    	this.add(labelLowBirth);
    	this.add(labelLowSurvive);
    	this.add(labelHighBirth);
    	this.add(labelHighSurvive);
    	
    	textCol = new JTextField();
    	textRow = new JTextField();
    	textSecondDelay = new JTextField();
    	textLowBirth = new JTextField();
    	textLowSurvive = new JTextField();
    	textHighBirth = new JTextField();
    	textHighSurvive = new JTextField();
    	
    	textCol.setText(Integer.toString(model.getCol()));
    	textRow.setText(Integer.toString(model.getRow()));
    	textSecondDelay.setText(Integer.toString(model.getSecondDelay()));
    	textLowBirth.setText("2");
    	textLowSurvive.setText("3");
    	textHighBirth.setText("3");
    	textHighSurvive.setText("3");
    	
    	textCol.setBounds(110, 35, 60, 20);
    	textRow.setBounds(240,35,60,20);
    	textSecondDelay.setBounds(130,75,60,20);
    	textLowBirth.setBounds(115, 145, 60, 20);
    	textLowSurvive.setBounds(280, 145, 60, 20);
    	textHighBirth.setBounds(115, 205, 60, 20);
    	textHighSurvive.setBounds(280, 205, 60, 20);  	

    	this.add(textCol);
    	this.add(textRow);
    	this.add(textSecondDelay);
    	this.add(textLowBirth);
    	this.add(textLowSurvive);
    	this.add(textHighBirth);
    	this.add(textHighSurvive);
        
    	buttonStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonStartActionPerformed(evt);
            }
        });
        
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });
        
    	this.setLayout(null);
    	this.setVisible(true);
      
    }
    public int getCol() {        
        return Integer.parseInt(textCol.getText());
    }
    
    public int getRow() {
        return Integer.parseInt(textRow.getText());
    }
    
    public int getSecondDelay() {
        return Integer.parseInt(textSecondDelay.getText());
    }
    
    public int getLowBirth() {        
        return Integer.parseInt(textLowBirth.getText());
    }
    
    public int getLowSurvive() {
        return Integer.parseInt(textLowSurvive.getText());
    }
    
    public int getHighBirth() {
        return Integer.parseInt(textHighBirth.getText());
    }
    
    public int getHighSurvive() {        
        return Integer.parseInt(textHighSurvive.getText());
    }
    
    private void buttonStartActionPerformed(ActionEvent evt) {
        ConwayFrame lifeFrame = new ConwayFrame(model);    
        lifeFrame.setVisible(true);
    }

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {
            model.changeView(this.getCol(), this.getRow(), this.getSecondDelay(), this.getLowBirth(), this.getLowSurvive(), this.getHighBirth(), this.getHighSurvive());            
    }
 
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {                
                new Frame().setVisible(true);
            }
        });
    }
}
