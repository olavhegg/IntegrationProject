package aiss.gitminer.controllers;

import aiss.gitminer.model.*;
import aiss.gitminer.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired 
    ProjectRepostitory repostitory;

    public ProjectController(ProjectRepostitory repostitory) {
        this.repostitory = repostitory;
    }

    

    @GetMapping()
    public Page<Project> getProjects(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortOrder,
            @RequestParam(required = false) String projectName
    ){
        Sort sort = sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        PageRequest pageable = PageRequest.of(page, size, sort);

        if(projectName != null){
            
            return repostitory.findByName(projectName,pageable);
        }

        return repostitory.findAll(pageable);
        
        
       


    
    }


    // @ResponseStatus(HttpStatus.CREATED)
    // @PostMapping
    // public Project create(@Valid @RequestBody Project project){
    //     Project _project = repostitory
    //         .save(new Project(project.getName(),project.getWeb_url()));
    //     return _project;
    // }

    @GetMapping("/page1")
    public List<Project> pagination(){
        return repostitory.findAll().subList(0, 2);
    }

    @GetMapping("/sorted")
    public List<Project> sorting(){
        return repostitory.findAll();
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public List getResults(@RequestParam("numPage") int pages ){
        return repostitory.findAll().subList(0, pages);
    }


}
