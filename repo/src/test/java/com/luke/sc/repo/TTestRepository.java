package com.luke.sc.repo;

import com.luke.sc.repo.db.ITestRepository;
import com.luke.sc.repo.pojo.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=App.class)
public class TTestRepository {

    @Resource
    private ITestRepository repository ;



    @org.junit.Test
    public void save(){
        Test t = new Test() ;
        t.setName("luke");
        t.setAge(36);
        this.repository.save(t) ;
    }

}
