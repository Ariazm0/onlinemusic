package util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Ariazm
 * Date: 2020-07-24
 * Time: 22:59
 */
public class JDBCUtil {
    private static String url = "jdbc:mysql://127.0.0.1:3306/onlinemusic?useSSL=false";
    private static String userName = "root";
    private static String password = "110603";
    private static volatile DataSource dataSource = null;

    private static DataSource getDataSource() {
        if(dataSource == null) {
            synchronized (JDBCUtil.class) {
                if (dataSource == null) {
                    dataSource = new MysqlDataSource();
                    ((MysqlDataSource)dataSource).setDatabaseName(userName);
                    ((MysqlDataSource)dataSource).setPassword(password);
                    ((MysqlDataSource)dataSource).setUrl(url);
                }
            }
        }
        return dataSource;
    }

    public static void getConnection() {
        try {
            Connection connection = getDataSource().getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("获取数据库连接失败");
        }
    }
    public static void close (Connection connection,
                              PreparedStatement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
