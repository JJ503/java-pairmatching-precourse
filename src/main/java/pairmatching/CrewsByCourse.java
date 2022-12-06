package pairmatching;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class CrewsByCourse {
    private Course course;
    private List<String> crews;

    CrewsByCourse(Course course, List<String> crews) {
        this.course = course;
        this.crews = crews;
    }

    public List<String> shuffledCrews() {
        return Randoms.shuffle(crews);
    }
}
