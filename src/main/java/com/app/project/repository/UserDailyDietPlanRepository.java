package com.app.project.repository;


import com.app.project.entity.UserDailyDietPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDailyDietPlanRepository extends JpaRepository<UserDailyDietPlan, Long> {
    @Query("SELECT userDailyDietPlan from UserDailyDietPlan userDailyDietPlan where lower(userDailyDietPlan.loginId)=lower(:loginId)")
    UserDailyDietPlan findByLoginId(@Param("loginId") String loginId);
}
