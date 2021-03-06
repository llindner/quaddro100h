package br.com.luizlindner.quaddro100h.lab04.domain.model;

import java.io.Serializable;
import java.util.Locale;

import br.com.luizlindner.quaddro100h.lab04.domain.exception.LogradouroException;

/**
 * Created by Luiz on 12/07/2017.
 */

public class Logradouro implements Serializable {
    private String tipo;
    private String nome;
    private Bairro bairro;

    public Logradouro() {
        super();
        this.bairro = new Bairro();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public void setUf(UF uf) {
        getBairro().setUf(uf);
    }

    public void setMunicipioNome(String nome) {
        getBairro().setMunicipioNome(nome);
    }

    public void setBairroNome(String nome) {
        getBairro().setNome(nome);
    }

    public UF getUf(){
        return getBairro().getUf();
    }

    public String getLogradouroCompleto(){
        return String.format(Locale.getDefault(), "%s %s", getTipo(), getNome());
    }

    public void setLogradouroCompleto(String logradouroCompleto){
        String tipo;
        if(logradouroCompleto.length() > 0) {
            int index = logradouroCompleto.indexOf(" ");
            if(index > 0) {
                tipo = logradouroCompleto.substring(0, index);
                logradouroCompleto = logradouroCompleto.substring(index + 1);
            }else{
                tipo = logradouroCompleto;
                logradouroCompleto = "";
            }
        } else {
            tipo = "";
        }
        setTipo(tipo);
        setNome(logradouroCompleto);
    }

    public Integer getUFOrdinal(){
        return getBairro().getUFOrdinal();
    }

    public String getMunicipioNome(){
        return getBairro().getMunicipioNome();
    }

    @Override
    public String toString() {
        return "Logradouro{" +
                "tipo='" + tipo + '\'' +
                ", nome='" + nome + '\'' +
                ", bairro=" + bairro +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Logradouro that = (Logradouro) o;

        if (getTipo() != null ? !getTipo().equals(that.getTipo()) : that.getTipo() != null)
            return false;
        if (getNome() != null ? !getNome().equals(that.getNome()) : that.getNome() != null)
            return false;
        return getBairro() != null ? getBairro().equals(that.getBairro()) : that.getBairro() == null;

    }

    @Override
    public int hashCode() {
        int result = getTipo() != null ? getTipo().hashCode() : 0;
        result = 31 * result + (getNome() != null ? getNome().hashCode() : 0);
        result = 31 * result + (getBairro() != null ? getBairro().hashCode() : 0);
        return result;
    }

    public Municipio getMunicipio() {
        return getBairro().getMunicipio();
    }

    public void validar() throws LogradouroException{
        if(tipo == null){
            throw new LogradouroException("Tipo nulo!");
        }

        if(tipo.isEmpty()){
            throw new LogradouroException("Favor, informe o tipo!");
        }

        if(nome == null){
            throw new LogradouroException("Nome nulo!");
        }

        if(nome.isEmpty()){
            throw new LogradouroException("Favor, informe o nome");
        }

    }
}
