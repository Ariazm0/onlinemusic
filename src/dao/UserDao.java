package dao;

import entity.User;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Ariazm
 * Date: 2020-07-24
 * Time: 22:57
 */
public class UserDao {
    //登录
    public User login(User loginUser) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        User user = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from user where username = ? and password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,loginUser.getUserName());
            statement.setString(2,loginUser.getPassword());
            set = statement.executeQuery();
            if (set.next()) {
                user = new User();
                user.setUserId(set.getInt("id"));
                user.setUserName(set.getString("username"));
                user.setPassword(set.getString("password"));
                user.setAge(set.getInt("age"));
                user.setGender(set.getString("gender"));
                user.setEmail(set.getString("email"));
            }


        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            JDBCUtil.close(connection,statement,set);
        }
        return user;
    }
    //注册
    public void register (User user) {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "insert into user values (null,?,?,?,?,?) ";
            statement = connection.prepareStatement(sql);
            statement.setString(1,user.getUserName());
            statement.setString(2,user.getPassword());
            statement.setInt(3,user.getAge());
            statement.setString(4,user.getGender());
            statement.setString(5,user.getEmail());
            int ret = statement.executeUpdate();
            if (ret != 1) {
                System.out.println("注册失败");
                return;
            }
            System.out.println("注册成功");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection,statement,null);
        }

    }

    /*public static void main(String[] args) {
        UserDao dao = new UserDao();
        User user = new User();
        user.setUserName("bit");
        user.setPassword("123");
        User s = dao.login(user);
        System.out.println(s);
    }*/
}
