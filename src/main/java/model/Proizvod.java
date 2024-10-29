package model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
/*
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
 */
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

@Entity
@Indexed
public class Proizvod {

    @Id
    private int proizvodID;
//            @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)  
//            @Analyzer(definition = "customanalyzer")
    @FullTextField(analyzer = "customanalyzer")
    private String naziv;
    private double cena;

    @ManyToMany(mappedBy = "proizvodi")
    List<Dobavljac> dobavljaci;

    public Proizvod() {
    }

    public Proizvod(int proizvodID, String naziv, double cena) {
        this.proizvodID = proizvodID;
        this.naziv = naziv;
        this.cena = cena;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String toString() {
        return "ProizvodID: " + getProizvodID() + "\tNaziv: " + naziv + "\tCena: " + cena;
    }

    public int getProizvodID() {
        return proizvodID;
    }

    public void setProizvodID(int proizvodID) {
        this.proizvodID = proizvodID;
    }

    public List<Dobavljac> getDobavljaci() {
        return dobavljaci;
    }

    public void setDobavljaci(List<Dobavljac> dobavljaci) {
        this.dobavljaci = dobavljaci;
    }

}
