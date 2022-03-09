Codebase to try various suggestions for the following StackOverflow question: 
https://stackoverflow.com/questions/71389597/is-it-possible-in-hibernate-to-add-remove-children-of-a-onetomany-relationship-w/71391778#71391778

Requirements: 
* java11 or higher
* maven

To run the tests:
```
    bash ./mvnw test 
```

Current Hibernate Log:

########################################################## RUNNING TEST CASE
Hibernate: select parent0_.parent_id as parent_i1_1_0_, parent0_.name as name2_1_0_ from zz_parent parent0_ where parent0_.parent_id=?
Hibernate: select children0_.parent_parent_id as parent_p3_0_0_, children0_.child_id as child_id1_0_0_, children0_.child_id as child_id1_0_1_, children0_.name as name2_0_1_, children0_.parent_parent_id as parent_p3_0_1_ from zz_child children0_ where children0_.parent_parent_id=?
Hibernate: call next value for hibernate_sequence
########################################################## COMPLETING TEST CASE 
