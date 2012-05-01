package org.springframework.test.web.issue34.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties({"action"})
@Entity
@Table(name = "someBean")
public class SomeBean implements Serializable {

	private static final long serialVersionUID = -7618972426188924888L;

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column( name = "Id", nullable = false  )
	private Long id = 0L; // init for hibernate bug workaround
	
    @Basic
	@Column( name = "Name" )
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
