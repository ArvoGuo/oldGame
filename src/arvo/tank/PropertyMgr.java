package arvo.tank;
import java.io.IOException;
import java.util.Properties;

/**
 * 类PropertyMgr.java的实现描述：TODO 类实现描述 
 * @author Arvo 2014-4-13 下午1:44:04
 */
public class PropertyMgr {
    static Properties props = new Properties();
    static 
    {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config/tank.properties"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    private PropertyMgr(){}//构造方法为私有  即不能new
    public static String getProperty(String key){
        return props.getProperty(key);
    }
    

}
