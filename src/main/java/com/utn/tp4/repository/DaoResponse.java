package com.utn.tp4.repository;

import com.utn.tp4.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoResponse extends JpaRepository<Response,Long>{

   /* @PersistenceContext
    private EntityManager entityManager;*/

    @Query("Select soName,browserName from Response")
    List<?> getResponses();

    @Query("Select browserName,count(id) as cant from Response group by(browserName) order by cant desc")
    List<?> popularBrowser();

    @Query("Select soName,count(id) as cant from Response group by(soName) order by cant desc")
    List<?> popularSo();

    @Query("Select combination,count(id) as cant from Response group by(combination) order by cant desc")
    List<?> popularCombination();

}
