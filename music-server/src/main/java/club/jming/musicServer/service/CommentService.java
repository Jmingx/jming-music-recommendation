package club.jming.musicServer.service;

import club.jming.musicServer.domain.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CommentService extends IService<Comment> {

    boolean addComment(Comment comment);

    boolean updateCommentMsg(Comment comment);

    boolean deleteComment(Integer id);

    List<Comment> allComment();

    List<Comment> commentOfMusicId(Integer MusicId);

    List<Comment> commentOfMusicListId(Integer MusicListId);

}
