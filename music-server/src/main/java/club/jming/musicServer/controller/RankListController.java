package club.jming.musicServer.controller;

import club.jming.musicServer.service.impl.RankListServiceImpl;
import com.alibaba.fastjson.JSONObject;
import club.jming.musicServer.domain.RankList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RankListController {

    @Autowired
    private RankListServiceImpl rankListService;

    // 提交评分
    @ResponseBody
    @RequestMapping(value = "/rankList/add", method = RequestMethod.POST)
    public Object addRank(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String musicListId = req.getParameter("musicListId").trim();
        String consumerId = req.getParameter("consumerId").trim();
        String score = req.getParameter("score").trim();

        RankList rank_list = new RankList();
        rank_list.setMusicListId(Long.parseLong(musicListId));
        rank_list.setConsumerId(Long.parseLong(consumerId));
        rank_list.setRankListScore(Integer.parseInt(score));

        boolean res = rankListService.addRank(rank_list);
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

    // 获取指定歌单的评分
    @RequestMapping(value = "/rankList", method = RequestMethod.GET)
    public Object rankOfMusicListId(HttpServletRequest req) {
        String musicListId = req.getParameter("musicListId");
        return rankListService.rankOfMusicListId(Long.parseLong(musicListId));
    }

    // 获取指定用户的歌单评分
    @RequestMapping(value = "/rankList/user", method = RequestMethod.GET)
    public Object getUserRank(HttpServletRequest req) {
        String consumerId = req.getParameter("consumerId");
        String musicListId = req.getParameter("musicListId");
        return rankListService.getUserRank(Long.parseLong(consumerId), Long.parseLong(musicListId));
    }
}
