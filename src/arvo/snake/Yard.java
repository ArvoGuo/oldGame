/*
 * create by Arvo Email:250185087@qq.com
 */
package arvo.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * 类Yard.java的实现描述：TODO 类实现描述 
 * @author Arvo 2014-4-14 下午4:00:29
 */
public class Yard extends Frame {
    
    PaintThread paintThread = new PaintThread();
    private boolean gameOver = false;
    public  static final int ROWS=30;
    public static final int COLS=30;
    public static final int BLOCK_SIZE=15;
    Image offScreenImage = null;
    Snake s= new Snake(this);
    Egg   e = new Egg();
    private int score = 0 ;
    private Font fontScore = new Font("宋体",Font.BOLD,14);
    private Font fontScoreNum = new Font("宋体",Font.BOLD,22);
    private Font fontGameOver = new Font("宋体",Font.BOLD,50);
  
    public void launch(){
        this.setLocation(300,100);
        this.setSize(COLS*BLOCK_SIZE+10, ROWS*BLOCK_SIZE+10);
        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
               dispose();
            }
        
        });
        this.setVisible(true);
        this.addKeyListener(new KeyMonitor());
        new Thread(paintThread).start();
        
    }
    public void stop(){
        gameOver = true;
    }
    public void paint(Graphics g) {
       
        Color c =g.getColor();
        
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, COLS*BLOCK_SIZE, ROWS*BLOCK_SIZE);
        g.setColor(Color.PINK);
        for (int i = 1; i < ROWS; i++) {
            g.drawLine(0, BLOCK_SIZE*i, COLS*BLOCK_SIZE, BLOCK_SIZE*i);
        }
        for (int i = 1; i < COLS; i++) {
            g.drawLine(BLOCK_SIZE*i, 0, BLOCK_SIZE*i, ROWS*BLOCK_SIZE);
        }
        g.setColor(Color.ORANGE);
        g.fillRect(0, BLOCK_SIZE*2, BLOCK_SIZE * COLS, BLOCK_SIZE);
        g.fillRect(0, BLOCK_SIZE*(ROWS-1), BLOCK_SIZE * COLS, BLOCK_SIZE);
        g.fillRect(0,0, BLOCK_SIZE, BLOCK_SIZE * ROWS );
        g.fillRect(BLOCK_SIZE * (COLS-1),0, BLOCK_SIZE, BLOCK_SIZE * ROWS );
        g.setColor(Color.YELLOW);
        g.setFont(fontScore);
        g.drawString("Score:", 320, 60);
        g.setFont(fontScoreNum);
        g.drawString(""+score, 390, 60);
        
        if(gameOver){
            g.setColor(Color.cyan);
            g.setFont(fontGameOver);
            g.drawString("游戏结束", 120, 180);
            g.drawString("F2重新开始", 100, 240);
            paintThread.pause();
        }
        
        g.setColor(c);
        s.eat(e);
        s.draw(g);
        e.draw(g);
    }
    public void update(Graphics g){
        if(offScreenImage == null){
            offScreenImage  = this .createImage(COLS *BLOCK_SIZE,ROWS*BLOCK_SIZE);
            
        }
        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage , 0,0,null);
        
    }
    
    private class PaintThread implements Runnable{
private boolean running = true;
private boolean pause = false;
        public void run() {
            while(running){
                if(pause) {
                    continue ;
                }
                else repaint();
                try{
                    Thread.sleep(150);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        public void pause(){
            this.pause = true;
        }
        public void reStart(){
            this.pause = false;
            s = new Snake (Yard.this);
            score=0;
            gameOver = false;
        }
        public void gameOver(){
            this.running = false;
        }
        
    }
    private class KeyMonitor extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_F2){
                paintThread.reStart();
            }
            s.keyPressed(e);
        }
        
    }
//    public static void main(String[] args) {
//        new Yard().launch();
//
//    }
//  
    
    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }
    
    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }
}
