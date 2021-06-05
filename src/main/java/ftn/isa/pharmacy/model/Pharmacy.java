package ftn.isa.pharmacy.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name ="pharmacy")
public class Pharmacy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String address;

    @Column
    private String pharmacyDescription;

    @Column
    private float evaluationGrade;

    @Column
    private float counselingPrice;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicineQuantityPharmacy> medicationQuantities = new HashSet<MedicineQuantityPharmacy>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Pharmacist> pharmacists = new HashSet<Pharmacist>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PersList> perscirptions = new HashSet<PersList>();

    @ManyToMany
    @JoinTable(name = "pharmacy_dermatologist", joinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "dermatologist_id", referencedColumnName = "id"))
    private Set<Dermatologist> dermatologists = new HashSet<Dermatologist>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PriceMediceList> priceMediceLists = new HashSet<PriceMediceList>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PriceExaminationList> priceExaminationLists = new HashSet<PriceExaminationList>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<WorkingHours> workingHours = new HashSet<WorkingHours>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Promotion> promotions = new HashSet<Promotion>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AbsenceRequest> absenceRequests = new HashSet<AbsenceRequest>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PharmacyAdmin> pharmacyAdmins = new HashSet<PharmacyAdmin>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Examination> listOfFreeExamination = new HashSet<Examination>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<Reservation>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PurchaseOrder> purchaseOrders = new HashSet<PurchaseOrder>();

    public Set<Patient> getSubscribedPatients() {
        return subscribedPatients;
    }

    public void setSubscribedPatients(Set<Patient> subscribedPatients) {
        this.subscribedPatients = subscribedPatients;
    }

    @ManyToMany(mappedBy = "subscribedPharmacies")
    private Set<Patient> subscribedPatients = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "pharmaci_missmedicine", joinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "medicine_id", referencedColumnName = "id"))
    private Set<Medicine> missingMedicine = new HashSet<Medicine>();

    public Pharmacy() {
    }

    public Pharmacy(Long id, String name, String country, String city, String address, String pharmacyDescription, float evaluationGrade, float counselingPrice, Set<MedicineQuantityPharmacy> medicationQuantities, Set<Pharmacist> pharmacists, Set<Dermatologist> dermatologists, Set<PriceMediceList> priceMediceLists, Set<PriceExaminationList> priceExaminationLists, Set<WorkingHours> workingHours, Set<Promotion> promotions, Set<PharmacyAdmin> pharmacyAdmins, Set<Examination> listOfFreeExamination, Set<Reservation> reservations, Set<Patient> subscribedPatients, Set<Medicine> missingMedicine) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
        this.pharmacyDescription = pharmacyDescription;
        this.evaluationGrade = evaluationGrade;
        this.counselingPrice = counselingPrice;
        this.medicationQuantities = medicationQuantities;
        this.pharmacists = pharmacists;
        this.dermatologists = dermatologists;
        this.priceMediceLists = priceMediceLists;
        this.priceExaminationLists = priceExaminationLists;
        this.workingHours = workingHours;
        this.promotions = promotions;
        this.pharmacyAdmins = pharmacyAdmins;
        this.listOfFreeExamination = listOfFreeExamination;
        this.reservations = reservations;
        this.subscribedPatients = subscribedPatients;
        this.missingMedicine = missingMedicine;
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

    public Set<MedicineQuantityPharmacy> getMedicationQuantities() {
        return medicationQuantities;
    }

    public void setMedicationQuantities(Set<MedicineQuantityPharmacy> medicationQuantities) {
        this.medicationQuantities = medicationQuantities;
    }

    public Set<Pharmacist> getPharmacists() {
        return pharmacists;
    }

    public void setPharmacists(Set<Pharmacist> pharmacists) {
        this.pharmacists = pharmacists;
    }

    public Set<Dermatologist> getDermatologists() {
        return dermatologists;
    }

    public void setDermatologists(Set<Dermatologist> dermatologists) {
        this.dermatologists = dermatologists;
    }

    public Set<PriceMediceList> getPriceMediceLists() {
        return priceMediceLists;
    }

    public void setPriceMediceLists(Set<PriceMediceList> priceMediceLists) {
        this.priceMediceLists = priceMediceLists;
    }

    public Set<PriceExaminationList> getPriceExaminationLists() {
        return priceExaminationLists;
    }

    public void setPriceExaminationLists(Set<PriceExaminationList> priceExaminationLists) {
        this.priceExaminationLists = priceExaminationLists;
    }

    public Set<WorkingHours> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Set<WorkingHours> workingHours) {
        this.workingHours = workingHours;
    }

    public Set<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(Set<Promotion> promotions) {
        this.promotions = promotions;
    }

    public Set<PharmacyAdmin> getPharmacyAdmins() {
        return pharmacyAdmins;
    }

    public void setPharmacyAdmins(Set<PharmacyAdmin> pharmacyAdmins) {
        this.pharmacyAdmins = pharmacyAdmins;
    }

    public Set<Examination> getListOfFreeExamination() {
        return listOfFreeExamination;
    }

    public void setListOfFreeExamination(Set<Examination> listOfFreeExamination) {
        this.listOfFreeExamination = listOfFreeExamination;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Medicine> getMissingMedicine() {
        return missingMedicine;
    }

    public void setMissingMedicine(Set<Medicine> missingMedicine) {
        this.missingMedicine = missingMedicine;
    }

    public Set<PersList> getPerscirptions() {
        return perscirptions;
    }

    public void setPerscirptions(Set<PersList> perscirptions) {
        this.perscirptions = perscirptions;
    }

    public Set<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(Set<PurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    public Set<AbsenceRequest> getAbsenceRequests() {
        return absenceRequests;
    }

    public void setAbsenceRequests(Set<AbsenceRequest> absenceRequests) {
        this.absenceRequests = absenceRequests;
    }
}
