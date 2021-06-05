package ftn.isa.pharmacy.dto;

public class PharmacyDto {

    private Long id;
    private String name;
    private String country;
    private String city;
    private String address;
    private String pharmacyDescription;
    private float evaluationGrade;
    private float counselingPrice;

    public PharmacyDto(){}

    public PharmacyDto(Long id, String name, String country, String city, String address, String pharmacyDescription, float evaluationGrade, float counselingPrice) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
        this.pharmacyDescription = pharmacyDescription;
        this.evaluationGrade = evaluationGrade;
        this.counselingPrice = counselingPrice;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPharmacyDescription() {
        return pharmacyDescription;
    }

    public void setPharmacyDescription(String pharmacyDescription) {
        this.pharmacyDescription = pharmacyDescription;
    }

    public float getEvaluationGrade() {
        return evaluationGrade;
    }

    public void setEvaluationGrade(float evaluationGrade) {
        this.evaluationGrade = evaluationGrade;
    }

    public float getCounselingPrice() {
        return counselingPrice;
    }

    public void setCounselingPrice(float counselingPrice) {
        this.counselingPrice = counselingPrice;
    }
}
