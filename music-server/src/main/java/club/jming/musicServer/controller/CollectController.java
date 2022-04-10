package club.jming.musicServer.controller;

import club.jming.musicServer.service.impl.CollectServiceImpl;
import club.jming.musicServer.utils.R;
import com.alibaba.fastjson.JSONObject;
import club.jming.musicServer.domain.Collect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class CollectController {

    @Autowired
    private CollectServiceImpl collectService;

    // 添加收藏的歌曲
    @ResponseBody
    @RequestMapping(value = "/collection/add", method = RequestMethod.POST)
    public Object addCollection(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String user_id = req.getParameter("userId");
        String type = req.getParameter("type");
        String music_id = req.getParameter("musicId");
        String music_list_id = req.getParameter("musicListId");

        if (music_id == "") {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "收藏歌曲为空");
            return jsonObject;
        } else if (collectService.existMusicId(Integer.parseInt(user_id), Integer.parseInt(music_id))) {
            jsonObject.put("code", 2);
            jsonObject.put("msg", "已收藏");
            return jsonObject;
        }

        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(user_id));
        collect.setType(new Byte(type));
        if (new Byte(type) == 0) {
            collect.setMusicId(Integer.parseInt(music_id));
        } else if (new Byte(type) == 1) {
            collect.setMusicListId(Integer.parseInt(music_list_id));
        }
        collect.setUpdateTime(new Date());

        boolean res = collectService.addCollection(collect);
        if (res) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "收藏成功");
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "收藏失败");
            return jsonObject;
        }
    }

    // 返回所有用户收藏列表
    @RequestMapping(value = "/collection", method = RequestMethod.GET)
    public Object allCollection() {
        return collectService.allCollect();
    }

    // 返回的指定用户ID收藏列表
    @RequestMapping(value = "/collection/detail", method = RequestMethod.GET)
    public Object collectionOfUser(HttpServletRequest req) {
        String userId = req.getParameter("userId");
        return collectService.collectionOfUser(Integer.parseInt(userId));
    }

    // 删除收藏的歌曲
    @RequestMapping(value = "/collection/delete", method = RequestMethod.GET)
    public Object deleteCollection(HttpServletRequest req) {
        String user_id = req.getParameter("userId").trim();
        String music_id = req.getParameter("musicId").trim();
        return collectService.deleteCollect(Integer.parseInt(user_id), Integer.parseInt(music_id));
    }

    // 更新收藏
    @ResponseBody
    @RequestMapping(value = "/collection/update", method = RequestMethod.POST)
    public Object updateCollectMsg(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String user_id = req.getParameter("userId").trim();
        String type = req.getParameter("type").trim();
        String music_id = req.getParameter("musicId").trim();
        // String music_list_id = req.getParameter("musicListId").trim();

        Collect collect = new Collect();
        collect.setCollectId(Integer.parseInt(id));
        collect.setUserId(Integer.parseInt(user_id));
        collect.setType(new Byte(type));
        collect.setMusicId(Integer.parseInt(music_id));

        boolean res = collectService.updateCollectMsg(collect);
        if (res) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "修改成功");
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "修改失败");
            return jsonObject;
        }
    }
}
