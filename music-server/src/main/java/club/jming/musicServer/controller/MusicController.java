package club.jming.musicServer.controller;

import club.jming.musicApi.domain.CfRate;
import club.jming.musicServer.domain.Music;
import club.jming.musicServer.service.KafkaService;
import club.jming.musicServer.service.impl.MusicServiceImpl;
import club.jming.musicServer.utils.R;
import com.alibaba.fastjson.JSONObject;
import club.jming.musicServer.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
public class MusicController {

    @Autowired
    private MusicServiceImpl musicService;

    @Autowired
    private KafkaService kafkaService;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 文件最大10M,DataUnit提供5中类型B,KB,MB,GB,TB
        factory.setMaxFileSize(DataSize.of(10, DataUnit.MEGABYTES));
        // 设置总上传数据总大小10M
        factory.setMaxRequestSize(DataSize.of(10, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }

    @Configuration
    public static class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/songPic/**")
                    .addResourceLocations(Constants.SONG_PIC_PATH);
            registry.addResourceHandler("/song/**")
                    .addResourceLocations(Constants.SONG_PATH);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/music/play",method = RequestMethod.GET)
    public R playMusic(@RequestParam("userId")Integer userId,@RequestParam("musicId")Integer musicId){
        if (userId != null && musicId != null){
            this.kafkaService.sendRate(CfRate.playCfRate(userId,musicId));
            return R.ok();
        }else {
            return R.error(-1,"数据格式错误");
        }
    }

    // 添加歌曲
    @ResponseBody
    @RequestMapping(value = "/music/add", method = RequestMethod.POST)
    public Object addMusic(HttpServletRequest req, @RequestParam("file") MultipartFile mpfile) {
        JSONObject jsonObject = new JSONObject();
        String singer_id = req.getParameter("singerId").trim();
        String name = req.getParameter("name").trim();
        String introduction = req.getParameter("introduction").trim();
        String pic = "/img/musicPic/tubiao.jpg";
        String lyric = req.getParameter("lyric").trim();

        if (mpfile.isEmpty()) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "音乐上传失败！");
            return jsonObject;
        }
        String fileName = mpfile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "music";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/music/" + fileName;
        try {
            mpfile.transferTo(dest);
            Music music = new Music();
            music.setSingerId(Integer.parseInt(singer_id));
            music.setMusicName(name);
//            music.setIntroduction(introduction);
//            music.setCreateTime(new Date());
            music.setUpdateTime(new Date());
            music.setImgAddress(pic);
            music.setMusicLyric(lyric);
            music.setMusicAddress(storeUrlPath);
            boolean res = musicService.addMusic(music);
            if (res) {
                jsonObject.put("code", 1);
                jsonObject.put("avator", storeUrlPath);
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

    // 返回所有歌曲
    @RequestMapping(value = "/music", method = RequestMethod.GET)
    public Object allMusic() {
        return musicService.allMusic();
    }

    // 返回指定歌曲ID的歌曲
    @RequestMapping(value = "/music/detail", method = RequestMethod.GET)
    public Object musicOfId(HttpServletRequest req) {
        String id = req.getParameter("id");
        return musicService.musicOfId(Integer.parseInt(id));
    }

    // 返回指定歌手ID的歌曲
    @RequestMapping(value = "/music/singer/detail", method = RequestMethod.GET)
    public Object musicOfSingerId(HttpServletRequest req) {
        String singerId = req.getParameter("singerId");
        return musicService.musicOfSingerId(Integer.parseInt(singerId));
    }

    // 返回指定歌手名的歌曲
    @RequestMapping(value = "/music/singerName/detail", method = RequestMethod.GET)
    public Object musicOfSingerName(HttpServletRequest req) {
        String name = req.getParameter("name");
        return musicService.musicOfSingerName(name);
    }

    // 返回指定歌曲名的歌曲
    @RequestMapping(value = "/music/name/detail", method = RequestMethod.GET)
    public Object musicOfName(HttpServletRequest req) {
        String name = req.getParameter("name").trim();
        return musicService.musicOfName(name);
    }

    // 删除歌曲
    @RequestMapping(value = "/music/delete", method = RequestMethod.GET)
    public Object deleteMusic(HttpServletRequest req) {
        String id = req.getParameter("id");
        return musicService.deleteMusic(Integer.parseInt(id));
    }

    // 更新歌曲信息
    @ResponseBody
    @RequestMapping(value = "/music/update", method = RequestMethod.POST)
    public Object updateMusicMsg(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String singer_id = req.getParameter("singerId").trim();
        String name = req.getParameter("name").trim();
        String introduction = req.getParameter("introduction").trim();
        String lyric = req.getParameter("lyric").trim();

        Music music = new Music();
        music.setMusicId(Integer.parseInt(id));
        music.setSingerId(Integer.parseInt(singer_id));
        music.setMusicName(name);
//        music.setIntroduction(introduction);
        music.setUpdateTime(new Date());
        music.setMusicLyric(lyric);

        boolean res = musicService.updateMusicMsg(music);
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

    // 更新歌曲图片
    @ResponseBody
    @RequestMapping(value = "/music/img/update", method = RequestMethod.POST)
    public Object updateMusicPic(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();

        if (urlFile.isEmpty()) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "音乐上传失败！");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis() + urlFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "musicPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/img/musicPic/" + fileName;
        try {
            urlFile.transferTo(dest);
            Music music = new Music();
            music.setMusicId(id);
            music.setImgAddress(storeUrlPath);
            boolean res = musicService.updateMusicPic(music);
            if (res) {
                jsonObject.put("code", 1);
                jsonObject.put("avator", storeUrlPath);
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

    // 更新歌曲URL
    @ResponseBody
    @RequestMapping(value = "/music/url/update", method = RequestMethod.POST)
    public Object updateMusicUrl(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        if (urlFile.isEmpty()) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "音乐上传失败！");
            return jsonObject;
        }
        String fileName = urlFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "music";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/music/" + fileName;
        try {
            urlFile.transferTo(dest);
            Music music = new Music();
            music.setMusicId(id);
            music.setMusicAddress(storeUrlPath);
            boolean res = musicService.updateMusicUrl(music);
            if (res) {
                jsonObject.put("code", 1);
                jsonObject.put("avator", storeUrlPath);
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
