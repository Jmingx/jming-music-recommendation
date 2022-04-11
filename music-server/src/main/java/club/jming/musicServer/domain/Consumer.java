package club.jming.musicServer.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户表
 * @TableName basic_consumer
 */
@TableName(value ="basic_consumer")
@Data
public class Consumer implements Serializable {
    /**
     * 用户Id
     */
    @TableId(value = "consumer_id", type = IdType.AUTO)
    private Integer consumerId;

    /**
     * 用户账号
     */
    @TableField(value = "consumer_username")
    private String consumerUsername;

    /**
     * 用户密码
     */
    @TableField(value = "consumer_password")
    private String consumerPassword;

    /**
     * 性别
     */
    @TableField(value = "consumer_sex")
    private Byte consumerSex;

    /**
     * 电话号码
     */
    @TableField(value = "consumer_phone_num")
    private String consumerPhoneNum;

    /**
     * 邮件
     */
    @TableField(value = "consumer_email")
    private String consumerEmail;

    /**
     * 生日
     */
    @TableField(value = "consumer_birth")
    private Date consumerBirth;

    /**
     * 简介
     */
    @TableField(value = "consumer_introduction")
    private String consumerIntroduction;

    /**
     * 地址
     */
    @TableField(value = "consumer_location")
    private String consumerLocation;

    /**
     * 头像地址
     */
    @TableField(value = "consumer_avator")
    private String consumerAvator;

    /**
     * 创建时间
     */
    @TableField(value = "consumer_create_time")
    private Date consumerCreateTime;

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
        Consumer other = (Consumer) that;
        return (this.getConsumerId() == null ? other.getConsumerId() == null : this.getConsumerId().equals(other.getConsumerId()))
            && (this.getConsumerUsername() == null ? other.getConsumerUsername() == null : this.getConsumerUsername().equals(other.getConsumerUsername()))
            && (this.getConsumerPassword() == null ? other.getConsumerPassword() == null : this.getConsumerPassword().equals(other.getConsumerPassword()))
            && (this.getConsumerSex() == null ? other.getConsumerSex() == null : this.getConsumerSex().equals(other.getConsumerSex()))
            && (this.getConsumerPhoneNum() == null ? other.getConsumerPhoneNum() == null : this.getConsumerPhoneNum().equals(other.getConsumerPhoneNum()))
            && (this.getConsumerEmail() == null ? other.getConsumerEmail() == null : this.getConsumerEmail().equals(other.getConsumerEmail()))
            && (this.getConsumerBirth() == null ? other.getConsumerBirth() == null : this.getConsumerBirth().equals(other.getConsumerBirth()))
            && (this.getConsumerIntroduction() == null ? other.getConsumerIntroduction() == null : this.getConsumerIntroduction().equals(other.getConsumerIntroduction()))
            && (this.getConsumerLocation() == null ? other.getConsumerLocation() == null : this.getConsumerLocation().equals(other.getConsumerLocation()))
            && (this.getConsumerAvator() == null ? other.getConsumerAvator() == null : this.getConsumerAvator().equals(other.getConsumerAvator()))
            && (this.getConsumerCreateTime() == null ? other.getConsumerCreateTime() == null : this.getConsumerCreateTime().equals(other.getConsumerCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getShowStatus() == null ? other.getShowStatus() == null : this.getShowStatus().equals(other.getShowStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getConsumerId() == null) ? 0 : getConsumerId().hashCode());
        result = prime * result + ((getConsumerUsername() == null) ? 0 : getConsumerUsername().hashCode());
        result = prime * result + ((getConsumerPassword() == null) ? 0 : getConsumerPassword().hashCode());
        result = prime * result + ((getConsumerSex() == null) ? 0 : getConsumerSex().hashCode());
        result = prime * result + ((getConsumerPhoneNum() == null) ? 0 : getConsumerPhoneNum().hashCode());
        result = prime * result + ((getConsumerEmail() == null) ? 0 : getConsumerEmail().hashCode());
        result = prime * result + ((getConsumerBirth() == null) ? 0 : getConsumerBirth().hashCode());
        result = prime * result + ((getConsumerIntroduction() == null) ? 0 : getConsumerIntroduction().hashCode());
        result = prime * result + ((getConsumerLocation() == null) ? 0 : getConsumerLocation().hashCode());
        result = prime * result + ((getConsumerAvator() == null) ? 0 : getConsumerAvator().hashCode());
        result = prime * result + ((getConsumerCreateTime() == null) ? 0 : getConsumerCreateTime().hashCode());
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
        sb.append(", consumerId=").append(consumerId);
        sb.append(", consumerUsername=").append(consumerUsername);
        sb.append(", consumerPassword=").append(consumerPassword);
        sb.append(", consumerSex=").append(consumerSex);
        sb.append(", consumerPhoneNum=").append(consumerPhoneNum);
        sb.append(", consumerEmail=").append(consumerEmail);
        sb.append(", consumerBirth=").append(consumerBirth);
        sb.append(", consumerIntroduction=").append(consumerIntroduction);
        sb.append(", consumerLocation=").append(consumerLocation);
        sb.append(", consumerAvator=").append(consumerAvator);
        sb.append(", consumerCreateTime=").append(consumerCreateTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", showStatus=").append(showStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}