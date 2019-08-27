package annotation.database;

/**
 * 根据此JavaBean生成数据库表
 */
@DBTable(name = "MEMBER")
public class Member {

    @SQLString(30)
    String firstName;

    @SQLString(50)
    String lastName;

    @SQLInteger
    Integer age;

    @SQLString(value = 10, constraints = @Constraints(primaryKey = true))
    String handle;

    static int memberCount;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getHandle() {
        return handle;
    }

    @Override
    public String toString() {
        return handle;
    }
}
