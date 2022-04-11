package club.jming.musicServer.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 打分表
 * @TableName basic_rank_list
 */
@TableName(value ="basic_rank_list")
@Data
public class RankList implements Serializable {
    /**
     * 打分表Id
     */
    @TableId(value = "rank_list_id", type = IdType.AUTO)
    private Long rankListId;

    /**
     * 歌单Id
     */
    @TableField(value = "music_list_id")
    private Long musicListId;

    /**
     * 用户Id
     */
    @TableField(value = "consumer_id")
    private Long consumerId;

    /**
     * 分值
     */
    @TableField(value = "rank_list_score")
    private Integer rankListScore;

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
        RankList other = (RankList) that;
        return (this.getRankListId() == null ? other.getRankListId() == null : this.getRankListId().equals(other.getRankListId()))
            && (this.getMusicListId() == null ? other.getMusicListId() == null : this.getMusicListId().equals(other.getMusicListId()))
            && (this.getConsumerId() == null ? other.getConsumerId() == null : this.getConsumerId().equals(other.getConsumerId()))
            && (this.getRankListScore() == null ? other.getRankListScore() == null : this.getRankListScore().equals(other.getRankListScore()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRankListId() == null) ? 0 : getRankListId().hashCode());
        result = prime * result + ((getMusicListId() == null) ? 0 : getMusicListId().hashCode());
        result = prime * result + ((getConsumerId() == null) ? 0 : getConsumerId().hashCode());
        result = prime * result + ((getRankListScore() == null) ? 0 : getRankListScore().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rankListId=").append(rankListId);
        sb.append(", musicListId=").append(musicListId);
        sb.append(", consumerId=").append(consumerId);
        sb.append(", rankListScore=").append(rankListScore);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}