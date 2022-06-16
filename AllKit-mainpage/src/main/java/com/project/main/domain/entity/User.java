package com.project.main.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.crypto.password.PasswordEncoder;




import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userid;
	private String useremail;
	private String password;
	private String username;
	private String userphone;
	private String usergender;
	private String useraddr;
	private String userbirth;
	private Boolean enabled;
	
	@ManyToMany  
    @JoinTable(
            name = "userrole",
            joinColumns = @JoinColumn(name = "userid"), //user 테이블
            inverseJoinColumns = @JoinColumn(name = "roleid")) //조인될 상태 테이블 role
    private List<Role> roles = new ArrayList<>();

	
//	@Builder
//	public User(Integer userid, String password, String username,String useremail,String userphone,String usergender, String useraddr, String userbirth) {
//		this.userid = userid;
//		this.password = password;
//		this.username = username;
//		this.useremail = useremail;
//		this.userphone = userphone;
//		this.usergender = usergender;
//		this.useraddr = useraddr;
//		this.userbirth = userbirth;
//		
//	}
	
 
	

}
