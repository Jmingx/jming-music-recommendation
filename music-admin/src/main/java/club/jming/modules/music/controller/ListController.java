package club.jming.modules.music.controller;

import java.util.Arrays;
import java.util.Map;

import club.jming.modules.music.entity.ListEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import club.jming.modules.music.service.ListService;
import club.jming.common.utils.PageUtils;
import club.jming.common.utils.R;



/**
 * 歌单表
 *
 * @author jmingxie
 * @email 782893110@qq.com
 * @date 2022-04-07 00:29:53
 */
@RestController
@RequestMapping("/music/list")
public class ListController {
    @Autowired
    private ListService listService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("music:list:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = listService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{listId}")
    @RequiresPermissions("music:list:info")
    public R info(@PathVariable("listId") Integer listId){
		ListEntity list = listService.getById(listId);

        return R.ok().put("list", list);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("music:list:save")
    public R save(@RequestBody ListEntity list){
		listService.save(list);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("music:list:update")
    public R update(@RequestBody ListEntity list){
		listService.updateById(list);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("music:list:delete")
    public R delete(@RequestBody Integer[] listIds){
		listService.removeByIds(Arrays.asList(listIds));

        return R.ok();
    }

}
