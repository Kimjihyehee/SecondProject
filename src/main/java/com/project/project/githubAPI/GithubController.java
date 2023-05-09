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

    // 페이지
    @GetMapping("/IRIS_Issues_page")
    public ModelAndView getIssues(ModelAndView mv) throws IOException {
        String repositoryUrl = "mobigen/IRIS-Analyzer";
        List<IssueData> issues = githubService.getIssues(repositoryUrl);
        System.out.println(issues);
        mv.addObject("issues", issues);
        mv.setViewName("main");
        return mv;
    }

}