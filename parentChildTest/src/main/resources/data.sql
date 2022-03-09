--TRUNCATE TABLE zz_parent;
INSERT into zz_parent (parent_id, name) VALUES (4291893511, 'testParent');

--TRUNCATE TABLE zz_child;
INSERT into zz_child (child_id, name, parent_parent_id) VALUES (4291893512, 'c326229c-38a6-46d6-b99f-766d097c063a', 4291893511);
INSERT into zz_child (child_id, name, parent_parent_id) VALUES (4291893513 , '9843bc94-580d-4a45-be92-60453dd472ff', 4291893511);
INSERT into zz_child (child_id, name, parent_parent_id) VALUES (4291893514 , 'f6dfe6a5-1d0b-4289-b40f-17ba6ab6d15b', 4291893511);