package arvo.tank;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
/**
 * 类Explode.java的实现描述：TODO 类实现描述
 * 
 * @author Arvo 2014-3-31 下午1:35:54
 */
public class Explode {

    int             x, y;
    private boolean live     = true;
    int             step     = 0;
    private TankClient tc;
    
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    
    private static Image[] imgs = {
                                   tk.getImage(Explode.class.getClassLoader().getResource("images/0.gif")),
                                   tk.getImage(Explode.class.getClassLoader().getResource("images/1.gif")),
                                   tk.getImage(Explode.class.getClassLoader().getResource("images/2.gif")),
                                   tk.getImage(Explode.class.getClassLoader().getResource("images/3.gif")),
                                   tk.getImage(Explode.class.getClassLoader().getResource("images/4.gif")),
                                   tk.getImage(Explode.class.getClassLoader().getResource("images/5.gif")),
                                   tk.getImage(Explode.class.getClassLoader().getResource("images/6.gif")),
                                   tk.getImage(Explode.class.getClassLoader().getResource("images/7.gif")),
                                   tk.getImage(Explode.class.getClassLoader().getResource("images/8.gif")),
                                   tk.getImage(Explode.class.getClassLoader().getResource("images/9.gif")),
                                   tk.getImage(Explode.class.getClassLoader().getResource("images/10.gif"))
                                   
    };
    private boolean init = false;
    
    public Explode(int x,int y,TankClient tc){
        this.x=x;
        this.y=y;
        this.tc=tc;
    }
    public void draw(Graphics g) {
        if(false == init ){
            for(int i =0;i<imgs.length;i++){
                g.drawImage(imgs[i],-100,-100,null);
                init  = true;
            }
            
        }
        if (!live) {
            tc.explodes.remove(this);
            return;
        }
        if (step == imgs.length) {
            live = false;
            step = 0;
            return;
        }
        g.drawImage(imgs[step],x,y,null);
//        Color c = g.getColor();
//        g.setColor(Color.ORANGE);
//        g.fillOval(x, y, diameter[step], diameter[step]);
//        g.setColor(c);
        step++;
    }

}
