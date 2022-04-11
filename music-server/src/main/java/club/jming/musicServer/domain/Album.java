package club.jming.musicServer.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 专辑表
 * @TableName basic_album
 */
@TableName(value ="basic_album")
@Data
public class Album implements Serializable {
    /**
     * 主键Id
     */
    @TableId(value = "album_id", type = IdType.AUTO)
    private Integer albumId;

    /**
     * 专辑名
     */
    @TableField(value = "album_name")
    private String albumName;

    /**
     * 歌手Id
     */
    @TableField(value = "singer_id")
    private Integer singerId;

    /**
     * 专辑介绍
     */
    @TableField(value = "album_description")
    private String albumDescription;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

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
        Album other = (Album) that;
        return (this.getAlbumId() == null ? other.getAlbumId() == null : this.getAlbumId().equals(other.getAlbumId()))
            && (this.getAlbumName() == null ? other.getAlbumName() == null : this.getAlbumName().equals(other.getAlbumName()))
            && (this.getSingerId() == null ? other.getSingerId() == null : this.getSingerId().equals(other.getSingerId()))
            && (this.getAlbumDescription() == null ? other.getAlbumDescription() == null : this.getAlbumDescription().equals(other.getAlbumDescription()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getImgAddress() == null ? other.getImgAddress() == null : this.getImgAddress().equals(other.getImgAddress()))
            && (this.getClickNumber() == null ? other.getClickNumber() == null : this.getClickNumber().equals(other.getClickNumber()))
            && (this.getShowStatus() == null ? other.getShowStatus() == null : this.getShowStatus().equals(other.getShowStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAlbumId() == null) ? 0 : getAlbumId().hashCode());
        result = prime * result + ((getAlbumName() == null) ? 0 : getAlbumName().hashCode());
        result = prime * result + ((getSingerId() == null) ? 0 : getSingerId().hashCode());
        result = prime * result + ((getAlbumDescription() == null) ? 0 : getAlbumDescription().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getImgAddress() == null) ? 0 : getImgAddress().hashCode());
        result = prime * result + ((getClickNumber() == null) ? 0 : getClickNumber().hashCode());
        result = prime * result + ((getShowStatus() == null) ? 0 : getShowStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", albumId=").append(albumId);
        sb.append(", albumName=").append(albumName);
        sb.append(", singerId=").append(singerId);
        sb.append(", albumDescription=").append(albumDescription);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", imgAddress=").append(imgAddress);
        sb.append(", clickNumber=").append(clickNumber);
        sb.append(", showStatus=").append(showStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}