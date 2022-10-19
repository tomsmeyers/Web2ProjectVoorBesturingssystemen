package domain.model;

public class Speler {
    private int id;
    private String naam, club;
    private int doelpunten, wedstrijden;

    public Speler(){}
    public Speler(String naam, String club, int doelpunten, int wedstrijden){
        if(wedstrijden==0&&doelpunten>0){
            throw new IllegalArgumentException("Als je nog niet gespeeld hebt kan je niet al gescoord hebben!");
        }
        setNaam(naam);
        setClub(club);
        setDoelpunten(doelpunten);
        setWedstrijden(wedstrijden);

    }

    public void setWedstrijden(int wedstrijden) {
        if(wedstrijden<0){
            throw new IllegalArgumentException("Geen geldig aantal wedstrijden!");
        }
        else {
            this.wedstrijden=wedstrijden;
        }
    }

    public void setDoelpunten(int doelpunten) {
        if(doelpunten<0){
            throw new IllegalArgumentException("Geen geldig aantal doelpunten!");
        }
        else {
            this.doelpunten=doelpunten;
        }
    }

    public void setClub(String club) {
        if(club==null||club.trim().isEmpty()){
            throw new IllegalArgumentException("Geen geldige club!");
        }
        else {
            this.club=club;
        }
    }

    public void setNaam(String naam) {
        if(naam==null||naam.trim().isEmpty()){
            throw new IllegalArgumentException("Geen geldige naam!");
        }
        else {
            this.naam=naam;
        }
    }
    public void setId(int id){
        if(id<0){
            throw new IllegalArgumentException("Geen geldige id!");
        }
        else {
            this.id=id;
        }
    }
    public String getNaam() {
        return naam;
    }

    public String getClub() {
        return club;
    }

    public int getDoelpunten() {
        return doelpunten;
    }

    public int getWedstrijden() {
        return wedstrijden;
    }

    public int getId() {
        return id;
    }
}
