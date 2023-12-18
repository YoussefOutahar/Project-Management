package com.yorastd.projectmanagement.Repositories.Budget;

import com.yorastd.projectmanagement.Models.Budget.HumanResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanResourceRepository extends JpaRepository<HumanResource,Integer> {
}
