package aiss.gitminer.repository;

import aiss.gitminer.model.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitRepository extends JpaRepository<Commit,Long>{

}
