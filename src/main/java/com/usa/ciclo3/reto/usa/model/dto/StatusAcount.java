package com.usa.ciclo3.reto.usa.model.dto;

public class StatusAcount {

    private Integer completed;
    private Integer cancelled;

    public StatusAcount(int completed,int cancelled){
        this.completed=completed;
        this.cancelled=cancelled;
    }




    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public Integer getCancelled() {
        return cancelled;
    }

    public void setCancelled(Integer cancelled) {
        this.cancelled = cancelled;
    }
}
