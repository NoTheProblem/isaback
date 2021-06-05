package ftn.isa.pharmacy.dto;

public class PharmacistDTO {

    private Long id;
    private float evaluationGrade;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String type;
    private PharmacyDto pharmacy;
    private WorkingHoursPharmacistDTO workingHours;

    public PharmacistDTO() {
    }

    public PharmacistDTO(Long id, float evaluationGrade, String firstName, String lastName, String username, String email, String type, PharmacyDto pharmacy, WorkingHoursPharmacistDTO workingHours) {
        this.id = id;
        this.evaluationGrade = evaluationGrade;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.type = type;
        this.pharmacy = pharmacy;
        this.workingHours = workingHours;
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

    public PharmacyDto getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(PharmacyDto pharmacy) {
        this.pharmacy = pharmacy;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public WorkingHoursPharmacistDTO getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(WorkingHoursPharmacistDTO workingHours) {
        this.workingHours = workingHours;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
