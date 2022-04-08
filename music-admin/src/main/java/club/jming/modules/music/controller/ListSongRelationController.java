package club.jming.modules.music.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import club.jming.modules.music.entity.ListSongRelationEntity;
import club.jming.modules.music.service.ListSongRelationService;
import club.jming.common.utils.PageUtils;
import club.jming.common.utils.R;



/**
 * 歌单、歌曲中间表
 *
 * @author jmingxie
 * @email 782893110@qq.com
 * @date 2022-04-07 00:29:53
 */
@RestController
@RequestMapping("/music/listsongrelation")
public class ListSongRelationController {
    @Autowired
    private ListSongRelationService listSongRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("music:listsongrelation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = listSongRelationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{listSongRelationId}")
    @RequiresPermissions("music:listsongrelation:info")
    public R info(@PathVariable("listSongRelationId") Integer listSongRelationId){
		ListSongRelationEntity listSongRelation = listSongRelationService.getById(listSongRelationId);

        return R.ok().put("listSongRelation", listSongRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("music:listsongrelation:save")
    public R save(@RequestBody ListSongRelationEntity listSongRelation){
		listSongRelationService.save(listSongRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("music:listsongrelation:update")
    public R update(@RequestBody ListSongRelationEntity listSongRelation){
		listSongRelationService.updateById(listSongRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("music:listsongrelation:delete")
    public R delete(@RequestBody Integer[] listSongRelationIds){
		listSongRelationService.removeByIds(Arrays.asList(listSongRelationIds));

        return R.ok();
    }

}
