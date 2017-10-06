package br.com.luxoft.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
@Table(name = "InstrumentPriceModifier")
public class InstrumentPriceModifier implements Serializable {

	private static final long serialVersionUID = 1612367375090296691L;

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;

	private String instrumentName;
	
	private BigDecimal multiplier;

}