/* Copyright (C) PublicRelay, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * The work belongs to the author's employer under work made for hire principles.
 */

package com.roman.example.parentChildTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.roman.example.parentChildTest.domain.Child;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {

}
