/*
 * create by Arvo Email:250185087@qq.com
 */
package arvo.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * 类Egg.java的实现描述：TODO 类实现描述 
 * @author Arvo 2014-4-14 下午3:59:55
 */
public class Egg {
    int row ,col;
   
    int w = Yard.BLOCK_SIZE;
    int h = Yard.BLOCK_SIZE;
    private static Random r = new Random();
    public Egg(int row,int col){
        this.row = row;
        this.col = col;
        
    }
    public Egg(){
        this(r.nextInt(Yard.ROWS-5)+3,r.nextInt(Yard.COLS-5)+3);
        
    }
    public void reAppear(){
        this.row = r.nextInt(Yard.ROWS-5)+3;
        this.col = r.nextInt(Yard.COLS-5)+3;
    }
    public Rectangle getRect(){
        return new Rectangle(Yard.BLOCK_SIZE * col, Yard.BLOCK_SIZE * row,w,h);
    }
    public void draw(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(Yard.BLOCK_SIZE * col, Yard.BLOCK_SIZE * row, w, h);
        g.setColor(c);
        
    }
    
    /**
     * @return the row
     */
    public int getRow() {
        return row;
    }
    
    /**
     * @param row the row to set
     */
    public void setRow(int row) {
        this.row = row;
    }
    
    /**
     * @return the col
     */
    public int getCol() {
        return col;
    }
    
    /**
     * @param col the col to set
     */
    public void setCol(int col) {
        this.col = col;
    }
}
