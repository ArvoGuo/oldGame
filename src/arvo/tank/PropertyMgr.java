package arvo.tank;
import java.io.IOException;
import java.util.Properties;

/**
 * ��PropertyMgr.java��ʵ��������TODO ��ʵ������ 
 * @author Arvo 2014-4-13 ����1:44:04
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
    private PropertyMgr(){}//���췽��Ϊ˽��  ������new
    public static String getProperty(String key){
        return props.getProperty(key);
    }
    

}
