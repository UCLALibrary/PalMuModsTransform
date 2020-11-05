<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:mods="http://www.loc.gov/mods/v3"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xs="http://www.w3.org/2001/XMLSchema"
	exclude-result-prefixes="xs" version="2.0">
	<xsl:output method="xml" version="1.0" encoding="UTF-8"
		indent="yes" />
	<xsl:strip-space elements="*" />
	<xsl:template match="node() | @*">
		<xsl:copy>
			<xsl:apply-templates select="@* | node()" />
		</xsl:copy>
	</xsl:template>

	<xsl:template match="mods:mods">
		<xsl:copy>
			<xsl:apply-templates select="node() | @*" />
			<!-- <mods:relatedItem type="program">
				<mods:titleInfo>
					<mods:title>
						International Digital Ephemera Project
					</mods:title>
				</mods:titleInfo>
			</mods:relatedItem> -->
			<mods:relatedItem type="host">
				<mods:titleInfo>
					<mods:title>
						Palestinian Museum
					</mods:title>
				</mods:titleInfo>
			</mods:relatedItem>
		</xsl:copy>

	</xsl:template>
	<xsl:template match="mods:name[not(mods:role)]">
		<xsl:copy>
			<xsl:apply-templates select="node() | @*" />
			<mods:role>
				<mods:roleTerm>contributor</mods:roleTerm>
			</mods:role>
		</xsl:copy>

	</xsl:template>
	<xsl:template match="mods:languageTerm[. eq 'en']">
		<mods:languageTerm type="text">English</mods:languageTerm>
		<mods:languageTerm type="code" authority="iso639-2b">eng
		</mods:languageTerm>

	</xsl:template>

</xsl:stylesheet>