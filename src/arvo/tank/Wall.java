package arvo.tank;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * 类Wall.java的实现描述：TODO 类实现描述 
 * @author Arvo 2014-3-31 下午4:34:45
 */
public class Wall {
   
    int x,y,w,h;
    boolean dir;//false垂直，ture水平
    TankClient tc;
    int lineDis =8;
    int lineDisF =20;
    /**
     * @param x
     * @param y
     * @param w
     * @param h
     * @param tc
     */
    public Wall(int x, int y, int w, int h, TankClient tc,boolean dir){
        super();
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.tc = tc;
        this.dir = dir;
    }
    
    public void draw(Graphics g){
        Color c= g.getColor();
        g.setColor(Color.BLACK);
        g.fillRect(x, y, w, h);
        g.setColor(Color.YELLOW);
        
        if(dir){
            
           
           for(int i=0;i<=h/lineDis;i++){
               g.drawLine(x, y+i*lineDis, x+w, y+i*lineDis);
           }
//           for(int i=0;i<=w/lineDisF;i++){
//               g.drawLine(x+i*lineDisF, y, x+i*lineDisF, y+h);
//           }
           
            
        }else{
            for(int i=0;i<=w/lineDis;i++){
                g.drawLine(x+i*lineDis, y, x+i*lineDis, y+h);
            }
        }
        g.setColor(c);
    }
    public Rectangle getRect(){
        return new Rectangle(x,y,w,h);
    }
    

}
