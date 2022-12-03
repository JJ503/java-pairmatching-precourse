package pairmatching;

import java.util.ArrayList;
import java.util.List;

public class Crew {
    private Course course;
    private String name;
    private List<String> pairName;

    Crew (Course course, String name) {
        this.course = course;
        this.name = name;
        pairName = new ArrayList<>();
    }
}
