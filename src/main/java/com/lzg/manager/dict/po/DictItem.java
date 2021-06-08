package com.lzg.manager.dict.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_dict")
@org.hibernate.annotations.Table(appliesTo = "t_dict", comment = "字典表")
@EntityListeners(AuditingEntityListener.class)
public class DictItem {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = false, columnDefinition = "varchar(32) comment '主键ID'")
    private String id;

    @Column(name = "TAG_CODE", nullable = false, columnDefinition = "varchar(50) comment '标签编码'")
    private String tagCode;

    @Column(name = "REMARK", columnDefinition = "varchar(50) comment '备注'")
    private String remark;

    @Column(name = "NAME", nullable = false, columnDefinition = "varchar(100) comment '键name'")
    private String name;

    @Column(name = "VALUE", nullable = false, columnDefinition = "varchar(100) comment '值value'")
    private String value;

    @Column(name = "ORDER_INDEX", columnDefinition = "int comment '排序序号'")
    private Integer orderIndex;

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
