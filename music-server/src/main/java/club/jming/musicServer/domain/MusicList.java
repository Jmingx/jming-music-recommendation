package club.jming.musicServer.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 歌单表
 * @TableName basic_music_list
 */
@TableName(value ="basic_music_list")
@Data
public class MusicList implements Serializable {
    /**
     * 歌单Id
     */
    @TableId(value = "music_list_id", type = IdType.AUTO)
    private Integer musicListId;

    /**
     * 歌单标题
     */
    @TableField(value = "music_list_title")
    private String musicListTitle;

    /**
     * 歌单封面地址
     */
    @TableField(value = "music_list_img_address")
    private String musicListImgAddress;

    /**
     * 歌单描述
     */
    @TableField(value = "music_list_description")
    private String musicListDescription;

    /**
     * 歌单风格
     */
    @TableField(value = "music_list_style")
    private String musicListStyle;

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
        MusicList other = (MusicList) that;
        return (this.getMusicListId() == null ? other.getMusicListId() == null : this.getMusicListId().equals(other.getMusicListId()))
            && (this.getMusicListTitle() == null ? other.getMusicListTitle() == null : this.getMusicListTitle().equals(other.getMusicListTitle()))
            && (this.getMusicListImgAddress() == null ? other.getMusicListImgAddress() == null : this.getMusicListImgAddress().equals(other.getMusicListImgAddress()))
            && (this.getMusicListDescription() == null ? other.getMusicListDescription() == null : this.getMusicListDescription().equals(other.getMusicListDescription()))
            && (this.getMusicListStyle() == null ? other.getMusicListStyle() == null : this.getMusicListStyle().equals(other.getMusicListStyle()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMusicListId() == null) ? 0 : getMusicListId().hashCode());
        result = prime * result + ((getMusicListTitle() == null) ? 0 : getMusicListTitle().hashCode());
        result = prime * result + ((getMusicListImgAddress() == null) ? 0 : getMusicListImgAddress().hashCode());
        result = prime * result + ((getMusicListDescription() == null) ? 0 : getMusicListDescription().hashCode());
        result = prime * result + ((getMusicListStyle() == null) ? 0 : getMusicListStyle().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", musicListId=").append(musicListId);
        sb.append(", musicListTitle=").append(musicListTitle);
        sb.append(", musicListImgAddress=").append(musicListImgAddress);
        sb.append(", musicListDescription=").append(musicListDescription);
        sb.append(", musicListStyle=").append(musicListStyle);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}