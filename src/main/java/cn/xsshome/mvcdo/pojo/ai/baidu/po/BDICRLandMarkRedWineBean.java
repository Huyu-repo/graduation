package cn.xsshome.mvcdo.pojo.ai.baidu.po;
/**
 * 接口返回的对象 针对于landmark,redwine通用
 * @author 小帅丶
 * @date 2019年3月19日
 * <p>Description: landmark,redwine融合对象</p>
 */
public class BDICRLandMarkRedWineBean {
	private String error_msg;
	private String error_code;
	private long log_id;
	private Result result;
	
	public long getLog_id() {
		return log_id;
	}

	public void setLog_id(long log_id) {
		this.log_id = log_id;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public static class Result{
		private String landmark;
		private String hasdetail;
		private String wineNameCn;
		private String wineNameEn;
		private String countryCn;
		private String countryEn;
		private String regionCn;
		private String regionEn;
		private String subRegionCn;
		private String subRegionEn;
		private String wineryCn;
		private String wineryEn;
		private String classifyByColor;
		private String classifyBySugar;
		private String color;
		private String grapeCn;
		private String grapeEn;
		private String tasteTemperature;
		private String description;
		public String getLandmark() {
			return landmark;
		}
		public void setLandmark(String landmark) {
			this.landmark = landmark;
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
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
	}
}
