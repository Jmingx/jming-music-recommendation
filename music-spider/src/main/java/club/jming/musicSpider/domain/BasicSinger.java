package club.jming.musicSpider.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 歌手表
 * @TableName basic_singer
 */
@TableName(value ="basic_singer")
@Data
public class BasicSinger implements Serializable {
    /**
     * 主键Id
     */
    @TableId(value = "singer_id", type = IdType.AUTO)
    private Integer singerId;

    /**
     * 歌手名
     */
    @TableField(value = "singer_name")
    private String singerName;

    /**
     * 歌手介绍
     */
    @TableField(value = "singer_description")
    private String singerDescription;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 歌手封面地址
     */
    @TableField(value = "img_address")
    private String imgAddress;

    /**
     * 性别，0表示女，1表示男
     */
    @TableField(value = "gender")
    private Integer gender;

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
        BasicSinger other = (BasicSinger) that;
        return (this.getSingerId() == null ? other.getSingerId() == null : this.getSingerId().equals(other.getSingerId()))
            && (this.getSingerName() == null ? other.getSingerName() == null : this.getSingerName().equals(other.getSingerName()))
            && (this.getSingerDescription() == null ? other.getSingerDescription() == null : this.getSingerDescription().equals(other.getSingerDescription()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getImgAddress() == null ? other.getImgAddress() == null : this.getImgAddress().equals(other.getImgAddress()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getClickNumber() == null ? other.getClickNumber() == null : this.getClickNumber().equals(other.getClickNumber()))
            && (this.getShowStatus() == null ? other.getShowStatus() == null : this.getShowStatus().equals(other.getShowStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSingerId() == null) ? 0 : getSingerId().hashCode());
        result = prime * result + ((getSingerName() == null) ? 0 : getSingerName().hashCode());
        result = prime * result + ((getSingerDescription() == null) ? 0 : getSingerDescription().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getImgAddress() == null) ? 0 : getImgAddress().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
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
        sb.append(", singerId=").append(singerId);
        sb.append(", singerName=").append(singerName);
        sb.append(", singerDescription=").append(singerDescription);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", imgAddress=").append(imgAddress);
        sb.append(", gender=").append(gender);
        sb.append(", clickNumber=").append(clickNumber);
        sb.append(", showStatus=").append(showStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}