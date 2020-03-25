package com.test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.test.model.Role;
import com.test.model.Users;
import com.test.repository.RoleRepository;
import com.test.repository.UserRepository;

@Service
public class UserService  implements UserDetailsService{
	@Autowired UserRepository userRepo;
	@Autowired RoleRepository roleRepo;
	
	
	public Users save(Users user){
		System.out.println("In Service");
		return userRepo.save(user);
	}
	
	public Role getRoleById(int roleId){
		return roleRepo.findById(roleId);
	}
	
	public List<Role> getAllRoles(){
		return (List<Role>)(roleRepo.findAll());
	}
	
	public boolean existByEmail(String email){
		return userRepo.existsByUserEmail(email);
	}
	public boolean existByUsername(String userName){
		return userRepo.existsByUserName(userName);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
			Users users=userRepo.findByUserName(userName);
			Role roles=roleRepo.findById(users.getRoles());
			List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(roles.getRoleName()));
			User user=new User(users.getUserName(),users.getPassword(),authorities);
			return user;
	}
	

//	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
