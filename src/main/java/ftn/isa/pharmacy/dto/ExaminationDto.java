package ftn.isa.pharmacy.dto;

import ftn.isa.pharmacy.model.Examination;

import javax.persistence.Column;
import java.sql.Time;
import java.util.Date;

public class ExaminationDto {


    private Long id;

    private Boolean penalty;

    private Boolean isFree;

    private String examinationReport;

    private int loyaltyScore;

    private Date date;

    private String time;

    private int durationMinutes;

    private float price;

    private DermatologistDto dermatologistDto;

    public ExaminationDto() {
    }

    public ExaminationDto(Long id, Boolean penalty, Boolean isFree, String examinationReport, int loyaltyScore, Date date, String time, int durationMinutes, float price, DermatologistDto dermatologistDto, Long patientId, String dermatologistName, String dermatologistLastname, Long dermatologistId, Float dermatologistEvaluationGrade) {
        this.id = id;
        this.penalty = penalty;
        this.isFree = isFree;
        this.examinationReport = examinationReport;
        this.loyaltyScore = loyaltyScore;
        this.date = date;
        this.time = time;
        this.durationMinutes = durationMinutes;
        this.price = price;
        this.dermatologistDto = dermatologistDto;
        this.patientId = patientId;
        this.dermatologistName = dermatologistName;
        this.dermatologistLastname = dermatologistLastname;
        this.dermatologistId = dermatologistId;
        this.dermatologistEvaluationGrade = dermatologistEvaluationGrade;
    }

    public Long getDermatologistId() {
        return dermatologistId;
    }

    public void setDermatologistId(Long dermatologistId) {
        this.dermatologistId = dermatologistId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    private Long patientId;

    public String getDermatologistName() {
        return dermatologistName;
    }

    public void setDermatologistName(String dermatologistName) {
        this.dermatologistName = dermatologistName;
    }

    public String getDermatologistLastname() {
        return dermatologistLastname;
    }

    public void setDermatologistLastname(String dermatologistLastname) {
        this.dermatologistLastname = dermatologistLastname;
    }

    private String dermatologistName;

    private String dermatologistLastname;


    private Long dermatologistId;

    private Float dermatologistEvaluationGrade;

    public Float getDermatologistEvaluationGrade() {
        return dermatologistEvaluationGrade;
    }

    public void setDermatologistEvaluationGrade(Float dermatologistEvaluationGrade) {
        this.dermatologistEvaluationGrade = dermatologistEvaluationGrade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPenalty() {
        return penalty;
    }

    public void setPenalty(Boolean penalty) {
        this.penalty = penalty;
    }

    public Boolean getFree() {
        return isFree;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }

    public String getExaminationReport() {
        return examinationReport;
    }

    public void setExaminationReport(String examinationReport) {
        this.examinationReport = examinationReport;
    }

    public int getLoyaltyScore() {
        return loyaltyScore;
    }

    public void setLoyaltyScore(int loyaltyScore) {
        this.loyaltyScore = loyaltyScore;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public DermatologistDto getDermatologistDto() {
        return dermatologistDto;
    }

    public void setDermatologistDto(DermatologistDto dermatologistDto) {
        this.dermatologistDto = dermatologistDto;
    }

    @Override
    public String toString() {
        return "ExaminationDto{" +
                "id=" + id +
                ", penalty=" + penalty +
                ", isFree=" + isFree +
                ", examinationReport='" + examinationReport + '\'' +
                ", loyaltyScore=" + loyaltyScore +
                ", date=" + date +
                ", time=" + time +
                ", durationMinutes=" + durationMinutes +
                ", price=" + price +
                ", patientId=" + patientId +
                ", dermatologistName='" + dermatologistName + '\'' +
                ", dermatologistLastname='" + dermatologistLastname + '\'' +
                ", dermatologistId=" + dermatologistId +
                ", dermatologistEvaluationGrade=" + dermatologistEvaluationGrade +
                '}';
    }




}
