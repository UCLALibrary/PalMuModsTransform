package edu.ucla.library.dep.PalMuModsTransform;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;

import com.fasterxml.jackson.databind.ObjectMapper;


import edu.ucla.library.dep.model.PalMuJson;
import edu.ucla.library.dep.model.PalMuRecord;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class PalMuJsonModMultipleFiles {
	 Map<Integer, String> numberMapping = new HashMap<>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Map<Integer, String> type = new HashMap<>();
		type.put(23,"data (information)");
		type.put(25,"software/multimedia");
		type.put(26,"moving image");
		type.put(27,"three dimensional object");
		type.put(28,"service (general activity)");
		type.put(29,"software/multimedia");
		type.put(30,"sound recording");
		type.put(24,"still image");
		type.put(31,"text");
		 

   
		Map<Integer, String> format = new HashMap<>();
         //audio
		format.put(168,"sound recordings");
		format.put(169,"sound recordings");
		format.put(170,"sound recordings");
		format.put(171,"sound recordings");
		format.put(171,"sound recordings");
		format.put(173,"sound recordings");
		format.put(175,"sound recordings");
		format.put(176,"sound recordings");
		format.put(174,"sound recordings");
		format.put(177,"sound recordings");
		
		//text

		format.put(233,"books");
        format.put(270,"brochure");
        format.put(238,"diaries");
        format.put(248,"correspondence");
        format.put(247,"magazines (periodicals)");
        format.put(237,"manuscripts (documents)");
        format.put(236,"newspapers");
        format.put(249,"notebooks");
        format.put(239,"records (documents)");
        format.put(235,"reports");
        format.put(234,"sheet music");
        
        //image
        
        format.put(216,"black-and-white photographs");
        format.put(217,"black-and-white slides");
        format.put(221,"cartographic materials");
        format.put(214,"color photographs");
        format.put(215,"color slide");
        format.put(222,"cartographic materials");
        format.put(220,"negatives (photographs)");
        format.put(219,"paintings (visual works)");
        format.put(250,"postcards");
        format.put(218,"posters");
        format.put(251,"postage stamps");
        
        //video
        format.put(194,"video recordings");
        format.put(201,"video recordings");
        format.put(196,"video recordings");
        format.put(195,"video recordings");
        format.put(197,"video recordings");
        format.put(198,"video recordings");
        format.put(199,"video recordings");
        format.put(200,"video recordings");
        
        
        
        //language
		Map<Integer, String> language = new HashMap<>();
		language.put(153,"Arabic");
        language.put(154,"Bengali");
        language.put(155,"Chinese");
        language.put(156,"English");
        language.put(259,"Finnish");
        language.put(157,"French");
        language.put(158,"German");
        language.put(262,"Greek");
        language.put(245,"Hebrew");
        language.put(159,"Hindustani");
        language.put(160,"Italian");
        language.put(161,"Japanese");
        language.put(166,"Klingon");
        language.put(268,"Luxembourgish");
        language.put(162,"Malay");
        language.put(261,"Pashto");
        language.put(260,"Persian");
        language.put(163,"Portuguese");
        language.put(164,"Russian");
        language.put(165,"Spanish");
        language.put(267,"Tigrinya");
        
        long countArFiles = Files.list(Paths.get("C:\\Users\\pghorpade\\export\\export"))
                .filter(p -> p.toFile().isFile()).filter(p -> p.getFileName().toString().contains("ar-")).count();
        long countEnFiles = Files.list(Paths.get("C:\\Users\\pghorpade\\export\\export"))
                .filter(p -> p.toFile().isFile()).filter(p -> p.getFileName().toString().contains("en-")).count();
        Namespace namespace = Namespace.getNamespace("mods", "http://www.loc.gov/mods/v3");
		Namespace namespacexlink = Namespace.getNamespace("xlink", "http://www.w3.org/1999/xlink");
		Namespace namespacexsi = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		Namespace namespaceCopyrightMD = Namespace.getNamespace("copyrightMD", "http://www.cdlib.org/inside/diglib/copyrightMD");
		ObjectMapper mapper = new ObjectMapper();
        for (int j = 1; j < countArFiles+1; j++) {

            byte[] jsonData =  Files.readAllBytes(Paths.get("C:\\Users\\pghorpade\\export\\export\\en-"+j+".json"));
            byte[] jsonDataAr = Files.readAllBytes(Paths.get("C:\\Users\\pghorpade\\export\\export\\ar-"+j+".json"));
            
    		PalMuRecord[] records = mapper.readValue(jsonData, PalMuRecord[].class);
    		PalMuRecord[] recordsAr = mapper.readValue(jsonDataAr, PalMuRecord[].class);
		
		
		
		
		    for (int i = 0; i < records.length; i++) {
				PalMuRecord record = records[i];
				PalMuRecord recordAr = recordsAr[i];
			
				System.out.println(record);
				
				Document jdomDoc = new Document();
				// create root element
				Element rootElement = new Element("mods", namespace);
				rootElement.addNamespaceDeclaration(namespacexlink);
				rootElement.addNamespaceDeclaration(namespacexsi);
				rootElement.setAttribute("schemaLocation",
						"http://www.loc.gov/mods/v3 http://www.loc.gov/standards/mods/v3/mods-3-4.xsd", namespacexsi);
				Element childOriginInfo = new Element("originInfo", namespace);
				
				
				if(null != recordAr.getDisplay_label()) {
					Element childTitleInfo = new Element("titleInfo", namespace);
					Element childTitle = new Element("title", namespace);
					childTitle.addContent(recordAr.getDisplay_label());			
					childTitle.setAttribute("lang","ar");
					childTitleInfo.addContent(childTitle);
					rootElement.addContent(childTitleInfo);
				}
				
				if(null != record.getDisplay_label()) {
					Element childTitleInfo = new Element("titleInfo", namespace);
					Element childTitle = new Element("title", namespace);
					childTitle.addContent(record.getDisplay_label());			
					childTitle.setAttribute("type","translated");
					childTitleInfo.addContent(childTitle);
					rootElement.addContent(childTitleInfo);
				}
				
				
				if(null != record.getOriginal_url() && record.getOriginal_url().length > 0) {
					String original_url = record.getOriginal_url()[0];
					Element childIdentifier = new Element("identifier", namespace);
					childIdentifier.setAttribute("type", "uri");	
					childIdentifier.setAttribute("displayLabel", "Electronic resource (JPEG)");
					childIdentifier.addContent(original_url.substring(original_url.lastIndexOf("/")+1));
					rootElement.addContent(childIdentifier);
				}
				
				if(null != record.getIdno()) {
					Element childIdentifier = new Element("identifier", namespace);
					childIdentifier.setAttribute("type", "local");
					childIdentifier.addContent(record.getIdno());
					rootElement.addContent(childIdentifier);
				}
				if(null != record.getIdno()) {
					Element childIdentifier = new Element("identifier", namespace);
					childIdentifier.setAttribute("type", "local");
					childIdentifier.addContent(record.getId());
					rootElement.addContent(childIdentifier);
				}
				
				//description
				if(null != record.getDescription()) {
					Element childNote = new Element("note", namespace);
					childNote.addContent(record.getDescription());
					rootElement.addContent(childNote);
				}
				if(null != recordAr.getDescription()) {
					Element childNote = new Element("note", namespace);
					childNote.addContent(recordAr.getDescription());
					childNote.setAttribute("type","ar");
					rootElement.addContent(childNote);
				}
				//thumbnail
				if(null != record.getPreview_url() && record.getPreview_url().length > 0){
					
					Element childIdentifier = new Element("identifier", namespace);
					childIdentifier.setAttribute("type", "uri");
				    childIdentifier.addContent(record.getPreview_url()[0].replaceFirst("http://127.0.0.1/palarchive.org/providence/media", "http://d7rm5xoig729r.cloudfront.net"));
				    childIdentifier.setAttribute("displayLabel", "Preview Image");
					rootElement.addContent(childIdentifier);
				}
				//medium_url
				if(null != record.getMedium_url() && record.getMedium_url().length > 0) {
					
					Element childIdentifier = new Element("identifier", namespace);
					childIdentifier.setAttribute("type", "uri");
				    childIdentifier.addContent(record.getMedium_url()[0].replaceFirst("http://127.0.0.1/palarchive.org/providence/media", "http://d7rm5xoig729r.cloudfront.net"));
				    childIdentifier.setAttribute("displayLabel", "Display Image");
					rootElement.addContent(childIdentifier);
				}
				//object in context
				if(null != record.getId()) {
					Element childIdentifier = new Element("identifier", namespace);
					childIdentifier.setAttribute("type", "uri");
				    childIdentifier.addContent("http://palarchive.org/item/"+record.getId()+"/");
				    childIdentifier.setAttribute("displayLabel", "View Record");
					rootElement.addContent(childIdentifier);
				}	
				//date
				if(null != record.getDate_created()){
					Element childDate = new Element("dateCreated", namespace);
					childDate.addContent(record.getDate_created());							
					childOriginInfo.addContent(childDate);
				}
				if(null != recordAr.getDate_created()){
					Element childDate = new Element("dateCreated", namespace);
					childDate.addContent(recordAr.getDate_created());
					childDate.setAttribute("type","ar");
					childOriginInfo.addContent(childDate);
				}
				//place
				if(null != record.getCa_places()) {
					for (String place : record.getCa_places()) {
						Element childPlace = new Element("place", namespace);
						Element childPlaceTerm = new Element("placeTerm", namespace);
						childPlaceTerm.addContent(place);
						childPlace.addContent(childPlaceTerm);
						childOriginInfo.addContent(childPlace);
					}
				}
				
				if(null != recordAr.getCa_places()) {
					for (String place : recordAr.getCa_places()) {
						Element childPlace = new Element("place", namespace);
						Element childPlaceTerm = new Element("placeTerm", namespace);
						childPlaceTerm.addContent(place);
						childPlaceTerm.setAttribute("type","ar");
						childPlace.addContent(childPlaceTerm);
						childOriginInfo.addContent(childPlace);
					}
				}
				
				if (null != childOriginInfo.getChildren() && childOriginInfo.getChildren().size() > 0) {
					rootElement.addContent(childOriginInfo);
				}
				//creator
				if(null != record.getCa_entities()) {
					for (String entity :  record.getCa_entities()) {
						Element childName = new Element("name", namespace);
						Element childNamePart = new Element("namePart", namespace);
						childNamePart.addContent(entity);
						childName.addContent(childNamePart);
						Element childNameRole = new Element("role", namespace);
						Element childNameRoleTerm = new Element("roleTerm", namespace);
						childNameRoleTerm.addContent("creator");
						childNameRole.addContent(childNameRoleTerm);
						childName.addContent(childNameRole);
						rootElement.addContent(childName);
					}
					
				}
				if(null != recordAr.getCa_entities()) {
					for (String entity :  recordAr.getCa_entities()) {
						Element childName = new Element("name", namespace);
						Element childNamePart = new Element("namePart", namespace);
						childNamePart.addContent(entity);
						childNamePart.setAttribute("type","ar");
						childName.addContent(childNamePart);
						Element childNameRole = new Element("role", namespace);
						Element childNameRoleTerm = new Element("roleTerm", namespace);
						childNameRoleTerm.addContent("creator");
						childNameRole.addContent(childNameRoleTerm);
						childName.addContent(childNameRole);
						rootElement.addContent(childName);
					}
					
				}
				//language
				if(null != record.getLanguage()){
					for(String languageid : record.getLanguage()) {
						Element childLanguage = new Element("language", namespace);
						Element childLanguageTermText = new Element("languageTerm", namespace);
						childLanguageTermText.addContent(language.get(Integer.valueOf(languageid)));
						childLanguageTermText.setAttribute("type", "text");
						childLanguage.addContent(childLanguageTermText);
						rootElement.addContent(childLanguage);
					}
				}
				//typeofresource
				if(null != record.getType_id()){
					Element childTypeOfResource = new Element("typeOfResource", namespace);
					childTypeOfResource.addContent(type.get(Integer.valueOf(record.getType_id())).toLowerCase());
					rootElement.addContent(childTypeOfResource);
				}
				//format /genre
				if(null != record.getAudio_format()) {
					for (String genre : record.getAudio_format()) {
						Element childTypeGenre = new Element("genre", namespace);
						childTypeGenre.addContent(format.get(Integer.valueOf(genre)).toLowerCase());
						rootElement.addContent(childTypeGenre);
					}
				}
				if(null != record.getText_format()) {
					for (String genre : record.getText_format()) {
						Element childTypeGenre = new Element("genre", namespace);
						childTypeGenre.addContent(format.get(Integer.valueOf(genre)).toLowerCase());
						rootElement.addContent(childTypeGenre);
					}
				}
				if(null != record.getImage_format()) {
					for (String genre : record.getImage_format()) {
						Element childTypeGenre = new Element("genre", namespace);
						childTypeGenre.addContent(format.get(Integer.valueOf(genre)).toLowerCase());
						rootElement.addContent(childTypeGenre);
					}
				}
				if(null != record.getVideo_format()) {
					for (String genre : record.getVideo_format()) {
						Element childTypeGenre = new Element("genre", namespace);
						childTypeGenre.addContent(format.get(Integer.valueOf(genre)).toLowerCase());
						rootElement.addContent(childTypeGenre);
					}
				}
				//series
				if(null != record.getCa_collections()) {
					for (String collection_label : record.getCa_collections()) {
						Element childRelatedItem = new Element("relatedItem", namespace);
						childRelatedItem.setAttribute("type", "series");
						Element childTitleInfo = new Element("titleInfo", namespace);
						Element childTitle = new Element("title", namespace);
						childTitle.addContent(collection_label);
						childTitleInfo.addContent(childTitle);
						childRelatedItem.addContent(childTitleInfo);
						rootElement.addContent(childRelatedItem);
					}
					
				}
				if(null != recordAr.getCa_collections()) {
					for (String collection_label : recordAr.getCa_collections()) {
						Element childRelatedItem = new Element("relatedItem", namespace);
						childRelatedItem.setAttribute("type", "series");
						Element childTitleInfo = new Element("titleInfo", namespace);
						Element childTitle = new Element("title", namespace);
						childTitle.addContent(collection_label);
						childTitle.setAttribute("lang", "ar");
						childTitleInfo.addContent(childTitle);
						childRelatedItem.addContent(childTitleInfo);
						rootElement.addContent(childRelatedItem);
					}
					
				}
				//Repository
				Element childLocation = new Element("location", namespace);
				Element childPhysicalLocation = new Element("physicalLocation", namespace);
				childPhysicalLocation.addContent("The Palestinian Museum");
				childPhysicalLocation.setAttribute("type", "repository");
				childPhysicalLocation.setAttribute("displayLabel", "Repository");
				childLocation.addContent(childPhysicalLocation);
				rootElement.addContent(childLocation);
				
				
				//Add Collection
				Element childRelatedItem = new Element("relatedItem", namespace);
				childRelatedItem.setAttribute("type", "host");
				Element childTitleInfo = new Element("titleInfo", namespace);
				Element childTitle = new Element("title", namespace);
				childTitle.addContent("The Palestinian Museum");
				childTitleInfo.addContent(childTitle);
				childRelatedItem.addContent(childTitleInfo);
				rootElement.addContent(childRelatedItem);
				
				// add idep porgram for cross collection search
				Element childRelatedItemProgram = new Element("relatedItem", namespace);
				childRelatedItemProgram.setAttribute("type", "program");
				Element childTitleInfoProgram = new Element("titleInfo", namespace);
				Element childTitleProgram = new Element("title", namespace);
				childTitleProgram.addContent("International Digital Ephemera Project");
				childTitleInfoProgram.addContent(childTitleProgram);
				childRelatedItemProgram.addContent(childTitleInfoProgram);
				rootElement.addContent(childRelatedItemProgram);
				
				jdomDoc.setRootElement(rootElement);
				XMLOutputter xmlOutput = new XMLOutputter();
	
				// passsed System.out to see document content on console
				// xmlOutput.output(jdomDoc, System.out);
	
				// passed fileWriter to write content in specified file
				xmlOutput.setFormat(Format.getPrettyFormat());
				xmlOutput.output(jdomDoc,
						new FileWriter("\\\\svm_dlib\\DLIngest\\palmu\\Dec12-2019\\"
								+ record.getIdno().replace('.', '_')+".xml"));
			}
		//System.out.println(palmuJson.toString());
        }

	}

}
