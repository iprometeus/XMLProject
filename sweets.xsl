<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
<xsl:output method="html"/>
	<xsl:template match="/">
		<html>
			<head>
				<style type="text/css">
					table {
						border-collapse: collapse;
					}

					table, td {
						border: 1px solid black;
						background-color: white;
						color: black;
						text-align:center;
					}

					th {
						border: 1px solid black;
						background-color: #dadada;
					}

				</style>
			</head>

			<body>
				<table class="tfmt">
					<tr>
						<th style="width:250px" rowspan="2">Name:</th>
						<th style="width:60px" rowspan="2">Energy:</th>
						<th style="width:200px" rowspan="2">Type:</th>
						<th style="width:500px" colspan="5">Ingredients:</th>
						<th style="width:300px" colspan="3">Value:</th>
						<th style="width:250px" rowspan="2">Production:</th>
					</tr>
					<tr>
						<th>Water:</th>
						<th>Sugar:</th>
						<th>Fruit sugar:</th>
						<th>Vanillin:</th>
						<th>Chocolate:</th>
						<th>Proteins:</th>
						<th>Carbohydrates:</th>
						<th>Fats:</th>
					</tr>

					<xsl:for-each select="sweets/candy">
						<tr>
							<td>
								<xsl:value-of select="name" />
							</td>
							<td>
								<xsl:value-of select="energy" />
							</td>
							<td>
								<xsl:value-of select="type/name" />
							</td>
							<td>
								<xsl:value-of select="ingredients/water" />
							</td>
							<td>
								<xsl:value-of select="ingredients/sugar" />
							</td>
							<td>
								<xsl:value-of select="ingredients/fruitSugar" />
							</td>
							<td>
								<xsl:value-of select="ingredients/vanillin" />
							</td>
							<td>
								<xsl:value-of select="ingredients/chocolate/name" />
							</td>
							<td>
								<xsl:value-of select="value/proteins" />
							</td>
							<td>
								<xsl:value-of select="value/carbohydrates" />
							</td>
							<td>
								<xsl:value-of select="value/fats" />
							</td>
							<td>
								<xsl:value-of select="production/name" />
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>