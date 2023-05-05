//package com.project.project.githubAPI;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import org.apache.catalina.Group;
//import org.apache.catalina.Role;
//import org.apache.catalina.User;
//import org.apache.catalina.UserDatabase;
//
//import java.util.Iterator;
//@JsonIgnoreProperties(ignoreUnknown = true)
//public class GithubUser implements User {
//    private String id;
//    private String username;
//    private String password;
//    private String fullName;
//
////    public GithubUser(String username, String password, String fullName) {
////        this.username = username;
////        this.password = password;
////        this.fullName = fullName;
////    }
//
//    public GithubUser() {
//        // 기본 생성자를 추가하여 Jackson이 객체를 생성할 수 있도록 합니다.
//    }
//    public String getId() {
//        return id;
//    }
//    public String getUsername() {
//        return username;
//    }
//    public void setUsername(String username) {
//        this.username = username;
//    }
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    @Override
//    public String getFullName() {
//        return null;
//    }
//
//    @Override
//    public void setFullName(String s) {
//
//    }
//
//    @Override
//    public Iterator<Group> getGroups() {
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public void setPassword(String s) {
//
//    }
//
//    @Override
//    public Iterator<Role> getRoles() {
//        return null;
//    }
//
//    @Override
//    public UserDatabase getUserDatabase() {
//        return null;
//    }
//
//
//
//
//    @Override
//    public void addGroup(Group group) {
//
//    }
//
//    @Override
//    public void addRole(Role role) {
//
//    }
//
//    @Override
//    public boolean isInGroup(Group group) {
//        return false;
//    }
//
//    @Override
//    public boolean isInRole(Role role) {
//        return false;
//    }
//
//    @Override
//    public void removeGroup(Group group) {
//
//    }
//
//    @Override
//    public void removeGroups() {
//
//    }
//
//    @Override
//    public void removeRole(Role role) {
//
//    }
//
//    @Override
//    public void removeRoles() {
//
//    }
//
//    @Override
//    public boolean equals(Object another) {
//        return false;
//    }
//
//    @Override
//    public String toString() {
//        return null;
//    }
//
//    @Override
//    public int hashCode() {
//        return 0;
//    }
//
//    @Override
//    public String getName() {
//        return null;
//    }
//}
