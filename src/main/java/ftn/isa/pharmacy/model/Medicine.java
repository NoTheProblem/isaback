package ftn.isa.pharmacy.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column
    private String shape;

    @Column(nullable = false)
    private String composition;

    @Column
    private String manufacturer;

    @Column
    private Boolean prescriptionRequirement;

    @Column
    private String additionalNotes;

    @Column
    private float evaluationGrade;

    @Column
    private int loyaltyScore;

    @Column(nullable = false)
    private String contraindications;

    //recommended daily intake of therapy
    @Column(nullable = false)
    private String rdiot;

    @ManyToMany(mappedBy = "basedMedicines")
    private Set<Medicine> replacedMedicines = new HashSet<Medicine>();

    @ManyToMany
    @JoinTable(name = "basedMedicines", joinColumns = @JoinColumn(name = "basedMedicine", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "replacedMedicine", referencedColumnName = "id"))
    private Set<Medicine> basedMedicines = new HashSet<Medicine>();

    @ManyToMany(mappedBy = "allergicMedicines")
    private Set<Patient> alergicMedicine = new HashSet<Patient>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PriceMediceList> priceMediceLists = new HashSet<PriceMediceList>();

    @ManyToMany(mappedBy = "missingMedicine")
    private Set<Pharmacy> pharmacies = new HashSet<Pharmacy>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicineQuantityReservation> reservationMedicines = new HashSet<MedicineQuantityReservation>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicineQuantityPharmacy> medicationQuantities = new HashSet<MedicineQuantityPharmacy>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicineQuantitySupplier> suppliersMedicines = new HashSet<MedicineQuantitySupplier>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PersList> persLists = new HashSet<PersList>();

    public Medicine() {
    }

    public Medicine(Long id, String code, String name, String type, String shape, String composition, String manufacturer, Boolean prescriptionRequirement, String additionalNotes, float evaluationGrade, int loyaltyScore, String contraindications, String rdiot, Set<Medicine> replacedMedicines, Set<Medicine> basedMedicines, Set<Patient> alergicMedicine, Set<PriceMediceList> priceMediceLists, Set<Pharmacy> pharmacies, Set<MedicineQuantityReservation> reservationMedicines, Set<MedicineQuantityOrder> orderMedicines, Set<MedicineQuantityPharmacy> medicationQuantities, Set<MedicineQuantitySupplier> suppliersMedicines, Set<PersList> persLists) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.type = type;
        this.shape = shape;
        this.composition = composition;
        this.manufacturer = manufacturer;
        this.prescriptionRequirement = prescriptionRequirement;
        this.additionalNotes = additionalNotes;
        this.evaluationGrade = evaluationGrade;
        this.loyaltyScore = loyaltyScore;
        this.contraindications = contraindications;
        this.rdiot = rdiot;
        this.replacedMedicines = replacedMedicines;
        this.basedMedicines = basedMedicines;
        this.alergicMedicine = alergicMedicine;
        this.priceMediceLists = priceMediceLists;
        this.pharmacies = pharmacies;
        this.reservationMedicines = reservationMedicines;
        this.medicationQuantities = medicationQuantities;
        this.suppliersMedicines = suppliersMedicines;
        this.persLists = persLists;
    }

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

    public String getContraindications() {
        return contraindications;
    }

    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }

    public String getRdiot() {
        return rdiot;
    }

    public void setRdiot(String rdiot) {
        this.rdiot = rdiot;
    }

    public Set<Medicine> getReplacedMedicines() {
        return replacedMedicines;
    }

    public void setReplacedMedicines(Set<Medicine> replacedMedicines) {
        this.replacedMedicines = replacedMedicines;
    }

    public Set<Medicine> getBasedMedicines() {
        return basedMedicines;
    }

    public void setBasedMedicines(Set<Medicine> basedMedicines) {
        this.basedMedicines = basedMedicines;
    }

    public Set<Patient> getAlergicMedicine() {
        return alergicMedicine;
    }

    public void setAlergicMedicine(Set<Patient> alergicMedicine) {
        this.alergicMedicine = alergicMedicine;
    }

    public Set<PriceMediceList> getPriceMediceLists() {
        return priceMediceLists;
    }

    public void setPriceMediceLists(Set<PriceMediceList> priceMediceLists) {
        this.priceMediceLists = priceMediceLists;
    }

    public Set<Pharmacy> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(Set<Pharmacy> pharmacies) {
        this.pharmacies = pharmacies;
    }

    public Set<MedicineQuantityReservation> getReservationMedicines() {
        return reservationMedicines;
    }

    public void setReservationMedicines(Set<MedicineQuantityReservation> reservationMedicines) {
        this.reservationMedicines = reservationMedicines;
    }

    public Set<MedicineQuantityPharmacy> getMedicationQuantities() {
        return medicationQuantities;
    }

    public void setMedicationQuantities(Set<MedicineQuantityPharmacy> medicationQuantities) {
        this.medicationQuantities = medicationQuantities;
    }

    public Set<MedicineQuantitySupplier> getSuppliersMedicines() {
        return suppliersMedicines;
    }

    public void setSuppliersMedicines(Set<MedicineQuantitySupplier> suppliersMedicines) {
        this.suppliersMedicines = suppliersMedicines;
    }

    public Set<PersList> getPersLists() {
        return persLists;
    }

    public void setPersLists(Set<PersList> persLists) {
        this.persLists = persLists;
    }
}
