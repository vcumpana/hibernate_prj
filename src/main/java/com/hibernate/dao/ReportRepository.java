package com.hibernate.dao;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReportRepository {

  @Autowired
  private EntityManager entityManager;



}
