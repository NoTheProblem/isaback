package ftn.isa.pharmacy.dto;


public class MedicineRegisterDto {

    private Long id;
    private String code;
    private String contraindications;
    private String name;
    private String type;
    private String composition;
    private String rdiot;
    private int loyaltyScore;

    public MedicineRegisterDto(){
    }

    public MedicineRegisterDto(Long id, String code, String name, String type, String contraindications, String composition, String rdiot, int loyaltyScore) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.type = type;
        this.contraindications = contraindications;
        this.composition = composition;
        this.rdiot = rdiot;
        this.loyaltyScore = loyaltyScore;
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

    public String getContraindications() {
        return contraindications;
    }

    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getRdiot() {
        return rdiot;
    }

    public void setRdiot(String rdiot) {
        this.rdiot = rdiot;
    }

    public int getLoyaltyScore() {
        return loyaltyScore;
    }

    public void setLoyaltyScore(int loyaltyScore) {
        this.loyaltyScore = loyaltyScore;
    }


}
