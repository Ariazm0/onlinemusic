package dao;

import entity.Music;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Ariazm
 * Date: 2020-07-24
 * Time: 22:58
 */
public class MusicDao {
    //查询所有歌单
    public List<Music> findMusic() {
        List<Music> list = new ArrayList<>();
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            String sql = "";
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                Music music = new Music();
                music.setMusicId(rs.getInt("musicId"));
                music.setTitle(rs.getString("title"));
                music.setSinger(rs.getString("singer"));
                music.setTime(rs.getString("time"));
                music.setUserId(rs.getInt("userId"));
                list.add(music);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection,statement,rs);
        }
    }
}
