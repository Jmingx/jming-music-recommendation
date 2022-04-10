package club.jming.musicServer.controller;

import club.jming.musicServer.domain.RankList;
import club.jming.musicServer.domain.RankMusic;
import club.jming.musicServer.service.RankMusicService;
import club.jming.musicServer.utils.R;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class RankMusicController {

    @Autowired
    private RankMusicService rankMusicService;

    // 提交评分
    @ResponseBody
    @RequestMapping(value = "/rankMusic/add", method = RequestMethod.POST)
    public Object addRank(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String musicId = req.getParameter("musicId").trim();
        String consumerId = req.getParameter("consumerId").trim();
        String score = req.getParameter("score").trim();

        RankMusic rankMusic = new RankMusic();
        rankMusic.setMusicId(Long.parseLong(musicId));
        rankMusic.setConsumerId(Long.parseLong(consumerId));
        rankMusic.setRankMusicScore(Integer.parseInt(score));

        boolean res = rankMusicService.addRank(rankMusic);

        if (res) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "评价成功");
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "评价失败");
            return jsonObject;
        }
    }

    // 获取指定歌的评分
    @RequestMapping(value = "/rankMusic", method = RequestMethod.GET)
    public R rankOfMusicId(HttpServletRequest req) {
        String musicId = req.getParameter("musicId");
        Integer score = (Integer) rankMusicService.rankOfMusicId(Long.parseLong(musicId));
        return R.ok().put("score",score);
    }

    // 获取指定用户的歌评分
    @RequestMapping(value = "/rankMusic/user", method = RequestMethod.GET)
    public Object getUserRank(HttpServletRequest req) {
        String consumerId = req.getParameter("consumerId");
        String musicId = req.getParameter("musicId");
        return rankMusicService.getUserRank(Long.parseLong(consumerId), Long.parseLong(musicId));
    }
}
