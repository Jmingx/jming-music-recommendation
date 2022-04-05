package club.jming.musicSpider.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 歌单、歌曲中间表
 * @TableName basic_list_song_relation
 */
@TableName(value ="basic_list_song_relation")
@Data
public class BasicListSongRelation implements Serializable {
    /**
     * 主键Id
     */
    @TableId(value = "list_song_relation_id", type = IdType.AUTO)
    private Integer listSongRelationId;

    /**
     * 歌单Id
     */
    @TableField(value = "list_id")
    private String listId;

    /**
     * 歌曲Id
     */
    @TableField(value = "song_id")
    private Integer songId;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 是否展示，逻辑删除
     */
    @TableField(value = "show_status")
    private Integer showStatus;

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
        BasicListSongRelation other = (BasicListSongRelation) that;
        return (this.getListSongRelationId() == null ? other.getListSongRelationId() == null : this.getListSongRelationId().equals(other.getListSongRelationId()))
            && (this.getListId() == null ? other.getListId() == null : this.getListId().equals(other.getListId()))
            && (this.getSongId() == null ? other.getSongId() == null : this.getSongId().equals(other.getSongId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getShowStatus() == null ? other.getShowStatus() == null : this.getShowStatus().equals(other.getShowStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getListSongRelationId() == null) ? 0 : getListSongRelationId().hashCode());
        result = prime * result + ((getListId() == null) ? 0 : getListId().hashCode());
        result = prime * result + ((getSongId() == null) ? 0 : getSongId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getShowStatus() == null) ? 0 : getShowStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", listSongRelationId=").append(listSongRelationId);
        sb.append(", listId=").append(listId);
        sb.append(", songId=").append(songId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", showStatus=").append(showStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}