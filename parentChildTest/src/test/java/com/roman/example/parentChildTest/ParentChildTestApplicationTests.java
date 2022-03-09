package com.roman.example.parentChildTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.roman.example.parentChildTest.domain.Child;
import com.roman.example.parentChildTest.domain.Parent;
import com.roman.example.parentChildTest.repository.ChildRepository;
import com.roman.example.parentChildTest.repository.ParentRepository;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest
@Rollback(false)
class ParentChildTestApplicationTests {

    @Autowired
    ParentRepository parentRepo;

    @Autowired
    ChildRepository childRepo;

    @Transactional
	@Test
    void test01_addChild() {
        System.out.println("########################################################## ADDING CHILD");

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

        //parent.addChild(child);
        parent.getChildren()
                .add(child);

        childRepo.save(child);
        parentRepo.save(parent);

        final Long childId = child.getId();
        assertNotNull(childId);

        // And making sure that child was created
        final Child childVerify = childRepo.getById(childId);
        assertNotNull(childVerify);

        // Finally making sure that parent.children are not lazy initialized
        assertFalse(Hibernate.isInitialized(parent.getChildren()));

        System.out.println("########################################################## DONE ADDING CHILD");

	}

    @Transactional
    @Test
    void test02_removeChild() {
        System.out.println("########################################################## REMOVING CHILD");

        // Fetching existing parent by id
        // comes from predefined dataset in src/main/resources/data.sql
        final Long parentId = 4291893511L;
        final Parent parent = parentRepo.getById(parentId);

        assertNotNull(parent);

        // Fetching child we want to remove by id
        final Long childToRemoveId = 4291893513L;
        final Child childToRemove = childRepo.getById(childToRemoveId);

        assertNotNull(childToRemove);

        // Now trying to remove a child (the goal is not to load p.children in memory)
        parent.getChildren()
                .remove(childToRemove);

        childRepo.delete(childToRemove);
        parentRepo.save(parent);

        childRepo.flush();

        System.out.println("Verification:");
        // And making sure that child was created
        final Optional<Child> childVerify = childRepo.findById(childToRemoveId + 100);
        assertTrue(childVerify.isEmpty());

        // Finally making sure that parent.children are not lazy initialized
        assertFalse(Hibernate.isInitialized(parent.getChildren()));

        System.out.println("########################################################## DONE REMOVING CHILD");

    }

}
