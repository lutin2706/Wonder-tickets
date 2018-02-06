package be.formation.spring.labo.model.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderForm {

    @Min(value = 1, message = "Le nombre de tickets doit être d'au moins 1")
    private int nbr;

    @NotNull(message = "Please choose an expedition mode")
    String expedition;

    long eventId;

    public OrderForm() {
    }

    public OrderForm(long eventId) {
        this.eventId = eventId;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public String getExpedition() {
        return expedition;
    }

    public void setExpedition(String expedition) {
        this.expedition = expedition;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }
}
