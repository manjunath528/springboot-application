package com.app.project.repository;


import com.app.project.entity.UserDailyPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDailyPlanRepository extends JpaRepository<UserDailyPlan, Long> {
    @Query("SELECT userDailyPlan from UserDailyPlan userDailyPlan where lower(userDailyPlan.loginId)=lower(:loginId)")
    UserDailyPlan findByLoginId(@Param("loginId") String loginId);
}
