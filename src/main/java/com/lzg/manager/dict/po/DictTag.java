package com.lzg.manager.dict.po;


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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_dict_tag")
@org.hibernate.annotations.Table(appliesTo = "t_dict_tag", comment = "字典标签表")
@EntityListeners(AuditingEntityListener.class)
public class DictTag {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = false, columnDefinition = "varchar(32) comment '主键ID'")
    private String id;

    @Column(name = "TAG_CODE", nullable = false, columnDefinition = "varchar(50) comment '标签编码'")
    private String tagCode;

    @Column(name = "TAG_NAME", nullable = false, columnDefinition = "varchar(50) comment '标签名称'")
    private String tagName;

    @Column(name = "REMARK", nullable = false, columnDefinition = "varchar(50) comment '备注'")
    private String remark;

    @Column(name = "CREATE_TIME", columnDefinition = "datetime comment '创建时间'")
    @CreatedDate
    private Date createTime;

    @Column(name = "UPDATE_TIME", columnDefinition = "datetime comment '更新时间'")
    @LastModifiedDate
    private Date updateTime;
}
