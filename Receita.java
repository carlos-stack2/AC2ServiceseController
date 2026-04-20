package com.hospital.hospital.model;

import jakarta.persistence.*;

@Entity
@Table(name = "receitas")
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medicamento;
    private String posologia;
    private String dataEmissao;

    @ManyToOne
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMedicamento() { return medicamento; }
    public void setMedicamento(String medicamento) { this.medicamento = medicamento; }

    public String getPosologia() { return posologia; }
    public void setPosologia(String posologia) { this.posologia = posologia; }

    public String getDataEmissao() { return dataEmissao; }
    public void setDataEmissao(String dataEmissao) { this.dataEmissao = dataEmissao; }

    public Consulta getConsulta() { return consulta; }
    public void setConsulta(Consulta consulta) { this.consulta = consulta; }
}