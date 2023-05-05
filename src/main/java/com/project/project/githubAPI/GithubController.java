package com.project.project.githubAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.catalina.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.kohsuke.github.GHIssue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@SpringBootApplication
public class GithubController {

    private GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }



//    @GetMapping("/issues/mobigen/IRIS-Analyzer")
//    public ModelAndView getIssues(ModelAndView mv) throws IOException {
//        String repositoryUrl = "mobigen/IRIS-Analyzer";
//        List<GHIssue> issues = githubService.getIssues(repositoryUrl);
//        System.out.println(issues.toString());
//        mv.addObject("issues", issues);
//
//        mv.setViewName("main");
//        return mv;
//    }
//
//    @RequestMapping("/test2")
//    public String kimjihyebabo(){
//        System.out.println("test");
//        return "index";
//    }
//    @GetMapping("/test")
//    public ModelAndView getIssueData(ModelAndView mv) {
//        // Owner와 Repository 이름
//        String owner = "mobigen";
//        String repo = "IRIS-Analyzer";
//
//        // API 요청 URL 생성
//        String url = String.format("https://api.github.com/repos/%s/%s/issues", owner, repo);
//
//        // API 요청 보내기
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "token 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"); // 개인토크값 헤더에 담기
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);
//
//        if (response.getStatusCode() == HttpStatus.OK) {
//            // JSON 데이터 파싱
//            String responseBody = response.getBody();
//            // 필요한 데이터 추출
//            // 예를 들어, 이슈 생성일과 이슈 상태 데이터를 추출해보겠습니다.
//            List<String> dates = new ArrayList<>();
//            List<String> statuses = new ArrayList<>();
//
//            JSONArray jsonArray = new JSONArray(responseBody);
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                dates.add(jsonObject.getString("created_at"));
//                statuses.add(jsonObject.getString("state"));
//            }
//
//            System.out.println("dates : " +dates.toString());
//            System.out.println("statuses : " +statuses.toString());
//            // 모델에 데이터 추가
//            mv.addObject("dates", dates);
//            mv.addObject("statuses", statuses);
//            mv.setViewName("main");
//        }
//
//        // Thymeleaf 템플릿 파일 리턴
//        return mv;
//    }
@GetMapping("/test")
public ModelAndView getIssueData(ModelAndView mv) {
    // Owner와 Repository 이름
    String owner = "mobigen";
    String repo = "IRIS-Analyzer";

    // API 요청 URL 생성
    String url = String.format("https://api.github.com/repos/%s/%s/issues", owner, repo);

    // API 요청 보내기
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "token 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"); // 개인토크값 헤더에 담기
    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);

    if (response.getStatusCode() == HttpStatus.OK) {
        // JSON 데이터 파싱
        String responseBody = response.getBody();

        // 이슈 상태별로 분류
        Map<String, List<Map<String, String>>> issuesByStatus = new HashMap<>();
        JSONArray jsonArray = new JSONArray(responseBody);
        if (jsonArray.length() > 0) {
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            // 처리할 코드 작성
        }

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String status = jsonObject.getString("state");
            String milestone;

            Map<String, String> issueData = new HashMap<>();
            if (jsonObject.isNull("milestone")) {
                milestone = "No milestone assigned";
            } else {
                milestone = jsonObject.getJSONObject("milestone").getString("title");
                issueData.put("milestone_created_at", jsonObject.getJSONObject("milestone").getString("created_at"));
                issueData.put("milestone_closed_at", jsonObject.getJSONObject("milestone").getString("closed_at"));
            }
            issueData.put("number",String.valueOf(jsonObject.getInt("number")));
            issueData.put("title", jsonObject.getString("title"));
            issueData.put("user", jsonObject.getJSONObject("user").getString("login"));

            issueData.put("state", jsonObject.getString("state"));
            if (jsonObject.getJSONArray("labels").length() > 0) {
                issueData.put("label_name", jsonObject.getJSONArray("labels").getJSONObject(0).getString("name"));
            }
            if (!issuesByStatus.containsKey(status)) {
                issuesByStatus.put(status, new ArrayList<>());
            }
            issuesByStatus.get(status).add(issueData);
        }

        // 모델에 데이터 추가
        mv.addObject("issuesByStatus", issuesByStatus);
        mv.setViewName("main");
    }

    // Thymeleaf 템플릿 파일 리턴
    return mv;
}
}