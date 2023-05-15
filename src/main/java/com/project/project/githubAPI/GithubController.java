package com.project.project.githubAPI;

import org.kohsuke.github.GHIssue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/issue")
public class GithubController {

    private GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    // view(main.html)
    @GetMapping("")
    public String getIssues() throws IOException {
        return "main";
    }

    // 차트
    @GetMapping("/chart")
    @ResponseBody
    public Map<String, Map<String, Integer>> getChartData() throws IOException {
        return githubService.getIssueCounts("mobigen/IRIS-Analyzer");
    }
    // 라벨 필터링
    @GetMapping("/label")
    @ResponseBody
    public List<String> getFilteredLabels(){
        return githubService.getIssueLabels();
    }
    // 테이블
    @GetMapping("/list" )
    @ResponseBody
    public Map<String, Object> getFilteredIssues(@RequestParam(value = "state", defaultValue = "") String state,
                                                 @RequestParam(value = "labels", defaultValue = "") String labels,
                                                 @RequestParam(value = "startDate", defaultValue = "") String startDate,
                                                 @RequestParam(value = "endDate", defaultValue = "") String endDate,
                                                 @RequestParam(value = "page", defaultValue = "1") int page) {

        return githubService.getIssueTable(state, labels, startDate, endDate, page);
    }
}
