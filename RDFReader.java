/***
 * Author: Jorge Chong
 * Prof: Antoine Zimmermann
 * Subject: Semantic Web
 * 
 */
package semweb;
import java.util.Iterator;
import java.util.Map;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.rdf.model.NodeIterator;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.SimpleSelector;

import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.rdf.model.StmtIterator; 


public class RDFReader {
	// public static final String RDF_FILE = "http://www.bbc.co.uk/nature/life/Human.rdf";
	// public static final String RDF_FILE = "http://www.bbc.co.uk/nature/life/Great_White_Shark.rdf";

	public static void main(String[] args) {
		String firstArg;
		firstArg = "http://www.bbc.co.uk/nature/life/Human.rdf";
		if (args.length > 0) {
		    try {
		        firstArg = args[0];
		    } catch (NumberFormatException e) {
		        System.err.println("Argument" + args[0] + " must be an integer.");
		        System.exit(1);
		    }
		}
		
		Model model = ModelFactory.createDefaultModel();
		// model.read(firstArg);
		Property RDF_LABEL = model.createProperty("http://www.w3.org/2000/01/rdf-schema#label");
		Property FOAF_DEPICTION = model.createProperty("http://xmlns.com/foaf/0.1/depiction");
		Property WO_SPECIES = model.createProperty("http://purl.org/ontology/wo/Species");
		Property WO_NAME = model.createProperty("http://purl.org/ontology/wo/name");
		Property WO_COMMON_NAME = model.createProperty("http://purl.org/ontology/wo/commonName");
		Property WO_KINGDOM = model.createProperty("http://purl.org/ontology/wo/kingdom");
		Property WO_KINGDOM_NAME = model.createProperty("http://purl.org/ontology/wo/kingdomName");
		Property WO_PHYLUM = model.createProperty("http://purl.org/ontology/wo/phylum");
		Property WO_PHYLUM_NAME = model.createProperty("http://purl.org/ontology/wo/phylumName");
		Property WO_CLASS = model.createProperty("http://purl.org/ontology/wo/class");
		Property WO_CLASS_NAME = model.createProperty("http://purl.org/ontology/wo/className");
		Property WO_ORDER = model.createProperty("http://purl.org/ontology/wo/order");
		Property WO_ORDER_NAME = model.createProperty("http://purl.org/ontology/wo/orderName");
		Property WO_FAMILY = model.createProperty("http://purl.org/ontology/wo/family");
		Property WO_FAMILY_NAME = model.createProperty("http://purl.org/ontology/wo/familyName");
		Property WO_GENUS = model.createProperty("http://purl.org/ontology/wo/genus");
		Property WO_GENUS_NAME = model.createProperty("http://purl.org/ontology/wo/genusName");
		Property DC_DESCRIPTION = model.createProperty("http://purl.org/dc/terms/description");

		model.read(firstArg);
		
		/** Get the name of the Species */
		StmtIterator iter = model.listStatements(
			    new SimpleSelector(null, null, (RDFNode) WO_SPECIES) {
			        public Statement selects(Statement s)
			            {return s;}
			    });
		
		String Obj = "";
		String ObjLink = "";
		if (iter.hasNext()) {
            // System.out.println("The database contains shit for:");
            while (iter.hasNext()) {
                // System.out.println("  " + iter.nextStatement().getSubject().toString());
            	Statement StObj = iter.nextStatement();
            	Obj = StObj.getSubject().toString();
            	ObjLink = StObj.getResource().toString();
            	System.out.println(Obj);
            	System.out.println(ObjLink);
            }
        } else {
            System.out.println("No Species were found in the database");
        }
		
		/** Get The Name of the Species */
		
		Resource THE_SPECIES = model.createResource(Obj);
		iter = model.listStatements(THE_SPECIES, RDF_LABEL, (RDFNode) null);
		
		String SpeciesName = "";
		
		if (iter.hasNext()) {
            // System.out.println("The database contains shit for:");
            while (iter.hasNext()) {
                // System.out.println("  " + iter.nextStatement().getSubject().toString());
            	Statement st = iter.nextStatement();
            	SpeciesName = st.getString();
            }
        } else {
            System.out.println("No Label found for the Species were found in the database");
        }
		
		/** Get the Image: foaf:depiction */
		//Property WO_SPECIES_NAME = model.createProperty(THE_SPECIES + "#name");
		iter = model.listStatements(THE_SPECIES, FOAF_DEPICTION, (RDFNode) null);
		
		String Image = "";
		
		if (iter.hasNext()) {
            // System.out.println("The database contains shit for:");
            while (iter.hasNext()) {
                // System.out.println("  " + iter.nextStatement().getSubject().toString());
            	Statement st = iter.nextStatement();
            	Image = st.getResource().toString();
            	// System.out.println(Image);
            }
        } else {
            System.out.println("No Image found for the Species were found in the database");
        }
		
		// Get the IRI of the name property
		iter = model.listStatements(THE_SPECIES, WO_NAME, (RDFNode) null);
		
		String SpeciesName_IRI = "";
		
		if (iter.hasNext()) {
            // System.out.println("The database contains shit for:");
            while (iter.hasNext()) {
                // System.out.println("  " + iter.nextStatement().getSubject().toString());
            	Statement st = iter.nextStatement();
            	SpeciesName_IRI = st.getResource().toString();
            	//System.out.println(SpeciesName_IRI);
            }
        } else {
            System.out.println("No Species IRI found for the Species were found in the database");
        }
		
		
		// Get the Descripcion of the name property
		iter = model.listStatements(THE_SPECIES, DC_DESCRIPTION, (RDFNode) null);
		
		String Dc_Description = "";
		
		if (iter.hasNext()) {
            // System.out.println("The database contains shit for:");
            while (iter.hasNext()) {
                // System.out.println("  " + iter.nextStatement().getSubject().toString());
            	Statement st = iter.nextStatement();
            	Dc_Description = st.getObject().toString();
            	// System.out.println(Dc_Description);
            }
        } else {
            System.out.println("No Description found for the Species were found in the database");
        }
		
		
		Resource WO_NAME_RS = model.createResource(SpeciesName_IRI);
		// Get the Common Name of the name property
		iter = model.listStatements(WO_NAME_RS, WO_COMMON_NAME, (RDFNode) null);
		
		// Get the wo:commonName
		String Wo_CommonName = "";
		String HTML_LI = "";
		StringBuilder HTML_LI_STRBL = new StringBuilder();
		if (iter.hasNext()) {
            // System.out.println("The database contains shit for:");
            while (iter.hasNext()) {
                // System.out.println("  " + iter.nextStatement().getSubject().toString());
            	Statement st = iter.nextStatement();
            	Wo_CommonName = st.getObject().toString();
            	HTML_LI_STRBL.append("<li>" + Wo_CommonName + "</li>\n");
            	// System.out.println(Wo_CommonName);
            }
            HTML_LI = HTML_LI_STRBL.toString();
        } else {
            System.out.println("No Common Name found for the Species were found in the database");
        }
		
		// Get the Kingdom of the name property
		iter = model.listStatements(THE_SPECIES, WO_KINGDOM, (RDFNode) null);
		
		String Wo_Kingdom_IRI = "";
		if (iter.hasNext()) {
            // System.out.println("The database contains shit for:");
            while (iter.hasNext()) {
                // System.out.println("  " + iter.nextStatement().getSubject().toString());
            	Statement st = iter.nextStatement();
            	Wo_Kingdom_IRI = st.getObject().toString();
            	//System.out.println(Wo_Kingdom_IRI);
            }
            
        } else {
            System.out.println("No Kingdom IRI found for the Species were found in the database");
        }
		
		iter = model.listStatements(WO_NAME_RS, WO_KINGDOM_NAME, (RDFNode) null);
		
		String Wo_Kingdom_Name = "";
		if (iter.hasNext()) {
            // System.out.println("The database contains shit for:");
            while (iter.hasNext()) {
                // System.out.println("  " + iter.nextStatement().getSubject().toString());
            	Statement st = iter.nextStatement();
            	Wo_Kingdom_Name = st.getObject().toString();
            	//System.out.println(Wo_Kingdom_Name);
            }
            
        } else {
            System.out.println("No Kingdom Name found for the Species were found in the database");
        }
		
		
		// Get the Phylum of the name property
		iter = model.listStatements(THE_SPECIES, WO_PHYLUM, (RDFNode) null);
		
		String Wo_Phylum_IRI = "";
		if (iter.hasNext()) {
            // System.out.println("The database contains shit for:");
            while (iter.hasNext()) {
                // System.out.println("  " + iter.nextStatement().getSubject().toString());
            	Statement st = iter.nextStatement();
            	Wo_Phylum_IRI = st.getObject().toString();
            	//System.out.println(Wo_Kingdom_IRI);
            }
            
        } else {
            System.out.println("No Phylum IRI found for the Species were found in the database");
        }
		
		iter = model.listStatements(WO_NAME_RS, WO_PHYLUM_NAME, (RDFNode) null);
		
		String Wo_Phylum_Name = "";
		if (iter.hasNext()) {
            // System.out.println("The database contains shit for:");
            while (iter.hasNext()) {
                // System.out.println("  " + iter.nextStatement().getSubject().toString());
            	Statement st = iter.nextStatement();
            	Wo_Phylum_Name = st.getObject().toString();
            	//System.out.println(Wo_Kingdom_Name);
            }
            
        } else {
            System.out.println("No Phylum Name found for the Species were found in the database");
        }
		
		
		// Get the Class of the name property
		iter = model.listStatements(THE_SPECIES, WO_CLASS, (RDFNode) null);
		
		String Wo_Class_IRI = "";
		if (iter.hasNext()) {
            // System.out.println("The database contains shit for:");
            while (iter.hasNext()) {
                // System.out.println("  " + iter.nextStatement().getSubject().toString());
            	Statement st = iter.nextStatement();
            	Wo_Class_IRI = st.getObject().toString();
            	//System.out.println(Wo_Kingdom_IRI);
            }
            
        } else {
            System.out.println("No Class IRI found for the Species were found in the database");
        }
		
		iter = model.listStatements(WO_NAME_RS, WO_CLASS_NAME, (RDFNode) null);
		
		String Wo_Class_Name = "";
		if (iter.hasNext()) {
            // System.out.println("The database contains shit for:");
            while (iter.hasNext()) {
                // System.out.println("  " + iter.nextStatement().getSubject().toString());
            	Statement st = iter.nextStatement();
            	Wo_Class_Name = st.getObject().toString();
            	//System.out.println(Wo_Kingdom_Name);
            }
            
        } else {
            System.out.println("No Class Name found for the Species were found in the database");
        }
		
		
		// Get the Order of the name property
		iter = model.listStatements(THE_SPECIES, WO_ORDER, (RDFNode) null);
		
		String Wo_Order_IRI = "";
		if (iter.hasNext()) {
            // System.out.println("The database contains shit for:");
            while (iter.hasNext()) {
                // System.out.println("  " + iter.nextStatement().getSubject().toString());
            	Statement st = iter.nextStatement();
            	Wo_Order_IRI = st.getObject().toString();
            	//System.out.println(Wo_Kingdom_IRI);
            }
            
        } else {
            System.out.println("No Order IRI found for the Species were found in the database");
        }
		
		iter = model.listStatements(WO_NAME_RS, WO_ORDER_NAME, (RDFNode) null);
		
		String Wo_Order_Name = "";
		if (iter.hasNext()) {
            // System.out.println("The database contains shit for:");
            while (iter.hasNext()) {
                // System.out.println("  " + iter.nextStatement().getSubject().toString());
            	Statement st = iter.nextStatement();
            	Wo_Order_Name = st.getObject().toString();
            	//System.out.println(Wo_Kingdom_Name);
            }
            
        } else {
            System.out.println("No Order Name found for the Species were found in the database");
        }
		
		// Get the Family of the name property
		iter = model.listStatements(THE_SPECIES, WO_FAMILY, (RDFNode) null);
		
		String Wo_Family_IRI = "";
		if (iter.hasNext()) {
            // System.out.println("The database contains shit for:");
            while (iter.hasNext()) {
                // System.out.println("  " + iter.nextStatement().getSubject().toString());
            	Statement st = iter.nextStatement();
            	Wo_Family_IRI = st.getObject().toString();
            	//System.out.println(Wo_Kingdom_IRI);
            }
            
        } else {
            System.out.println("No Order IRI found for the Species were found in the database");
        }
		
		iter = model.listStatements(WO_NAME_RS, WO_FAMILY_NAME, (RDFNode) null);
		
		String Wo_Family_Name = "";
		if (iter.hasNext()) {
            // System.out.println("The database contains shit for:");
            while (iter.hasNext()) {
                // System.out.println("  " + iter.nextStatement().getSubject().toString());
            	Statement st = iter.nextStatement();
            	Wo_Family_Name = st.getObject().toString();
            	//System.out.println(Wo_Kingdom_Name);
            }
            
        } else {
            System.out.println("No Family Name found for the Species were found in the database");
        }
		
		// Get the Genus of the name property
		iter = model.listStatements(THE_SPECIES, WO_GENUS, (RDFNode) null);
		
		String Wo_Genus_IRI = "";
		if (iter.hasNext()) {
            // System.out.println("The database contains shit for:");
            while (iter.hasNext()) {
                // System.out.println("  " + iter.nextStatement().getSubject().toString());
            	Statement st = iter.nextStatement();
            	Wo_Genus_IRI = st.getObject().toString();
            	//System.out.println(Wo_Kingdom_IRI);
            }
            
        } else {
            System.out.println("No Genus IRI found for the Species were found in the database");
        }
		
		iter = model.listStatements(WO_NAME_RS, WO_GENUS_NAME, (RDFNode) null);
		
		String Wo_Genus_Name = "";
		if (iter.hasNext()) {
            // System.out.println("The database contains shit for:");
            while (iter.hasNext()) {
                // System.out.println("  " + iter.nextStatement().getSubject().toString());
            	Statement st = iter.nextStatement();
            	Wo_Genus_Name = st.getObject().toString();
            	//System.out.println(Wo_Kingdom_Name);
            }
            
        } else {
            System.out.println("No Genus Name found for the Species were found in the database");
        }
		
		
		
		// The HTML generation
		String HTML = "";
		
		HTML =  new StringBuilder()
		           .append("<!DOCTYPE html>\n")
		           .append("<html>\n")
		           .append("  <head>\n")
		           .append("    <title>" + SpeciesName + "</title>\n")
		           .append("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n")
		           .append("    <style>\n")
		           .append("      .img {\n")
		           .append("	float: right;\n")
		           .append("      }\n")
		           .append("      .content {\n")
		           .append("	float: left;\n")
		           .append("      }\n")
		           .append("      .main {\n")
		           .append("	width: 974px;\n")
		           .append("	margin: 0 auto;")
		           .append("	padding: 0px;\n")
		           .append("	background: transparent;\n")
		           .append("	color: white;\n")
		           .append("	font-family: sans-serif;\n")
		           .append("	position: relative;\n")
		           .append("      }\n")
		           .append("      body {\n")
		           .append("	background-color: rgb(26,26,26);\n")
		           .append("	background-image: url('http://static.bbci.co.uk/naturelibrary/3.1.37/css/2/f/banners/2/fur_banner.jpg');\n")
		           .append("	background-repeat: no-repeat;\n")
		           .append("	background-position: 50% 0px;\n")
		           .append("	background-attachment: scroll;\n")
		           .append("      }\n")
		           .append("      a {\n")
		           .append("	color: rgb(240,140,75);\n")
		           .append("      }\n")
		           .append("    </style>\n")
		           .append("  </head>\n")
		           .append("  <body>\n")
		           .append("  <div class=\"main\">\n")
		           .append("    <div class=\"img\">\n")
		           .append("      <img alt=\"" + SpeciesName + "\" src=\"" + Image + "\"/>\n")
		           .append("    </div>\n")
		           .append("    <h1><a href=\"" + SpeciesName_IRI + "\">" + SpeciesName + "</a></h1>\n")
		           .append("    <p class=\"content\">" + Dc_Description + "</p>\n")
		           .append("    <div>\n")
		           .append("      <p>Scientific name: <a href=\""+ SpeciesName_IRI + "\">" + SpeciesName + "</a></p>\n")
		           .append("      <p>Common names:</p>\n")
		           .append("      <ul>\n")
		           .append(HTML_LI)
		           .append("      </ul>\n")
		           .append("      <p>Classification:</p>\n")
		           .append("      <ul>\n")
		           .append("	<li>Kingdom: <a href=\"" + Wo_Kingdom_IRI + "\">" + Wo_Kingdom_Name + "</a></li>\n")
		           .append("	<li>Phylum: <a href=\"" + Wo_Phylum_IRI +"\">" + Wo_Phylum_Name + "</a></li>\n")
		           .append("	<li>Class: <a href=\"" + Wo_Class_IRI + "\">" + Wo_Class_Name + "</a></li>\n")
		           .append("	<li>Order: <a href=\"" + Wo_Order_IRI + "\">" + Wo_Order_Name + "</a></li>\n")
		           .append("	<li>Family: <a href=\"" + Wo_Family_IRI + "\">" + Wo_Family_Name + "</a></li>\n")
		           .append("	<li>Genus: <a href=\"" + Wo_Genus_IRI + "\">" + Wo_Genus_Name + "</a></li>\n")
		           .append("      </ul>\n")
		           .append("    </div>\n")
		           .append("  </div>\n")
		           .append("  </body>\n")
		           .append("</html>\n")
		           .toString();
		
		System.out.println(HTML);
	}
}
