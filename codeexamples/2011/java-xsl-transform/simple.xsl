<xsl:stylesheet xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
    version='1.0'>
	<xsl:output method='xml' version='1.0' encoding='UTF-8' indent='yes'/>
    <xsl:template match='/data'>
		<h1>Data</h1>
		<xsl:for-each select='book' >
			<xsl:value-of select='@title'/><br/>
		</xsl:for-each>
    </xsl:template>
</xsl:stylesheet>