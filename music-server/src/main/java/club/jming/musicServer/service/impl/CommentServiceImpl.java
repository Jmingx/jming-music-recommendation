package club.jming.musicServer.service.impl;

import club.jming.musicServer.dao.CommentMapper;
import club.jming.musicServer.domain.Comment;
import club.jming.musicServer.service.CommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public boolean addComment(Comment comment) {
//        return commentMapper.insertSelective(comment) > 0 ? true : false;
        return this.save(comment);
    }

    @Override
    public boolean updateCommentMsg(Comment comment) {
//        return commentMapper.updateCommentMsg(comment) > 0 ? true : false;
        return this.updateById(comment);
    }

    //    删除评论
    @Override
    public boolean deleteComment(Integer id) {
//        return commentMapper.deleteComment(id) > 0 ? true : false;
        return this.removeById(id);
    }

    @Override
    public List<Comment> allComment() {
//        return commentMapper.allComment();
        return this.list();
    }

    @Override
    public List<Comment> commentOfMusicId(Integer musicId) {
//        return commentMapper.commentOfMusicId(MusicId);
        QueryWrapper<Comment> wrapper = new QueryWrapper<Comment>();
        wrapper.eq("music_id",musicId);
        return this.list(wrapper);
    }

    @Override
    public List<Comment> commentOfMusicListId(Integer musicListId) {
//        return commentMapper.commentOfMusicListId(MusicListId);
        QueryWrapper<Comment> wrapper = new QueryWrapper<Comment>();
        wrapper.eq("music_list_id",musicListId);
        return this.list(wrapper);
    }
}
