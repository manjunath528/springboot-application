package com.app.project.repository;


import com.app.project.entity.UserDailyDietPlan;
import com.app.project.entity.UserDailyExercisePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDailyPlanRepository extends JpaRepository<UserDailyExercisePlan, Long> {
    @Query("SELECT userDailyExercisePlan from UserDailyExercisePlan userDailyExercisePlan where lower(userDailyExercisePlan.loginId)=lower(:loginId)")
    UserDailyDietPlan findByLoginId(@Param("loginId") String loginId);
}
