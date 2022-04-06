package club.jming.musicAdmin.controller;

import club.jming.musicAdmin.domain.BasicAlbum;
import club.jming.musicAdmin.service.BasicAlbumService;
import club.jming.musicCommon.domain.R;
import club.jming.musicCommon.utils.Constant;
import club.jming.musicCommon.utils.PageUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/album")
@Slf4j
public class BasicAlbumController {

    @Autowired
    private BasicAlbumService albumService;

    /**
     * 增加
     *
     * @param albumJSON
     * @param multipartFile
     * @return
     */
    @RequestMapping("/save")
    public R save(@RequestParam("album") String albumJSON, @RequestParam("imgSource") MultipartFile multipartFile) {
        BasicAlbum basicAlbum = JSON.parseObject(albumJSON, BasicAlbum.class);
        String albumImgSrc = Constant.ALBUM_IMG_SRC + basicAlbum.getAlbumName() + ".jpg";
        try {
            multipartFile.transferTo(new File(albumImgSrc));
            basicAlbum.setImgAddress(albumImgSrc);
            basicAlbum.setUpdateTime(new Date());
            albumService.save(basicAlbum);
        } catch (IOException e) {
            return R.error(500, "封面保存失败");
        }
        return R.ok();
    }

    /**
     * 批量删除
     *
     * @param albumIds
     * @return
     */
    @RequestMapping("/delete")
    public R deleteBatch(@RequestBody BasicAlbum[] albumIds) {
        albumService.removeBatch(albumIds);
        return R.ok();
    }

    /**
     * 分页返回数据
     *
     * @param params
     * @return
     */
    @RequestMapping("/query")
    public R list(@RequestBody Map<String, Object> params) {
        PageUtils page = albumService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 更新
     *
     * @param multipartFile
     * @param albumJSON
     * @return
     */
    @RequestMapping("/update")
    public R update(@RequestParam("imgSource") MultipartFile multipartFile,@RequestParam("album") String albumJSON) {
        BasicAlbum basicAlbum = JSON.parseObject(albumJSON, BasicAlbum.class);
        String albumImgSrc = Constant.ALBUM_IMG_SRC + basicAlbum.getAlbumName() + ".jpg";
        try {
            multipartFile.transferTo(new File(albumImgSrc));
            basicAlbum.setImgAddress(albumImgSrc);
            basicAlbum.setUpdateTime(new Date());
        } catch (IOException e) {
            return R.error(500, "封面保存失败");
        }
        albumService.save(basicAlbum);
        albumService.updateById(basicAlbum);
        return R.ok();
    }
}
