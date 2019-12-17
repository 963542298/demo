import java.lang.reflect.Method;

public class Test {

    public static Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SQL sql = method.getAnnotation(SQL.class);
        System.out.println(sql.value());
        return null;
    }

    public static void main(String[] args) {
//        SessionFactory factory = new VSessionFactory("config.xml");
//        Session session = factory.openSession();
//        ArticleMapper mapper = session.getMapper(ArticleMapper.class);
//        mapper.findById(1);
    }
}
