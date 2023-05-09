package aiss.GitLabMiner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import aiss.gitminer.model.*;

@Service
public class GitLabClient {
    private final RestTemplate restTemplate;
    private final String gitMinerUrl;

    @Autowired
    public GitLabClient(RestTemplate restTemplate, @Value("${gitminer.url}") String gitMinerUrl) {
        this.restTemplate = restTemplate;
        this.gitMinerUrl = gitMinerUrl;
    }

    public void sendProjects(List<Project> projects) {
        HttpEntity<List<Project>> request = new HttpEntity<>(projects);
        restTemplate.postForObject(gitMinerUrl + "/api/projects", request, Void.class);
    }

    public void sendCommits(List<Commit> commits) {
        HttpEntity<List<Commit>> request = new HttpEntity<>(commits);
        restTemplate.postForObject(gitMinerUrl + "/api/commits", request, Void.class);
    }

    public void sendIssues(List<Issue> issues) {
        HttpEntity<List<Issue>> request = new HttpEntity<>(issues);
        restTemplate.postForObject(gitMinerUrl + "/api/issues", request, Void.class);
    }
}