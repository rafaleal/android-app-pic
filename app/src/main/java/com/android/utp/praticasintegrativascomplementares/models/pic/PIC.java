package com.android.utp.praticasintegrativascomplementares.models.pic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class PIC {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("nome")
    @Expose
    private String nome;

    @SerializedName("descricao")
    @Expose
    private String descricao;

    @SerializedName("foto")
    @Expose
    private String foto;

    @SerializedName("url")
    @Expose
    private String url;

    /**
     * No args constructor for use in serialization
     *
     */
    public PIC() {
    }

    /**
     *
     * @param id
     * @param nome
     * @param foto
     * @param descricao
     * @param url
     */
    public PIC(String id, String nome, String descricao, String foto, String url) {
        super();
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.foto = foto;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "PIC{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", foto='" + foto + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
