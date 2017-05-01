/*
 * create by Arvo Email:250185087@qq.com
 */
package arvo.startgame;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import arvo.snake.Yard;
import arvo.tank.TankClient;

/**
 * 类ArvoGame.java的实现描述：TODO 类实现描述 
 * @author Arvo 2014-4-21 下午1:15:56
 */
public class ArvoGame extends JFrame{
    JMenuBar mb  = new JMenuBar();
    JMenu    mExit = new JMenu("退出");
    JMenu   mHelp = new JMenu("帮助");
    JMenu   mAbout = new JMenu ("关于");
    JMenuItem miExit = new JMenuItem ("退出");
    JMenuItem miHelp = new JMenuItem ("帮助");
    JMenuItem miAbout = new JMenuItem ("关于游戏");
    JButton btnGameTank  = new JButton("开始坦克大战游戏");
    JButton btnGameSnake = new JButton("开始贪吃蛇游戏");
    JLabel  LblTitle    = new JLabel("回味经典游戏");
    JLabel  LblBackGroundImg = new JLabel();
    
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image backGroundImg = null;
    static {
        backGroundImg = 
          tk.getImage(ArvoGame.class.getClassLoader().getResource("images/backGround.gif"));
       
    }
    
    
    public ArvoGame(){
        mb.add(mExit);
        mb.add(mHelp);
        mb.add(mAbout);
        mExit.add(miExit);
        mHelp.add(miHelp);
        mAbout.add(miAbout);
        btnGameSnake.setBounds(180, 150, 200, 50);
        btnGameTank.setBounds(180, 250, 200, 50);
        LblTitle.setFont(new Font("宋体",Font.BOLD,34));
        LblTitle.setBounds(180,50,300,50);
        LblBackGroundImg.setIcon(new ImageIcon(backGroundImg));
        LblBackGroundImg.setBounds(0, 0, 600, 500);
        this.setLayout(null);
        this.add(LblTitle);
        this.add(btnGameSnake);
        this.add(btnGameTank);
        this.add(LblBackGroundImg);
        
        this.setJMenuBar(mb);
        this.setSize(600, 500);
        this.setLocation(300, 100);
        this.setVisible(true);
        this.setTitle("031510131郭文豪毕业设计");
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
            
        });
        miExit.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                
                System.exit(0);
            }
        });
        btnGameSnake.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                new Yard().launch();
            }
        });
           btnGameTank.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                new TankClient().lunchFrame();
            }
        });
        
    }
   
    public static void main(String[] args) {
        new ArvoGame();
    }

}
