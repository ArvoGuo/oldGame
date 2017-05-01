package arvo.tank;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;



/*
 * Copyright 2011-2012 ifamilyglobal.com All right reserved. This software is the
 * confidential and proprietary information of ifamilyglobal.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ifamilyglobal.com.
 */
/**
 * 类TankClient.java的实现描述：TODO 类实现描述 
 * @author Arvo 2014-3-9 下午2:10:47
 */
public class TankClient extends Frame {
    public static final int GAME_WIDTH=800;
    public static final int GAME_HEIGHT=600;
    Image offScreenImage=null;
    Tank myTank = new Tank(650,450,true,Direction.STOP,this);
    Wall w1=new Wall(100,200,40,200,this,false),w2= new Wall(400,200,300,40,this,true);
    List<Tank> tanks = new ArrayList<Tank>();
    List<Missile> missiles= new ArrayList<Missile>();
    List<Explode> explodes = new ArrayList<Explode>();
    public void paint(Graphics g){
        g.drawString("missiles count:"+missiles.size(), 10, 50);
        g.drawString("explodes count:"+explodes.size(), 10, 70);
        g.drawString("tanks count:"+tanks.size(), 10, 90);
        g.drawString("tanks life:"+myTank.getLife(), 10, 110);
        myTank.draw(g);
     if (tanks.size()<=0) {
         for (int i = 0; i < Integer.parseInt(PropertyMgr.getProperty("reproductTank")); i++) {
             tanks.add(new Tank(50+60*(i+1),50,false,Direction.D,this));

        }
        
    }
     for (int i = 0; i < missiles.size(); i++) {
        Missile m =missiles.get(i);
        m.hitTanks(tanks);
        m.hitTank(myTank);
        m.hitWall(w1);
        m.hitWall(w2);
        m.draw(g);
    }
     for(int i = 0;i<explodes.size();i++){
         Explode e = explodes.get(i);
         e.draw(g);
     }
     for (int i = 0; i < tanks.size(); i++) {
         Tank t = tanks.get(i);
        
         t.collidesWithWall(w1);
         t.collidesWithWall(w2);
         t.collidesWithTanks(tanks);
         myTank.collidesWithTanks(tanks);
         t.draw(g);
        
    }
     w1.draw(g);
     w2.draw(g);
        
    }
    //双缓冲
    public void update(Graphics g){
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(new Color(166,81,35));
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
        
    }
    public void lunchFrame(){
       
        int initTankCount  = Integer.parseInt(PropertyMgr.getProperty("initTankCount")) ;
        for (int i = 0; i < initTankCount; i++) {
            tanks.add(new Tank(50+60*(i+1),50,false,Direction.D,this));
            
        }
        this.setLocation(300,100);
        this.setSize(800,600);
        this.setTitle("TankWar");
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                dispose();
            }
            
        });
        this.setResizable(false);
        this.setBackground(Color.GREEN);
        this.addKeyListener(new KeyMonitor());
        setVisible(true);
        new Thread(new PaintThread()).start();
        
    }
//    public static void main(String[] args) {
//        tc= new TankClient();
//        tc.lunchFrame();
//    }
//    public TankClient(){
//        tc= new TankClient();
//        tc.lunchFrame();
//    }
    //线程刷新
    private class PaintThread implements Runnable{
        public void run() {
            while(true){
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
//键盘监听
    private class KeyMonitor extends KeyAdapter{
        public void keyReleased(KeyEvent e) {
            myTank.keyReleased(e);
        }

        public void keyPressed(KeyEvent e){
            myTank.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_F2 ){
                if(!myTank.isLive()){
                    myTank=new Tank(650,450,true,Direction.STOP,TankClient.this);
                }
            }
        }
    }
    
    
    
    
    
    
    
}
