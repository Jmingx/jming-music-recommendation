package club.jming.modules.music.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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

import club.jming.modules.music.entity.SingerEntity;
import club.jming.modules.music.service.SingerService;
import club.jming.common.utils.PageUtils;
import club.jming.common.utils.R;
import org.springframework.web.multipart.MultipartFile;


/**
 * 歌手表
 *
 * @author jmingxie
 * @email 782893110@qq.com
 * @date 2022-04-07 00:29:53
 */
@RestController
@RequestMapping("/music/singer")
public class SingerController {
    @Autowired
    private SingerService singerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("music:singer:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = singerService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 增加
     * @param singerJSON
     * @param imgSource
     * @return
     */
    @RequestMapping("/save")
    @RequiresPermissions("music:singer:save")
    public R save(@RequestParam("singer") String singerJSON, @RequestParam("imgSource") MultipartFile imgSource){
        SingerEntity basicSinger = JSON.parseObject(singerJSON, SingerEntity.class);
        String singerImgPath = Constant.SINGER_IMG_SRC+basicSinger.getSingerName()+".jpg";
        try {
            imgSource.transferTo(new File(singerImgPath));
            basicSinger.setImgAddress(singerImgPath);
            basicSinger.setUpdateTime(new Date());
        } catch (IOException e) {
            e.printStackTrace();
        }
        singerService.save(basicSinger);
        return R.ok();
    }

    /**
     * 返回所有歌手信息
     * @param params
     * @return
     */
    @RequestMapping("/listAll")
    @RequiresPermissions("music:singer:list")
    public R listAll(@RequestParam Map<String, Object> params){
        List<SingerEntity> singers = singerService.queryAll();

        return R.ok().put("singers", singers);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{singerId}")
    @RequiresPermissions("music:singer:info")
    public R info(@PathVariable("singerId") Integer singerId){
		SingerEntity singer = singerService.getById(singerId);

        return R.ok().put("singer", singer);
    }

    /**
     * 保存
     */
//    @RequestMapping("/save")
//    @RequiresPermissions("music:singer:save")
//    public R save(@RequestBody SingerEntity singer){
//		singerService.save(singer);
//
//        return R.ok();
//    }

    /**
     * 修改
     */
//    @RequestMapping("/update")
//    @RequiresPermissions("music:singer:update")
//    public R update(@RequestBody SingerEntity singer){
//		singerService.updateById(singer);
//
//        return R.ok();
//    }

    /**
     * 更新
     * @param singerJSON
     * @param imgSource
     * @return
     */
    @RequestMapping("/update")
    @RequiresPermissions("music:singer:update")
    public R update(@RequestParam("singer") String singerJSON, @RequestParam("imgSource")MultipartFile imgSource){
        SingerEntity basicSinger = JSON.parseObject(singerJSON, SingerEntity.class);
        String singerImgPath = Constant.SINGER_IMG_SRC+basicSinger.getSingerName()+".jpg";
        try {
            imgSource.transferTo(new File(singerImgPath));
            basicSinger.setImgAddress(singerImgPath);
            basicSinger.setUpdateTime(new Date());
        } catch (IOException e) {
            e.printStackTrace();
        }
        singerService.updateById(basicSinger);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("music:singer:delete")
    public R delete(@RequestBody Integer[] singerIds){
		singerService.removeByIds(Arrays.asList(singerIds));

        return R.ok();
    }

}
