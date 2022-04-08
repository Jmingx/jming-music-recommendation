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

import club.jming.modules.music.entity.UserListRelationEntity;
import club.jming.modules.music.service.UserListRelationService;
import club.jming.common.utils.PageUtils;
import club.jming.common.utils.R;



/**
 * 用户歌单中间表(收藏歌单表)
 *
 * @author jmingxie
 * @email 782893110@qq.com
 * @date 2022-04-07 00:29:53
 */
@RestController
@RequestMapping("/music/userlistrelation")
public class UserListRelationController {
    @Autowired
    private UserListRelationService userListRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("music:userlistrelation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userListRelationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userListRelationId}")
    @RequiresPermissions("music:userlistrelation:info")
    public R info(@PathVariable("userListRelationId") Integer userListRelationId){
		UserListRelationEntity userListRelation = userListRelationService.getById(userListRelationId);

        return R.ok().put("userListRelation", userListRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("music:userlistrelation:save")
    public R save(@RequestBody UserListRelationEntity userListRelation){
		userListRelationService.save(userListRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("music:userlistrelation:update")
    public R update(@RequestBody UserListRelationEntity userListRelation){
		userListRelationService.updateById(userListRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("music:userlistrelation:delete")
    public R delete(@RequestBody Integer[] userListRelationIds){
		userListRelationService.removeByIds(Arrays.asList(userListRelationIds));

        return R.ok();
    }

}
