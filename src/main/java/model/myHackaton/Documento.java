package myHackaton;

import java.util.Date;

public class Documento {
    private String descrizioneAggiornamento;    //Descrivi in maniera sostanziale il contenuto dell'aggiornamento
    private Date dataRilascio;  //Mantiene la data di rilascio dell'aggiornamento

    //Definizione e dichiarazione metodi
    public void Documento(){    //Costruttore della classe
        this.descrizioneAggiornamento = "";
        this.dataRilascio = null;
    }

    //Metodi di set per gli attributi delle istanze
    public void setDescrizioneAggiornamento(String descrizioneAggiornamento) {
        this.descrizioneAggiornamento = descrizioneAggiornamento;
    }
    public void setDataRilascio(Date dataRilascio) {
        this.dataRilascio = dataRilascio;
    }


    //Metodi di get degli attributi delle istanze
    public String getDescrizioneAggiornamento() {
        return descrizioneAggiornamento;
    }
    public Date getDataRilascio() {
        return dataRilascio;
    }


}
