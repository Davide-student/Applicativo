package myHackaton;

public class Team {
    private String nome;
    private int numeroMembri;
    public void Team(){   //Costruttore della classe
        this.nome = "";
        this.numeroMembri = 0;
    }


    //Definizione e dichiarazione metodi


    //Metodi per il setting degli attributi delle istanze di "Team"
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setNumeroMembri(int numeroMembri) {
        this.numeroMembri = numeroMembri;
    }
    //Metodi per ottenere i valori degli attributi delle istanze di "Team"
    public String getNome() {
        return nome;
    }
    public int getNumeroMembri() {
        return numeroMembri;
    }

    public void caricaNuovaVersione(String descrizioneDocumento)    //Usato per caricare un nuovo documento riguardante un aggiornamento
    {
        //
    }


}
