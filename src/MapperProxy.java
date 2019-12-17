import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapperProxy implements InvocationHandler {
    private Session session;

    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<T> clazz, Session session)
    {
        MapperProxy<T> proxy = new MapperProxy<T>();
        proxy.session = session;
        // 动态代理
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, proxy);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        SQL anno = method.getAnnotation(SQL.class);
        if (anno == null) {
            return null;
        }
        String sql = anno.value();
        Pattern p = Pattern.compile("\\{[^\\}]+\\}");
        Matcher m = p.matcher(sql);
        if (m.find()) {
            StringBuffer sb = new StringBuffer();
            do {
                //获取{param}，用BeanWrapper处理后的返回值来替换
                String s = m.group();
                int index = Integer.valueOf(s.substring(1, s.length() - 1));
                m.appendReplacement(sb, args[index].toString());
            } while (m.find());
            sql = m.appendTail(sb).toString();
        }
        System.out.println(sql);
        session.exec(sql);
        return null;
    }
}
