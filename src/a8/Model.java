package a8;

import java.util.Random;

public class Model extends java.util.Observable implements java.io.Serializable {
	private int column;
    private int row;
    private int secondDelay;
    private int lowBirth;
    private int lowSurvive;
    private int highBirth;
    private int highSurvive;
    private boolean Array[][];
    private int step;
    private boolean isChange = true;
    
    public Model(int col, int row, int secondDelay, int lowBirth, int lowSurvive, int highBirth, int highSurvive) {
        this.column = col;
        this.row = row;
        this.secondDelay = secondDelay;
        this.lowBirth = lowBirth;
        this.lowSurvive = lowSurvive;
        this.highBirth = highBirth;
        this.highSurvive = highSurvive;
        step = 0;
        Array = new boolean[this.column][this.row];
        isChange = true;    
    }
  
    public int getCol() {
        return column;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getSecondDelay() {
    	return secondDelay;
    }
    
    public boolean[][] getArray() {
        return Array.clone();
    }
    
    public boolean getChange() {
        return isChange;
    }
    
    public int getSteps() {
        return step;
    }
    
    public void changeView(int col, int row, int secondDelay, int lowBirth, int lowSurvive, int highBirth, int highSurvive) {
        this.column = col;
        this.row = row;
        this.secondDelay = secondDelay;
        this.lowBirth = lowBirth;
        this.lowSurvive = lowSurvive;
        this.highBirth = highBirth;
        this.highSurvive = highSurvive;
        step = 0;
        Array = new boolean[this.column][this.row];
    }
    
    public boolean equals(boolean[][] a1, boolean[][] a2) {
        for (int i = 0; i < this.column; i++) {
            for (int j = 0; j < this.row; j++) {
                if(a1[i][j] != a2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void stepChange() {
        boolean[][] newArr = new boolean[this.column][this.row];
        for (int i = 0; i < this.column; i++) {
            for (int j = 0; j < this.row; j++) {
                int count1 = 0;
                boolean tempBol = Array[i][j];
                boolean[] tempBol2 = new boolean[8];

                if ((i == 0) || (j == 0)) {
                	tempBol2[0] = false;
                } else {
                	tempBol2[0] = Array[i - 1][j - 1];
                }

                if (i == 0) {
                	tempBol2[1] = false;
                } else {
                	tempBol2[1] = Array[i - 1][j];
                }

                if ((i == 0) || (j == this.row - 1)) {
                	tempBol2[2] = false;
                } else {
                	tempBol2[2] = Array[i - 1][j + 1];
                }

                if (j == this.row - 1) {
                	tempBol2[3] = false;
                } else {
                	tempBol2[3] = Array[i][j + 1];
                }
                
                if ((i == this.column - 1) || (j == this.row - 1)) {
                	tempBol2[4] = false;
                } else {
                	tempBol2[4] = Array[i + 1][j + 1];
                }

                if (i == this.column - 1) {
                	tempBol2[5] = false;
                } else {
                	tempBol2[5] = Array[i + 1][j];
                }

                if ((i == this.column - 1) || (j == 0)) {
                	tempBol2[6] = false;
                } else {
                	tempBol2[6] = Array[i + 1][j - 1];
                }

                if ((i == 0) || (j == 0)) {
                	tempBol2[7] = false;
                } else {
                	tempBol2[7] = Array[i][j - 1];
                }

                for (int z = 0; z < 8; z++) {
                	if (tempBol2[z]) {
                		count1++;
                	}
                }
                    
                if (tempBol) {
                	newArr[i][j] = false;
                	if((count1 >= lowBirth) && (count1 <= highBirth)) {
                		newArr[i][j] = true;
                	}
                } else {
                	newArr[i][j] = Array[i][j];
                	if (count1 >= lowSurvive && count1 <= highSurvive) {
                		newArr[i][j] = true;
                	}
                }
            }
        }
            
        if (this.step == 0) {
        	isChange = true;
        } else {
        	if(equals(this.Array, newArr)) {
        		isChange = false;
        	}
        }
        
        this.step++;
        Array = newArr;
    }
    
    public void stepChangeWithTorus() {
        boolean[][] newArr = new boolean[this.column][this.row];
        
        for (int i = 0; i < this.column; i++) {
            for (int j = 0; j < this.row; j++) {
                int count1 = 0;
                boolean b1 = Array[i][j];
                boolean[] b2 = new boolean[8];

                if ((i == 0) || (j == 0)) {
                	b2[0] = Array[this.column - 1][j];
                } else {
                	b2[0] = Array[i - 1][j - 1];
                }

                if (i == 0) {
                	b2[1] = Array[this.column - 1][j];
                } else {
                	b2[1] = Array[i - 1][ j];
                }

                if ((i == 0) || (j == this.row - 1)) {
                	b2[2] = Array[this.column - 1][j];
                } else {
                	b2[2] = Array[i - 1][j + 1];
                }

                if (j == this.row - 1) {
                	b2[3] = Array[i][0];
                } else {
                	b2[3] = Array[i][j + 1];
                }
                
                if ((i == this.column - 1) || (j == this.row - 1)) {
                	b2[4] = Array[i][0];
                } else {
                	b2[4] = Array[i + 1][ j + 1];
                }

                if (i == this.column - 1) {
                	b2[5] = Array[0][j];
                } else {
                	b2[5] = Array[i + 1][ j];
                }

                if ((i == this.column - 1) || (j == 0)) {
                	b2[6] = Array[0][j];
                } else {
                	b2[6] = Array[i + 1][ j - 1];
                }

                if ((i == 0) || (j == 0)) {
                	b2[7] = Array[i][this.row - 1];
                } else {
                	b2[7] = Array[i][j - 1];
                }

                for (int k = 0; k < 8; k++) {
                	if (b2[k]) {
                		count1++;
                	}
                }
                
                if (b1) {
                	newArr[i][j] = false;
                	if((count1 >= lowBirth) && (count1 <= highBirth)) {
                		newArr[i][j] = true;
                	}
                } else {
                	newArr[i][j] = Array[i][j];
                	if (count1 >= lowSurvive && count1 <= highSurvive) {
                		newArr[i][j] = true;
                	}
                }
            }
        }
            
        if (this.step == 0) {
        	isChange = true;
        } else {
        	if (equals(this.Array, newArr)) {
        		isChange = false;
        	}
        }
        
        this.step++;
        Array = newArr;
    }
    
    public void changeCell(int i, int j) {
        Array[i][j] = !Array[i][j];
    }
    
    public void setClear() {
        for (int i = 0; i < this.column  ; i++) {
            for (int j = 0; j < this.row; j++) {
                step = 0;
                Array[i][j] = false;    
            }
        }
        step = 0;
    }
    
    public void setRandom() {
        step = 0;
        
        Random rand = new Random(); 
        for(int i = 0; i < this.column; i++) {
            for(int j = 0; j < this.row; j++) {
                Array[i][j] = rand.nextBoolean();
            }
        }
    }
    
    
}
