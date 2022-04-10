package club.jming.musicServer.controller;

import club.jming.musicServer.domain.MusicList;
import club.jming.musicServer.service.impl.MusicListServiceImpl;
import com.alibaba.fastjson.JSONObject;
import club.jming.musicServer.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
public class MusicListController {

    @Autowired
    private MusicListServiceImpl musicListService;

    @Configuration
    public static class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/musicListPic/**")
                    .addResourceLocations(Constants.SONGLIST_PIC_PATH);
        }
    }

    // 添加歌单
    @ResponseBody
    @RequestMapping(value = "/musicList/add", method = RequestMethod.POST)
    public Object addMusicList(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String title = req.getParameter("title").trim();
        String pic = req.getParameter("pic").trim();
        String introduction = req.getParameter("introduction").trim();
        String style = req.getParameter("style").trim();

        MusicList musicList = new MusicList();
        musicList.setMusicListTitle(title);
        musicList.setMusicListImgAddress(pic);
        musicList.setMusicListDescription(introduction);
        musicList.setMusicListStyle(style);

        boolean res = musicListService.addMusicList(musicList);
        if (res) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "添加成功");
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "添加失败");
            return jsonObject;
        }
    }

    // 返回所有歌单
    @RequestMapping(value = "/musicList", method = RequestMethod.GET)
    public Object allMusicList() {
        return musicListService.allMusicList();
    }

    // 返回指定标题对应的歌单
    @RequestMapping(value = "/musicList/title/detail", method = RequestMethod.GET)
    public Object musicListOfTitle(HttpServletRequest req) {
        String title = req.getParameter("title").trim();
        return musicListService.musicListOfTitle(title);
    }

    // 返回标题包含文字的歌单
    @RequestMapping(value = "/musicList/likeTitle/detail", method = RequestMethod.GET)
    public Object musicListOfLikeTitle(HttpServletRequest req) {
        String title = req.getParameter("title").trim();
        return musicListService.likeTitle('%' + title + '%');
    }

    // 返回指定类型的歌单
    @RequestMapping(value = "/musicList/style/detail", method = RequestMethod.GET)
    public Object musicListOfStyle(HttpServletRequest req) {
        String style = req.getParameter("style").trim();
        return musicListService.likeStyle('%' + style + '%');
    }

    // 删除歌单
    @RequestMapping(value = "/musicList/delete", method = RequestMethod.GET)
    public Object deleteMusicList(HttpServletRequest req) {
        String id = req.getParameter("id");
        return musicListService.deleteMusicList(Integer.parseInt(id));
    }

    // 更新歌单信息
    @ResponseBody
    @RequestMapping(value = "/musicList/update", method = RequestMethod.POST)
    public Object updateMusicListMsg(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String title = req.getParameter("title").trim();
        String pic = req.getParameter("pic").trim();
        String introduction = req.getParameter("introduction").trim();
        String style = req.getParameter("style").trim();

        MusicList musicList = new MusicList();
        musicList.setMusicListId(Integer.parseInt(id));
        musicList.setMusicListTitle(title);
        musicList.setMusicListImgAddress(pic);
        musicList.setMusicListDescription(introduction);
        musicList.setMusicListStyle(style);

        boolean res = musicListService.updateMusicListMsg(musicList);
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

    // 更新歌单图片
    @ResponseBody
    @RequestMapping(value = "/musicList/img/update", method = RequestMethod.POST)
    public Object updateMusicListPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();

        if (avatorFile.isEmpty()) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "文件上传失败！");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "musicListPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeAvatorPath = "/img/musicListPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            MusicList musicList = new MusicList();
            musicList.setMusicListId(id);
            musicList.setMusicListImgAddress(storeAvatorPath);
            boolean res = musicListService.updateMusicListImg(musicList);
            if (res) {
                jsonObject.put("code", 1);
                jsonObject.put("avator", storeAvatorPath);
                jsonObject.put("msg", "上传成功");
                return jsonObject;
            } else {
                jsonObject.put("code", 0);
                jsonObject.put("msg", "上传失败");
                return jsonObject;
            }
        } catch (IOException e) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "上传失败" + e.getMessage());
            return jsonObject;
        }
    }
}
