package club.jming.musicServer.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 歌曲打分表
 * @TableName basic_rank_music
 */
@TableName(value ="basic_rank_music")
@Data
public class RankMusic implements Serializable {
    /**
     * 打分表Id
     */
    @TableId(value = "rank_music_id", type = IdType.AUTO)
    private Long rankMusicId;

    /**
     * 歌单Id
     */
    @TableField(value = "music_id")
    private Long musicId;

    /**
     * 用户Id
     */
    @TableField(value = "consumer_id")
    private Long consumerId;

    /**
     * 分值
     */
    @TableField(value = "rank_music_score")
    private Integer rankMusicScore;

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
        RankMusic other = (RankMusic) that;
        return (this.getRankMusicId() == null ? other.getRankMusicId() == null : this.getRankMusicId().equals(other.getRankMusicId()))
            && (this.getMusicId() == null ? other.getMusicId() == null : this.getMusicId().equals(other.getMusicId()))
            && (this.getConsumerId() == null ? other.getConsumerId() == null : this.getConsumerId().equals(other.getConsumerId()))
            && (this.getRankMusicScore() == null ? other.getRankMusicScore() == null : this.getRankMusicScore().equals(other.getRankMusicScore()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRankMusicId() == null) ? 0 : getRankMusicId().hashCode());
        result = prime * result + ((getMusicId() == null) ? 0 : getMusicId().hashCode());
        result = prime * result + ((getConsumerId() == null) ? 0 : getConsumerId().hashCode());
        result = prime * result + ((getRankMusicScore() == null) ? 0 : getRankMusicScore().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rankMusicId=").append(rankMusicId);
        sb.append(", musicId=").append(musicId);
        sb.append(", consumerId=").append(consumerId);
        sb.append(", rankMusicScore=").append(rankMusicScore);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}