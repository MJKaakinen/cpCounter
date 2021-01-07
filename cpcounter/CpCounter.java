package cpcounter;
import java.util.*; 
import java.util.Optional; 
import java.util.Comparator; 

/**
 * Command Point Counter
 * @author matias.kaakinen, matias.kaakinen@tuni.fi
 *
 * Luokka, jonka oliot pitävät yllä ja manipuloivat Games Workshop -yhtiön Warhammer40K-pelissä 
 * hyödynnettävän Command Points -resurssin tilaa. 
 */
public class CpCounter {

    
    private final int[] SPENDBYTURN;
   
   /*
     * Attribuutit
    */
    
    private int cp;
    
    private int turn;
    
    private int spendCounter;
    
    private int gainCounter;
    
    private String back;
    
    private String forward;
    
    /*
     * Rakentaja
    */
    
    public CpCounter(int i) {
        this.SPENDBYTURN = new int[] {0,0,0,0,0,0};
        cp(i);
        turn(0);
        spendCounter(0);
        gainCounter(0);
        back("back");
        forward("forward");
    }
    
    /*
     * Aksessorit
    */
    
    public int cp() {
        return cp;
    }
    
    public void cp(int i) throws IllegalArgumentException {
        if (i >= 0) {
            cp = i;
        }
        else {
            throw new IllegalArgumentException("Wrong parameter!");
        }
    }
    
    
    public int turn() {
        return turn;
    }
    
    public void turn(int i) throws IllegalArgumentException {
        if (i >= 0) {
            turn = i;
        }
        else {
            throw new IllegalArgumentException("Wrong parameter!");
        }
    }
    
    
    public int spendCounter() {
        return spendCounter;
    }
    
    public void spendCounter(int i) throws IllegalArgumentException {
        if (i >= 0) {
            spendCounter = i;
        }
        else {
            throw new IllegalArgumentException("Wrong parameter!");
        }
    }
    
    
    public int gainCounter() {
        return gainCounter;
    }
    
    public void gainCounter(int i) throws IllegalArgumentException {
        if (i >= 0) {
            gainCounter = i;
        }
        else {
            throw new IllegalArgumentException("Wrong parameter!");
        }
    }
    
    
    public int[] spendByTurn() {
        return SPENDBYTURN;
    }
    
    
    public String back() {
        return back;
    }
    
    public void back(String str) throws IllegalArgumentException {
      if (str != null && str.length() > 0) {
         back = str;
      }
      else {
         throw new IllegalArgumentException("Wrong parameter!");
      }
    }
    
    
    public String forward() {
        return forward;
    }
    
    public void forward(String str) throws IllegalArgumentException {
      if (str != null && str.length() > 0) {
         forward = str;
      }
      else {
         throw new IllegalArgumentException("Wrong parameter!");
      }
    }
    
    /*
     * Metodit
    */
    
    public void increaseCp(int i) {
        cp(cp + i);
        gainCounter(gainCounter + i);
    }
    
    public void decreaseCp(int i) {
        cp(cp - i);
        spendCounter(spendCounter + i);
    }
    
    
    public String changeTurn(String str) throws IllegalArgumentException {
        String message = "";
        if (str != null && str.equals(back) && turn > 0) {
            turn(turn - 1);
            decreaseCp(1);
        }
        else if (str != null && str.equals(forward)) {
            int totalSpent = 0;
            for (int i = 0; i < turn + 1; i++) {
                totalSpent = totalSpent+SPENDBYTURN[i];
            }
            SPENDBYTURN[turn] = spendCounter-totalSpent;
            if (turn < 5) {
                turn(turn + 1);
                increaseCp(1);
                message = message + "true";
            }
            else {
                message = message + end();
            }
        }
        else {
            throw new IllegalArgumentException("Wrong parameter!");
        }
        return message;
    }
    
    public String show(){
        String str = "Turn number: " + turn + "\n" + "Total Command Points: " + 
                cp + "\n" + "Average spend/turn: " + averageSpend()
                + "\n" + "Average gain/turn: " + averageGain();
        for (int i = 0; i < turn; i++) {
            str = str + "\n" + "Command Points spent turn " + i + ": " 
                    + SPENDBYTURN[i];
        }
        return str;
    }
    
    public double averageSpend() {
        if (turn > 0) {
            return (double)spendCounter() / turn;
        }
        else {
            return spendCounter();
        }
    }
    
    public double averageGain() {
        if (turn > 0) {
            return (double)gainCounter() / turn;
        }
        else {
            return gainCounter();
        }
    }
    
    public String end() {
        String str = "\n" + show();
        str = str + "\n" + "Command Points spent turn 5: " 
                    + SPENDBYTURN[5] + "\n" + "Total Command Points spent: " 
                + spendCounter + "\n" + "Most Command Points spent: " + 
                max(SPENDBYTURN) + " (turn " 
                + maxIndex(SPENDBYTURN,max(SPENDBYTURN)) + ")";
        return str;
    }
    
    public int max(int[] ii) {
        int max = ii[0];
        for (int i = 0; i < ii.length; i++) {
            if (ii[i] > max) {
                max = ii[i];
            }
        }
        return max;
    }
    
    public int maxIndex(int[] ii, int max) {
        int index = 0;
        for (int i = 0; i < ii.length; i++) {
            if (ii[i] == max) {
                index = i;
            }
        }
        return index;
    }
    
    @Override
    public String toString() {
        String className = getClass().getSimpleName();
        
        return className + ": " + cp + ", " + turn + ", " + back + ", " + 
                spendCounter + ", " + gainCounter + ", " + forward;
    }
}
