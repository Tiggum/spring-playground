package com.example.demo;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lessons")
public class LessonController {
    private final LessonRepository repository;

    public LessonController(LessonRepository repository){
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson){
        return this.repository.save(lesson);
    }

    @GetMapping("/{id}")
    public Optional<Lesson> getLesson(@PathVariable Long id){
        return this.repository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteLesson(@PathVariable long id){
        this.repository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public Lesson patchLesson(@PathVariable long id, @RequestBody Lesson lesson) {
        lesson.setId(id);
        return this.repository.save(lesson);
    }

    @GetMapping("/find/{title}")
    public Lesson getTitle(@PathVariable String title){
        return this.repository.findByTitle(title);
    }

    @GetMapping("/between")
    public List<Lesson> getDateBetween(@RequestParam
                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date1,
                                       @RequestParam
                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date2){
        return this.repository.findAllByDeliveredOnBetween(date1, date2);
    }


}

