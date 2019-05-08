package com.luke.sc.repo.db;

import com.luke.sc.repo.pojo.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ITestRepository extends JpaRepository<Test,Long>, JpaSpecificationExecutor {

}
