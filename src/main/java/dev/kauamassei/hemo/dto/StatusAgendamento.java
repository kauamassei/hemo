package dev.kauamassei.hemo.dto;

public enum StatusAgendamento {
    AGENDADO("agendado"),
    REALIZADO("realizado"),
    CANCELADO("cancelado");

    private String descricao;

    StatusAgendamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
