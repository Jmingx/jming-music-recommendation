package club.jming.musicServer.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 音乐表
 * @TableName basic_music
 */
@TableName(value ="basic_music")
@Data
public class Music implements Serializable {
    /**
     * 主键Id
     */
    @TableId(value = "music_id", type = IdType.AUTO)
    private Integer musicId;

    /**
     * 歌曲名
     */
    @TableField(value = "music_name")
    private String musicName;

    /**
     * 歌手Id
     */
    @TableField(value = "singer_id")
    private Integer singerId;

    /**
     * 专辑id
     */
    @TableField(value = "album_id")
    private Integer albumId;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 音乐文件地址
     */
    @TableField(value = "music_address")
    private String musicAddress;

    /**
     * 封面地址
     */
    @TableField(value = "img_address")
    private String imgAddress;

    /**
     * 点击次数
     */
    @TableField(value = "click_number")
    private Integer clickNumber;

    /**
     * 音乐类型
     */
    @TableField(value = "music_type")
    private String musicType;

    /**
     * 歌词
     */
    @TableField(value = "music_lyric")
    private String musicLyric;

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
        Music other = (Music) that;
        return (this.getMusicId() == null ? other.getMusicId() == null : this.getMusicId().equals(other.getMusicId()))
            && (this.getMusicName() == null ? other.getMusicName() == null : this.getMusicName().equals(other.getMusicName()))
            && (this.getSingerId() == null ? other.getSingerId() == null : this.getSingerId().equals(other.getSingerId()))
            && (this.getAlbumId() == null ? other.getAlbumId() == null : this.getAlbumId().equals(other.getAlbumId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getMusicAddress() == null ? other.getMusicAddress() == null : this.getMusicAddress().equals(other.getMusicAddress()))
            && (this.getImgAddress() == null ? other.getImgAddress() == null : this.getImgAddress().equals(other.getImgAddress()))
            && (this.getClickNumber() == null ? other.getClickNumber() == null : this.getClickNumber().equals(other.getClickNumber()))
            && (this.getMusicType() == null ? other.getMusicType() == null : this.getMusicType().equals(other.getMusicType()))
            && (this.getMusicLyric() == null ? other.getMusicLyric() == null : this.getMusicLyric().equals(other.getMusicLyric()))
            && (this.getShowStatus() == null ? other.getShowStatus() == null : this.getShowStatus().equals(other.getShowStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMusicId() == null) ? 0 : getMusicId().hashCode());
        result = prime * result + ((getMusicName() == null) ? 0 : getMusicName().hashCode());
        result = prime * result + ((getSingerId() == null) ? 0 : getSingerId().hashCode());
        result = prime * result + ((getAlbumId() == null) ? 0 : getAlbumId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getMusicAddress() == null) ? 0 : getMusicAddress().hashCode());
        result = prime * result + ((getImgAddress() == null) ? 0 : getImgAddress().hashCode());
        result = prime * result + ((getClickNumber() == null) ? 0 : getClickNumber().hashCode());
        result = prime * result + ((getMusicType() == null) ? 0 : getMusicType().hashCode());
        result = prime * result + ((getMusicLyric() == null) ? 0 : getMusicLyric().hashCode());
        result = prime * result + ((getShowStatus() == null) ? 0 : getShowStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", musicId=").append(musicId);
        sb.append(", musicName=").append(musicName);
        sb.append(", singerId=").append(singerId);
        sb.append(", albumId=").append(albumId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", musicAddress=").append(musicAddress);
        sb.append(", imgAddress=").append(imgAddress);
        sb.append(", clickNumber=").append(clickNumber);
        sb.append(", musicType=").append(musicType);
        sb.append(", musicLyric=").append(musicLyric);
        sb.append(", showStatus=").append(showStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}