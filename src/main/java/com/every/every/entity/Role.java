package com.every.every.entity;

public enum Role {
        USER("just some user's rights"), ADMIN("all rights"), MANAGER("content");

        private String description;

        Role(String description){
            this.description = description;
        }
}
