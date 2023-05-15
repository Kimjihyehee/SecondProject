package com.project.project.githubAPI;

import org.kohsuke.github.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GithubService {

    // 차트
    public Map<String, Map<String, Integer>> getIssueCounts(String repositoryUrl) throws IOException {
        String password = "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";
        GitHub github = new GitHubBuilder().withOAuthToken(password).build();
        GHRepository repository = github.getRepository(repositoryUrl);

        Map<String, Integer> userCounts = new HashMap<>();
        Map<String, Integer> assigneeCounts = new HashMap<>();
        Map<String, Integer> dailyCounts = new HashMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (GHIssue issue : repository.getIssues(GHIssueState.ALL)) {
            String userLogin = issue.getUser() != null ? issue.getUser().getLogin() : null;
            if (userLogin != null) {
                int count = userCounts.getOrDefault(userLogin, 0);
                userCounts.put(userLogin, count + 1);
            }

            String assigneeLogin = issue.getAssignee() != null ? issue.getAssignee().getLogin() : null;
            if (assigneeLogin != null) {
                int count = assigneeCounts.getOrDefault(assigneeLogin, 0);
                assigneeCounts.put(assigneeLogin, count + 1);
            }

            Date createdAt = issue.getCreatedAt();
            if (createdAt != null) {
                String dateStr = sdf.format(createdAt);
                int count = dailyCounts.getOrDefault(dateStr, 0);
                dailyCounts.put(dateStr, count + 1);
            }
        }

        Map<String, Map<String, Integer>> result = new HashMap<>();
        result.put("userCounts", userCounts);
        result.put("assigneeCounts", assigneeCounts);
        result.put("dailyCounts", dailyCounts);

        return result;
    }

// 테이블=====================================================================================
    // 전체 데이터 추출
    public Map<String, Object> getIssueTable(String state, String labels, String startDate, String endDate, int page) {
        String baseUrl = "https://api.github.com/search/issues";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("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");
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // build query string
        StringBuilder queryBuilder = new StringBuilder("?q=repo:mobigen/IRIS-Analyzer+type:issue,pr");

        if (!"".equals(state)) { //null과 empty
            queryBuilder.append("+state:").append(state);
        }
        if (!"".equals(labels)) {
            queryBuilder.append("+label:").append(labels);
        }
        if (!"".equals(startDate) && !"".equals(endDate)) {
            queryBuilder.append("+created:").append(startDate).append("..").append(endDate);
        }

        String query = queryBuilder.toString();

        String url = baseUrl + query + "&page=" + page+ "&per_page=" + 10;


        ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Map.class);
        Map<String, Object> responseMap = responseEntity.getBody();
        Map<String, Object> results = new HashMap<>();
        results.put("total_count", responseMap.get("total_count"));
        //JSON 응답에서 "items" 키에 해당하는 값을 가져와서 이를 List<Map<String, Object>> 형태로 변환하여 반환
        List<Map<String, Object>> issues = (List<Map<String, Object>>) responseMap.get("items");
        List<Map<String, Object>> issueList = new ArrayList<>();
        for (Map<String, Object> issue : issues) {
            Map<String, Object> map = new HashMap<>();
            map.put("number", issue.get("number"));
            map.put("title", issue.get("title"));
            map.put("username", Optional.ofNullable(issue.get("user")).map(user -> ((Map<String, Object>) user).get("login")).orElse("unknown"));
            map.put("createdAt", issue.get("created_at"));
            map.put("closedAt", Optional.ofNullable(issue.get("closed_at")).orElse(LocalDateTime.now()));
            map.put("state", issue.get("state"));
            map.put("labels", Optional.ofNullable(issue.get("labels")).map(labelsList -> ((List<Map<String, Object>>) labelsList).stream().map(label -> label.get("name")).collect(Collectors.toList())).orElse(Collections.emptyList()));
            issueList.add(map);
            results.put("list", issueList);
        }
        return results;
    }

    // 라벨 필터 추출
    public List<String> getIssueLabels() {
        //
        String Url = "https://api.github.com/repos/mobigen/IRIS-Analyzer/labels";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("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");
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Map[]> responseEntity = restTemplate.exchange(Url, HttpMethod.GET, requestEntity, Map[].class);
        Map[] responseMapArray = responseEntity.getBody();

        List<String> labelList = new ArrayList<>();// label값 담기

        if (responseMapArray != null) {
            for (Map<String, Object> label : responseMapArray) {
                String labelName = (String) label.get("name");
                labelList.add(labelName);
            }
        }

        return labelList;
    }
}

