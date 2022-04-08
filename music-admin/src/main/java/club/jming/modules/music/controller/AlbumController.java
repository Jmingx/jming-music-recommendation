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

import club.jming.modules.music.entity.AlbumEntity;
import club.jming.modules.music.service.AlbumService;
import club.jming.common.utils.PageUtils;
import club.jming.common.utils.R;
import org.springframework.web.multipart.MultipartFile;


/**
 * 专辑表
 *
 * @author jmingxie
 * @email 782893110@qq.com
 * @date 2022-04-07 00:29:53
 */
@RestController
@RequestMapping("/music/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    /**
     * 增加
     *
     * @param albumJSON
     * @param multipartFile
     * @return
     */
    @RequestMapping("/save")
    @RequiresPermissions("music:album:save")
    public R save(@RequestParam("album") String albumJSON, @RequestParam("imgSource") MultipartFile multipartFile) {
        AlbumEntity basicAlbum = JSON.parseObject(albumJSON, AlbumEntity.class);
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
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("music:album:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = albumService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 返回所有专辑信息
     * @param params
     * @return
     */
    @RequestMapping("/listAll")
    @RequiresPermissions("music:album:list")
    public R listAll(@RequestParam Map<String, Object> params) {
        List<AlbumEntity> albums = albumService.list();

        return R.ok().put("albums", albums);
    }

    /**
     * 根据singerId返回专辑信息
     * @param singerId
     * @return
     */
    @RequestMapping("/list/{singerId}")
    @RequiresPermissions("music:album:list")
    public R listBySingerId(@PathVariable("singerId") Integer singerId){
        List<AlbumEntity> albums = albumService.listBySingerId(singerId);

        return R.ok().put("albums", albums);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{albumId}")
    @RequiresPermissions("music:album:info")
    public R info(@PathVariable("albumId") Integer albumId) {
        AlbumEntity album = albumService.getById(albumId);

        return R.ok().put("album", album);
    }

    /**
     * 保存
     */
//    @RequestMapping("/save")
//    @RequiresPermissions("music:album:save")
//    public R save(@RequestBody AlbumEntity album){
//		albumService.save(album);
//
//        return R.ok();
//    }

    /**
     * 修改
     */
//    @RequestMapping("/update")
//    @RequiresPermissions("music:album:update")
//    public R update(@RequestBody AlbumEntity album){
//		albumService.updateById(album);
//
//        return R.ok();
//    }

    /**
     * 更新
     *
     * @param multipartFile
     * @param albumJSON
     * @return
     */
    @RequestMapping("/update")
    @RequiresPermissions("music:album:update")
    public R update(@RequestParam("imgSource") MultipartFile multipartFile, @RequestParam("album") String albumJSON) {
        AlbumEntity basicAlbum = JSON.parseObject(albumJSON, AlbumEntity.class);
        String albumImgSrc = Constant.ALBUM_IMG_SRC + basicAlbum.getAlbumName() + ".jpg";
        try {
            multipartFile.transferTo(new File(albumImgSrc));
            basicAlbum.setImgAddress(albumImgSrc);
            basicAlbum.setUpdateTime(new Date());
        } catch (IOException e) {
            return R.error(500, "封面保存失败");
        }
        albumService.updateById(basicAlbum);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("music:album:delete")
    public R delete(@RequestBody Integer[] albumIds) {
        albumService.removeByIds(Arrays.asList(albumIds));

        return R.ok();
    }

}
