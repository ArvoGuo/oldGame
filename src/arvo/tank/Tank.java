package arvo.tank;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * 类Tank.java的实现描述：TODO 类实现描述
 * 
 * @author Arvo 2014-3-9 下午4:12:32
 */
public class Tank {

    public static final int XSPEED = 5;
    public static final int YSPEED = 5;
   
    private int             x, y;
    private int             OldX, OldY;
    private boolean         bL     = false, bU = false, bR = false, bD = false;
    private boolean         good;
    private int             life   = 100;

    private boolean         live   = true;
    private BloodBar        bb     = new BloodBar();


    private static Random r     = new Random();
    private Direction     dir   = Direction.STOP;
    private Direction     ptDir = Direction.D;
    TankClient            tc;
    private int           step  = r.nextInt(12) + 3;
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] tankImages = null;
    private static Map<String,Image> imgs = new HashMap<String,Image>();
    private static Image[] mytankImages = null;
    private static Map<String,Image> myimgs = new HashMap<String,Image>();
    static {
        tankImages = new Image[]{
                                 tk.getImage(Tank.class.getClassLoader().getResource("images/tankL.gif")),
                                 tk.getImage(Tank.class.getClassLoader().getResource("images/tankLU.gif")),
                                 tk.getImage(Tank.class.getClassLoader().getResource("images/tankU.gif")),
                                 tk.getImage(Tank.class.getClassLoader().getResource("images/tankRU.gif")),
                                 tk.getImage(Tank.class.getClassLoader().getResource("images/tankR.gif")),
                                 tk.getImage(Tank.class.getClassLoader().getResource("images/tankRD.gif")),
                                 tk.getImage(Tank.class.getClassLoader().getResource("images/tankD.gif")),
                                 tk.getImage(Tank.class.getClassLoader().getResource("images/tankLD.gif"))
        };
        mytankImages = new Image[]{
                                 tk.getImage(Tank.class.getClassLoader().getResource("images/mytankL.gif")),
                                 tk.getImage(Tank.class.getClassLoader().getResource("images/mytankLU.gif")),
                                 tk.getImage(Tank.class.getClassLoader().getResource("images/mytankU.gif")),
                                 tk.getImage(Tank.class.getClassLoader().getResource("images/mytankRU.gif")),
                                 tk.getImage(Tank.class.getClassLoader().getResource("images/mytankR.gif")),
                                 tk.getImage(Tank.class.getClassLoader().getResource("images/mytankRD.gif")),
                                 tk.getImage(Tank.class.getClassLoader().getResource("images/mytankD.gif")),
                                 tk.getImage(Tank.class.getClassLoader().getResource("images/mytankLD.gif"))
        };
        imgs.put("L", tankImages[0]);
        imgs.put("LU", tankImages[1]);
        imgs.put("U", tankImages[2]);
        imgs.put("RU", tankImages[3]);
        imgs.put("R", tankImages[4]);
        imgs.put("RD", tankImages[5]);
        imgs.put("D", tankImages[6]);
        imgs.put("LD", tankImages[7]);
        myimgs.put("L", mytankImages[0]);
        myimgs.put("LU", mytankImages[1]);
        myimgs.put("U", mytankImages[2]);
        myimgs.put("RU", mytankImages[3]);
        myimgs.put("R", mytankImages[4]);
        myimgs.put("RD", mytankImages[5]);
        myimgs.put("D", mytankImages[6]);
        myimgs.put("LD", mytankImages[7]);
    }
    public static final int WIDTH  = 50;
    public static final int HEIGHT = 50;
    
    public Tank(int x, int y, boolean good){
        super();
        this.x = x;
        this.y = y;
        this.good = good;
        this.OldX = x;
        this.OldY = y;
    }

    public Tank(int x, int y, boolean good, Direction dir, TankClient tc){
        this(x, y, good);
        this.tc = tc;
        this.dir = dir;
    }

    public void draw(Graphics g) {
        if (!live) {
            if (!good) {
                tc.tanks.remove(this);
            }
            return;
        }
//        Color c = g.getColor();
//        if (good) {
//            g.setColor(Color.red);
//        } else {
//            g.setColor(Color.gray);
//        }
//
//        g.fillOval(x, y, WIDTH, HEIGHT);
//        g.setColor(c);
        
        if(good){bb.draw(g);}

        switch (ptDir) {
            case L:
                if(good){g.drawImage(myimgs.get("L"), x, y, null);}
                else
                g.drawImage(imgs.get("L"), x, y, null);
                break;
            case LU:
                if(good){ g.drawImage(myimgs.get("LU"), x, y, null);}
                else
                g.drawImage(imgs.get("LU"), x, y, null);
                break;
            case U:
                if(good){ g.drawImage(myimgs.get("U"), x, y, null);}
                else
                g.drawImage(imgs.get("U"), x, y, null);
                break;
            case RU:
                if(good){g.drawImage(myimgs.get("RU"), x, y, null);}
                else
                g.drawImage(imgs.get("RU"), x, y, null);
                break;
            case R:
                if(good){  g.drawImage(myimgs.get("R"), x, y, null);}
                else
                g.drawImage(imgs.get("R"), x, y, null);
                break;
            case RD:
                if(good){ g.drawImage(myimgs.get("RD"), x, y, null);}
                else
                g.drawImage(imgs.get("RD"), x, y, null);
                break;
            case D:
                if(good){ g.drawImage(myimgs.get("D"), x, y, null);}
                else
                g.drawImage(imgs.get("D"), x, y, null);
                break;
            case LD:
                if(good){ g.drawImage(myimgs.get("LD"), x, y, null);}
                else
                g.drawImage(imgs.get("LD"), x, y, null);
                break;
        }

        move();

    }

    void move() {
        this.OldX = x;
        this.OldY = y;
        switch (dir) {
            case L:
                x -= XSPEED;
                break;
            case LU:
                x -= XSPEED;
                y -= YSPEED;
                break;
            case U:
                y -= XSPEED;
                break;
            case RU:
                x += XSPEED;
                y -= XSPEED;
                break;
            case R:
                x += XSPEED;
                break;
            case RD:
                x += XSPEED;
                y += XSPEED;
                break;
            case D:
                y += XSPEED;
                break;
            case LD:
                x -= XSPEED;
                y += XSPEED;
                break;
            case STOP:
                break;
        }
        if (this.dir != Direction.STOP) {
            this.ptDir = this.dir;
        }
        if (x < 0) {
            x = 0;
        }
        if (y < 25) {
            y = 25;
        }
        if (x + Tank.WIDTH > TankClient.GAME_WIDTH) {
            x = TankClient.GAME_WIDTH - Tank.WIDTH;
        }
        if (y + Tank.WIDTH > TankClient.GAME_HEIGHT) {
            y = TankClient.GAME_HEIGHT - Tank.HEIGHT;
        }
        if (!good) {
            Direction[] dirs = Direction.values();
            if (step == 0) {
                step = r.nextInt(12) + 3;
                int rn = r.nextInt(dirs.length);
                dir = dirs[rn];

            }
            step--;
            if (r.nextInt(40) > 37) {
                this.fire();
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                bL = true;
                break;
            case KeyEvent.VK_UP:
                bU = true;
                break;
            case KeyEvent.VK_RIGHT:
                bR = true;
                break;
            case KeyEvent.VK_DOWN:
                bD = true;
                break;
            
                
        }
        locateDirection();
    }

    void locateDirection() {
        if (bL && !bU && !bR && !bD) {
            dir = Direction.L;
        } else if (!bL && bU && !bR && !bD) {
            dir = Direction.U;
        } else if (!bL && !bU && bR && !bD) {
            dir = Direction.R;
        } else if (!bL && !bU && !bR && bD) {
            dir = Direction.D;
        } else if (bL && bU && !bR && !bD) {
            dir = Direction.LU;
        } else if (bL && !bU && !bR && bD) {
            dir = Direction.LD;
        } else if (!bL && bU && bR && !bD) {
            dir = Direction.RU;
        } else if (!bL && !bU && bR && bD) {
            dir = Direction.RD;
        } else if (!bL && !bU && !bR && !bD) {
            dir = Direction.STOP;
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_CONTROL:
                fire();
                break;
            case KeyEvent.VK_LEFT:
                bL = false;
                break;
            case KeyEvent.VK_UP:
                bU = false;
                break;
            case KeyEvent.VK_RIGHT:
                bR = false;
                break;
            case KeyEvent.VK_DOWN:
                bD = false;
                break;
            case KeyEvent.VK_A:
                superFire();
                break;
        }
        locateDirection();

    }

    public Rectangle getRect() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public Missile fire() {
        if (!live) return null;
        int x = this.x + Tank.WIDTH / 2 - Missile.WIDTH / 2;
        int y = this.y + Tank.HEIGHT / 2 - Missile.HEIGHT / 2;
        Missile m = new Missile(x, y, ptDir, good, this.tc);
        tc.missiles.add(m);
        return m;
    }

    public Missile fire(Direction dir) {
        if (!live) return null;
        int x = this.x + Tank.WIDTH / 2 - Missile.WIDTH / 2;
        int y = this.y + Tank.HEIGHT / 2 - Missile.HEIGHT / 2;
        Missile m = new Missile(x, y, dir, good, this.tc);
        tc.missiles.add(m);
        return m;
    }

    /**
     * @return the live
     */
    public boolean isLive() {
        return live;
    }

    /**
     * @param live the live to set
     */
    public void setLive(boolean live) {
        this.live = live;
    }

    /**
     * @return the good
     */
    public boolean isGood() {
        return good;
    }

    public boolean collidesWithWall(Wall w) {
        if (this.live && this.getRect().intersects(w.getRect())) {
            this.stay();
            return true;
        }
        return false;
    }

    private void stay() {
        x = OldX;
        y = OldY;
    }

    public boolean collidesWithTanks(java.util.List<Tank> tanks) {

        for (int i = 0; i < tanks.size(); i++) {
            Tank t = tanks.get(i);
            if (this != t) {
                if (this.live && t.isLive() && this.getRect().intersects(t.getRect())) {
                    this.stay();
                    t.stay();
                    return true;
                }
            }
        }
        return false;
    }

    private void superFire() {
        Direction[] dirs = Direction.values();
        for (int i = 0; i < 8; i++) {
            fire(dirs[i]);
        }
    }

    /**
     * @return the life
     */
    public int getLife() {
        return life;
    }

    /**
     * @param life the life to set
     */
    public void setLife(int life) {
        this.life = life;
    }

    private class BloodBar {

        public void draw(Graphics g) {
            Color c = g.getColor();
            g.setColor(Color.RED);
            g.drawRect(x, y - 15, WIDTH, 8);
            int w = WIDTH * life / 100;
            g.fillRect(x, y - 15, w, 8);
            g.setColor(c);
        }
    }

}
