package club.jming.musicServer.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 评论表
 * @TableName basic_comment
 */
@TableName(value ="basic_comment")
@Data
public class Comment implements Serializable {
    /**
     * 评论Id
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    /**
     * 用户Id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 歌曲Id
     */
    @TableField(value = "music_id")
    private Integer musicId;

    /**
     * 歌单Id
     */
    @TableField(value = "music_list_id")
    private Integer musicListId;

    /**
     * 内容
     */
    @TableField(value = "comment_content")
    private String commentContent;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 类型
     */
    @TableField(value = "comment_type")
    private Byte commentType;

    /**
     * 点赞
     */
    @TableField(value = "comment_up")
    private Integer commentUp;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Comment other = (Comment) that;
        return (this.getCommentId() == null ? other.getCommentId() == null : this.getCommentId().equals(other.getCommentId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getMusicId() == null ? other.getMusicId() == null : this.getMusicId().equals(other.getMusicId()))
            && (this.getMusicListId() == null ? other.getMusicListId() == null : this.getMusicListId().equals(other.getMusicListId()))
            && (this.getCommentContent() == null ? other.getCommentContent() == null : this.getCommentContent().equals(other.getCommentContent()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCommentType() == null ? other.getCommentType() == null : this.getCommentType().equals(other.getCommentType()))
            && (this.getCommentUp() == null ? other.getCommentUp() == null : this.getCommentUp().equals(other.getCommentUp()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCommentId() == null) ? 0 : getCommentId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getMusicId() == null) ? 0 : getMusicId().hashCode());
        result = prime * result + ((getMusicListId() == null) ? 0 : getMusicListId().hashCode());
        result = prime * result + ((getCommentContent() == null) ? 0 : getCommentContent().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCommentType() == null) ? 0 : getCommentType().hashCode());
        result = prime * result + ((getCommentUp() == null) ? 0 : getCommentUp().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", commentId=").append(commentId);
        sb.append(", userId=").append(userId);
        sb.append(", musicId=").append(musicId);
        sb.append(", musicListId=").append(musicListId);
        sb.append(", commentContent=").append(commentContent);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", commentType=").append(commentType);
        sb.append(", commentUp=").append(commentUp);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}