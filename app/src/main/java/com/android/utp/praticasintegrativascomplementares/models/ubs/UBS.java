package com.android.utp.praticasintegrativascomplementares.models.ubs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class UBS {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("nome")
    @Expose
    private String nome;

    @SerializedName("endereco")
    @Expose
    private String endereco;

    @SerializedName("cidade")
    @Expose
    private String cidade;

    @SerializedName("telefone")
    @Expose
    private String telefone;

    @SerializedName("foto")
    @Expose
    private String foto;

    @SerializedName("info")
    @Expose
    private String info;

    @SerializedName("distrito")
    @Expose
    private String distrito;

    @SerializedName("mapa_url")
    @Expose
    private String mapaUrl;

    @SerializedName("latitude")
    @Expose
    private String latitude;

    @SerializedName("longitude")
    @Expose
    private String longitude;

    /**
     * No args constructor for use in serialization
     *
     */
    public UBS() {
    }

    /**
     *
     * @param id
     * @param cidade
     * @param mapaUrl
     * @param distrito
     * @param telefone
     * @param longitude
     * @param nome
     * @param latitude
     * @param foto
     * @param endereco
     * @param info
     */
    public UBS(Integer id, String nome, String endereco, String cidade, String telefone, String foto, String info, String distrito, String mapaUrl, String latitude, String longitude) {
        super();
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.telefone = telefone;
        this.foto = foto;
        this.info = info;
        this.distrito = distrito;
        this.mapaUrl = mapaUrl;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getMapaUrl() {
        return mapaUrl;
    }

    public void setMapaUrl(String mapaUrl) {
        this.mapaUrl = mapaUrl;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "UBS{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", cidade='" + cidade + '\'' +
                ", telefone='" + telefone + '\'' +
                ", foto='" + foto + '\'' +
                ", info='" + info + '\'' +
                ", distrito='" + distrito + '\'' +
                ", mapaUrl='" + mapaUrl + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
