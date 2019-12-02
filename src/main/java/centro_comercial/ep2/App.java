package centro_comercial.ep2;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.Rule;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello OWL World!" );
        //OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
        Model model = ModelFactory.createDefaultModel();
        model.read( "./EP1.owl" );
        
		Reasoner reasoner = new GenericRuleReasoner( Rule.rulesFromURL( "rules.txt" ) );
		
		InfModel infModel = ModelFactory.createInfModel( reasoner, model );
 
		StmtIterator it = infModel.listStatements();
		
		while ( it.hasNext() )
		{
			Statement stmt = it.nextStatement();
			
			Resource subject = stmt.getSubject();
			Property predicate = stmt.getPredicate();
			RDFNode object = stmt.getObject();
 
			System.out.println( subject.toString() + " " + predicate.toString() + " " + object.toString() );
		}
        
    }
}
