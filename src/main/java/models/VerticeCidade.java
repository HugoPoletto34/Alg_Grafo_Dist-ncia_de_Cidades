package models;

public class VerticeCidade extends Vertice {
  private double latitude;
  private double longitude;
  private String nome;

  public VerticeCidade(int id, String nome, double latitude, double longitude) {
    super(id);
    this.nome = nome;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public String getNome() {
    return nome;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLatitude() {
    return this.latitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public double getLongitude() {
    return this.longitude;
  }

}
