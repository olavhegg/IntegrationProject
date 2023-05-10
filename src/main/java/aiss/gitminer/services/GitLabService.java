package aiss.gitminer.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import aiss.gitminer.model.Commit;
import aiss.gitminer.model.Issue;
import aiss.gitminer.model.Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitLabService {
    private static final String GITLAB_API_BASE_URL = "https://gitlab.com/api/v4/projects/";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public Project getProjectById(Long id) {
        String url = GITLAB_API_BASE_URL + id;
        String response = restTemplate.getForObject(url, String.class);
        Project gitLabProject = null;

        try {
            gitLabProject = objectMapper.readValue(response, Project.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return gitLabProject;
    }

    public List<Commit> getCommitsById(Long id){
        String url = GITLAB_API_BASE_URL + id + "/repository/commits";
        String response = restTemplate.getForObject(url, String.class);
        List<Commit> commits = new ArrayList<>();

        try {
            Commit[] commitArray = objectMapper.readValue(response, Commit[].class);
            commits = Arrays.asList(commitArray);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return commits;
    }

    public List<Issue> getAllIssuesFromGitLabProject(Long projectId) {
        String url = GITLAB_API_BASE_URL + projectId + "/issues";
        String response = restTemplate.getForObject(url, String.class);
        List<Issue> issues = new ArrayList<>();

        try {
            Issue[] issueArray = objectMapper.readValue(response, Issue[].class);
            issues = Arrays.asList(issueArray);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return issues;
    }


}
