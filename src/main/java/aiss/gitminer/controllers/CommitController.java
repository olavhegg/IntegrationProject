package aiss.gitminer.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import aiss.gitminer.model.*;
import aiss.gitminer.repository.CommitRepository;

@RestController
@RequestMapping("/api/commits")
public class CommitController {

    @Autowired 
    CommitRepository repostitory;

    public CommitController(CommitRepository repostitory) {
        this.repostitory = repostitory;
    }

    @GetMapping
    public List<Commit> findAll(){
        return repostitory.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Commit create(@Valid @RequestBody Commit commit){

        Commit _commit = repostitory
            .save(new Commit(commit.getTitle(), commit.getMessage(), commit.getAuthorEmail(), commit.getAuthoredDate()));

        return _commit;
    }
 


}
