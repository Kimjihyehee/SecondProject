//package com.project.project.githubAPI;
//
//import org.kohsuke.github.GHIssue;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//// 디티오 역할
//    public class GithubIssue{
//
//        private int number;
//        private List<String> labels;
//        private String title;
//        private String state;
//        private LocalDateTime created_at;
//        private String resister;
//
//        GithubIssue githubIssue = new GithubIssue();
//
//        githubIssue.setTitle(ghIssue.getTitle());
//        githubIssue.setState(ghIssue.getState());
//        githubIssue.setCreated_at(ghIssue.getCreatedAt().toLocalDateTime());
//        githubIssue.setResister(ghIssue.getUser().getLogin());
//        githubIssue.setNumber(ghIssue.getNumber());
//        githubIssue.setLabels(ghIssue.getLabels().stream()
//                .map(GHLabel::getName)
//                .collect(Collectors.toList()));
//
//        public int getNumber() {
//            return number;
//        }
//
//        public void setNumber(int number) {
//            this.number = number;
//        }
//
//        public List<String> getLabels() {
//            return labels;
//        }
//
//        public void setLabels(List<String> labels) {
//            this.labels = labels;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public String getState() {
//            return state;
//        }
//
//        public void setState(String state) {
//            this.state = state;
//        }
//
//        public LocalDateTime getCreated_at() {
//            return created_at;
//        }
//
//        public void setCreated_at(LocalDateTime created_at) {
//            this.created_at = created_at;
//        }
//
//        public String getResister() {
//            return resister;
//        }
//
//        public void setResister(String resister) {
//            this.resister = resister;
//        }
//    }