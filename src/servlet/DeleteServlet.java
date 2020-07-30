package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.MusicDao;
import entity.Music;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Ariazm
 * Date: 2020-07-30
 * Time: 16:03
 */
@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        String idStr = req.getParameter("id");
        int id = Integer.parseInt(idStr);
        System.out.println("id: " + id );
        Map<String, Object> return_map = new HashMap<>();
        try {
            MusicDao dao = new MusicDao();
            Music music = dao.fingMusicById(id);
            if (music == null) {
                return;
            }
            if (dao.deleteMusicById(id) == 1) {
                File file = new File("D:\\javacode\\onlinemusic\\web\\" + music.getUrl()+ ".mp3");
                System.out.println("文件是否存在：" + file.exists());
                System.out.println("file: " + file);
                if (file.delete()) {
                    System.out.println("删除成功");
                    return_map.put("mgs", true);
                }else {
                    System.out.println("文件名： " + file.getName());
                    System.out.println("删除失败");
                    return_map.put("mgs", false);
                }

            } else {
                System.out.println("删除失败");
                return_map.put("mgs", false);
            }
        }catch (Exception e) {
            e.printStackTrace();

        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),return_map);
    }
}
