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
            String sql = "select * from user where userName = ? and password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,loginUser.getUserName());
            statement.setString(2,loginUser.getPassword());
            set = statement.executeQuery();
            if (set.next()) {
                user = new User();
                user.setUserId(set.getInt("userId"));
                user.setUserName(set.getString("userName"));
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
}
