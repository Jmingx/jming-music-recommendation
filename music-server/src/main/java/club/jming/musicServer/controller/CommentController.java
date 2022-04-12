package club.jming.musicServer.controller;

import club.jming.musicApi.domain.CfRate;
import club.jming.musicServer.service.KafkaService;
import club.jming.musicServer.service.impl.CommentServiceImpl;
import com.alibaba.fastjson.JSONObject;
import club.jming.musicServer.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private KafkaService kafkaService;

    // 提交评论
    @ResponseBody
    @RequestMapping(value = "/comment/add", method = RequestMethod.POST)
    public Object addComment(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String userId = req.getParameter("userId");
        String type = req.getParameter("type");
        String musicListId = req.getParameter("musicListId");
        String musicId = req.getParameter("musicId");
        String content = req.getParameter("content").trim();

        Comment comment = new Comment();
        comment.setUserId(Integer.parseInt(userId));
        comment.setCommentType(new Byte(type));
        if (new Byte(type) == 0) {
            comment.setMusicId(Integer.parseInt(musicId));
        } else if (new Byte(type) == 1) {
            comment.setMusicListId(Integer.parseInt(musicListId));
        }
        comment.setCommentContent(content);
        comment.setUpdateTime(new Date());
        boolean res = commentService.addComment(comment);
        if (res) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "评论成功");
            this.kafkaService.sendRate(CfRate.commentCfRate(Integer.parseInt(userId),Integer.parseInt(musicId)));
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "评论失败");
            return jsonObject;
        }
    }

    // 获取所有评论列表
    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public Object allComment() {
        return commentService.allComment();
    }

    // 获得指定歌曲ID的评论列表
    @RequestMapping(value = "/comment/music/detail", method = RequestMethod.GET)
    public Object commentOfMusicId(HttpServletRequest req) {
        String musicId = req.getParameter("musicId");
        return commentService.commentOfMusicId(Integer.parseInt(musicId));
    }

    // 获得指定歌单ID的评论列表
    @RequestMapping(value = "/comment/musicList/detail", method = RequestMethod.GET)
    public Object commentOfMusicListId(HttpServletRequest req) {
        String musicListId = req.getParameter("musicListId");
        return commentService.commentOfMusicListId(Integer.parseInt(musicListId));
    }

    // 点赞
    @ResponseBody
    @RequestMapping(value = "/comment/like", method = RequestMethod.POST)
    public Object commentOfLike(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String up = req.getParameter("up").trim();

        Comment comment = new Comment();
        comment.setCommentId(Integer.parseInt(id));
        comment.setCommentUp(Integer.parseInt(up));
        boolean res = commentService.updateCommentMsg(comment);
        if (res) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "点赞成功");
            //TODO 增加打分
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "点赞失败");
            return jsonObject;
        }
    }

    // 删除评论
    @RequestMapping(value = "/comment/delete", method = RequestMethod.GET)
    public Object deleteComment(HttpServletRequest req) {
        String id = req.getParameter("id");
        return commentService.deleteComment(Integer.parseInt(id));
    }

    // 更新评论
    @ResponseBody
    @RequestMapping(value = "/comment/update", method = RequestMethod.POST)
    public Object updateCommentMsg(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String user_id = req.getParameter("userId").trim();
        String music_id = req.getParameter("musicId").trim();
        String music_list_id = req.getParameter("musicListId").trim();
        String content = req.getParameter("content").trim();
        String type = req.getParameter("type").trim();
        String up = req.getParameter("up").trim();

        Comment comment = new Comment();
        comment.setCommentId(Integer.parseInt(id));
        comment.setUserId(Integer.parseInt(user_id));
        if (music_id == "") {
            comment.setMusicId(null);
        } else {
            comment.setMusicId(Integer.parseInt(music_id));
        }

        if (music_list_id == "") {
            comment.setMusicListId(null);
        } else {
            comment.setMusicListId(Integer.parseInt(music_list_id));
        }
        comment.setCommentContent(content);
        comment.setCommentType(new Byte(type));
        comment.setCommentUp(Integer.parseInt(up));

        boolean res = commentService.updateCommentMsg(comment);
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
