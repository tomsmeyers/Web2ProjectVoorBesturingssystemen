package domain.model;

public class Studentenkamer {
    private int id;
    private String straat;
    private int huisnummer;
    private String kamernummer;
    private int kostPerMaand;

    public Studentenkamer(String straat, int huisnummer, String kamernummer, int kostPerMaand) {
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.kamernummer = kamernummer;
        this.kostPerMaand = kostPerMaand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        if (straat == null || straat.isEmpty())
            throw new DomainException("Geen geldige straat");
        this.straat = straat;
    }

    public int getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(int huisnummer) {
        if (huisnummer < 0)
            throw new DomainException("Huisnummer moet positief getal zijn");
        this.huisnummer = huisnummer;
    }

    public String getKamernummer() {
        return kamernummer;
    }

    public void setKamernummer(String kamernummer) {
        if (kamernummer == null || kamernummer.isEmpty())
            throw new DomainException("Geen geldig kamernummer");
        this.kamernummer = kamernummer;
    }

    public int getKostPerMaand() {
        return kostPerMaand;
    }

    public void setKostPerMaand(int kostPerMaand) {
        if (kostPerMaand <= 0)
            throw new DomainException("Kost per maand moet positief getal zijn");
        this.kostPerMaand = kostPerMaand;
    }

    @Override
    public String toString() {
        return getId() + ": " + getStraat() + " " + getHuisnummer() + " (kamer " + getKamernummer() + ") kost €" + getKostPerMaand() + " per maand";
    }
}
