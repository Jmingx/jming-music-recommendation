package club.jming.musicServer.controller;

import club.jming.musicServer.domain.ListMusic;
import club.jming.musicServer.service.impl.ListMusicServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ListMusicController {

    @Autowired
    private ListMusicServiceImpl listMusicService;

    // 给歌单添加歌曲
    @ResponseBody
    @RequestMapping(value = "/listMusic/add", method = RequestMethod.POST)
    public Object addListMusic(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String music_id = req.getParameter("musicId").trim();
        String music_list_id = req.getParameter("musicListId").trim();

        ListMusic listmusic = new ListMusic();
        listmusic.setMusicId(Integer.parseInt(music_id));
        listmusic.setMusicListId(Integer.parseInt(music_list_id));

        boolean res = listMusicService.addListMusic(listmusic);
        if (res) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "添加成功");
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "添加失败");
        }
        return jsonObject;
    }

    // 返回歌单里包含的所有歌曲
    @RequestMapping(value = "/listMusic", method = RequestMethod.GET)
    public Object allListMusic() {
        return listMusicService.allListMusic();
    }

    // 返回歌单里指定歌单ID的歌曲
    @RequestMapping(value = "/listMusic/detail", method = RequestMethod.GET)
    public Object listMusicOfMusicId(HttpServletRequest req) {
        String musicListId = req.getParameter("musicListId");
        return listMusicService.listMusicOfMusicId(Integer.parseInt(musicListId));
    }

    // 删除歌单里的歌曲
    @RequestMapping(value = "/listMusic/delete", method = RequestMethod.GET)
    public Object deleteListMusic(HttpServletRequest req) {
        String musicId = req.getParameter("musicId");
        return listMusicService.deleteListMusic(Integer.parseInt(musicId));
    }

    // 更新歌单里面的歌曲信息
    @ResponseBody
    @RequestMapping(value = "/listMusic/update", method = RequestMethod.POST)
    public Object updateListMusicMsg(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String music_id = req.getParameter("musicId").trim();
        String music_list_id = req.getParameter("musicListId").trim();

        ListMusic listmusic = new ListMusic();
        listmusic.setMusicListId(Integer.parseInt(id));
        listmusic.setMusicId(Integer.parseInt(music_id));
        listmusic.setMusicListId(Integer.parseInt(music_list_id));

        boolean res = listMusicService.updateListMusicMsg(listmusic);
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
