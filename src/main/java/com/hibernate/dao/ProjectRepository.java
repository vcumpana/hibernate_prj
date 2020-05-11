package com.hibernate.dao;

import com.hibernate.entities.Employee;
import com.hibernate.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query(value = "select p.employees from Project p where p.projectCode=:code")
    List<Employee> findEmployeesWithProjectCode(@Param("code") String code);

    @Query(value = "from Project p join fetch p.employees")
    List<Project> getProjectsByJavaSkilledEmployees();

    @Query(value = "select c.projects from Company c where c.id=?1")
    List<Project> getProjectsByCompanyId(Long id);
}
