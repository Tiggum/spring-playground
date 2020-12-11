package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, Long> {

    Lesson findByTitle(String title);

    List<Lesson> findAllByDeliveredOnBetween(Date startDate, Date endDate);

}
