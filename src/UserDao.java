
public interface UserDao {
    // 正规写法是@SQL(value="xxxx")，若只有value属性可略去
    @SQL("select * from user where id = {0}")
    User getUser(int id);
}
