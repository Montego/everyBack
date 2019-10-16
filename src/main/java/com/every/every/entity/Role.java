package com.every.every.entity;

//import org.springframework.security.core.GrantedAuthority;


public enum Role{
        USER("just some user's rights"), ADMIN("all rights (set roles to users,list of users"), MANAGER("content");

        private String description;

        Role(String description){
            this.description = description;
        }

//    @Override
//    public String getAuthority() {
//        return name();
//    }
}
