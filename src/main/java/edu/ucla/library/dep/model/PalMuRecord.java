package edu.ucla.library.dep.model;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PalMuRecord {
	private String object_id;
	private String id;
	private String idno;
	private String display_label;
	private String type_id;
	private String[] ca_collections;
	private String[] ca_places;
	private String[] ca_entities;
	private String[] language;
	private String[] audio_format;
	private String[] text_format;
	private String[] image_format;
	private String[] video_format;
	private String description;
	private String[] preview_url;
	private String[] medium_url;
	private String[] original_url;
	private String date_created;
	
	public String getObject_id() {
		return object_id;
	}
	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
	}
	public String getDisplay_label() {
		return display_label;
	}
	public void setDisplay_label(String display_label) {
		this.display_label = display_label;
	}
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public String[] getCa_collections() {
		return ca_collections;
	}
	public void setCa_collections(String[] ca_collections) {
		this.ca_collections = ca_collections;
	}
	public String[] getCa_places() {
		return ca_places;
	}
	public void setCa_places(String[] ca_places) {
		this.ca_places = ca_places;
	}
	public String[] getCa_entities() {
		return ca_entities;
	}
	public void setCa_entities(String[] ca_entities) {
		this.ca_entities = ca_entities;
	}
	public String[] getLanguage() {
		return language;
	}
	public void setLanguage(String[] language) {
		this.language = language;
	}
	public String[] getAudio_format() {
		return audio_format;
	}
	public void setAudio_format(String[] audio_format) {
		this.audio_format = audio_format;
	}
	public String[] getText_format() {
		return text_format;
	}
	public void setText_format(String[] text_format) {
		this.text_format = text_format;
	}
	public String[] getImage_format() {
		return image_format;
	}
	public void setImage_format(String[] image_format) {
		this.image_format = image_format;
	}
	public String[] getVideo_format() {
		return video_format;
	}
	public void setVideo_format(String[] video_format) {
		this.video_format = video_format;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@JsonProperty("ca_object_representations.media.preview170.url") 
	public String[] getPreview_url() {
		return preview_url;
	}
	public void setPreview_url(String[] preview_url) {
		this.preview_url = preview_url;
	}
	@JsonProperty("ca_object_representations.media.medium.url")
	public String[] getMedium_url() {
		return medium_url;
	}
	public void setMedium_url(String[] medium_url) {
		this.medium_url = medium_url;
	}
	@JsonProperty("ca_object_representations.media.original.url")
	public String[] getOriginal_url() {
		return original_url;
	}
	public void setOriginal_url(String[] original_url) {
		this.original_url = original_url;
	}
	@JsonProperty("ca_objects.date.dates_value")
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	@Override
	public String toString() {
		return "PalMuRecord [object_id=" + object_id + ", id=" + id + ", idno=" + idno + ", display_label="
				+ display_label + ", type_id=" + type_id + ", ca_collections=" + Arrays.toString(ca_collections)
				+ ", ca_places=" + Arrays.toString(ca_places) + ", ca_entities=" + Arrays.toString(ca_entities)
				+ ", language=" + Arrays.toString(language) + ", audio_format=" + Arrays.toString(audio_format)
				+ ", text_format=" + Arrays.toString(text_format) + ", image_format=" + Arrays.toString(image_format)
				+ ", video_format=" + Arrays.toString(video_format) + ", description=" + description + ", preview_url="
				+ Arrays.toString(preview_url) + ", medium_url=" + Arrays.toString(medium_url) + ", date_created="
				+ date_created + "]";
	}

}
