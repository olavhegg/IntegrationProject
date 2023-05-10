package aiss.GitLabMiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import aiss.gitminer.GitMinerClient;

@RestController
@RequestMapping("/apipath")
public class GitLabMiner {

    @Autowired
    private GitLabClient gitLabClient; // Assuming you have created a GitLab client class

    @Autowired
    private GitMinerClient gitMinerClient; // Assuming you have created a GitMiner client class

    @PostMapping("/{id}")
    public ResponseEntity<String> mineGitLab(@PathVariable String id,
        @RequestParam(defaultValue = "2") int sinceCommits,
        @RequestParam(defaultValue = "20") int sinceIssues,
        @RequestParam(defaultValue = "2") int maxPages) {
        
        try {
            // Read data from GitLab REST API using GitLab API client
            // process data according to data model
            // send processed data to GitMiner microservice using GitMiner API client
            // return appropriate HTTP response
            
            
            return new ResponseEntity<>("GitLab data mining completed successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to mine GitLab data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Implement equivalent read-only operation for testing purposes
    @GetMapping("/{id}")
    public ResponseEntity<String> getGitLabData(@PathVariable String id,
        @RequestParam(defaultValue = "2") int sinceCommits,
        @RequestParam(defaultValue = "20") int sinceIssues,
        @RequestParam(defaultValue = "2") int maxPages) {
        
        try {
            final String uri = "https://gitlab.com/api/v4/projects/"+id;

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);

            System.out.println(result);
            // Read data from GitLab REST API using GitLab API client
            // process data according to data model
            // return the search results without sending them to GitMiner microservice
            return new ResponseEntity<>("GitLab data retrieved successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve GitLab data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}