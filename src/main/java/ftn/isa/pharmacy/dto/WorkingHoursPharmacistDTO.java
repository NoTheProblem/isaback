package ftn.isa.pharmacy.dto;

public class WorkingHoursPharmacistDTO {
    private Long id;
    private String startTime;
    private String endTime;
    private PharmacyDto pharmacy;
    private PharmacistDTO pharmacistDTO;
    private String workDay;

    public WorkingHoursPharmacistDTO() {
    }

    public WorkingHoursPharmacistDTO(Long id, String startTime, String endTime, PharmacyDto pharmacy, PharmacistDTO pharmacistDTO, String workDay) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pharmacy = pharmacy;
        this.pharmacistDTO = pharmacistDTO;
        this.workDay = workDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public PharmacyDto getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(PharmacyDto pharmacy) {
        this.pharmacy = pharmacy;
    }

    public PharmacistDTO getPharmacistDTO() {
        return pharmacistDTO;
    }

    public void setPharmacistDTO(PharmacistDTO pharmacistDTO) {
        this.pharmacistDTO = pharmacistDTO;
    }

    public String getWorkDay() {
        return workDay;
    }

    public void setWorkDay(String workDay) {
        this.workDay = workDay;
    }
}
