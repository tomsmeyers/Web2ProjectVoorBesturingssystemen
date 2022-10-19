package domain.db;
// pas naam van de package aan naar je eigen situatie

import java.util.ArrayList;
import domain.model.Studentenkamer;
import domain.model.DomainException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentenkamerDB {
    private int sequence = 0;
    private final List<Studentenkamer> studentenkamers = new ArrayList<>();

    public StudentenkamerDB() {
        this.add(new Studentenkamer("Kaboutermansstraat", 12, "1/1", 345));
        this.add(new Studentenkamer("Brusselsestraat", 7, "3/5", 482));
        this.add(new Studentenkamer("Wilselsesteenweg", 415, "7/12", 340));
        this.add(new Studentenkamer("Liemingenstraat", 89, "2/78", 547));
        this.add(new Studentenkamer("Vlietpad", 9, "127", 460));
        this.add(new Studentenkamer("Vogelzang", 29, "3/7", 338));
    }

    public void add(Studentenkamer studentenkamer) {
        if (studentenkamer == null)
            throw new DomainException("Geen geldige studentenkamer om toe te voegen");
        this.sequence++;
        studentenkamer.setId(sequence);
        studentenkamers.add(studentenkamer);
    }

    public Studentenkamer findById(int id) {
        for (Studentenkamer studentenkamer : studentenkamers)
            if (studentenkamer.getId() == id)
                return studentenkamer;
        throw new DomainException("Er is geen kamer met gegeven id");
    }

    public List<Studentenkamer> getAllStudentenkamers() {
        return studentenkamers;
    }

 
    @Override
    public String toString() {
        String out = "";
        for (Studentenkamer studentenkamer : getAllStudentenkamers()
        ) {
            out += studentenkamer.toString() + "\n";
        }
        return out;
    }


}
