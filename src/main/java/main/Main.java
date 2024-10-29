package main;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Dobavljac;
import org.hibernate.HibernateException;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;

/*
//prethodne verzije
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
 */
import util.HibernateUtility;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) throws HibernateException {
        Main m = new Main();

        List<Dobavljac> dobavljaci = m.pronadjiDobavljaceFullText("čačak кремпита");//Чачак Котор //Čačak Котор //кремпита
        System.out.println(dobavljaci);
        HibernateUtility.getSessionFactory().close();
    }

    public List<Dobavljac> pronadjiDobavljaceFullText(String keyword) {
        List dobavljaci = null;
        try {
            /*
            //prethodne verzije
            //otvaranje sesije
            Session session = HibernateUtility.getSessionFactory().openSession();
            //kreiranje sesije za FullText upite
            FullTextSession fullTextSession = Search.getFullTextSession(session);
            //pravljenje/osvjezavanje indeksera
            fullTextSession.createIndexer().startAndWait();
             */
            SearchSession searchSession = Search.session(HibernateUtility.getSessionFactory().openSession());

            MassIndexer indexer = searchSession.massIndexer(Dobavljac.class)
                    .threadsToLoadObjects(7);

            indexer.startAndWait();

            //pretraga
            SearchResult<Dobavljac> result = searchSession.search(Dobavljac.class)
                    .where(f -> f.match()
                    .fields("naziv", "adresa", "proizvodi.naziv")
                    .matching(keyword))
                    .fetch(20);

            long totalHitCount = result.total().hitCount();
            dobavljaci = result.hits();
            return dobavljaci;
            /*
            //prethodne verzije
            //pocetak transakcije
            fullTextSession.beginTransaction();
            QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Dobavljac.class).get();
            //pravljenje Lucene upita
            org.apache.lucene.search.Query luceneQuery = qb
                    .keyword()
                    .onFields("naziv", "adresa", "proizvodi.naziv")
                    .matching(keyword)
                    .createQuery();

            // konveryija Lucene upita u Query
            org.hibernate.Query query = fullTextSession.createFullTextQuery(luceneQuery, Dobavljac.class);

            // izvrsenje upita
            dobavljaci = query.list();
            return dobavljaci;
             */
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dobavljaci;
    }

}
