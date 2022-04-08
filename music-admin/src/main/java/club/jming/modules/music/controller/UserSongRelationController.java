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

import club.jming.modules.music.entity.UserSongRelationEntity;
import club.jming.modules.music.service.UserSongRelationService;
import club.jming.common.utils.PageUtils;
import club.jming.common.utils.R;



/**
 * 用户歌曲中间表(收藏表)
 *
 * @author jmingxie
 * @email 782893110@qq.com
 * @date 2022-04-07 00:29:53
 */
@RestController
@RequestMapping("/music/usersongrelation")
public class UserSongRelationController {
    @Autowired
    private UserSongRelationService userSongRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("music:usersongrelation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userSongRelationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userSongRelationId}")
    @RequiresPermissions("music:usersongrelation:info")
    public R info(@PathVariable("userSongRelationId") Integer userSongRelationId){
		UserSongRelationEntity userSongRelation = userSongRelationService.getById(userSongRelationId);

        return R.ok().put("userSongRelation", userSongRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("music:usersongrelation:save")
    public R save(@RequestBody UserSongRelationEntity userSongRelation){
		userSongRelationService.save(userSongRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("music:usersongrelation:update")
    public R update(@RequestBody UserSongRelationEntity userSongRelation){
		userSongRelationService.updateById(userSongRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("music:usersongrelation:delete")
    public R delete(@RequestBody Integer[] userSongRelationIds){
		userSongRelationService.removeByIds(Arrays.asList(userSongRelationIds));

        return R.ok();
    }

}
