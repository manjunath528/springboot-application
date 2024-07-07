package com.app.project.repository;

import com.app.project.entity.UserDailyDietPlan;
import com.app.project.entity.UserDailyExercisePlan;
import com.app.project.entity.UserHealthDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Manjunath Reddy
 */
@Repository
public interface UserDailyExercisePlanRepository extends JpaRepository<UserDailyExercisePlan, Long> {
    @Query("SELECT userDailyExercisePlan from UserDailyExercisePlan userDailyExercisePlan where lower(userDailyExercisePlan.loginId)=lower(:loginId)")
    UserHealthDetails findByLoginId(@Param("loginId") String loginId);

}
