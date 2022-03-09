package com.roman.example.parentChildTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.roman.example.parentChildTest.domain.Child;
import com.roman.example.parentChildTest.domain.Parent;
import com.roman.example.parentChildTest.repository.ChildRepository;
import com.roman.example.parentChildTest.repository.ParentRepository;

@SpringBootTest
@Rollback(false)
class ParentChildTestApplicationTests {

    @Autowired
    ParentRepository parentRepo;

    @Autowired
    ChildRepository childRepo;

    @Transactional
	@Test
    void addChildParent() {
        System.out.println("########################################################## RUNNING TEST CASE");

        // Fetching existing parent by id
        // comes from predefined dataset in src/main/resources/data.sql
        final Long parentId = 4291893511L;
        final Parent parent = parentRepo.getById(parentId);

        assertNotNull(parent);

        // Now trying to add a child (the goal is not to load p.children in memory)

        final Child child = new Child();
        child.setParent(parent);
        child.setName(UUID.randomUUID()
                .toString());

        parent.addChild(child);

        childRepo.save(child);
        parentRepo.save(parent);

        final Long childId = child.getId();
        assertNotNull(childId);

        // And making sure that child was created
        final Child childVerify = childRepo.getById(childId);
        assertNotNull(childVerify);

        System.out.println("########################################################## COMPLETING TEST CASE");

	}

}
