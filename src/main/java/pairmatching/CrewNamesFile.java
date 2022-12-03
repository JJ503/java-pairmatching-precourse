package pairmatching;

public enum CrewNamesFile {
    BACKEND_CREW(Course.BACKEND, "src/main/resources/backend-crew.md"),
    FRONTEND_CREW(Course.FRONTEND, "src/main/resources/frontend-crew.md");

    private final Course course;
    private final String filePath;

    CrewNamesFile(Course course, String filePath) {
        this.course = course;
        this.filePath = filePath;
    }

    public Course getCourse() {
        return course;
    }

    public String getFilePath() {
        return filePath;
    }
}
