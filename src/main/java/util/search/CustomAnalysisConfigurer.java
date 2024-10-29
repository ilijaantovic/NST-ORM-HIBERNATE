/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.search;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.sr.SerbianNormalizationFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurationContext;
import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurer;

/**
 *
 * @author Cartman
 */
public class CustomAnalysisConfigurer implements LuceneAnalysisConfigurer{

    @Override
    public void configure(LuceneAnalysisConfigurationContext context) {
        context.analyzer( "customanalyzer" ).custom() 
                .tokenizer( StandardTokenizerFactory.class ) 
                .tokenFilter( LowerCaseFilterFactory.class ) 
                .tokenFilter( SnowballPorterFilterFactory.class ) 
                .tokenFilter(SerbianNormalizationFilterFactory.class)
                        .param( "haircut", "bald" ) 
                .tokenFilter( ASCIIFoldingFilterFactory.class );

    }
    
}
