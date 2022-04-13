package club.jming.musicServer.controller;

import club.jming.musicApi.domain.Music;
import club.jming.musicApi.service.RecommendationRPCService;
import club.jming.musicServer.utils.R;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Copyright (C), 2017-2022
 *
 * @author: jmingXie
 * Date: 2022-04-13 10:56
 * FileName: RecommendationController
 * Description: 推荐系统controller，调用music-cf获取
 */
@RestController
public class RecommendationController {

    @Reference(interfaceClass = RecommendationRPCService.class,version = "1.0.0",check = false)
    private RecommendationRPCService rpcService;

    @RequestMapping(value = "/recommendation",method = RequestMethod.GET)
    public R list(@RequestParam("topK")Integer topK,@RequestParam(value = "userId",required = false)Integer userId){
        if (topK == null){
            return R.error(-1,"传入参数topK为null");
        }else {
            List<Music> musicList = rpcService.getRecommendationMusic(topK,userId);
            return R.ok().put("data",musicList);
        }
    }
}
