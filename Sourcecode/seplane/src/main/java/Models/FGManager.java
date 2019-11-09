package Models;

public class FGManager {
    
    private Benutzer benutzername;
    private String passwort;
    private String land;
    private Double budget;
    private Fluggesellschaft fluggesellschaft;
    

    public FGManager() {
    }
    public FGManager(Benutzer benutzername, String passwort, Fluggesellschaft fluggesellschaft) {
        
        this.benutzername = benutzername;
        this.passwort = passwort;
        this.fluggesellschaft = fluggesellschaft;
      
    }
    
    public Benutzer getBenutzername() {
        return benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public void setFluggesellschaft(Fluggesellschaft fluggesellschaft) {
        this.fluggesellschaft = fluggesellschaft;
        //nichts

    }
}
