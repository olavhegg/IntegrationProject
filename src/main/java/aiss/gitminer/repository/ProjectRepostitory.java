package aiss.gitminer.repository;

import aiss.gitminer.model.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepostitory extends JpaRepository<Project,Long>{
    Page<Project> findByName(String name, Pageable pageable);
}
