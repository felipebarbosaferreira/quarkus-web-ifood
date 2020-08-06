package com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.localizacao;

public class LocalizacaoDomain {

    public Double latitude;
    
    public Double longitude;

	public LocalizacaoDomain() {
		super();
	}

	public LocalizacaoDomain(Double latitude, Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
