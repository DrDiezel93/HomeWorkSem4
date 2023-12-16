package org.example;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "courses")
public class Course {
    private static final String[] names = new String[] { "Alex", "Joli", "Kuprum", "Lisa", "Cox", "Poppy"};
    private static final Random random = new Random();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private int duration;

    public Course() {

    }

    public Course(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }
    public static Course create(){
        return new Course(names[random.nextInt(names.length)], random.nextInt(0, 1000));
    }

    public void updateAge(){
        duration = random.nextInt(20, 26);
    }

    public void updateName(){
        title = names[random.nextInt(names.length)];
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }
}
