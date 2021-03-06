package org.isatools.linkedISA.converter;

import org.isatools.linkedISA.mapping.ISA2OWLMappingParser;
import org.isatools.linkedISA.mapping.ISASyntax2OWLMapping;
import org.isatools.linkedISA.mapping.ISASyntax2OWLMappingFiles;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;

/**
 * Created by agbeltran on 19/06/2014.
 */
public class ISAtab2OWLFolderConverterTest {

    private String configDir = null;
    private ISA2OWLMappingParser parser = null;
    private ISASyntax2OWLMapping mapping = null;
    private ISAtab2OWLConverter isatab2owl = null;
    private ISAtab2OWLFolderConverter isatab2owlfolder = null;


    @Before
    public void setUp() throws Exception {
        //configDir = getClass().getResource("/configurations/isaconfig-default_v2014-01-16").getFile();
        configDir = getClass().getResource("/configurations/isaconfig-Scientific-Data-v1.2").getFile();

        //parsing mappings
        parser = new ISA2OWLMappingParser();
        URL isa_obi_mapping_url = getClass().getClassLoader().getResource(ISASyntax2OWLMappingFiles.ISA_OBI_MAPPING_FILENAME);
        System.out.println("isa_obi_mapping_url="+isa_obi_mapping_url);
        parser.parseCSVMappingFile(isa_obi_mapping_url.toURI().getRawPath().toString());

        URL isa_isa_mapping_url = getClass().getClassLoader().getResource(ISASyntax2OWLMappingFiles.ISA_ISA_MAPPING_FILENAME);
        System.out.println("isa_isa_mapping_url="+isa_isa_mapping_url);
        parser.parseCSVMappingFile(isa_isa_mapping_url.toURI().getRawPath().toString());

        mapping = parser.getMapping();

        System.out.println("MAPPING-----");
        System.out.println(mapping);


        isatab2owl = new ISAtab2OWLConverter(configDir, mapping);
        isatab2owlfolder = new ISAtab2OWLFolderConverter(isatab2owl);
    }

    @Test
    public void convertMetabolightsDatasets() throws Exception {
        isatab2owlfolder.convert("/Users/agbeltran/Metabolights/","http://w3id.org/isa/metabolights/","/Users/agbeltran/Metabolights/rdf/");
    }

    @Test
    public void convertSDataDatasets() throws Exception {
        isatab2owlfolder.convert("/Users/agbeltran/ScientificData/isa-tab","http://w3id.org/isa/sdata/","/Users/agbeltran/ScientificData/rdf/");
    }

}