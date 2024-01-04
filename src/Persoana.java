public class Persoana {


    private  String nume;
    private String prenume;
    private String email;
    private String facultate;
    private String programStudii;
    private String specializare;
    private  String an;

    public Persoana(String nume, String prenume, String email, String facultate,String programStudii, String specializare, String an) {
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.facultate = facultate;
        this.programStudii=programStudii;
        this.specializare = specializare;
        this.an = an;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getEmail() {
        return email;
    }

    public String getFacultate() {
        return facultate;
    }

    public String getProgramStudii() {
        return programStudii;
    }

    public String getSpecializare() {
        return specializare;
    }

    public String getAn() {
        return an;
    }
}
