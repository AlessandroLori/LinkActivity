package linkactivity.linkactivity;

import java.time.LocalDate;
import java.util.Date;

public class EventBean {

    String eventName;
    String dataEvento;
    String expirationDate;
    String description;

    int partecipantNumber;

    String nomeAzienda;

    String tag;




    public EventBean(){}    //costruttore vuoto

    public EventBean(String eventName, String description, String dataEvento, String expirationDate, int partecipantNumber, String nomeAzienda, String tag ){
        super();
        this.setEventName(eventName);
        this.setDescription(description);
        this.setDataEvento(dataEvento);
        this.setExpirationDate(expirationDate);
        this.setPartecipantNumber(partecipantNumber);
        this.setNomeAzienda(nomeAzienda);
        this.setTag(tag);
        System.out.println(eventName+" aaaaaaaaaaaaaaaa");
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {

        this.dataEvento=dataEvento;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate=expirationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description=description;
    }

    public Integer getPartecipantNumber() {
        return partecipantNumber;
    }

    public void setPartecipantNumber(int partecipantNumber) {
        this.partecipantNumber=partecipantNumber;
    }

    public String getNomeAzienda() {
        return nomeAzienda;
    }

    public void setNomeAzienda(String nomeAzienda) {
        this.nomeAzienda=nomeAzienda;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag=tag;
    }
}