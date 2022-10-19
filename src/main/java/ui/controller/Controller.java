package ui.controller;

import domain.db.SpelerDB;
import domain.db.StudentenkamerDB;
import domain.model.DomainException;
import domain.model.Speler;
import domain.model.Studentenkamer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private final SpelerDB spelerDB = new SpelerDB();
    private final StudentenkamerDB studentenkamerDB = new StudentenkamerDB();
    public Controller() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = "home";
        if (request.getParameter("command") != null) {
            command = request.getParameter("command");
        }
        String destination = home(request,response);
        switch (command) {
            case "Overzicht":
                destination = Overzicht(request, response);
                break;
            case "VoegToe":
                destination = VoegToe(request, response);
                break;
            case "VoegSpelerToe":
                destination = VoegSpelerToe(request, response);
                break;
            case "Verwijder":
                destination = Verwijder(request,response);
                break;
            case "Zoek":
                destination = Zoek(request,response);
                break;
            case "VerwijderPage":
                destination = VerwijderPage(request,response);
                break;
            case "ZoekSpeler":
                destination = ZoekSpeler(request,response);
                break;
            case "PasAanPage":
                destination = PasAanPage(request,response);
                break;
            case "PasSpelerAan":
                destination = PasSpelerAan(request,response);
                break;
            case "AntwoordJa":
                SwitchCookies(request,response, "Ja");
                break;
            case "AntwoordNee":
                SwitchCookies(request,response,"Nee");
                break;
            case "AntwoordWijzig":
                SwitchCookies(request,response,"Wijzig");
                break;
            case "AanpassenOngedaanMaken":
                destination = AanpassenOngedaanMaken(request, response);
                break;
            case "VindKamer":
                destination = VindKamer(request, response);
                break;
            case "ZoekKamer":
                destination = ZoekKamer(request, response);
                break;
            default:
                destination = home(request, response);
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }

    private String ZoekKamer(HttpServletRequest request, HttpServletResponse response) {
        String errors = "";
        String idKamer = request.getParameter("number");
        try {
            Integer.parseInt(idKamer);
        }
        catch (NumberFormatException exc) {
            errors = "U vulde het formulier niet correct in";
            request.setAttribute("errors", errors);
            return VindKamer(request,response);
        }
        int id = Integer.parseInt(idKamer);
        try {
            studentenkamerDB.findById(id);
            request.setAttribute("kamer", studentenkamerDB.findById(id));
            return "result_kamer.jsp";
        } catch (DomainException exc) {
            errors = "U vulde het formulier niet correct in";
        }
        request.setAttribute("errors", errors);
        return VindKamer(request,response);
    }

    private String VindKamer(HttpServletRequest request, HttpServletResponse response) {
        Footer(request);
        return "form_vindKamer.jsp";
    }

    private String AanpassenOngedaanMaken(HttpServletRequest request, HttpServletResponse response) {
        Speler speler = (Speler) request.getSession().getAttribute("vorigeSpeler");
        if(speler != null) {
            int id = (int) request.getSession().getAttribute("idSpeler");
            spelerDB.AanpassenSpeler(id, speler.getNaam(), speler.getWedstrijden(), speler.getDoelpunten(), speler.getClub());
            return Overzicht(request, response);
        }
        request.setAttribute("foutmelding", "Er is nog niks aangepast!");
        return home(request,response);
    }

    private String PasSpelerAan(HttpServletRequest request, HttpServletResponse response) {
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);
        Speler test = spelerDB.ZoekSpeler(id);
        Speler vorigeSpeler = new Speler(test.getNaam(),test.getClub(),test.getDoelpunten(),test.getWedstrijden());
        ArrayList<String> errors = new ArrayList<>();
        Speler speler = new Speler();
        setNaam(speler, request, errors);
        setWedstrijden(speler, request, errors);
        setDoelpunten(speler, request, errors);
        setClub(speler, request, errors);

        if(speler.getWedstrijden()==0 && speler.getDoelpunten()>0){
            errors.add("Je kan niet al gescoord hebben als je nog niet gespeeld hebt!");
        }
        if (errors.size() == 0) {
            try {
                HttpSession session = request.getSession();
                session.setAttribute("vorigeSpeler", vorigeSpeler);
                session.setAttribute("idSpeler",id);
                spelerDB.AanpassenSpeler(id, speler.getNaam(), speler.getWedstrijden(), speler.getDoelpunten(), speler.getClub());

                return Overzicht(request, response);
            } catch (IllegalArgumentException exc) {
                errors.add(exc.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        request.setAttribute("speler",spelerDB.ZoekSpeler(id));
        request.setAttribute("id", id);
        return "PasAan.jsp";
    }

    private String PasAanPage(HttpServletRequest request, HttpServletResponse response) {
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);
        request.setAttribute("speler",spelerDB.ZoekSpeler(id));
        request.setAttribute("id", id);
        Cookie cookie = getCookieWithKey(request, "Antwoord");
        if(cookie == null || cookie.getValue().equals("")|| cookie.getValue().equals("Wijzig")){
            request.setAttribute("footer", "FooterCookies.jsp");
        }
        else {
            request.setAttribute("footer", "FooterWithCookies.jsp");
        }
        return "PasAan.jsp";
    }

    private String ZoekSpeler(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<String>();
        String naamSpeler = request.getParameter("Name");
        String clubSpeler = request.getParameter("Club");
        try {
            spelerDB.getSpeler(naamSpeler,clubSpeler);
        }catch (IllegalArgumentException exc){
            errors.add(exc.getMessage());
        }
        if(errors.size() == 0) {
            Speler speler = spelerDB.getSpeler(naamSpeler, clubSpeler);
            request.setAttribute("speler", speler);
            request.setAttribute("naam", naamSpeler);
            if (speler != null) {
                request.setAttribute("doelpunten", spelerDB.getSpeler(naamSpeler, clubSpeler).getDoelpunten());
                request.setAttribute("wedstrijden", spelerDB.getSpeler(naamSpeler, clubSpeler).getWedstrijden());
                request.setAttribute("club", spelerDB.getSpeler(naamSpeler, clubSpeler).getClub());
            }
            return "Gevonden.jsp";
        }
        request.setAttribute("errors", errors);
        return Zoek(request,response);
    }

    private String VerwijderPage(HttpServletRequest request, HttpServletResponse response) {
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);
        request.setAttribute("speler",spelerDB.ZoekSpeler(id));
        Footer(request);
        return "Verwijder.jsp";
    }

    private String Zoek(HttpServletRequest request, HttpServletResponse response) {
        Footer(request);
        return "Zoek.jsp";
    }

    private String Verwijder(HttpServletRequest request, HttpServletResponse response) {
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);
        request.setAttribute("speler", spelerDB.Verwijder(id));
        return Overzicht(request, response);
    }
    private void setNaam(Speler speler, HttpServletRequest request, ArrayList<String> errors){
        String naam = request.getParameter("Name");
        boolean naamHasErrors = false;
        try {
            request.setAttribute("VorigeNaam", naam);
            speler.setNaam(naam);
        } catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
            naamHasErrors = true;
        } finally {
            request.setAttribute("naamHasErrors", naamHasErrors);
        }
    }
    private void setClub(Speler speler, HttpServletRequest request, ArrayList<String> errors){
        String Club = request.getParameter("Club");
        boolean clubHasErrors = false;
        try {
            request.setAttribute("VorigeClub", Club);
            speler.setClub(Club);
        } catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
            clubHasErrors = true;
        } finally {
            request.setAttribute("clubHasErrors", clubHasErrors);
        }
    }
    private void setDoelpunten(Speler speler, HttpServletRequest request, ArrayList<String> errors){
        String doelpuntenFromParameter = request.getParameter("Doelpunten");
        boolean doelpuntenHasErrors = false;
        try {
            request.setAttribute("VorigeDoelpunten", doelpuntenFromParameter);
            speler.setDoelpunten(Integer.parseInt(doelpuntenFromParameter));
        } catch(NumberFormatException exc){
            errors.add("Vul een nummer in voor doelpunten.");
            doelpuntenHasErrors = true;
        }
        catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
            doelpuntenHasErrors = true;
        } finally {
            request.setAttribute("doelpuntenHasErrors", doelpuntenHasErrors);
        }
    }
    private void setWedstrijden(Speler speler, HttpServletRequest request, ArrayList<String> errors){
        String WedstrijdenFromParameter = request.getParameter("Wedstrijden");
        boolean wedstrijdenHasErrors = false;
        try {
            request.setAttribute("VorigeWedstrijden", WedstrijdenFromParameter);
            speler.setWedstrijden(Integer.parseInt(WedstrijdenFromParameter));
        } catch(NumberFormatException exc){
            errors.add("Vul een nummer in voor wedstrijden.");
            wedstrijdenHasErrors = true;
        }
        catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
            wedstrijdenHasErrors = true;
        } finally {
            request.setAttribute("wedstrijdenHasErrors", wedstrijdenHasErrors);
        }
    }
    private String VoegSpelerToe(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<String>();
        Speler speler = new Speler();

        setNaam(speler, request, errors);
        setClub(speler, request, errors);
        setDoelpunten(speler, request, errors);
        setWedstrijden(speler, request, errors);
        if(speler.getWedstrijden()==0 && speler.getDoelpunten()>0){
            errors.add("Je kan niet al gescoord hebben als je nog niet gespeeld hebt!");
        }
        if(errors.size() == 0) {
            try {
                spelerDB.add(speler);
                return Overzicht(request, response);
            }catch (IllegalArgumentException exc){
                errors.add(exc.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return  "VoegToe.jsp";
    }

    private String VoegToe(HttpServletRequest request, HttpServletResponse response) {
        Footer(request);
        return "VoegToe.jsp";
    }
    private String home(HttpServletRequest request, HttpServletResponse response) {
        Footer(request);
        request.setAttribute("topscoorder", spelerDB.getSpelerMetMeesteGoals());
        return "index.jsp";
    }

    private String Overzicht(HttpServletRequest request, HttpServletResponse response) {
        Footer(request);
        request.setAttribute("lijst", spelerDB.getSpelers());
        return "Overzicht.jsp";
    }

    private void Footer(HttpServletRequest request) {
        Cookie cookie = getCookieWithKey(request, "Antwoord");
        if(cookie == null || cookie.getValue().equals("")|| cookie.getValue().equals("Wijzig")){
            request.setAttribute("footer", "FooterCookies.jsp");
        }
        else if(cookie.getValue().equals("Ja")){
            request.setAttribute("zin","cookies worden verzameld");
            request.setAttribute("footer", "FooterWithCookies.jsp");
        }
        else if(cookie.getValue().equals("Nee")){
            request.setAttribute("zin","cookies worden niet verzameld");
            request.setAttribute("footer", "FooterWithCookies.jsp");
        }
    }

    private String SwitchCookies(HttpServletRequest request, HttpServletResponse response, String Antwoord){
        Cookie c = new Cookie("Antwoord",Antwoord);
        response.addCookie(c);
        if(Antwoord == null || Antwoord.equals("")|| Antwoord.equals("Wijzig")){
            request.setAttribute("footer", "FooterCookies.jsp");
        }
        else if(Antwoord.equals("Ja")){
            request.setAttribute("zin","cookies worden verzameld");
            request.setAttribute("footer", "FooterWithCookies.jsp");
        }
        else if(Antwoord.equals("Nee")){
            request.setAttribute("zin","cookies worden niet verzameld");
            request.setAttribute("footer", "FooterWithCookies.jsp");
        }
        response.addCookie(c);
        //return home(request,response);
        request.setAttribute("topscoorder", spelerDB.getSpelerMetMeesteGoals());
        return "index.jsp";
    }
    private Cookie getCookieWithKey(HttpServletRequest request, String key){
        Cookie[] cookies = request.getCookies();
        if(cookies==null){
            return null;
        }
        for(Cookie cookie: cookies){
            if(cookie.getName().equals(key)){
                return cookie;
            }
        }
        return null;
    }

}
