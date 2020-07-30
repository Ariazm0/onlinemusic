package entity;

import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Ariazm
 * Date: 2020-07-24
 * Time: 22:49
 */
public class Music {
    private int musicId;
    private String title;
    private String singer;
    private String url;
    private Date time;
    private int userId;

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Music{" +
                "musicId=" + musicId +
                ", title='" + title + '\'' +
                ", singer='" + singer + '\'' +
                ", url='" + url + '\'' +
                ", time='" + time + '\'' +
                ", userId=" + userId +
                '}';
    }
}
