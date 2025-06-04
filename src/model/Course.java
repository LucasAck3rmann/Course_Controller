package model;

public class Course {
    private Long id;
    private String name;
    private String description;
    private int workload; // carga horária

    public Course() {
    }

    public Course(Long id, String name, String description, int workload) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.workload = workload;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getWorkload() {
        return workload;
    }
    public void setWorkload(int workload) {
        this.workload = workload;
    }

    public String toString() {
        return "Course { " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", workload=" + workload +
                " }";
    }
}
