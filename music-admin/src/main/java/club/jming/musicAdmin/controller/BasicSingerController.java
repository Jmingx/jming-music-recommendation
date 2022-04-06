package club.jming.musicAdmin.controller;

import club.jming.musicAdmin.domain.BasicSinger;
import club.jming.musicAdmin.service.BasicSingerService;
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
@RequestMapping("/singer")
public class BasicSingerController {

    @Autowired
    private BasicSingerService singerService;

    /**
     * 增加
     * @param singerJSON
     * @param imgSource
     * @return
     */
    @RequestMapping("/save")
    public R save(@RequestParam("singerJSON") String singerJSON, @RequestParam("imgSource")MultipartFile imgSource){
        BasicSinger basicSinger = JSON.parseObject(singerJSON, BasicSinger.class);
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
     * 批量删除
     * @param singers
     * @return
     */
    @RequestMapping("/delete")
    public R deleteBatch(@RequestBody BasicSinger[] singers){
        singerService.removeBatch(singers);
        return R.ok();
    }

    /**
     * 分页返回数据
     * @param params
     * @return
     */
    @RequestMapping("/query")
    public R list(@RequestBody Map<String,Object> params){
        PageUtils page =singerService.queryPage(params);
        return R.ok().put("page",page);
    }

    /**
     * 更新
     * @param singerJSON
     * @param imgSource
     * @return
     */
    @RequestMapping("/update")
    public R update(@RequestParam("singerJSON") String singerJSON, @RequestParam("imgSource")MultipartFile imgSource){
        BasicSinger basicSinger = JSON.parseObject(singerJSON, BasicSinger.class);
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
}