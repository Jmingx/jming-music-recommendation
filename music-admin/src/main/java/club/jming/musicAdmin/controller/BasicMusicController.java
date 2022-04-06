package club.jming.musicAdmin.controller;

import club.jming.musicAdmin.domain.BasicMusic;
import club.jming.musicAdmin.service.BasicMusicService;
import club.jming.musicCommon.domain.R;
import club.jming.musicCommon.utils.Constant;
import club.jming.musicCommon.utils.PageUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/music")
public class BasicMusicController {

    @Autowired
    private BasicMusicService musicService;

    /**
     * 增加
     * @param musicJSON
     * @param musicSource
     * @param imgSource
     * @return
     */
    @RequestMapping("/save")
    public R save(@RequestParam("musicJSON") String musicJSON, @RequestParam("musicSource")MultipartFile musicSource,@RequestParam("imgSource") MultipartFile imgSource){
        BasicMusic basicMusic = JSON.parseObject(musicJSON, BasicMusic.class);
        String musicSourcePath = Constant.MUSIC_SRC + basicMusic.getMusicName() + ".mp3";
        String musicImgSourcePath = Constant.IMG_SRC + basicMusic.getMusicName() + ".jpg";
        try {
            musicSource.transferTo(new File(musicSourcePath));
            imgSource.transferTo(new File(musicImgSourcePath));
            basicMusic.setMusicAddress(musicImgSourcePath);
            basicMusic.setImgAddress(musicImgSourcePath);
            basicMusic.setUpdateTime(new Date());
        } catch (IOException e) {
            return R.error(500, "封面保存失败");
        }
        musicService.save(basicMusic);
        return R.ok();
    }

    /**
     * 批量删除
     * @param musics
     * @return
     */
    @RequestMapping("/delete")
    public R deleteBatch(@RequestBody BasicMusic[] musics){
        musicService.removeBatch(musics);
        return R.ok();
    }

    /**
     * 分页返回数据
     * @param params
     * @return
     */
    @RequestMapping("/query")
    public R list(@RequestBody Map<String,Object> params){
        PageUtils page =musicService.queryPage(params);
        return R.ok().put("page",page);
    }

    /**
     * 更新
     * @param musicJSON
     * @param musicSource
     * @param imgSource
     * @return
     */
    @RequestMapping("/update")
    public R update(@RequestParam("musicJSON") String musicJSON, @RequestParam("musicSource")MultipartFile musicSource,@RequestParam("imgSource") MultipartFile imgSource){
        BasicMusic basicMusic = JSON.parseObject(musicJSON, BasicMusic.class);
        String musicSourcePath = Constant.MUSIC_SRC + basicMusic.getMusicName() + ".mp3";
        String musicImgSourcePath = Constant.IMG_SRC + basicMusic.getMusicName() + ".jpg";
        try {
            musicSource.transferTo(new File(musicSourcePath));
            imgSource.transferTo(new File(musicImgSourcePath));
            basicMusic.setMusicAddress(musicImgSourcePath);
            basicMusic.setImgAddress(musicImgSourcePath);
            basicMusic.setUpdateTime(new Date());
        } catch (IOException e) {
            return R.error(500, "封面保存失败");
        }
        musicService.updateById(basicMusic);
        return R.ok();
    }
}
