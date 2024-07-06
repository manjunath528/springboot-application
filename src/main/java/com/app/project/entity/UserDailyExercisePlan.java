package com.app.project.entity;

import javax.persistence.*;

public class UserDailyExercisePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence_user_health_details")
    @SequenceGenerator(name = "id_sequence_user_health_details", sequenceName = "sequence_user_health_details", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "exercise_name")
    private String exerciseName;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "calories_burned")
    private Long caloriesBurned;

    @Column(name = "repetitions")
    private Long repetitions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(Long caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public Long getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(Long repetitions) {
        this.repetitions = repetitions;
    }

    @Override
    public String toString() {
        return "UserDailyExercisePlan{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", exerciseName='" + exerciseName + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", caloriesBurned=" + caloriesBurned +
                ", repetitions=" + repetitions +
                '}';
    }
}
