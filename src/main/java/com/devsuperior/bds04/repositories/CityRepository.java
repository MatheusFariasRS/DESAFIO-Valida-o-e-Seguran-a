package com.devsuperior.bds04.repositories;

import com.devsuperior.bds04.entities.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query("""
    SELECT obj
    FROM City obj
    WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%'))
    ORDER BY obj.name
""")
    Page<City> searchByName(String name, Pageable pageable);
}
