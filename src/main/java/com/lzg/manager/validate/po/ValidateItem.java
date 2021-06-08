package com.lzg.manager.validate.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author luoZhaoGong
 * @date 2021/6/2
 * @Description:
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_validate")
@org.hibernate.annotations.Table(appliesTo = "t_validate", comment = "校验表")
@EntityListeners(AuditingEntityListener.class)
public class ValidateItem {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = false, columnDefinition = "varchar(32) comment '主键ID'")
    private String id;

    @Column(name = "CREATE_TIME", columnDefinition = "datetime comment '创建时间'")
    @CreatedDate
    private Date createTime;

    @Column(name = "UPDATE_TIME", columnDefinition = "datetime comment '更新时间'")
    @LastModifiedDate
    private Date updateTime;

    @Column(name = "CREATE_BY", columnDefinition = "varchar(32) comment '创建人'")
    private String createBy;

    @Column(name = "UPDATE_BY", columnDefinition = "varchar(32) comment '更新人'")
    private String updateBy;
}
