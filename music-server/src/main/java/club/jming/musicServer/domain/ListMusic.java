package club.jming.musicServer.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 歌单-歌曲中间表
 * @TableName basic_list_music
 */
@TableName(value ="basic_list_music")
@Data
public class ListMusic implements Serializable {
    /**
     * Id
     */
    @TableId(value = "list_music_id", type = IdType.AUTO)
    private Integer listMusicId;

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
        ListMusic other = (ListMusic) that;
        return (this.getListMusicId() == null ? other.getListMusicId() == null : this.getListMusicId().equals(other.getListMusicId()))
            && (this.getMusicId() == null ? other.getMusicId() == null : this.getMusicId().equals(other.getMusicId()))
            && (this.getMusicListId() == null ? other.getMusicListId() == null : this.getMusicListId().equals(other.getMusicListId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getListMusicId() == null) ? 0 : getListMusicId().hashCode());
        result = prime * result + ((getMusicId() == null) ? 0 : getMusicId().hashCode());
        result = prime * result + ((getMusicListId() == null) ? 0 : getMusicListId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", listMusicId=").append(listMusicId);
        sb.append(", musicId=").append(musicId);
        sb.append(", musicListId=").append(musicListId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}