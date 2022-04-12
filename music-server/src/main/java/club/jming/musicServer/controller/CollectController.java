package club.jming.musicServer.controller;

import club.jming.musicApi.domain.CfRate;
import club.jming.musicServer.service.CollectService;
import club.jming.musicServer.service.KafkaService;
import club.jming.musicServer.utils.R;
import com.alibaba.fastjson.JSONObject;
import club.jming.musicServer.domain.Collect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class CollectController {

    @Autowired
    private CollectService collectService;

    @Autowired
    private KafkaService kafkaService;

    /**
     * 判断歌曲是否已收藏
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/collection/collected",method = RequestMethod.GET)
    public R isCollected(@RequestParam("userId")Long userId,@RequestParam("musicId")Long musicId){
        Collect collect = this.collectService.findByUserIdAndMusicId(userId,musicId);
        if (collect == null){
            return R.ok(0,"没有收藏");
        }else {
            return R.ok(1,"已被收藏");
        }
    }

    // 添加收藏的歌曲
    @ResponseBody
    @RequestMapping(value = "/collection/add", method = RequestMethod.POST)
    public Object addCollection(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String userId = req.getParameter("userId");
        String type = req.getParameter("type");
        String musicId = req.getParameter("musicId");
        String musicListId = req.getParameter("musicListId");

        if (musicId == "") {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "收藏歌曲为空");
            return jsonObject;
        } else if (collectService.existMusicId(Integer.parseInt(userId), Integer.parseInt(musicId))) {
            jsonObject.put("code", 2);
            jsonObject.put("msg", "已收藏");
            return jsonObject;
        }

        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(userId));
        collect.setType(new Byte(type));
        if (new Byte(type) == 0) {
            collect.setMusicId(Integer.parseInt(musicId));
        } else if (new Byte(type) == 1) {
            collect.setMusicListId(Integer.parseInt(musicListId));
        }
        collect.setUpdateTime(new Date());

        boolean res = collectService.addCollection(collect);
        if (res) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "收藏成功");
            kafkaService.sendRate(CfRate.collectedCfRate(Integer.parseInt(userId), Integer.parseInt(musicId)));
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "收藏失败");
            return jsonObject;
        }
    }

    // 返回所有用户收藏列表
    @RequestMapping(value = "/collection", method = RequestMethod.GET)
    public Object allCollection() {
        return collectService.allCollect();
    }

    // 返回的指定用户ID收藏列表
    @RequestMapping(value = "/collection/detail", method = RequestMethod.GET)
    public Object collectionOfUser(HttpServletRequest req) {
        String userId = req.getParameter("userId");
        return collectService.collectionOfUser(Integer.parseInt(userId));
    }

    // 删除收藏的歌曲
    @RequestMapping(value = "/collection/delete", method = RequestMethod.GET)
//    public Object deleteCollection(HttpServletRequest req) {
    public R deleteCollection(@RequestParam("userId")Long userId,@RequestParam("musicId")Long musicId) {
//        String user_id = req.getParameter("userId").trim();
//        String music_id = req.getParameter("musicId").trim();
//        return collectService.deleteCollect(Integer.parseInt(user_id), Integer.parseInt(music_id));
        if (this.collectService.deleteCollect(userId,musicId)){
            return R.ok(0,"取消收藏成功");
        }else {
            return R.error();
        }
    }

    // 更新收藏
    @ResponseBody
    @RequestMapping(value = "/collection/update", method = RequestMethod.POST)
    public Object updateCollectMsg(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String user_id = req.getParameter("userId").trim();
        String type = req.getParameter("type").trim();
        String music_id = req.getParameter("musicId").trim();
        // String music_list_id = req.getParameter("musicListId").trim();

        Collect collect = new Collect();
        collect.setCollectId(Integer.parseInt(id));
        collect.setUserId(Integer.parseInt(user_id));
        collect.setType(new Byte(type));
        collect.setMusicId(Integer.parseInt(music_id));

        boolean res = collectService.updateCollectMsg(collect);
        if (res) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "修改成功");
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "修改失败");
            return jsonObject;
        }
    }
}
