package aiss.gitminer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aiss.gitminer.model.Commit;
import aiss.gitminer.model.Issue;
import aiss.gitminer.model.Project;
import aiss.gitminer.repository.ProjectRepostitory;
import aiss.gitminer.services.GitLabService;

@RestController
@RequestMapping("/api/gitlab")
public class GitLabController {

    @Autowired
    private GitLabService gitLabService;

    @Autowired 
    private ProjectRepostitory projectRepostitory;

    @PostMapping
    public Project getProjects(){
        long id = 3978569;
        return gitLabService.getProjectById(id);
    }

    @GetMapping("/projects/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return gitLabService.getProjectById(id);
    }
    
    @PostMapping("/projects/{id}")
    public Project createProjectById(@PathVariable Long id){
        Project project = gitLabService.getProjectById(id);
        List<Commit> commits = gitLabService.getCommitsById(id);
        project.setCommits(commits);
        List<Issue> issues = gitLabService.getAllIssuesFromGitLabProject(id);
        project.setIssues(issues);
        projectRepostitory.save(project);
        return project;
    }

}