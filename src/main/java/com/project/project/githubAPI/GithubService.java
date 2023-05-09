package com.project.project.githubAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.kohsuke.github.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@SpringBootApplication
public class GithubService {

    public List<IssueData> getIssues(String repositoryUrl) throws IOException {
        String password = "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";
        GitHub github = new GitHubBuilder().withOAuthToken(password).build(); //withOAuthToken() : Token을 사용하여 Github API에 로그인
        GHRepository repository = github.getRepository(repositoryUrl); //github.getRepository(repositoryUrl) : repositoryUrl로 전달된 레포지토리의 GHRepository 객체를 가져옴

        List<IssueData> issueDataList = new ArrayList<>();
        for (GHIssue issue : repository.getIssues(GHIssueState.ALL)) { // 전체 데이터를 가져와 반복
            IssueData issueData = new IssueData(); // 필요한 데이터만 IssueData에 저장하도록 함.
            issueData.setNumber(issue.getNumber());
            issueData.setTitle(issue.getTitle());
            issueData.setUser(issue.getUser() != null ? issue.getUser().getLogin() : null);
            issueData.setAssignee(issue.getAssignee() != null ? issue.getAssignee().getLogin() : null);
            issueData.setCreateDate(issue.getCreatedAt());
            issueData.setClosedDate(issue.getClosedAt() != null ? issue.getClosedAt() : null);
            issueData.setState(issue.getState().toString());
            //labels 값 list 로 담아서 넣기
            List<String> labelNames = new ArrayList<>();
            for (GHLabel label : issue.getLabels()) {
                labelNames.add(label.getName());
            }
            issueData.setLabels(labelNames); // issueData에 값넣기

            issueDataList.add(issueData);
        }

        return issueDataList;
    }
}
