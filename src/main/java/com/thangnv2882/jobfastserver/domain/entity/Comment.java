package com.thangnv2882.jobfastserver.domain.entity;//package com.thangnv2882.jobfastserver.domain.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.thangnv2882.jobfastserver.domain.entity.base.AbstractAuditingEntity;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "comments")
//public class Comment extends AbstractAuditingEntity {
//
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String contentComment;
//
////    link to table user
////    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
////    @JoinColumn(name = "user_id")
////    private User user;
//
//    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
//    @JoinColumn(name = "job_id")
//    private Job job;
//
////    link to table comment
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commentParent")
//    @JsonIgnore
//    private Set<Comment> commentChilds = new HashSet<>();
//
//    //    link to table comment
//    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
//    @JoinColumn(name = "comment_parent_id")
//    private Comment commentParent;
//
//
//}
