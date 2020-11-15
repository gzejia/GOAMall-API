package com.nuon.goamall.model;

import com.nuon.goamall.util.MapAndJson;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Map;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
@Where(clause = "delete_time is null")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String openid;

    private String nickname;

    private String email;

    private String mobile;

    private String password;

    private Long unifyUid;

//    private String group;

    @Convert(converter = MapAndJson.class)
    private Map<String, Object> wxProfile;
}