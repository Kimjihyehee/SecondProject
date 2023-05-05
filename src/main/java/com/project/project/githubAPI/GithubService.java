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
import java.util.List;

@Service
@SpringBootApplication
public class GithubService {
//    private static final String GITHUB_API_BASE_URL = "https://api.github.com"; // 호출할 API의 URL 지정
//    private RestTemplate restTemplate; // restTemplate를 이용해 외부 API에서 가져온 JSON 데이터를 자바 객체로 매핑하도록 수행
//    private ObjectMapper objectMapper;

//    public GithubService() {
//        this.restTemplate = new RestTemplate();
//        this.objectMapper = new ObjectMapper();
//    }

//    public GithubUser getUser(String username) throws JsonProcessingException {
//        String url = GITHUB_API_BASE_URL + "/users/" + username;
//        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//        GithubUser user = objectMapper.readValue(response.getBody(), GithubUser.class);
//        return user;
//
//    }

    public List<GHIssue> getIssues(String repositoryUrl) throws IOException {
        String password = "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";
        GitHub github = new GitHubBuilder().withOAuthToken(password).build(); //withOAuthToken() : Token을 사용하여 Github API에 로그인
        GHRepository repository = github.getRepository(repositoryUrl); //github.getRepository(repositoryUrl) : repositoryUrl로 전달된 레포지토리의 GHRepository 객체를 가져옴
        return repository.getIssues(GHIssueState.ALL); //getIssues() : repository 객체에 연결된 레포지토리의 모든 이슈 목록을 가져옴(GHIssueState.ALL 파라미터를 사용하여 모든 상태의 이슈를 가져옴)
    }


}
