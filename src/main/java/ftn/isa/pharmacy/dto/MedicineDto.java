package ftn.isa.pharmacy.dto;


import ftn.isa.pharmacy.model.Medicine;


public class MedicineDto {


    private Long id;

    private String code;


    private String name;


    private String type;


    private String shape;


    private String composition;


    private String manufacturer;




    private Boolean prescriptionRequirement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Boolean getPrescriptionRequirement() {
        return prescriptionRequirement;
    }

    public void setPrescriptionRequirement(Boolean prescriptionRequirement) {
        this.prescriptionRequirement = prescriptionRequirement;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public float getEvaluationGrade() {
        return evaluationGrade;
    }

    public void setEvaluationGrade(float evaluationGrade) {
        this.evaluationGrade = evaluationGrade;
    }

    public int getLoyaltyScore() {
        return loyaltyScore;
    }

    public void setLoyaltyScore(int loyaltyScore) {
        this.loyaltyScore = loyaltyScore;
    }

    private String additionalNotes;


    private float evaluationGrade;


    private int loyaltyScore;


    public MedicineDto() {
    }


    public MedicineDto(Medicine medicine) {
        this.id = medicine.getId();
        this.code = medicine.getCode();
        this.name = medicine.getName();
        this.type = medicine.getType();
        this.shape = medicine.getShape();
        this.composition = medicine.getComposition();
        this.manufacturer = medicine.getManufacturer();
        this.prescriptionRequirement = medicine.getPrescriptionRequirement();
        this.additionalNotes = medicine.getAdditionalNotes();
        this.evaluationGrade = medicine.getEvaluationGrade();
        this.loyaltyScore = medicine.getLoyaltyScore();
    }


}
