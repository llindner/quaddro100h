package br.com.luizlindner.quaddro100h.lab04.domain.model;

import android.util.Log;

import java.io.Serializable;
import java.util.Locale;

import br.com.luizlindner.quaddro100h.lab04.domain.exception.BairroException;
import br.com.luizlindner.quaddro100h.lab04.domain.exception.CEPException;
import br.com.luizlindner.quaddro100h.lab04.domain.exception.EnderecoException;
import br.com.luizlindner.quaddro100h.lab04.domain.exception.LogradouroException;
import br.com.luizlindner.quaddro100h.lab04.domain.exception.MunicipioException;

/**
 * Created by Luiz on 12/07/2017.
 */

public class Endereco implements Serializable {
    // Alameda Santos, 1000, andar 7 , Jardim Paulista, 00000-000, São Paulo, SP

    private Long id;
    private String numero;
    private String complemento;
    private CEP cep;
    private Logradouro logradouro;

    private Endereco(){
        super();
        this.cep = CEP.getInstance();
        this.logradouro = new Logradouro();
    }

    public Endereco(Long id){
        this();

        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public CEP getCep() {
        return cep;
    }

    public void setCep(CEP cep) {
        this.cep = cep;
    }

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public void setUf(UF uf) {
        getLogradouro().setUf(uf);
    }

    public void setMunicipioNome(String nome) {
        getLogradouro().setMunicipioNome(nome);
    }

    public void setBairroNome(String nome) {
        getLogradouro().setBairroNome(nome);
    }

    public String getLogradouroTipo() {
        return getLogradouro().getTipo();
    }

    public String getLogradouroNome(){
        return getLogradouro().getNome();
    }

    public void setLogradouroTipo(String tipo) {
        getLogradouro().setTipo(tipo);
    }

    public void setLogradouroNome(String nome) {
        getLogradouro().setNome(nome);
    }

    public void setCEPCodigo(String codigo) {
        getCep().setCodigo(codigo);
    }

    public static Endereco getInstance(){
        return new Endereco();
    }

    public static Endereco getInstance(Long id){
        return new Endereco(id);
    }

    public UF getUf(){
        return getLogradouro().getUf();
    }

    public Integer getUFOrdinal(){
        return getLogradouro().getUFOrdinal();
    }

    public String getMunicipioNome(){
        return getLogradouro().getMunicipioNome();
    }

    public Bairro getBairro(){
        return getLogradouro().getBairro();
    }

    public Municipio getMunicipio(){
        return getLogradouro().getMunicipio();
    }

    public boolean isNullId(){
        return getId() == null;
    }

    public static Endereco of(String cep, String logradouroTipo, String logradouroNome, String numero, String complemento, String bairro, String municipio, UF uf) {
        Endereco e = getInstance();

        e.setCEPCodigo(cep);
        e.setLogradouroTipo(logradouroTipo);
        e.setLogradouroNome(logradouroNome);
        e.setNumero(numero);
        e.setComplemento(complemento);
        e.setBairroNome(bairro);
        e.setMunicipioNome(municipio);
        e.setUf(uf);

        return e;
    }

    public static Endereco of(String cep, String logradouroCompleto, String bairro, String municipio, String uf){
        Endereco e = getInstance();

        e.setCEPCodigo(cep);
        int index = logradouroCompleto.indexOf(" ");
        e.setLogradouroTipo(logradouroCompleto.substring(0, index));
        e.setLogradouroNome(logradouroCompleto.substring(index+1));
        e.setNumero("");
        e.setComplemento("");
        e.setBairroNome(bairro);
        e.setMunicipioNome(municipio);
        e.setUf(UF.valueOf(uf));

        return e;
    }

    public static Endereco of(String cep, String logradouroCompleto, String numero, String complemento, String bairro, String municipio, UF uf) {
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

        return of(cep, tipo, logradouroCompleto, numero, complemento, bairro, municipio, uf);
    }

    public void validar() throws EnderecoException{
    }

    public void validarLogradouro() throws LogradouroException{
        getLogradouro().validar();
    }

    public void validarCEP() throws CEPException{
        getCep().validar();
    }

    public void validarBairro() throws BairroException{
        getBairro().validar();
    }

    public void validarMunicipio() throws MunicipioException{
        getMunicipio().validar();
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                ", cep=" + cep +
                ", logradouro=" + logradouro +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Endereco endereco = (Endereco) o;

        if (getNumero() != null ? !getNumero().equals(endereco.getNumero()) : endereco.getNumero() != null)
            return false;
        if (getComplemento() != null ? !getComplemento().equals(endereco.getComplemento()) : endereco.getComplemento() != null)
            return false;
        if (getCep() != null ? !getCep().equals(endereco.getCep()) : endereco.getCep() != null)
            return false;
        return getLogradouro() != null ? getLogradouro().equals(endereco.getLogradouro()) : endereco.getLogradouro() == null;

    }

    @Override
    public int hashCode() {
        int result = getNumero() != null ? getNumero().hashCode() : 0;
        result = 31 * result + (getComplemento() != null ? getComplemento().hashCode() : 0);
        result = 31 * result + (getCep() != null ? getCep().hashCode() : 0);
        result = 31 * result + (getLogradouro() != null ? getLogradouro().hashCode() : 0);
        return result;
    }
}
