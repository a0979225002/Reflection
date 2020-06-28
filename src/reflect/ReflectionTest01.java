package reflect;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 框架類
 */

public class ReflectionTest01 {

    public static void main(String[] args) throws Exception{
        //如果要把它當成框架,他不能改變裡面的任何code,也能創造任意對象的類,可以執行任意方法

        //1.加載配置文件
        Properties properties = new Properties();
        //獲取配置文件

        //讓ReflctionTest01.class 加載類加載器
        ClassLoader classLoader =  ReflectionTest01.class.getClassLoader();

        //ClassLoader(類加載器)可以獲得整個src內所得的文件,這裡獲得配置文件
        InputStream inputStream =
                classLoader.getResourceAsStream("Resources/pro.properties");

        properties.load(inputStream);

        //獲取配置文件內的值
        String className =  properties.getProperty("className");
        String methodName =  properties.getProperty("methodName");

        //加載該類進內存
            //加載Class (這裡配置文件內加載的是Person這個類)
            Class cls = Class.forName(className);

            //創建對象,理解成 new Person()
            Constructor constructor = cls.getConstructor();

            Object o =  constructor.newInstance();

            //獲取方法對象
            //使用配置文件的方法 eat,該eat方法在Person是有的
            Method method = cls.getMethod(methodName,String.class);

            //對該方法給值
            method.invoke(o,"麥當勞");//eat....麥當勞


    }

}
