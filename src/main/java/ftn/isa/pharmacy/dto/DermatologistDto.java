package ftn.isa.pharmacy.dto;

import java.util.List;

public class DermatologistDto {

    private Long id;
    private float evaluationGrade;
    private String firstName;
    private String lastName;
    private String type;
    private List<WorkingHoursDTO> workingHours;
    private List<ExaminationDto> examinations;
    private List<PharmacyDto> pharmacys;

    public DermatologistDto() {
    }

    public DermatologistDto(Long id, float evaluationGrade, String firstName, String lastName, String type, List<WorkingHoursDTO> workingHours, List<ExaminationDto> examinations, List<PharmacyDto> pharmacys) {
        this.id = id;
        this.evaluationGrade = evaluationGrade;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.workingHours = workingHours;
        this.examinations = examinations;
        this.pharmacys = pharmacys;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getEvaluationGrade() {
        return evaluationGrade;
    }

    public void setEvaluationGrade(float evaluationGrade) {
        this.evaluationGrade = evaluationGrade;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<WorkingHoursDTO> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(List<WorkingHoursDTO> workingHours) {
        this.workingHours = workingHours;
    }

    public List<ExaminationDto> getExaminations() {
        return examinations;
    }

    public void setExaminations(List<ExaminationDto> examinations) {
        this.examinations = examinations;
    }

    public List<PharmacyDto> getPharmacys() {
        return pharmacys;
    }

    public void setPharmacys(List<PharmacyDto> pharmacys) {
        this.pharmacys = pharmacys;
    }
}
