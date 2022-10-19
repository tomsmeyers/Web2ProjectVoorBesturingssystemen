package domain.db;

import domain.model.Speler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SpelerDB {
    private int sequence=0;
    private List<Speler> spelers = new ArrayList<>();

    public SpelerDB(){
        this.add(new Speler("Deniz Undav", "Union", 20, 28));
        this.add(new Speler("Michael Frey", "Antwerp", 19, 27));
        this.add(new Speler("Nikola Storm", "KV Mechelen", 15, 26));
        this.add(new Speler("Paul Onuachu", "Genk", 15, 24));
        this.add(new Speler("Joshua Zirkzee", "Anderlecht", 12, 25));
    }
    public void add(Speler speler){
        this.sequence++;
        speler.setId(sequence);
        spelers.add(speler);
    }
    public List getSpelers(){
        return spelers;
    }
    public void addSpeler(Speler speler){
        spelers.add(speler);
    }
    public void vervang(String naam, Speler speler){
        for(Speler speler1: spelers){
            if(speler1.getNaam().equals(naam)){
                speler1 = speler;
            }
        }
    }
    public Speler getSpeler(String naam){
        for(Speler speler1: spelers){
            if(speler1.getNaam().equals(naam)){
                return speler1;
            }
        }
        throw new IllegalArgumentException("Speler niet gevonden!");
    }
    public Speler getSpeler(String naam, String club) {
        if((naam == null || naam.isEmpty()) && (club == null || club.isEmpty()))
            throw new IllegalArgumentException("Naam en Club mogen niet leeg zijn!");
        if (naam == null || naam.isEmpty())
            throw new IllegalArgumentException("Naam mag niet leeg zijn!");
        if (club == null || club.isEmpty())
            throw new IllegalArgumentException("Club mag niet leeg zijn!");
        for(Speler speler1: spelers){
            if (speler1.getNaam().toLowerCase().equals(naam.toLowerCase()))
                if(speler1.getClub().contains(club)){
                    return speler1;
                }
        }
        return null;
    }
    public String getSpelerMetMeesteGoals(){
        Speler speler = null;
        int meestedoelpunten = 0;
        for(Speler speler1: spelers){
            if(speler1.getDoelpunten()>meestedoelpunten){
                speler = speler1;
                meestedoelpunten = speler1.getDoelpunten();
            }
        }
        return speler == null?"":speler.getNaam();
    }
    public Speler ZoekSpeler(int id) {
        for(Speler s: spelers){
            if(s.getId()==id){
                return s;
            }
        }
        return null;
    }

    public boolean Verwijder(int id){
        return spelers.remove(ZoekSpeler(id));
    }
    public boolean AanpassenSpeler(int id, String naam, int wedstrijden, int doelpunten, String club) {
        for(Speler s: spelers){
            if(s.getId()==id){
                s.setNaam(naam);
                s.setWedstrijden(wedstrijden);
                s.setDoelpunten(doelpunten);
                s.setClub(club);
                return true;
            }
        }
        return false;
    }

}
