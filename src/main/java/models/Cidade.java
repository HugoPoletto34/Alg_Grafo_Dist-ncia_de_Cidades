package models;

public class Cidade {
  private double latitude;
  private double longitude;
  private String nome;

  public Cidade(String nome, double latitude, double longitude) {
    this.nome = nome;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public String getNome() {
    return nome;
  }
  
  public void setNome(String nome) {
	    this.nome = nome;
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
  
  public double paraDouble(String palavra) {
	  return Double.parseDouble(palavra);
	}

  @Override
  public String toString() {
    return this.nome;
  }

}
