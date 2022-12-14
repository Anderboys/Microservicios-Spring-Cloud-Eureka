package com.ander.commons.examenes.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="examenes")
public class Examen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty //para q no sea vacio
	@Size(min = 4,max = 30)
	private String nombre;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_at")
	private Date createAt;
	
	
	//33. -1 Añadiendo relación bidireccional en examen con sus preguntas
	@JsonIgnoreProperties(value = {"examen"} , allowSetters = true) // para evitar relacion inversa
	@OneToMany(mappedBy="examen", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pregunta> preguntas;	
	
	//40. -2 creando el atributo y relacion 
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull // que sea distinto a nulos para objetos
	private Asignatura asignatura;		
	
	public Asignatura getAsignatura() {
		return asignatura;
	}
	
	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}
	// ---------- end 40 -2
	

	// constructor: preguntas
	public Examen() {
		this.preguntas = new ArrayList<>();
	}

	// generar gentter and setters de List<Preguntas> preguntas
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas.clear();
		//preguntas.forEach(p -> this.addPregunta(p));		
		preguntas.forEach(this::addPregunta);		
	}
	
	public void addPregunta(Pregunta pregunta) {
		this.preguntas.add(pregunta);
		pregunta.setExamen(this);
	}
	
	public void removePregunta(Pregunta pregunta) {
		this.preguntas.remove(pregunta);
		pregunta.setExamen(null);
	}


	// inizializar fecha
	@PrePersist
	public void PrePersist() {
		this.createAt = new Date();
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	// 38. Añadiendo relación entre cursos y exámenes
	// 4 - 4.
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}		
		if(!(obj instanceof Examen)) {
			return false;
		}		
		Examen a = (Examen) obj;
	    // video #27. relacion curso con sus exámenes
		return this.id != null && this.id.equals(a.getId());
	}
	
	
	
}
