package myHackaton;


public class UtentePiattaforma {
    private String username;
    private String nome;
    private String cognome;
    private int giornoIscrizionePiattaforma;
    private int meseIscrizionePiattaforma;
    private int annoIscrizionePiattaforma;

    //Dichirazione e definizione dei metodi della classe

    //Costruttore della classe
    public void UtentePiattaforma(String username, String nome, String cognome, int giornoIscrizionePiattaforma, int meseIscrizionePiattaforma, int annoIscrizionePiattaforma) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.giornoIscrizionePiattaforma = giornoIscrizionePiattaforma;
        this.meseIscrizionePiattaforma = meseIscrizionePiattaforma;
        this.annoIscrizionePiattaforma = annoIscrizionePiattaforma;
    }

    //Metodi di set per le istanze di classe
    public void setUsername(String username) {
        this.username = username;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public void setGiornoIscrizionePiattaforma(int giornoIscrizionePiattaforma) {
        this.giornoIscrizionePiattaforma = giornoIscrizionePiattaforma;
    }
    public void setMeseIscrizionePiattaforma(int meseIscrizionePiattaforma) {
        this.giornoIscrizionePiattaforma = meseIscrizionePiattaforma;
    }
    public void setAnnoIscrizionePiattaforma(int annoIscrizionePiattaforma) {
        this.annoIscrizionePiattaforma = annoIscrizionePiattaforma;
    }

    //Metodi di get per le istanze di classe
    public String getUsername() {
        return username;
    }
    public String getNome() {
        return nome;
    }
    public String getCognome() {
        return cognome;
    }
    public int getGiornoIscrizionePiattaforma() {
        return giornoIscrizionePiattaforma;
    }
    public int getMeseIscrizionePiattaforma() {
        return meseIscrizionePiattaforma;
    }
    public int getAnnoIscrizionePiattaforma() {
        return annoIscrizionePiattaforma;
    }



    public void registraHackathon(){    //L'utente può decidere di registrarsi ad un hackathon

    }
}