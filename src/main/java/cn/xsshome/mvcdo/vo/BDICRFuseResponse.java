package cn.xsshome.mvcdo.vo;

public class BDICRFuseResponse extends RestResponse{
	/**
	 * 识别的名称
	 */
	private String icrName;
	/**
	 * 识别的分数
	 */
	private String score;
	/**
	 * 识别的年份
	 */
	private String year;
	/**
	 * 识别的颜色
	 */
	private String colorResult;
	/**
	 * 置信度值，0-1
	 */
	private String probability;
	/**
	 * 百度百科url
	 */
	private String baikeUrl;
	/**
	 * 百科图片链接
	 */
	private String imageUrl;
	/**
	 * 百科内容描述|酒品描述
	 */
	private String description;
	/**
	 * 判断是否返回详细信息（除红酒中文名之外的其他字段），含有返回1，不含有返回0
	 */
	private String hasdetail;
	/**
	 * 红酒中文名
	 */
	private String wineNameCn;
	/**
	 * 红酒英文名
	 */
	private String wineNameEn;
	/**
	 * 国家中文名
	 */
	private String countryCn;
	/**
	 * 国家英文名
	 */
	private String countryEn;
	/**
	 * 产区中文名
	 */
	private String regionCn;
	/**
	 * 产区英文名
	 */
	private String regionEn;
	/**
	 * 子产区中文名
	 */
	private String subRegionCn;
	/**
	 * 子产区英文名
	 */
	private String subRegionEn;
	/**
	 * 酒庄中文名
	 */
	private String wineryCn;
	/**
	 * 酒庄英文名
	 */
	private String wineryEn;
	/**
	 * 酒类型
	 */
	private String classifyByColor;
	/**
	 * 糖分类型
	 */
	private String classifyBySugar;
	/**
	 * 色泽
	 */
	private String color;
	/**
	 * 葡萄品种
	 */
	private String grapeCn;
	/**
	 * 葡萄品种英文名
	 */
	private String grapeEn;
	/**
	 * 品尝温度
	 */
	private String tasteTemperature;
	public String getIcrName() {
		return icrName;
	}
	public void setIcrName(String icrName) {
		this.icrName = icrName;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getColorResult() {
		return colorResult;
	}
	public void setColorResult(String colorResult) {
		this.colorResult = colorResult;
	}
	public String getProbability() {
		return probability;
	}
	public void setProbability(String probability) {
		this.probability = probability;
	}
	public String getBaikeUrl() {
		return baikeUrl;
	}
	public void setBaikeUrl(String baikeUrl) {
		this.baikeUrl = baikeUrl;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHasdetail() {
		return hasdetail;
	}
	public void setHasdetail(String hasdetail) {
		this.hasdetail = hasdetail;
	}
	public String getWineNameCn() {
		return wineNameCn;
	}
	public void setWineNameCn(String wineNameCn) {
		this.wineNameCn = wineNameCn;
	}
	public String getWineNameEn() {
		return wineNameEn;
	}
	public void setWineNameEn(String wineNameEn) {
		this.wineNameEn = wineNameEn;
	}
	public String getCountryCn() {
		return countryCn;
	}
	public void setCountryCn(String countryCn) {
		this.countryCn = countryCn;
	}
	public String getCountryEn() {
		return countryEn;
	}
	public void setCountryEn(String countryEn) {
		this.countryEn = countryEn;
	}
	public String getRegionCn() {
		return regionCn;
	}
	public void setRegionCn(String regionCn) {
		this.regionCn = regionCn;
	}
	public String getRegionEn() {
		return regionEn;
	}
	public void setRegionEn(String regionEn) {
		this.regionEn = regionEn;
	}
	public String getSubRegionCn() {
		return subRegionCn;
	}
	public void setSubRegionCn(String subRegionCn) {
		this.subRegionCn = subRegionCn;
	}
	public String getSubRegionEn() {
		return subRegionEn;
	}
	public void setSubRegionEn(String subRegionEn) {
		this.subRegionEn = subRegionEn;
	}
	public String getWineryCn() {
		return wineryCn;
	}
	public void setWineryCn(String wineryCn) {
		this.wineryCn = wineryCn;
	}
	public String getWineryEn() {
		return wineryEn;
	}
	public void setWineryEn(String wineryEn) {
		this.wineryEn = wineryEn;
	}
	public String getClassifyByColor() {
		return classifyByColor;
	}
	public void setClassifyByColor(String classifyByColor) {
		this.classifyByColor = classifyByColor;
	}
	public String getClassifyBySugar() {
		return classifyBySugar;
	}
	public void setClassifyBySugar(String classifyBySugar) {
		this.classifyBySugar = classifyBySugar;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getGrapeCn() {
		return grapeCn;
	}
	public void setGrapeCn(String grapeCn) {
		this.grapeCn = grapeCn;
	}
	public String getGrapeEn() {
		return grapeEn;
	}
	public void setGrapeEn(String grapeEn) {
		this.grapeEn = grapeEn;
	}
	public String getTasteTemperature() {
		return tasteTemperature;
	}
	public void setTasteTemperature(String tasteTemperature) {
		this.tasteTemperature = tasteTemperature;
	}
	
}
