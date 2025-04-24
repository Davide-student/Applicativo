package myHackaton;

public class Hackathon {
    private String titolo;
    private String sede;    //Nome di riferimento della sede in cui ha luogo l'hackathon
    private int giornoInizio;
    private int meseInizio;
    private int annoInizio;
    private int giornoFine;
    private int meseFine;
    private int annoFine;
    private String orarioInizio;
    private String orarioFine;
    private int massimoMembriTeam;  //Numero massimo di membri per un team dell'hackathon
    private int massimoIscritti; //Numero massimo di partecipanti possibili

    //Dichiarazione e definizione dei metodi della classe
    public void Hackathon(){    //Costruttore della classe
        this.titolo = "":
        this.sede = "";
        this.giornoInizio = 0;
        this.meseInizio = 0;
        this.annoInizio = 0;
        this.giornoFine = 0;
        this.meseFine = 0;
        this.annoFine = 0;
        this.orarioInizio = "";
        this.orarioFine = "";
        this.massimoMembriTeam = 0;
        this.massimoIscritti = 0;
    }

    public void iniziaHackathon(){  //Utilizzato per dare inizio all'hackathon

    }
    public void terminaHackathon(){ //Utilizzato per far temrinare l'hackathon

    }


    //Metodi di set per la classe


    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public void setGiornoInizio(int giornoInizio) {
        this.giornoInizio = giornoInizio;
    }

    public void setMeseInizio(int meseInizio) {
        this.meseInizio = meseInizio;
    }

    public void setAnnoInizio(int annoInizio) {
        this.annoInizio = annoInizio;
    }

    public void setGiornoFine(int giornoFine) {
        this.giornoFine = giornoFine;
    }

    public void setMeseFine(int meseFine) {
        this.meseFine = meseFine;
    }

    public void setAnnoFine(int annoFine) {
        this.annoFine = annoFine;
    }

    public void setOrarioInizio(String orarioInizio) {
        this.orarioInizio = orarioInizio;
    }

    public void setOrarioFine(String orarioFine) {
        this.orarioFine = orarioFine;
    }

    public void setMassimoMembriTeam(int massimoMembriTeam) {
        this.massimoMembriTeam = massimoMembriTeam;
    }

    public void setMassimoIscritti(int massimoIscritti) {
        this.massimoIscritti = massimoIscritti;
    }


    //Metodi di get per la classe


    public String getTitolo() {
        return titolo;
    }

    public String getSede() {
        return sede;
    }

    public int getGiornoInizio() {
        return giornoInizio;
    }

    public int getMeseInizio() {
        return meseInizio;
    }

    public int getAnnoInizio() {
        return annoInizio;
    }

    public int getGiornoFine() {
        return giornoFine;
    }

    public int getMeseFine() {
        return meseFine;
    }

    public int getAnnoFine() {
        return annoFine;
    }

    public String getOrarioInizio() {
        return orarioInizio;
    }

    public String getOrarioFine() {
        return orarioFine;
    }

    public int getMassimoMembriTeam() {
        return massimoMembriTeam;
    }

    public int getMassimoIscritti() {
        return massimoIscritti;
    }
}
