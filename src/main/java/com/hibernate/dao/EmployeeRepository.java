package com.hibernate.dao;

import com.hibernate.entities.Employee;
import com.hibernate.enums.SkillType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//    @Query(value = "from Employee e join fetch e.projects p where p.projectCode =:code")
//    List<Employee> findEmployeesWithProjectCode(@Param("code") String code);

    @Query(value = "select e.* from employee e join employee_projects ep on e.id = ep.employee_id" +
        " join project p on ep.project_id=p.id where p.project_code =:code", nativeQuery = true)
    List<Employee> findEmployeesWithProjectCode(@Param("code") String code);

//    @Query(value = )
//    @Modifying
//    Long updateEmployeeById(Long employeeId, );

    @Query(value = "from Employee e")
    List<Employee> getEmployeesBySkillType(SkillType soft);

    @Query(value = "from Employee e join fetch e.skills s ")
    List<Employee> getEmployeesBySkillName(String presentation);
}
