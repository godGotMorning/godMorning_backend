package com.godMorning_backend.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity //entity라는 것을 명시하는 어노테이션, table어노테이션 따로 안 하면 클래스 이름이 테이블로 생성된다
public class User {
    @Id //기본키 지정
    @GeneratedValue(strategy = GenerationType.AUTO)//mysql에서 auth_increment가능하게 함
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String nickname;
    private String roles;
    private String providerId;
    private String provider;

    public List<String> getRoleList(){ //enum 대신 권한 부여하도록
        if(this.roles.length() >0 ){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }




}