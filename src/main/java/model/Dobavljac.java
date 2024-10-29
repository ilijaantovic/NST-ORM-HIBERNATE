package model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.AssociationInverseSide;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

/*
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.sr.SerbianNormalizationFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.search.annotations.*;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
*/


@Entity
@Indexed

/*
@Indexed
@AnalyzerDef(name = "customanalyzer",
  tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
  filters = {
    @TokenFilterDef(factory = LowerCaseFilterFactory.class),
    @TokenFilterDef(factory = SnowballPorterFilterFactory.class),
    @TokenFilterDef(factory = SerbianNormalizationFilterFactory.class, params = {
      @Parameter(name = "haircut", value = "bald")
    })
  })
*/
public class Dobavljac {

	@Id
	private int dobavljacID;
        //@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
        //@Analyzer(definition = "customanalyzer")
        @FullTextField(analyzer = "customanalyzer")
	private String naziv;
        //@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
        //@Analyzer(definition = "customanalyzer")
        @FullTextField(analyzer = "customanalyzer")
	private String adresa;
	
        
        //@IndexedEmbedded
        
	@ManyToMany
        @IndexedEmbedded
  @JoinTable(name = "dobavlja",
	    joinColumns = {
	      @JoinColumn(name="dobavljacID", unique = true)           
	    },
	    inverseJoinColumns = {
	      @JoinColumn(name="proizvodID", unique = true)
	    }
	  )
	  @Cascade (CascadeType.ALL)
	private List<Proizvod> proizvodi;

	
	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public int getDobavljacID() {
		return dobavljacID;
	}

	public void setDobavljacID(int dobavljacID) {
		this.dobavljacID = dobavljacID;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Proizvod> getProizvodi() {
		return proizvodi;
	}

	public void setProizvodi(List<Proizvod> proizvodi) {
		this.proizvodi = proizvodi;
	}
	
	@Override
	public String toString() {
		
		return "Dobavljac ID: "+dobavljacID+", Naziv: "+naziv+", Adresa: "+adresa+", Proizvodi: "+proizvodi.toString();
	}
	
}
