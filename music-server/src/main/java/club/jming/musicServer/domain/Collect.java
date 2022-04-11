package club.jming.musicServer.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收藏表
 * @TableName basic_collect
 */
@TableName(value ="basic_collect")
@Data
public class Collect implements Serializable {
    /**
     * 收藏Id
     */
    @TableId(value = "collect_id", type = IdType.AUTO)
    private Integer collectId;

    /**
     * 用户Id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 类型
     */
    @TableField(value = "type")
    private Byte type;

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
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

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
        Collect other = (Collect) that;
        return (this.getCollectId() == null ? other.getCollectId() == null : this.getCollectId().equals(other.getCollectId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getMusicId() == null ? other.getMusicId() == null : this.getMusicId().equals(other.getMusicId()))
            && (this.getMusicListId() == null ? other.getMusicListId() == null : this.getMusicListId().equals(other.getMusicListId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCollectId() == null) ? 0 : getCollectId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getMusicId() == null) ? 0 : getMusicId().hashCode());
        result = prime * result + ((getMusicListId() == null) ? 0 : getMusicListId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", collectId=").append(collectId);
        sb.append(", userId=").append(userId);
        sb.append(", type=").append(type);
        sb.append(", musicId=").append(musicId);
        sb.append(", musicListId=").append(musicListId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}