package club.jming.modules.music.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import club.jming.musicCommon.utils.Constant;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import club.jming.modules.music.entity.MusicEntity;
import club.jming.modules.music.service.MusicService;
import club.jming.common.utils.PageUtils;
import club.jming.common.utils.R;
import org.springframework.web.multipart.MultipartFile;


/**
 * 音乐表
 *
 * @author jmingxie
 * @email 782893110@qq.com
 * @date 2022-04-07 00:29:53
 */
@RestController
@RequestMapping("/music/music")
public class MusicController {
    @Autowired
    private MusicService musicService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("music:music:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = musicService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{musicId}")
    @RequiresPermissions("music:music:info")
    public R info(@PathVariable("musicId") Integer musicId){
		MusicEntity music = musicService.getById(musicId);

        return R.ok().put("music", music);
    }

    /**
     * 保存
     */
//    @RequestMapping("/save")
//    @RequiresPermissions("music:music:save")
//    public R save(@RequestBody MusicEntity music){
//		musicService.save(music);
//
//        return R.ok();
//    }

    /**
     * 增加
     * @param musicJSON
     * @param musicSource
     * @param imgSource
     * @return
     */
    @RequestMapping("/save")
    @RequiresPermissions("music:music:save")
    public club.jming.musicCommon.domain.R save(@RequestParam("music") String musicJSON, @RequestParam("musicSource") MultipartFile musicSource, @RequestParam("imgSource") MultipartFile imgSource){
        MusicEntity basicMusic = JSON.parseObject(musicJSON, MusicEntity.class);
        String musicSourcePath = Constant.SONG_PATH + basicMusic.getMusicName() + ".mp3";
        String musicImgSourcePath = Constant.SONG_PIC_PATH + basicMusic.getMusicName() + ".jpg";
        try {
            musicSource.transferTo(new File(musicSourcePath));
            imgSource.transferTo(new File(musicImgSourcePath));
            basicMusic.setMusicAddress(musicImgSourcePath);
            basicMusic.setImgAddress(musicImgSourcePath);
            basicMusic.setUpdateTime(new Date());
        } catch (IOException e) {
            return club.jming.musicCommon.domain.R.error(500, "封面保存失败");
        }
        musicService.save(basicMusic);
        return club.jming.musicCommon.domain.R.ok();
    }

    /**
     * 修改
     */
//    @RequestMapping("/update")
//    @RequiresPermissions("music:music:update")
//    public R update(@RequestBody MusicEntity music){
//		musicService.updateById(music);
//
//        return R.ok();
//    }

    /**
     * 更新
     * @param musicJSON
     * @param musicSource
     * @param imgSource
     * @return
     */
    @RequestMapping("/update")
    @RequiresPermissions("music:music:update")
    public club.jming.musicCommon.domain.R update(@RequestParam("music") String musicJSON, @RequestParam("musicSource")MultipartFile musicSource, @RequestParam("imgSource") MultipartFile imgSource){
        MusicEntity basicMusic = JSON.parseObject(musicJSON, MusicEntity.class);
        String musicSourcePath = Constant.SONG_PATH + basicMusic.getMusicName() + ".mp3";
        String musicImgSourcePath = Constant.SONG_PIC_PATH + basicMusic.getMusicName() + ".jpg";
        try {
            musicSource.transferTo(new File(musicSourcePath));
            imgSource.transferTo(new File(musicImgSourcePath));
            basicMusic.setMusicAddress(musicImgSourcePath);
            basicMusic.setImgAddress(musicImgSourcePath);
            basicMusic.setUpdateTime(new Date());
        } catch (IOException e) {
            return club.jming.musicCommon.domain.R.error(500, "封面保存失败");
        }
        musicService.updateById(basicMusic);
        return club.jming.musicCommon.domain.R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("music:music:delete")
    public R delete(@RequestBody Integer[] musicIds){
		musicService.removeByIds(Arrays.asList(musicIds));

        return R.ok();
    }

}
