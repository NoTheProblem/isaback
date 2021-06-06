package ftn.isa.pharmacy.dto;

import java.util.Date;

public class WorkingHoursDTO {

    private Long id;
    private Date startTime;
    private Date endTime;
    private PharmacyDto pharmacy;
    private DermatologistDto dermatologist;
    private String workDay;

    public WorkingHoursDTO() {
    }

    public WorkingHoursDTO(Long id, Date startTime, Date endTime, PharmacyDto pharmacy, DermatologistDto dermatologist, String workDay) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pharmacy = pharmacy;
        this.dermatologist = dermatologist;
        this.workDay = workDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public PharmacyDto getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(PharmacyDto pharmacy) {
        this.pharmacy = pharmacy;
    }

    public DermatologistDto getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(DermatologistDto dermatologist) {
        this.dermatologist = dermatologist;
    }

    public String getWorkDay() {
        return workDay;
    }

    public void setWorkDay(String workDay) {
        this.workDay = workDay;
    }

    @Override
    public String toString() {
        return "WorkingHoursDTO{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", pharmacy=" + pharmacy +
                ", dermatologist=" + dermatologist +
                ", workDay='" + workDay + '\'' +
                '}';
    }
}
