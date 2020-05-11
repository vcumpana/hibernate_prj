package com.hibernate.dao;

import com.hibernate.entities.Company;
import com.hibernate.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query(value = "select c.projects from Company c where c.id=?1")
    List<Project> getProjectsByCompanyId(Long id);


}
