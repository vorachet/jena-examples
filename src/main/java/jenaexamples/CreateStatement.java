package jenaexamples;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

public class CreateStatement {

	public static void main(String[] args) {
		String resourceURI = "http://somewhere/Thing";
		String theLiteralValue1 = "A";
		Property theProperty1 = ResourceFactory.createProperty("http://myontology/vocab#name");

		Model model = ModelFactory.createDefaultModel();		
		Resource myFirstResource =
		        model.createResource(resourceURI)
		             .addProperty(theProperty1, theLiteralValue1);

		System.out.println(myFirstResource.getNameSpace()); // http://somewhere/
		System.out.println(myFirstResource.getLocalName()); // Thing
		System.out.println(myFirstResource.getURI());       // http://somewhere/Thing
		System.out.println(myFirstResource.isResource());   // true
		System.out.println(myFirstResource.isLiteral());    // false
		System.out.println(myFirstResource.toString());     // http://somewhere/Thing

		StmtIterator iter = myFirstResource.listProperties();
		while (iter.hasNext()) {
		    Statement stmt      = iter.nextStatement();  
		    Resource  subject   = stmt.getSubject();     
		    Property  predicate = stmt.getPredicate();   
		    RDFNode   object    = stmt.getObject();      

		    System.out.print(subject.toString());
		    System.out.print(" " + predicate.toString() + " ");
		    if (object instanceof Resource) {
		       System.out.print(object.toString());
		    } else {
		        System.out.print(" \"" + object.toString() + "\"");
		    }

		    System.out.println(" .");
		} 
		
		// http://somewhere/Thing http://myontology/vocab#name  "A" .
	}

}
